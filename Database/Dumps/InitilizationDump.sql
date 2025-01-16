-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: parkingmonitor
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(32) NOT NULL,
  `DepartmentType` enum('POLICE','PARKING-AUTHORITY') NOT NULL,
  `CreatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `JurisdictionID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `JurisdictionIDX` (`JurisdictionID`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`JurisdictionID`) REFERENCES `jurisdiction` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `department_chk_1` CHECK ((char_length(`Name`) >= 3))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jurisdiction`
--

DROP TABLE IF EXISTS `jurisdiction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jurisdiction` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `City` varchar(32) NOT NULL,
  `StateInitials` char(2) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UniqueCityState` (`City`,`StateInitials`),
  KEY `CityStateIDX` (`City`,`StateInitials`),
  CONSTRAINT `jurisdiction_chk_1` CHECK ((char_length(`City`) >= 3)),
  CONSTRAINT `jurisdiction_chk_2` CHECK ((char_length(`StateInitials`) = 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jurisdiction`
--

LOCK TABLES `jurisdiction` WRITE;
/*!40000 ALTER TABLE `jurisdiction` DISABLE KEYS */;
/*!40000 ALTER TABLE `jurisdiction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `officer`
--

DROP TABLE IF EXISTS `officer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `officer` (
  `ID` int NOT NULL,
  `IdentificationNumber` varchar(16) NOT NULL,
  `DepartmentID` int NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UniqueIDDepartment` (`IdentificationNumber`,`DepartmentID`),
  KEY `DepartmentIDX` (`DepartmentID`),
  CONSTRAINT `officer_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `officer_ibfk_2` FOREIGN KEY (`DepartmentID`) REFERENCES `department` (`ID`),
  CONSTRAINT `officer_chk_1` CHECK ((char_length(`IdentificationNumber`) >= 3))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `officer`
--

LOCK TABLES `officer` WRITE;
/*!40000 ALTER TABLE `officer` DISABLE KEYS */;
/*!40000 ALTER TABLE `officer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NOT NULL,
  `VehicleID` int NOT NULL,
  `AddressID` int NOT NULL,
  `ViolationDescription` varchar(256) DEFAULT NULL,
  `CreatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `UserIDX` (`UserID`),
  KEY `VehicleIDX` (`VehicleID`),
  KEY `AddressIDX` (`AddressID`),
  KEY `CreatedAtIDX` (`CreatedAt`),
  CONSTRAINT `report_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`ID`) ON UPDATE CASCADE,
  CONSTRAINT `report_ibfk_2` FOREIGN KEY (`VehicleID`) REFERENCES `reportvehicle` (`ID`) ON UPDATE CASCADE,
  CONSTRAINT `report_ibfk_3` FOREIGN KEY (`AddressID`) REFERENCES `reportaddress` (`ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reportaddress`
--

DROP TABLE IF EXISTS `reportaddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reportaddress` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `JurisdictionID` int NOT NULL,
  `Zipcode` varchar(9) NOT NULL,
  `Latitude` decimal(9,6) NOT NULL,
  `Longitude` decimal(9,6) NOT NULL,
  `Notes` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `JurisdictionIDX` (`JurisdictionID`),
  CONSTRAINT `reportaddress_ibfk_1` FOREIGN KEY (`JurisdictionID`) REFERENCES `jurisdiction` (`ID`) ON UPDATE CASCADE,
  CONSTRAINT `reportaddress_chk_1` CHECK ((char_length(`Zipcode`) >= 5))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reportaddress`
--

LOCK TABLES `reportaddress` WRITE;
/*!40000 ALTER TABLE `reportaddress` DISABLE KEYS */;
/*!40000 ALTER TABLE `reportaddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reportimage`
--

DROP TABLE IF EXISTS `reportimage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reportimage` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ReportID` int NOT NULL,
  `ImageURL` text NOT NULL,
  `ImageType` enum('LICENSE-PLATE','VIOLATION') NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ReportIDX` (`ReportID`),
  CONSTRAINT `reportimage_ibfk_1` FOREIGN KEY (`ReportID`) REFERENCES `report` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reportimage`
--

LOCK TABLES `reportimage` WRITE;
/*!40000 ALTER TABLE `reportimage` DISABLE KEYS */;
/*!40000 ALTER TABLE `reportimage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reportvehicle`
--

DROP TABLE IF EXISTS `reportvehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reportvehicle` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Manufacturer` varchar(16) NOT NULL,
  `Model` varchar(32) NOT NULL,
  `Year` smallint NOT NULL,
  `Color` varchar(8) DEFAULT NULL,
  `LicensePlateID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `LicensePlateIDX` (`LicensePlateID`),
  CONSTRAINT `reportvehicle_ibfk_1` FOREIGN KEY (`LicensePlateID`) REFERENCES `vehiclelicenseplate` (`ID`),
  CONSTRAINT `reportvehicle_chk_1` CHECK ((char_length(`Manufacturer`) >= 2)),
  CONSTRAINT `reportvehicle_chk_2` CHECK ((char_length(`Model`) >= 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reportvehicle`
--

LOCK TABLES `reportvehicle` WRITE;
/*!40000 ALTER TABLE `reportvehicle` DISABLE KEYS */;
/*!40000 ALTER TABLE `reportvehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(32) NOT NULL,
  `LastName` varchar(32) NOT NULL,
  `BirthDate` date NOT NULL,
  `ProfilePictureURL` varchar(2048) DEFAULT 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png',
  `AccountType` enum('USER','OFFICER') NOT NULL DEFAULT 'USER',
  `CreatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `AccountTypeIDX` (`AccountType`),
  CONSTRAINT `user_chk_1` CHECK ((char_length(`FirstName`) >= 2)),
  CONSTRAINT `user_chk_2` CHECK ((char_length(`LastName`) >= 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usercredentials`
--

DROP TABLE IF EXISTS `usercredentials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usercredentials` (
  `ID` int NOT NULL,
  `EmailAddress` varchar(320) NOT NULL,
  `HashedPassword` varchar(256) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EmailAddress` (`EmailAddress`),
  KEY `EmailAddressIDX` (`EmailAddress`),
  CONSTRAINT `usercredentials_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usercredentials_chk_1` CHECK ((char_length(`EmailAddress`) >= 8)),
  CONSTRAINT `usercredentials_chk_2` CHECK ((char_length(`HashedPassword`) >= 8))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usercredentials`
--

LOCK TABLES `usercredentials` WRITE;
/*!40000 ALTER TABLE `usercredentials` DISABLE KEYS */;
/*!40000 ALTER TABLE `usercredentials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiclelicenseplate`
--

DROP TABLE IF EXISTS `vehiclelicenseplate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiclelicenseplate` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Number` varchar(10) NOT NULL,
  `StateInitials` char(2) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UniqueLicensePlate` (`Number`,`StateInitials`),
  CONSTRAINT `vehiclelicenseplate_chk_1` CHECK ((char_length(`StateInitials`) = 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiclelicenseplate`
--

LOCK TABLES `vehiclelicenseplate` WRITE;
/*!40000 ALTER TABLE `vehiclelicenseplate` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehiclelicenseplate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'parkingmonitor'
--

--
-- Dumping routines for database 'parkingmonitor'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-13 12:47:40
