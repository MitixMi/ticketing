<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map" %>  <!-- Import for Map interface -->
<!DOCTYPE html>
<html>
<head>
    <title>Employee Form</title>
    <style>
        .error-message {
            color: red;
            font-size: small;
        }
    </style>
</head>
<body>
    <h2>Employee Form</h2>

    <%
        // Retrieve field-specific errors
        Map<String, String> fieldErrors = (Map<String, String>) request.getAttribute("fieldErrors");
    %>

    <form action="${pageContext.request.contextPath}/listControllers/submitEmpForm" method="post">
        <label for="name">Employee Name:</label>
        <input type="text" name="name" id="name" value="${param.name}">
        <% if (fieldErrors != null && fieldErrors.containsKey("name")) { %>
            <span class="error-message"><%= fieldErrors.get("name") %></span>
        <% } %>
        <br>

        <label for="employeeId">Employee ID:</label>
        <input type="text" name="employeeId" id="employeeId" value="${param.employeeId}">
        <% if (fieldErrors != null && fieldErrors.containsKey("employeeId")) { %>
            <span class="error-message"><%= fieldErrors.get("employeeId") %></span>
        <% } %>
        <br>

        <label for="email">Email:</label>
        <input type="text" name="email" id="email" value="${param.email}">
        <% if (fieldErrors != null && fieldErrors.containsKey("email")) { %>
            <span class="error-message"><%= fieldErrors.get("email") %></span>
        <% } %>
        <br>

        <label for="age">Age:</label>
        <input type="number" name="age" id="age" value="${param.age}">
        <% if (fieldErrors != null && fieldErrors.containsKey("age")) { %>
            <span class="error-message"><%= fieldErrors.get("age") %></span>
        <% } %>
        <br>

        <label for="position">Position:</label>
        <input type="text" name="position" id="position" value="${param.position}">
        <% if (fieldErrors != null && fieldErrors.containsKey("position")) { %>
            <span class="error-message"><%= fieldErrors.get("position") %></span>
        <% } %>
        <br>

        <button type="submit">Submit</button>
    </form>
</body>
</html>



