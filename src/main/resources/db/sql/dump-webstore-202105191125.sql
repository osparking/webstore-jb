-- MariaDB dump 10.19  Distrib 10.5.9-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: webstore
-- ------------------------------------------------------
-- Server version	10.5.9-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ZIPCODE` varchar(25) DEFAULT NULL,
  `WIDECIDO` varchar(25) DEFAULT NULL,
  `CIGOONGU` varchar(25) DEFAULT NULL,
  `STREETNAME` varchar(25) DEFAULT NULL,
  `BUILDINGNO` varchar(25) DEFAULT NULL,
  `UNITNO` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'01020','서울시','종로구','종로2가100길','15','501호'),(2,'02040','부산시','영도구','영도대로7로56길','64','101동309호'),(24,'02040','부산시','영도구','영도대로7로56길','64','홍실아파트 101동309호'),(25,'02040','부산시','영도구','영도대로7로56길','64','홍실아파트 101동309호'),(26,'07070','서울시','서초구','강남대로 25번길','37동','24호'),(27,'01020','서울시','종로구','종로2가100길','15','501호'),(28,'01020','서울시','종로구','종로2가100길','15','501호'),(29,'88888','팔팔시도','팔팔군','팔팔로','88','888'),(30,'01020','서울시','종로구','종로2가100길','15','501호'),(31,'01020','서울시','종로구','종로2가100길','15','501호'),(32,'01020','서울시','종로구','종로2가100길','15','501호'),(33,'','','','','','');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `ID` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES ('B164DDE4998746A695B6693381847E12'),('E19B77F9CAFBB03414FD69FC3176AC49');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart_item` (
  `ID` varchar(75) NOT NULL,
  `PRODUCT_ID` varchar(25) DEFAULT NULL,
  `CART_ID` varchar(50) NOT NULL,
  `QUANTITY` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`,`CART_ID`),
  KEY `product_FK` (`PRODUCT_ID`),
  KEY `cart_FK` (`CART_ID`),
  CONSTRAINT `cart_FK` FOREIGN KEY (`CART_ID`) REFERENCES `cart` (`ID`),
  CONSTRAINT `product_FK` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `products` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES ('E19B77F9CAFBB03414FD69FC3176AC49P1234','P1234','E19B77F9CAFBB03414FD69FC3176AC49',1);
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(25) DEFAULT NULL,
  `PHONE_NUMBER` varchar(25) DEFAULT NULL,
  `BILLING_ADDRESS_ID` int(11) DEFAULT NULL,
  `noOfOrdersMade` int(11) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `address_FK` (`BILLING_ADDRESS_ID`),
  CONSTRAINT `address_FK` FOREIGN KEY (`BILLING_ADDRESS_ID`) REFERENCES `address` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (10,'이상호','010-2324-2345',1,1,'강원도 태백시 문동면 100번지'),(11,'박찬호','010-5555-9898',2,0,'세종시 세종대로 15로 103동 1105호'),(12,'이상호','010-2324-2345',31,NULL,NULL),(13,'이상호','010-2324-2345',32,NULL,NULL);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CART_ID` varchar(50) DEFAULT NULL,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  `SHIPPING_DETAIL_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `order_cart_FK` (`CART_ID`),
  KEY `customer_FK` (`CUSTOMER_ID`),
  KEY `ship_detail_FK` (`SHIPPING_DETAIL_ID`),
  CONSTRAINT `customer_FK` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customers` (`ID`),
  CONSTRAINT `order_cart_FK` FOREIGN KEY (`CART_ID`) REFERENCES `cart` (`ID`),
  CONSTRAINT `ship_detail_FK` FOREIGN KEY (`SHIPPING_DETAIL_ID`) REFERENCES `shipping_detail` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'B164DDE4998746A695B6693381847E12',13,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `ID` varchar(25) NOT NULL,
  `prod_NAME` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `UNIT_PRICE` decimal(10,0) DEFAULT NULL,
  `MANUFACTURER` varchar(50) DEFAULT NULL,
  `CATEGORY` varchar(50) DEFAULT NULL,
  `PROD_CONDITION` varchar(50) DEFAULT NULL,
  `UNITS_IN_STOCK` bigint(20) DEFAULT NULL,
  `UNITS_IN_ORDER` bigint(20) DEFAULT NULL,
  `DISCONTINUED` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('P1234','iPhone 6s','Apple iPhone 6s smartphone with 4.00-inch\r\n640x1136 display and 8-megapixel rear camera',500,'Apple','Smartphone','New',450,0,0),('P1235','Dell\r\nInspiron','Dell Inspiron 14-inch Laptop (Black) with 3rd\r\nGeneration Intel Core processors',700,'Dell','Laptop','New',999,0,0),('P1236','Nexus 7','Google Nexus 7 is the lightest 7 inch tablet With a\r\nquad-core Qualcomm Snapdragon?S4 Pro\r\nprocessor',300,'Google','Tablet','New',1000,0,0);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping_detail`
--

DROP TABLE IF EXISTS `shipping_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipping_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(25) DEFAULT NULL,
  `SHIPPING_DATE` varchar(25) DEFAULT NULL,
  `SHIPPING_ADDRESS_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `shcustomersip_addr_ID_FK` (`SHIPPING_ADDRESS_ID`),
  CONSTRAINT `shcustomersip_addr_ID_FK` FOREIGN KEY (`SHIPPING_ADDRESS_ID`) REFERENCES `address` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_detail`
--

LOCK TABLES `shipping_detail` WRITE;
/*!40000 ALTER TABLE `shipping_detail` DISABLE KEYS */;
INSERT INTO `shipping_detail` VALUES (1,'',NULL,33);
/*!40000 ALTER TABLE `shipping_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(16) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','1234',NULL,'2021-05-18 23:36:03'),('custrep','1234',NULL,'2021-05-18 23:36:03'),('park','1234',NULL,'2021-05-18 23:39:49');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'webstore'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-19 11:25:07
