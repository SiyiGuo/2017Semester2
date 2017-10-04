-- Q1--
SELECT  Course.name, COUNT(Student.id) AS Number_of_Student
FROM  Student INNER JOIN Course
ON Student.course = Course.id
GROUP BY Course.name;

-- Q2--
SELECT CONCAT(area, yearlevel, code) AS Subject, COUNT(student) AS Number_of_Student
FROM StudentTakesSubject
WHERE result < 50 || result = NULL
GROUP BY CONCAT(area, yearlevel, code)
HAVING COUNT(Student) > 1;

-- Q3 --
SELECT Student, Course.creditpoints - SUM(if(result >= 50 , Subject.creditpoints, 0)) AS Credit_to_Complete
FROM Course INNER JOIN Student INNER JOIN StudentTakesSubject STS INNER JOIN Subject
ON Course.id = Student.course AND Student.id = STS.student 
AND STS.area = Subject.area AND STS.yearlevel = Subject.yearlevel AND STS.code = Subject.code
WHERE STS.yearlevel < 9
GROUP BY Student

-- Q4 -- 
SELECT id as student_number, lastname, course, SUM(result * creditpoints) / SUM(creditpoints) as GPA
FROM Student INNER JOIN StudentTakesSubject STS INNER JOIN Subject
ON id = student AND STS.area = Subject.area AND STS.code = Subject.code AND Subject.yearlevel = STS.yearlevel
WHERE STS.yearlevel < 9
GROUP BY id
HAVING COUNT(STS.yearlevel) > 4;

-- Q5 --
SELECT CONCAT(firstname, " ", lastname) AS fullname, result, CONCAT(STS.Area, STS.yearlevel, STS.code) AS subjec_code
FROM Lecturer INNER JOIN Subject INNER JOIN StudentTakesSubject STS
ON id = lecturer 
AND Subject.area = STS.area AND Subject.code = STS.code AND Subject.yearlevel = STS.yearlevel
WHERE result = (SELECT MAX(result) FROM StudentTakesSubject);

-- Q6 --
SELECT CONCAT(firstname, ' ', lastname) AS name,  result,
(CASE
	WHEN result >= 80 THEN "H1"
    WHEN result >= 75 THEN "H2A"
    WHEN result >= 70 THEN "H2B"
    WHEN result >= 65 THEN "H3"
    WHEN result >= 50 THEN "P"
    WHEN result = 49 THEN "NH"
    WHEN result >= 0 THEN "N"
    ELSE "UNKNOWN"
END) AS AcademicGrade
FROM Student INNER JOIN StudentTakesSubject 
ON Student.id = StudentTakesSubject.student
WHERE area = "COMP" && yearlevel = 1 && code = 0001;

-- Q7 --
SELECT id AS lecturer_id, CONCAT(firstname, ' ', lastname) AS lecturer_name
FROM Lecturer INNER JOIN Subject
ON id = lecturer
WHERE id IN (SELECT id FROM Lecturer INNER JOIN Subject ON id = lecturer WHERE yearlevel >= 9)
AND yearlevel <9

-- Q8 --
SELECT Lecturer.id AS lecturer_id, CONCAT(firstname, ' ', lastname) AS lecturer_name
FROM Lecturer INNER JOIN Subject INNER JOIN StudyArea
ON Lecturer.id = Subject.lecturer AND StudyArea.id = area
GROUP BY Lecturer.id
HAVING COUNT(DISTINCT area) = (SELECT COUNT(DISTINCT name) FROM StudyArea)

-- Q9 --
SELECT Student, CONCAT(firstname, ' ', lastname) AS Student_name
FROM StudentTakesSubject STS INNER JOIN Student INNER JOIN Suburb
ON STS.student = Student.id AND Student.postcode = Suburb.postcode
WHERE Student.course = "B-SCI" AND Suburb.name = "Gilberton" AND yearlevel < 9
GROUP BY Student
HAVING COUNT(DISTINCT CONCAT(area, yearlevel, STS.code)) < COUNT(CONCAT(area, yearlevel, STS.code))

-- Q10 --
DROP TABLE IF EXISTS `Evaluation`;
CREATE TABLE `Evaluation` (
  `lecturer_id` mediumint(8) unsigned NOT NULL,
  `area` char(4) NOT NULL,
  `yearlevel` tinyint(3) unsigned NOT NULL,
  `code` char(4) NOT NULL,
  `student_id` mediumint(8) unsigned NOT NULL,
  `evaluation_score` tinyint(3) NOT NULL,
  `comment` text,
  PRIMARY KEY(`lecturer_id`),
  KEY `fk_Evaluation_Student` (`student_id`),
  KEY `fk_Evaluation_Subject1_idx` (`area`,`yearlevel`,`code`),
  CONSTRAINT `fk_Evaluation_Lecture` FOREIGN KEY (`lecturer_id`) REFERENCES `Lecturer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Evaluation_Student` FOREIGN KEY (`student_id`) REFERENCES `Student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Evaluation_Subject1` FOREIGN KEY (`area`, `yearlevel`, `code`) REFERENCES `Subject` (`area`, `yearlevel`, `code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
