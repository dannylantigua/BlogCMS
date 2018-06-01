DROP DATABASE IF EXISTS BlogCMS;

CREATE DATABASE BlogCMS;

USE BlogCMS;

CREATE TABLE Entity(
recordId INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
FirstName VARCHAR(20),
LastName VARCHAR(20),
EMAIL VARCHAR(25),
PhoneNumber VARCHAR(10),
AboutMe TEXT,
UserName VARCHAR(30),
passwd VARCHAR(50),
isAdmin boolean,
`enabled` tinyint(1) NOT NULL,
 KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

CREATE TABLE EntitySocialProfiles(
EntityId INT,
WebName VARCHAR(20),
Website VARCHAR(100),
FOREIGN KEY (EntityId) REFERENCES Entity(recordId)
);

CREATE TABLE Categories(
recordId INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
CategoryName VARCHAR(20)
);

CREATE TABLE Posts(
recordId INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
postTittle TEXT,
postBody TEXT,
userId INT,
postDate DateTime,
expireDate DateTime,
likes INT,
isPending boolean,
isApproved boolean,
isRejected boolean,
FOREIGN KEY (userId) REFERENCES Categories (recordId) ON DELETE CASCADE
);

CREATE TABLE PostsTags(
postId INT,
Tag VARCHAR(30),
FOREIGN KEY (postId) REFERENCES Posts (recordId) ON DELETE CASCADE
);

CREATE TABLE StaticPages(
recordId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
PageName Varchar(20),
Content TEXT
);

CREATE TABLE IF NOT EXISTS `authorities` (
`UserName` varchar(20) NOT NULL,
`authority` varchar(20) NOT NULL,
KEY `UserName` (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `authorities`
 ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`UserName`) REFERENCES `Entity` (`UserName`) ON DELETE CASCADE;
 
--
-- Dumping data for table `users`
--
INSERT INTO `Entity` (`recordId`, `UserName`, `passwd`, `enabled`) VALUES
(1, 'admin', 'password', 1),
(2, 'user', 'password', 1);
--
-- Table structure for table `authorities`
-- 
--
-- Dumping data for table `authorities`
--
INSERT INTO `authorities` (`UserName`, `authority`) VALUES
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_USER'),
('user', 'ROLE_USER');
