-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 192.168.1.159    Database: electronic
-- ------------------------------------------------------
-- Server version	5.6.31-log

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
-- Table structure for table `notebook`
--

DROP TABLE IF EXISTS `notebook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notebook` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `producer` varchar(45) NOT NULL,
  `model` varchar(45) NOT NULL,
  `price` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `display_diagonal` varchar(10) NOT NULL,
  `article` int(40) DEFAULT NULL,
  `processor` varchar(20) DEFAULT NULL,
  `ram` int(20) DEFAULT NULL,
  `hdd` int(20) DEFAULT NULL,
  `image_ref` varchar(100) DEFAULT NULL,
  `category` varchar(20) NOT NULL,
  `storage_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `notebook_article_uindex` (`article`),
  KEY `article_idx` (`storage_id`),
  CONSTRAINT `article` FOREIGN KEY (`storage_id`) REFERENCES `storage` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notebook`
--

LOCK TABLES `notebook` WRITE;
/*!40000 ALTER TABLE `notebook` DISABLE KEYS */;
INSERT INTO `notebook` VALUES (1,'Lenovo','IdeaPad 100-14',5688,'Экран 14\" (1366x768) HD LED, глянцевый / Intel Celeron N2840 (2.16 - 2.58 ГГц) / RAM 2 ГБ / HDD 500 ГБ / Intel HD Graphics / без ОД / LAN / Wi-Fi / веб-камера / DOS / 1.9 кг / черный','14',131,'Intel Celeron',2,500,'Lenovo_IdeaPad 100-14_1.jpg','notebook',12),(2,'HP','15-ac162ur',7999,'Экран 15.6” (1366x768) HD LED, глянцевый / Intel Pentium N3700 (1.6 - 2.4 ГГц) / RAM 4 ГБ / HDD 1 TБ / AMD Radeon R5 M330, 1 ГБ / без ОД / LAN / Wi-Fi / Bluetooth / веб-камера / DOS / 2.19 кг / черный','15.6',132,'Intel Pentium',4,1000,'HP_15-ac162ur_1.jpg','notebook',13),(3,'Dell','Inspiron 3552',7299,'Экран 15.6\" (1366x768) HD, глянцевый / Intel Pentium N3700 (1.6 - 2.4 ГГц) / RAM 4 ГБ / HDD 500 ГБ / Intel HD Graphics / без ОД / Wi-Fi / Bluetooth / веб-камера / Linux / 2.14 кг / черный','15.6',133,'Intel Pentium',4,500,'Dell_Inspiron 3552_1.jpg','notebook',14),(4,'Asus','X540SA',6749,'Экран 15.6\" (1366x768) HD LED, глянцевый / Intel Celeron N3050 (1.6 - 2.16 ГГц) / RAM 2 ГБ / HDD 500 ГБ / Intel HD Graphics / без ОД / LAN / Wi-Fi / Bluetooth / веб-камера / DOS / 1.9 кг / темно-коричневый','15.6',134,'Intel Celeron',2,500,'Asus_X540SA_1.jpg','notebook',15),(5,'Acer','EX2519-P9ZV',6899,'Экран 15.6\'\' (1366x768) HD LED, матовый / Intel Pentium N3700 (1.6 - 2.4 ГГц) / RAM 4 ГБ / HDD 500 ГБ / Intel HD Graphics / Без ОД / LAN / Wi-Fi / Bluetooth / веб-камера / Linux / 2.4 кг / черный','15.6',135,'Intel Pentium',4,500,'Acer_EX2519-P9ZV_1.jpg','notebook',16),(6,'Asus','X555SJ',8690,'Экран 15.6\" (1366x768) HD LED, матовый / Intel Pentium N3700 (1.6 - 2.4 ГГц) / RAM 4 ГБ / HDD 1 ТБ / nVidia GeForce GT 920M, 1 ГБ / DVD Super Multi / LAN / Wi-Fi / Bluetooth / веб-камера / DOS / 2.3 кг / черный','15.6',136,'Intel Pentium',4,1000,'Asus_X555SJ_1.jpg','notebook',17),(7,'HP','250 G4',8499,'Экран 15.6” (1366x768) HD LED, матовый / Intel Core i3-4005U (1.7 ГГц) / RAM 4 ГБ / HDD 500 ГБ / Intel HD Graphics / без ОД / LAN / Wi-Fi / Bluetooth / веб-камера / DOS / 2.15 кг / черный','15.6',137,'Intel Core i3',4,500,'HP_250 G4_1.jpg','notebook',18),(8,'Lenovo','G70-80',10999,'Экран 17.3\" (1600x900) HD+, глянцевый / Intel Core i3-5005U (2.0 ГГц) / RAM 4 ГБ / HDD 500 ГБ / nVidia GeForce 920M, 2 ГБ / без ОД / LAN / Wi-Fi / Bluetooth / веб-камера / DOS / 3 кг / черный','17.3',138,'Intel Core i3',4,500,'Lenovo_G70-80_1.jpg','notebook',19),(9,'Acer','ES1-731-P0D3',8599,'Экран 17.3\'\' (1600x900) HD+ LED, глянцевый / Intel Pentium N3700 (1.6 - 2.4 ГГц) / RAM 4 ГБ / HDD 500 ГБ / Intel HD Graphics / Без ОД / LAN / Wi-Fi / Bluetooth / веб-камера / Linux / 3 кг / черный','17.3',139,'Intel Pentium',4,500,'Acer_ES1-731-P0D3_1.jpg','notebook',20),(10,'Lenovo','IdeaPad 300-17 ',13775,'Экран 17.3\" (1600x900) HD+ LED, глянцевый / Intel Core i5-6200U (2.3 - 2.8 ГГц) / RAM 4 ГБ / HDD 1 ТБ / AMD Radeon R5 M330, 2 ГБ / DVD Super Multi / LAN / Wi-Fi / Bluetooth 4.0 / веб-камера / DOS / 3 кг / черный','17.3',1310,'Intel Core i5',4,1000,'Lenovo_IdeaPad 100-14_1.jpg','notebook',21),(33,'Apple','MacBook Air',26499,'Экран 13.3\" (1440x900) WXGA+ LED, глянцевый / Intel Core i5 (1.6 - 2.7 ГГц) / RAM 8 ГБ / SSD 128 ГБ / Intel HD Graphics 6000 / без ОД / Wi-Fi / Bluetooth / веб-камера / OS X El Capitan / 1.35 кг','13.3',1333,'i5',8,128,'Apple_MacBook_Air_1.jpg','notebook',22);
/*!40000 ALTER TABLE `notebook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `comment` varchar(500) DEFAULT NULL,
  `is_done` tinyint(1) DEFAULT '0',
  `buyer_name` varchar(50) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `buyer_cell_number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_user_id_fk` (`user_id`),
  CONSTRAINT `order_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (10,2,'fsdfsdf',0,'Николай Савельичев','Киев, ул. Академика доброхотова 9 кв 53 4 подъезд','+380639765432'),(11,2,'ggg',0,'Николай Савельичев','Киев, ул. Академика доброхотова 9 кв 53 4 подъезд','+380639765432'),(12,2,'bbkj',0,'Николай Савельичев','Киев, ул. Академика доброхотова 9 кв 53 4 подъезд','+380639765432'),(13,2,';j',0,'Николай Савельичев','Киев, ул. Академика доброхотова 9 кв 53 4 подъезд','+380639765432'),(14,2,'sada',0,'Николай Савельичев','Киев, ул. Академика доброхотова 9 кв 53 4 подъезд','+380639765432'),(15,2,'',0,'Николай Савельичев','Киев, ул. Академика доброхотова 9 кв 53 4 подъезд','+380639765432'),(16,2,'сегодня',0,'Николай Савельичев','Киев, ул. Академика доброхотова 9 кв 53 4 подъезд','+380639765432'),(18,9,'csd',0,'Степан Степанов','Киев, ул. Северная 36 кв 54','+380671234567'),(19,9,'срочно',0,'Степан Степанов','Киев, ул. Северная 36 кв 54','+380671234567'),(20,9,'',0,'Степан Степанов','Киев, ул. Северная 36 кв 54','+380671234567'),(22,9,'',0,'Степан Степанов','Киев, ул. Северная 36 кв 54','+380671234567'),(23,9,'',0,'Степан Степанов','Киев, ул. Северная 36 кв 54','+380671234567'),(24,9,'',0,'Степан Степанов','Киев, ул. Северная 36 кв 54','+380671234567'),(25,9,'',0,'Степан Степанов','Киев, ул. Северная 36 кв 54','+380671234567'),(26,9,'',0,'Степан Степанов','Киев, ул. Северная 36 кв 54','+380671234567');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `product_article` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_item_order_id_fk` (`order_id`),
  CONSTRAINT `order_item_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (31,10,133,7299,1,'Dell Inspiron 3552'),(32,10,134,6749,1,'Asus X540SA'),(33,11,132,7999,1,'HP 15-ac162ur'),(34,12,134,6749,1,'Asus X540SA'),(35,13,134,6749,1,'Asus X540SA'),(36,13,136,8690,3,'Asus X555SJ'),(37,14,132,7999,1,'HP 15-ac162ur'),(38,15,134,6749,1,'Asus X540SA'),(39,16,131,5688,2,'Lenovo IdeaPad 100-14'),(41,18,132,7999,1,'HP 15-ac162ur'),(42,19,131,5688,1,'Lenovo IdeaPad 100-14'),(43,20,132,7999,1,'HP 15-ac162ur'),(44,20,134,6749,1,'Asus X540SA'),(45,20,136,8690,1,'Asus X555SJ'),(46,20,135,6899,1,'Acer EX2519-P9ZV'),(47,22,131,5688,1,'Lenovo IdeaPad 100-14'),(48,23,131,5688,1,'Lenovo IdeaPad 100-14'),(49,24,131,5688,1,'Lenovo IdeaPad 100-14'),(50,25,131,5688,1,'Lenovo IdeaPad 100-14'),(51,26,133,7299,2,'Dell Inspiron 3552');
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone`
--

DROP TABLE IF EXISTS `phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `producer` varchar(45) NOT NULL,
  `model` varchar(45) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `category` varchar(20) DEFAULT 'phone',
  `display_diagonal` varchar(10) DEFAULT NULL,
  `image_ref` varchar(100) DEFAULT NULL,
  `os` varchar(20) DEFAULT NULL,
  `main_camera` varchar(20) DEFAULT NULL,
  `battery_capacity` varchar(20) DEFAULT NULL,
  `article` int(11) DEFAULT NULL,
  `storage_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `phone_storage_id_fk` (`storage_id`),
  CONSTRAINT `phone_storage_id_fk` FOREIGN KEY (`storage_id`) REFERENCES `storage` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
INSERT INTO `phone` VALUES (4,'Motorola','MOTO X Force',18999,'Экран (5.4\", IPS, 2560x1440)/ Qualcomm Snapdragon 810 (2.0 ГГц)/ основная камера: 21 Мп, фронтальная камера: 5 Мп/ RAM 3 ГБ/ 32 ГБ встроенной памяти + microSD/SDHC (до 2 ТБ)/ 3G/ LTE/ GPS/ Nano-SIM/ Android 6.0 (Marshmallow) / 3760 мА*ч ','phone','5.4','Motorola_MOTO_X_Force_1.jpg','Android','21','3760',144,7),(6,'Sony','Xperia Z5 Dual Premium ',17999,'Экран (5.5\", IPS, 3840x2160)/ Qualcomm Snapdragon 810 MSM8994 (Quad 2 ГГц + Quad 1.5 ГГц)/ основная камера: 23 Мп, фронтальная камера: 5 Мп/ RAM 3 ГБ/ 32 ГБ встроенной памяти + microSD/SDHC (до 200 ГБ)/ 3G/ LTE/ GPS/ поддержка 2х SIM-карт (Nano-SIM)/ Android 5.1 (Lollipop) / 3430 мА*ч ','phone','5.5','Sony_Xperia_Z5_Dual_Premium_1.jpg','Android','23','3430',146,8),(9,'Samsung','Galaxy S7 Edge',23999,'Экран (5.5\", Super AMOLED, 2560х1440)/ Exynos 8890 Octa (Quad 2.3 ГГц + Quad 1.6 ГГц)/ камера 12 Мп + фронтальная 5 Мп/ RAM 4 ГБ/ 32 ГБ встроенной памяти + microSD (до 200 ГБ)/ 3G/ LTE/ GPS/ поддержка 2х SIM-карт (Nano-SIM)/ Android 6.0 (Marshmallow) / 3600 мА*ч','notebook','5.5','Samsung_Galaxy_S7_Edge_1.jpg','Android','12','3600',149,9),(10,'LG','K7 X210',3333,'Экран (5\", TFT, 854x480)/ MediaTek МТ6582 (1.3 ГГц)/ основная камера: 8 Мп, фронтальная камера: 5 Мп/ RAM 1 ГБ/ 8 ГБ встроенной памяти + microSD/SDHC (до 32 ГБ)/ 3G/ GPS/ поддержка 2х SIM-карт (Nano-SIM)/ Android 5.1 (Lollipop) / 2125 мА*ч','notebook','5','LG_K7_X210_1.jpg','Android','8','2125',1410,10),(11,'Lenovo','Vibe K5 Note',5999,'Экран (5.5\", IPS, 1920x1080)/ MediaTek Helio P10 (1.8 ГГц)/ основная камера: 13 Мп, фронтальная камера: 5 Мп/ RAM 3 ГБ/ 16 ГБ встроенной памяти + microSD/SDHC (до 128 ГБ)/ 3G/ LTE/ GPS/ поддержка 2х SIM-карт (Nano-SIM)/ Android 5.1 (Lollipop) / 3500 мА*ч','notebook','5.5','Lenovo_Vibe_K5_Note_1.jpg','Android','13','3500',1411,11),(13,'Lenovo','A7000',3199,'Экран (5.5\", IPS, 1280x720)/ MediaTek MT6752 (1.5 ГГц)/ основная камера: 8 Мп, фронтальная камера: 5 Мп/ RAM 2 ГБ/ 8 ГБ встроенной памяти + microSD/SDHC (до 32 ГБ)/ 3G/ GPS/ поддержка 2х SIM-карт (Micro-SIM)/ Android 5.0 (Lollipop) / 2900 мА*ч','phone','5.5','Lenovo_A7000_1.jpg','Android','8','2900',1413,3),(15,'Apple','iPhone SE',13999,'Экран (4\", IPS, 1136x640)/ Apple A9/ основная камера: 12 Мп, фронтальная: 1.2 Мп/ RAM 2 ГБ/ 16 ГБ встроенной памяти/ 3G/ LTE/ GPS/ Nano-SIM/ iOS 9','phone','4','Apple_iPhone_SE_1.jpg','iOS','12','1500',1415,4),(16,'Apple','iPhone 5s',8499,'Экран (4\", IPS, 1136x640)/ Apple A7 (1.3 ГГц)/ основная камера: 8 Мп, фронтальная камера: 1.2 Мп/ RAM 1 ГБ/ 16 ГБ встроенной памяти/ 3G/ LTE/ GPS/ Nano-SIM/ iOS 8/ 1560 мА*ч','phone','4','Apple_iPhone_5s_1.jpg','iOS','8','1450',1416,5),(17,'Samsung','Galaxy S5 G900H',7777,'Экран (5.1\", Super AMOLED, 1920x1080) / Samsung Exynos 5422 (Quad 1.9 ГГц + Quad 1.3 ГГц) / основная камера: 16 Мп, фронтальная камера: 2 Мп / RAM 2 ГБ / 16 ГБ встроенной памяти + microSD/SDHC (до 128 ГБ) / 3G / GPS / Micro-SIM / Android 4.4.2 (KitKat) / 2800 мА*ч','phone','5.1','Samsung_Galaxy_S5_G900H_1.jpg','Android','16','2800',1417,6);
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storage`
--

DROP TABLE IF EXISTS `storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) DEFAULT '0',
  `article` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storage`
--

LOCK TABLES `storage` WRITE;
/*!40000 ALTER TABLE `storage` DISABLE KEYS */;
INSERT INTO `storage` VALUES (3,0,'1413'),(4,0,'1415'),(5,0,'1416'),(6,0,'1417'),(7,0,'144'),(8,0,'146'),(9,0,'149'),(10,0,'1410'),(11,0,'1411'),(12,0,'131'),(13,0,'132'),(14,0,'133'),(15,0,'134'),(16,0,'135'),(17,0,'136'),(18,0,'137'),(19,0,'138'),(20,0,'139'),(21,0,'1310'),(22,0,'1333');
/*!40000 ALTER TABLE `storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `cell_number` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `role` varchar(10) NOT NULL DEFAULT 'CLIENT',
  `blocked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `iduser_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `cell_number_UNIQUE` (`cell_number`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'Николай','Савельичев','+380639765432','savelichev.n@gmail.com','savelichev.n','savelichev','Киев, ул. Академика доброхотова 9 кв 53 4 подъезд','ADMIN',0),(3,'Сергей','Брин','+380631234567','brin@gmail.com','brin','google','Киев, ул. Северная 36 кв 53','CLIENT',0),(9,'Степан','Степанов','+380671234567','stepan@gmail.com','stepa','12345','Киев, ул. Северная 36 кв 54','CLIENT',0),(11,'sadad','dasdad','423421','sh@gmail.com','sh','1111','','CLIENT',0),(12,'asad','dads','3242342','abc@mail','abc','1111','','CLIENT',0),(13,'adsa','asda','asd','dcsdcc@sd','sdc','cdscs','asdasd','CLIENT',0);
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

-- Dump completed on 2016-08-04 15:17:21
