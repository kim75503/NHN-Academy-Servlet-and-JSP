<%--
  Created by IntelliJ IDEA.
  User: kimminseo
  Date: 26. 2. 25.
  Time: 오전 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>googooDan</title>
</head>
<body>
<%
    for(int i = 2; i < 10; i++){
%>
<h1><%=i%>단</h1>
<%
    for(int j = 1; j< 10; j++){
%>
    <p>
        <%=i%>*<%=j%>=<%=i*j%>
    </p>
<%
        }
    }
%>

</body>
</html>
