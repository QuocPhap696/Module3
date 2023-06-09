<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>book</title>
</head>
<body>
<h1>${action}</h1>
<a href="/book?action=create">Create Book</a>
<c:if test="${requestScope['book'].size() != 0}">
  <table border="1">
    <tr>
      <td>Id</td>
      <td>Name</td>
      <td>publicDate</td>
      <td>author</td>
      <td>idcategory</td>
      <td>namecategory</td>
    </tr>
    <c:forEach items="${book}" var="book">
      <tr>
        <td>${book.id}</td>
        <td>${book.name}</td>
        <td>${book.publicDate}</td>
        <td>${book.author}</td>
        <td>${book.category.idcategory}</td>
        <td>${book.category.namecategory}</td>
        <td><a href="/book?action=edit&id=${book.id}">Edit</a> </td>
        <td><a href="/book?action=delete&id=${book.id}" onclick="return confirm('Do you want to remove ${book.name}?')">Delete</a> </td>
      </tr>
    </c:forEach>
  </table>
</c:if>
</body>
</html>