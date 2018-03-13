/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.19-log : Database - prebid
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prebid` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `prebid`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '',
  `image` varchar(200) DEFAULT '',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`id`,`name`,`image`,`created_at`,`updated_at`) values (1,'category1','1520881964.jpg','2018-03-13 22:03:37','2018-03-13 22:03:37'),(2,'category2','1520882114.jpg','2018-03-13 22:03:48','2018-03-13 22:03:48'),(3,'news','','2018-03-13 22:17:33','2018-03-13 22:17:33'),(4,'videos','','2018-03-13 22:17:39','2018-03-13 22:17:39'),(5,'audios','','2018-03-13 22:17:41','2018-03-13 22:17:41'),(6,'children','','2018-03-13 22:17:46','2018-03-13 22:17:46'),(7,'sports','','2018-03-13 22:17:55','2018-03-13 22:17:55');

/*Table structure for table `charity` */

DROP TABLE IF EXISTS `charity`;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `charity` */

insert  into `charity`(`id`,`name`,`logo`,`bio`,`website`,`category`,`metric`,`amount`,`quantity`,`created_at`,`updated_at`) values (1,'test','1520932395.jpg','test','test111',1,'test',0.00,0,'2018-03-13 17:13:25','2018-03-13 22:19:16'),(3,'adsfadsf','1520949161.jpg','asdfadsf','sdfgf',2,'asdf',0.00,0,'2018-03-13 21:52:43','2018-03-13 22:19:16'),(4,'ccvvssdf','1520949193.jpg','arasddfd','sdfsae',3,'dfgddf',20.50,10,'2018-03-13 21:53:15','2018-03-13 22:19:17'),(5,'kaka','1520953369.jpg','dfgdcvc','dkdoskdjfdj',7,'etreds',100.00,10,'2018-03-13 23:02:52','2018-03-13 23:02:52');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fullname` varchar(50) NOT NULL DEFAULT '',
  `email` varchar(30) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `type` int(11) NOT NULL DEFAULT '2',
  `act_code` varchar(20) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`fullname`,`email`,`password`,`type`,`act_code`,`created_at`,`updated_at`) values (1,'admin','admin@admin.com','1bbd886460827015e5d605ed44252251',1,NULL,'2018-03-13 23:03:57','2018-03-13 23:04:35'),(2,'test user','test@test.com','f5bb0c8de146c67b44babbf4e6584cc0',2,NULL,'2018-03-13 11:53:48','2018-03-13 23:04:02');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
