-- Q1--
SELECT  Course.name, Count(Student.id)
FROM  Student INNER JOIN Course
on Student.course = Course.id
GROUP BY Course.name;

-- Q2--
SELECT area, code, Count(student)
FROM StudentTakesSubject
WHERE result < 50 || result = NULL
GROUP BY code
HAVING Count(Student) > 1;

-- Q3 --
SELECT *
FROM (Student INNER JOIN StudentTakesSubject ON Student.id = StudentTakesSubject.student)
INNER JOIN Subject
ON StudentTakesSubject.code = Subject.code && StudentTakesSubject.area = Subject.area;

-- Q6 --
SELECT firstname, lastname, result
FROM Student INNER JOIN StudentTakesSubject 
ON Student.id = StudentTakesSubject.student
where area = "COMP" && yearlevel = 1 && code = 0001;
