<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html lang="ko">
<head>
  <title>student - list</title>
  <link rel="stylesheet" href="/student/style.css" />
</head>

<body>
<h1>학생 리스트</h1>
<p><a href="/student/register.do" >학생(등록)</a></p>
<table class="student-table">
  <thead>
  <tr>
    <th class="center">아이디</th>
    <th class="center">이름</th>
    <th class="center">성별</th>
    <th class="center">나이</th>
    <th class="center">cmd</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="item" items="${studentList}">
    <tr>
      <td class="center">${item.id}</td>
      <td class="center">${item.name}</td>
      <td class="center">${item.gender}</td>
      <td class="center">${item.age}</td>
      <td class="center">
        <a href="/student/view.do?id=${item.id}">조회</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>