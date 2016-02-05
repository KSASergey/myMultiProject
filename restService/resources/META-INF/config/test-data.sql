INSERT INTO `mydbtest`.`department` (`ID`, `DepartmentName`) VALUES ('1', 'Development');
INSERT INTO `mydbtest`.`department` (`ID`, `DepartmentName`) VALUES ('2', 'Communication');
INSERT INTO `mydbtest`.`department` (`ID`, `DepartmentName`) VALUES ('3', 'Information');
INSERT INTO `mydbtest`.`department` (`ID`, `DepartmentName`) VALUES ('4', 'Asou');
INSERT INTO `mydbtest`.`department` (`ID`, `DepartmentName`) VALUES ('5', 'Testing');
INSERT INTO `mydbtest`.`department` (`ID`, `DepartmentName`) VALUES ('6', 'Central department');

COMMIT;

INSERT INTO `mydbtest`.`employee` (`ID`, `ID_Department`, `FullName`, `BirthDate`, `Salary`) VALUES ('1', '1', 'Kiril', '1999-08-11', '1992');
INSERT INTO `mydbtest`.`employee` (`ID`, `ID_Department`, `FullName`, `BirthDate`, `Salary`) VALUES ('2', '1', 'Ivan', '1981-06-01', '2000');
INSERT INTO `mydbtest`.`employee` (`ID`, `ID_Department`, `FullName`, `BirthDate`, `Salary`) VALUES ('3', '2', 'Sergey', '1989-12-28', '858');
INSERT INTO `mydbtest`.`employee` (`ID`, `ID_Department`, `FullName`, `BirthDate`, `Salary`) VALUES ('4', '3', 'Oleg', '1995-05-10', '1234');
INSERT INTO `mydbtest`.`employee` (`ID`, `ID_Department`, `FullName`, `BirthDate`, `Salary`) VALUES ('5', '2', 'Viktor', '1985-08-21', '900');
INSERT INTO `mydbtest`.`employee` (`ID`, `ID_Department`, `FullName`, `BirthDate`, `Salary`) VALUES ('6', '2', 'Drozd', '1988-01-01', '560');
INSERT INTO `mydbtest`.`employee` (`ID`, `ID_Department`, `FullName`, `BirthDate`, `Salary`) VALUES ('7', '4', 'Alex', '1977-12-28', '1100');
INSERT INTO `mydbtest`.`employee` (`ID`, `ID_Department`, `FullName`, `BirthDate`, `Salary`) VALUES ('8', '6', 'Kiril', '1985-08-21', '455');
INSERT INTO `mydbtest`.`employee` (`ID`, `ID_Department`, `FullName`, `BirthDate`, `Salary`) VALUES ('9', '6', 'Nikolai', '1985-08-23', '789');
INSERT INTO `mydbtest`.`employee` (`ID`, `ID_Department`, `FullName`, `BirthDate`, `Salary`) VALUES ('10', '4', 'Max', '1994-07-20', '990');
INSERT INTO `mydbtest`.`employee` (`ID`, `ID_Department`, `FullName`, `BirthDate`, `Salary`) VALUES ('11', '1', 'Makar', '1991-05-21', '2500');
INSERT INTO `mydbtest`.`employee` (`ID`, `ID_Department`, `FullName`, `BirthDate`, `Salary`) VALUES ('12', '2', 'Klarc', '1983-02-21', '1850');

COMMIT;