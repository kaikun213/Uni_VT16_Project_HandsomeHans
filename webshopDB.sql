-- MySQL dump 10.13  Distrib 5.7.9, for linux-glibc2.5 (x86_64)
--
-- Host: localhost    Database: webshopDB
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_bin NOT NULL,
  `description` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'pistols','Pistols from everywhere in the galaxy!'),(2,'robots','Robots can help you in everything'),(3,'stuff','all kind of stuff');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderStatus` int(3) NOT NULL COMMENT '1 - new\n2 - shipped\n3 - delivered',
  `products` varchar(100) COLLATE utf8_bin NOT NULL COMMENT 'product id''s listed seperated with a semicolon',
  `price` int(11) NOT NULL,
  `date` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (23,3,'1:223;',111500,'2016-05-03'),(28,1,'1:223;2:181;',292500,'2016-05-04'),(29,2,'1:223;2:181;',292500,'2016-05-04'),(30,1,'1:1;',500,'2016-05-11'),(31,1,'1:1;',500,'2016-05-12'),(33,1,'1:1;',500,'2016-05-12'),(35,1,'2:1;1:1;',1500,'2016-05-12'),(37,1,'1:219;',109500,'2016-05-10'),(38,1,'1:219;2:177;',286500,'2016-05-10'),(39,3,'1:219;2:177;',286500,'2016-05-10'),(40,2,'1:219;2:177;',286500,'2016-05-10');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_bin NOT NULL,
  `category` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `image` varchar(400) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(800) COLLATE utf8_bin NOT NULL,
  `shortDescription` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `quantity` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Blaster Pistol',1,500,'http://cgcookie.com/app/uploads/2015/01/dl18_render_test_3edit1-1024x576.png','These compact blaster pistols were favored by elements of the underworld, particularly those based on Tatooine. In the early days of the Galactic Civil War, former Jedi Kanan Jarrus carried one, using it far more often than his secret lightsaber. Later in the war, DL-18s were in wide distribution inside Jabba’s Palace in the last days of his life. Luke Skywalker used the Force to snatch one from one of Jabba’s men, just before he fell into the rancor pit below Jabba’s throne.','What a pistol!',219),(2,'R5-series',2,1000,'http://new-republic.net/wiki/images/6/6b/R5_Series.png','The R5-series astromech droid was a line of low cost astromech droids built by Industrial Automaton. Based upon the success of prior astromech models, such as the wildly popular R2-series, Industrial Automaton intended the R5-series to cater to budget buyers at the cost of some functionality.','    \"A meter-tall stack of the worst business decisions you could possibly want.\" \n    ―Mechtech Illustrated, criticizing the R5-series',177),(3,'Holster Model A',3,300,'http://previewcf.turbosquid.com/Preview/2014/05/19__19_27_56/Tactical_SERPA_Pistol_Holster_V2_000.jpgf1cc72a2-1e6f-4867-bf95-d3ff9f7c5961Res200.jpg','A quick-draw holster was a special type of holster designed to allow an individual to very rapidly draw their blaster pistol. This was partly achieved by removing the strap used to secure the blaster in place which was found on common holsters. The notorious bounty hunter Cad Bane had dual quick-draw holsters for his twin modified LL-30 blaster pistols. ','Can hold everything you need!',444);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_bin NOT NULL,
  `orders` text COLLATE utf8_bin NOT NULL,
  `email` varchar(200) COLLATE utf8_bin NOT NULL,
  `password` varchar(100) COLLATE utf8_bin NOT NULL,
  `admin` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'paulius','','test','team2',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-12 17:30:47
