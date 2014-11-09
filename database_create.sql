-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `findyourartist`
--

CREATE DATABASE  IF NOT EXISTS `findyourartist`;

-- --------------------------------------------------------

--
-- Structure de la table `artist`
--

CREATE TABLE IF NOT EXISTS `findyourartist`.`artist` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `style` text NOT NULL,
  `id_shop` int(16) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_shop` (`id_shop`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Contenu de la table `artist`
--

INSERT INTO `findyourartist`.`artist` (`id`, `name`, `style`, `id_shop`) VALUES
(14, 'Nils', 'Graphic', 4),
(15, 'David Morrison', 'Traditional', 4),
(16, 'Klaim', 'Abstract', 6),
(17, 'Maxyne', 'Realism', 4),
(18, 'Ludo', 'Old school', 4),
(19, 'Belly', 'Graphic', 7),
(20, 'Niko Inko', 'Abstract', 7),
(21, 'Olivier Poinsignon', 'Geometric', 8),
(22, 'Sacha Madewithlove', 'Graphic', 5),
(23, 'Marine Martin', 'Dotwork', 4),
(24, 'Loic', 'Traditional', 1),
(25, 'El Pimp', 'Old school', 7),
(26, 'Deexen', 'Graphic', 8);

-- --------------------------------------------------------

--
-- Structure de la table `shop`
--

CREATE TABLE IF NOT EXISTS `findyourartist`.`shop` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `number` int(24) NOT NULL,
  `street` varchar(256) NOT NULL,
  `zipcode` int(24) NOT NULL,
  `city` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `shop`
--

INSERT INTO `findyourartist`.`shop` (`id`, `name`, `number`, `street`, `zipcode`, `city`) VALUES
(1, 'Abraxas Beaubourg', 9,'rue Saint-Merri', 75004, 'Paris'),
(2, 'Abraxas St-Honoré', 5,'rue du Marché Saint-Honoré', 75001, 'Paris'),
(3, 'Abraxas Neuilly', 1,'rue Casimir Pinel', 92200, 'Paris'),
(4, 'Art Corpus', 49, 'rue Greneta',75002, 'Neuilly-sur-Seine'),
(5, 'Tribal Act', 161, 'rue Amelot',75001, 'Paris'),
(6, 'Street Tattoo', 7, 'boulevard Toussaint Lucas', 95130, 'Franconville'),
(7, 'Belly Button Tattoo Shop', 15,'rue Joseph Coma', 66000, 'Perpignan'),
(8, 'Laissez-moi dessiner', 56,'avenue de Royat', 63400, 'Chamalières');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `artist`
--
ALTER TABLE `findyourartist`.`artist`
  ADD CONSTRAINT `shop_frkey` FOREIGN KEY (`id_shop`) REFERENCES `findyourartist`.`shop` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
