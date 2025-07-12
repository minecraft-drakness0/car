CREATE TABLE `submenu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `mid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `submenu_ibfk_1` (`mid`),
  CONSTRAINT `submenu_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `mainmenu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



INSERT INTO `submenu` (`id`, `title`, `path`, `mid`) VALUES (101, '用户列表', '/user', 100);
INSERT INTO `submenu` (`id`, `title`, `path`, `mid`) VALUES (102, '员工信息模块', '/staff', 100);
INSERT INTO `submenu` (`id`, `title`, `path`, `mid`) VALUES (103, '零件入库模块', '/put', 100);
INSERT INTO `submenu` (`id`, `title`, `path`, `mid`) VALUES (104, '零件出库模块', '/out', 100);
INSERT INTO `submenu` (`id`, `title`, `path`, `mid`) VALUES (201, '汽车登记模块', '/register', 200);
INSERT INTO `submenu` (`id`, `title`, `path`, `mid`) VALUES (202, '汽车修理模块', '/repair', 200);
