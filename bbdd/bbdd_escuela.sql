-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bbdd_escuela
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.9-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
--
-- Base de datos: `bbdd_escuela`
--

CREATE DATABASE IF NOT EXISTS `bbdd_escuela`;
USE `bbdd_escuela`;

--
-- Table structure for table `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumnos` (
  `DNI` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `APELLIDO_NOMBRE` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `DIRECCION` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `POBLACION` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `TELEFONO` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnos`
--

LOCK TABLES `alumnos` WRITE;
/*!40000 ALTER TABLE `alumnos` DISABLE KEYS */;
INSERT INTO `alumnos` VALUES ('12345678','Eistein, Albert','C/Radiación, 24 - Chalet','Madrid','917777777'),('15975328','Dawkins, Richard','C/Genes, 25 - 4B','Móstoles','918888888'),('35795146','de Alejandria, Hipatia','C/Agora 89 - 3C','Madrid','915555555'),('98765432','Lovelace, Ada','C/Programación, 28 - 3A','Madrid','919999999');
/*!40000 ALTER TABLE `alumnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asignaturas`
--

DROP TABLE IF EXISTS `asignaturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asignaturas` (
  `CODIGO` int(11) NOT NULL,
  `NOMBRE` varchar(25) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignaturas`
--

LOCK TABLES `asignaturas` WRITE;
/*!40000 ALTER TABLE `asignaturas` DISABLE KEYS */;
INSERT INTO `asignaturas` VALUES (1,'Física'),(2,'Química'),(3,'Lengua'),(4,'Inglés');
/*!40000 ALTER TABLE `asignaturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notas`
--

DROP TABLE IF EXISTS `notas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notas` (
  `alumno` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `asignatura` int(11) NOT NULL,
  `nota` int(2) DEFAULT NULL,
  PRIMARY KEY (`alumno`,`asignatura`),
  KEY `notas_ibfk_2` (`asignatura`),
  CONSTRAINT `notas_ibfk_1` FOREIGN KEY (`alumno`) REFERENCES `alumnos` (`DNI`),
  CONSTRAINT `notas_ibfk_2` FOREIGN KEY (`asignatura`) REFERENCES `asignaturas` (`CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notas`
--

LOCK TABLES `notas` WRITE;
/*!40000 ALTER TABLE `notas` DISABLE KEYS */;
INSERT INTO `notas` VALUES ('12345678',2,3),('12345678',3,6),('12345678',4,8),('15975328',1,8),('15975328',2,7),('15975328',3,6),('15975328',4,10),('35795146',1,5),('35795146',2,7),('35795146',3,6),('35795146',4,10),('98765432',1,6),('98765432',2,7),('98765432',3,6),('98765432',4,10);
/*!40000 ALTER TABLE `notas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-24 14:17:18
