<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="cfmt" uri="http://nhnacademy.com/cfmt" %>
<html lang="ko">
<head>
    <title>학생-조회</title>
    <link rel="stylesheet" href="/student/style.css" />
</head>
<body>
<h1>학생 - 조회</h1>
<hr/>

<table class="form-table">
    <tbody>
    <tr>
        <th>아이디</th>
        <td>${student.id}</td>
    </tr>
    <tr>
        <th>이름</th>
        <td>${student.name}</td>
    </tr>
    <tr>
        <th>성별</th>
        <td>${student.gender}</td>
    </tr>
    <tr>
        <th>나이</th>
        <td>${student.age}</td>
    </tr>
    <tr>
        <th>등록일</th>
        <td>
            ${cfmt:formatDate(student.createdAt, "yyyy-MM-dd HH:mm:ss")}
        </td>
    </tr>
    </tbody>
</table>

<div class="action-links">
    <a href="/student/list.do">리스트</a>
    <a href="/student/update.do?id=${student.id}">수정</a>
    <form method="post" action="/student/delete.do" style="display:inline;">
        <input type="hidden" name="id" value="${student.id}" />
        <button type="submit">삭제</button>
    </form>
</div>

</body>
</html>