<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>CreateBook</title>
</head>
<body>
<c:if test="${requestScope['message'] != null}">
  <span>${message}</span>
</c:if>
<a href="/book">Back</a>
<form action="/book?action=create" method="post">
  <label for="name">Name</label>
  <input type="text" name="name" id="name" value="${student.name}" />

  <label for="publicDate">publicDate</label>
  <input type="date" name="publicDate" id="publicDate" value="${student.publicDate}" />

  <label for="author">author</label>
  <input type="text" name="author" id="author" value="${student.author}" />

  <label for="role">Category</label>

  <select name="categoryID" id="role">
    <option value="">--None--</option>
    <c:forEach items="${categoryBook}" var="category">
      <option value="${category.idcategory}">${category.namecategory}</option>
    </c:forEach>
  </select>

  <button type="submit">Create</button>
</form>
</body>
</html>