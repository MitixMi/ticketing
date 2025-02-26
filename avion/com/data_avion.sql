CREATE DATABASE IF NOT EXISTS ticketing_db;
USE ticketing_db;

-- Table des avions
CREATE TABLE avion (
    id INT PRIMARY KEY AUTO_INCREMENT,
    modele VARCHAR(100) NOT NULL,
    nb_siege_business INT NOT NULL,
    nb_siege_eco INT NOT NULL,
    date_fabrication DATE NOT NULL
);

-- Table des villes desservies
CREATE TABLE ville (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL UNIQUE
);

-- Table des vols
CREATE TABLE vol (
    id INT PRIMARY KEY AUTO_INCREMENT,
    avion_id INT NOT NULL,
    ville_depart_id INT NOT NULL,
    ville_arrivee_id INT NOT NULL,
    date_depart DATETIME NOT NULL,
    date_arrivee DATETIME NOT NULL,
    prix DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (avion_id) REFERENCES avion(id) ON DELETE CASCADE,
    FOREIGN KEY (ville_depart_id) REFERENCES ville(id) ON DELETE CASCADE,
    FOREIGN KEY (ville_arrivee_id) REFERENCES ville(id) ON DELETE CASCADE
);

-- Table des utilisateurs
CREATE TABLE utilisateur (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin', 'client') NOT NULL DEFAULT 'client'
);

-- Table des types de sièges
CREATE TABLE type_siege (
    id INT PRIMARY KEY AUTO_INCREMENT,
    type ENUM('business', 'eco') NOT NULL,
    prix DECIMAL(10,2) NOT NULL
);

-- Table des réservations
CREATE TABLE reservation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    vol_id INT NOT NULL,
    utilisateur_id INT NOT NULL,
    type_siege ENUM('business', 'eco') NOT NULL,
    date_reservation DATETIME NOT NULL DEFAULT NOW(),
    statut ENUM('confirmée', 'annulée') NOT NULL DEFAULT 'confirmée',
    FOREIGN KEY (vol_id) REFERENCES vol(id) ON DELETE CASCADE,
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id) ON DELETE CASCADE
);

-- Table des promotions
CREATE TABLE promotion (
    id INT PRIMARY KEY AUTO_INCREMENT,
    vol_id INT NOT NULL,
    pourcentage_reduction INT NOT NULL CHECK (pourcentage_reduction BETWEEN 0 AND 100),
    nb_siege_promo INT NOT NULL CHECK (nb_siege_promo >= 0),
    FOREIGN KEY (vol_id) REFERENCES vol(id) ON DELETE CASCADE
);



