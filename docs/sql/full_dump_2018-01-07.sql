-- MySQL dump 10.16  Distrib 10.1.30-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: db_locadora
-- ------------------------------------------------------
-- Server version	10.1.30-MariaDB

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
-- Table structure for table `seq_cli`
--

DROP TABLE IF EXISTS `seq_cli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seq_cli` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq_cli`
--

LOCK TABLES `seq_cli` WRITE;
/*!40000 ALTER TABLE `seq_cli` DISABLE KEYS */;
INSERT INTO `seq_cli` VALUES (132);
/*!40000 ALTER TABLE `seq_cli` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq_func`
--

DROP TABLE IF EXISTS `seq_func`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seq_func` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq_func`
--

LOCK TABLES `seq_func` WRITE;
/*!40000 ALTER TABLE `seq_func` DISABLE KEYS */;
INSERT INTO `seq_func` VALUES (2017070);
/*!40000 ALTER TABLE `seq_func` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq_veic`
--

DROP TABLE IF EXISTS `seq_veic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seq_veic` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq_veic`
--

LOCK TABLES `seq_veic` WRITE;
/*!40000 ALTER TABLE `seq_veic` DISABLE KEYS */;
INSERT INTO `seq_veic` VALUES (1020);
/*!40000 ALTER TABLE `seq_veic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cancelamento_locacao`
--

DROP TABLE IF EXISTS `tb_cancelamento_locacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_cancelamento_locacao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_funcionario` bigint(20) NOT NULL,
  `id_locacao` bigint(20) NOT NULL,
  `descricao` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_funcionario` (`id_funcionario`),
  KEY `id_locacao` (`id_locacao`),
  CONSTRAINT `fk_gerente` FOREIGN KEY (`id_funcionario`) REFERENCES `tb_funcionario` (`id`),
  CONSTRAINT `fk_locacao` FOREIGN KEY (`id_locacao`) REFERENCES `tb_locacao` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cancelamento_locacao`
--

LOCK TABLES `tb_cancelamento_locacao` WRITE;
/*!40000 ALTER TABLE `tb_cancelamento_locacao` DISABLE KEYS */;
INSERT INTO `tb_cancelamento_locacao` VALUES (25,2017002,24,'Locação fictícia para fins de teste'),(26,2017002,11,'Cortesia oferecida pela locadora.'),(27,2017002,22,'Cortesia oferecida pela locadora.'),(28,2017003,19,'Cortesia oferecida pela locadora.'),(29,2017003,20,'Locação fictícia para fins de teste'),(30,2017003,21,'Locação fictícia para fins de teste'),(31,2017003,23,'Locação fictícia para fins de teste'),(32,2017003,28,'Locação fictícia para fins de teste'),(33,2017003,25,'Locação fictícia para fins de teste'),(34,2017003,29,'Locação fictícia para fins de teste'),(35,2017003,31,'Locação fictícia para fins de teste'),(36,2017003,30,'Locação fictícia para fins de teste'),(37,2017003,36,'Locação fictícia para fins de teste'),(38,2017003,33,'Cortesia oferecida pela locadora.'),(39,2017003,34,'Cortesia oferecida pela locadora.'),(40,2017003,35,'Locação fictícia para fins de teste'),(41,2017003,38,'Locação fictícia para fins de teste'),(42,2017003,32,'Cortesia oferecida pela locadora.'),(43,2017003,37,'Locação fictícia para fins de teste'),(44,2017003,18,'Locação fictícia para fins de teste'),(45,2017003,42,'Locação fictícia para fins de teste'),(46,2017003,39,'Locação fictícia para fins de teste'),(47,2017003,43,'Locação fictícia para fins de teste'),(48,2017003,44,'Locação fictícia para fins de teste');
/*!40000 ALTER TABLE `tb_cancelamento_locacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cliente`
--

DROP TABLE IF EXISTS `tb_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_cliente` (
  `id` bigint(20) NOT NULL,
  `data_cadastro` date NOT NULL,
  `nome` varchar(20) NOT NULL,
  `sobrenome` varchar(50) NOT NULL,
  `genero` varchar(1) NOT NULL,
  `data_nascimento` date NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `habilitacao` varchar(11) NOT NULL,
  `validade_habilitacao` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `habilitacao_uk` (`habilitacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cliente`
--

LOCK TABLES `tb_cliente` WRITE;
/*!40000 ALTER TABLE `tb_cliente` DISABLE KEYS */;
INSERT INTO `tb_cliente` VALUES (101,'2017-09-27','elizabeth','alexandra mary windsor','F','1989-04-19','(11) 1111-33333','11111111111','2018-04-19'),(102,'2017-10-14','rachel','sheherazade barbosa','F','1973-08-28','(22) 2222-22222','22222222222','2018-08-28'),(103,'2017-10-12','james','gosling pai do java','M','1955-05-19','(11) 1111-11111','33333333333','2029-12-30'),(104,'2017-09-27','alberto','santos dumont art','M','1983-07-19','(11) 1111-11111','44444444444','2018-01-19'),(105,'2017-10-06','cora','coralina anna lins','F','1985-08-19','(11) 1111-11111','55555555555','2018-03-30'),(106,'2017-10-07','kate','middleton duquesa de cambridge','F','1982-01-08','(11) 1111-11111','66666666666','2019-01-08'),(107,'2017-10-06','william','arthur philip príncipe inglaterra','M','1982-06-21','(11) 1111-11111','77777777777','2018-06-21'),(108,'2017-10-07','james','earl carter jr presidente eua','M','1994-09-30','(11) 2222-2211','88888888888','2017-11-20'),(109,'2017-10-14','joice','cristina hasselmann','F','1978-01-29','(11) 1111-11111','99999999999','2018-01-28'),(110,'2017-10-14','miguel','angelo laporta cientista brasileiro','M','1961-03-05','(11) 3333-33333','00000000000','2018-03-05'),(111,'2017-11-05','joão','francisco de azevedo inventor da máq de escrever','M','1994-03-04','(11) 1111-11111','00000000001','2020-01-30'),(131,'2017-12-27','amélia','império hamburger física brasileira','F','1952-06-12','(11) 1111-11111','11111111112','2019-06-12');
/*!40000 ALTER TABLE `tb_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_funcionario`
--

DROP TABLE IF EXISTS `tb_funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_funcionario` (
  `id` bigint(20) NOT NULL,
  `data_cadastro` date NOT NULL,
  `nome` varchar(20) NOT NULL,
  `sobrenome` varchar(50) NOT NULL,
  `cargo` varchar(2) NOT NULL,
  `genero` varchar(1) NOT NULL,
  `data_nascimento` date NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `password` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf_uk` (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_funcionario`
--

LOCK TABLES `tb_funcionario` WRITE;
/*!40000 ALTER TABLE `tb_funcionario` DISABLE KEYS */;
INSERT INTO `tb_funcionario` VALUES (2017001,'2017-10-15','atendente','tester tester teste','AT','M','1991-01-01','000.000.000-00','(11) 1111-2222','11111111'),(2017002,'2017-09-28','ada','augusta king condessa de lovelace dana','GE','F','1985-12-09','111.111.111-11','(11) 4444-4444','12345678'),(2017003,'2017-09-28','dana','katherine scully','GE','F','1964-02-21','222.222.222-22','(11) 1111-12222','11111111');
/*!40000 ALTER TABLE `tb_funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_locacao`
--

DROP TABLE IF EXISTS `tb_locacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_locacao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_funcionario` bigint(20) NOT NULL,
  `id_cliente` bigint(20) NOT NULL,
  `id_veiculo` bigint(20) NOT NULL,
  `data_inicial` date NOT NULL,
  `data_final` date DEFAULT NULL,
  `km_percorrida` int(11) NOT NULL,
  `status` varchar(15) NOT NULL,
  `valor_diaria` double NOT NULL,
  `valor_total` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_veiculo` (`id_veiculo`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_funcionario` (`id_funcionario`),
  CONSTRAINT `fk_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `tb_cliente` (`id`),
  CONSTRAINT `fk_funcionario` FOREIGN KEY (`id_funcionario`) REFERENCES `tb_funcionario` (`id`),
  CONSTRAINT `fk_veiculo` FOREIGN KEY (`id_veiculo`) REFERENCES `tb_veiculo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_locacao`
--

LOCK TABLES `tb_locacao` WRITE;
/*!40000 ALTER TABLE `tb_locacao` DISABLE KEYS */;
INSERT INTO `tb_locacao` VALUES (1,2017002,110,1000,'2017-12-09','2017-12-14',0,'FINALIZADO',30,150),(2,2017002,104,1015,'2017-12-09','2017-12-14',0,'FINALIZADO',50,250),(3,2017002,131,1017,'2017-12-10','2018-01-05',0,'FINALIZADO',50,1300),(4,2017002,110,1002,'2017-12-14','2017-12-15',0,'FINALIZADO',30,60),(5,2017002,110,1008,'2017-12-15','2017-12-28',0,'FINALIZADO',20,260),(6,2017002,104,1013,'2017-12-20','2017-12-29',0,'FINALIZADO',30,270),(7,2017002,101,1018,'2017-12-23','2017-12-29',0,'FINALIZADO',50,300),(8,2017002,102,1019,'2017-12-27','2017-12-29',0,'FINALIZADO',50,100),(9,2017002,103,1017,'2017-12-27','2017-12-29',0,'FINALIZADO',50,100),(10,2017002,105,1000,'2017-12-27','2017-12-31',0,'FINALIZADO',30,120),(11,2017002,106,1012,'2017-12-27','2017-12-31',0,'CANCELADO',30,0),(12,2017002,107,1001,'2017-12-27','2017-12-29',0,'FINALIZADO',400,800),(13,2017002,109,1005,'2017-12-27','2017-12-28',0,'FINALIZADO',400,400),(14,2017002,111,1016,'2017-12-27','2017-12-28',0,'FINALIZADO',20,20),(15,2017002,131,1006,'2017-12-27','2017-12-28',0,'FINALIZADO',50,50),(16,2017002,131,1016,'2017-12-28','2017-12-28',0,'FINALIZADO',20,20),(17,2017002,131,1010,'2017-12-28','2017-12-28',0,'FINALIZADO',20,20),(18,2017002,131,1001,'2017-12-29','2018-01-05',0,'CANCELADO',400,0),(19,2017002,101,1005,'2017-12-31','2018-01-01',0,'CANCELADO',400,0),(20,2017002,102,1018,'2017-12-31','2018-01-01',0,'CANCELADO',50,0),(21,2017002,105,1002,'2017-12-31','2018-01-01',0,'CANCELADO',30,0),(22,2017002,106,1001,'2017-12-31','2017-12-31',0,'CANCELADO',400,0),(23,2017002,103,1006,'2017-12-31','2018-01-01',0,'CANCELADO',50,0),(24,2017002,104,1013,'2017-12-31','2017-12-31',0,'CANCELADO',30,0),(25,2017002,110,1000,'2018-01-01','2018-01-04',0,'CANCELADO',30,0),(26,2017002,105,1010,'2018-01-01','2018-01-04',0,'FINALIZADO',20,60),(27,2017002,104,1008,'2018-01-01','2018-01-04',0,'FINALIZADO',20,60),(28,2017002,102,1011,'2018-01-03','2018-01-03',5,'CANCELADO',20,0),(29,2017002,102,1011,'2018-01-03','2018-01-04',0,'CANCELADO',20,0),(30,2017002,102,1011,'2018-01-04','2018-01-04',0,'CANCELADO',20,0),(31,2017002,103,1000,'2018-01-04','2018-01-04',0,'CANCELADO',30,0),(32,2017002,101,1013,'2018-01-04','2018-01-05',0,'CANCELADO',30,0),(33,2017002,107,1016,'2018-01-04','2018-01-05',0,'CANCELADO',20,0),(34,2017002,106,1015,'2018-01-04','2018-01-05',0,'CANCELADO',20,0),(35,2017002,111,1002,'2018-01-04','2018-01-05',0,'CANCELADO',30,0),(36,2017002,131,1019,'2018-01-04','2018-01-05',0,'CANCELADO',50,0),(37,2017002,103,1012,'2018-01-04','2018-01-05',0,'CANCELADO',30,0),(38,2017002,110,1012,'2018-01-05','2018-01-05',0,'CANCELADO',30,0),(39,2017002,103,1005,'2018-01-05','2018-01-05',0,'CANCELADO',400,0),(40,2017002,109,1001,'2018-01-05','2018-01-06',0,'FINALIZADO',400,400),(41,2017002,110,1006,'2018-01-05','2018-01-05',0,'FINALIZADO',50,50),(42,2017002,102,1018,'2018-01-05','2018-01-05',0,'CANCELADO',50,0),(43,2017002,131,1011,'2018-01-06','2018-01-06',0,'CANCELADO',20,0),(44,2017002,103,1018,'2018-01-06','2018-01-06',0,'CANCELADO',50,0),(45,2017002,101,1008,'2018-01-06','2018-01-06',0,'FINALIZADO',20,20);
/*!40000 ALTER TABLE `tb_locacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_veiculo`
--

DROP TABLE IF EXISTS `tb_veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_veiculo` (
  `id` bigint(20) NOT NULL,
  `data_cadastro` date NOT NULL,
  `placa` varchar(8) NOT NULL,
  `marca` varchar(20) NOT NULL,
  `modelo` varchar(20) NOT NULL,
  `categoria` varchar(20) NOT NULL,
  `cor` varchar(20) NOT NULL,
  `ano_fabricacao` int(11) NOT NULL,
  `combustivel` varchar(20) NOT NULL,
  `km_inicial` int(11) NOT NULL,
  `km_atual` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `valor_diaria` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_leg11chk88p1csrmb81seh701` (`placa`),
  UNIQUE KEY `placa_uk` (`placa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_veiculo`
--

LOCK TABLES `tb_veiculo` WRITE;
/*!40000 ALTER TABLE `tb_veiculo` DISABLE KEYS */;
INSERT INTO `tb_veiculo` VALUES (1000,'2017-09-16','xxx-3333','FORD','tester','SEDA','AMARELO',2016,'FLEX',0,0,'DISPONIVEL',30),(1001,'2017-09-18','car-1987','HYUNDAI','tester','PREMIUM','PRATA',2016,'FLEX',30000,30000,'DISPONIVEL',400),(1002,'2017-09-18','isa-2007','AUDI','tester','SEDA','VERMELHO',2017,'FLEX',10000,10000,'DISPONIVEL',30),(1005,'2017-09-18','thi-1985','FORD','tester','PREMIUM','PRETO',2016,'GASOLINA',32000,32000,'DISPONIVEL',400),(1006,'2017-09-18','luc-1993','FIAT','tester','SUV','PRETO',2015,'ALCOOL',24000,24000,'DISPONIVEL',50),(1008,'2017-09-22','xxx-4444','RENAULT','tester','HATCH','AMARELO',2018,'FLEX',0,0,'DISPONIVEL',20),(1010,'2017-09-28','xxx-5555','FIAT','tester','HATCH','AMARELO',2016,'FLEX',0,0,'DISPONIVEL',20),(1011,'2017-10-15','xxx-2222','CITROEN','tester','HATCH','AMARELO',2018,'FLEX',0,0,'DISPONIVEL',20),(1012,'2017-11-22','xxx-0000','HYUNDAI','tester','SEDA','PRETO',2018,'FLEX',0,0,'DISPONIVEL',30),(1013,'2017-11-22','xxx-1111','VOLKSWAGEN','tester','SEDA','AMARELO',2017,'FLEX',0,0,'DISPONIVEL',30),(1015,'2017-11-24','xxx-6666','RENAULT','tester','HATCH','PRATA',2010,'FLEX',0,0,'DISPONIVEL',20),(1016,'2017-11-30','xxx-7777','VOLKSWAGEN','tester','HATCH','BRANCO',2017,'FLEX',0,0,'DISPONIVEL',20),(1017,'2017-12-14','ros-1954','AUDI','tester','SUV','VERMELHO',2015,'FLEX',63000,63000,'DISPONIVEL',50),(1018,'2017-12-14','pal-1978','AUDI','tester','SUV','AMARELO',2015,'FLEX',39000,39000,'DISPONIVEL',50),(1019,'2017-12-14','dan-1973','CITROEN','tester','SUV','AMARELO',2015,'FLEX',44000,44000,'DISPONIVEL',50);
/*!40000 ALTER TABLE `tb_veiculo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-07 21:23:12
