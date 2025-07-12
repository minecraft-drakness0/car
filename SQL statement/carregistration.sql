CREATE TABLE `carregistration` (
  `id` int NOT NULL AUTO_INCREMENT,
  `driver_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `car_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `car_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `reg_time` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`car_number`) USING BTREE,
  KEY `car_number` (`car_number`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `carregistration` (`id`, `driver_name`, `car_number`, `car_model`, `reg_time`) VALUES (1, '苏童', '辽B-AAAAA', '三菱', '2021/06/16');
INSERT INTO `carregistration` (`id`, `driver_name`, `car_number`, `car_model`, `reg_time`) VALUES (3, '大明', '新A-87432', '劳斯莱斯', '2019/09/30');
INSERT INTO `carregistration` (`id`, `driver_name`, `car_number`, `car_model`, `reg_time`) VALUES (4, '许凯达', '京A-90674', '法拉利', '2020/3/5');
