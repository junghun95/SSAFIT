-- MySQL Workbench Forward Engineering
drop database if exists ssafit;
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
  `password` VARCHAR(500) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `role` enum('admin','user') not null,
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `url` VARCHAR(100) NOT NULL,
  `par_id` INT NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `category_self_fk_idx` (`par_id` ASC) VISIBLE,
  CONSTRAINT `category_self_fk`
    FOREIGN KEY (`par_id`)
    REFERENCES `ssafit`.`category` (`id`))
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
  `view_cnt` INT NOT NULL DEFAULT '0',
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `delete_date` TIMESTAMP NULL DEFAULT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `board_user_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `board_category_fk_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `board_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `board_category_fk`
    FOREIGN KEY (`category_id`)
    REFERENCES `ssafit`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
-- Table `ssafit`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `file_name` VARCHAR(300) NOT NULL,
  `file_location` VARCHAR(300) NOT NULL,
  `user_id` INT NOT NULL,
  `board_image_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `image_user_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `image_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`imageboard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`imageboard` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `board_id` INT NOT NULL,
  `image_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `imageboard_board_fk_idx` (`board_id` ASC) VISIBLE,
  INDEX `imageboard_image_fk_idx` (`image_id` ASC) VISIBLE,
  CONSTRAINT `imageboard_board_fk`
    FOREIGN KEY (`board_id`)
    REFERENCES `ssafit`.`board` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `imageboard_image_fk`
    FOREIGN KEY (`image_id`)
    REFERENCES `ssafit`.`image` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`like` (
  `user_id` INT NOT NULL,
  `review_id` INT NOT NULL,
  `board_id` INT NOT NULL,
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`, `review_id`, `board_id`),
  INDEX `like_user_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `like_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`notify`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`notify` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `object_id` INT NOT NULL,
  `d_type` ENUM('review', 'board', 'admin') NULL DEFAULT NULL,
  `reg_date` timestamp not null default current_timestamp,
  `read_date` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `notify_user_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `notify_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`report`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`report` (
  `user_id` INT NOT NULL,
  `review_id` INT NOT NULL,
  `board_id` INT NOT NULL,
  `content` VARCHAR(2000) NOT NULL,
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`, `review_id`, `board_id`),
  INDEX `report_user_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `report_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit`.`user` (`id`))
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
  `video_id` VARCHAR(30) NULL DEFAULT NULL,
  `board_id` INT NULL DEFAULT NULL,
  `delete_date` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `REVIEW_USER_idx` (`user_id` ASC) VISIBLE,
  INDEX `REVIEW_BOARD_FK_idx` (`board_id` ASC) VISIBLE,
  CONSTRAINT `REVIEW_BOARD_FK`
    FOREIGN KEY (`board_id`)
    REFERENCES `ssafit`.`board` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `REVIEW_USER_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`tag` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`tagboard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`tagboard` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `board_id` INT NOT NULL,
  `tag_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `tagtboard_board_fk_idx` (`board_id` ASC) VISIBLE,
  INDEX `tagboard_tag_FK` (`tag_id` ASC) VISIBLE,
  CONSTRAINT `tagboard_board_fk`
    FOREIGN KEY (`board_id`)
    REFERENCES `ssafit`.`board` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `tagboard_tag_FK`
    FOREIGN KEY (`tag_id`)
    REFERENCES `ssafit`.`tag` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`zzim`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`zzim` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `video_id` VARCHAR(30) NOT NULL,
  `user_id` INT NOT NULL,
  `part_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `zzim_user_FK_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `zzim_user_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `ssafit`.`dm`
-- -----------------------------------------------------
create table `ssafit`.`dm` (
	`id` INT NOT NULL AUTO_INCREMENT,
    primary key (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
