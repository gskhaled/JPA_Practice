CREATE TABLE Projects ( 
	id int not null auto_increment,
	name varchar(200) not null,
    start_date date not null,
    project_manager int,
    primary key (id),
    foreign key (project_manager) references employees(id)
);

create trigger insertionTrigger after insert on Projects for each row insert into employeeprojects values(new.project_manager, new.id);