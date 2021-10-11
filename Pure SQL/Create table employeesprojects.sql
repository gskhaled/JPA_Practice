CREATE TABLE EmployeeProjects (
	employee int,
    project int,
    foreign key (employee) references Employees(id),
    foreign key (project) references Projects(id),
    primary key (employee, project)
);