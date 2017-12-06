-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 06, 2017 at 07:39 AM
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
  `dropofflocation` varchar(256) NOT NULL,
  `acceptedby` int(16) NOT NULL,
  `complete` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `requests`
--

INSERT INTO `requests` (`id`, `userid`, `deadline`, `requesttype`, `title`, `description`, `pickuplocation`, `dropofflocation`, `acceptedby`, `complete`) VALUES
(2, 0, '2017-12-12', 0, 'Need Coffee', 'Send coffee please.', 'Philz Coffee', 'SJSU Engineering Building', 0, 0),
(3, 0, '2018-02-15', 0, 'Title', 'Send coffee please.', 'Philz Coffee', 'SJSU Engineering Building', 0, 0),
(4, 0, '2017-11-17', 0, 'Need a vacuum', 'need it', 'SJSU', 'SJSU Engineering Building', 0, 0),
(5, 0, '2017-12-02', 0, 'Need a Pencil', 'Any pencil', 'Anywhere', 'SJSU Student Union', 0, 0),
(6, 0, '2017-12-29', 0, 'Sugar', 'Need a cup of sugar', 'Grocery Store', 'SJSU', 0, 0),
(7, 11, '2017-12-23', 0, '123', 'qwe', 'qwe', 'qwe', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(15) NOT NULL,
  `username` varchar(50) NOT NULL,
  `sugarcubes` int(16) NOT NULL,
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

INSERT INTO `users` (`id`, `username`, `sugarcubes`, `password`, `email`, `homeaddr`, `stateid`, `sugartype`, `profilepic`, `cellphone`, `zipcode`) VALUES
(1, 'divyang', 0, 'password123', NULL, '', '', 0, 0, 0, 0),
(10, 'Bob Newbie', 0, 'Password123', 'bnewbie@hmail.com', '', '', 0, 0, 0, 0),
(11, 'Panfilo', 0, 'qwerty', 'panfilo@mail.com', '', '', 0, 0, 0, 0),
(12, 'Ofelia', 0, 'ofelia1970', 'ofelia1970@email.com', '', '', 0, 0, 0, 0),
(13, 'sugaruser1019', 0, 'Password', 'sugaruser@email.com', '', '', 0, 0, 0, 0),
(14, 'asdfasd', 0, '2312', 'asdf', 'fasdfasdf', 'asdf23231', 0, 0, 33399933, 91234),
(15, 'dar', 0, 'qwer', 'bad@maillcom', 'asd12', 'sd123', 0, 0, 444111292, 9319),
(16, 'Alba', 0, '123123', 'alba@mail.com', '123 S St', 'A212121', 0, 0, 1234567890, 91229),
(17, 'Test2', 0, '123123', 'test@mail.com', '123123', 'f123232', 0, 0, 115551111, 12343),
(18, 'johndoe', 0, '123qwe', 'johnd@rmail.com', '123 T St', '123123q', 0, 0, 1115551233, 95151),
(19, 'test9', 0, 'qwer', 'test9@mail.com', '123 S St', 'A1234567', 0, 0, 5555511, 12345),
(20, '56r65', 0, '76t76g76', 'gu6g7', '123ggg', 'g767676', 0, 0, 3333333, 7777),
(21, 'Ghad', 0, '123', 'ghad21@mail.com', '123 ad', '1923sdd', 0, 0, 49391, 9183),
(22, 'testuser9', 0, 'abdc123', 'testuser9@mail.com', '1234 D', 'CA', 0, 0, 555123123, 93191),
(23, 'NewUser', 0, '123', 'newuser@mail.com', '555 d st', '12345', 0, 0, 555, 95110),
(24, 'NewUser', 0, '123', 'newuser@mail.com', '555 d st', '12345', 0, 0, 555, 95110);

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
  MODIFY `id` int(16) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
