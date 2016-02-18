Project myProject

is going to file webProject.war from Command line:
assemble draft: mvn package
assemble without tests: mvn -DskipTests=true package

ready file is located in the target

Received from war-File fill in tomcat on ways:
...\apache-tomcat\webapps\

after starting the tomcat home page is available at url:
http://localhost:8080/myProject/table_Department

Used  version:
apache-IOC-3.3.9
apache-tomcat-9.0.0.M1

==================================
For the operation of the application requires the MySql5
for the operation of the application you want to create the schema - "mydbtest"
(gives schema is not created automatically)

for access to the database is used the following settings:
Host: localhost
Database: mydbtest
User: root
Password: root

For tests used DB h2 In-memory database mode
when starting the application database is automatically populated
data are taken from:

For Project: src\Main\resources\test_data.sql

for tests: src\Main\resources\db-schema.sql

 src\Main\resources\db-test-data.sql

==================================

Web - application allows you to:

1. See the list of divisions and the average salary (will be automatically calculated) on the divisions (first payroll form);

2. The list of staff in the departments with the salary for each staff member and a search field to search for staff born in a certain date or in the period between the dates of the (second payroll form);

3. Change (add/edit/delete) the above data.