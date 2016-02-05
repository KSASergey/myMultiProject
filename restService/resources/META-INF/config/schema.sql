DROP SCHEMA if exists `mydbtest`;
CREATE SCHEMA `mydbtest` DEFAULT CHARACTER SET utf8 ;

DROP TABLE if exists `mydbtest`.`employee` ;
DROP TABLE if exists `mydbtest`.`department` ;

COMMIT;

CREATE TABLE `mydbtest`.`department` (
    `ID` INT NOT NULL AUTO_INCREMENT,
    `DepartmentName` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`ID`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

COMMIT;

DROP TABLE if exists `mydbtest`.`employee` ;

COMMIT;

CREATE TABLE `mydbtest`.`employee` (
    `ID` INT NOT NULL AUTO_INCREMENT,
    `ID_Department` INT NOT NULL,
    `FullName` VARCHAR(45) NOT NULL,
    `BirthDate` DATE NOT NULL,
    `Salary` INT NULL,
    PRIMARY KEY (`ID`),
    INDEX `ID_Department_idx` (`ID_Department` ASC),
    CONSTRAINT `ID_Department`
    FOREIGN KEY (`ID_Department`)
    REFERENCES `mydbtest`.`department` (`ID`)
        ON DELETE CASCADE
        ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

COMMIT;