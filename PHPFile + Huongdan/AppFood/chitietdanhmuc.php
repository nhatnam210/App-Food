<?php
	include "connect.php";
	// $page = $_POST['page'];
	// $select = $_POST['select'];
	// $pos = ($page - 1) * $select;
	$madanhmuc = $_POST['madanhmuc'];

	// $query = "SELECT * FROM `mon` WHERE `madanhmuc` = $madanhmuc LIMIT $pos,$select";
	$query = "SELECT * FROM `mon` WHERE `madanhmuc` = $madanhmuc  ORDER BY `gia` ASC ";
	$data = mysqli_query($conn, $query);
	$result = array();

	while ($row = mysqli_fetch_assoc($data)) {
		$result[] = ($row);
		//code...
	}
	if (!empty($result)) {
		$arr = [
			'success' => true,
			'message' => "Thành công",
			'result' => $result
		];
	} else {
		$arr = [
			'success' => false,
			'message' => "Thất bại!",
			'result' => $result
		];
	}
	print_r(json_encode($arr));
?>