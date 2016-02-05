<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring_tags" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <spring_tags:url value="/resources/css/table.css" var="mainCss" />
    <link href="${mainCss}" rel="stylesheet" />

    <title>Update Employee</title>
</head>

<body>

<div class="mystil">
    <h1 align="center">Simple WEB project</h1>
    <br><br>

    <div align="center">
        <spring:form method="post" modelAttribute="modelEmployee" action="update_Employee">
            <h2 class="all"> Edit 'Employee' for Department: '${tempDepartment.department}' </h2>
            <table class="myformat">
                <tr>
                    <td>
                        <label>Full Name:</label>
                    </td>
                    <td>
                        <spring:input size="40%" value="${employee.fullName}" path="fullName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Date of birth:</label>
                    </td>
                    <td>
                        <spring:input type="Date" size="40%" value="${employee.birthDate}" path="birthDate"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Salary:</label>
                    </td>
                    <td>
                        <spring:input size="40%" value="${employee.salary}" path="salary"/>
                    </td>
                </tr>
            </table>
            <br><br>

            <input type="submit" value="Submit" class="myinput">
            <spring:hidden path="id" value="${employee.id}" />
        </spring:form>
    </div>

</div>

</body>

</html>
