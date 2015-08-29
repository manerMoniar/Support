/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : soporte

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2013-09-03 17:22:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `etiquetas`
-- ----------------------------
DROP TABLE IF EXISTS `etiquetas`;
CREATE TABLE `etiquetas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of etiquetas
-- ----------------------------
INSERT INTO `etiquetas` VALUES ('1', 'Smartphones');
INSERT INTO `etiquetas` VALUES ('2', 'Impresoras');
INSERT INTO `etiquetas` VALUES ('3', 'Equipo de Audio');
INSERT INTO `etiquetas` VALUES ('4', 'Laptops');
INSERT INTO `etiquetas` VALUES ('5', 'Cámaras');

-- ----------------------------
-- Table structure for `puntuacion`
-- ----------------------------
DROP TABLE IF EXISTS `puntuacion`;
CREATE TABLE `puntuacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `puntos` float NOT NULL,
  `idUsuarioOrigen` int(11) NOT NULL,
  `idUsuarioDestino` int(11) NOT NULL,
  `comentario` varchar(140) CHARACTER SET latin1 DEFAULT NULL,
  `estatus` char(1) CHARACTER SET latin1 NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `puntuacion_ibfk_1` (`idUsuarioOrigen`),
  KEY `idUsuarioDestino` (`idUsuarioDestino`),
  CONSTRAINT `puntuacion_ibfk_1` FOREIGN KEY (`idUsuarioOrigen`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `puntuacion_ibfk_2` FOREIGN KEY (`idUsuarioDestino`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of puntuacion
-- ----------------------------
INSERT INTO `puntuacion` VALUES ('1', '5', '2', '1', null, '1');
INSERT INTO `puntuacion` VALUES ('2', '4', '3', '1', null, '1');
INSERT INTO `puntuacion` VALUES ('3', '5', '8', '1', null, '1');
INSERT INTO `puntuacion` VALUES ('4', '3', '1', '7', null, '1');
INSERT INTO `puntuacion` VALUES ('5', '2', '8', '7', null, '1');
INSERT INTO `puntuacion` VALUES ('6', '3', '5', '7', null, '1');
INSERT INTO `puntuacion` VALUES ('7', '4', '15', '7', null, '1');
INSERT INTO `puntuacion` VALUES ('8', '5', '3', '7', null, '1');
INSERT INTO `puntuacion` VALUES ('9', '4', '5', '14', null, '1');
INSERT INTO `puntuacion` VALUES ('10', '5', '6', '14', null, '1');
INSERT INTO `puntuacion` VALUES ('11', '1', '12', '14', null, '1');
INSERT INTO `puntuacion` VALUES ('12', '3', '2', '3', null, '1');
INSERT INTO `puntuacion` VALUES ('13', '4', '4', '3', null, '1');
INSERT INTO `puntuacion` VALUES ('14', '2', '15', '2', null, '1');
INSERT INTO `puntuacion` VALUES ('15', '3', '14', '6', null, '1');
INSERT INTO `puntuacion` VALUES ('16', '1', '12', '6', null, '1');
INSERT INTO `puntuacion` VALUES ('17', '5', '3', '6', null, '1');
INSERT INTO `puntuacion` VALUES ('18', '2', '12', '11', null, '1');
INSERT INTO `puntuacion` VALUES ('19', '4', '1', '15', null, '1');
INSERT INTO `puntuacion` VALUES ('20', '4', '13', '9', null, '1');
INSERT INTO `puntuacion` VALUES ('21', '3', '8', '15', null, '1');
INSERT INTO `puntuacion` VALUES ('22', '2', '2', '8', null, '1');

-- ----------------------------
-- Table structure for `usuarios`
-- ----------------------------
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET latin1 NOT NULL,
  `contrasenia` varchar(32) CHARACTER SET latin1 NOT NULL,
  `latitud` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `longitud` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `direccion` text CHARACTER SET latin1 NOT NULL,
  `telefono` varchar(255) CHARACTER SET latin1 NOT NULL,
  `email` varchar(255) CHARACTER SET latin1 NOT NULL,
  `activo` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usuarios
-- ----------------------------
INSERT INTO `usuarios` VALUES ('19', 'NEfi34,.', 'NEfi34,.', null, null, 'NEfi34,.', 'NEfi34,.', 'pavkai@yahoo.com', '');

-- ----------------------------
-- Table structure for `usuariosetiquetas`
-- ----------------------------
DROP TABLE IF EXISTS `usuariosetiquetas`;
CREATE TABLE `usuariosetiquetas` (
  `idUsuario` int(11) NOT NULL,
  `idEtiqueta` int(11) NOT NULL,
  PRIMARY KEY (`idUsuario`,`idEtiqueta`),
  KEY `idEtiqueta` (`idEtiqueta`),
  CONSTRAINT `usuariosetiquetas_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `usuariosetiquetas_ibfk_2` FOREIGN KEY (`idEtiqueta`) REFERENCES `etiquetas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usuariosetiquetas
-- ----------------------------
INSERT INTO `usuariosetiquetas` VALUES ('1', '1');
INSERT INTO `usuariosetiquetas` VALUES ('1', '2');
INSERT INTO `usuariosetiquetas` VALUES ('2', '2');
INSERT INTO `usuariosetiquetas` VALUES ('2', '3');
INSERT INTO `usuariosetiquetas` VALUES ('7', '3');
INSERT INTO `usuariosetiquetas` VALUES ('5', '4');
INSERT INTO `usuariosetiquetas` VALUES ('12', '4');
INSERT INTO `usuariosetiquetas` VALUES ('15', '4');
INSERT INTO `usuariosetiquetas` VALUES ('2', '5');
INSERT INTO `usuariosetiquetas` VALUES ('6', '5');
