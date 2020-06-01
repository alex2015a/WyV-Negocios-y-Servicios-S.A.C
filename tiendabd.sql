-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-06-2020 a las 01:42:12
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tiendabd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`codigo`, `nombre`, `descripcion`) VALUES
(1, 'Piel', 'Para piel'),
(2, 'Perfume', 'Para hombre'),
(3, 'ColoniaEDIT', 'Para mujer');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idcli` int(11) NOT NULL,
  `nomCli` varchar(100) NOT NULL,
  `teleCli` varchar(9) NOT NULL,
  `direCli` varchar(100) NOT NULL,
  `Email` varchar(200) NOT NULL,
  `Dni` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idcli`, `nomCli`, `teleCli`, `direCli`, `Email`, `Dni`) VALUES
(3, 'Angel1', '1245789', 'villa', 'chorillos', '54897612'),
(5, 'luis', '45797562', 'villa', 'luis@hotmail.com', '45879634');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

CREATE TABLE `detalle` (
  `can` int(11) NOT NULL,
  `precio` double(10,2) NOT NULL,
  `importe` double(10,2) NOT NULL,
  `num_venta` char(10) NOT NULL,
  `codPro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `codPro` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `descuento` int(11) NOT NULL,
  `proveedor` varchar(100) NOT NULL,
  `preciocom` double(10,2) NOT NULL,
  `precioven` double(10,2) NOT NULL,
  `codigocat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`codPro`, `nombre`, `cantidad`, `descuento`, `proveedor`, `preciocom`, `precioven`, `codigocat`) VALUES
(1, 'Crema', 12, 10, 'Esika', 15.00, 12.00, 3),
(2, 'PERFUME GRAZZIA EXOTIC', 12, 20, 'esika', 12.00, 60.00, 3),
(3, 'Perfume flue', 12, 30, 'Label', 12.00, 60.00, 3),
(8, 'Crema para acne', 15, 0, 'Esika', 12.00, 15.00, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `dni` char(8) NOT NULL,
  `nom` varchar(40) NOT NULL,
  `pass` varchar(10) NOT NULL,
  `tipo` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`dni`, `nom`, `pass`, `tipo`) VALUES
('64896475', 'Angel', '223', '2'),
('73184116', 'Bryan', '12345', '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `num` char(10) NOT NULL,
  `fec` varchar(20) NOT NULL,
  `pago` double(10,2) NOT NULL,
  `total` double(10,2) NOT NULL,
  `estado` int(1) NOT NULL,
  `idcli` int(11) NOT NULL,
  `dni_usu` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idcli`);

--
-- Indices de la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD PRIMARY KEY (`num_venta`,`codPro`),
  ADD KEY `codPro` (`codPro`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`codPro`),
  ADD KEY `codigocat` (`codigocat`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`num`),
  ADD KEY `idcli` (`idcli`),
  ADD KEY `dni_usu` (`dni_usu`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idcli` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `codPro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD CONSTRAINT `detalle_ibfk_1` FOREIGN KEY (`num_venta`) REFERENCES `venta` (`num`),
  ADD CONSTRAINT `detalle_ibfk_2` FOREIGN KEY (`codPro`) REFERENCES `producto` (`codPro`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`codigocat`) REFERENCES `categoria` (`codigo`);

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`idcli`) REFERENCES `cliente` (`idcli`),
  ADD CONSTRAINT `venta_ibfk_2` FOREIGN KEY (`dni_usu`) REFERENCES `usuario` (`dni`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
