-- MySQL dump 10.13  Distrib 8.0.12, for osx10.13 (x86_64)
--
-- Host: localhost    Database: first
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Agent`
--

DROP TABLE IF EXISTS `Agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Agent` (
  `aid` varchar(60) DEFAULT NULL,
  `aname` varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Agent`
--

LOCK TABLES `Agent` WRITE;
/*!40000 ALTER TABLE `Agent` DISABLE KEYS */;
INSERT INTO `Agent` VALUES ('1','AAA有限公司'),('2','BBB有限公司'),('3','CCC有限公司');
/*!40000 ALTER TABLE `Agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Deal`
--

DROP TABLE IF EXISTS `Deal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Deal` (
  `sid` varchar(60) DEFAULT NULL,
  `pid` varchar(60) DEFAULT NULL,
  `volume` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Deal`
--

LOCK TABLES `Deal` WRITE;
/*!40000 ALTER TABLE `Deal` DISABLE KEYS */;
INSERT INTO `Deal` VALUES ('1','1','21'),('1','2','56'),('1','3','33'),('2','2','120'),('2','1','32'),('2','3','26'),('3','1','73'),('3','3','45'),('4','2','111'),('4','3','84'),('5','3','52'),('5','1','38'),('5','2','91'),('6','2','36'),('6','3','48'),('6','1','15');
/*!40000 ALTER TABLE `Deal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Production`
--

DROP TABLE IF EXISTS `Production`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Production` (
  `pid` varchar(60) DEFAULT NULL,
  `pname` varchar(60) DEFAULT NULL,
  `pdate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Production`
--

LOCK TABLES `Production` WRITE;
/*!40000 ALTER TABLE `Production` DISABLE KEYS */;
INSERT INTO `Production` VALUES ('1','产品A','2016-06-25'),('2','产品B','2017-05-25'),('3','产品C','2018-10-08');
/*!40000 ALTER TABLE `Production` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sales`
--

DROP TABLE IF EXISTS `Sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Sales` (
  `sid` varchar(60) DEFAULT NULL,
  `sname` varchar(60) DEFAULT NULL,
  `aid` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sales`
--

LOCK TABLES `Sales` WRITE;
/*!40000 ALTER TABLE `Sales` DISABLE KEYS */;
INSERT INTO `Sales` VALUES ('1','赵','2'),('2','钱','2'),('3','孙','3'),('4','李','1'),('5','石','3'),('6','刘','1');
/*!40000 ALTER TABLE `Sales` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-20 16:40:24
