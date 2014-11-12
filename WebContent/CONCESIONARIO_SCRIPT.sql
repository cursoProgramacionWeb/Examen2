-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.0.45-community-nt - MySQL Community Edition (GPL)
-- SO del servidor:              Win32
-- HeidiSQL Versión:             8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para concesionario
DROP DATABASE IF EXISTS `concesionario`;
CREATE DATABASE IF NOT EXISTS `concesionario` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `concesionario`;


-- Volcando estructura para tabla concesionario.vehiculo
DROP TABLE IF EXISTS `vehiculo`;
CREATE TABLE IF NOT EXISTS `vehiculo` (
  `id` int(4) NOT NULL auto_increment,
  `matricula` varchar(10) NOT NULL,
  `marca` varchar(20) NOT NULL,
  `modelo` varchar(4) NOT NULL,
  `color` varchar(20) NOT NULL,
  `numeroCaballos` int(3) NOT NULL,
  `marchas` tinyint(5) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `matricula` (`matricula`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla concesionario.vehiculo: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` (`id`, `matricula`, `marca`, `modelo`, `color`, `numeroCaballos`, `marchas`) VALUES
	(1, '6182-FSH', 'Peugeot 807', '1997', 'Gris Marengo', 136, 1),
	(3, '4858-DMR', 'Opel Corsa', '1996', 'Blanco', 90, 0),
	(4, '9589-DBG', 'Seat Toledo', '1995', 'Rojo', 105, 1),
	(7, '6248-FGT', 'Renault Megane', '2011', 'Verde', 104, 1),
	(8, '3698-DRT', 'Peugeot 205', '1983', 'Rosa', 78, 0);
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
