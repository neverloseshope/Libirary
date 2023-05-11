/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.5.39 : Database - library
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`library` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `library`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `number` int(11) DEFAULT '0',
  `place` varchar(20) DEFAULT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `book_fk1` (`pid`),
  CONSTRAINT `book_fk1` FOREIGN KEY (`pid`) REFERENCES `publisher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`id`,`name`,`number`,`place`,`pid`) values 
(1,'java',15,'A3',1),
(2,'c',10,'B3',2),
(3,'c++',20,'C2',3),
(4,'c#',15,'D5',1),
(5,'python',15,'D5',2),
(6,'php',15,'D3',4),
(7,'软件工程',10,'D4',6),
(8,'面向对象方法',5,'E1',3),
(9,'软件工程与项目管理',10,'A2',11),
(10,'Linux网络操作系统',7,'E2',6),
(11,'Linux宝典第二版',8,'E2',1),
(12,'软件测试实践',12,'F1',12),
(13,'软件安全测试艺术',13,'F2',13),
(14,'嵌入式软件测试',11,'F3',9),
(15,'软件技术经典教程',7,'G2',8);

/*Table structure for table `borrower` */

DROP TABLE IF EXISTS `borrower`;

CREATE TABLE `borrower` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `borrower` */

insert  into `borrower`(`id`,`name`,`phone`) values 
(1,'张三','15386427788'),
(2,'李四','15386428899'),
(3,'王五','15186447788'),
(4,'赵六','18244567789'),
(5,'陈晨','15611232234'),
(6,'李代平','19133454456'),
(7,'王素芬','13145677654'),
(8,'杨卫东','13178900987'),
(9,'刘竹林','17343522345'),
(10,'张权范','13723400536'),
(11,'陈巧莉','19323456547'),
(12,'赵池龙','13426023566'),
(13,'韩万江','13244547896'),
(14,'成奋华','15256475686');

/*Table structure for table `borrower_book` */

DROP TABLE IF EXISTS `borrower_book`;

CREATE TABLE `borrower_book` (
  `bbId` int(11) NOT NULL AUTO_INCREMENT,
  `borrowerId` int(11) NOT NULL,
  `bookId` int(11) NOT NULL,
  `borrowDate` date DEFAULT NULL,
  `returnDate` date DEFAULT NULL,
  PRIMARY KEY (`bbId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `borrower_book` */

insert  into `borrower_book`(`bbId`,`borrowerId`,`bookId`,`borrowDate`,`returnDate`) values 
(1,1,2,'2022-02-02','2022-03-02'),
(2,1,5,'2022-03-30','2022-05-06'),
(3,2,2,'2022-02-03','2022-03-06'),
(4,13,3,'2022-02-03','2022-03-06'),
(5,3,4,'2022-02-22','2022-05-16'),
(6,12,5,'2022-06-18','2022-06-27'),
(7,5,12,'2022-06-18','2022-06-28'),
(8,7,8,'2022-06-25','2022-07-10'),
(9,6,9,'2022-06-24','2022-07-08'),
(10,9,10,'2022-06-22','2022-07-05'),
(11,10,6,'2022-06-09','2022-07-19');

/*Table structure for table `publisher` */

DROP TABLE IF EXISTS `publisher`;

CREATE TABLE `publisher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `publisher` */

insert  into `publisher`(`id`,`name`,`phone`,`email`,`address`) values 
(1,'清华大学出版社','88880088','qh@tsinghua.edu.cn','北京'),
(2,'北京大学出版社','88666666','bj@beijing.edu.cn','北京'),
(3,'高等教育出版社','88884444','gd@jiaoyu.edu.cn','北京'),
(4,'湖南人民出版社','88228822','hn@renmin.edu.cn','湖南'),
(5,'北京交通大学出版社','88648581','bjjt@bj.com','北京'),
(6,'大连理工大学出版社','88022009','dllg@ln.com','辽宁'),
(7,'西安电子科技大学出版社','88887777','xadzkj@xa.com','西安'),
(8,'人民邮电出版社','88996644','rmyd@bj.com','北京'),
(9,'北京师范大学出版社','88993333','bjsf@bj.com','北京'),
(10,'西南师范大学出版社','88009900','xnsf@cq.com','重庆'),
(11,'国防科技大学出版社','88221111','gfkj@hn.com','长沙'),
(12,'电子工业出版社','88992323','dzgy@bj.com','北京'),
(13,'机械工业出版社','88994554','jxgy@bj.com','北京');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
