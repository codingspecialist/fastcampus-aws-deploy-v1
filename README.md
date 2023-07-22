```sql
CREATE SCHEMA IF NOT EXISTS `metadb` DEFAULT CHARACTER SET utf8mb4;

USE `metadb`;

CREATE TABLE `user_tb` (
   `id` int AUTO_INCREMENT,
   `email` varchar(100) NOT NULL,
   `password` varchar(256) NOT NULL,
   `username` varchar(45) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `product_tb` (
  `id` int AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```

```sql
INSERT INTO user_tb (`id`,`email`,`password`,`username`) VALUES ('1', 'ssar@nate.com', 'meta1234!', 'ssar');
INSERT INTO product_tb (`id`,`product_name`,`image`, `price`) VALUES ('1', '기본에 슬라이딩 지퍼백 크리스마스/플라워에디션 에디션 외 주방용품 특가전', '/images/1.jpg', '1000');
INSERT INTO product_tb (`id`,`product_name`,`image`, `price`) VALUES ('2', '[황금약단밤 골드]2022년산 햇밤 칼집밤700g외/군밤용/생율', '/images/2.jpg', '2000');
INSERT INTO product_tb (`id`,`product_name`,`image`, `price`) VALUES ('3', '삼성전자 JBL JR310 외 어린이용/성인용 헤드셋 3종!', '/images/3.jpg', '30000');
```

