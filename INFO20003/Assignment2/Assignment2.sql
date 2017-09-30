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
SELECT Student, Course.creditpoints - sum(Subject.creditpoints)
FROM Course INNER JOIN Student INNER JOIN StudentTakesSubject STS INNER JOIN Subject
ON Course.id = Student.course AND Student.id = STS.student 
AND STS.area = Subject.area AND STS.yearlevel = Subject.yearlevel AND STS.code = Subject.code
WHERE Student IN (SELECT DISTINCT Student from Student INNER JOIN StudentTakesSubject on id = student WHERE yearlevel < 9)
GROUP BY Student

-- Q4 -- 
SELECT id as student_number, lastname, course, sum(result * creditpoints) / sum(creditpoints) as GPA
FROM Student INNER JOIN StudentTakesSubject STS INNER JOIN Subject
ON id = student and STS.area = Subject.area and STS.code = Subject.code and Subject.yearlevel = STS.yearlevel
WHERE STS.yearlevel < 9
GROUP BY id
HAVING COUNT(STS.yearlevel) > 4;

-- Q5 --
SELECT CONCAT(firstname, " ", lastname) as fullname, (result), CONCAT(STS.Area, STS.yearlevel, STS.code) as subjec_code
FROM Lecturer INNER JOIN Subject INNER JOIN StudentTakesSubject STS
ON id = lecturer AND Subject.area = STS.area AND Subject.code = STS.code AND Subject.yearlevel = STS.yearlevel
WHERE result = (SELECT MAX(result) FROM StudentTakesSubject);

-- Q6 --
SELECT CONCAT(firstname, ' ', lastname) as name,  result,
(CASE
	WHEN result >= 80 THEN "H1"
    WHEN result >= 75 THEN "H2A"
    WHEN result >= 70 THEN "H2B"
    WHEN result >= 65 THEN "H3"
    WHEN result >= 50 THEN "P"
    WHEN result = 49 THEN "NH"
    WHEN result >= 0 THEN "N"
    ELSE "UNKNOWN"
END) as AcademicGrade
FROM Student INNER JOIN StudentTakesSubject 
ON Student.id = StudentTakesSubject.student
WHERE area = "COMP" && yearlevel = 1 && code = 0001;

-- Q7 --
SELECT id AS lecturer_id, CONCAT(firstname, ' ', lastname) as lecturer_name
FROM Lecturer INNER JOIN Subject
ON id = lecturer
WHERE id IN (SELECT id from Lecturer INNER JOIN Subject ON id = lecturer WHERE yearlevel >= 9)
AND yearlevel <9

-- Q8 --
SELECT Lecturer.id AS lecturer_id, CONCAT(firstname, ' ', lastname) as lecturer_name
FROM Lecturer INNER JOIN Subject INNER JOIN StudyArea
ON Lecturer.id = Subject.lecturer AND StudyArea.id = area
GROUP BY Lecturer.id
HAVING COUNT(DISTINCT area) = (SELECT COUNT(DISTINCT area) FROM Subject)

-- Q9 --
SELECT Student, CONCAT(firstname, ' ', lastname) as Student_name
FROM StudentTakesSubject STS INNER JOIN Student INNER JOIN Suburb
ON STS.student = Student.id AND Student.postcode = Suburb.postcode
WHERE Student.course = "B-SCI" AND Suburb.name = "Gilberton" AND yearlevel < 9
GROUP BY Student
HAVING COUNT(DISTINCT CONCAT(area, yearlevel, STS.code)) < COUNT(CONCAT(area, yearlevel, STS.code))

-- Q10 --
CREATE TABLE `Evaluation` (
  `lecturer_id` MEDIUMINT(8) NOT NULL,
  `area` char(4) NOT NULL,
  `yearlevel` tinyint(3) unsigned NOT NULL,
  `code` char(4) NOT NULL,
  `student_id` MEDIUMINT(8) NOT NULL,
  `evaluation_score` TINYINT(3) NOT NULL,
  `comment` TEXT,
  Evaluation
  PRIMARY KEY(`lecturer_id`),
  KEY (`area`,`yearlevel`,`code`),
  KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

