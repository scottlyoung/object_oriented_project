-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: laikaDB
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `dbAccounts`
--

DROP TABLE IF EXISTS `dbAccounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbAccounts` (
  `accountID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(350) NOT NULL,
  `password` varchar(350) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL,
  PRIMARY KEY (`accountID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbAccounts`
--

LOCK TABLES `dbAccounts` WRITE;
/*!40000 ALTER TABLE `dbAccounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbAccounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbArtists`
--

DROP TABLE IF EXISTS `dbArtists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbArtists` (
  `artistID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(350) NOT NULL,
  PRIMARY KEY (`artistID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbArtists`
--

LOCK TABLES `dbArtists` WRITE;
/*!40000 ALTER TABLE `dbArtists` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbArtists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbPlaylistSongs`
--

DROP TABLE IF EXISTS `dbPlaylistSongs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbPlaylistSongs` (
  `playlistID` int(11) NOT NULL,
  `songID` int(11) NOT NULL,
  PRIMARY KEY (`playlistID`,`songID`),
  KEY `songID` (`songID`),
  CONSTRAINT `dbPlaylistSongs_ibfk_1` FOREIGN KEY (`playlistID`) REFERENCES `dbPlaylists` (`playlistID`),
  CONSTRAINT `dbPlaylistSongs_ibfk_2` FOREIGN KEY (`songID`) REFERENCES `dbSongs` (`songID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbPlaylistSongs`
--

LOCK TABLES `dbPlaylistSongs` WRITE;
/*!40000 ALTER TABLE `dbPlaylistSongs` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbPlaylistSongs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbPlaylists`
--

DROP TABLE IF EXISTS `dbPlaylists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbPlaylists` (
  `playlistID` int(11) NOT NULL AUTO_INCREMENT,
  `accountID` int(11) NOT NULL,
  `name` varchar(350) NOT NULL,
  PRIMARY KEY (`playlistID`),
  KEY `accountID` (`accountID`),
  CONSTRAINT `dbPlaylists_ibfk_1` FOREIGN KEY (`accountID`) REFERENCES `dbAccounts` (`accountID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbPlaylists`
--

LOCK TABLES `dbPlaylists` WRITE;
/*!40000 ALTER TABLE `dbPlaylists` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbPlaylists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbSongs`
--

DROP TABLE IF EXISTS `dbSongs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbSongs` (
  `songID` int(11) NOT NULL AUTO_INCREMENT,
  `artistID` int(11) NOT NULL,
  `title` varchar(350) NOT NULL,
  `genre` varchar(350) NOT NULL,
  `duration` smallint(6) NOT NULL,
  `album` varchar(350) NOT NULL,
  PRIMARY KEY (`songID`),
  KEY `artistID` (`artistID`),
  CONSTRAINT `dbSongs_ibfk_1` FOREIGN KEY (`artistID`) REFERENCES `dbArtists` (`artistID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbSongs`
--

LOCK TABLES `dbSongs` WRITE;
/*!40000 ALTER TABLE `dbSongs` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbSongs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-20 20:11:46
