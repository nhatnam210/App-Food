<?php
	include "connect.php";
	$tenkhachhang = $_POST['tenkhachhang'];
	$email = $_POST['email'];
	$sodienthoai = $_POST['sodienthoai'];
	$tongtien = $_POST['tongtien'];
	$ghichu = $_POST['ghichu'];

	$query = "INSERT INTO donhang(id,tenkhachhang,email,sodienthoai,tongtien,ghichu) VALUES (null,'$tenkhachhang','$email','$sodienthoai','$tongtien','$ghichu') ";
	if(mysqli_query($conn,$query)) {
		$id = $conn -> insert_id;
		echo $id;
	}else{
		echo "Thất bại!";
	}

?>