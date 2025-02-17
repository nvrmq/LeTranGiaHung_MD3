use QuanLySinhVien;
select * from Subject where credit = (select max(credit) from subject);

select distinct * from Subject 
join Mark on Subject.SubId = Mark.SubId
where Mark.Mark = (select max(Mark) from Mark);

select *, avg(Mark.Mark) as avg_score from Student
left join Mark on Student.StudentId = Mark.StudentId
group by Student.StudentId
order by avg_score desc;