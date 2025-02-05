package net.ausiasmarch.academyapp.exception;

public class ResourceNotModifiedException extends RuntimeException {
    public ResourceNotModifiedException(String mensaje) {
        super(mensaje);
    }
}
