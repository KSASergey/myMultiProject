<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring_tags" uri="http://www.springframework.org/tags"%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <%--<link href="<c:url value="/resources/css/table.css" />" rel="stylesheet">--%>
    <%--<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/table.css"/>">--%>
    <spring_tags:url value="/resources/css/table.css" var="mainCss" />
    <link href="${mainCss}" rel="stylesheet" />

    <title>Update Department</title>
</head>

<body>

<div class="mystil">
    <h1 align="center">Simple WEB project</h1>
    <br><br>
    <div align="center">
        <spring:form method="post" modelAttribute="modelDepartment" action="update_Department">
            <h2 class="all"> Edit 'Department' for table </h2>
            Department: <spring:input size="40%" value="${department.department}" path="department"/>
            <br><br><br>
            <%--<spring:button>Add</spring:button>--%>
            <input type="submit" value="Submit" class="myinput">
            <spring:hidden path="id" value="${department.id}" />
        </spring:form>
    </div>

</div>



</body>

</html>
