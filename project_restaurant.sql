-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 16, 2021 lúc 04:06 PM
-- Phiên bản máy phục vụ: 10.4.21-MariaDB
-- Phiên bản PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `project_restaurant`
--
CREATE DATABASE IF NOT EXISTS `project_restaurant` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `project_restaurant`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `ID` int(11) NOT NULL,
  `Account` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`ID`, `Account`, `Password`, `Type`, `Status`) VALUES
(1, 'cuongpm', '123', 'Manager', 'Open'),
(2, 'thang', '1', 'Staff', 'Open'),
(14, 'haianh', '1', 'Staff', 'Open'),
(18, 'cuong', '1', 'Staff', 'Lock'),
(19, 'huy', '1', 'Staff', 'Open'),
(21, 'quanganh', '1', 'Staff', 'Open');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `ID` int(11) NOT NULL,
  `Customer` varchar(50) DEFAULT NULL,
  `Phone` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `meal`
--

CREATE TABLE `meal` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Price` float DEFAULT NULL,
  `Image` varchar(50) DEFAULT NULL,
  `MealCategoryID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `meal`
--

INSERT INTO `meal` (`ID`, `Name`, `Type`, `Price`, `Image`, `MealCategoryID`) VALUES
(17, 'Sandwich', 'Meal', 600, 'Breakfast-Sandwich-1-800x800.jpg', 18),
(18, 'Enchiladas', 'Meal', 600, 'Breakfast-Enchiladas-6-800x800.jpg', 18),
(19, 'Granola Bars', 'Meal', 300, 'Granola-Bars-6-800x800.jpg', 18),
(20, 'Lemon Bread', 'Meal', 400, 'Lemon-Bread-1-6-800x800.jpg', 18),
(21, 'Sheet Pan Chicken Tinga Bowls', 'Meal', 1000, 'Sheet-Pan-Chicken-Tinga-Prep-800x800.jpg', 10),
(22, 'Harissa Meatballs', 'Meal', 1000, 'Harissa-Meatballs-with-Whipped-Feta-3-800x800.jpg', 10),
(24, 'Spicy Chickpea Salad', 'Meal', 800, '5-Minute-Chickpea-Salad-1-800x800.jpg', 10),
(26, 'Back Pocket Stir Fry', 'Meal', 800, 'Back-Pocket-Stir-Fry-3-800x800.jpg', 11),
(27, 'Beet and Burrata Salad', 'Meal', 900, 'Best-Easy-Beef-and-Broccoli-800x800.jpg', 11),
(28, 'Detox Broccoli Cheese', 'Meal', 800, 'Detox-Broccoli-Cheese-Soup-2-2-800x800.jpg', 11),
(29, 'Cashew Crunch Salad', 'Meal', 800, 'Cashew-Crunch-Salad-3-800x800.jpg', 11),
(30, 'Crockpot Lentil Soup', 'Meal', 800, 'Crockpot-Lentil-Soup-3-Homepage-800x800.jpg', 11),
(31, 'Cake Truffles', 'Meal', 300, 'Cake-Truffles-6-800x800.jpg', 12),
(32, 'Chocolate Cake', 'Meal', 300, 'Chocolate-Cake-2-800x800.jpg', 12),
(33, 'Caramel Brownies', 'Meal', 400, 'Caramel-Brownies-Close-Up-183x183.jpg', 12),
(34, 'Berry Cheesecake', 'Meal', 400, 'Berry-Cheesecake-Muffins-1-2-800x800.jpg', 12),
(35, 'Absolut White Russian', 'Drink', 150, 'Absolut-White-Russian-1-800x800.jpg', 17),
(36, 'Agua Fresca', 'Drink', 150, 'Agua-Fresca-1-800x800.jpg', 17),
(37, 'Bulletproof Mocha', 'Drink', 200, 'Bulletproof-Mocha-5-Square-800x800.jpg', 15),
(38, 'Chia Overnight Oát', 'Drink', 200, 'Chia-Overnight-Oats-1-2-800x800.jpg', 17),
(39, 'Watermelon Smoothie', 'Drink', 200, 'Watermelon-Smoothie-4-800x800.jpg', 17),
(40, 'Grapefruit Mojito', 'Drink', 150, 'grapefruit-mojito-5-800x800.jpg', 17),
(41, 'Mango Kiwi Coolers', 'Drink', 150, 'Mango-Kiwi-Coolers-3-800x800.jpg', 17),
(42, 'Peppermint Mocha', 'Drink', 200, 'Peppermint-Mocha-3-800x800.jpg', 15),
(44, 'abc', 'Meal', 234, 'Avocado-Shrimp-Salad-800x800.jpg', 11),
(45, 'abc', 'Meal', 1234, 'Aloo-Tikki.jpeg', 11),
(46, 'abcd', 'Meal', 1234, '5-Ingredient-Spicy-Pork-1-2-800x800.jpg', 18);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `meal_category`
--

CREATE TABLE `meal_category` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `meal_category`
--

INSERT INTO `meal_category` (`ID`, `Name`, `Type`) VALUES
(10, 'Lunch', 'Meal'),
(11, 'Dinner', 'Meal'),
(12, 'Desserts', 'Meal'),
(15, 'Hot', 'Drink'),
(17, 'Cold', 'Drink'),
(18, 'Breakfast', 'Meal');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `oder`
--

CREATE TABLE `oder` (
  `ID` int(11) NOT NULL,
  `TableID` int(11) DEFAULT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `OrderStatus` varchar(50) DEFAULT NULL,
  `Total` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `oderdetail`
--

CREATE TABLE `oderdetail` (
  `ID` int(11) NOT NULL,
  `Number` int(11) DEFAULT NULL,
  `MealID` int(11) DEFAULT NULL,
  `OderID` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `table`
--

CREATE TABLE `table` (
  `ID` int(11) NOT NULL,
  `Floor` varchar(10) DEFAULT NULL,
  `TableNumber` varchar(10) DEFAULT NULL,
  `Status` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `table`
--

INSERT INTO `table` (`ID`, `Floor`, `TableNumber`, `Status`) VALUES
(19, '1F', '1', 'Empty'),
(20, '1F', '2', 'Empty'),
(21, '1F', '3', 'Empty'),
(22, '1F', '4', 'Empty'),
(23, '1F', '5', 'Empty'),
(24, '1F', '6', 'Empty'),
(25, '1F', '7', 'Empty'),
(26, '1F', '8', 'Empty'),
(27, '1F', '9', 'Empty'),
(28, '1F', '10', 'Empty'),
(29, '2F', '1', 'Empty'),
(30, '2F', '2', 'Empty'),
(31, '2F', '3', 'Empty'),
(32, '2F', '4', 'Empty'),
(33, '2F', '5', 'Empty'),
(34, '2F', '6', 'Empty'),
(35, '2F', '7', 'Empty'),
(36, '2F', '8', 'Empty'),
(37, '2F', '9', 'Empty');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `meal`
--
ALTER TABLE `meal`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `MealCategoryID` (`MealCategoryID`);

--
-- Chỉ mục cho bảng `meal_category`
--
ALTER TABLE `meal_category`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `oder`
--
ALTER TABLE `oder`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `TableID` (`TableID`),
  ADD KEY `CustomerID` (`CustomerID`);

--
-- Chỉ mục cho bảng `oderdetail`
--
ALTER TABLE `oderdetail`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `MealID` (`MealID`),
  ADD KEY `OderID` (`OderID`);

--
-- Chỉ mục cho bảng `table`
--
ALTER TABLE `table`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT cho bảng `customer`
--
ALTER TABLE `customer`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT cho bảng `meal`
--
ALTER TABLE `meal`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT cho bảng `meal_category`
--
ALTER TABLE `meal_category`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT cho bảng `oder`
--
ALTER TABLE `oder`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT cho bảng `oderdetail`
--
ALTER TABLE `oderdetail`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT cho bảng `table`
--
ALTER TABLE `table`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `meal`
--
ALTER TABLE `meal`
  ADD CONSTRAINT `meal_ibfk_1` FOREIGN KEY (`MealCategoryID`) REFERENCES `meal_category` (`ID`);

--
-- Các ràng buộc cho bảng `oder`
--
ALTER TABLE `oder`
  ADD CONSTRAINT `oder_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`ID`),
  ADD CONSTRAINT `oder_ibfk_2` FOREIGN KEY (`TableID`) REFERENCES `table` (`ID`);

--
-- Các ràng buộc cho bảng `oderdetail`
--
ALTER TABLE `oderdetail`
  ADD CONSTRAINT `oderdetail_ibfk_1` FOREIGN KEY (`MealID`) REFERENCES `meal` (`ID`),
  ADD CONSTRAINT `oderdetail_ibfk_2` FOREIGN KEY (`OderID`) REFERENCES `oder` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
