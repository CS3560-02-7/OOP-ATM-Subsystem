-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: atm
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `transferdestinationaccount`
--

DROP TABLE IF EXISTS `transferdestinationaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transferdestinationaccount` (
  `transactionID` int NOT NULL,
  `destinationAccountID` int NOT NULL,
  PRIMARY KEY (`transactionID`,`destinationAccountID`),
  KEY `destinationAccountID` (`destinationAccountID`),
  CONSTRAINT `transferdestinationaccount_ibfk_1` FOREIGN KEY (`transactionID`) REFERENCES `transfer` (`transactionID`),
  CONSTRAINT `transferdestinationaccount_ibfk_2` FOREIGN KEY (`destinationAccountID`) REFERENCES `account` (`accountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferdestinationaccount`
--

LOCK TABLES `transferdestinationaccount` WRITE;
/*!40000 ALTER TABLE `transferdestinationaccount` DISABLE KEYS */;
INSERT INTO `transferdestinationaccount` VALUES (100,12345678),(108,12345678),(109,12345678),(114,12345678),(116,12345678),(118,12345678),(120,12345678),(3,23456789),(7,23456789),(110,23456789),(111,23456789),(112,23456789),(113,23456789),(115,23456789),(117,23456789),(119,23456789);
/*!40000 ALTER TABLE `transferdestinationaccount` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-12  2:01:11
