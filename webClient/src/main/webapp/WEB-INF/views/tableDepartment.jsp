<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring_tags" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE html>--%>
<html>

  <head>
    <spring_tags:url value="/resources/css/table.css" var="mainCss" />
    <link href="${mainCss}" rel="stylesheet"  type="text/css" />

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Table Department</title>
  </head>
  <body>
  <div class="mystil">
    <h1 align="center">Simple WEB project</h1>
    <br>
    <table class="mytable" width="60%">
      <caption>Departments</caption>
      <tr>
        <th>ID</th>
        <th>Department</th>
        <th width="20%" >Average Salary</th>
        <th></th>
      <c:forEach var="department" items="${modelDepartmentList}" varStatus="status">
        <tr>
          <td>${department.id}</td>
          <td>${department.departmentName}</td>
          <td align="right">${department.averageSalary}</td>
          <td>
            <button type="submit" name="id" value=${department.id} form="select_Department">Select</button>
            <button type="submit" name="id" value=${department.id} form="update_Department">Update</button>
            <button type="submit" name="id" value=${department.id} form="delete_Department">Delete</button>
          </td>
        </tr>
      </c:forEach>

      <form id="select_Department" method="get" modelAttribute="modelDepartment" action="table_Employee"></form>
      <form id="update_Department" method="post" modelAttribute="modelDepartment" action="edit_Department"></form>
      <form id="delete_Department" method="post" modelAttribute="modelDepartment" action="delete_Department"></form>

    </table>

    <br>

    <div align="center">
      <form action="add_Department">
         <label>Add new 'Department' for table:</label>
         <input type="submit" value="Add"  class="myinput">
      </form>
    </div>

  </div>

  <br><br><br>
  <div class="mystil" align="center">
    <h2> Search 'Employee'</h2>

    <form method="get" action="find_Employee">
        <label>Date of birth:</label>
        <input type="Date" size="40%" name="firstBirthDate">
        <%--<input hidden type="Date" size="40%" name="d2">--%>
        <input type="submit" value="Search" class="myinput">
    </form>

    <form method="get" action="find_Employee">
        <label>Range Date of birth:</label>
        <input type="Date" size="40%" name="firstBirthDate">
        <input type="Date" size="40%" name="lastBirthDate">
        <input type="submit" value="Search" class="myinput">
    </form>
  </div>


  </body>
</html>