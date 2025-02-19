package net.ausiasmarch.academyapp.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.ausiasmarch.academyapp.bean.LogindataBean;
import net.ausiasmarch.academyapp.entity.UsuarioEntity;
import net.ausiasmarch.academyapp.exception.UnauthorizedAccessException;
import net.ausiasmarch.academyapp.repository.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    JWTService JWTHelper;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    public boolean checkLogin(LogindataBean oLogindataBean) {
        if (oUsuarioRepository.findByEmailAndPassword(oLogindataBean.getEmail(), oLogindataBean.getPassword())
                .isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    private Map<String, String> getClaims(String email) {
        Map<String, String> claims = new HashMap<>();
        claims.put("email", email);
        return claims;
    };

    public String getToken(String email) {
        return JWTHelper.generateToken(getClaims(email));
    }

    public UsuarioEntity getUsuarioFromToken() {
        if (oHttpServletRequest.getAttribute("email") == null) {
            throw new UnauthorizedAccessException("No hay usuario en la sesión");
        } else {
            String email = oHttpServletRequest.getAttribute("email").toString();
            return oUsuarioRepository.findByEmail(email).get();
        }                
    }

    public boolean isSessionActive() {
        return oHttpServletRequest.getAttribute("email") != null;
    }

    public boolean isAdmin() {
        return this.getUsuarioFromToken().getTipousuario().getId() == 1L;
    }

    public boolean isContable() {
        return this.getUsuarioFromToken().getTipousuario().getId() == 2L;
    }

    public boolean isAuditor() {
        return this.getUsuarioFromToken().getTipousuario().getId() == 3L;
    }

    public boolean isAdminOrContable() {
        return this.isAdmin() || this.isContable();
    }

    public boolean isContableWithItsOwnData(Long id) {
        UsuarioEntity oUsuarioEntity = this.getUsuarioFromToken();
        return this.isContable() && oUsuarioEntity.getId() == id;
    }

    public boolean isAuditorWithItsOwnData(Long id) {
        UsuarioEntity oUsuarioEntity = this.getUsuarioFromToken();
        return this.isAuditor() && oUsuarioEntity.getId() == id;
    }

    public boolean isUserAuthenticated() {
        return oHttpServletRequest.getUserPrincipal() != null;
    }    
    

}
