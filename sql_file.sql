-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.29 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table logistic_management_system_2.account
CREATE TABLE IF NOT EXISTS `account` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `ACCOUNTNO` varchar(255) DEFAULT NULL,
  `BALANCE` double DEFAULT NULL,
  `USER_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ACCOUNT_USER_ID` (`USER_ID`),
  CONSTRAINT `FK_ACCOUNT_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table logistic_management_system_2.account: ~0 rows (approximately)

-- Dumping structure for table logistic_management_system_2.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table logistic_management_system_2.admin: ~0 rows (approximately)
INSERT INTO `admin` (`id`, `name`, `password`) VALUES
	(1, 'admin', 'admin');

-- Dumping structure for table logistic_management_system_2.destination
CREATE TABLE IF NOT EXISTS `destination` (
  `id` int NOT NULL AUTO_INCREMENT,
  `destination_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table logistic_management_system_2.destination: ~3 rows (approximately)
INSERT INTO `destination` (`id`, `destination_name`) VALUES
	(1, 'Singopore-China-Colombo-Kandy'),
	(2, 'Colombo-Malaysia'),
	(22, 'Mumbai-Colombo-Durban-Savannah-New York'),
	(23, 'London - Paris - Rome - Cairo - Dubai');

-- Dumping structure for table logistic_management_system_2.freight
CREATE TABLE IF NOT EXISTS `freight` (
  `id` int NOT NULL AUTO_INCREMENT,
  `weight` int DEFAULT NULL,
  `transportation_id` int NOT NULL,
  `route_id` int NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `eta` varchar(45) DEFAULT NULL,
  `is_delivered` tinyint DEFAULT NULL,
  `is_failed` tinyint DEFAULT NULL,
  `has_started` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_freight_transportation1_idx` (`transportation_id`),
  KEY `fk_freight_route1_idx` (`route_id`),
  CONSTRAINT `fk_freight_route1` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`),
  CONSTRAINT `fk_freight_transportation1` FOREIGN KEY (`transportation_id`) REFERENCES `transportation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table logistic_management_system_2.freight: ~11 rows (approximately)
INSERT INTO `freight` (`id`, `weight`, `transportation_id`, `route_id`, `start_date`, `end_date`, `eta`, `is_delivered`, `is_failed`, `has_started`) VALUES
	(1, 100, 1, 1, '2024-05-16 21:55:01', '2024-05-16 21:55:04', '500', 0, 0, 0),
	(2, 100, 1, 2, '2024-05-16 21:55:01', '2024-05-16 21:55:03', '500', 0, 0, 0),
	(3, 100, 2, 3, '2024-05-16 21:55:02', '2024-05-16 21:55:03', '500', 0, 0, 0),
	(4, 500000, 4, 37, '2024-05-25 10:00:00', '2024-06-02 10:00:00', '550000.0', 1, 0, 1),
	(5, 500000, 4, 36, '2024-06-03 10:00:00', '2024-06-05 10:00:00', '550000.0', 1, 0, 1),
	(6, 500000, 5, 38, '2024-06-15 10:00:00', '2024-06-20 10:00:00', '550000.0', 1, 0, 1),
	(7, 2000, 6, 39, '2024-06-06 10:00:00', '2024-06-10 15:00:00', '200.0', 1, 0, 1),
	(8, 2000, 1, 39, '2024-06-06 10:00:00', '2024-06-10 15:00:00', NULL, NULL, NULL, 0),
	(13, 20000, 1, 42, '2024-05-20 02:00:00', '2024-05-22 02:00:00', '5000.0', 1, 0, 0),
	(14, 20000, 3, 40, '2024-05-23 02:00:00', '2024-05-25 02:00:00', '5000.0', 1, 0, 0),
	(15, 20000, 3, 43, '2024-05-27 02:00:00', '2024-05-29 02:00:00', '5000.0', 1, 0, 0),
	(16, 20000, 3, 41, '2024-06-01 02:00:00', '2024-06-06 02:00:00', '5000.0', 1, 0, 0);

-- Dumping structure for table logistic_management_system_2.freight_has_orders
CREATE TABLE IF NOT EXISTS `freight_has_orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orders_id` int NOT NULL,
  `freight_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_shipment_has_freight_orders1_idx` (`orders_id`),
  KEY `fk_freight_has_orders_freight1_idx` (`freight_id`),
  CONSTRAINT `fk_freight_has_orders_freight1` FOREIGN KEY (`freight_id`) REFERENCES `freight` (`id`),
  CONSTRAINT `fk_shipment_has_freight_orders1` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table logistic_management_system_2.freight_has_orders: ~11 rows (approximately)
INSERT INTO `freight_has_orders` (`id`, `orders_id`, `freight_id`) VALUES
	(5, 8, 5),
	(6, 8, 4),
	(7, 8, 6),
	(8, 8, 7),
	(29, 20, 5),
	(30, 20, 4),
	(31, 20, 6),
	(32, 20, 7),
	(33, 21, 16),
	(34, 21, 13),
	(35, 21, 15),
	(36, 21, 14);

-- Dumping structure for table logistic_management_system_2.freight_tracking
CREATE TABLE IF NOT EXISTS `freight_tracking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coordinates` varchar(45) DEFAULT NULL,
  `route_progress` varchar(50) DEFAULT NULL,
  `expected_delay` int DEFAULT NULL,
  `freight_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_freight_tracking_freight1_idx` (`freight_id`),
  CONSTRAINT `fk_freight_tracking_freight1` FOREIGN KEY (`freight_id`) REFERENCES `freight` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table logistic_management_system_2.freight_tracking: ~8 rows (approximately)
INSERT INTO `freight_tracking` (`id`, `coordinates`, `route_progress`, `expected_delay`, `freight_id`) VALUES
	(11, '235', '100%', 0, 6),
	(12, '476', '100%', 0, 7),
	(13, '227', '100%', 0, 4),
	(14, '326', '100%', 0, 5),
	(15, '0 0', 'not started', 0, 14),
	(16, '0 0', 'not started', 0, 16),
	(17, '0 0', 'not started', 0, 13),
	(18, '0 0', 'not started', 0, 15);

-- Dumping structure for table logistic_management_system_2.merchant
CREATE TABLE IF NOT EXISTS `merchant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table logistic_management_system_2.merchant: ~0 rows (approximately)
INSERT INTO `merchant` (`id`, `name`, `email`, `password`) VALUES
	(1, 'Test', 'Test', '123');

-- Dumping structure for table logistic_management_system_2.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `qty` int DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `expected_date` datetime DEFAULT NULL,
  `destination_id` int NOT NULL,
  `order_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_product1_idx` (`product_id`),
  KEY `fk_orders_destination1_idx` (`destination_id`),
  CONSTRAINT `fk_order_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_orders_destination1` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table logistic_management_system_2.orders: ~3 rows (approximately)
INSERT INTO `orders` (`id`, `product_id`, `qty`, `created_at`, `expected_date`, `destination_id`, `order_status`) VALUES
	(1, 1, 3, '2024-05-01 14:52:02', '2024-08-16 14:52:03', 22, 'not_shipped'),
	(5, 1, 20, '2024-05-17 19:55:26', '2024-07-27 00:00:00', 22, 'not_shipped'),
	(20, 2, 9, '2024-05-18 17:11:38', '2024-06-18 00:00:00', 22, 'not_shipped'),
	(21, 2, 20, '2024-05-19 15:34:51', '2024-06-12 20:00:00', 23, 'not_shipped');

-- Dumping structure for table logistic_management_system_2.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `measurement_unit` varchar(45) DEFAULT NULL,
  `merchant_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_merchant1_idx` (`merchant_id`),
  CONSTRAINT `fk_product_merchant1` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table logistic_management_system_2.product: ~0 rows (approximately)
INSERT INTO `product` (`id`, `title`, `weight`, `measurement_unit`, `merchant_id`) VALUES
	(1, 'abc_product_001', 50, 'Kg', 1),
	(2, 'abc_product_002', 10, 'Kg', 1);

-- Dumping structure for table logistic_management_system_2.route
CREATE TABLE IF NOT EXISTS `route` (
  `id` int NOT NULL AUTO_INCREMENT,
  `starting_point` varchar(45) DEFAULT NULL,
  `destination_point` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `destination_id` int NOT NULL,
  `route_order` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_route_destination1_idx` (`destination_id`),
  CONSTRAINT `fk_route_destination1` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table logistic_management_system_2.route: ~9 rows (approximately)
INSERT INTO `route` (`id`, `starting_point`, `destination_point`, `name`, `destination_id`, `route_order`) VALUES
	(1, 'China444', 'Singapore', 'China-Singapore', 1, 1),
	(2, 'Singapore', 'Colombo', 'Singapore-Colombo', 1, 2),
	(3, 'Colombo', 'Kandy', 'Colombo-Kandy', 1, 3),
	(36, 'Colombo', 'Durban', 'Colombo-Durban', 22, 2),
	(37, 'Mumbai', 'Colombo', 'Mumbai-Colombo', 22, 1),
	(38, 'Savannah', 'New York', 'Savannah-New York', 22, 4),
	(39, 'Durban', 'Savannah', 'Durban-Savannah', 22, 3),
	(40, ' Paris ', ' Rome ', ' Paris - Rome ', 23, 2),
	(41, ' Cairo ', ' Dubai', ' Cairo - Dubai', 23, 4),
	(42, 'London ', ' Paris ', 'London - Paris ', 23, 1),
	(43, ' Rome ', ' Cairo ', ' Rome - Cairo ', 23, 3);

-- Dumping structure for table logistic_management_system_2.shipment
CREATE TABLE IF NOT EXISTS `shipment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table logistic_management_system_2.shipment: ~0 rows (approximately)

-- Dumping structure for table logistic_management_system_2.ships
CREATE TABLE IF NOT EXISTS `ships` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ship_id` varchar(45) DEFAULT NULL,
  `ship_name` varchar(45) DEFAULT NULL,
  `transportation_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ships_transportation1_idx` (`transportation_id`),
  CONSTRAINT `fk_ships_transportation1` FOREIGN KEY (`transportation_id`) REFERENCES `transportation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table logistic_management_system_2.ships: ~0 rows (approximately)

-- Dumping structure for table logistic_management_system_2.transportation
CREATE TABLE IF NOT EXISTS `transportation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `transportation_type_id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `maximum_weight` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_transportation_transportation_type1_idx` (`transportation_type_id`),
  CONSTRAINT `fk_transportation_transportation_type1` FOREIGN KEY (`transportation_type_id`) REFERENCES `transportation_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table logistic_management_system_2.transportation: ~5 rows (approximately)
INSERT INTO `transportation` (`id`, `transportation_type_id`, `name`, `maximum_weight`) VALUES
	(1, 1, 'Cargo Ship 001', 2000000),
	(2, 2, 'Truck 001', 2000),
	(3, 1, 'Cargo Ship 002', 2000000),
	(4, 1, 'Cargo Ship 003 ', 500000),
	(5, 1, 'Cargo Ship 004', 500000),
	(6, 2, 'Truck 003', 5000);

-- Dumping structure for table logistic_management_system_2.transportation_type
CREATE TABLE IF NOT EXISTS `transportation_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table logistic_management_system_2.transportation_type: ~2 rows (approximately)
INSERT INTO `transportation_type` (`id`, `name`) VALUES
	(1, 'Ship'),
	(2, 'Truck');

-- Dumping structure for table logistic_management_system_2.truck
CREATE TABLE IF NOT EXISTS `truck` (
  `id` int NOT NULL AUTO_INCREMENT,
  `truck_id` varchar(45) DEFAULT NULL,
  `truck_name` varchar(45) DEFAULT NULL,
  `transportation_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_truck_transportation1_idx` (`transportation_id`),
  CONSTRAINT `fk_truck_transportation1` FOREIGN KEY (`transportation_id`) REFERENCES `transportation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table logistic_management_system_2.truck: ~0 rows (approximately)

-- Dumping structure for table logistic_management_system_2.users
CREATE TABLE IF NOT EXISTS `users` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table logistic_management_system_2.users: ~0 rows (approximately)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
