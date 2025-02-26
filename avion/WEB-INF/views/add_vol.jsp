<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 25/02/2025
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="avion.com.models.Avion" %>
<%@ page import="avion.com.models.Ville" %>

<html>
<head>
    <title>Ajout de Vol</title>
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
        .form-container {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }
        .form-container label {
            font-weight: bold;
            margin: 5px 0;
        }
        .form-container input, .form-container select {
            padding: 10px;
            margin: 5px 0 15px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
            width: 100%;
            max-width: 300px;
        }
        .form-container button {
            padding: 10px 20px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            max-width: 300px;
        }
        .form-container button:hover {
            background-color: #2980b9;
        }
    </style>
    <script>
        function updateArrivalCities() {
            var departCitySelect = document.getElementById("villeDepartId");
            var arrivalCitySelect = document.getElementById("villeArriveeId");
            var departCityId = departCitySelect.value;

            // Réinitialiser les options de la ville d'arrivée
            for (var i = 0; i < arrivalCitySelect.options.length; i++) {
                arrivalCitySelect.options[i].disabled = false; // Réactiver toutes les options
            }

            // Désactiver la ville de départ dans la ville d'arrivée
            for (var i = 0; i < arrivalCitySelect.options.length; i++) {
                if (arrivalCitySelect.options[i].value == departCityId) {
                    arrivalCitySelect.options[i].disabled = true;
                }
            }
        }
    </script>
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
    <h2>Ajouter un Vol</h2>
    <form action="${pageContext.request.contextPath}/listControllers/addVol" method="post" class="form-container">
        <label>Avion:</label>
        <select name="avionId" required>
            <% List<Avion> avions = (List<Avion>) request.getAttribute("avions"); %>
            <% for (Avion avion : avions) { %>
            <option value="<%= avion.getId() %>"><%= avion.getModele() %></option>
            <% } %>
        </select>

        <label>Ville Départ:</label>
        <select name="villeDepartId" id="villeDepartId" onchange="updateArrivalCities()" required>
            <% List<Ville> villes = (List<Ville>) request.getAttribute("villes"); %>
            <% for (Ville ville : villes) { %>
            <option value="<%= ville.getId() %>"><%= ville.getNom() %></option>
            <% } %>
        </select>

        <label>Ville Arrivée:</label>
        <select name="villeArriveeId" id="villeArriveeId" required>
            <% for (Ville ville : villes) { %>
            <option value="<%= ville.getId() %>"><%= ville.getNom() %></option>
            <% } %>
        </select>

        <label>Date Départ:</label>
        <input type="datetime-local" name="dateDepart" required><br>

        <label>Date Arrivée:</label>
        <input type="datetime-local" name="dateArrivee" required><br>

        <label>Prix:</label>
        <input type="number" step="0.01" name="prix" required><br>

        <button type="submit">Ajouter</button>
    </form>
</div>
</body>
</html>

