CREATE DATABASE community;
drop table if exists member_info
create table member_info(
                            member_info_id varchar(20) primary key auto_increament,
                            member_id varchar(20) not null,
                            password varchar(50) not null,
                            nick_name varchar(15) not null,
                            email varchar(20) not null,
                            phone_number varchar(11) not null,
                            status varchar(6) not null,
                            grade varchar(6) not null,
                            created_at datetime not null,
                            updated_at datetime
)
