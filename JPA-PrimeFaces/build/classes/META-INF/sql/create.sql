-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema workflow
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema workflow
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `workflow` DEFAULT CHARACTER SET utf8 ;
USE `workflow` ;

-- -----------------------------------------------------
-- Table `workflow`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workflow`.`person` ;

CREATE TABLE IF NOT EXISTS `workflow`.`person` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(255) NULL,
  `DISPLAY_COLOR` VARCHAR(6) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `workflow`.`userroles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workflow`.`userroles` ;

CREATE TABLE IF NOT EXISTS `workflow`.`userroles` (
  `username` VARCHAR(255) NULL DEFAULT NULL,
  `role` VARCHAR(32) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `workflow`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workflow`.`users` ;

CREATE TABLE IF NOT EXISTS `workflow`.`users` (
  `username` VARCHAR(255) NOT NULL,
  `passwd` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `workflow`.`worksheet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workflow`.`worksheet` ;

CREATE TABLE IF NOT EXISTS `workflow`.`worksheet` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` VARCHAR(255) NULL DEFAULT NULL,
  `START_DATE` DATETIME NULL,
  `END_DATE` DATETIME NULL,
  `DUE_DATE` DATETIME NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `workflow`.`WORKSHEET_PERSON`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workflow`.`WORKSHEET_PERSON` ;

CREATE TABLE IF NOT EXISTS `workflow`.`WORKSHEET_PERSON` (
  `WORKSHEET_ID` INT(11) NOT NULL,
  `PERSON_ID` INT(11) NOT NULL,
  PRIMARY KEY (`WORKSHEET_ID`, `PERSON_ID`),
  INDEX `fk_worksheet_has_person_person1_idx` (`PERSON_ID` ASC),
  CONSTRAINT `fk_worksheet_has_person_worksheet`
    FOREIGN KEY (`WORKSHEET_ID`)
    REFERENCES `workflow`.`worksheet` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_worksheet_has_person_person1`
    FOREIGN KEY (`PERSON_ID`)
    REFERENCES `workflow`.`person` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `workflow`.`workhours`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workflow`.`workhours` ;

CREATE TABLE IF NOT EXISTS `workflow`.`workhours` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `WORKSHEET_PERSON_WORKSHEET_ID` INT(11) NOT NULL,
  `WORKSHEET_PERSON_PERSON_ID` INT(11) NOT NULL,
  `DESCRIPTION` VARCHAR(255) NULL,
  `START_DATE` DATETIME NULL,
  `END_DATE` DATETIME NULL,
  PRIMARY KEY (`id`, `WORKSHEET_PERSON_WORKSHEET_ID`, `WORKSHEET_PERSON_PERSON_ID`),
  INDEX `fk_workhours_WORKSHEET_PERSON1_idx` (`WORKSHEET_PERSON_WORKSHEET_ID` ASC, `WORKSHEET_PERSON_PERSON_ID` ASC),
  CONSTRAINT `fk_workhours_WORKSHEET_PERSON1`
    FOREIGN KEY (`WORKSHEET_PERSON_WORKSHEET_ID` , `WORKSHEET_PERSON_PERSON_ID`)
    REFERENCES `workflow`.`WORKSHEET_PERSON` (`WORKSHEET_ID` , `PERSON_ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
