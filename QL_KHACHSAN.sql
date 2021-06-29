create database QL_KHACHSAN
use QL_KHACHSAN
create table PHONG
(
	MaP char(10) not null primary key,
	TenP nvarchar(50),
	GiaThue money,
	MaLoai char(10),
)
create table LOAIPHONG
(
	MaLoai char(10) not null primary key,
	TenLoai nvarchar(50),
	GhiChu nvarchar(50)
)
create table NHANVIEN
(
	MaNV char(10) not null primary key,
	HoTenNV nvarchar(50),
	NgaySinh date,
	DiaChi nvarchar(50),
	SDT char(11)
)
create table KHACHHANG
(
	MaKH char(10) not null primary key,
	TenKH nvarchar(50),
	NgaySinh date,
	DiaChi nvarchar(50),
	SDT char(11)
)
create table DICHVU
(
	MaDV char(10) not null primary key,
	TenDV nvarchar(50),
	GiaTien money
)
create table THUEPHONG
(
	MaThue char(10) not null primary key,
	MaP char(10),
	NgayVao date,
	MaKH char(10) ,
	MaNV char(10),
	DatCoc money,
	NgayRa date
)
create table SDDICHVU
(
	MaSD char(10) not null,
	MaThue char(10) not null,
	MaDV char(10) not null,
	NgaySD date,
	DonGia money,
	primary key(MaSD, MaThue, MaDV)
)
create table THANHTOAN
(
	MaThanhToan char(10) not null primary key,
	MaThue char(10),
	MaKH char(10) ,
	ThanhTien money,
	HinhThucThanhToan nvarchar(50),
	GhiChu nvarchar(50),
	NgayThanhToan date,
)
alter table PHONG
add constraint FK_PHONG_LOAIP foreign key (MaLoai) references LOAIPHONG(MaLoai)
alter table THUEPHONG
add constraint FK_THUEPHONG_KHACH foreign key (MaKH) references KHACHHANG(MaKH),
	constraint FK_THUEPHONG_PHONG foreign key (MaP) references PHONG(MaP),
	constraint FK_THUEPHONG_NHANVIEN foreign key (MaNV) references NHANVIEN(MaNV)
alter table SDDICHVU
add constraint FK_SDDICHVU_THUE foreign key (MaThue) references THUEPHONG(MaThue),
	constraint FK_SDDICHVU_PHONG foreign key (MaDV) references DICHVU(MaDV)
alter table THANHTOAN 
add constraint FK_THNAHTOAN_MAKH foreign key (MaKH) references KHACHHANG(MaKH),
	constraint FK_THANHTOAN_THUEPHONG foreign key (MaThue) references THUEPHONG(MaThue)
----------------Ràng buộc(check, unique, default, trigger)------------------------------
--Kiểm tra độ tuổi khách hàng
alter table KHACHHANG
add check (year(getdate()) - year(NgaySinh)>=18)
--Kiểm tra tiền đặc cọc >= 100 (THUEPHONG)
alter table THUEPHONG
add check (DatCoc >= 100)
--Kiểm tra tiền Đơn giá > 0 (SSDUNGDICH)
alter table SDDICHVU
add check (DonGia > 0)
--Kiểm tra Giá Thuê > 0 (PHONG)
alter table PHONG
add check (GiaThue > 0)
--Mặt định ThanhTien trong THANHTOAN bằng 0
alter table THANHTOAN
add constraint TK_ThanhTien default 0 for ThanhTien
--Kiểm tra ngày vào ngày ra của khách hàng
create trigger KT_NGAY on THUEPHONG for insert
as
if(select NgayRa from inserted) >= (select NgayVao from inserted)
	commit tran
else 
	begin 
	print 'Ngày vào  > Ngày ra ROI!!!!'
	rollback tran 
	end
--Kiểm tra địa chỉ của khách hàng nếu để trống để chưa xác định
alter table KHACHHANG
add constraint DF_KHACHHANG default 'Chua xac dinh ' for DIACHI
--Kiểm tra phòng đã đặt cùng ngày 
alter table THUEPHONG
add constraint UNI_NgayVao_MaPH unique(MaP,NgayVao)
--Kiểm tra ngày thanh toán phải nằm trong thời gian thuê
create trigger KT_NGAY_THANHTOAN on THANHTOAN for insert
as
if((select NgayThanhToan from inserted) >= (select NgayVao from THUEPHONG 
																where MaThue = (select MaThue 
																				from inserted)) 
	and (select NgayThanhToan from inserted) <= (select NgayRa from THUEPHONG 
																where MaThue = (select MaThue 
																				from inserted)))
	commit tran
else 
	begin 
	print 'Chua thanh toan!!!!'
	rollback tran 
	end
-----------------------------------------------------------------------------------------------
Insert into LOAIPHONG
Values('LA1',N'Phòng thường hai giường', N'Phong cách cổ điển'),
	('LA2',N'Phòng đơn một giường', N'Phong cách hoang dã'),
	('LB1',N'Phòng VIP loại 1', N'Phong cách Châu Âu'),
	('LB2',N'Phòng VIP loại 2', N'Phong cách Nhật cổ điển'),
	('LC1',N'Phòng đơn một giường', N'Phong cách tự nhiên'),
	('LC2',N'Phòng thường 2 người', N'Phong cách romatic'),
	('LD1',N'Phòng VIP loại 3', N'Phong cách Châu Á'),
	('LD2',N'Phòng đôi 2 giường', N'Phong cách Hàn'),
	('LE1',N'Phòng đơn một giường', N'Phong cách Âu Mỹ'),
	('LE2',N'Phòng VIP loại 4', N'Phong cách Châu Phi')
Select*from LOAIPHONG
Insert into PHONG
Values('PH001',N'Phòng Thường', 150, 'LA1'),
	('PH002',N'Phòng Đơn', 250, 'LA2'),
	('PH003',N'Phòng VIP1',450, 'LB1'),
	('PH004',N'Phòng VIP2', 550, 'LB2'),
	('PH005',N'Phòng Đơn', 250, 'LC1'),
	('PH006',N'Phòng Thường',  250, 'LC2'),
	('PH007',N'Phòng VIP3', 750, 'LD1'),
	('PH008',N'Phòng Đôi', 1050, 'LD2'),
	('PH009',N'Phòng Đơn',  1150, 'LE1'),
	('PH0010',N'Phòng VIP4',  2150, 'LE2')
Select*from PHONG
set dateformat dmy
Insert into NHANVIEN
Values('NV001',N'Đỗ Thanh Tùng', '06/11/2003',N'172 Nguyễn Tư Giãn','086494949'),
('NV002',N'Phạm Thị Vi', '04/05/2000',N'1 Bùi Xuân Phái','0873838218'),
('NV003',N'Phan Thị Ngọc Dung', '03/04/2001',N'30 Nguyễn Đỗ Cung','0938392821'),
('NV004',N'Lý Hoàng Phi Dũng', '04/03/1999',N'25 Nguyễn Đỗ Cung','0989888626'),
('NV005',N'Nguyễn Đực Thịnh', '10/10/2000',N'287 Tân Kỳ Tân Quý','0767859404'),
('NV006',N'Trường Hồng Phi', '06/09/2000',N'29 Âu Cơ','0984435291'),
('NV007',N'Ngô Đình Long', '05/12/1998',N'393 Lũy Bán Bích','0336372821'),
('NV008',N'Đỗ Trần Kim Nguyên', '23/09/1999',N'261 Nguyễn Văn Quá','086494949'),
('NV009',N'Trần Đức Bo', '15/07/1991',N'65 Quan Trung','0989654611'),
('NV0010',N'Trần Thanh Tâm', '15/10/1998',N'74 Tô Ký','0987479373')
Select*from NHANVIEN
Insert into KHACHHANG
Values('KH001', N'Đỗ Ái Vy','20/10/1998',N'173 Nguyễn Văn Khối', '0998765754'),
('KH002', N'Nguyễn Duy Khương','29/03/2000',N'80 Quang Trung', '0975846321'),
('KH003', N'Dương Văn Ngoan','26/01/1999',N'469 Thống Nhất', '0778727261'),
('KH004', N'Nguyễn Thị Lệ Anh','22/08/1997',N'190 Phạm Văn Chiêu', '0919821021'),
('KH005', N'Nguyễn Trang Cẩm Tú','11/09/1998',N'375 Phan Huy Ích', '0393728900'),
('KH006', N'Châu Hoàng Minh Khang','24/06/2001',N'189  Phạm Văn Bạch', '0797779652'),
('KH007', N'Bùi Hoàng Khải','14/02/1995',N'40A Nguyễn Oanh', '0887567564'),
('KH008', N'Hà Hạ Minh Kha','18/02/1992', N'198 Trường Trinh', '0864363864'),
('KH009', N'Trần Hoàng Phong','29/09/1999',N'173 Nguyễn Văn Khối', '0998765754'),
('KH0010', N'Lương Kim Sang','02/02/2002',N'17A Cộng Hòa', '0972872982')
Select* from KHACHHANG
Insert into DICHVU
Values('DV001',N'Nhà Hàng',700),
('DV002',N'Spa',500),
('DV003',N'Fitness',300),
('DV004',N'Casino',200),
('DV005',N'Chơi Golf và Tennis',100),
('DV006',N'Karaoke',150),
('DV007',N'Bar',700),
('DV008',N'Taxi',100),
('DV009',N'Giặc ủi',50),
('DV0010',N'Môi giới',500)
Select *from DICHVU
Insert into THUEPHONG
Values('TH001','PH001','01/12/2019','KH001','NV001',500,'03/12/2019')
Insert into THUEPHONG
Values('TH0010','PH002','01/12/2019','KH001','NV001',500,'03/12/2019')
Insert into THUEPHONG
Values ('TH002','PH003','12/12/2019','KH009','NV002' ,1000,'14/12/2019')
Insert into THUEPHONG
Values ('TH003','PH002','18/12/2019','KH002','NV004' ,500,'19/12/2019')
Insert into THUEPHONG
Values ('TH004','PH004','08/01/2020','KH003','NV005' ,700,'18/01/2020')
Insert into THUEPHONG
Values ('TH005','PH005','12/02/2020','KH004','NV003' ,200,'20/02/2020')
Insert into THUEPHONG
Values('TH006','PH006','07/03/2020','KH005','NV007' ,500,'08/03/2020')
Insert into THUEPHONG
Values('TH007','PH008','09/03/2020','KH006','NV001' ,100,'10/03/2020')
Insert into THUEPHONG
Values('TH008','PH007','23/03/2020','KH007','NV005' ,500,'24/03/2020')
Insert into THUEPHONG
Values ('TH009','PH009','22/03/2020','KH008','NV009' ,500,'23/03/2020')
Select*from THUEPHONG
Insert into THANHTOAN(MaThanhToan,MaThue,MaKH,HinhThucThanhToan,GhiChu,NgayThanhToan)
Values('TT001','TH001','KH001',N'Master Card', N'Đã Thanh Toán','02/12/2019')
Insert into THANHTOAN(MaThanhToan,MaThue,MaKH,HinhThucThanhToan,GhiChu,NgayThanhToan)
Values('TT002','TH002','KH009',N'Visa Card', N'Đã Thanh Toán','12/12/2019')
Insert into THANHTOAN(MaThanhToan,MaThue,MaKH,HinhThucThanhToan,GhiChu,NgayThanhToan)
Values('TT003','TH003','KH002',N'Cash', N'Đã Thanh Toán','18/12/2019')
Insert into THANHTOAN(MaThanhToan,MaThue,MaKH,HinhThucThanhToan,GhiChu,NgayThanhToan)
Values('TT004','TH004','KH003',N'Cash', N'Đã Thanh Toán','09/01/2020')
Insert into THANHTOAN(MaThanhToan,MaThue,MaKH,HinhThucThanhToan,GhiChu,NgayThanhToan)
Values('TT005','TH005','KH004',N'Payair' , N'Đã Thanh Toán','13/02/2020')
Insert into THANHTOAN(MaThanhToan,MaThue,MaKH,HinhThucThanhToan,GhiChu,NgayThanhToan)
Values('TT006','TH006','KH005',N'Cash', N'Đã Thanh Toán','08/03/2020')
Insert into THANHTOAN(MaThanhToan,MaThue,MaKH,HinhThucThanhToan,GhiChu,NgayThanhToan)
Values('TT007','TH007','KH006',N'Master Card', N'Đã Thanh Toán','09/03/2020')
Insert into THANHTOAN(MaThanhToan,MaThue,MaKH,HinhThucThanhToan,GhiChu,NgayThanhToan)
Values('TT008','TH008','KH007',N'Napas Card', N'Đã Thanh Toán','24/03/2020')
Insert into THANHTOAN(MaThanhToan,MaThue,MaKH,HinhThucThanhToan,GhiChu,NgayThanhToan)
Values('TT009','TH009','KH008',N'Momo', N'Đã Thanh Toán','22/03/2020')
Select*from THANHTOAN
Insert into SDDICHVU
Values('SD001','TH001','DV002','12/12/2019', 200),
('SD002','TH002','DV001','01/12/2019', 100),
('SD003','TH003','DV003','18/12/2019', 250),
('SD004','TH004','DV004','08/01/2020', 200),
('SD005','TH005','DV005','12/02/2020', 200),
('SD006','TH006','DV006','07/03/2020', 100),
('SD007','TH007','DV007','09/03/2020', 300),
('SD008','TH008','DV003','23/03/2020', 200),
('SD009','TH009','DV0010','22/03/2019', 240)
Select*from SDDICHVU
----Sử dụng các PROC-------------------------------------
----Xem các phòng trống--------------------------------
create proc Xem_PhongTrong  @ngay date
as
	select *from PHONG 
	where not exists (select *from THUEPHONG
						where PHONG.MaP = THUEPHONG.MaP and NgayVao <= @ngay and NgayRa > @ngay)
go
exec Xem_PhongTrong '22/3/2020'
select *from THUEPHONG 
select *from PHONG
----Xem các phòng đã thuê--------------------------------
create proc Xem_PhongDaThue @ngay date
as
	select *from PHONG 
	where  exists (select *from THUEPHONG
						where PHONG.MaP = THUEPHONG.MaP and NgayVao <= @ngay and NgayRa > @ngay)
go
exec Xem_PhongDaThue '22/3/2020'
--Xem ngày sử dụng dịch vụ---------------
create proc Xem_DichVu @ngay date
as
	select MaSD, SDDICHVU.MaThue, MaDV, NgaySD, DonGia
	from SDDICHVU, THUEPHONG
	where SDDICHVU.MaThue = THUEPHONG.MaThue and NgayVao <= @ngay and NgayRa > @ngay
go
exec  Xem_DichVu '22/3/2020'
--------Viết hàm xuất ra họ tên nhân viên, tên khách hàng------
create proc Xuat_TenKhachHang @MaThue char(10)
as
	
	select HoTenNV, TenKH  , THUEPHONG.MaThue
	from THUEPHONG,NHANVIEN, KHACHHANG 
	where  NHANVIEN.MaNV = THUEPHONG.MaNV  and THUEPHONG.MaKH = KHACHHANG.MaKH and THUEPHONG.MaThue = @MaThue
exec Xuat_TenKhachHang 'TH001'
-------------Viết hàm xuất ra Tiền
create proc Xuat_Tien @MaThue char(10)
as 
	
	select ThanhTien, MaThue
	from THANHTOAN
	where MaThue = @MaThue
exec  Xuat_Tien 'TH003'
--Cap nhap DonGia = GiaTien----------------------------
create proc CN_DONGIA_SDDV 
as
declare CS_DONGIA_SDDV cursor
dynamic 
for select MaDV, GiaTien from DICHVU
open  CS_DONGIA_SDDV 
declare @maDV char(10), @giaTien money
Fetch next from CS_DONGIA_SDDV into @maDV, @giaTien
while(@@FETCH_STATUS = 0)
begin 

	update SDDICHVU
	set DonGia = @giaTien
	where MaDV = @maDV
Fetch next from CS_DONGIA_SDDV into @maDV, @giaTien
end
close CS_DONGIA_SDDV
deallocate CS_DONGIA_SDDV
----------Cap nhat thành tiền = sum(dongia(SDDVU)) + TienPhong*SoNgayo - tiền cọc
create proc CNN_ThanhTien
as
select *from THANHTOAN
declare  CN_ThanhTien cursor
dynamic 
for select MaThue from ThuePhong
open  CN_ThanhTien
declare @maThue char(10)
Fetch next from CN_ThanhTien into @maThue
while(@@FETCH_STATUS = 0)
begin 
	
	update THANHTOAN
	set ThanhTien = (select sum(DonGia) 
								from SDDICHVU 
								where MaThue = @maThue) + (select GiaThue 
															from PHONG, THUEPHONG 
															where PHONG.MaP = THUEPHONG.MaP and MaThue = @maThue)*(select  DATEDIFF(day, NgayVao, NgayRa) 
																															from THUEPHONG 
																															where MaThue = @maThue) - (select DatCoc
																																						 from THUEPHONG 
																																						where MaThue = @maThue)
	where MaThue = @maThue
Fetch next from CN_ThanhTien into @maThue
end
close CN_ThanhTien
deallocate CN_ThanhTien
exec CNN_ThanhTien
create proc TinhTienTungMa @maThue char(10)
as
	update THANHTOAN
	set ThanhTien = (select sum(DonGia) 
								from SDDICHVU 
								where MaThue = @maThue) + (select GiaThue 
															from PHONG, THUEPHONG 
															where PHONG.MaP = THUEPHONG.MaP and MaThue = @maThue)*(select  DATEDIFF(day, NgayVao, NgayRa) 
																															from THUEPHONG 
																															where MaThue = @maThue) - (select DatCoc
																																						 from THUEPHONG 
																																						 where MaThue = @maThue)
	where MaThue = @maThue
create proc ThanhTien1 @maThue char(10)
as
	exec TinhTienTungMa @maThue
	select ThanhTien from THANHTOAN where MaThue = @mathue

	exec ThanhTien1 'TH0017'
--------------------------------------------Viết hàm tim kiếm---------------------------
--Tim kiem dich vu
create proc TimKiemDichVu @tim nvarchar(100)
as
	select *from DICHVU where MaDV like N'%' + @tim + '%' or TenDV  like N'%' + @tim + '%'

exec TimKiemDichVu N'tim'
--Tim kiem KhachHang
create proc TimKiemKhachHang @tim nvarchar(100)
as
	select *from KHACHHANG where TenKH like N'%' + @tim + '%' or MaKH like N'%' + @tim + '%' 
--Tim kiem NhanVien
create proc TimKiemNhanVien @tim nvarchar(100)
as 
	select *from NHANVIEN where HoTenNV like N'%' + @tim + '%' or MaNV like N'%' + @tim + '%' 
exec TimKiemNhanVien N'NV'
--Tim kiem Phong
create proc TimKiemPhong @tim nvarchar(100)
as 
	select *from PHONG where TenP like N'%' + @tim + '%' or MaP like N'%' + @tim + '%' 
---Thống kê
--Tổng các Loại Phòng: 
create proc TinhTongLoaiPhong 
as
	select count(MaLoai) as Tong from LOAIPHONG
--Tổng các Phòng
create proc TinhTongPhong
as 
	select count(MaP) as Tong from PHONG
--Tổng các dịch vụ
create proc TinhTongDichVu
as
	select count(MaDV) as Tong from DICHVU
exec TinhTongDichVu
--Tổng các khách hang
create proc TingTongKhachHang @ngay date
as
	select COUNT(MaKH) as Tong from KHACHHANG 
	where  exists (select *from THUEPHONG
						where KHACHHANG.MaKH = THUEPHONG.MaKH and NgayVao <= @ngay and NgayRa > @ngay)
--Tổng nhân viên
create proc TinhTongNhanVien 
as
  select COUNT(MaNV) as Tong from NhanVien
--Tổng các Phòng Đã thuê trong ngày đó
create proc TongPhongDaThue @ngay date
as
	select count(MaP) as Tong from PHONG 
	where  exists (select *from THUEPHONG
						where PHONG.MaP = THUEPHONG.MaP and NgayVao <= @ngay and NgayRa > @ngay)
--Tổng các Phòng Chưa thuê trong ngày đó
create proc TongPhongChuaThue @Ngay date
as 
	select count(MaP) as Tong from PHONG 
	where not exists (select *from THUEPHONG
						where PHONG.MaP = THUEPHONG.MaP and NgayVao <= @ngay and NgayRa > @ngay)
--Tong tien theo ngay
create proc TongTienTien @ngay date
as
	select sum(ThanhTien) as Tong from THANHTOAN where NgayThanhToan = @ngay
backup database QL_KHACHSAN
to disk = 'F:\HeThongThongTin\HeQuanTri\QL_KHACHSANBak.BAK'
