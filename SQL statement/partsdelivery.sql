CREATE TABLE `partsdelivery` (
  `id` int NOT NULL AUTO_INCREMENT,
  `part_number` varchar(255) NOT NULL,
  `delivery_quantity` int DEFAULT NULL,
  `inventory_quantity` int NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `part_number` (`part_number`) USING BTREE,
  KEY `inventory_quantity` (`inventory_quantity`) USING BTREE,
  KEY `price` (`price`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `partsdelivery` (`id`, `part_number`, `delivery_quantity`, `inventory_quantity`, `price`) VALUES (15, '发动机01', 10, 90, 8001);
INSERT INTO `partsdelivery` (`id`, `part_number`, `delivery_quantity`, `inventory_quantity`, `price`) VALUES (16, '挡风玻璃', 1, 301, 402);
INSERT INTO `partsdelivery` (`id`, `part_number`, `delivery_quantity`, `inventory_quantity`, `price`) VALUES (17, '远光灯-81', 3, 90, 100);
