CREATE SCHEMA IF NOT EXISTS `catalog` DEFAULT CHARACTER SET utf8 ;
USE `catalog` ;

-- -----------------------------------------------------
-- Table `catalog`.`table1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalog`.`table1` (
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `catalog`.`instruments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalog`.`instruments` (
  `instrument_id` INT NOT NULL AUTO_INCREMENT,
  `catalog_number` VARCHAR(50) NOT NULL,
  `brand` VARCHAR(100) NULL,
  `model` VARCHAR(100) NULL,
  `instrument_type` VARCHAR(100) NULL,
  `serial_number` VARCHAR(100) NULL,
  `location` VARCHAR(100) NULL,
  `comment` VARCHAR(150) NULL,
  `rented` TINYINT NULL,
  `high_value` TINYINT NULL,
  `waste` TINYINT NULL,
  `last_showing_date` DATE NULL,
  PRIMARY KEY (`instrument_id`),
  UNIQUE INDEX `catalog_number_UNIQUE` (`catalog_number` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `catalog`.`students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalog`.`students` (
  `student_id` INT NOT NULL AUTO_INCREMENT,
  `educational_id` BIGINT(11) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `birth_name` VARCHAR(100) NOT NULL,
  `mothers_name` VARCHAR(100) NOT NULL,
  `birth_place` VARCHAR(100) NOT NULL,
  `birth_date` VARCHAR(100) NOT NULL,
  `gender` VARCHAR(50) NULL,
  `active` TINYINT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE INDEX `educational_id_UNIQUE` (`educational_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `catalog`.`teachers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalog`.`teachers` (
  `teacher_id` INT NOT NULL AUTO_INCREMENT,
  `educational_id` BIGINT(11) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `active` TINYINT NULL,
  PRIMARY KEY (`teacher_id`),
  UNIQUE INDEX `educational_id_UNIQUE` (`educational_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `catalog`.`rental`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalog`.`rental` (
  `rental_id` INT NOT NULL AUTO_INCREMENT,
  `start_of_rental` DATE NOT NULL,
  `end_of_rental` DATE NULL,
  `instrument_id` INT NOT NULL,
  `student_id` INT NULL,
  `teacher_id` INT NULL,
  PRIMARY KEY (`rental_id`),
  INDEX `fk_rental_instruments_idx` (`instrument_id` ASC),
  INDEX `fk_rental_students1_idx` (`student_id` ASC),
  INDEX `fk_rental_teachers1_idx` (`teacher_id` ASC),
  CONSTRAINT `fk_rental_instruments`
    FOREIGN KEY (`instrument_id`)
    REFERENCES `catalog`.`instruments` (`instrument_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rental_students1`
    FOREIGN KEY (`student_id`)
    REFERENCES `catalog`.`students` (`student_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rental_teachers1`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `catalog`.`teachers` (`teacher_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;