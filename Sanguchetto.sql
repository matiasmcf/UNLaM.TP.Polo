-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 03-05-2017 a las 00:18:02
-- Versión del servidor: 5.7.18-0ubuntu0.16.04.1
-- Versión de PHP: 5.6.30-10+deb.sury.org~xenial+2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `Sanguchetto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Stock`
--

CREATE TABLE `Stock` (
  `ingrediente` text NOT NULL,
  `precio` double NOT NULL,
  `stock` int(11) NOT NULL,
  `tipo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Stock`
--

INSERT INTO `Stock` (`ingrediente`, `precio`, `stock`, `tipo`) VALUES
('Mayonesa', 0.5, 100, 1),
('Ketchup', 0.5, 100, 1),
('Mostaza', 0.5, 100, 1),
('Salsa Golf', 0.5, 100, 1),
('Salsa Bolognesa', 5, 100, 1),
('Salsa Barbacoa', 5, 100, 1),
('Salsa Sanguchetto', 10, 100, 1),
('Jamon', 15.5, 100, 0),
('Queso', 10, 100, 0),
('Carne', 30, 100, 0),
('Pollo', 21.5, 100, 0),
('Pescado', 15.5, 100, 0),
('Cebollas', 5.5, 100, 0),
('Lechuga', 1.5, 100, 0),
('Tomate', 7, 100, 0),
('Pepino', 3, 100, 0),
('Carne de cerdo', 25, 100, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuario`
--

CREATE TABLE `Usuario` (
  `nombre` text NOT NULL,
  `password` text NOT NULL,
  `tipo` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Usuario`
--

INSERT INTO `Usuario` (`nombre`, `password`, `tipo`) VALUES
('admin', 'admin', 'admin'),
('nicolas', 'nicolas', 'cliente'),
('juan', 'juan', 'cliente'),
('jose', 'jose', 'cliente');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
