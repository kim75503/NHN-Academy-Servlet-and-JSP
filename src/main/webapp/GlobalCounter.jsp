<%--
  Created by IntelliJ IDEA.
  User: kimminseo
  Date: 26. 2. 25.
  Time: 오전 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
    <title>GlobalCounter</title>
</head>
<body>
<%!
  private  long counter = 0;
  private  long increaseCounter(){
    return ++counter;
  }
  public void jspinit(){
      counter = 100 ;
  }
%>

<h1> counter:<%=increaseCounter()%></h1>
</body>
</html>
