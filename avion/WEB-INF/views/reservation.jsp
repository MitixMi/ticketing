<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 20/02/2025
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="avion.com.models.Vol" %>
<%@ page import="avion.com.models.Promotion" %>

<html>
<head>
    <title>Réserver un Vol</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
        }

        /* Navbar */
        .navbar {
            width: 250px;
            background-color: #0056b3;
            color: white;
            padding: 20px;
            height: 100vh;
            position: fixed;
            left: 0;
            top: 0;
        }

        .navbar h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .navbar a {
            display: block;
            color: white;
            text-decoration: none;
            padding: 10px;
            border-radius: 5px;
            margin: 5px 0;
        }

        .navbar a:hover {
            background-color: #003d80;
        }

        /* Contenu principal */
        .main-content {
            margin-left: 270px;
            width: calc(100% - 270px);
            padding: 20px;
            display: flex;
            flex-direction: column;
        }

        /* Liste des vols en haut */
        .vols-container {
            max-height: 200px;
            overflow-y: auto;
            border: 1px solid #ccc;
            padding: 10px;
            background: white;
            border-radius: 8px;
        }

        /* Disposition des promotions et formulaire */
        .bottom-section {
            display: flex;
            gap: 20px;
            margin-top: 20px;
        }

        /* Tableaux avec défilement */
        .scroll-table {
            max-height: 300px;
            overflow-y: auto;
            width: 100%;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
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

        /* Formulaire */
        .reservation-form {
            flex: 1;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        select, input, button {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
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
    </style>
</head>
<body>

<!-- Navbar -->
<div class="navbar">
    <h2>Menu</h2>
    <li><a href="${pageContext.request.contextPath}/listControllers/reserver_page">Réserver un vol</a></li>
    <li><a href="${pageContext.request.contextPath}/listControllers/mes-reservations">Liste des réservations</a></li>
    <li><a href="${pageContext.request.contextPath}/listControllers/search_page">Recherche de vol</a></li>
    <li><a href="${pageContext.request.contextPath}/listControllers/login_out">Déconnexion</a></li>
</div>

<!-- Contenu principal -->
<div class="main-content">
    <h2>Réserver un Vol</h2>

    <% if (request.getAttribute("message") != null) { %>
    <p class="message"><%= request.getAttribute("message") %></p>
    <% } %>

    <% if (request.getAttribute("error") != null) { %>
    <p class="error"><%= request.getAttribute("error") %></p>
    <% } %>

    <!-- Liste des vols -->
    <div class="vols-container">
        <h3>Liste des Vols</h3>
        <% if (request.getAttribute("vols") != null) { %>
        <div class="scroll-table">
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
                    <td><%= vol.getVilleDepartNom() %></td>
                    <td><%= vol.getVilleArriveeNom() %></td>
                    <td><%= vol.getDateDepart() %></td>
                    <td><%= vol.getDateArrivee() %></td>
                    <td><%= vol.getPrix() %>€</td>
                </tr>
                <% } %>
            </table>
        </div>
        <% } else { %>
        <p>Aucun vol trouvé.</p>
        <% } %>
    </div>

    <!-- Promotions et Formulaire de réservation -->
    <div class="bottom-section">
        <!-- Promotions -->
        <div class="scroll-table" style="flex: 1;">
            <h3>Promotions Actuelles</h3>
            <% if (request.getAttribute("promotions") != null) { %>
            <table>
                <tr>
                    <th>Vol ID</th>
                    <th>Réduction (%)</th>
                    <th>Sièges Restants</th>
                </tr>
                <% for (Promotion promo : (List<Promotion>) request.getAttribute("promotions")) { %>
                <tr>
                    <td><%= promo.getVolId() %></td>
                    <td><%= promo.getPourcentageReduction() %>%</td>
                    <td><%= promo.getNbSiegePromo() %></td>
                </tr>
                <% } %>
            </table>
            <% } else { %>
            <p>Aucune promotion disponible.</p>
            <% } %>
        </div>

        <!-- Formulaire de réservation -->
        <div class="reservation-form">
            <h3>Réserver un Vol</h3>
            <form action="/avion/listControllers/reserverVol" method="post">
                <label>Choisir un Vol :</label>
                <select name="volId" required>
                    <% List<Vol> vols = (List<Vol>) request.getAttribute("vols");
                        if (vols != null) {
                            for (Vol vol : vols) { %>
                    <option value="<%= vol.getId() %>">
                        Vol <%= vol.getId() %> | Départ: <%= vol.getVilleDepartNom() %> | Arrivée: <%= vol.getVilleArriveeNom() %> | Prix: <%= vol.getPrix() %>€
                    </option>
                    <% }
                    } %>
                </select>

                <label>Type de siège :</label>
                <select name="typeSiege" required>
                    <option value="business">Business</option>
                    <option value="eco">Économique</option>
                </select>

                <button type="submit">Réserver</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>




