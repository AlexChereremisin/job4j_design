select *
from employees
left join departments on employees.department_id = departments.id;

select *
from employees
right join departments on employees.department_id = departments.id;

select *
from employees
full join departments d on employees.department_id = d.id;

select employees.name as emploer, departments.name as department
from employees
cross join departments;

select d.name as department
from departments as d
left join employees as e on d.id = e.department_id
where e.name is null;

select *
from employees as e
left join departments as d on d.id = e.department_id;

select *
from departments as d
right join employees as e on d.id = e.department_id;

select t1.name as name1, t1.gender as gender1, t2.name as name2, t2.gender as gender2
from teens as t1
cross join teens as t2
where (t1.name = t2.name) != true;