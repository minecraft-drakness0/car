CREATE TABLE `mainmenu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `mainmenu` (`id`, `title`, `path`) VALUES (100, '权限管理', '/admin');
INSERT INTO `mainmenu` (`id`, `title`, `path`) VALUES (200, '汽修平台', '/use');
