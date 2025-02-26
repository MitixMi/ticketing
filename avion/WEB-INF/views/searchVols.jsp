<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 20/02/2025
  Time: 09:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="avion.com.models.Ville" %>
<%@ page import="avion.com.models.Vol" %>

<html>
<head>
    <title>Recherche de Vols</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            color: #0056b3;
            margin-top: 20px;
        }

        form {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        input[type="number"],
        input[type="date"],
        input[type="text"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        select {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #0056b3;
            border: none;
            color: white;
            font-size: 16px;
            cursor: pointer;
            border-radius: 4px;
        }

        button:hover {
            background-color: #003d80;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th,
        td {
            padding: 12px;
            text-align: center;
            border: 1px solid #ccc;
        }

        th {
            background-color: #0056b3;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f4f4f4;
        }

        tr:hover {
            background-color: #e1e1e1;
        }

        nav {
            background-color: #333;
            color: #fff;
            padding: 10px;
        }

        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
        }

        nav ul li {
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
    </style>
</head>
<body>
<h2>Recherche de Vols</h2>

<%
    String role = (String) session.getAttribute("role");
    if ("admin".equals(role)) {
%>
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/listControllers/vols_page">Ajout Vol</a></li>
        <li><a href="${pageContext.request.contextPath}/listControllers/vols">Liste Vol</a></li>
        <li><a href="${pageContext.request.contextPath}/listControllers/search_page">Recherche de vol</a></li>
        <li><a href="${pageContext.request.contextPath}/listControllers/login_out">Déconnexion</a></li>
    </ul>
</nav>
<%
} else if ("client".equals(role)) {
%>
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/listControllers/reserver_page">Réserver un vol</a></li>
        <li><a href="${pageContext.request.contextPath}/listControllers/mes-reservations">Liste des réservations</a></li>
        <li><a href="${pageContext.request.contextPath}/listControllers/search_page">Recherche de vol</a></li>
        <li><a href="${pageContext.request.contextPath}/listControllers/login_out">Déconnexion</a></li>
    </ul>
</nav>
<% } %>

<form action="/avion/listControllers/searchVol" method="post">
    <label>Ville de départ :</label>
    <select name="villeDepartId">
        <option value="">all</option>
        <% List<Ville> villes = (List<Ville>) request.getAttribute("villes"); %>
        <% for (Ville ville : villes) { %>
        <option value="<%= ville.getId() %>"><%= ville.getNom() %></option>
        <% } %>
    </select><br>

    <label>Ville d’arrivée :</label>
    <select name="villeArriveeId">
        <option value="">all</option>
        <% for (Ville ville : villes) { %>
        <option value="<%= ville.getId() %>"><%= ville.getNom() %></option>
        <% } %>
    </select><br>

    <label>Date de départ :</label>
    <input type="date" name="dateDepart"><br>

    <label>Prix maximum :</label>
    <input type="number" step="0.01" name="maxPrix"><br>

    <button type="submit">Rechercher</button>
</form>

<h3>Résultats de la recherche</h3>
<% if (request.getAttribute("vols") != null) { %>
<table>
    <tr>
        <th>ID</th>
        <th>Départ</th>
        <th>Arrivée</th>
        <th>Date Départ</th>
        <th>Date Arrivée</th>
        <th>Prix</th>
    </tr>
    <% for (Vol vol : (List<Vol>) request.getAttribute("vols")) { %>
    <tr>
        <td><%= vol.getId() %></td>
        <td><%= vol.getVilleDepartId() %></td>
        <td><%= vol.getVilleArriveeId() %></td>
        <td><%= vol.getDateDepart() %></td>
        <td><%= vol.getDateArrivee() %></td>
        <td><%= vol.getPrix() %>€</td>
    </tr>
    <% } %>
</table>
<% } else { %>
<p>Aucun vol trouvé.</p>
<% } %>
</body>
</html>




