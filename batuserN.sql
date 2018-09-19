-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.22-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for bioinformaticsdb
DROP DATABASE IF EXISTS `bioinformaticsdb`;
CREATE DATABASE IF NOT EXISTS `bioinformaticsdb` /*!40100 DEFAULT CHARACTER SET cp1250 */;
USE `bioinformaticsdb`;

-- Dumping structure for table bioinformaticsdb.interview_shedules
DROP TABLE IF EXISTS `interview_shedules`;
CREATE TABLE IF NOT EXISTS `interview_shedules` (
  `sheid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `request_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `Name` varchar(200) NOT NULL,
  `Number` varchar(20) NOT NULL DEFAULT '0',
  `EmailID` varchar(100) NOT NULL,
  `Address` varchar(500) DEFAULT NULL,
  `Qualification_Id` bigint(20) unsigned NOT NULL,
  `Interview_DateTime` datetime NOT NULL,
  `Result_Status_Id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`sheid`),
  KEY `FK_interview shedules_users` (`user_id`),
  KEY `FK_interview shedules_recruitment requests` (`request_id`),
  KEY `FK_interview_shedules_qualification` (`Qualification_Id`),
  KEY `FK_interview_shedules_result_status` (`Result_Status_Id`),
  CONSTRAINT `FK_interview shedules_recruitment requests` FOREIGN KEY (`request_id`) REFERENCES `recruitment_requests` (`reqid`),
  CONSTRAINT `FK_interview shedules_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`userid`),
  CONSTRAINT `FK_interview_shedules_qualification` FOREIGN KEY (`Qualification_Id`) REFERENCES `qualification` (`id`),
  CONSTRAINT `FK_interview_shedules_result_status` FOREIGN KEY (`Result_Status_Id`) REFERENCES `result_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf16 COMMENT='Shedule for interview';

-- Dumping data for table bioinformaticsdb.interview_shedules: ~3 rows (approximately)
DELETE FROM `interview_shedules`;
/*!40000 ALTER TABLE `interview_shedules` DISABLE KEYS */;
INSERT INTO `interview_shedules` (`sheid`, `request_id`, `user_id`, `Name`, `Number`, `EmailID`, `Address`, `Qualification_Id`, `Interview_DateTime`, `Result_Status_Id`) VALUES
	(1, 2, 1, 'Steve Jobs', '8090107040', 'stevejobs@gmail.com', 'california', 2, '2018-06-27 15:30:00', 2),
	(2, 1, 2, 'Dennis Ritche', '8090107041', 'dennis.ritche@gmail.com', 'Newyork', 1, '2018-06-25 14:00:00', 1),
	(3, 1, 1, 'Bill Gates', '8090107042', 'bill.gates@gmail.com', 'California', 2, '2018-06-27 17:00:00', 1);
/*!40000 ALTER TABLE `interview_shedules` ENABLE KEYS */;

-- Dumping structure for table bioinformaticsdb.qualification
DROP TABLE IF EXISTS `qualification`;
CREATE TABLE IF NOT EXISTS `qualification` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `Qualification` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=cp1250 COMMENT='user qualification';

-- Dumping data for table bioinformaticsdb.qualification: ~2 rows (approximately)
DELETE FROM `qualification`;
/*!40000 ALTER TABLE `qualification` DISABLE KEYS */;
INSERT INTO `qualification` (`id`, `Qualification`) VALUES
	(1, 'ME CSE'),
	(2, 'BE CSE');
/*!40000 ALTER TABLE `qualification` ENABLE KEYS */;

-- Dumping structure for table bioinformaticsdb.recruitment_requests
DROP TABLE IF EXISTS `recruitment_requests`;
CREATE TABLE IF NOT EXISTS `recruitment_requests` (
  `reqid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `Language` varchar(50) NOT NULL,
  `Years_Of_Experience` bigint(20) DEFAULT NULL,
  `Job_Desc` varchar(100) DEFAULT NULL,
  `Vacancies` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`reqid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf16 COMMENT='Recruitment Requests of new Candidates';

-- Dumping data for table bioinformaticsdb.recruitment_requests: ~3 rows (approximately)
DELETE FROM `recruitment_requests`;
/*!40000 ALTER TABLE `recruitment_requests` DISABLE KEYS */;
INSERT INTO `recruitment_requests` (`reqid`, `Language`, `Years_Of_Experience`, `Job_Desc`, `Vacancies`) VALUES
	(1, 'C#', 3, 'C#Expert', 3),
	(2, 'Java', 2, 'Java Spring Hibernate', 2),
	(3, 'Python', 2, 'Python Expert', 2);
/*!40000 ALTER TABLE `recruitment_requests` ENABLE KEYS */;

-- Dumping structure for table bioinformaticsdb.result_status
DROP TABLE IF EXISTS `result_status`;
CREATE TABLE IF NOT EXISTS `result_status` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `Status` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=cp1250 COMMENT='result_status';

-- Dumping data for table bioinformaticsdb.result_status: ~3 rows (approximately)
DELETE FROM `result_status`;
/*!40000 ALTER TABLE `result_status` DISABLE KEYS */;
INSERT INTO `result_status` (`id`, `Status`) VALUES
	(1, 'Pending'),
	(2, 'Selected'),
	(3, 'Rejected');
/*!40000 ALTER TABLE `result_status` ENABLE KEYS */;

-- Dumping structure for table bioinformaticsdb.selected_candidates
DROP TABLE IF EXISTS `selected_candidates`;
CREATE TABLE IF NOT EXISTS `selected_candidates` (
  `selid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `request_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `interview_id` bigint(20) unsigned NOT NULL,
  `joining_date` date NOT NULL,
  PRIMARY KEY (`selid`),
  KEY `FK_selected_candidates_recruitment_requests` (`request_id`),
  KEY `FK_selected_candidates_users` (`user_id`),
  KEY `FK_selected_candidates_interview_shedules` (`interview_id`),
  CONSTRAINT `FK_selected_candidates_interview_shedules` FOREIGN KEY (`interview_id`) REFERENCES `interview_shedules` (`sheid`),
  CONSTRAINT `FK_selected_candidates_recruitment_requests` FOREIGN KEY (`request_id`) REFERENCES `recruitment_requests` (`reqid`),
  CONSTRAINT `FK_selected_candidates_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf16 COMMENT='Selected Candidates';

-- Dumping data for table bioinformaticsdb.selected_candidates: ~1 rows (approximately)
DELETE FROM `selected_candidates`;
/*!40000 ALTER TABLE `selected_candidates` DISABLE KEYS */;
INSERT INTO `selected_candidates` (`selid`, `request_id`, `user_id`, `interview_id`, `joining_date`) VALUES
	(1, 1, 1, 1, '2018-06-27');
/*!40000 ALTER TABLE `selected_candidates` ENABLE KEYS */;

-- Dumping structure for table bioinformaticsdb.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `userid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(200) NOT NULL,
  `Number` varchar(20) NOT NULL,
  `Address` varchar(500) DEFAULT NULL,
  `EmailID` varchar(100) NOT NULL,
  `Qualification_ID` bigint(20) unsigned NOT NULL,
  `Designation` varchar(50) DEFAULT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Role_Id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`userid`),
  KEY `FK_users_qualification` (`Qualification_ID`),
  KEY `FK_users_user_role` (`Role_Id`),
  CONSTRAINT `FK_users_qualification` FOREIGN KEY (`Qualification_ID`) REFERENCES `qualification` (`id`),
  CONSTRAINT `FK_users_user_role` FOREIGN KEY (`Role_Id`) REFERENCES `user_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf16 COMMENT='BAT Employees';

-- Dumping data for table bioinformaticsdb.users: ~2 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`userid`, `Name`, `Number`, `Address`, `EmailID`, `Qualification_ID`, `Designation`, `Username`, `Password`, `Role_Id`) VALUES
	(1, 'Ajinkya Bhosale', '9028571192', 'Solapur', 'ajinkya.bhosale@bioanalytical.net', 1, 'Intern', 'abhosale', '04DbCrXN5BhxaK28dIhiQg==', 1),
	(2, 'Avanendra Basutkar', '0123456789', 'Pune', 'avanendra.basutkar@bioanalytical.net', 2, 'Manager', 'abasutkar', '04DbCrXN5BhxaK28dIhiQg==', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table bioinformaticsdb.user_role
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `Role` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf16;

-- Dumping data for table bioinformaticsdb.user_role: ~2 rows (approximately)
DELETE FROM `user_role`;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`id`, `Role`) VALUES
	(1, 'Admin'),
	(2, 'User');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
