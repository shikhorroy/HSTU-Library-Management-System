-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 03, 2017 at 12:55 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `lbms`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE IF NOT EXISTS `books` (
  `isbn` varchar(20) NOT NULL,
  `book name` varchar(50) NOT NULL,
  `author name` varchar(100) NOT NULL,
  `category` varchar(50) NOT NULL,
  `self no` int(11) NOT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`isbn`, `book name`, `author name`, `category`, `self no`) VALUES
('123-456-789', 'Matal Hawa', 'Mitali Chattergee', 'Story', 10),
('12312', 'Ekaras', 'Md. Zafar Iqbal', 'Science Fiction', 7),
('135520', 'Tukun Jeel', 'Md. Zafar Iqbal', 'Science Fiction', 7),
('14789', 'Competitive Programming', 'Felix Halim, Steven Halim', 'Programming', 18),
('1968', 'Java How to Programming', 'Deitel & Deitel', 'Programming', 18),
('45564', 'Sohoj Calculus', 'Md. Zafar Iqbal', 'Math', 50),
('654543', 'Ritin', 'Md. Zafar Iqbal', 'Science Fiction', 7),
('7988', 'Proramming Contest DS', 'Mahbubul Hasan Shanto', 'Programming', 18),
('897521', 'Compiler', 'M. Martin', 'Syllabus', 11),
('99-777-01', 'Data Communication and Networking', 'Forouzan', 'Networking', 13);

-- --------------------------------------------------------

--
-- Table structure for table `borrow`
--

CREATE TABLE IF NOT EXISTS `borrow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student id` varchar(20) NOT NULL,
  `book id` varchar(50) NOT NULL,
  `borrow date` date NOT NULL,
  `return date` date NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `borrow`
--

INSERT INTO `borrow` (`id`, `student id`, `book id`, `borrow date`, `return date`, `status`) VALUES
(1, '1202031', '12312', '2017-02-28', '2017-03-07', 1),
(2, '1202024', '12312', '2017-02-28', '2017-03-07', 1),
(3, '1202036', '135520', '2017-02-28', '2017-03-07', 1),
(4, '1202043', '123-456-789', '2017-02-28', '2017-03-07', 1),
(5, '1202031', '12312', '2017-03-01', '2017-03-08', 0),
(6, '1202031', '12312', '2017-03-01', '2017-03-08', 0),
(7, '1202031', '12312', '2017-03-01', '2017-03-08', 0),
(8, '1202031', '135520', '2017-03-02', '2017-03-09', 1),
(9, '1202031', '135520', '2017-03-02', '2017-03-09', 1),
(10, '1202031', '135520', '2017-03-02', '2017-03-09', 0),
(11, '1202031', '12312', '2017-03-02', '2017-03-09', 0);

-- --------------------------------------------------------

--
-- Table structure for table `returns`
--

CREATE TABLE IF NOT EXISTS `returns` (
  `id` int(11) NOT NULL,
  `student id` varchar(20) NOT NULL,
  `book id` varchar(50) NOT NULL,
  `return date` date NOT NULL,
  `fine` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `returns`
--

INSERT INTO `returns` (`id`, `student id`, `book id`, `return date`, `fine`) VALUES
(1, '1202031', '12312', '2017-03-02', 0),
(2, '1202024', '12312', '2017-03-02', 0),
(3, '1202036', '135520', '2017-03-02', 0),
(4, '1202043', '123-456-789', '2017-03-02', 0),
(8, '1202031', '135520', '2017-03-02', 0),
(9, '1202031', '135520', '2017-03-02', 0);

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE IF NOT EXISTS `students` (
  `Id` int(11) NOT NULL,
  `First Name` varchar(20) NOT NULL,
  `Last Name` varchar(20) NOT NULL,
  `Email` varchar(20) NOT NULL,
  `Phone No` varchar(20) NOT NULL,
  `Address` varchar(80) NOT NULL,
  `Gender` int(11) NOT NULL,
  `MS` int(11) NOT NULL,
  `Dept` varchar(15) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`Id`, `First Name`, `Last Name`, `Email`, `Phone No`, `Address`, `Gender`, `MS`, `Dept`) VALUES
(1002004, 'Harun-Or', 'Rasid', 'harun.cse@hstu.com', '017xxxx021x', 'Zia Hall, Hstu, Dinajpur.', 1, 2, 'CSE'),
(1102009, 'Udashi', 'Hawya', 'u.h@test.com', '01950230498', 'Rangpur', 2, 2, 'ECE'),
(1102014, 'Sathi', 'C.', 's.josh@test.com', '01721212212', 'Dinajpur', 2, 2, 'CSE'),
(1102015, 'Sumya', 'Akter', 'sumya.hstu@gmail.com', '0172XXXXXXX', 'Golapbag, Suihari, Dinajpur.', 2, 1, 'CSE'),
(1202002, 'Kamrul', 'Hasan', 'hasan@test.com', '017xxxxxxxx', 'Shuhari, Dinajpur', 1, 1, 'CSE'),
(1202007, 'Lubna', 'Juthy', 'juthy@test.com', '017xxxx58xx', 'Sadar, Dinajpur.', 2, 1, 'CSE'),
(1202009, 'Farhad', 'John', 'john@test.com', '017xx23154x', 'HSTU, Dinajpur.', 1, 2, 'CSE'),
(1202014, 'asfd', 'afsd', '', 'asfd', 'sda', 1, 1, 'asdfd'),
(1202024, 'Asha', 'Roy', 'rasha@roy.com', '017XXXXXXX9', 'HSTU, Dinajpur', 2, 2, 'CSE'),
(1202027, 'Nurul', 'Amin', 'amin@test.com', '017xxxxxxxxxx', '', 1, 2, 'CSE'),
(1202031, 'Shikhor', 'Roy', 'roy@asha.com', '01722xxxxxx', 'HSTU, Dinajpur.', 1, 2, 'CSE'),
(1202036, 'Rinku', 'Rajesh', 'rr@test.com', '01892525525', 'HSTU', 1, 2, 'CSE'),
(1202042, 'Titly', 'Deeba', 'titly@test.com', '017xxxxxxxxxx', 'Dinajpur', 2, 2, 'CSE'),
(1202043, 'Makha', 'Mim', 'mim@test.com', '012555', 'HSTU', 1, 2, 'CSE'),
(1202070, 'Lidam', 'Paul', 'lid.paul@gmail.com', '017XXXXXXXXX', 'Basherhat, Dinajpur.', 1, 2, 'ECE'),
(1202079, 'Mehadi', 'Hossain', 'mehadi@test.com', '0172315885xx', 'Station Road, Dinajpur.', 1, 2, 'ECE');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user name` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(20) NOT NULL,
  PRIMARY KEY (`user name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user name`, `password`, `email`) VALUES
('admin', 'a', 'admin@test.com'),
('admin1', 'a1', 'admin1@test.com'),
('admin2', 'a2', 'admin2@test.com');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `returns`
--
ALTER TABLE `returns`
  ADD CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `borrow` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
