<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 25/02/2025
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Menu Client</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f4f4f4;
      text-align: center;
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
    h1 {
      color: #333;
      margin-top: 50px;
    }
    .container {
      margin-top: 30px;
    }
  </style>
</head>
<body>

<nav>
  <ul>
    <li><a href="${pageContext.request.contextPath}/listControllers/reserver_page">Réserver un vol</a></li>
    <li><a href="${pageContext.request.contextPath}/listControllers/mes-reservations">Liste des réservations</a></li>
    <li><a href="${pageContext.request.contextPath}/listControllers/search_page">Recherche de vol</a></li>
    <li><a href="${pageContext.request.contextPath}/listControllers/login_out">Déconnexion</a></li>
  </ul>
</nav>

<h1>Bienvenue sur le menu client</h1>

<div class="container">
  <p>Sélectionnez une option dans le menu ci-dessus pour gérer vos vols et réservations.</p>
</div>

</body>
</html>

