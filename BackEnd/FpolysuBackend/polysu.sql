drop database polysu
create database polysu
go
use polysu
go


CREATE TABLE users
(
    email         NVARCHAR(50) NOT NULL PRIMARY KEY,
    _password     VARCHAR(100),
    name          NVARCHAR(100),
    business_name NVARCHAR(200),
    phone         NVARCHAR(15),
    _role         VARCHAR(15),
    gender        NVARCHAR(15),
    _address      NVARCHAR(100),
    date_of_birth date,
    _status       NVARCHAR(50),
    created_date  date,
    created_by    NVARCHAR(50)
)

-- menu
CREATE TABLE menu
(
    id                INT IDENTITY (1,1) NOT NULL PRIMARY KEY,
    email             NVARCHAR(50) FOREIGN KEY REFERENCES dbo.users (email),
    name_menu         NVARCHAR(30),
    color_menu        NVARCHAR(50),
    _status           BIT,
    menu_type         nvarchar(20),
    date_start        date,
    menu_location     nvarchar(20),
    menu_code         VARCHAR(50),
    opacity           NVARCHAR(10),
    from_display_time int default (0),
    to_display_time   int default (2359)
)

--button
CREATE TABLE button
(
    id               INT IDENTITY (1,1) NOT NULL PRIMARY KEY,
    id_menu          INT FOREIGN KEY REFERENCES menu (id),
    type_button      nvarchar(100),
    name_button      NVARCHAR(100),
    color_text       NVARCHAR(300),
    link             NVARCHAR(300),
    icon             NVARCHAR(300),
    color_background NVARCHAR(300),
    color_icon       NVARCHAR(300),
    caption_content  NVARCHAR(100)
)


create table activity_button
(
    id           int identity                                                                  not null primary key,
    id_button    int foreign key references dbo.button (id) not                                null,
    created_at   datetime      default getdate()                                               null,
    from_url     nvarchar(300) default 'http://localhost:3000/this-URL-from-local-environment' null,
    equipment    bit                                                                           null,
    ip_address   nvarchar(100) default '0.0.0.0'                                               null,
    user_address nvarchar(100)                                                                 null,
    languages    nvarchar(50)  default 'vietnamese'                                            null
)


create table activity_menu
(
    id         int identity                                 not null primary key,
    id_menu    int foreign key references dbo.menu (id) not null,
    created_at datetime default getdate()                   null
)


-- dataofcustom
create table dataofcustomer
(
    id             INT IDENTITY (1,1) NOT NULL PRIMARY KEY,
    email          NVARCHAR(50) FOREIGN KEY REFERENCES dbo.users (email),
    fullname       NVARCHAR(100),
    phone          varchar(15),
    email_customer NVARCHAR(100),
    _address       NVARCHAR(200),
    content        NVARCHAR(MAX),
    notes          NVARCHAR(MAX),
    create_date    datetime
)

-- modal
CREATE TABLE modal
(
    id                INT IDENTITY (1,1) NOT NULL PRIMARY KEY,
    id_button         int FOREIGN KEY REFERENCES dbo.button (id),
    id_dataofcustomer int FOREIGN KEY REFERENCES dbo.dataofcustomer (id),
    input_name        NVARCHAR(50)       NOT NULL,
    input_value       NVARCHAR(50)
)


-- servicefee
create table servicefee
(
    id           INT IDENTITY (1,1) NOT NULL PRIMARY KEY,
    name_service NVARCHAR(200),
    price        float,
)

-- payment_history
create table payment_history
(
    id          INT IDENTITY (1,1) NOT NULL PRIMARY KEY,
    email       NVARCHAR(50) FOREIGN KEY REFERENCES dbo.users (email),
    id_service  INT FOREIGN KEY REFERENCES servicefee (id),
    date_end    datetime,
    _status     nvarchar(100),
    total_price float
)


-- insert user
--admin
insert into users(email, _password, name, business_name, phone, _role, gender, _address, date_of_birth, _status,
                  created_date, created_by)
values ('admin@gmail.com', 'admin', 'admin', '', '0433252525', 'admin', N'Nam', N'TP Hà Nội', '05-05-2021', 1,
        N'05-05-2021', '')
insert into users(email, _password, name, business_name, phone, _role, gender, _address, date_of_birth, _status,
                  created_date, created_by)
values ('lekhuongduy1998@gmail.com', 'duy123', 'Lê Khương Duy', 'CD FPT', '0972222111', 'admin', N'Nam', N'TP Hà Nội',
        '10-11-1998', 1, N'05-05-2021', N'admin')
insert into users(email, _password, name, business_name, phone, _role, gender, _address, date_of_birth, _status,
                  created_date, created_by)
values ('leducbinh@gmail.com', 'binh123', 'Lê Đức Bình', 'CD FPT', '0872222133', 'admin', N'Nam', N'TP Hà Nội',
        '04-04-1994', 1, N'05-05-2021', '')
insert into users(email, _password, name, business_name, phone, _role, gender, _address, date_of_birth, _status,
                  created_date, created_by)
values ('vuthanhnam@gmail.com', 'nam123', 'Vũ Thành Nam', 'CD FPT', '0472222133', 'admin', N'Nam', N'TP Hà Nội',
        '4-14-1993', 1, N'05-05-2021', '')
--employee
insert into users(email, _password, name, business_name, phone, _role, gender, _address, date_of_birth, _status,
                  created_date, created_by)
values ('duongtunglam@gmail.com', 'lam123', 'Dương Tùng Lâm', 'CD FPT', '035552133', 'employee', N'Nam', N'TP Hà Nội',
        '8-20-1995', 1, N'05-05-2021', '')
insert into users(email, _password, name, business_name, phone, _role, gender, _address, date_of_birth, _status,
                  created_date, created_by)
values ('duyle1998@gmail.com', 'duy123', 'Lê Duy', 'CD FPT', '035552133', 'employee', N'Nam', N'TP Hà Nội', '8-10-1998',
        1, N'05-05-2021', '')
-- customer
insert into users(email, _password, name, business_name, phone, _role, gender, _address, date_of_birth, _status,
                  created_date, created_by)
values ('nguyenphuonghang@gmail.com', 'hang123', 'Nguyễn Phương Hằng', 'Công ty Cổ phần Đại Nam', '015552133',
        'customer', N'Nữ', N'TP Bình Dương', '1-26-1971', 1, N'5-08-2021', '')
insert into users(email, _password, name, business_name, phone, _role, gender, _address, date_of_birth, _status,
                  created_date, created_by)
values ('phamnhatvuong@gmail.com', 'vuong123', 'Phạm Nhật Vượng', 'Tập đoàn vingroup', '015552133', 'customer', N'Nam',
        N'Phù Lưu, Lộc Hà, ‎Hà Tĩnh', '8-5-1968', 1, N'5-09-2021', '')
insert into users(email, _password, name, business_name, phone, _role, gender, _address, date_of_birth, _status,
                  created_date, created_by)
values ('nguyenhoabinh@gmail.com', 'binh123', 'Shark Nguyễn Hòa Bình', 'Công Ty Tnhh Nextech', '02439932628',
        'customer', N'Nam', N'Ngách 1 Ngõ 243 Nam Dư, Lĩnh Nam, Hoàng Mai, Hà Nội', '8-5-1981', 1, N'5-09-2021', '')


-- insert menu
insert into menu (email, name_menu, color_menu, _status, menu_type, date_start, menu_location, menu_code, opacity,
                  from_display_time, to_display_time)
values ('nguyenphuonghang@gmail.com', 'menu1', '#F50A05', 1, 1, '', 1, '', 0.67, 2200, 2127)
insert into menu (email, name_menu, color_menu, _status, menu_type, date_start, menu_location, menu_code, opacity,
                  from_display_time, to_display_time)
values ('nguyenphuonghang@gmail.com', 'menu2', '#FF0571', 0, 1, '', 2, '', 0.67, 2200, 2127)
insert into menu (email, name_menu, color_menu, _status, menu_type, date_start, menu_location, menu_code, opacity,
                  from_display_time, to_display_time)
values ('phamnhatvuong@gmail.com', 'menu1', '#FF0571', 0, 1, '', 2, '', 0.67, 2200, 2127)
insert into menu (email, name_menu, color_menu, _status, menu_type, date_start, menu_location, menu_code, opacity,
                  from_display_time, to_display_time)
values ('phamnhatvuong@gmail.com', 'menu2', '#E807D9', 1, 1, '', 2, '', 0.67, 2200, 2127)
insert into menu (email, name_menu, color_menu, _status, menu_type, date_start, menu_location, menu_code, opacity,
                  from_display_time, to_display_time)
values ('nguyenhoabinh@gmail.com', 'menu1', '#FF0571', 0, 1, '', 2, '', 0.67, 2200, 2127)
insert into menu (email, name_menu, color_menu, _status, menu_type, date_start, menu_location, menu_code, opacity,
                  from_display_time, to_display_time)
values ('nguyenhoabinh@gmail.com', 'menu2', '#E807D9', 1, 1, '', 2, '', 0.67, 2200, 2127)


-- insert button
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (1, 1, 'Tin nhắn', '#F50A05', '#7B05F5', 'https://www.facebook.com/messages/', '7B05F5', '#7B05F5', 'Tin nhắn')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (1, 2, 'call', '#F50A05', '#7B05F5', '02463252525', '#7B05F5', '#7B05F5', N'taxi sông nhuệ')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (1, 3, 'modal', '#F50A05', '#7B05F5', '', '#7B05F5', '#7B05F5', '')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (1, 1, 'youtube', '#F50A05', '#7B05F5', 'https://www.youtube.com/', '#7B05F5', '#7B05F5', N'taxi sông nhuệ')

insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (2, 1, 'Tin nhắn', '#F50A05', '#7B05F5', 'https://www.facebook.com/messages/', '7B05F5', '#7B05F5', 'Tin nhắn')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (2, 2, 'call', '#F50A05', '#7B05F5', '02463252525', '#7B05F5', '#7B05F5', N'taxi sông nhuệ')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (2, 3, 'modal', '#F50A05', '#7B05F5', '', '#7B05F5', '#7B05F5', '')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (2, 1, 'youtube', '#F50A05', '#7B05F5', 'https://www.youtube.com/', '#7B05F5', '#7B05F5', N'taxi sông nhuệ')

insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (3, 1, 'Tin nhắn', '#F50A05', '#7B05F5', 'https://www.facebook.com/messages/', '7B05F5', '#7B05F5', 'Tin nhắn')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (3, 2, 'call', '#F50A05', '#7B05F5', '02463252525', '#7B05F5', '#7B05F5', N'taxi sông nhuệ')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (3, 3, 'modal', '#F50A05', '#7B05F5', '', '#7B05F5', '#7B05F5', '')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (3, 1, 'youtube', '#F50A05', '#7B05F5', 'https://www.youtube.com/', '#7B05F5', '#7B05F5', N'taxi sông nhuệ')

insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (4, 1, 'Tin nhắn', '#F50A05', '#7B05F5', 'https://www.facebook.com/messages/', '7B05F5', '#7B05F5', 'Tin nhắn')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (4, 2, 'call', '#F50A05', '#7B05F5', '02463252525', '#7B05F5', '#7B05F5', N'taxi sông nhuệ')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (4, 3, 'modal', '#F50A05', '#7B05F5', '', '#7B05F5', '#7B05F5', '')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (4, 1, 'youtube', '#F50A05', '#7B05F5', 'https://www.youtube.com/', '#7B05F5', '#7B05F5', N'taxi sông nhuệ')


insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (5, 1, 'Tin nhắn', '#F50A05', '#7B05F5', 'https://www.facebook.com/messages/', '7B05F5', '#7B05F5', 'Tin nhắn')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (5, 2, 'call', '#F50A05', '#7B05F5', '02463252525', '#7B05F5', '#7B05F5', N'taxi sông nhuệ')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (5, 3, 'modal', '#F50A05', '#7B05F5', '', '#7B05F5', '#7B05F5', '')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (5, 1, 'youtube', '#F50A05', '#7B05F5', 'https://www.youtube.com/', '#7B05F5', '#7B05F5', N'taxi sông nhuệ')

insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (6, 1, 'Tin nhắn', '#F50A05', '#7B05F5', 'https://www.facebook.com/messages/', '7B05F5', '#7B05F5', 'Tin nhắn')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (6, 2, 'call', '#F50A05', '#7B05F5', '02463252525', '#7B05F5', '#7B05F5', N'taxi sông nhuệ')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (6, 3, 'modal', '#F50A05', '#7B05F5', '', '#7B05F5', '#7B05F5', '')
insert into button(id_menu, type_button, name_button, color_text, link, icon, color_background, color_icon,
                   caption_content)
values (6, 1, 'youtube', '#F50A05', '#7B05F5', 'https://www.youtube.com/', '#7B05F5', '#7B05F5', N'taxi sông nhuệ')


-- insert dataofcustomer
insert into dataofcustomer(email, fullname, phone, email_customer, _address, content, notes)
values ('nguyenphuonghang@gmail.com', N'Lê Đức bình', '044445566', 'ducbinh@gmail.com', N'Nghệ An',
        N'tôi được nhận vào công ty savis', N'abc')
insert into dataofcustomer(email, fullname, phone, email_customer, _address, content, notes)
values ('nguyenphuonghang@gmail.com', N'lê anh tùng', '0343445566', 'leanhtung@gmail.com', N'phú thọ',
        N'thu nhập 1000$', N'chị 97')
insert into dataofcustomer(email, fullname, phone, email_customer, _address, content, notes)
values ('nguyenphuonghang@gmail.com', N'Vũ thành Nan', '033445566', 'thanhnam93@gmail.com', N'Nam định',
        N'tôi mới có người yêu', N'thuu')
insert into dataofcustomer(email, fullname, phone, email_customer, _address, content, notes)
values ('phamnhatvuong@gmail.com', N'Lê khương duy', '044445566', 'lekhuongduy@gmail.com', N'ha noi', N'THPT lý tử tấn',
        N'cyz')
insert into dataofcustomer(email, fullname, phone, email_customer, _address, content, notes)
values ('phamnhatvuong@gmail.com', N'duong tung lam', '0343445566', 'tunglam@gmail.com', N'Hà Nội', N'thu nhập 10000$',
        N'chưa có người yêu')
insert into dataofcustomer(email, fullname, phone, email_customer, _address, content, notes)
values ('phamnhatvuong@gmail.com', N'vu thanh nam', '0343445566', 'thanhnam93@gmail.com', N'Hà Nội', N'thu nhập 10000$',
        N'chưa có người yêu')
insert into dataofcustomer(email, fullname, phone, email_customer, _address, content, notes)
values ('nguyenhoabinh@gmail.com', N'vu thanh nam', '123456789', 'thanhnam93@gmail.com', N'Hà Nội', N'thu nhập cao',
        N'chưa có người yêu')
insert into dataofcustomer(email, fullname, phone, email_customer, _address, content, notes)
values ('nguyenhoabinh@gmail.com', N'test', '123456789', 'test1@gmail.com', N'Hà Nội', N'thu nhập cao',
        N'chưa có người yêu')


--insert modal
SELECT *
FROM dbo.modal
INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (1, 1, N'GioiTinh', 'Nam')
INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (1, 1, N'Ho tên', 'Bình')
INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (1, 1, N'Năm sinh', '1994')
INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (1, 1, N'Địa chỉ', 'Nghệ an')

INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (2, 2, N'GioiTinh', 'Nam')
INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (2, 2, N'Ho tên', 'Bình')
INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (2, 2, N'Năm sinh', '1994')
INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (2, 2, N'Địa chỉ', 'Nghệ an')

INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (3, 2, N'GioiTinh', 'Nam')
INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (3, 2, N'Ho tên', 'Bình')
INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (3, 2, N'Năm sinh', '1994')
INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (3, 1, N'Địa chỉ', 'Nghệ an')

INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (4, 2, N'Năm sinh', '1996')
INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (4, 1, N'Địa chỉ', 'Hà tây')

INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (5, 2, N'họ tên', 'Duy')
INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (5, 3, N'Địa chỉ', 'Nghệ an')


INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (6, 1, N'họ tên', 'Tùng lâm')
INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (6, 1, N'Địa chỉ', 'Hưng yên')


INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (7, 1, N'họ tên', 'Lê Anh tùng')
INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (7, 1, N'Địa chỉ', 'Phú thọ')

INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (8, 1, N'họ tên', 'Nguyễn bá vinh')
INSERT INTO dbo.modal(id_button, id_dataofcustomer, input_name, input_value)
VALUES (8, 1, N'Địa chỉ', 'Hà Đông')

-- insert servicefee
select *
from servicefee
insert into servicefee(name_service, price)
values ('1 tháng', 1.9)
insert into servicefee(name_service, price)
values ('3 tháng', 4.9)
insert into servicefee(name_service, price)
values ('6 tháng', 8.9)
insert into servicefee(name_service, price)
values ('12 tháng', 15.9)


-- insert payment_history
insert into payment_history(email, id_service, date_end, _status)
values ('nguyenphuonghang@gmail.com', 1, GETDATE(), 0)
insert into payment_history(email, id_service, date_end, _status)
values ('phamnhatvuong@gmail.com', 2, GETDATE(), 1)
insert into payment_history(email, id_service, date_end, _status)
values ('nguyenhoabinh@gmail.com', 2, GETDATE(), 1)


-- activity-menu
insert into activity_menu(id_menu, created_at)
values (1, N'2021-08-21 15:15:34')
insert into activity_menu(id_menu, created_at)
values (2, N'2021-08-24 15:15:34')
insert into activity_menu(id_menu, created_at)
values (3, N'2021-08-14 15:15:34')
insert into activity_menu(id_menu, created_at)
values (1, N'2021-08-24 15:15:34')
insert into activity_menu(id_menu, created_at)
values (2, N'2021-08-04 15:15:34')
insert into activity_menu(id_menu, created_at)
values (3, N'2021-08-24 15:15:34')
insert into activity_menu(id_menu, created_at)
values (1, N'2021-08-04 15:15:34')
insert into activity_menu(id_menu, created_at)
values (2, N'2021-08-24 15:15:34')
insert into activity_menu(id_menu, created_at)
values (3, N'2021-08-11 15:15:34')
insert into activity_menu(id_menu, created_at)
values (1, N'2021-08-24 15:15:34')
insert into activity_menu(id_menu, created_at)
values (2, N'2021-08-11 15:15:34')
insert into activity_menu(id_menu, created_at)
values (3, N'2021-08-08 15:15:34')


-- activity-button
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (1, '2021-08-01 12:15:34', 'https://tinhte.vn/', 1, '202.74.58.0/23', N'HaNam', N'Jappanes', 'Mobiphone')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (1, '2021-08-04 11:15:34', 'https://www.thegioididong.com/', 1, '202.59.252.0/23', N'CaMau', N'English', 'FPT')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (2, '2021-08-14 13:15:34', 'https://fptshop.com.vn/', 1, '103.9.80.0/22', N'HaGiang', N'Francess', 'Viettel')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (3, '2021-08-22 15:15:34', 'https://dantri.com.vn/', 1, '112.3.119.116', N'SocTrang', N'Chinese', 'Viettel')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (4, '2021-07-24 22:15:34', 'https://mp3.zing.vn/', 1, '208.74.58.0/23', N'HoChiMinh', N'Chinese', 'Mobiphone')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (3, '2021-08-05 22:15:34', 'https://gunnypc.zing.vn/', 1, '201.74.58.0/23', N'NinhThuan', N'English', 'CMC')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (2, '2021-08-06 18:15:34', 'https://voz.vn/', 1, '402.74.58.0/23', N'HoChiMinh', N'Spanish', 'FPT')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (1, '2021-8-07 18:15:34', 'https://kenh14.vn/', 1, '102.74.58.0/23', N'CaMau', N'Korean', 'FPT')

insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (8, '2021-08-04 12:15:34', 'https://tinhte.vn/', 1, '202.74.58.0/23', N'HaNam', N'Jappanes', 'Mobiphone')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (8, '2021-08-04 11:15:34', 'https://www.thegioididong.com/', 1, '202.59.252.0/23', N'CaMau', N'English', 'FPT')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (7, '2021-08-14 13:15:34', 'https://fptshop.com.vn/', 1, '103.9.80.0/22', N'HaGiang', N'Francess', 'Viettel')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (6, '2021-08-24 15:15:34', 'https://dantri.com.vn/', 1, '112.3.119.116', N'SocTrang', N'Chinese', 'Viettel')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (5, '2021-07-24 22:15:34', 'https://mp3.zing.vn/', 1, '208.74.58.0/23', N'HoChiMinh', N'Chinese', 'Mobiphone')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (6, '2021-08-05 22:15:34', 'https://gunnypc.zing.vn/', 1, '201.74.58.0/23', N'NinhThuan', N'English', 'CMC')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (7, '2021-08-06 18:15:34', 'https://voz.vn/', 1, '402.74.58.0/23', N'HoChiMinh', N'Spanish', 'FPT')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (9, '2021-8-07 19:15:34', 'https://kenh14.vn/', 1, '102.74.58.0/23', N'CaMau', N'Korean', 'FPT')

insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (4, '2021-8-07 19:15:34', 'https://tinhte.vn/', 1, '202.77.58.0/23', N'HaNoi', N'Jappanes', 'Mobiphone')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (4, '2021-08-06 18:15:34', 'https://www.thegioididong.com/', 1, '202.99.252.0/23', N'CaMau', N'English', 'FPT')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (3, '2021-08-05 22:15:34', 'https://fptshop.com.vn/', 1, '103.99.80.0/22', N'HaGiang', N'Francess', 'Viettel')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (3, '2021-08-24 15:15:34', 'https://dantri.com.vn/', 1, '112.33.119.116', N'SocTrang', N'Chinese', 'Viettel')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (2, '2021-08-14 13:15:34', 'https://mp3.zing.vn/', 1, '208.77.58.0/23', N'HoChiMinh', N'Chinese', 'Mobiphone')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (4, '2021-08-04 11:15:34', 'https://gunnypc.zing.vn/', 1, '201.74.58.0/23', N'NinhThuan', N'English', 'CMC')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (4, '2021-08-04 12:15:34', 'https://voz.vn/', 1, '402.74.88.0/23', N'HoChiMinh', N'Spanish', 'FPT')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (5, '2021-08-02 12:15:34', 'https://kenh14.vn/', 1, '102.44.58.0/23', N'CaMau', N'Korean', 'FPT')

insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (1, '2021-09-02 19:15:34', 'https://tinhte.vn/', 1, '202.74.58.0/23', N'HaNam', N'Jappanes', 'Mobiphone')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (2, '2021-09-02 19:15:34', 'https://www.thegioididong.com/', 1, '202.59.252.0/23', N'CaMau', N'English', 'FPT')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (3, '2021-09-02 12:15:34', 'https://fptshop.com.vn/', 1, '103.9.80.0/22', N'HaGiang', N'Francess', 'Viettel')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (1, '2021-09-02 1:15:34', 'https://dantri.com.vn/', 1, '112.3.119.116', N'SocTrang', N'Chinese', 'Viettel')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (1, '2021-09-02 15:15:34', 'https://mp3.zing.vn/', 1, '208.74.58.0/23', N'HoChiMinh', N'Chinese', 'Mobiphone')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (2, '2021-09-02 15:15:34', 'https://gunnypc.zing.vn/', 1, '201.74.58.0/23', N'NinhThuan', N'English', 'CMC')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (2, '2021-09-02 15:15:34', 'https://voz.vn/', 1, '402.74.58.0/23', N'HoChiMinh', N'Spanish', 'FPT')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (2, '2021-09-02 09:15:34', 'https://kenh14.vn/', 1, '102.74.58.0/23', N'CaMau', N'Korean', 'FPT')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (2, '2021-09-02 08:15:34', 'https://dantri.com.vn/', 1, '112.3.119.116', N'SocTrang', N'Chinese', 'Viettel')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (1, '2021-09-02 04:15:34', 'https://mp3.zing.vn/', 1, '208.74.58.0/23', N'HoChiMinh', N'Chinese', 'Mobiphone')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (3, '2021-09-02 15:15:34', 'https://gunnypc.zing.vn/', 1, '201.74.58.0/23', N'NinhThuan', N'English', 'CMC')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (3, '2021-09-02 11:15:34', 'https://voz.vn/', 1, '402.74.58.0/23', N'HoChiMinh', N'Spanish', 'FPT')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (1, '2021-09-02 16:15:34', 'https://kenh14.vn/', 1, '102.74.58.0/23', N'CaMau', N'Korean', 'FPT')

insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (1, '2021-09-02 18:15:34', 'https://tinhte.vn/', 1, '202.74.58.0/23', N'HaNam', N'Jappanes', 'Mobiphone')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (2, '2021-09-02 18:15:34', 'https://www.thegioididong.com/', 1, '202.59.252.0/23', N'CaMau', N'English', 'FPT')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (3, '2021-09-02 11:15:34', 'https://fptshop.com.vn/', 1, '103.9.80.0/22', N'HaGiang', N'Francess', 'Viettel')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (1, '2021-09-02 12:15:34', 'https://dantri.com.vn/', 1, '112.3.119.116', N'SocTrang', N'Chinese', 'Viettel')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (1, '2021-09-02 14:15:34', 'https://mp3.zing.vn/', 1, '208.74.58.0/23', N'HoChiMinh', N'Chinese', 'Mobiphone')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (2, '2021-09-02 13:15:34', 'https://gunnypc.zing.vn/', 1, '201.74.58.0/23', N'NinhThuan', N'English', 'CMC')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (2, '2021-09-02 13:15:34', 'https://voz.vn/', 1, '402.74.58.0/23', N'HoChiMinh', N'Spanish', 'FPT')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (2, '2021-09-02 04:15:34', 'https://kenh14.vn/', 1, '102.74.58.0/23', N'CaMau', N'Korean', 'FPT')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (2, '2021-09-02 02:15:34', 'https://dantri.com.vn/', 1, '112.3.119.116', N'SocTrang', N'Chinese', 'Viettel')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (1, '2021-09-02 08:15:34', 'https://mp3.zing.vn/', 1, '208.74.58.0/23', N'HoChiMinh', N'Chinese', 'Mobiphone')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (3, '2021-09-02 15:15:34', 'https://gunnypc.zing.vn/', 1, '201.74.58.0/23', N'NinhThuan', N'English', 'CMC')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (3, '2021-09-02 11:15:34', 'https://voz.vn/', 1, '402.74.58.0/23', N'HoChiMinh', N'Spanish', 'FPT')
insert into activity_button(id_button, created_at, from_url, equipment, ip_address, user_address, languages, supplier)
values (1, '2021-09-02 16:15:34', 'https://kenh14.vn/', 1, '102.74.58.0/23', N'CaMau', N'Korean', 'FPT')

select *
from users
select *
from menu
select *
from button
select *
from modal
select *
from dataofcustomer
select *
from activity_button
select *
from activity_menu
select *
from servicefee
select *
from payment_history

select GETDATE()


select menu.id, activity_button.created_at
from activity_button
         join button on button.id = activity_button.id_button
         join menu on
    menu.id = button.id_menu
         join users on users.email = menu.email and users.email = 'vuthanhnam@gmail.com'
--Note: Button had Click----------> Menu had Action but Menu had Action ----------not sure Button had Click
-------------------------------------------------------------------------Char 1--------------------------------------------------------
select count(*) as TotalClickOnMenuEnable
from activity_button
         join button on button.id = activity_button.id_button
         join menu on menu.id = button.id_menu
         join users on users.email = menu.email
where users.email = 'vuthanhnam@gmail.com'
  and menu._status = 1
  and DATEPART(HOUR, activity_button.created_at) = 17
  and DAY(activity_button.created_at) = 19
  and MONTH(activity_button.created_at) = 8
  and YEAR(activity_button.created_at) = 2021


select count(*) as TotalClickOnMenuEnable
from activity_button
         join button on button.id = activity_button.id_button
         join menu on menu.id = button.id_menu
         join users on users.email = menu.email
where users.email = 'vuthanhnam@gmail.com'
  and menu.id = 6
  and activity_button.created_at like '2021-08-19'


-------------------------------------------------------------------------Table 1--------------------------------------------------------
-- get Total number Click on one Menu group by MenuID of username: countNumberClickMenu = Total Click all Button of this
select distinct menu.name_menu, count(*) as countNumberClickMenu
from activity_button
         join button on button.id = activity_button.id_button
         join menu on menu.id = button.id_menu
         join users on users.email = menu.email and users.email = 'vuthanhnam@gmail.com'
group by menu.name_menu

-- get Total number Click on one Menu group by MenuID of username: countNumberClickMenu = Total Click all Button of this by Time range selectn use DatetimePicker
select count(*) as countNumberClickMenu
from activity_button
         join button on button.id = activity_button.id_button
         join menu on menu.id = button.id_menu
         join users on users.email = menu.email and users.email = 'vuthanhnam@gmail.com' and activity_button.created_at
    between '2021-08-17' AND '2021-08-22' and menu.id = 6


--get Total number Action on one Menu group by MenuId of username
select distinct menu.name_menu, count(*) as countNumberActionMenu
from activity_menu
         join menu on menu.id = activity_menu.id_menu
         join users
              on users.email = menu.email
where users.email = 'vuthanhnam@gmail.com'
group by menu.name_menu

--two query on up certain have problem because action of menu
--independence with action of button (have action menu but can't action button  but have action button must action of menu  --FUCK--)
-------------------------------------------------------------------------Table 2--------------------------------------------------------
-- get Total number display of button by TypeButton
select button.type_button, count(*) as countNumberActionButtonByType
from button
         join menu on menu.id = button.id_menu
         join activity_menu
              on menu.id = activity_menu.id_menu
         join users on users.email = menu.email
where users.email = 'vuthanhnam@gmail.com'
group by button.type_button

-- get Total number Click of button by TypeButton
select button.type_button, count(*) as countNumberActionButtonByType
from activity_button
         join button on button.id = activity_button.id_button
         join menu on menu.id = button.id_menu
         join users on users.email = menu.email
where users.email = 'vuthanhnam@gmail.com'
  and button.id_menu in (select distinct activity_menu.id_menu from activity_menu)
group by button.type_button
-------------------------------------------------------------------------Table 3--------------------------------------------------------
select activity_button.created_at as ClickTime,
       menu.name_menu,
       button.name_button,
       button.link,
       activity_button.from_url
from activity_button
         join button on button.id = activity_button.id_button
         join menu on menu.id = button.id_menu
         join users on users.email = menu.email
where users.email = 'vuthanhnam@gmail.com'
  and activity_button.created_at between '2021-08-17' and '2021-08-22'

select distinct button.name_button, count(*) as countNumber
from activity_button
         join button on button.id = activity_button.id_button
         join menu on menu.id = button.id_menu
         join activity_menu
              on menu.id = activity_menu.id_menu
where menu.email = 'vuthanhnam@gmail.com'
group by button.name_button


select distinct button.name_button, count(*) as countNumberActionByButton
from button
         join menu on menu.id = button.id_menu
         join activity_menu on menu.id = activity_menu.id_menu
where menu.email = 'vuthanhnam@gmail.com'
group by button.name_button


select distinct button.name_button, count(*) as countNumberClickButton
from activity_button
         join button on button.id = activity_button.id_button
         join menu on menu.id = button.id_menu
         join users on users.email = menu.email and
                       users.email = 'vuthanhnam@gmail.com'
group by button.name_button


select equipment, count(*)
from activity_button
group by equipment
select distinct button.type_button, count(*)
from button
         join activity_button on button.id = activity_button.id_button
         join menu on menu.id = button.id_menu
where email = 'vuthanhnam@gmail.com'
group by button.type_button


select activity_button.from_url, count(*) as Total
from activity_button
         join button on button.id = activity_button.id_button
         join menu on menu.id = button.id_menu
where menu.email = 'vuthanhnam@gmail.com'
group by activity_button.from_url
order by Total desc

select *
from activity_button
where DATEPART(day, activity_button.created_at) = 31

delete
from activity_button
where DATEPART(day, activity_button.created_at) = 31

select activity_button.ip_address, user_address, count(*)
from activity_button
         join button on button.id = activity_button.id_button
         join menu on menu.id = button.id_menu
         join users on users.email = menu.email and users.email = 'vuthanhnam@gmail.com'
group by activity_button.ip_address, user_address

select distinct button.name_button, COUNT(*) as Total
from activity_button
         join button on button.id = activity_button.id_button
         join menu on menu.id = button.id_menu
where menu.email = 'namvtph09571@fpt.edu.vn'
  and activity_button.from_url = 'https://dantri.com.vn/'
group by button.name_button
order by Total desc