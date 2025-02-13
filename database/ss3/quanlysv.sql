use QuanLySinhVien;
Select * from Student where StudenName like '% % h%';
Select * from Class where StartDate like '%-12-%';
Select * from Subject where Credit between 3 and 5;
update Student set ClassId =2 where name like '% % Hung';
Select StudentName, SubName, point from student;
order by point DESC, StudentName ASC;