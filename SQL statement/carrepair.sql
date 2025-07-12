CREATE TABLE `carrepair` (
  `id` int NOT NULL AUTO_INCREMENT,
  `car_number` varchar(255) NOT NULL,
  `employee_id` varchar(255) NOT NULL,
  `repairlist_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `repair_items` varchar(255) NOT NULL,
  `repair_time` int NOT NULL,
  PRIMARY KEY (`id`,`repairlist_id`) USING BTREE,
  KEY `car_num` (`car_number`),
  KEY `fk_c_e` (`employee_id`),
  CONSTRAINT `fk_c_e` FOREIGN KEY (`employee_id`) REFERENCES `employeeinformation` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



INSERT INTO `carrepair` (`id`, `car_number`, `employee_id`, `repairlist_id`, `repair_items`, `repair_time`) VALUES (1, '辽B-AAAAA', '101', '001', 'xxx1', 3);
INSERT INTO `carrepair` (`id`, `car_number`, `employee_id`, `repairlist_id`, `repair_items`, `repair_time`) VALUES (2, '新A-87432', '110', '002', 'xxx2', 6);
