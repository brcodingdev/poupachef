
CREATE TABLE `supplier` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `supplier` (id, name, created_at, updated_at) VALUES (1, 'Fornecedor Arroz', now(), now());
INSERT INTO `supplier` (id, name, created_at, updated_at) VALUES (2, 'Fornecedor Feijao', now(), now());
INSERT INTO `supplier` (id, name, created_at, updated_at) VALUES (3, 'Fornecedor Macarrao', now(), now());

CREATE TABLE `product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DOUBLE NOT NULL,
  `supplier_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `supplier_id_fk` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX name_idx ON product(name);
CREATE INDEX supplier_id_idx ON product(supplier_id);


CREATE TABLE `inventory` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `qty` INT UNSIGNED NOT NULL,
  `product_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX product_id_idx ON inventory(product_id);

CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `role` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX user_username_idx ON `user`(username);

INSERT INTO `user` (username, password, role) VALUES ('admin', '$2a$10$Szg4OyHX7UPzQY7JSVGIR.WYtk8KufgA2GgvoGzQxl.dCKqCsyLa.', 'ADMIN');

