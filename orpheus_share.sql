-- phpMyAdmin SQL Dump
-- version 3.1.3
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 28, 2009 at 07:17 AM
-- Server version: 5.1.32
-- PHP Version: 5.2.9-1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `orpheus_share`
--

-- --------------------------------------------------------

--
-- Table structure for table `addfriends`
--

CREATE TABLE IF NOT EXISTS `addfriends` (
  `newfriend` varchar(30) NOT NULL,
  `friend` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `addfriends`
--

INSERT INTO `addfriends` (`newfriend`, `friend`) VALUES
('alex', 'trial'),
('ajith', 'alan');

-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE IF NOT EXISTS `friends` (
  `f1` varchar(30) NOT NULL,
  `f2` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `friends`
--

INSERT INTO `friends` (`f1`, `f2`) VALUES
('trial', 'jcub'),
('ajith', 'trial'),
('jcub', 'ajith'),
('alan', 'alex');

-- --------------------------------------------------------

--
-- Table structure for table `nowonline`
--

CREATE TABLE IF NOT EXISTS `nowonline` (
  `user` varchar(30) NOT NULL,
  `ip` varchar(14) NOT NULL,
  `status` varchar(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nowonline`
--

INSERT INTO `nowonline` (`user`, `ip`, `status`) VALUES
('alan', '', '0'),
('trial', '', '0'),
('ajith', '', '0'),
('jcub', '', '0'),
('alex', '', '0');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userid` int(5) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `gender` varchar(1) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `username`, `name`, `password`, `gender`) VALUES
(2, 'alan', 'alankoshy', '1', 'M'),
(4, 'trial', 'trial run', 'trial', 'F'),
(5, 'ajith', 'ajithkumar', '1', 'M'),
(7, 'jcub', 'jacobantony', '1', 'M'),
(9, 'alex', 'alexander', '1', 'M');
