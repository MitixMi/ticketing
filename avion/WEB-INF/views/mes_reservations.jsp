<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 25/02/2025
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="avion.com.models.Reservation" %>

<html>
<head>
    <title>Mes Réservations</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        nav {
            background-color: #007BFF;
            padding: 15px;
        }
        nav ul {
            list-style-type: none;
            padding: 0;
        }
        nav ul li {
            display: inline;
            margin: 0 15px;
        }
        nav ul li a {
            text-decoration: none;
            color: white;
            font-size: 18px;
            font-weight: bold;
            padding: 10px 15px;
            border-radius: 5px;
            transition: background 0.3s;
        }
        nav ul li a:hover {
            background-color: #0056b3;
        }

        h2 {
            text-align: center;
            color: #0056b3;
            margin-top: 20px;
        }

        .container {
            width: 80%;
            margin: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: #fff;
            margin-top: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }

        th, td {
            padding: 12px;
            text-align: center;
            border: 1px solid #ddd;
        }

        th {
            background-color: #0056b3;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .cancel-btn {
            background-color: #ff4444;
            color: white;
            padding: 8px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .cancel-btn:hover {
            background-color: #cc0000;
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

    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/listControllers/reserver_page">Réserver un vol</a></li>
            <li><a href="${pageContext.request.contextPath}/listControllers/mes-reservations">Liste des réservations</a></li>
            <li><a href="${pageContext.request.contextPath}/listControllers/search_page">Recherche de vol</a></li>
            <li><a href="${pageContext.request.contextPath}/listControllers/login_out">Déconnexion</a></li>
        </ul>
    </nav>

<h2>Mes Réservations</h2>
<div class="container">
    <% if (request.getAttribute("reservations") != null) { %>
    <table>
        <tr>
            <th>ID</th>
            <th>Vol ID</th>
            <th>Type de Siège</th>
            <th>Date de Réservation</th>
            <th>Statut</th>
            <th>Prix Final (€)</th>
            <th>Action</th>
        </tr>
        <% for (Reservation res : (List<Reservation>) request.getAttribute("reservations")) { %>
        <tr>
            <td><%= res.getId() %></td>
            <td><%= res.getVolId() %></td>
            <td><%= res.getTypeSiege() %></td>
            <td><%= res.getDateReservation() %></td>
            <td><%= res.getStatut() %></td>
            <td><%= res.getPrixFinal() %></td>
            <td>
                <% if ("confirmée".equals(res.getStatut())) { %>
                <form action="${pageContext.request.contextPath}/listControllers/annulerReservation" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= res.getId() %>">
                    <button type="submit" class="cancel-btn">Annuler</button>
                </form>
                <% } else { %>
                <span>Annulation impossible</span>
                <% } %>
            </td>
        </tr>
        <% } %>
    </table>
    <% } else { %>
    <p>Aucune réservation trouvée.</p>
    <% } %>
</div>
</body>
</html>

