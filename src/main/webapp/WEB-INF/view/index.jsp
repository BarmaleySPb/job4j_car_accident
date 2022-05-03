<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- CSS only -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
        crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
          crossorigin="anonymous"></script>
  <title>Accident</title>
  <meta content="text/html; charset=utf-8"/>
</head>
<body>

<div>
  Login as : ${user.username}
</div>

<table class="table">
  <thead>
  <tr>
    <th scope="col">ID</th>
    <th scope="col">Name</th>
    <th scope="col">Text</th>
    <th scope="col">Address</th>
    <th scope="col">Type</th>
    <th scope="col">Rules</th>
  </tr>
  </thead>

  <tbody>
  <c:forEach items="${accidents}" var="accident">
    <tr>
      <td>
        <a href="<c:url value='/edit?id=${accident.id}'/>">${accident.id}</a>
      </td>
      <td>
          ${accident.name}
      </td>
      <td>
          ${accident.text}
      </td>
      <td>
          ${accident.address}
      </td>
      <td>
          ${accident.type.name}
      </td>
      <td>
        <c:forEach items="${accident.rules}" var="rule">
          ${rule.name}
        </c:forEach>
      </td>
    </tr>
  </c:forEach>
  </tbody>

</table>

<a href="<c:url value='/create'/>">Добавить инцидент</a>

</body>
</html>