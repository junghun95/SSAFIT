-- MySQL Workbench Forward Engineering
DROP SCHEMA IF EXISTS `ssafit`;
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
  `view_cnt` INT NOT NULL default 0,
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `board_user_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `board_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `ssafit`.`partboard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`partboard` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `board_id` INT NOT NULL,
  `part_id` int not null,
  PRIMARY KEY (`id`),
  INDEX `partboard_board_fk_idx` (`board_id` ASC) VISIBLE,
  INDEX `partboard_part_FK` (`part_id` ASC) VISIBLE,
  CONSTRAINT `partboard_part_FK`
    FOREIGN KEY (`part_id`)
    REFERENCES `ssafit`.`part` (`id`),
  CONSTRAINT `partboard_board_fk`
    FOREIGN KEY (`board_id`)
    REFERENCES `ssafit`.`board` (`id`)
    on delete cascade
    on update cascade)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `ssafit`.`follow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`follow` (
  `from_username` VARCHAR(50) NOT NULL,
  `to_email` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`from_username`, `to_email`),
  INDEX `follow_from_username_FK_idx` (`from_username` ASC) VISIBLE,
  INDEX `follow_to_email_FK_idx` (`to_email` ASC) VISIBLE,
  CONSTRAINT `follow_from_username_FK`
    FOREIGN KEY (`from_username`)
    REFERENCES `ssafit`.`user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `follow_to_email_FK`
    FOREIGN KEY (`to_email`)
    REFERENCES `ssafit`.`user` (`email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
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
  `video_id` VARCHAR(30),
  `board_id` INT,
  PRIMARY KEY (`id`),
  INDEX `REVIEW_VIDEO_idx` (`video_id` ASC) VISIBLE,
  INDEX `REVIEW_USER_idx` (`user_id` ASC) VISIBLE,
  INDEX `REVIEW_BOARD_FK_idx` (`board_id` ASC) VISIBLE,
  CONSTRAINT `REVIEW_USER_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit`.`user` (`id`)
    on delete cascade
    on update cascade,
  CONSTRAINT `REVIEW_VIDEO_FK`
    FOREIGN KEY (`video_id`)
    REFERENCES `ssafit`.`video` (`id`)
    on delete cascade
    on update cascade,
  CONSTRAINT `REVIEW_BOARD_FK`
    FOREIGN KEY (`board_id`)
    REFERENCES `ssafit`.`board` (`id`)
    on delete cascade
    on update cascade)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
