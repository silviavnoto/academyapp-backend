-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: database:3306
-- Tiempo de generación: 19-02-2025 a las 18:23:48
-- Versión del servidor: 8.4.2
-- Versión de PHP: 8.2.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `academy`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clase`
--

CREATE TABLE `clase` (
  `id` bigint NOT NULL,
  `asignatura` varchar(255) CHARACTER SET utf32 COLLATE utf32_unicode_ci NOT NULL,
  `precio` decimal(5,2) NOT NULL,
  `hora` decimal(4,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `clase`
--

INSERT INTO `clase` (`id`, `asignatura`, `precio`, `hora`) VALUES
(3, 'Tecnología', 802.07, 5.00),
(4, 'Literatura', 107.70, 1.00),
(5, 'Francés', 857.91, 3.00),
(6, 'Tecnología', 857.91, 1.00),
(7, 'Informática', 326.28, 6.00),
(8, 'Informática', 326.28, 1.00),
(9, 'Alemán', 326.28, 5.00),
(10, 'Ciencias Sociales', 363.24, 0.50),
(11, 'Inglés', 601.82, 5.00),
(12, 'Informática', 579.15, 5.00),
(13, 'Inglés', 454.70, 6.00),
(14, 'Literatura', 668.22, 2.00),
(15, 'Alemán', 802.07, 5.00),
(16, 'Alemán', 107.70, 1.00),
(17, 'Tecnología', 744.87, 4.00),
(18, 'Informática', 744.87, 1.00),
(19, 'Química', 601.82, 0.50),
(20, 'Literatura', 98.42, 5.00),
(21, 'Alemán', 668.22, 4.00),
(22, 'Química', 98.42, 2.00),
(23, 'Química', 107.70, 6.00),
(24, 'Tecnología', 668.22, 4.00),
(25, 'Tecnología', 450.34, 6.00),
(26, 'Matemáticas', 10.25, 2.00),
(27, 'aaaaaaaaa', 1.00, 1.00),
(28, 'uuu', 10.00, 10.00),
(29, 'pppp', 11.00, 11.00),
(30, 'ttt', 10.00, 10.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `participa`
--

CREATE TABLE `participa` (
  `id` bigint NOT NULL,
  `id_usuario` bigint NOT NULL,
  `id_clase` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE `tipousuario` (
  `id` bigint NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf32 COLLATE utf32_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`id`, `descripcion`) VALUES
(1, 'Administrador'),
(2, 'Contable'),
(3, 'Auditor'),
(4, 'Administrador'),
(5, 'Contable'),
(6, 'Auditor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) CHARACTER SET utf32 COLLATE utf32_unicode_ci NOT NULL,
  `apellido1` varchar(255) CHARACTER SET utf32 COLLATE utf32_unicode_ci NOT NULL,
  `apellido2` varchar(255) CHARACTER SET utf32 COLLATE utf32_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf32 COLLATE utf32_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf32_unicode_ci DEFAULT NULL,
  `id_tipousuario` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellido1`, `apellido2`, `email`, `password`, `id_tipousuario`) VALUES
(1, 'Rosa', 'Escriche', 'Gonzalez', 'emailRosa3517@gmail.com', '7e4b4f5529e084ecafb996c891cfbd5b5284f5b00dc155c37bbb62a9f161a72e', 1),
(2, 'Rafa', 'Rodriguez', 'Fernandez', 'emailRafa2149@gmail.com', '7e4b4f5529e084ecafb996c891cfbd5b5284f5b00dc155c37bbb62a9f161a72e', 2),
(3, 'Ignacio', 'Sanchez', 'Fernandez', 'emailIgnacio8900@gmail.com', '7e4b4f5529e084ecafb996c891cfbd5b5284f5b00dc155c37bbb62a9f161a72e', 3),
(4, 'Lorenzo', 'Gonzalez', 'Vidal', 'emailLorenzo9309@gmail.com', '', 1),
(5, 'Analia', 'Martinez', 'Garcia', 'emailAna3579@gmail.com', '', 3),
(6, 'Luis', 'Pérez', 'Garcia', 'emailLuis5642@gmail.com', '', 3),
(7, 'Ignacio', 'Gonzalez', 'Hermoso', 'emailIgnacio5912@gmail.com', '', 1),
(8, 'Rosa', 'Gomez', 'Pérez', 'emailRosa1816@gmail.com', '', 2),
(9, 'Lorenzo', 'Garcia', 'Pérez', 'emailLorenzo3254@gmail.com', '', 1),
(10, 'Rocio', 'Hermoso', 'Pérez', 'emailRocio3989@gmail.com', '', 1),
(11, 'Marta', 'Pérez', 'Moreno', 'emailMarta1978@gmail.com', '', 2),
(12, 'Pepe', 'Escriche', 'Sancho', 'emailPepe9370@gmail.com', '', 1),
(13, 'Pepe', 'Fernandez', 'Gonzalez', 'emailPepe1395@gmail.com', '', 2),
(14, 'Sara', 'Gomez', 'Gonzalez', 'emailSara2191@gmail.com', '', 3),
(15, 'Maria', 'Gomez', 'Vidal', 'emailMaria4964@gmail.com', '', 3),
(16, 'Rocio', 'Moreno', 'Pérez', 'emailRocio4965@gmail.com', '', 1),
(17, 'Ignacio', 'Hermoso', 'Gonzalez', 'emailIgnacio5391@gmail.com', '', 3),
(18, 'Lorenzo', 'Feliu', 'Gimenez', 'emailLorenzo5074@gmail.com', '', 1),
(19, 'Rosa', 'Pérez', 'Sancho', 'emailRosa8144@gmail.com', '', 2),
(20, 'Rafa', 'Fernandez', 'Moreno', 'emailRafa3035@gmail.com', '', 3),
(21, 'Rocio', 'Rodriguez', 'Rodriguez', 'emailRocio1448@gmail.com', '', 1),
(22, 'Carmen', 'Garcia', 'Feliu', 'emailCarmen1289@gmail.com', '', 1),
(23, 'Carmen', 'Pérez', 'Pérez', 'emailCarmen5165@gmail.com', '', 1),
(24, 'Ana', 'Sanchez', 'Pérez', 'emailAna7058@gmail.com', '', 3),
(25, 'Carmen', 'Vidal', 'Escriche', 'emailCarmen4168@gmail.com', '', 3),
(26, 'Carmen', 'Sancho', 'Sancho', 'emailCarmen5962@gmail.com', '', 3),
(27, 'Maria', 'Gonzalez', 'Vidal', 'emailMaria9926@gmail.com', '', 6),
(28, 'Rocio', 'Sancho', 'Gonzalez', 'emailRocio3928@gmail.com', '', 5),
(29, 'Lorenzo', 'Lopez', 'Martinez', 'emailLorenzo4600@gmail.com', '', 1),
(30, 'Rafa', 'Sanchez', 'Moreno', 'emailRafa5635@gmail.com', '', 2),
(31, 'Carmen', 'Rodriguez', 'Gonzalez', 'emailCarmen4107@gmail.com', '', 5),
(32, 'Ignacio', 'Feliu', 'Gonzalez', 'emailIgnacio4620@gmail.com', '', 3),
(33, 'Sara', 'Rodriguez', 'Pérez', 'emailSara5261@gmail.com', '', 5),
(34, 'Rafa', 'Martinez', 'Feliu', 'emailRafa4960@gmail.com', '', 3),
(35, 'Laura', 'Fernandez', 'Escriche', 'emailLaura8143@gmail.com', '', 2),
(36, 'Carmen', 'Gonzalez', 'Moreno', 'emailCarmen2893@gmail.com', '', 6),
(37, 'Manolo', 'Sancho', 'Gonzalez', 'emailManolo1735@gmail.com', '', 6),
(38, 'Ana', 'Gonzalez', 'Vidal', 'emailAna8991@gmail.com', '', 3),
(39, 'Pepe', 'Lopez', 'Vidal', 'emailPepe3611@gmail.com', '', 4),
(40, 'Luis', 'Feliu', 'Sanchez', 'emailLuis9018@gmail.com', '', 2),
(41, 'Lorenzo', 'Hermoso', 'Martinez', 'emailLorenzo6148@gmail.com', '', 5),
(42, 'Ana', 'Pérez', 'Vidal', 'emailAna2796@gmail.com', '', 1),
(43, 'Maria', 'Hermoso', 'Lopez', 'emailMaria1088@gmail.com', '', 4),
(44, 'Laura', 'Gonzalez', 'Fernandez', 'emailLaura9700@gmail.com', '', 3),
(45, 'Ignacio', 'Escriche', 'Gonzalez', 'emailIgnacio8663@gmail.com', '', 4),
(46, 'Lucia', 'Moreno', 'Lopez', 'emailLucia7900@gmail.com', '', 4),
(47, 'Paco', 'Fernandez', 'Vidal', 'emailPaco6099@gmail.com', '', 6),
(48, 'Carmen', 'Sanchez', 'Garcia', 'emailCarmen2464@gmail.com', '', 6),
(49, 'Sara', 'Fernandez', 'Gonzalez', 'emailSara4954@gmail.com', '', 5),
(50, 'Rocio', 'Vidal', 'Sanchez', 'emailRocio4115@gmail.com', '', 6),
(51, 'hghjhh', 'hhhhh', 'hhhhh', 'hghgg@gmail.com', 'ausias', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clase`
--
ALTER TABLE `clase`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `participa`
--
ALTER TABLE `participa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK63x9okjt2ld5ohysubomr6pos` (`id_clase`),
  ADD KEY `FK9bwjbnmbl51obq46avbwmslqv` (`id_usuario`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK142plrytoogsme2hd0d9xm7c0` (`id_tipousuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clase`
--
ALTER TABLE `clase`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `participa`
--
ALTER TABLE `participa`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `participa`
--
ALTER TABLE `participa`
  ADD CONSTRAINT `FK63x9okjt2ld5ohysubomr6pos` FOREIGN KEY (`id_clase`) REFERENCES `clase` (`id`),
  ADD CONSTRAINT `FK9bwjbnmbl51obq46avbwmslqv` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FK142plrytoogsme2hd0d9xm7c0` FOREIGN KEY (`id_tipousuario`) REFERENCES `tipousuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
