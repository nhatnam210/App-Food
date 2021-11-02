<?php
	include "connect.php";
	$json = $_POST['json'];
	$data = json_decode($json,true);
	foreach ($data as $value) {
		// code...
		$madonhang = $value['madonhang'];
		$mamon = $value['mamon'];
		$tenmon = $value['tenmon'];
		$gia = $value['gia'];
		$soluong = $value['soluong'];
		$query = "INSERT INTO chitietdonhang(id,madonhang,mamon,tenmon,gia,soluong) VALUES (null,'$madonhang','$mamon','$tenmon','$gia','$soluong')";
		$result = mysqli_query($conn,$query);
	}
	 if($result) {
	 	echo "1";
	 }else{
	 	echo "0";
	 }
?>