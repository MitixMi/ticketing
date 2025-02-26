<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Validation Errors</title>
</head>
<body>
<h2>Form Errors</h2>
<ul>
    <c:forEach items="${errors}" var="error">
        <li>${error.key}: ${error.value}</li>
    </c:forEach>
</ul>
<a href="/WEB-INF/views/form.jsp">Go back</a>
</body>
</html>
