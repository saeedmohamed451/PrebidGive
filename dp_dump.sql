CREATE DATABASE  IF NOT EXISTS `prebid` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `prebid`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: prebid
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '',
  `image` varchar(200) DEFAULT '',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Urgent','1520881964.jpg','2018-03-13 22:03:37','2018-04-07 11:48:44'),(2,'Education','1520882114.jpg','2018-03-13 22:03:48','2018-04-07 11:48:44'),(3,'Animals','','2018-03-13 22:17:33','2018-04-07 11:48:44'),(4,'Enivironment','','2018-03-13 22:17:39','2018-04-07 11:48:44'),(5,'Health','','2018-03-13 22:17:41','2018-04-07 11:48:44'),(6,'Refuges','','2018-03-13 22:17:46','2018-04-07 11:48:44'),(7,'Hunger','','2018-03-13 22:17:55','2018-04-07 11:48:44'),(8,'Water','','2018-04-07 13:48:44','2018-04-07 11:48:44'),(9,'Children','','2018-04-07 13:48:44','2018-04-07 11:48:44'),(10,'Povetry','','2018-04-07 13:48:44','2018-04-07 11:48:44'),(11,'Injustice','','2018-04-07 13:48:44','2018-04-07 11:48:44');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `charity`
--

DROP TABLE IF EXISTS `charity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `charity` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT '',
  `logo` varchar(200) DEFAULT '',
  `bio` varchar(1024) DEFAULT '',
  `website` varchar(200) DEFAULT '',
  `category` int(11) DEFAULT '0',
  `metric` varchar(50) DEFAULT '',
  `amount` double(10,2) DEFAULT '0.00',
  `quantity` int(11) DEFAULT '0',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charity`
--

LOCK TABLES `charity` WRITE;
/*!40000 ALTER TABLE `charity` DISABLE KEYS */;
INSERT INTO `charity` VALUES (1,'Test urgent ','1520932395.jpg','This is test description from urgent','test111',1,'test',0.00,0,'2018-03-13 17:13:25','2018-04-07 11:54:19'),(3,'adsfadsf','1520949161.jpg','asdfadsf','sdfgf',1,'asdf',0.00,0,'2018-03-13 21:52:43','2018-04-07 12:46:34'),(4,'ccvvssdf','1520949193.jpg','arasddfd','sdfsae',1,'dfgddf',20.50,10,'2018-03-13 21:53:15','2018-04-07 12:46:34'),(5,'kaka','1520953369.jpg','dfgdcvc','dkdoskdjfdj',1,'etreds',100.00,10,'2018-03-13 23:02:52','2018-04-07 12:46:34'),(6,'asdas','','asdasdsa','adsdasad',1,'asd',11.11,2,'2018-04-07 14:46:33','2018-04-07 12:46:33'),(7,'asd','1523355925.jpg','asd','asd',3,'12312',0.00,0,'2018-04-10 12:25:26','2018-04-10 10:25:26'),(8,'asd','1523355955.jpg','asd','asd',3,'123',0.00,0,'2018-04-10 12:25:55','2018-04-10 10:25:55'),(9,'asd','1523357787.jpg','asd','asdas',3,'12',0.00,0,'2018-04-10 12:56:31','2018-04-10 10:56:31'),(10,'asdasd','1523357826.jpg','sadadasdasdasssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss\nsadasdasdasda','asdasd',3,'12312',0.00,0,'2018-04-10 12:57:08','2018-04-10 10:57:08');
/*!40000 ALTER TABLE `charity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fullname` varchar(50) NOT NULL DEFAULT '',
  `email` varchar(30) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `type` int(11) NOT NULL DEFAULT '2',
  `act_code` varchar(20) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `alltimedonation` int(11) DEFAULT '0',
  `education` int(11) DEFAULT '0',
  `animals` int(11) DEFAULT '0',
  `enivironment` int(11) DEFAULT '0',
  `health` int(11) DEFAULT '0',
  `refuges` int(11) DEFAULT '0',
  `hunger` int(11) DEFAULT '0',
  `water` int(11) DEFAULT '0',
  `children` int(11) DEFAULT '0',
  `povetry` int(11) DEFAULT '0',
  `injustice` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin@admin.com','1bbd886460827015e5d605ed44252251',1,NULL,'2018-03-13 23:03:57','2018-03-13 22:04:35',0,0,0,0,0,0,0,0,0,0,0),(2,'test user','test@test.com','f5bb0c8de146c67b44babbf4e6584cc0',2,NULL,'2018-03-13 11:53:48','2018-04-08 13:14:26',0,2,3,4,5,6,7,8,9,10,11),(3,'ilija','ilija@ilija.com','098f6bcd4621d373cade4e832627b4f6',2,NULL,'2018-03-30 18:39:23','2018-04-08 22:54:23',0,0,0,0,0,0,0,0,0,0,0),(4,'ilija user','ilija@ilija.com','098f6bcd4621d373cade4e832627b4f6',1,NULL,'2018-03-13 11:53:48','2018-04-08 22:54:23',0,0,0,0,0,0,0,0,0,0,0),(5,'Petar Prvi','petarpetrovi@gmail.com','256d07609b4895d4fabbf95e92ef371b',2,NULL,'2018-04-08 14:54:48','2018-04-08 13:14:56',0,0,30,500,0,70,0,0,0,0,0),(6,'ikrsta','ilijakrstic77@gmail.com','6aa51e869f1961100d757c9b560a2a58',2,NULL,'2018-04-09 01:03:09','2018-04-09 12:15:04',0,0,0,0,0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `waterchar`
--

DROP TABLE IF EXISTS `waterchar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `waterchar` (
  `idurgentchar` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`idurgentchar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waterchar`
--

LOCK TABLES `waterchar` WRITE;
/*!40000 ALTER TABLE `waterchar` DISABLE KEYS */;
/*!40000 ALTER TABLE `waterchar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'prebid'
--

--
-- Dumping routines for database 'prebid'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-18 17:35:41
