-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ssafit
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssafit
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafit` DEFAULT CHARACTER SET utf8 ;
USE `ssafit` ;

-- -----------------------------------------------------
-- Table `ssafit`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`board` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `content` VARCHAR(2000) NOT NULL,
  `user_id` INT NOT NULL,
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `view_cnt` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `BOARD_USER_FK_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `BOARD_USER_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`follow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`follow` (
  `follow_id` VARCHAR(50) NOT NULL,
  `user_id` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`follow_id`, `user_id`),
  INDEX `follow_follow_id_FK_idx` (`follow_id` ASC) VISIBLE,
  INDEX `follow_user_id_FK_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `follow_follow_id_FK`
    FOREIGN KEY (`follow_id`)
    REFERENCES `ssafit`.`user` (`username`)
    ON DELETE CASCADE,
  CONSTRAINT `follow_user_id_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit`.`user` (`email`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`part`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`part` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`video`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`video` (
  `id` VARCHAR(30) NOT NULL,
  `title` VARCHAR(200) NOT NULL,
  `channel_name` VARCHAR(50) NOT NULL,
  `url` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`like_video`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`like_video` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `video_id` VARCHAR(30) NOT NULL,
  `user_id` INT NOT NULL,
  `part_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `like_video_video_id_FK_idx` (`video_id` ASC) VISIBLE,
  INDEX `USER_VIDEO_FK_idx` (`user_id` ASC) VISIBLE,
  INDEX `PART_LIKE_FK` (`part_id` ASC) VISIBLE,
  CONSTRAINT `PART_LIKE_FK`
    FOREIGN KEY (`part_id`)
    REFERENCES `ssafit`.`part` (`id`),
  CONSTRAINT `USER_LIKE_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit`.`user` (`id`),
  CONSTRAINT `VIDEO_LIKE_FK`
    FOREIGN KEY (`video_id`)
    REFERENCES `ssafit`.`video` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(2000) NOT NULL,
  `user_id` INT NOT NULL,
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `video_id` VARCHAR(30) NOT NULL,
  `board_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `REVIEW_VIDEO_idx` (`video_id` ASC) VISIBLE,
  INDEX `REVIEW_USER_idx` (`user_id` ASC) VISIBLE,
  INDEX `REVIEW_BOARD_idx` (`board_id` ASC) VISIBLE,
  CONSTRAINT `REVIEW_USER_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit`.`user` (`id`),
  CONSTRAINT `REVIEW_VIDEO_FK`
    FOREIGN KEY (`video_id`)
    REFERENCES `ssafit`.`video` (`id`),
  CONSTRAINT `REVIEW_BOARD_FK`
    FOREIGN KEY (`board_id`)
    REFERENCES `ssafit`.`board` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
