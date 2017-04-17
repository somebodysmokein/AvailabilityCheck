-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: allocation_db
-- ------------------------------------------------------
-- Server version	5.6.17

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
-- Table structure for table `allocation`
--

DROP TABLE IF EXISTS `allocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `allocation` (
  `alloc_id` int(11) NOT NULL AUTO_INCREMENT,
  `Emp_key` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  PRIMARY KEY (`alloc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allocation`
--

LOCK TABLES `allocation` WRITE;
/*!40000 ALTER TABLE `allocation` DISABLE KEYS */;
INSERT INTO `allocation` VALUES (12,13,11111),(13,14,22222),(14,15,22222),(15,16,33333);
/*!40000 ALTER TABLE `allocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `avail_view`
--

DROP TABLE IF EXISTS `avail_view`;
/*!50001 DROP VIEW IF EXISTS `avail_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `avail_view` (
  `firstname` tinyint NOT NULL,
  `lastname` tinyint NOT NULL,
  `Emp_key` tinyint NOT NULL,
  `id` tinyint NOT NULL,
  `project_name` tinyint NOT NULL,
  `project_end_dt` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` int(11) NOT NULL,
  `project_name` varchar(40) NOT NULL,
  `proj_start_dt` date NOT NULL,
  `project_end_dt` date NOT NULL,
  `effort` varchar(20) DEFAULT NULL,
  `funding` varchar(20) DEFAULT NULL,
  `proj_mgr` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (11111,'Fi','2017-04-06','2017-04-19','Medium','Business Funded','Lucius'),(22222,'Loon','2017-01-05','2017-02-08','Medium','Business Funded','Lucius'),(33333,'Hyperloop','2017-04-07','2017-04-28','MEdium','Business Funded','Lucius');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `firstname` varchar(40) NOT NULL,
  `lastname` varchar(40) NOT NULL,
  `Emp_key` int(11) NOT NULL AUTO_INCREMENT,
  `emp_ty` varchar(20) DEFAULT NULL,
  `skill` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Emp_key`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` VALUES ('Ross','Geller',13,'Contractoor','Angular','bruce@tcs.com'),('Ross','Geller',14,'Contractoor','Angular','bruce@tcs.com'),('Venkatesh','Raghunathan',15,'Contractoor','Angular','bruce@tcs.com'),('Ross','Geller',16,'Contractor','Angular','bruce@tcs.com');
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `avail_view`
--

/*!50001 DROP TABLE IF EXISTS `avail_view`*/;
/*!50001 DROP VIEW IF EXISTS `avail_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `avail_view` AS select `r1`.`firstname` AS `firstname`,`r1`.`lastname` AS `lastname`,`r1`.`Emp_key` AS `Emp_key`,`r3`.`id` AS `id`,`r3`.`project_name` AS `project_name`,`r3`.`project_end_dt` AS `project_end_dt` from ((`resource` `r1` join `allocation` `r2`) join `project` `r3`) where ((`r3`.`project_end_dt` > (select (curdate() - interval 30 day))) and (`r3`.`id` = `r2`.`project_id`) and (`r2`.`Emp_key` = `r1`.`Emp_key`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-17  0:05:21
