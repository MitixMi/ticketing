<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 19/02/2025
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="avion.com.models.Vol" %>
<%@ page import="avion.com.models.Avion" %>
<%@ page import="avion.com.models.Ville" %>

<html>
<head>
    <title>Liste des Vols</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fc;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
            overflow-y: auto;
        }
        .container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 80%;
            padding: 20px;
            margin-top: 20px;
            max-height: 90vh;
            overflow-y: auto;
        }
        h2 {
            color: #2c3e50;
            text-align: center;
        }
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }
        table th, table td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        table th {
            background-color: #f1f1f1;
        }
        table tr:hover {
            background-color: #f9f9f9;
        }
        nav {
            background-color: #333;
            color: #fff;
            padding: 10px;
            margin-bottom: 20px;
            width: 100%;
            text-align: center;
        }
        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }
        nav ul li {
            display: inline;
            margin-right: 10px;
        }
        nav ul li a {
            color: #fff;
            text-decoration: none;
            padding: 5px 10px;
        }
        nav ul li a:hover {
            background-color: #575757;
        }
        .alert {
            color: green;
            text-align: center;
            margin: 20px 0;
        }
        .error {
            color: red;
            text-align: center;
            margin: 20px 0;
        }
    </style>
</head>
<body>
<div class="container">
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/listControllers/vols_page">Ajout Vol</a></li>
            <li><a href="${pageContext.request.contextPath}/listControllers/vols">Liste Vol</a></li>
            <li><a href="${pageContext.request.contextPath}/listControllers/search_page">Recherche de vol</a></li>
            <li><a href="${pageContext.request.contextPath}/listControllers/login_out">Déconnexion</a></li>
        </ul>
    </nav>
    <h2>Liste des Vols</h2>

    <% if (request.getAttribute("message") != null) { %>
    <div class="alert">
        <%= request.getAttribute("message") %>
    </div>
    <% } %>

    <% if (request.getAttribute("error") != null) { %>
    <div class="error">
        <%= request.getAttribute("error") %>
    </div>
    <% } %>

    <%
        List<Vol> vols = (List<Vol>) request.getAttribute("vols");
    %>

    <table>
        <tr>
            <th>ID</th>
            <th>Départ</th>
            <th>Arrivée</th>
            <th>Date Départ</th>
            <th>Date Arrivée</th>
            <th>Prix</th>
            <th>Actions</th>
        </tr>
        <% for (Vol vol : vols) { %>
        <tr>
            <td><%= vol.getId() %></td>
            <td><%= vol.getVilleDepartNom() %></td>
            <td><%= vol.getVilleArriveeNom() %></td>
            <td><%= vol.getDateDepart() %></td>
            <td><%= vol.getDateArrivee() %></td>
            <td><%= vol.getPrix() %></td>
            <td>
                <form action="/listControllers/deleteVol" method="post">
                    <input type="hidden" name="id" value="<%= vol.getId() %>">
                    <button type="submit">Supprimer</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>



