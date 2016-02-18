<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring_tags" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <spring_tags:url value="/resources/css/table.css" var="mainCss" />
    <link href="${mainCss}" rel="stylesheet" />

    <title>Add Department</title>
</head>

<body>

<div class="mystil">
    <h1 align="center">Simple WEB project</h1>
    <br><br>

    <div align="center">
        <spring:form method="post" modelAttribute="modelDepartment" action="insert_Department">
            <h2 class="all"> Add new 'Department' for table </h2>
            Department: <spring:input size="40%" path="departmentName"/>
            <br><br>
            <input type="submit" value="Add" class="myinput">
        </spring:form>
    </div>

</div>



</body>

</html>
