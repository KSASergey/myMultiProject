<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring_tags" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <spring_tags:url value="/resources/css/table.css" var="mainCss" />
    <link href="${mainCss}" rel="stylesheet" />

    <title>Add Employee for Department'</title>
</head>

<body>

<div class="mystil">
    <h1 align="center">Simple WEB project</h1>
    <br><br>

    <div align="center">
        <spring:form method="post" modelAttribute="modelEmployee" action="insert_Employee">
            <h2 class="all"> Add 'Employee' for Department: '${tempDepartment.department}' </h2>
            <table class="myformat">
                <tr>
                    <td>
                        <label>Full Name:</label>
                    </td>
                    <td>
                        <spring:input size="40%" path="fullName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Date of birth:</label>
                    </td>
                    <td>
                        <spring:input type="Date" size="40%" path="birthDate"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Salary:</label>
                    </td>
                    <td>
                        <spring:input size="40%" path="salary"/>
                    </td>
                </tr>
            </table>
            <br><br>
            <input type="submit" value="Add" class="myinput">
        </spring:form>
    </div>

</div>

</body>

</html>
