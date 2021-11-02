-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 02, 2021 at 01:50 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `appfood`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` int(10) NOT NULL,
  `madonhang` int(10) NOT NULL,
  `mamon` int(10) NOT NULL,
  `tenmon` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `gia` double NOT NULL,
  `soluong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`id`, `madonhang`, `mamon`, `tenmon`, `gia`, `soluong`) VALUES
(12, 29, 10, 'Bún bò Huế chay', 25000, 2),
(13, 29, 4, 'Cơm tấm đặc biệt', 35000, 11),
(14, 30, 7, 'Nước ép cam', 15000, 1),
(15, 31, 9, 'Cơm gà xối mỡ', 30000, 1),
(16, 32, 2, 'Cơm tấm sườn trứng', 27000, 3),
(17, 32, 7, 'Nước ép cam', 15000, 1),
(18, 33, 8, 'Nước ép chanh', 15000, 1),
(19, 34, 5, 'Khoai tây chiên', 15000, 184);

-- --------------------------------------------------------

--
-- Table structure for table `danhmuc`
--

CREATE TABLE `danhmuc` (
  `id` int(10) NOT NULL,
  `tendanhmuc` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `hinhdanhmuc` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `danhmuc`
--

INSERT INTO `danhmuc` (`id`, `tendanhmuc`, `hinhdanhmuc`) VALUES
(1, 'Món chính', 'https://meatboxvietnam.com/wp-content/uploads/2021/04/165286230_2842665565956321_8014338361105436241_n.jpg'),
(2, 'Ăn vặt', 'https://cdn.daotaobeptruong.vn/wp-content/uploads/2020/11/do-an-vat-han-quoc.jpg'),
(3, 'Đồ uống', 'https://blog.beemart.vn/wp-content/uploads/2017/05/cac-loai-do-uong-hot-nhat-mua-he-1234.jpg'),
(4, 'Món chay', 'https://cdn.daotaobeptruong.vn/wp-content/uploads/2019/10/canh-nam-hat-sen-mon-chay-ngon-de-lam.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `donhang`
--

CREATE TABLE `donhang` (
  `id` int(10) NOT NULL,
  `tenkhachhang` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `sodienthoai` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `tongtien` double NOT NULL,
  `ghichu` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `donhang`
--

INSERT INTO `donhang` (`id`, `tenkhachhang`, `email`, `sodienthoai`, `tongtien`, `ghichu`) VALUES
(29, 'nam', 'nam@gmail.com', '337662557', 435000, ''),
(30, 'nam', 'nam@gmail.com', '337662557', 15000, ''),
(31, 'nam', 'tnnhatnam210@gmail.com', '337662557', 30000, ''),
(32, 'nam', 'nam@example.com', '337662557', 96000, 'nước cam ít đá ít đường nha bếp ơi :3'),
(33, 'nam', 'tnnhatnam210@gmail.com', '337662557', 15000, 'nhanh lên !! '),
(34, 'anh', 'anh@gmail.com', '5555555', 2760000, 'ít cay nha anh bếp :3');

-- --------------------------------------------------------

--
-- Table structure for table `mon`
--

CREATE TABLE `mon` (
  `id` int(10) NOT NULL,
  `madanhmuc` int(10) NOT NULL,
  `tenmon` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `hinhmon` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `gia` double NOT NULL,
  `mota` varchar(1000) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `mon`
--

INSERT INTO `mon` (`id`, `madanhmuc`, `tenmon`, `hinhmon`, `gia`, `mota`) VALUES
(1, 1, 'Cơm tấm sườn', 'https://img-global.cpcdn.com/recipes/49876fe80303b991/680x482cq70/c%C6%A1m-t%E1%BA%A5m-s%C6%B0%E1%BB%9Dn-recipe-main-photo.jpg', 25000, 'Cơm tấm sườn siu ngon có cơm và miếng sườn.'),
(2, 1, 'Cơm tấm sườn trứng', 'https://channel.mediacdn.vn/thumb_w/640/prupload/879/2018/10/img20181031164337363.jpg', 27000, 'Cơm tấm có cơm, miếng sườn và có thêm miếng trứng.'),
(3, 1, 'Cơm tấm sườn bì chả', 'https://pastaxi-manager.onepas.vn/content/uploads/articles/vuonghoai/cach-lam-com-tam-suon-bi-cha/cach-lam-com-tam-suon-bi-cha-1.jpg', 32000, 'Cơm tấm có cơm, miếng sường, cộng thêm bì và miếng chả siu thơm ngon.'),
(4, 1, 'Cơm tấm đặc biệt', 'https://statics.vinpearl.com/com-tam-ngon-o-sai-gon-0_1630562640.jpg', 35000, 'Cơm tấm đặc biệt có cơm và đầy đủ topping trứng, bì, chả siu thum ngon.'),
(5, 2, 'Khoai tây chiên', 'https://i.ndtvimg.com/i/2015-11/french-fries-625_625x350_61446325913.jpg', 15000, 'Khoai tây chiên giá kiểu Pháp giòn rụm màu vàng.'),
(6, 2, 'Phô mai que', 'https://cdn.shopify.com/s/files/1/0617/2497/files/fba9d5ae37d3dd37378106fe5aa1d357.jpg?v=1592534909', 15000, 'Phô mai que kéo sợi dai dai kèm tương ớt cay cay.'),
(7, 3, 'Nước ép cam', 'https://cope-cdnmed.agilecontent.com/resources/jpg/6/7/1593198983076.jpg', 15000, 'Nước cam chua ngon không quá chua nhưng ngon.'),
(8, 3, 'Nước ép chanh', 'https://image3.tienphong.vn/665x449/Uploaded/2020/rkznae/2020_11_26/vi_sao_ban_nen_uong_nuoc_chanh_hang_ngay_dac_biet_trong_mua_dich1580802869_mgnn.jpg', 15000, 'Chúng tôi gọi nó là Lemonade vì nó là nước chanh.'),
(9, 1, 'Cơm gà xối mỡ', 'https://bazantravel.com/cdn/medias/uploads/78/78876-60791265_144936746636954_6153101288902582280_n.jpg', 30000, 'Đĩa cơm gà bày ra đĩa trông bắt mắt với phần cơm vừa đủ ăn lưng bụng, thịt gà trộn bày lên trên, trang trí thêm ít rau răm và hành tây thái mỏng cùng muối tiêu bột. Cơm gà ăn kèm với ớt tương làm tại nhà, cay đậm đà thì quả thực là đáo khẩu. Chén canh cải nấu với gừng tươi và gỏi đu đủ, cà rốt thái sợi mỏng là những thành phần hỗ trợ tuyệt vời làm tôn thêm hương vị đĩa cơm gà.\r\nNhịp sống phố cổ khiến ta muốn thưởng thức buổi trưa thong thả và khoan thai. Gắp miếng thịt gà được trộn vừa miệng và cảm nhận hạt cơm mềm dẻo đang tan dần trong miệng thì quả thực ấn tượng khó phai.'),
(10, 4, 'Bún bò Huế chay', 'https://cdn.tgdd.vn/2021/02/CookProduct/1111-1200x676-3.jpg', 25000, 'Món bún bò Huế chay đặc trưng bởi nước dùng thơm cay mùi sa tế sả băm, cùng vị ngọt tự nhiên của các loại rau củ. Nhân nấm thơm mềm, đậu hũ chiên giòn mềm, đậu hũ ky giòn tan, thêm chả chay dai dai thật hấp dẫn.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `danhmuc`
--
ALTER TABLE `danhmuc`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mon`
--
ALTER TABLE `mon`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `danhmuc`
--
ALTER TABLE `danhmuc`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `mon`
--
ALTER TABLE `mon`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
