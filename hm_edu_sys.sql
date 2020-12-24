-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- 主机： mysql
-- 生成日期： 2020-12-24 09:42:08
-- 服务器版本： 8.0.21
-- PHP 版本： 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `hm_edu_sys`
--

-- --------------------------------------------------------

--
-- 表的结构 `class`
--

CREATE TABLE `class` (
  `id` int NOT NULL,
  `major_id` int NOT NULL,
  `student_num` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `director` varchar(50) NOT NULL,
  `party_secretary` varchar(50) NOT NULL,
  `college_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `class`
--

INSERT INTO `class` (`id`, `major_id`, `student_num`, `name`, `director`, `party_secretary`, `college_id`) VALUES
(2, 19, 123, '软件1804班', '赵班长', 'XXX', 57),
(3, 3, 123, '软件1804班', '赵例', '无私', 1),
(4, 19, 123, '软件1804班', '赵武', '临沂', 57),
(5, 3, 33, '计科1804班', '刘武', '临沂', 1),
(6, 19, 33, '计科1801班', '赵柳', '123', 1),
(7, 3, 33, '软件1805班', '赵利剑', '深雪', 1);

-- --------------------------------------------------------

--
-- 表的结构 `club`
--

CREATE TABLE `club` (
  `id` int NOT NULL,
  `director` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `college_id` int NOT NULL,
  `student_num` int NOT NULL,
  `description` varchar(50) NOT NULL,
  `phone` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `club`
--

INSERT INTO `club` (`id`, `director`, `name`, `college_id`, `student_num`, `description`, `phone`) VALUES
(2, '赵三', '动漫社', 39, 330, '加入去', '13689150936'),
(3, '赵三', '自律社', 1, 330, '冲冲冲', '13689150936');

-- --------------------------------------------------------

--
-- 表的结构 `college`
--

CREATE TABLE `college` (
  `id` int NOT NULL,
  `name` varchar(30) NOT NULL,
  `location` varchar(100) NOT NULL,
  `major_num` int NOT NULL COMMENT '专业数目',
  `president` varchar(50) NOT NULL COMMENT '院长',
  `phone` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `college`
--

INSERT INTO `college` (`id`, `name`, `location`, `major_num`, `president`, `phone`) VALUES
(1, '计算机学院', '长安区西区', 5, '余部长', '15533052683'),
(39, '理学院', '长安区东区', 8, '理院长', '15533052684'),
(40, '自动化学院', '长安区东区', 1, '张院长', '15533052684'),
(41, '1', '长安区东区', 1, '1', '15533052684'),
(42, '234', '长安区东区', 1, '1', '15533052684'),
(43, '234', '长安区东区', 1, '1', '15533052684'),
(44, '234', '长安区东区', 1, '1', '15533052684'),
(57, 'X学院', '雁塔区', 10, 'XXX', '15533052683'),
(58, '张军学院', '长安区东区', 11, '111', '15533052684'),
(61, '余龙', '长安区东区', 233, '余龙', '15533052684'),
(62, '余龙', '长安区东区', 2, '余龙', '15533052684'),
(63, '余龙学院', '雁塔区', 3, '张军', '15533052683');

-- --------------------------------------------------------

--
-- 表的结构 `course`
--

CREATE TABLE `course` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `college_id` int NOT NULL,
  `class_hour` int NOT NULL,
  `score` int NOT NULL,
  `required` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `course`
--

INSERT INTO `course` (`id`, `name`, `college_id`, `class_hour`, `score`, `required`) VALUES
(1, '英语', 1, 2, 2, 0),
(2, '数学', 39, 2, 2, 0),
(3, '移动应用开发', 1, 1, 1, 0),
(4, '软件体系结构', 1, 2, 1, 1),
(5, '软件体系结构', 1, 2, 1, 1),
(6, '软件体系结构', 1, 2, 1, 0),
(7, '软件体系结构', 1, 2, 1, 1),
(8, '软件体系结构', 1, 2, 1, 1),
(9, '软件体系结构', 1, 2, 1, 1),
(10, '软件体系结构', 1, 2, 1, 1),
(11, 'JW', 1, 2, 2, 1),
(12, 'JW', 1, 2, 2, 1),
(13, 'shud', 40, 2, 2, 1);

-- --------------------------------------------------------

--
-- 表的结构 `course_to_student`
--

CREATE TABLE `course_to_student` (
  `id` int NOT NULL,
  `student_id` int NOT NULL,
  `course_to_teacher_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `course_to_student`
--

INSERT INTO `course_to_student` (`id`, `student_id`, `course_to_teacher_id`) VALUES
(5, 28, 4),
(6, 28, 2),
(7, 28, 2),
(8, 28, 10),
(9, 29, 2),
(10, 29, 10),
(12, 23, 2),
(13, 29, 4),
(14, 29, 12),
(15, 1, 2),
(16, 1, 4),
(17, 29, 2),
(18, 29, 6);

-- --------------------------------------------------------

--
-- 表的结构 `course_to_teacher`
--

CREATE TABLE `course_to_teacher` (
  `id` int NOT NULL,
  `teacher_id` int NOT NULL,
  `course_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `course_to_teacher`
--

INSERT INTO `course_to_teacher` (`id`, `teacher_id`, `course_id`) VALUES
(2, 1, 2),
(4, 1, 3),
(5, 1, 4),
(6, 1, 3),
(7, 1, 4),
(8, 1, 5),
(10, 3, 1),
(11, 4, 1),
(12, 5, 1),
(13, 1, 1),
(15, 3, 1),
(16, 4, 1),
(17, 5, 1),
(18, 6, 1),
(21, 2, 1),
(22, 4, 1),
(23, 6, 1),
(24, 3, 1),
(25, 4, 1);

-- --------------------------------------------------------

--
-- 表的结构 `dormitory`
--

CREATE TABLE `dormitory` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `building` varchar(50) NOT NULL,
  `room` int NOT NULL,
  `number` int NOT NULL,
  `director` varchar(50) NOT NULL,
  `member` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `dormitory`
--

INSERT INTO `dormitory` (`id`, `name`, `building`, `room`, `number`, `director`, `member`) VALUES
(1, '圣诞小屋', '安悦公寓北楼', 2613, 6, '周冬阳', '周冬阳  张军  高星坤  康文哲  余龙  朱晨光'),
(2, '雪中小屋', '安悦公寓北楼', 2613, 6, '余龙', '周冬阳  张军  高星坤  康文哲  余龙  朱晨光'),
(3, '雪中小屋', '安悦公寓北楼', 2613, 6, '余龙', '周冬阳  张军  高星坤  康文哲  余龙  朱晨光'),
(4, '圣诞小屋', '安悦公寓北楼', 2613, 6, '余龙', '周冬阳  张军  高星坤  康文哲  余龙  朱晨光'),
(5, '国色天香', '安美公寓南楼', 2511, 6, '优衣', '优衣 可可萝 凯露 佩可 珠希 怜'),
(6, '圣诞大物', '安悦公寓南楼', 2611, 6, '张三', '张三 张三 张三 张三 张三 张四'),
(12, '雪舞', '按月被', 26101, 6, '李四', '李四 李四 李四 李四 李四 李四'),
(13, '雪舞', '按月被', 26101, 6, '李四', '李四 李四 李四 李四 李四 李四'),
(18, '2615', '安悦公寓北楼', 2615, 6, '吴力', '吴力 吴力 吴力 吴力 吴力 吴力');

-- --------------------------------------------------------

--
-- 表的结构 `grade`
--

CREATE TABLE `grade` (
  `id` int NOT NULL,
  `grade` int NOT NULL,
  `course_id` int NOT NULL,
  `student_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `grade`
--

INSERT INTO `grade` (`id`, `grade`, `course_id`, `student_id`) VALUES
(3, 50, 2, 29),
(4, 99, 2, 23),
(5, 90, 3, 29);

-- --------------------------------------------------------

--
-- 表的结构 `major`
--

CREATE TABLE `major` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `college_id` int NOT NULL,
  `number` int NOT NULL,
  `director` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `major`
--

INSERT INTO `major` (`id`, `name`, `college_id`, `number`, `director`) VALUES
(3, '软件工程', 1, 205, '张飞'),
(19, '计算机科学与技术', 1, 852, '百搭'),
(20, '物理', 39, 420, '石飞');

-- --------------------------------------------------------

--
-- 表的结构 `manager`
--

CREATE TABLE `manager` (
  `id` int NOT NULL,
  `username` varchar(11) NOT NULL,
  `password` varchar(30) NOT NULL,
  `sex` char(1) NOT NULL,
  `college_id` int NOT NULL,
  `avatar_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像url',
  `email` varchar(100) NOT NULL,
  `create_time` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `manager`
--

INSERT INTO `manager` (`id`, `username`, `password`, `sex`, `college_id`, `avatar_url`, `email`, `create_time`) VALUES
(1, 'sky', '123456', 'm', 39, '123', '123@test.com', '2020-11-03'),
(14, 'admin', '123456', 'f', 1, NULL, '123@test.com', '2020-12-09'),
(15, '张狗军', '123456', 'm', 40, NULL, '123@test.com', '2020-12-11'),
(16, '高星坤', '123456', 'f', 1, NULL, '123@test.com', '2020-12-11'),
(17, '田七', '123456', 'f', 39, NULL, '123@test.com', '2020-12-20'),
(18, '王五', '123456', 'm', 40, NULL, '123@test.com', '2020-12-20'),
(19, '余龙', '123456', 'm', 1, NULL, '123@test.com', '2020-12-20'),
(20, '张钰', '123456', 'm', 39, NULL, '123@test.com', '2020-12-23');

-- --------------------------------------------------------

--
-- 表的结构 `role`
--

CREATE TABLE `role` (
  `id` int NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` enum('manager','student','teacher') CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `old_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `role`
--

INSERT INTO `role` (`id`, `username`, `password`, `role`, `old_id`) VALUES
(1, 'admin', '123456', 'manager', 14),
(2, 'sky', '123456', 'manager', 1),
(3, '高老师', '123456', 'teacher', 1),
(4, '123', '123456', 'student', 29),
(5, '余龙', '123456', 'manager', 19),
(6, '学生张', '123456', 'student', 30),
(7, '李老师', '123456', 'teacher', 9),
(8, '张钰', '123456', 'manager', 20),
(9, 'root', '123456', 'manager', 21);

-- --------------------------------------------------------

--
-- 表的结构 `student`
--

CREATE TABLE `student` (
  `id` int NOT NULL,
  `college_id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `sex` tinyint(1) NOT NULL,
  `id_card` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `student`
--

INSERT INTO `student` (`id`, `college_id`, `name`, `phone`, `email`, `sex`, `id_card`) VALUES
(11, 39, 'XXX', '15530052684', '10050352666@qq.com', 0, '123'),
(13, 1, 'XXX', '15533052683', '103@test.com', 0, '111'),
(15, 1, '111', '123599', '29344m', 1, '13112'),
(16, 1, '111', '123599', '29344m', 1, '131'),
(17, 1, '111', '123599', '29344m', 1, '123'),
(19, 1, 'ZZZ', '15533052683', '103@test.com', 1, '111'),
(20, 39, 'ZZZ', '15533052683', '103@test.com', 0, '13'),
(21, 1, 'ZZZ', '15533052683', '103@test.com', 1, '130481'),
(22, 1, 'ZZZ', '15533052683', '103@test.com', 1, '1304812000'),
(23, 1, '李四', '15530052684', '10050352666@qq.com', 1, '123'),
(24, 1, '李四', '15530052684', '10050352666@qq.com', 1, '61240'),
(25, 1, '张三', '15533052683', '2934423206@qq.com', 1, '112'),
(26, 1, '张三2', '15533052683', '2934423206@qq.com', 1, '112'),
(27, 1, '张三2', '15533052683', '2934423206@qq.com', 0, '112'),
(29, 1, '123', '13689150936', '1755052303@qq.com', 1, '612401200011033431'),
(30, 1, '学生张', '15533052683', '123@test.com', 0, '11111111');

-- --------------------------------------------------------

--
-- 表的结构 `teacher`
--

CREATE TABLE `teacher` (
  `id` int NOT NULL,
  `college_id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `sex` tinyint(1) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `address` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `teacher`
--

INSERT INTO `teacher` (`id`, `college_id`, `name`, `sex`, `email`, `phone`, `address`) VALUES
(1, 63, '高老师', 0, '103@test.com', '15533052683', '西安'),
(9, 39, '李老师', 1, '123@test.com', '15533052683', '西安邮电大学');

--
-- 转储表的索引
--

--
-- 表的索引 `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`id`),
  ADD KEY `college_id` (`college_id`),
  ADD KEY `major_id` (`major_id`);

--
-- 表的索引 `club`
--
ALTER TABLE `club`
  ADD PRIMARY KEY (`id`),
  ADD KEY `college_id` (`college_id`);

--
-- 表的索引 `college`
--
ALTER TABLE `college`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`),
  ADD KEY `college_id` (`college_id`);

--
-- 表的索引 `course_to_student`
--
ALTER TABLE `course_to_student`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `course_to_teacher`
--
ALTER TABLE `course_to_teacher`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `dormitory`
--
ALTER TABLE `dormitory`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `major`
--
ALTER TABLE `major`
  ADD PRIMARY KEY (`id`),
  ADD KEY `college_id` (`college_id`);

--
-- 表的索引 `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `college_id` (`college_id`);

--
-- 表的索引 `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `college_id` (`college_id`);

--
-- 表的索引 `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`),
  ADD KEY `college_id` (`college_id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `class`
--
ALTER TABLE `class`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- 使用表AUTO_INCREMENT `club`
--
ALTER TABLE `club`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 使用表AUTO_INCREMENT `college`
--
ALTER TABLE `college`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- 使用表AUTO_INCREMENT `course`
--
ALTER TABLE `course`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- 使用表AUTO_INCREMENT `course_to_student`
--
ALTER TABLE `course_to_student`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- 使用表AUTO_INCREMENT `course_to_teacher`
--
ALTER TABLE `course_to_teacher`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- 使用表AUTO_INCREMENT `dormitory`
--
ALTER TABLE `dormitory`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- 使用表AUTO_INCREMENT `grade`
--
ALTER TABLE `grade`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- 使用表AUTO_INCREMENT `major`
--
ALTER TABLE `major`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- 使用表AUTO_INCREMENT `manager`
--
ALTER TABLE `manager`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- 使用表AUTO_INCREMENT `role`
--
ALTER TABLE `role`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- 使用表AUTO_INCREMENT `student`
--
ALTER TABLE `student`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- 使用表AUTO_INCREMENT `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
