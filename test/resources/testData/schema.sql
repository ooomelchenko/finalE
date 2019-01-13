-- -----------------------------------------------------
-- Schema test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test` DEFAULT CHARACTER SET utf8 ;
USE `test` ;

-- -----------------------------------------------------
-- Table `test`.`routes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`routes` (
  `id_route` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `route_start` VARCHAR(45) NULL DEFAULT NULL,
  `route_end` VARCHAR(45) NULL DEFAULT NULL,
  `distance_km` INT(11) NULL DEFAULT NULL,
  `route_start_ua` VARCHAR(45) NULL DEFAULT NULL,
  `route_end_ua` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_route`),
  UNIQUE INDEX `id_UNIQUE` (`id_route` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `test`.`routes` TEST CONTENT
-- -----------------------------------------------------
/*INSERT INTO `test`.`routes`(`route_start`, `route_end`, `distance_km`)
VALUES ("Kyiv", "Sumy", 100500);*/
INSERT INTO `routes` VALUES (1,'Odessa','Kyiv',500,'Одеса','Київ'),(2,'Lviv','Kyiv',550,'Львів','Київ'),(3,'Odessa','Kharkiv',800,'Одеса','Харків'),(4,'Kyiv','Vinnitsya',300,'Київ','Вінниця'),(5,'Kyiv','Zhitomir',150,'Київ','Житомир'),(6,'Lviv','Dnipro',700,'Львів','Дніпро'),(7,'Mykolaiv','Chernigiv',650,'Миколаїв','Чернігів'),(8,'Chernivci','Lviv',400,'Чернівці','Львів'),(9,'Zaporighya','Kyiv',370,'Запоріжжя','Київ'),(10,'Kherson','Khmelnitskyi',590,'Херсон','Хмельницький'),(11,'Kyiv','Paris',1500,'Київ','Париж'),(12,'Kyiv','Lutsk',670,'Київ','Луцьк'),(13,'Kyiv','Cherkasy',150,'Київ','Черкаси'),(14,'Kyiv','Ternopil',700,'Київ','Тернопіль'),(15,'Kyiv','Poltava',350,'Київ','Полтава'),(16,'Kyiv','Sumy',330,'Київ','Суми'),(17,'Kyiv','Uzhgorod',800,'Київ','Ужгород'),(18,'Kyiv','Ivano-Frankivsk',670,'Київ','Івано-Франківськ'),(19,'Lutsk','Kyiv',670,'Луцьк','Київ'),(20,'Cherkasy','Kyiv',150,'Черкаси','Київ'),(21,'Ternopil','Kyiv',700,'Тернопіль','Київ'),(22,'Poltava','Kyiv',350,'Полтава','Київ'),(23,'Sumy','Kyiv',330,'Суми','Київ'),(24,'Uzhgorod','Kyiv',800,'Ужгород','Київ'),(25,'Ivano-Frankivsk','Kyiv',670,'Івано-Франківськ','Київ');

-- -----------------------------------------------------
-- Table `test`.`tariffs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`tariffs` (
  `id_tariff` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `cost_per_km` BIGINT(19) NULL DEFAULT NULL,
  `cost_per_kg` BIGINT(19) NULL DEFAULT NULL,
  `pace_day_km` INT(11) NULL DEFAULT NULL,
  `name_ua` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_tariff`),
  UNIQUE INDEX `id_UNIQUE` (`id_tariff` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

INSERT INTO `tariffs` VALUES (1,'standart',1000,1000,150,'стандарт'),(2,'fast',150,1500,300,'швидкий'),(3,'express',220,2000,800,'експресс');
-- -----------------------------------------------------
-- Table `test`.`available_options`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`available_options` (
  `id_option` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `id_tariff` BIGINT(19) NULL DEFAULT NULL,
  `id_route` BIGINT(19) NULL DEFAULT NULL,
  `is_available` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_option`),
  UNIQUE INDEX `id_UNIQUE` (`id_option` ASC),
  INDEX `id_tariff_idx` (`id_tariff` ASC),
  INDEX `id_route_idx` (`id_route` ASC),
  CONSTRAINT `id_route`
    FOREIGN KEY (`id_route`)
    REFERENCES `test`.`routes` (`id_route`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_tariff`
    FOREIGN KEY (`id_tariff`)
    REFERENCES `test`.`tariffs` (`id_tariff`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

INSERT INTO `available_options` VALUES (1,1,1,1),(2,2,1,1),(3,3,1,1),(4,1,2,1),(5,2,2,0),(6,1,3,1),(9,2,3,1),(10,3,3,1),(11,3,2,1),(12,1,4,1),(13,2,4,1),(15,1,5,1),(16,2,5,1),(17,3,5,1),(18,1,6,0),(19,2,6,1),(20,3,6,0),(21,1,7,0),(22,2,7,1),(23,3,7,1),(24,1,8,0),(25,2,8,1),(26,3,8,0),(27,1,9,1),(28,2,9,0),(29,3,9,1),(30,1,10,1),(31,2,10,1),(32,3,10,0),(33,1,11,1),(34,2,11,0),(35,3,11,1),(36,1,12,1),(37,2,12,1),(38,3,12,0),(39,1,14,1),(40,2,14,1),(41,3,14,0),(42,1,16,0),(43,2,16,1),(44,3,16,1),(45,1,15,1),(46,2,15,0),(47,3,15,0),(48,1,17,1),(49,2,17,0),(50,3,17,1),(51,1,18,1),(52,2,18,1),(53,3,18,1),(54,1,19,1),(55,2,19,1),(56,3,19,1),(57,1,20,1),(58,2,20,1),(59,3,20,1),(60,1,21,1),(61,2,21,1),(62,3,21,1),(63,1,13,1),(64,2,13,1),(65,3,13,1),(66,1,25,1),(67,2,25,1),(68,3,25,0),(69,1,24,1),(70,2,24,1),(71,3,24,1),(72,1,23,1),(73,2,23,1),(74,3,23,1),(75,1,22,1),(76,2,22,1),(77,3,22,0),(78,3,4,1);
-- -----------------------------------------------------
-- Table `test`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`users` (
  `id_user` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `firstname` VARCHAR(90) NULL DEFAULT NULL,
  `lastname` VARCHAR(90) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `role` VARCHAR(45) NOT NULL,
  `account_sum` BIGINT(19) NULL DEFAULT '0',
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `id_UNIQUE` (`id_user` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

INSERT INTO `users` VALUES (1,'admin','admin','Oleksandr','Omelchenko','email@gmail.com','ADMIN',0),(2,'user','user','Ivanov','Ivan','ivan@gmail.com','USER',97125500),(3,'login','password','firstName','lastName','email@gmail.com','USER',215000),(8,'Gazille97','Gazille97@','Mykola','Filimonov','gmail@gmail.com','USER',0),(9,'ooomelchenko','A44n73','Oleksandr','Omelchenko','ooomelchenko@gmail.com','USER',7400),(14,'mykola','mykola1','Mmykola','Mmykola','filimonovkolya97@gmail.com','USER',4359985),(15,'vova','voav1','Vova','Vova','filimonovkolya97@gmail.com','ADMIN',0),(16,'Slavick','Slavick1','Slavick','Slavick','filimonovkolya97@gmail.com','USER',0),(17,'Slavickk','Slavickk1','Slavickk','Slavickk','weq@yuk.bet','ADMIN',0),(18,'mer','a44n73','Alexandr','Alexandro','malko@gmail.com','USER',0),(19,'merdok','a44n73gmail','Oleksa','Oliynyk','ret@gmail.com','USER',0),(20,'koller','a44n73','Koller','Yan','yan@gmail.com','USER',0),(21,'lor','a44n73','Lorik','Boroh','ukr@gmail.com','USER',0),(22,'olek','a44n73','Olek','Olek','Olek@gmail.com','USER',0),(28,'sstepanov','qwerty1','Stepan','Stepanov','sstepanov@gmail.com','USER',0);
-- -----------------------------------------------------
-- Table `test`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`orders` (
  `id_order` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `id_user` BIGINT(19) NULL DEFAULT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `weight` INT(11) NULL DEFAULT NULL,
  `arrival_date` DATE NULL DEFAULT NULL,
  `id_option` BIGINT(19) NULL DEFAULT NULL,
  PRIMARY KEY (`id_order`),
  INDEX `id_user_idx` (`id_user` ASC),
  INDEX `id_option_idx` (`id_order` ASC, `id_option` ASC),
  INDEX `id_available_option_idx` (`id_option` ASC),
  CONSTRAINT `id_available_option`
    FOREIGN KEY (`id_option`)
    REFERENCES `test`.`available_options` (`id_option`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `test`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

INSERT INTO `orders` VALUES (9,3,'MAIL',3000,'2018-12-27',1),(10,2,'MAIL',5000,'2018-12-27',4),(11,2,'MAIL',3000,'2018-12-29',6),(12,2,'MAIL',3000,'2018-12-27',4),(13,2,'MAIL',1000,'2018-12-25',3),(14,2,'MAIL',1000,'2018-12-27',1),(15,2,'MAIL',1000,'2018-12-27',1),(16,2,'MAIL',7000,'2018-12-27',1),(17,2,'MAIL',6000,'2018-12-27',4),(18,2,'MAIL',2000,'2018-12-27',1),(19,2,'MAIL',2000,'2018-12-27',1),(20,2,'MAIL',8000,'2018-12-27',1),(21,2,'MAIL',90000,'2018-12-27',1),(22,2,'MAIL',90000,'2018-12-25',3),(23,2,'MAIL',70000,'2018-12-27',4),(24,2,'MAIL',70000,'2018-12-27',4),(25,2,'MAIL',45000,'2018-12-27',4),(26,3,'PARCEL',10000,'2018-12-30',6),(27,3,'CARGO',450000,'2018-12-29',1),(28,2,'MAIL',10000,'2019-01-04',35),(29,2,'PARCEL',3000,'2019-01-05',25),(30,9,'PARCEL',5000,'2019-01-09',1),(31,9,'CARGO',240000,'2019-01-08',13),(32,9,'PARCEL',10000,'2019-01-09',19),(33,3,'PACKAGE',56000,'2019-01-08',15),(34,14,'PARCEL',5000,'2019-01-09',45),(35,14,'PARCEL',3000,'2019-01-09',35),(36,14,'CARGO',2000000,'2019-01-09',35),(37,14,'CARGO',2000000,'2019-01-09',35),(38,14,'CARGO',2000000,'2019-01-09',35),(39,14,'CARGO',2000000,'2019-01-09',35),(40,14,'CARGO',2000000,'2019-01-09',35),(41,14,'CARGO',2000000,'2019-01-09',35),(42,14,'CARGO',2000000,'2019-01-09',35),(43,14,'CARGO',2000000,'2019-01-09',35),(44,14,'CARGO',2000000,'2019-01-09',35),(45,14,'CARGO',2000000,'2019-01-09',35),(47,9,'PACKAGE',2000,'2019-01-11',19),(48,3,'CARGO',450000,'2019-01-11',28),(49,3,'MAIL',5000,'2019-01-11',63),(50,9,'MAIL',5000,'2019-01-14',30);
-- -----------------------------------------------------
-- Table `test`.`bills`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`bills` (
  `id_bill` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `is_paid` TINYINT(1) NOT NULL DEFAULT '0',
  `total` BIGINT(19) NULL DEFAULT NULL,
  `id_user` BIGINT(19) NULL DEFAULT NULL,
  `id_order` BIGINT(19) NULL DEFAULT NULL,
  PRIMARY KEY (`id_bill`),
  UNIQUE INDEX `id_UNIQUE` (`id_bill` ASC),
  INDEX `id_user_idx` (`id_user` ASC),
  INDEX `id_o_idx` (`id_order` ASC),
  CONSTRAINT `id_o`
    FOREIGN KEY (`id_order`)
    REFERENCES `test`.`orders` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_u`
    FOREIGN KEY (`id_user`)
    REFERENCES `test`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

INSERT INTO `bills` VALUES (1,1,50000,3,9),(2,1,62500,2,10),(3,1,84500,2,11),(4,1,59500,2,12),(5,1,102000,2,13),(6,0,51500,2,14),(7,1,51500,2,15),(8,1,60500,2,16),(9,1,64000,2,17),(10,1,53000,2,18),(11,0,53000,2,19),(12,1,62000,2,20),(13,1,185000,2,21),(14,0,280000,2,22),(15,1,160000,2,23),(16,1,160000,2,24),(17,1,122500,2,25),(18,1,95000,3,26),(19,1,725000,3,27),(20,0,225000,2,28),(21,1,66000,2,29),(22,1,57500,9,30),(23,1,525000,9,31),(24,0,125000,9,32),(25,1,99000,3,33),(26,1,42515,14,34),(27,1,307500,14,35),(28,1,5300000,14,36),(29,0,5300000,14,37),(30,0,5300000,14,38),(31,0,5300000,14,39),(32,0,5300000,14,40),(33,0,5300000,14,41),(34,0,5300000,14,42),(35,0,5300000,14,43),(36,0,5300000,14,44),(37,0,5300000,14,45),(39,1,109000,9,47),(40,1,955500,3,48),(41,1,22500,3,49),(42,0,64000,9,50);