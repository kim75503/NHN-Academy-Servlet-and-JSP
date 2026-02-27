<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:choose>
    <c:when test="${empty student}">
        <c:set var="formAction" value="${pageContext.request.contextPath}/student/register.do" />
    </c:when>
    <c:otherwise>
        <c:set var="formAction" value="${pageContext.request.contextPath}/student/update.do" />
    </c:otherwise>
</c:choose>

<html lang="ko">
<head>
    <title>회원가입</title>
    <link rel="stylesheet" href="/student/style.css" />
</head>

<body>
<h1>
    <c:choose>
        <c:when test="${empty student}">회원가입</c:when>
        <c:otherwise>학생-수정</c:otherwise>
    </c:choose>
</h1>
<hr/>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form method="post" action="${formAction}">
    <table class="form-table">
        <tbody>
        <tr>
            <th>ID</th>
            <td>
                <input type="text" name="id"
                       value="${empty student ? param.id : student.id}"
                       <c:if test="${not empty student}">readonly</c:if> />
            </td>
        </tr>
        <tr>
            <th>이름</th>
            <td>
                <input type="text" name="name"
                       value="${empty student ? param.name : student.name}" />
            </td>
        </tr>
        <tr>
            <th>성별</th>
            <td>
                <label>
                    <input type="radio" name="gender" value="M"
                           <c:if test="${(empty student and param.gender == 'M') or (not empty student and student.gender == 'M')}">checked</c:if> />
                    남
                </label>
                &nbsp;
                <label>
                    <input type="radio" name="gender" value="F"
                           <c:if test="${(empty student and (param.gender == 'F' or empty param.gender)) or (not empty student and student.gender == 'F')}">checked</c:if> />
                    여
                </label>
            </td>
        </tr>
        <tr>
            <th>나이</th>
            <td>
                <input type="number" name="age"
                       value="${empty student ? param.age : student.age}" />
            </td>
        </tr>
        </tbody>
    </table>

    <p>
        <button type="submit">
            <c:choose>
                <c:when test="${empty student}">등록</c:when>
                <c:otherwise>수정</c:otherwise>
            </c:choose>
        </button>
    </p>
</form>

</body>
</html>