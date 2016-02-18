DROP TABLE if exists `employee` ;
DROP TABLE if exists `department` ;

CREATE TABLE `department` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `DepartmentName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`));

CREATE TABLE `employee` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `DepartmentID` INT NOT NULL,
  `FullName` VARCHAR(45) NOT NULL,
  `BirthDate` DATE NOT NULL,
  `Salary` INT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `DepartmentID`
  FOREIGN KEY (`DepartmentID`)
  REFERENCES `department` (`ID`)
  ON DELETE CASCADE
  ON UPDATE CASCADE);