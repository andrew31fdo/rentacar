
LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (115,'Sri Lanka','763052222V','Fernando','23453233','Heenpandala','87475246892','80000',NULL,'31/7, Ossanagoda 1 st lane','test','Andrew','andrew31fdo@gmail.com','Galle',1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `custtype` WRITE;
/*!40000 ALTER TABLE `custtype` DISABLE KEYS */;
INSERT INTO `custtype` VALUES (1,'Corporate'),(2,'Internet');
/*!40000 ALTER TABLE `custtype` ENABLE KEYS */;
UNLOCK TABLES;



LOCK TABLES `feature` WRITE;
/*!40000 ALTER TABLE `feature` DISABLE KEYS */;
INSERT INTO `feature` VALUES (104,'AC'),(105,'Cruise Control'),(106,'CD Player'),(107,'Automatic'),(108,'7 Seater');
/*!40000 ALTER TABLE `feature` ENABLE KEYS */;
UNLOCK TABLES;



LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (109,'Colombo Airport','Katunayake','Lot 231, Arrival Hall,','Mr Dammika','Airport Branch'),(110,'Wakwella Rd','Galle','45, Stafford Building','Mr Punchihewa','Head Office - Galle');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;



LOCK TABLES `make` WRITE;
/*!40000 ALTER TABLE `make` DISABLE KEYS */;
INSERT INTO `make` VALUES (1,'Toyota','Japanies corporation'),(2,'Nissan','Japanies corporation'),(3,'Micro','Sri Lankan Company');
/*!40000 ALTER TABLE `make` ENABLE KEYS */;
UNLOCK TABLES;



LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (116,1000,'1','Driver'),(117,400,'','Navigation');
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `rate` WRITE;
/*!40000 ALTER TABLE `rate` DISABLE KEYS */;
/*!40000 ALTER TABLE `rate` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `ratetype` WRITE;
/*!40000 ALTER TABLE `ratetype` DISABLE KEYS */;
INSERT INTO `ratetype` VALUES (111,'Daily',1),(112,'weekly',7),(113,'Monthly',30),(114,'Extra Milage',1);
/*!40000 ALTER TABLE `ratetype` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `ratetype_rate` WRITE;
/*!40000 ALTER TABLE `ratetype_rate` DISABLE KEYS */;
/*!40000 ALTER TABLE `ratetype_rate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;



LOCK TABLES `reservationoption` WRITE;
/*!40000 ALTER TABLE `reservationoption` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservationoption` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,'333','AC','d84944932',NULL,NULL,'KH-3818','Gray',32832,100,109,1,101),(51,'333','AC','384295503CH',NULL,NULL,'KP-4523','Gray',12993,110,110,2,102);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `vehicle_feature` WRITE;
/*!40000 ALTER TABLE `vehicle_feature` DISABLE KEYS */;
INSERT INTO `vehicle_feature` VALUES (104,1),(105,1),(105,51),(107,51),(108,51);
/*!40000 ALTER TABLE `vehicle_feature` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `vehicle_rate` WRITE;
/*!40000 ALTER TABLE `vehicle_rate` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicle_rate` ENABLE KEYS */;
UNLOCK TABLES;



LOCK TABLES `vehicleclass` WRITE;
/*!40000 ALTER TABLE `vehicleclass` DISABLE KEYS */;
INSERT INTO `vehicleclass` VALUES (101,'Economy','Cheep'),(102,'Semi-Luxery','Medium priced'),(103,'Luxery','Comfotable');
/*!40000 ALTER TABLE `vehicleclass` ENABLE KEYS */;
UNLOCK TABLES;

-- Dumping data for table `vehiclemodel`
--

LOCK TABLES `vehiclemodel` WRITE;
/*!40000 ALTER TABLE `vehiclemodel` DISABLE KEYS */;
INSERT INTO `vehiclemodel` VALUES (1,'Puris',1),(2,'Sunny',2),(3,'Corolla',1),(4,'panda',3);
/*!40000 ALTER TABLE `vehiclemodel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-01-06 23:03:13
