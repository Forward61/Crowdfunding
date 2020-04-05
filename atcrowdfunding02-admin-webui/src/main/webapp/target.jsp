<%--
  Created by IntelliJ IDEA.
  User:
  Date: 2020/3/23
  Time: 下午10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>target jsp title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/ssm.html"/>

</head>
<body>
    <a href="ssm.html">测试s</a>
    ${requestScope.adminList}
</body>
</html>
