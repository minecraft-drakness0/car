CREATE TABLE `employeeinformation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` varchar(255) NOT NULL,
  `employee_name` varchar(255) NOT NULL,
  `employee_salary` int NOT NULL,
  PRIMARY KEY (`id`,`employee_id`) USING BTREE,
  KEY `employee_id` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `employeeinformation` (`id`, `employee_id`, `employee_name`, `employee_salary`) VALUES (1, '101', '张三', 3000);
INSERT INTO `employeeinformation` (`id`, `employee_id`, `employee_name`, `employee_salary`) VALUES (2, '110', '马三', 3100);
INSERT INTO `employeeinformation` (`id`, `employee_id`, `employee_name`, `employee_salary`) VALUES (3, '245', '泡泡', 2700);
INSERT INTO `employeeinformation` (`id`, `employee_id`, `employee_name`, `employee_salary`) VALUES (4, '126', '呜啦啦', 3511);
