<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>CreateStudent</title>
</head>
<body>
<c:if test="${requestScope['message'] != null}">
    <span>${message}</span>
</c:if>
<a href="/student">Back</a>
<form action="/student?action=create&id=${student.id}" method="post">
    <label for="name">Name</label>
    <input type="text" name="name" id="name" value="${student.name}" />

    <label for="dob">Dob</label>
    <input type="date" name="dob" id="dob" value="${student.dob}" />

    <label for="gender">Gender</label>
    <input type="text" name="gender" id="gender" value="${student.gender}" />

    <label for="role">Class</label>

    <select name="studentClassId" id="role">
        <option value="">--None--</option>
        <c:forEach items="${studentClass}" var="stdclass">
            <option value="${stdclass.idClass}">${stdclass.classname}</option>
        </c:forEach>
    </select>

    <button type="submit">Create</button>
</form>
</body>
</html>