-- Selects all employees with their information
select * from employees;
-- Selects all employees within a specific project (using project name)
select * from projects as p inner join employeeprojects as ep on ep.project = p.id inner join employees as e on e.id = ep.employee where p.name = 'electricity';
-- Selects all employees with a specific department that are not working on any projects
select * from employees as e where e.role = 'se' and not exists (select employee from employeeprojects where employee = e.id);
-- Selects all employees that are not working on any projects
select * from employees as e where not exists (select employee from employeeprojects where e.id = employee);