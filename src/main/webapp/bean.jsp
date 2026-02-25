<%--
  Created by IntelliJ IDEA.
  User: kimminseo
  Date: 26. 2. 25.
  Time: 오전 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp bean action tag</title>
</head>
<body>
<jsp:useBean id="user1" scope="request" class="com.nhnacademy.User" />    <jsp:setProperty name="user1" property="name" value="marco" />
<jsp:setProperty name="user1" property="age" value="38" />

<p>my name is <jsp:getProperty name="user1" property="name"/>.</p>
<p>i am <jsp:getProperty name="user1" property="age"/> years old.</p>
<p>toString : <%=request.getAttribute("user1")%></p>
</body>
</html>
