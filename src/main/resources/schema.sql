-- -----------------------------------------------------
-- Table `ormlearn`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ormlearn`.`country` ( 
	co_code varchar(2) PRIMARY KEY, 
	co_name varchar(50)
	);

-- -----------------------------------------------------
-- Table `ormlearn`.`stock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ormlearn`.`stock` (
`st_id` INT NOT NULL AUTO_INCREMENT,
`st_code` varchar(10),
`st_date` date,
`st_open` numeric(10,2),
`st_close` numeric(10,2),
`st_volume` numeric,
PRIMARY KEY (`st_id`)
);


-- -----------------------------------------------------
-- Table `ormlearn`.`department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ormlearn`.`department` ;

CREATE TABLE IF NOT EXISTS `ormlearn`.`department` (
  `dp_id` INT NOT NULL AUTO_INCREMENT,
  `dp_name` VARCHAR(45) NULL,
  PRIMARY KEY (`dp_id`),
  UNIQUE INDEX `name_UNIQUE` (`dp_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ormlearn`.`employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ormlearn`.`employee` ;

CREATE TABLE IF NOT EXISTS `ormlearn`.`employee` (
  `em_id` INT NOT NULL AUTO_INCREMENT,
  `em_name` VARCHAR(45) NULL,
  `em_salary` DOUBLE NULL,
  `em_permanent` TINYINT(1) NULL,
  `em_date_of_birth` DATE NULL,
  `em_dp_id` INT NULL,
  PRIMARY KEY (`em_id`),
  INDEX `em_dp_fk_idx` (`em_dp_id` ASC),
  CONSTRAINT `em_dp_fk`
    FOREIGN KEY (`em_dp_id`)
    REFERENCES `ormlearn`.`department` (`dp_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ormlearn`.`skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ormlearn`.`skill` ;

CREATE TABLE IF NOT EXISTS `ormlearn`.`skill` (
  `sk_id` INT NOT NULL AUTO_INCREMENT,
  `sk_name` VARCHAR(45) NULL,
  PRIMARY KEY (`sk_id`),
  UNIQUE INDEX `name_UNIQUE` (`sk_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ormlearn`.`employee_skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ormlearn`.`employee_skill` ;

CREATE TABLE IF NOT EXISTS `ormlearn`.`employee_skill` (
  `es_id` INT NOT NULL AUTO_INCREMENT,
  `es_em_id` INT NOT NULL,
  `es_sk_id` INT NOT NULL,
  PRIMARY KEY (`es_id`),
  INDEX `es_sk_fk_idx` (`es_sk_id` ASC),
  CONSTRAINT `es_em_fk`
    FOREIGN KEY (`es_em_id`)
    REFERENCES `ormlearn`.`employee` (`em_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `es_sk_fk`
    FOREIGN KEY (`es_sk_id`)
    REFERENCES `ormlearn`.`skill` (`sk_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `ormlearn`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ormlearn`.`user` (
  `us_id` INT NOT NULL AUTO_INCREMENT,
  `us_name` VARCHAR(45) NOT NULL,
  `us_email` VARCHAR(45) NULL,
  PRIMARY KEY (`us_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ormlearn`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ormlearn`.`question` (
  `qt_id` INT NOT NULL AUTO_INCREMENT,
  `qt_text` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`qt_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ormlearn`.`options`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ormlearn`.`options` (
  `op_id` INT NOT NULL AUTO_INCREMENT,
  `op_qt_id` INT NULL,
  `op_score` DOUBLE NULL,
  `op_text` VARCHAR(100) NULL,
  PRIMARY KEY (`op_id`),
  INDEX `op_qt_id_fkey_idx` (`op_qt_id` ASC) ,
  CONSTRAINT `op_qt_id_fkey`
    FOREIGN KEY (`op_qt_id`)
    REFERENCES `ormlearn`.`question` (`qt_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ormlearn`.`attempt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ormlearn`.`attempt` (
  `at_id` INT NOT NULL AUTO_INCREMENT,
  `at_date` DATE NULL,
  `at_us_id` INT NULL,
  `at_score` DOUBLE NULL,
  PRIMARY KEY (`at_id`),
  INDEX `at_us_id_fkey_idx` (`at_us_id` ASC),
  CONSTRAINT `at_us_id_fkey`
    FOREIGN KEY (`at_us_id`)
    REFERENCES `ormlearn`.`user` (`us_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ormlearn`.`attempt_question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ormlearn`.`attempt_question` (
  `aq_id` INT NOT NULL AUTO_INCREMENT,
  `aq_at_id` INT NULL,
  `aq_qt_id` INT NULL,
  PRIMARY KEY (`aq_id`),
  INDEX `qz_at_id_fkey_idx` (`aq_at_id` ASC),
  INDEX `ad_qt_id_idx` (`aq_qt_id` ASC),
  CONSTRAINT `aq_at_id_fk`
    FOREIGN KEY (`aq_at_id`)
    REFERENCES `ormlearn`.`attempt` (`at_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `aq_qt_id_fk`
    FOREIGN KEY (`aq_qt_id`)
    REFERENCES `ormlearn`.`question` (`qt_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ormlearn`.`attempt_option`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ormlearn`.`attempt_option` (
  `ao_id` INT NOT NULL AUTO_INCREMENT,
  `ao_op_id` INT NULL,
  `ao_aq_id` INT NULL,
  `ao_selected` BIT NULL,
  PRIMARY KEY (`ao_id`),
  INDEX `ao_aq_fk_idx` (`ao_aq_id` ASC) ,
  INDEX `ao_op_fk_idx` (`ao_op_id` ASC),
  CONSTRAINT `ao_aq_fk`
    FOREIGN KEY (`ao_aq_id`)
    REFERENCES `ormlearn`.`attempt_question` (`aq_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ao_op_fk`
    FOREIGN KEY (`ao_op_id`)
    REFERENCES `ormlearn`.`options` (`op_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
