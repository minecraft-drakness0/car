CREATE TABLE `partswarehousing` (
  `id` int NOT NULL AUTO_INCREMENT,
  `part_number` varchar(255) NOT NULL,
  `storage_quantity` int DEFAULT NULL,
  `inventory_quantity` int NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `part_number` (`part_number`),
  KEY `inventory_quantity` (`inventory_quantity`),
  KEY `price` (`price`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE DEFINER=`root`@`localhost` TRIGGER `pw_ins` BEFORE INSERT ON `partswarehousing` FOR EACH ROW BEGIN
	INSERT INTO partsdelivery(part_number,inventory_quantity,price) VALUE(new.part_number,new.inventory_quantity,new.price);
END;

CREATE DEFINER=`root`@`localhost` TRIGGER `pw_del` AFTER DELETE ON `partswarehousing` FOR EACH ROW BEGIN
	DELETE FROM partsdelivery WHERE part_number = old.part_number;
END;

CREATE DEFINER=`root`@`localhost` TRIGGER `pw_upd` BEFORE UPDATE ON `partswarehousing` FOR EACH ROW BEGIN
	UPDATE partsdelivery SET part_number = new.part_number ,
  inventory_quantity = new.inventory_quantity , price = new.price WHERE part_number = new.part_number;
END;


INSERT INTO `partswarehousing` (`id`, `part_number`, `storage_quantity`, `inventory_quantity`, `price`) VALUES (7, '发动机01', 0, 90, 8001);
INSERT INTO `partswarehousing` (`id`, `part_number`, `storage_quantity`, `inventory_quantity`, `price`) VALUES (8, '挡风玻璃', 0, 301, 402);
INSERT INTO `partswarehousing` (`id`, `part_number`, `storage_quantity`, `inventory_quantity`, `price`) VALUES (9, '远光灯-81', 0, 90, 100);
