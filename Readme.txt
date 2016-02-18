Project myMultiProject, the project consists of two modules 'restService' and 'webClient'
restService - the server part
webClient - the client part

is going to two file restService.war and webClient.war from Command line:
assemble draft: mvn package
assemble without tests: mvn -DskipTests=true package
War files are collected in the folder 'out' the root directory project

For the successful implementation of the test the client part of the need to run the server part of the

Received from war-File fill in tomcat on ways:
...\apache-tomcat\webapps\

Used  version:
apache-IOC-3.3.9
apache-tomcat-9.0.0.M1
java 1.7.0_79

==================================

after starting the tomcat home page is available at url:
http://localhost/webClient/client/department/table_Department

- the exchange of data between the client and the server has carried out using web-services REST (RESTful-WS)
    in the spring on the server

- took the data through the class RestTemplate

- The url of the server sewn into the client in the form of constants in class:
    myMultiProject\webClient\src\main\java\client\service\DepartmentServiceImpl.java
    myMultiProject\webClient\src\main\java\client\service\EmployeeServiceImpl.java

- The constant looks like this:

    private static final String URL_SERVER = "http://localhost";
    private static final String URL_GET_ALL_DepartmentS =
            "/restService/restful/department/listdata";

- For the server used json and XML mapping form

- URL list of commands to the server:
    GET "http://localhost/restService/restful/department/listdata" - list department
    GET "http://localhost/restService/restful/department/{id}" - department by id
    POST "http://localhost/restService/restful/department/" - new department
    PUT "http://localhost/restService/restful/department/{id}" - edit department by id
    DELETE "http://localhost/restService/restful/department/{id}" - delete department by id
    GET "http://localhost/restService/restful/employee/listdata/{id}" - list employee by departmentid
    GET "http://localhost/restService/restful/employee/{id}" - employee by id
    POST "http://localhost/restService/restful/employee/" - new employee
    PUT "http://localhost/restService/restful/employee/{id}" - edit employee
    DELETE "http://localhost/restService/restful/employee/{id}" - delete employee
    GET "http://localhost/restService/restful/employee/listdata/birthDate" - find employee

==================================
For the operation of the application requires the MySql5: jdbc:mysql://localhost:3306/mydbtest
for the operation of the application you want to create the schema - "mydbtest"
(gives schema is not created automatically)

for access to the database is used the following settings:
Host: localhost:3306
Database: mydbtest
User: root
Password: root

For tests used DB h2 In-memory database mode
when starting the application database is automatically populated
data are taken from:

For Project: myMultiProject/restService/src/Main\resources/META-INF/config/schema.sql
	         myMultiProject/restService/src/Main\resources/META-INF/config/test-data.sql

for tests: myMultiProject/restService/src/test/resources/META-INF/config/db-schema.sql
 	       myMultiProject/restService/src/test/resources/META-INF/config/db-test-data.sql

==================================

Web - application allows you to:

1. See the list of divisions and the average salary (will be automatically calculated) on the divisions (first payroll form);

2. The list of staff in the departments with the salary for each staff member and a search field to search for staff born in a certain date or in the period between the dates of the (second payroll form);

3. Change (add/edit/delete) the above data.