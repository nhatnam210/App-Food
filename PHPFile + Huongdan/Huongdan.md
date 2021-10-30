#1 Copy thư mục "AppFood" bên trên và dán vào BÊN TRONG thư mục "htdocs" được chứa trong đường dẫn máy nơi mà bạn lưu trữ XAMPP sau khi cài đặt

Đường dẫn có dạng : "...\XAMPP\htdocs"

Ví dụ : "D:\DOWNLOAD\XAMPP\htdocs"


#2 dùng XAMPP khởi động PHPMyadmin (chi tiết tra google)

#3 PHPMyadmin tạo database mới tên : "appfood" 

#4 Import file appfood.sql vào datase "appfood"  vừa tạo (chi tiết tra google)

#5 Mở code bằng Android studio, Vào Lib >java > com.example.lib > common > Url sửa :
private static String ipv4Address = "192.168.1.9"; ---> sửa ipv4Address = địa chị IPv4 của máy cá nhân.

(Kiểm tra IPv4 máy: vào command line gõ lệnh : "ipconfig")

Done...

create by Tran Nguyen Nhat Nam
Update : 30/10/2021
