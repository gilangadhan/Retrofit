-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 23, 2017 at 04:29 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_android`
--
CREATE DATABASE IF NOT EXISTS `db_android` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `db_android`;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_pohon`
--

CREATE TABLE `tbl_pohon` (
  `id` int(11) NOT NULL,
  `uuid` varchar(50) NOT NULL,
  `tgl` date NOT NULL,
  `jenis_pohon` varchar(40) NOT NULL,
  `usia_pohon` int(11) NOT NULL,
  `kondisi_pohon` enum('sehat','cukup','keropos','mati') NOT NULL,
  `latitude` varchar(40) NOT NULL,
  `longitude` varchar(40) NOT NULL,
  `foto_pohon` varchar(40) NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_pohon`
--

INSERT INTO `tbl_pohon` (`id`, `uuid`, `tgl`, `jenis_pohon`, `usia_pohon`, `kondisi_pohon`, `latitude`, `longitude`, `foto_pohon`, `keterangan`) VALUES
(2, '591bad515cb907.65200855', '2017-05-23', 'Mantabs', 31, 'sehat', '7.0589104', '110.4421442', 'foto.png', 'Sehat Banget udah umur segini'),
(3, '591bad515cb907.65200855', '2017-05-03', 'dasd', 31, 'sehat', '214124', '12412', 'fasfa', 'asfasf'),
(0, '?', '0000-00-00', '?', 0, '', '?', '?', '?', '?'),
(40, '591bad515cb907.65200855', '2017-05-23', 'keren', 30, 'sehat', '1000', '1000', 'nganu.png', 'oke');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL,
  `unique_id` varchar(23) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `encrypted_password` varchar(80) NOT NULL,
  `salt` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`id`, `unique_id`, `nama`, `email`, `encrypted_password`, `salt`) VALUES
(1, '591bad515cb907.65200855', 'gilang', 'gilang@gmail.com', '0v293fwN6YyT2SvvtGgA0xB1cW1hYTg3ZDYxOGY5', 'aa87d618f9'),
(2, '591be593ab8023.01027942', 'gilang', 'gilang_adhan@gmail.com', 'dyn03C3eB8MoQLqZ6gwCGSzW8PpmOTJhMTgxMWYw', 'f92a1811f0'),
(3, '591be8420259c5.29322400', 'Faizal Caem', 'faizal@gmail.com', 'TboOn/6e9rONw2EORCqUXz8SwnI3ZjUwOTMzY2Vl', '7f50933cee'),
(4, '591be863357128.45595769', 'thursina CANTEK', 'thursina@gmail.com', 'OccGcdhWgXyztonwF1kAy8HrfZNhMDcxZjNjZjkw', 'a071f3cf90');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_id` (`unique_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
