CREATE TABLE Employees (
	id int not null auto_increment,
	name varchar(50) not null,
    email varchar(50) not null unique,
    phone_number varchar(13) not null unique,
    age tinyint not null,
    nationalID varchar(14) not null unique,
    rolee varchar(20),
    primary key (id),
    foreign key (rolee) references roles(name)
);