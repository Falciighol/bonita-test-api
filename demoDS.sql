CREATE DATABASE demoDS;
USE demoDS;

CREATE TABLE `book` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO book VALUES (485418, "El hombre duplicado");
INSERT INTO book VALUES (716195, "Ensayo sobre la ceguera");
INSERT INTO book VALUES (983354, "All you zombies");