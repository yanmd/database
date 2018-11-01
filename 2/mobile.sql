-- MySQL dump 10.13  Distrib 8.0.12, for osx10.13 (x86_64)
--
-- Host: localhost    Database: mobile
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
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `orderTime` datetime DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,1,'2018-10-30 21:13:18','2018-10-30 21:13:18','2018-11-01 00:00:00'),(2,1,3,'2018-10-30 21:14:18','2018-11-01 00:00:00',NULL),(3,1,5,'2018-10-30 21:15:18','2018-10-30 21:15:18','2018-10-30 23:04:08'),(4,2,1,'2018-10-30 21:13:18','2018-10-30 21:13:18',NULL),(5,2,2,'2018-10-30 21:14:18','2018-10-30 21:14:18','2018-11-01 00:00:00'),(6,1,3,'2018-10-30 22:56:56','2018-10-30 22:56:56',NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plans`
--

DROP TABLE IF EXISTS `plans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `plans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `cost` decimal(11,2) DEFAULT NULL,
  `calls` int(11) DEFAULT NULL,
  `messages` int(11) DEFAULT NULL,
  `localData` int(11) DEFAULT NULL,
  `nationalData` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plans`
--

LOCK TABLES `plans` WRITE;
/*!40000 ALTER TABLE `plans` DISABLE KEYS */;
INSERT INTO `plans` VALUES (1,'话费套餐','月功能费20元，最多可拨打100分钟电话，超出时间按照0.5元/分钟计费。',20.00,100,0,0,0),(2,'短信套餐','月功能费10元，最多可发送200条短信，超出条数按0.1元/条计费。',10.00,0,200,0,0),(3,'本地流量套餐','月功能费20元，最多可获得2G流量，仅在本地使用，超出流量按2元/M计费。',20.00,0,0,2048,0),(4,'国内流量套餐','月功能费30元，最多可获得2G流量，超出流量按5元/M计费。',30.00,0,0,0,2048),(5,'大王卡套餐','月功能费68元，最多可拨打100分钟电话，超出时间按照0.5元/分钟计费； 最多可发送200条短信，超出条数按0.1元/条计费； 最多可获得2G流量，仅在本地使用，超出流量按2元/M计费； 最多可获得2G流量，超出流量按5元/M计费。',68.00,100,200,2048,2048);
/*!40000 ALTER TABLE `plans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usage`
--

DROP TABLE IF EXISTS `usage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usage` (
  `uid` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `useTime` datetime DEFAULT NULL,
  `type` enum('CALL','MESSAGE','LOCAL_DATA','NATIONAL_DATA') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usage`
--

LOCK TABLES `usage` WRITE;
/*!40000 ALTER TABLE `usage` DISABLE KEYS */;
INSERT INTO `usage` VALUES (1,5120,'2018-10-30 21:16:18','LOCAL_DATA'),(1,13,'2018-10-30 21:23:18','CALL'),(2,3,'2018-10-30 21:15:18','CALL'),(3,7,'2018-10-30 21:13:18','CALL'),(3,64,'2018-10-30 21:21:18','LOCAL_DATA'),(3,124,'2018-10-30 21:23:18','NATIONAL_DATA');
/*!40000 ALTER TABLE `usage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_telephone_uindex` (`telephone`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'赖立果','17118860822'),(2,'邹立辉','14717299635'),(3,'覃泽洋','15881283916'),(4,'江志泽','17711217189'),(5,'陶擎苍','17806699278'),(6,'陈峻熙','15589145977'),(7,'万果','18596506619'),(8,'苏振家','17670749161'),(9,'梁思淼','17841570468'),(10,'曹锦程','17668483881');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-30 23:40:13
