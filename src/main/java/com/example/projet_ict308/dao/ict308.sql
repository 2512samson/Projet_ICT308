-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 25, 2024 at 10:59 PM
-- Server version: 5.7.24
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ict308`
--

-- --------------------------------------------------------

--
-- Table structure for table `joueur`
--

CREATE TABLE `joueur` (
  `id` int(10) NOT NULL,
  `nom` varchar(256) NOT NULL,
  `sexe` enum('Masculin','Feminin') NOT NULL,
  `age` int(11) NOT NULL,
  `email` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `joueur`
--

INSERT INTO `joueur` (`id`, `nom`, `sexe`, `age`, `email`, `password`) VALUES
(1, 'SAMSON', 'Masculin', 24, 'sam@gmail.com', 'HDS2512SAM'),
(2, 'haranga', 'Masculin', 25, 'haranga@gmail.com', 'HDS2512HAR'),
(3, 'Victor', 'Masculin', 30, 'victor@gmail.com', 'HDS2512VI@c');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `Id` int(10) NOT NULL,
  `libelle` text NOT NULL,
  `reponse` text NOT NULL,
  `faurepo1` text NOT NULL,
  `faurepo2` text NOT NULL,
  `faurepo3` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`Id`, `libelle`, `reponse`, `faurepo1`, `faurepo2`, `faurepo3`) VALUES
(1, 'What is the capital of France?', 'Paris', 'London', 'Berlin', 'Madrid'),
(2, 'What is the largest planet in our Solar System?', 'Jupiter', 'Saturn', 'Earth', 'Mars'),
(3, 'What is the chemical symbol for water?', 'H2O', 'O2', 'CO2', 'N2'),
(4, 'Who wrote \"Romeo and Juliet\"?', 'William Shakespeare', 'Charles Dickens', 'Mark Twain', 'Jane Austen'),
(5, 'What is the square root of 64?', '8', '6', '7', '9'),
(6, 'What is 2 + 2?', '4', '3', '5', '6'),
(7, 'Who wrote \"To Kill a Mockingbird\"?', 'Harper Lee', 'Mark Twain', 'F. Scott Fitzgerald', 'Ernest Hemingway'),
(8, 'What is the smallest unit of life?', 'Cell', 'Atom', 'Molecule', 'Organ'),
(9, 'Who painted the Mona Lisa?', 'Leonardo da Vinci', 'Vincent van Gogh', 'Pablo Picasso', 'Claude Monet'),
(10, 'Quel est le plus grand océan du monde ?', 'Océan Pacifique', 'Océan Atlantique', 'Océan Indien', 'Océan Arctique'),
(11, 'Qui a écrit \"Les Misérables\" ?', 'Victor Hugo', 'Emile Zola', 'Gustave Flaubert', 'Alexandre Dumas'),
(12, 'Quel est le plus haut sommet du monde ?', 'Mont Everest', 'Mont Kilimandjaro', 'Mont Blanc', 'Mont Elbrouz');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `joueur`
--
ALTER TABLE `joueur`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `joueur`
--
ALTER TABLE `joueur`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
