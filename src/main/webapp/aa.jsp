
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="abc" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dd" uri="http://jtthink.com" %>
<%--
<%
    response.getWriter().write(request.getAttribute("name").toString());
%>
--%>

<html>
<head>
    <title>aa.jsp</title>
</head>
<body>
<abc:out value="${name}"></abc:out>
<hr/>
${age}
<hr/>
<dd:god/>
</body>
</html>
