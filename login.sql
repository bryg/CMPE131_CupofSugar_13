-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 12, 2017 at 07:49 PM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `login`
--

-- --------------------------------------------------------

--
-- Table structure for table `requests`
--

CREATE TABLE `requests` (
  `id` int(16) NOT NULL,
  `userid` int(16) NOT NULL,
  `deadline` date NOT NULL,
  `requesttype` int(3) NOT NULL,
  `title` text NOT NULL,
  `description` mediumtext NOT NULL,
  `pickuplocation` varchar(256) NOT NULL,
  `dropofflocation` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `requests`
--

INSERT INTO `requests` (`id`, `userid`, `deadline`, `requesttype`, `title`, `description`, `pickuplocation`, `dropofflocation`) VALUES
(2, 0, '2017-12-12', 0, 'Need Coffee', 'Send coffee please.', 'Philz Coffee', 'SJSU Engineering Building');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(15) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `homeaddr` varchar(50) NOT NULL,
  `stateid` varchar(16) NOT NULL,
  `sugartype` int(3) NOT NULL,
  `profilepic` int(16) NOT NULL,
  `cellphone` int(10) NOT NULL,
  `zipcode` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `homeaddr`, `stateid`, `sugartype`, `profilepic`, `cellphone`, `zipcode`) VALUES
(1, 'divyang', 'password123', NULL, '', '', 0, 0, 0, 0),
(10, 'Bob Newbie', 'Password123', 'bnewbie@hmail.com', '', '', 0, 0, 0, 0),
(11, 'Panfilo', 'qwerty', 'panfilo@mail.com', '', '', 0, 0, 0, 0),
(12, 'Ofelia', 'ofelia1970', 'ofelia1970@email.com', '', '', 0, 0, 0, 0),
(13, 'sugaruser1019', 'Password', 'sugaruser@email.com', '', '', 0, 0, 0, 0),
(14, 'asdfasd', '2312', 'asdf', 'fasdfasdf', 'asdf23231', 0, 0, 33399933, 91234);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `requests`
--
ALTER TABLE `requests`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `requests`
--
ALTER TABLE `requests`
  MODIFY `id` int(16) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
