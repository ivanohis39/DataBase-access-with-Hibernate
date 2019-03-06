-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-11-2018 a las 14:06:02
-- Versión del servidor: 5.6.24
-- Versión de PHP: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `multas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conductor`
--

CREATE TABLE IF NOT EXISTS `conductor` (
  `NIF` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `apellidos` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  `direccion` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  `poblacion` varchar(200) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `provincia` varchar(100) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `telefono` int(14) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `conductor`
--

INSERT INTO `conductor` (`NIF`, `nombre`, `apellidos`, `direccion`, `poblacion`, `provincia`, `telefono`) VALUES
('33567756H', 'Sergio', 'Dominguez Lucas', 'Avda. Extremadura, 16', 'Oliva de Plasencia', 'Cáceres', 927563456),
('70806676K', 'Beatriz', 'Dominguez Tovar', 'Calle la Vera, 21, 4B', 'Navaconcejo', 'Cáceres', NULL),
('77807658G', 'Antonio', 'Gómez Becerra', 'Calle Sevilla, 12, 1A', 'Plasencia', 'Cáceres', 927456745);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `multas`
--

CREATE TABLE IF NOT EXISTS `multas` (
  `codmulta` int(11) NOT NULL,
  `matricula` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `fecha` date NOT NULL,
  `lugar` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  `tipo` int(11) NOT NULL,
  `sancion` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `multas`
--

INSERT INTO `multas` (`codmulta`, `matricula`, `fecha`, `lugar`, `tipo`, `sancion`) VALUES
(1, '2093GSV', '2018-11-05', 'Carretera Salamanca, km 21', 3, 300),
(2, '9279HFY', '2018-10-24', 'Avenida Alfonso VIII', 3, 100),
(3, '9279HFY', '2018-05-21', 'Carretera N-VII', 1, 90),
(4, '9309HGY', '2018-04-12', 'Avenida Extremadura,10', 3, 200),
(5, '9309HGY', '2018-11-01', 'Puerta Sol', 1, 100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE IF NOT EXISTS `vehiculo` (
  `matricula` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `NIF` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `marca` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `modelo` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `CV` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `vehiculo`
--

INSERT INTO `vehiculo` (`matricula`, `NIF`, `marca`, `modelo`, `CV`) VALUES
('2093GSV', '70806676K', 'RENAULT', 'MEGANE', 90),
('9279HFY', '77807658G', 'SEAT', 'IBIZA', 90),
('9309HGY', '33567756H', 'SEAT', 'LEÓN', 120);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `conductor`
--
ALTER TABLE `conductor`
  ADD PRIMARY KEY (`NIF`);

--
-- Indices de la tabla `multas`
--
ALTER TABLE `multas`
  ADD PRIMARY KEY (`codmulta`), ADD KEY `fk_matricula_vehiculo` (`matricula`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`matricula`), ADD KEY `fk_NIF_vehiculo` (`NIF`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `multas`
--
ALTER TABLE `multas`
ADD CONSTRAINT `fk_matricula_vehiculo` FOREIGN KEY (`matricula`) REFERENCES `vehiculo` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
ADD CONSTRAINT `fk_NIF_vehiculo` FOREIGN KEY (`NIF`) REFERENCES `conductor` (`NIF`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
