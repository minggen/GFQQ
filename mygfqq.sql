-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2017-06-15 14:00:28
-- 服务器版本： 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mygfqq`
--

-- --------------------------------------------------------

--
-- 表的结构 `fqq_category`
--

CREATE TABLE `fqq_category` (
  `id` int(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `owner_id` varchar(10) NOT NULL,
  `category_type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `fqq_category`
--

INSERT INTO `fqq_category` (`id`, `name`, `owner_id`, `category_type`) VALUES
(1, '❀ 少女念南街', '1', '1'),
(3, '我的好友', '3', '1'),
(4, '❀ 少年痴北城', '1', '1'),
(5, '我的好友', '4', '1'),
(6, '❀ 睡于麋鹿林', '1', '1'),
(7, '❀ 醒在栀篮摇', '1', '1'),
(8, '❀ 树深时见鹿', '1', '1'),
(9, '❀ 梧桐谁在路', '1', '1'),
(10, '❀ 无人街七号', '1', '1'),
(0, '我的好友', '5', '1'),
(11, '╪———————', '2', '1'),
(12, '_____带着我的长安', '2', '1'),
(13, '_____误入你的江南', '2', '1'),
(14, '゛许我半生烟火 -', '2', '1'),
(15, '゛换你一世笑脸 -', '2', '1'),
(16, '┇ ╭╭╮╮┇', '2', '1'),
(17, '┇  ╲╳╱  ┇ -', '2', '1'),
(18, '_____靠近你的河岸', '2', '1'),
(19, '_____乘着我的小船', '2', '1'),
(20, '゛随我半生漂泊 - -', '2', '1'),
(21, '゛换你一世期盼 -', '2', '1'),
(22, '———————╪', '2', '1');

-- --------------------------------------------------------

--
-- 表的结构 `fqq_category_member`
--

CREATE TABLE `fqq_category_member` (
  `id` int(10) NOT NULL,
  `category_name` varchar(20) NOT NULL,
  `owner_id` int(10) NOT NULL,
  `member_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `fqq_category_member`
--

INSERT INTO `fqq_category_member` (`id`, `category_name`, `owner_id`, `member_id`) VALUES
(1, '我的好友', 1, 2),
(7, '我的好友', 2, 3),
(3, '我的好友', 1, 3),
(4, '我的好友', 1, 4),
(5, '我的好友', 1, 5),
(8, '我的好友', 4, 1),
(2, '我的好友', 4, 2),
(6, '我的好友', 4, 3),
(9, '我的好友', 2, 1),
(10, '我的好友', 2, 3),
(11, '我的好友', 2, 4),
(12, '我的好友', 3, 1),
(13, '我的好友', 3, 2),
(14, '我的好友', 3, 4),
(15, '❀ 少女念南街', 1, 2),
(16, '╪———————', 2, 1),
(17, '┇ ╭╭╮╮┇', 2, 3);

-- --------------------------------------------------------

--
-- 表的结构 `fqq_user`
--

CREATE TABLE `fqq_user` (
  `nick_name` varchar(50) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `user_signature` varchar(100) NOT NULL,
  `id` int(10) NOT NULL,
  `user_pic` varchar(50) NOT NULL DEFAULT 'tx.gif'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `fqq_user`
--

INSERT INTO `fqq_user` (`nick_name`, `user_name`, `user_password`, `user_signature`, `id`, `user_pic`) VALUES
('张小凡', '1', '1', '天地不仁以万物为刍狗', 0, 'tx.png'),
('碧瑶', '2', '2', '她很懂事', 1, 'tx.png'),
('韩硕', '3', '3', '情深不寿', 0, 'touxiang.png'),
('小六', '4', '4', '此恨绵绵无绝期', 0, 'tx.png'),
('冷公子', '5', '5', '55555', 6, 'touxiang.jpg');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
