CREATE DATABASE  IF NOT EXISTS `calendar` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `calendar`;
-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: localhost    Database: calendar
-- ------------------------------------------------------
-- Server version	5.7.15

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
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(400) NOT NULL,
  `date` date NOT NULL,
  `location_id` int(11) NOT NULL,
  `name_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_location_idx` (`location_id`),
  KEY `fk_name_idx` (`name_id`),
  CONSTRAINT `fk_location` FOREIGN KEY (`location_id`) REFERENCES `eventLocation` (`location_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_name` FOREIGN KEY (`name_id`) REFERENCES `eventName` (`name_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'Described by Brecht as \"a gangster play that would recall certain events familiar to us all,\" Arturo UI is a witty and savage satire of the rise of Hitler - recast by Brecht into a fictional, small-time Chicago gangster\'s takeover of the city\'s greengrocery trade in the 1930s.','2016-10-14',1,1),(2,'Step back in time to an era of flappers and suffragists, bootleggers and temperance workers, and legends like Al Capone and Carry Nation. Created by the National Constitution Center, this exhibit is the first comprehensive exhibition about America\'s most colorful and complex constitution hiccup.','2016-11-30',2,2),(3,'View new work from four artists in this month-long exhibit: Iowa Painter Duane Adams, glassblower Frank Daharsh, painter Hope Dendinger and artist Dax Sterner.','2015-09-01',3,3),(4,'Just filling out the table','2016-10-10',3,2);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventLocation`
--

DROP TABLE IF EXISTS `eventLocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventLocation` (
  `location_id` int(11) NOT NULL AUTO_INCREMENT,
  `venue` varchar(100) NOT NULL,
  `street` varchar(100) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventLocation`
--

LOCK TABLES `eventLocation` WRITE;
/*!40000 ALTER TABLE `eventLocation` DISABLE KEYS */;
INSERT INTO `eventLocation` VALUES (1,'Blue Barn','1106 S. 10th St. ','Omaha','NE'),(2,'Durham Museum','801 S 10th St.','Omaha','NE'),(3,'Artists\' Cooperative Gallery','405 S 11th St','Omaha','NE');
/*!40000 ALTER TABLE `eventLocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventName`
--

DROP TABLE IF EXISTS `eventName`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventName` (
  `name_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`name_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventName`
--

LOCK TABLES `eventName` WRITE;
/*!40000 ALTER TABLE `eventName` DISABLE KEYS */;
INSERT INTO `eventName` VALUES (1,'\'The Resistible Rise of Arturo UI\' by Bertolt Brecht'),(2,'American Spirits: The Rise and Fall of Prohibition'),(3,'Artober Exhibit');
/*!40000 ALTER TABLE `eventName` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-15 17:56:19
