CREATE TABLE `easyuser` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `state` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `easyuser` (`id`, `username`, `password`, `email`, `role`, `state`) VALUES (1, 'admin', '123456', '123@qq.com', '超级管理员', 1);
INSERT INTO `easyuser` (`id`, `username`, `password`, `email`, `role`, `state`) VALUES (2, 'subati', '000000', '456@qq.com', '普通管理员', 0);
INSERT INTO `easyuser` (`id`, `username`, `password`, `email`, `role`, `state`) VALUES (3, 'admin001', '654321', '432342@qq.com', '普通用户', 1);
INSERT INTO `easyuser` (`id`, `username`, `password`, `email`, `role`, `state`) VALUES (5, '21313213', '12313154', '3123123@', '普通用户', 0);
