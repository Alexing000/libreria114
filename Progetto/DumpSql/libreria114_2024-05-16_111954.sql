-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: libreria114
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `associa`
--

DROP TABLE IF EXISTS `associa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `associa` (
  `id_libro` int DEFAULT NULL,
  `id_utente` int DEFAULT NULL,
  KEY `id_libro` (`id_libro`),
  KEY `id_utente` (`id_utente`),
  CONSTRAINT `associa_ibfk_1` FOREIGN KEY (`id_libro`) REFERENCES `libro` (`id`),
  CONSTRAINT `associa_ibfk_2` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `associa`
--

/*!40000 ALTER TABLE `associa` DISABLE KEYS */;
INSERT INTO `associa` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15),(16,16),(17,17),(18,18),(19,19),(20,20);
/*!40000 ALTER TABLE `associa` ENABLE KEYS */;

--
-- Table structure for table `autore`
--

DROP TABLE IF EXISTS `autore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autore` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `cognome` varchar(100) DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `id_libro` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_libro` (`id_libro`),
  CONSTRAINT `autore_ibfk_1` FOREIGN KEY (`id_libro`) REFERENCES `libro` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autore`
--

/*!40000 ALTER TABLE `autore` DISABLE KEYS */;
INSERT INTO `autore` VALUES (1,'J.R.R.','Tolkien',4.9,1),(2,'George','Orwell',4.7,2),(3,'F. Scott','Fitzgerald',4.4,3),(4,'Jane','Austen',4.5,4),(5,'J.K.','Rowling',4.8,5),(6,'Dan','Brown',4.2,6),(7,'Herman','Melville',4.1,7),(8,'Harper','Lee',4.8,8),(9,'Umberto','Eco',4.6,9),(10,'Miguel','de Cervantes',4.3,10),(11,'Emily','Brontë',4.4,11),(12,'Dante','Alighieri',4.9,12),(13,'Antoine','de Saint-Exupéry',4.7,13),(14,'Mary','Shelley',4.3,14),(15,'Bram','Stoker',4.2,15),(16,'Alexandre','Dumas',4.6,16),(17,'Franz','Kafka',4.4,17),(18,'Lev','Tolstoj',4.8,18),(19,'Victor','Hugo',4.7,19),(20,'Lev','Tolstoj',4.5,20);
/*!40000 ALTER TABLE `autore` ENABLE KEYS */;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titolo` varchar(100) DEFAULT NULL,
  `trama` varchar(1000) DEFAULT NULL,
  `autore` varchar(100) DEFAULT NULL,
  `nPagine` int DEFAULT NULL,
  `genere` varchar(100) DEFAULT NULL,
  `dataPubblicazione` date DEFAULT NULL,
  `rating` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (1,'Il Signore degli Anelli','Una compagnia di eroi deve distruggere un anello magico per salvare il mondo.','J.R.R. Tolkien',1216,'Fantasy','1954-07-29',4.9),(2,'1984','Un uomo lotta contro un regime totalitario in un futuro distopico.','George Orwell',328,'Distopico','1949-06-08',4.7),(3,'Il Grande Gatsby','La storia di Jay Gatsby e il suo sogno americano nel 1920.','F. Scott Fitzgerald',180,'Classico','1925-04-10',4.4),(4,'Orgoglio e Pregiudizio','Una storia d\'amore e di classi sociali nella Inghilterra del XIX secolo.','Jane Austen',432,'Romance','1813-01-28',4.5),(5,'Harry Potter e la Pietra Filosofale','Un giovane mago scopre il suo destino in un mondo magico.','J.K. Rowling',309,'Fantasy','1997-06-26',4.8),(6,'Il Codice Da Vinci','Un professore di simbologia e una crittologa svelano un mistero religioso.','Dan Brown',489,'Thriller','2003-03-18',4.2),(7,'Moby Dick','Il capitano Achab insegue la leggendaria balena bianca.','Herman Melville',635,'Avventura','1851-10-18',4.1),(8,'To Kill a Mockingbird','Una giovane ragazza osserva il razzismo nel Sud degli Stati Uniti.','Harper Lee',281,'Classico','1960-07-11',4.8),(9,'Il Nome della Rosa','Un monaco detective indaga su misteriosi omicidi in un monastero medievale.','Umberto Eco',512,'Giallo','1980-09-01',4.6),(10,'Don Chisciotte','Le avventure di un cavaliere idealista e del suo fedele scudiero.','Miguel de Cervantes',863,'Classico','1605-01-16',4.3),(11,'Cime Tempestose','Una storia di amore e vendetta nella brughiera inglese.','Emily Brontë',416,'Romance','1847-12-01',4.4),(12,'La Divina Commedia','Un viaggio epico attraverso Inferno, Purgatorio e Paradiso.','Dante Alighieri',798,'Poesia','1320-01-01',4.9),(13,'Il Piccolo Principe','Un pilota si schianta nel deserto e incontra un giovane principe da un altro pianeta.','Antoine de Saint-Exupéry',96,'Fiaba','1943-04-06',4.7),(14,'Frankenstein','Uno scienziato crea una creatura mostruosa in un esperimento audace.','Mary Shelley',280,'Horror','1818-01-01',4.3),(15,'Dracula','Il conte Dracula cerca di trasferirsi dall\'Europa orientale all\'Inghilterra per trovare nuove vittime.','Bram Stoker',418,'Horror','1897-05-26',4.2),(16,'Il Conte di Montecristo','Un uomo ingiustamente imprigionato fugge e cerca vendetta.','Alexandre Dumas',1276,'Avventura','1844-08-28',4.6),(17,'La Metamorfosi','Un uomo si sveglia trasformato in un gigantesco insetto.','Franz Kafka',102,'Fantascienza','1915-01-01',4.4),(18,'Guerra e Pace','Un epico romanzo sulla vita in Russia durante le guerre napoleoniche.','Lev Tolstoj',1225,'Storico','1869-01-01',4.8),(19,'I Miserabili','La storia di Jean Valjean, un ex galeotto in cerca di redenzione.','Victor Hugo',1488,'Storico','1862-01-01',4.7),(20,'Anna Karenina','La tragica storia d\'amore di Anna Karenina nella Russia zarista.','Lev Tolstoj',864,'Classico','1877-01-01',4.5);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `cognome` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `ruolo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'jdoe','password1','John','Doe','jdoe@example.com','admin'),(2,'asmith','password2','Alice','Smith','asmith@example.com','user'),(3,'bwhite','password3','Bob','White','bwhite@example.com','user'),(4,'cgreen','password4','Carol','Green','cgreen@example.com','user'),(5,'dblack','password5','David','Black','dblack@example.com','user'),(6,'eknight','password6','Eva','Knight','eknight@example.com','user'),(7,'fwoods','password7','Frank','Woods','fwoods@example.com','user'),(8,'ghall','password8','Grace','Hall','ghall@example.com','user'),(9,'ijones','password9','Ivy','Jones','ijones@example.com','user'),(10,'jking','password10','Jack','King','jking@example.com','user'),(11,'kmoore','password11','Karen','Moore','kmoore@example.com','user'),(12,'llane','password12','Leo','Lane','llane@example.com','user'),(13,'mclark','password13','Mona','Clark','mclark@example.com','user'),(14,'nfox','password14','Nathan','Fox','nfox@example.com','user'),(15,'owilson','password15','Olivia','Wilson','owilson@example.com','user'),(16,'pscott','password16','Paul','Scott','pscott@example.com','user'),(17,'qyoung','password17','Quincy','Young','qyoung@example.com','user'),(18,'rhill','password18','Rachel','Hill','rhill@example.com','user'),(19,'ssanders','password19','Sam','Sanders','ssanders@example.com','user'),(20,'ttaylor','password20','Tina','Taylor','ttaylor@example.com','user');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;

--
-- Dumping routines for database 'libreria114'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-16 11:19:59
