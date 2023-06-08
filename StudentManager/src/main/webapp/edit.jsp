<%--
  Created by IntelliJ IDEA.
  User: QuocPhap
  Date: AD 2023/06/08
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<c:if test="${requestScope['message'] != null}">
    <span>${message}</span>
</c:if>
<a href="/student">Back</a><br>
<form action="/student?action=edit" method="post">
<%--    <label for="id">ID</label>--%>
    <input type="hidden" name="id" id="id" value="${student.id}" />

    <label for="name">Name</label>
    <input type="text" name="name" id="name" value="${student.name}" />

    <label for="dob">Dob</label>
    <input type="text" name="dob" id="dob" value="${student.dob}" />

    <label for="gender">Gender</label>
    <input type="text" name="gender" id="gender" value="${student.gender}" />

    <select name="studentClassId" id="role">
        <option value="">--None--</option>
        <c:forEach items="${studentClass}" var="stdclass">
            <option value="${stdclass.idClass}">${stdclass.classname}</option>
        </c:forEach>
    </select>
    <button type="submit">Edit</button>
</form>
</body>
</html>
