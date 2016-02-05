<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring_tags" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE html>--%>
<html>
  <head>
    <spring_tags:url value="/resources/css/table.css" var="mainCss" />
    <link href="${mainCss}" rel="stylesheet"  type="text/css" />

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Table Employee</title>
  </head>
  <body>
  <div class="mystil">
    <h1 align="center">Simple WEB project</h1>
    <br>
    <table class="mytable" width="90%">
      <caption>Employee for Department: '${modelDepartment.department}'</caption>
      <tr>
        <th>ID</th>
        <th>Department</th>
        <th>Name</th>
        <th>BirthDate</th>
        <th>Salary</th>
        <th colspan="1"></th>
      <c:forEach var="employee" items="${modelEmployeeList}" varStatus="status">
        <tr>
          <td>${employee.id}</td>
          <td>${modelDepartment.department}</td>
          <td>${employee.fullName}</td>
          <td align="right">
              <fmt:formatDate pattern="dd-MM-yyyy" value="${employee.birthDate}"/>
          </td>
          <td align="right">${employee.salary}</td>
          <td>
            <button type="submit" name="id" value=${employee.id} form="update_Employee">Update</button>
            <button type="submit" name="id" value=${employee.id} form="delete_Employee">Delete</button>
          </td>
        </tr>
      </c:forEach>
      <form id="update_Employee" method="post" modelAttribute="modelEmployee" action="edit_Employee"></form>
      <form id="delete_Employee" method="post" modelAttribute="modelEmployee" action="delete_Employee"></form>

    </table>

    <br>

    <div align="center">
      <form method="get" modelAttribute="modelDepartment" action="add_Employee">
         <label>Add new 'Employee' for Department: '${modelDepartment.department}'</label>
         <input type="hidden" name="id" value=${modelDepartment.id}>
         <input type="submit" value="Add" class="myinput">
      </form>
      <br><br><br>
      <form action="table_Department">
        <label>Back to Department table</label>
        <input type="submit" value="Back" class="myinput">
      </form>
    </div>

  </div>

  </body>
</html>