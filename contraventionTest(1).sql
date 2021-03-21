-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : Dim 21 mars 2021 à 05:45
-- Version du serveur :  8.0.23-0ubuntu0.20.04.1
-- Version de PHP : 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `contraventionTest`
--

-- --------------------------------------------------------

--
-- Structure de la table `amande`
--

CREATE TABLE `amande` (
  `code_amande` int NOT NULL,
  `matricule` varchar(254) DEFAULT NULL,
  `date_debut` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_fin` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `liste_infraction` varchar(254) NOT NULL,
  `statut` int NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `amande`
--

INSERT INTO `amande` (`code_amande`, `matricule`, `date_debut`, `date_fin`, `liste_infraction`, `statut`) VALUES
(2, 'CE 787 DR', '2021-01-30 23:00:00', '2021-01-31 13:35:27', '{\"0\":true,\"1\":true,\"2\":true,\"3\":false,\"4\":false,\"5\":true}', 1),
(3, 'NW 219 CP', '2021-01-30 23:00:00', '2021-01-31 13:35:56', '{\"0\":true,\"1\":true,\"2\":true,\"3\":false,\"4\":true,\"5\":false}', 0),
(4, 'AD 655 LA', '2021-01-30 23:00:00', '2021-01-31 13:36:05', '{\"0\":true,\"1\":true,\"2\":false,\"3\":true,\"4\":true,\"5\":true}', 0),
(5, 'ES 084 HA', '2021-01-30 23:00:00', '2021-01-31 13:36:07', '{\"0\":true,\"1\":true,\"2\":true,\"3\":true,\"4\":true,\"5\":true}', 0),
(6, 'NW 605 SB', '2021-01-30 23:00:00', '2021-01-31 15:05:24', '{\"0\":true,\"1\":false,\"2\":false,\"3\":false,\"4\":false,\"5\":false}', 0),
(7, 'NO 557 KA', '2021-01-30 23:00:00', '2021-01-31 15:05:28', '{\"0\":true,\"1\":true,\"2\":false,\"3\":false,\"4\":false,\"5\":true}', 0),
(8, 'OU 646 TS', '2021-01-30 23:00:00', '2021-01-31 18:35:30', '{\"0\":false,\"1\":false,\"2\":false,\"3\":false,\"4\":true,\"5\":false}', 0),
(9, 'EN 887 VJ', '2021-01-30 23:00:00', '2021-01-31 18:35:34', '{\"0\":true,\"1\":true,\"2\":true,\"3\":false,\"4\":false,\"5\":true}', 0),
(10, 'NW 663 VE', '2021-02-20 23:00:00', '2021-02-21 14:47:12', '{\"0\":true,\"1\":false,\"2\":true,\"3\":false,\"4\":true,\"5\":false}', 0),
(11, 'OU 463 TN', '2021-02-20 23:00:00', '2021-02-21 14:48:23', '{\"0\":true,\"1\":false,\"2\":false,\"3\":false,\"4\":false,\"5\":true}', 1),
(12, 'EN 865 NA', '2021-02-20 23:00:00', '2021-02-21 14:49:27', '{\"0\":true,\"1\":false,\"2\":true,\"3\":false,\"4\":true,\"5\":true}', 0),
(13, 'EN 887 VJ', '2021-03-04 09:09:32', '2021-03-04 09:09:32', '', 0),
(14, 'AD 661 XE', '2021-03-05 23:00:00', '2021-01-26 23:00:00', '{\"0\":false,\"1\":true,\"2\":false,\"3\":false,\"4\":false,\"5\":false}', 1),
(15, 'ES 600 TM', '2021-03-05 23:00:00', '2021-01-26 23:00:00', '{\"0\":true,\"1\":true,\"2\":false,\"3\":false,\"4\":true,\"5\":false}', 0),
(16, 'SU 912 DX', '2021-03-05 23:00:00', '2021-01-26 23:00:00', '{\"0\":true,\"1\":true,\"2\":false,\"3\":false,\"4\":true,\"5\":true}', 0),
(17, 'CE 899 BV', '2021-03-05 23:00:00', '2021-01-26 23:00:00', '{\"0\":false,\"1\":false,\"2\":true,\"3\":true,\"4\":false,\"5\":true}', 0),
(18, 'LT 349 CA', '2021-03-18 23:00:00', '2021-01-09 23:00:00', '{\"0\":true,\"1\":false,\"2\":true,\"3\":true,\"4\":true,\"5\":false}', 0),
(19, 'LT 189 PG', '2021-03-18 23:00:00', '2021-01-09 23:00:00', '{\"0\":false,\"1\":false,\"2\":true,\"3\":true,\"4\":false,\"5\":true}', 0),
(20, 'SU 571 GK', '2021-03-18 23:00:00', '2021-01-09 23:00:00', '{\"0\":true,\"1\":false,\"2\":true,\"3\":false,\"4\":true,\"5\":false}', 0),
(21, 'AD 598 HT', '2021-03-18 23:00:00', '2021-01-09 23:00:00', '{\"0\":true,\"1\":true,\"2\":true,\"3\":true,\"4\":false,\"5\":false}', 0),
(22, 'NW 558 GR', '2021-03-18 23:00:00', '2021-01-09 23:00:00', '{\"0\":false,\"1\":false,\"2\":true,\"3\":true,\"4\":false,\"5\":false}', 0),
(23, 'LT 613 AH', '2021-03-18 23:00:00', '2021-01-09 23:00:00', '{\"0\":false,\"1\":true,\"2\":true,\"3\":true,\"4\":false,\"5\":false}', 0),
(24, 'NO 684 EJ', '2021-03-18 23:00:00', '2021-01-09 23:00:00', '{\"0\":false,\"1\":true,\"2\":false,\"3\":true,\"4\":false,\"5\":true}', 0),
(25, 'AD 887 SA', '2021-03-18 23:00:00', '2021-01-09 23:00:00', '{\"0\":false,\"1\":false,\"2\":true,\"3\":false,\"4\":false,\"5\":true}', 0),
(26, 'AD 937 VF', '2021-03-20 23:00:00', '2021-01-11 23:00:00', '{\"0\":true,\"1\":true,\"2\":true,\"3\":true,\"4\":true,\"5\":false}', 1),
(27, 'OU 371 BK', '2021-03-20 23:00:00', '2021-01-11 23:00:00', '{\"0\":false,\"1\":false,\"2\":false,\"3\":false,\"4\":true,\"5\":false}', 0),
(28, 'SU 015 FW', '2021-03-20 23:00:00', '2021-01-20 23:00:00', '{\"0\":false,\"1\":true,\"2\":false,\"3\":true,\"4\":false,\"5\":true}', 0),
(29, 'NW 954 VE', '2021-03-20 23:00:00', '2021-01-20 23:00:00', '{\"0\":false,\"1\":false,\"2\":true,\"3\":true,\"4\":true,\"5\":true}', 0),
(30, 'EN 288 FB', '2021-03-20 23:00:00', '2021-01-20 23:00:00', '{\"0\":false,\"1\":true,\"2\":false,\"3\":false,\"4\":false,\"5\":false}', 0),
(31, 'EN 664 KO', '2021-03-20 23:00:00', '2021-01-20 23:00:00', '{\"0\":false,\"1\":true,\"2\":false,\"3\":false,\"4\":true,\"5\":true}', 0),
(32, 'ES 816 JN', '2021-03-20 23:00:00', '2021-01-20 23:00:00', '{\"0\":true,\"1\":true,\"2\":true,\"3\":true,\"4\":true,\"5\":false}', 0),
(33, 'LT 773 EE', '2021-03-20 23:00:00', '2021-01-20 23:00:00', '{\"0\":true,\"1\":false,\"2\":true,\"3\":true,\"4\":true,\"5\":false}', 0),
(34, 'SU 817 RY', '2021-03-20 23:00:00', '2021-01-20 23:00:00', '{\"0\":true,\"1\":false,\"2\":true,\"3\":true,\"4\":false,\"5\":false}', 0),
(35, 'EN 239 ER', '2021-03-20 23:00:00', '2021-01-20 23:00:00', '{\"0\":true,\"1\":true,\"2\":true,\"3\":false,\"4\":true,\"5\":false}', 0),
(36, 'CE 259 EX', '2021-03-20 23:00:00', '2021-01-20 23:00:00', '{\"0\":true,\"1\":true,\"2\":true,\"3\":false,\"4\":true,\"5\":false}', 0),
(37, 'CE 227 CY', '2021-03-20 23:00:00', '2021-01-20 23:00:00', '{\"0\":false,\"1\":false,\"2\":false,\"3\":true,\"4\":true,\"5\":true}', 0),
(38, 'EN 939 JK', '2021-03-20 23:00:00', '2021-01-20 23:00:00', '{\"0\":true,\"1\":false,\"2\":false,\"3\":false,\"4\":true,\"5\":false}', 0),
(39, 'ES 671 HV', '2021-03-20 23:00:00', '2021-01-20 23:00:00', '{\"0\":true,\"1\":true,\"2\":false,\"3\":true,\"4\":true,\"5\":true}', 0),
(40, 'ES 458 RG', '2021-03-20 23:00:00', '2021-01-20 23:00:00', '{\"0\":true,\"1\":true,\"2\":true,\"3\":true,\"4\":false,\"5\":true}', 0),
(41, 'LT 952 VI', '2021-03-20 23:00:00', '2021-01-20 23:00:00', '{\"0\":true,\"1\":false,\"2\":false,\"3\":false,\"4\":true,\"5\":true}', 0),
(42, 'SU 506 OU', '2021-03-20 23:00:00', '2021-01-20 23:00:00', '{\"0\":false,\"1\":true,\"2\":false,\"3\":false,\"4\":false,\"5\":true}', 0),
(43, 'EN 088 AY', '2021-03-20 23:00:00', '2021-01-20 23:00:00', '{\"0\":true,\"1\":false,\"2\":false,\"3\":false,\"4\":true,\"5\":false}', 0),
(44, 'LT 558 PW', '2021-03-20 23:00:00', '2021-01-20 23:00:00', '{\"0\":true,\"1\":false,\"2\":true,\"3\":false,\"4\":true,\"5\":true}', 0);

-- --------------------------------------------------------

--
-- Structure de la table `automobile`
--

CREATE TABLE `automobile` (
  `matricule` varchar(254) NOT NULL,
  `code_personne` int DEFAULT NULL,
  `premiere_immatriculation` varchar(254) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `automobile`
--

INSERT INTO `automobile` (`matricule`, `code_personne`, `premiere_immatriculation`) VALUES
('AD 598 HT', 1, NULL),
('AD 655 LA', 1, NULL),
('AD 661 XE', 1, NULL),
('AD 887 SA', 1, NULL),
('AD 937 VF', 15, NULL),
('CE 227 CY', 1, NULL),
('CE 259 EX', 1, NULL),
('CE 787 DR', 1, NULL),
('CE 899 BV', 1, NULL),
('EN 088 AY', 1, NULL),
('EN 239 ER', 1, NULL),
('EN 288 FB', 1, NULL),
('EN 664 KO', 1, NULL),
('EN 865 NA', 1, NULL),
('EN 887 VJ', 1, NULL),
('EN 939 JK', 1, NULL),
('ES 084 HA', 1, NULL),
('ES 458 RG', 1, NULL),
('ES 600 TM', 1, NULL),
('ES 671 HV', 1, NULL),
('ES 816 JN', 1, NULL),
('LT 189 PG', 1, NULL),
('LT 270 YT', 5, NULL),
('LT 349 CA', 1, NULL),
('LT 456 FD', 1, NULL),
('LT 456 RS', 4, NULL),
('LT 558 PW', 1, NULL),
('LT 613 AH', 1, NULL),
('LT 773 EE', 1, NULL),
('LT 952 VI', 1, NULL),
('NO 557 KA', 1, NULL),
('NO 684 EJ', 1, NULL),
('NW 219 CP', 1, NULL),
('NW 558 GR', 1, NULL),
('NW 605 SB', 1, NULL),
('NW 663 VE', 1, NULL),
('NW 954 VE', 1, NULL),
('OU 371 BK', 1, NULL),
('OU 463 TN', 1, NULL),
('OU 646 TS', 2, NULL),
('SU 015 FW', 1, NULL),
('SU 506 OU', 1, NULL),
('SU 571 GK', 14, NULL),
('SU 817 RY', 1, NULL),
('SU 889 UX', 1, NULL),
('SU 912 DX', 14, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `contraventions`
--

CREATE TABLE `contraventions` (
  `code_contraventions` int NOT NULL,
  `libelle` varchar(254) NOT NULL,
  `prix` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `contraventions`
--

INSERT INTO `contraventions` (`code_contraventions`, `libelle`, `prix`) VALUES
(1, 'Chevauchement des lignes continues ou discontinues', 25000),
(2, 'Circulation à gauche', 25000),
(3, 'Excès de vitesse', 25000),
(4, 'Défaut d\'essuie glace', 3600),
(5, 'Défaut de vitre de pare brise n\'altérant pas la vue', 3600);

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE `personne` (
  `code_personne` int NOT NULL,
  `mot_de_passe` varchar(254) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
  `num_cni` varchar(254) NOT NULL,
  `nom` varchar(254) NOT NULL,
  `prenom` varchar(254) NOT NULL,
  `date_naissance` date NOT NULL,
  `profession` varchar(254) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
  `tel` int NOT NULL,
  `adresse_mail` varchar(254) DEFAULT NULL,
  `adresse` varchar(254) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `personne`
--

INSERT INTO `personne` (`code_personne`, `mot_de_passe`, `num_cni`, `nom`, `prenom`, `date_naissance`, `profession`, `tel`, `adresse_mail`, `adresse`) VALUES
(1, '', '000000000000', 'john', 'doe', '2021-01-01', 'ing', 0, NULL, 'nkolmesseng'),
(2, 'deutschland', 'LTG234223HG', 'NZIMA YENGUE', 'IVAN FRANCK', '2021-02-01', 'ETudiant', 656488116, 'nzimaivan@gmail.com', 'Nkolmesseng'),
(3, '5f4dcc3b5aa765d61d8327deb882cf99', 'LT2289390HT63', 'nzima', 'ivan', '2021-03-01', 'etudiant', 657345217, 'nzimaivan@gmail.com', 'nkolmesseng'),
(4, 'b6edd10559b20cb0a3ddaeb15e5267cc', 'CT2565271567', 'jane', 'done', '1992-03-21', 'ingenieur', 654378980, 'janedoe@yahoo.com', 'paris'),
(5, '32250170a0dca92d53ec9624f336ca24', 'AD265176187H', 'franck', 'ivan', '2003-05-06', 'chauffeur', 655778822, 'franck@gmail.com', 'douala'),
(6, '1a1dc91c907325c69271ddf0c944bc72', 'CE82783527932U', 'john', 'ivan', '1994-06-02', 'artiste', 677889955, 'johnivan@yahoo.com', 'essos'),
(7, '1a1dc91c907325c69271ddf0c944bc72', 'FG27T26358273', 'bogne', 'stanley', '2010-10-05', 'musicien', 655334422, 'bogne@gmail.com', 'cradat'),
(8, '5d41402abc4b2a76b9719d911017c592', '1010118645', 'jelly', 'beans', '2021-03-03', 'etudiant', 677554411, 'jellybeans@gmail.com', 'essos'),
(9, '5d41402abc4b2a76b9719d911017c592', '245719920', 'marco', 'polo', '2021-03-16', 'proffesseur', 677889900, 'marcopolo@gmail.com', 'bastos'),
(10, '5d41402abc4b2a76b9719d911017c592', '517882623', 'flash', 'kid', '2021-03-01', 'athlete', 655443322, 'flashthekid@gmail.com', 'emonbo'),
(11, '5d41402abc4b2a76b9719d911017c592', '679276372', 'wesley', 'bryan', '2009-03-13', 'chauffeur taxi', 690674532, 'wesleybryan@gmail.com', 'cradat'),
(13, '5d41402abc4b2a76b9719d911017c592', '6757877755', 'bambi', 'choco', '2017-03-05', 'footballeur pro', 657289278, 'bambichoco@yahoo.com', 'nsam'),
(14, '5d41402abc4b2a76b9719d911017c592', '67879858', 'sergi', 'roberto', '1990-06-06', 'footballeur pro', 678665533, 'sergiroberto@barca.com', 'ekoundou'),
(15, '5d41402abc4b2a76b9719d911017c592', '', 'seraph', 'mitchum', '2002-03-10', 'artiste', 653889936, 'seraph.mitch@gmail.com', 'mvan');

-- --------------------------------------------------------

--
-- Structure de la table `poste_controle`
--

CREATE TABLE `poste_controle` (
  `code_pc` int NOT NULL,
  `code_sc` char(10) DEFAULT NULL,
  `nom_responsable` varchar(254) NOT NULL,
  `lieu` varchar(254) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `poste_controle`
--

INSERT INTO `poste_controle` (`code_pc`, `code_sc`, `nom_responsable`, `lieu`) VALUES
(1, '1', 'Ivan franck', 'douala'),
(2, '1', 'Tagne philip', 'yaounde');

-- --------------------------------------------------------

--
-- Structure de la table `systeme_central`
--

CREATE TABLE `systeme_central` (
  `code_sc` varchar(254) NOT NULL,
  `nom` varchar(254) NOT NULL,
  `password` varchar(254) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `systeme_central`
--

INSERT INTO `systeme_central` (`code_sc`, `nom`, `password`) VALUES
('1', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `systeme_flash`
--

CREATE TABLE `systeme_flash` (
  `code_sysflash` int NOT NULL,
  `code_pc` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `systeme_flash`
--

INSERT INTO `systeme_flash` (`code_sysflash`, `code_pc`) VALUES
(1, 1),
(2, 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `amande`
--
ALTER TABLE `amande`
  ADD PRIMARY KEY (`code_amande`),
  ADD KEY `fk_reference_2` (`matricule`);

--
-- Index pour la table `automobile`
--
ALTER TABLE `automobile`
  ADD PRIMARY KEY (`matricule`),
  ADD KEY `fk_possede` (`code_personne`);

--
-- Index pour la table `contraventions`
--
ALTER TABLE `contraventions`
  ADD PRIMARY KEY (`code_contraventions`);

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`code_personne`);

--
-- Index pour la table `poste_controle`
--
ALTER TABLE `poste_controle`
  ADD PRIMARY KEY (`code_pc`),
  ADD KEY `fk_administre` (`code_sc`);

--
-- Index pour la table `systeme_central`
--
ALTER TABLE `systeme_central`
  ADD PRIMARY KEY (`code_sc`);

--
-- Index pour la table `systeme_flash`
--
ALTER TABLE `systeme_flash`
  ADD PRIMARY KEY (`code_sysflash`),
  ADD KEY `fk_gere` (`code_pc`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `amande`
--
ALTER TABLE `amande`
  MODIFY `code_amande` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT pour la table `contraventions`
--
ALTER TABLE `contraventions`
  MODIFY `code_contraventions` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `personne`
--
ALTER TABLE `personne`
  MODIFY `code_personne` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `poste_controle`
--
ALTER TABLE `poste_controle`
  MODIFY `code_pc` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `systeme_flash`
--
ALTER TABLE `systeme_flash`
  MODIFY `code_sysflash` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `amande`
--
ALTER TABLE `amande`
  ADD CONSTRAINT `fk_reference_2` FOREIGN KEY (`matricule`) REFERENCES `automobile` (`matricule`);

--
-- Contraintes pour la table `automobile`
--
ALTER TABLE `automobile`
  ADD CONSTRAINT `fk_possede` FOREIGN KEY (`code_personne`) REFERENCES `personne` (`code_personne`);

--
-- Contraintes pour la table `poste_controle`
--
ALTER TABLE `poste_controle`
  ADD CONSTRAINT `fk_administre` FOREIGN KEY (`code_sc`) REFERENCES `systeme_central` (`code_sc`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `systeme_flash`
--
ALTER TABLE `systeme_flash`
  ADD CONSTRAINT `fk_gere` FOREIGN KEY (`code_pc`) REFERENCES `poste_controle` (`code_pc`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
