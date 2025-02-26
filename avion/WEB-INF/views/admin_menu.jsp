<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 25/02/2025
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil</title>
    <style>
        /* Global Styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            text-align: center;
        }

        /* Navigation Bar */
        nav {
            background-color: #333;
            padding: 10px 0;
            text-align: center;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        nav ul {
            list-style: none;
            padding: 0;
            margin: 0;
            display: flex;
            justify-content: center;
        }

        nav ul li {
            margin: 0 15px;
        }

        nav ul li a {
            text-decoration: none;
            color: white;
            font-weight: bold;
            padding: 10px 15px;
            transition: background 0.3s;
            border-radius: 5px;
        }

        nav ul li a:hover {
            background-color: #575757;
        }

        /* Page Title */
        h1 {
            margin-top: 50px;
            color: #333;
        }
    </style>
</head>
<body>
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/listControllers/vols_page">Ajout Vol</a></li>
        <li><a href="${pageContext.request.contextPath}/listControllers/vols">Liste Vol</a></li>
        <li><a href="${pageContext.request.contextPath}/listControllers/search_page">Recherche de vol</a></li>
        <li><a href="${pageContext.request.contextPath}/listControllers/login_out">Déconnexion</a></li>
    </ul>
</nav>
<h1>Bienvenue sur la page d'accueil</h1>
</body>
</html>


