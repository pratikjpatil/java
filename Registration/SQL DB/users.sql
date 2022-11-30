create database users;
use users;
create table registration(
	ID int NOT NULL PRIMARY KEY,
    name varchar(30), 
    address varchar(100),
    gender char(10),
    age int, 
    mobile varchar(13), 
    email varchar(30)
    );
desc registration;


