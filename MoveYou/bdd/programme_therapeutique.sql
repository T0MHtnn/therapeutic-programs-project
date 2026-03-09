-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 28 fév. 2025 à 13:30
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- Structure de la table `utilisateur`
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `genre` varchar(20) NOT NULL,
  `mdp` varchar(255) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `pathologie` varchar(255) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Données pour utilisateur (ATTENTION - Marchera pas car nécessite des mdp cryptés et non en clair)
INSERT INTO `utilisateur` (`user_id`, `email`, `mdp`, `genre`, `pathologie`, `nom`, `prenom`, `age`) VALUES
(1, 'john.doe@example.com', 'password123', 'Homme', 'Diabète', 'Doe', 'John', 34),
(2, 'jane.smith@example.com', 'password456', 'Femme', 'Hypertension', 'Smith', 'Jane', 29),
(3, 'alice.brown@example.com', 'password789', 'Femme', 'Asthme', 'Brown', 'Alice', 41),
(4, 'bob.white@example.com', 'password321', 'Homme', 'Arthrite', 'White', 'Bob', 55),
(5, 'charlie.green@example.com', 'password654', 'Homme', 'Obésité', 'Green', 'Charlie', 47),
(6, 'daisy.blue@example.com', 'password987', 'Femme', 'Migraines', 'Blue', 'Daisy', 36),
(7, 'eva.red@example.com', 'password567', 'Femme', 'Diabète', 'Red', 'Eva', 22),
(8, 'frank.yellow@example.com', 'password1234', 'Homme', 'Hypertension', 'Yellow', 'Frank', 60),
(9, 'george.purple@example.com', 'password4321', 'Homme', 'Asthme', 'Purple', 'George', 39),
(10, 'hannah.orange@example.com', 'password5678', 'Femme', 'Migraines', 'Orange', 'Hannah', 26);


-- Structure de la table `activite`
CREATE TABLE IF NOT EXISTS `activite` (
  `activity_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `discipline` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `pathologies` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- Données pour activite
INSERT INTO `activite` (`activity_id`, `name`, `description`, `discipline`, `pathologies`) VALUES
(1, 'Yoga doux', 'Yoga pour débutants', 'Yoga', 'Hypertension, Stress'),
(2, 'Pilates', 'Renforcement musculaire doux', 'Pilates', 'Douleurs lombaires'),
(3, 'Aquagym', 'Exercices en piscine', 'Natation', 'Arthrite'),
(4, 'Zumba', 'Danse fitness', 'Danse', 'Obésité'),
(5, 'Tai Chi', 'Relaxation et équilibre', 'Arts Martiaux', 'Stress, Migraines'),
(6, 'Randonnée', 'Activité en plein air', 'Marche', 'Diabète, Obésité'),
(7, 'Cyclisme', 'Exercice cardiovasculaire', 'Vélo', 'Hypertension, Diabète'),
(8, 'Musculation', 'Renforcement musculaire', 'Fitness', 'Général'),
(9, 'Méditation', 'Gestion du stress', 'Relaxation', 'Stress, Migraines'),
(10, 'Cours de natation', 'Apprentissage de la nage', 'Natation', 'Arthrite, Asthme');


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bddmoveyou`
--

-- --------------------------------------------------------

--
-- Structure de la table `programme_therapeutique`
--

CREATE TABLE `programme_therapeutique` (
  `therapeutic_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `programme_therapeutique`
--

INSERT INTO `programme_therapeutique` (`therapeutic_id`, `nom`, `user_id`) VALUES
(1, 'Programme Asthme Lourd', 1),
(2, 'Programme Diabète Contrôle', 2),
(3, 'Programme Cardiaque', 3),
(4, 'Programme Anti-Stress', 4),
(5, 'Programme Respiratoire', 5),
(6, 'Programme Hypertension', 6),
(7, 'Programme Arthrose Confort', 7),
(8, 'Programme Relaxation', 8),
(9, 'Programme Forme', 9),
(10, 'Programme Bien-Être', 10);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `programme_therapeutique`
--
ALTER TABLE `programme_therapeutique`
  ADD PRIMARY KEY (`therapeutic_id`),
  ADD KEY `FKe7eee4kq1llh6yqkux6ewuhxe` (`user_id`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `programme_therapeutique`
--
ALTER TABLE `programme_therapeutique`
  ADD CONSTRAINT `FKe7eee4kq1llh6yqkux6ewuhxe` FOREIGN KEY (`user_id`) REFERENCES `utilisateur` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
