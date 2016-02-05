<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring_tags" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE html>--%>
<html>
  <head>
    <spring_tags:url value="/resources/css/table.css" var="mainCss" />
    <link href="${mainCss}" rel="stylesheet"  type="text/css" />

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Find Employee</title>
  </head>
  <body>
  <div class="mystil">
    <h1 align="center">Simple WEB project</h1>
    <br>
    <table class="mytable" width="90%">
      <caption>Find Employee ${searshRange}</caption>
      <tr>
        <th>ID</th>
        <th>Department</th>
        <th>Name</th>
        <th>BirthDate</th>
        <th>Salary</th>
      <c:forEach var="employee" items="${modelEmployeeList}" varStatus="status">
        <tr>
          <td>${employee.id}</td>
          <td>
            <c:set var="department" value="${employee.department}"/>
            <c:out value="${department.department}"/>
          </td>
          <td>${employee.fullName}</td>
          <td align="right">
            <fmt:formatDate pattern="dd-MM-yyyy" value="${employee.birthDate}" />
          </td>
          <td align="right">${employee.salary}</td>
        </tr>
      </c:forEach>
    </table>

    <div align="center">
      <br><br><br>
      <form action="table_Department">
        <label>Back to Department table</label>
        <input type="submit" value="Back" class="myinput">
      </form>
    </div>

  </div>

  </body>
</html>