-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.9 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for usersdb
CREATE DATABASE IF NOT EXISTS `usersdb` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `usersdb`;


-- Dumping structure for table usersdb.guestbook
CREATE TABLE IF NOT EXISTS `guestbook` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `message` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='guestbook';

-- Dumping data for table usersdb.guestbook: ~11 rows (approximately)
/*!40000 ALTER TABLE `guestbook` DISABLE KEYS */;
INSERT IGNORE INTO `guestbook` (`id`, `name`, `message`) VALUES
	(1, 'John', 'Pettruci sdfdf sdfd  '),
	(17, 'Jack', 'Hello world now'),
	(19, 'Jim', 'Hi Jim'),
	(41, 'Jxx', 'Hi Jack 123 sadfsdf'),
	(42, 'Joey', 'Hi Jim 456'),
	(50, 'John 12', 'Pettruci dream tea ther 123456  '),
	(61, 'Test', 'again'),
	(63, 'sdf232', 'ทดสอบ สมุดฝากข้อความครับ'),
	(70, 'ทดสอบ', 'ภาษาไทย'),
	(72, 'sdfasd', 'sadfadfsdf dfddf0fdsdf'),
	(73, 'xx13', 'tes t again');
/*!40000 ALTER TABLE `guestbook` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
