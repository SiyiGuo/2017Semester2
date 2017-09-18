-- -----------------------------------------------------
-- if you are running this on YOUR OWN computer remove the comments from the following 3 commands
-- -----------------------------------------------------
-- DROP SCHEMA IF EXISTS `University` ;
-- CREATE SCHEMA IF NOT EXISTS `University` DEFAULT CHARACTER SET utf8 ;
-- USE `University` ;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0;


--
-- Table structure for table `Course`
--

DROP TABLE IF EXISTS `Course`;

CREATE TABLE `Course` (
  `id` char(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `level` char(1) NOT NULL,
  `creditpoints` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Course_CourseLevel1_idx` (`level`),
  CONSTRAINT `fk_Course_CourseLevel1` FOREIGN KEY (`level`) REFERENCES `CourseLevel` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `CourseLevel`
--
DROP TABLE IF EXISTS `CourseLevel`;

CREATE TABLE `CourseLevel` (
  `id` char(1) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--
-- Table structure for table `Lecturer`
--
DROP TABLE IF EXISTS `Lecturer`;

CREATE TABLE `Lecturer` (
  `id` mediumint(8) unsigned NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `rank` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `Student`
--
DROP TABLE IF EXISTS `Student`;

CREATE TABLE `Student` (
  `id` mediumint(8) unsigned NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `course` char(10) NOT NULL,
  `postcode` char(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Student_suburb1_idx` (`postcode`),
  KEY `fk_Student_Course1_idx` (`course`),
  CONSTRAINT `fk_Student_Course1` FOREIGN KEY (`course`) REFERENCES `Course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Student_suburb1` FOREIGN KEY (`postcode`) REFERENCES `Suburb` (`postcode`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `StudentTakesSubject`
--

DROP TABLE IF EXISTS `StudentTakesSubject`;
CREATE TABLE `StudentTakesSubject` (
  `student` mediumint(8) unsigned NOT NULL,
  `area` char(4) NOT NULL,
  `yearlevel` tinyint(3) unsigned NOT NULL,
  `code` char(4) NOT NULL,
  `year` year(4) NOT NULL,
  `sem` enum('1','2') NOT NULL,
  `result` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`student`,`area`,`yearlevel`,`code`,`year`,`sem`),
  KEY `fk_StudentTakesSubject_Subject1_idx` (`area`,`yearlevel`,`code`),
  CONSTRAINT `fk_StudentTakesSubject_Student` FOREIGN KEY (`student`) REFERENCES `Student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_StudentTakesSubject_Subject1` FOREIGN KEY (`area`, `yearlevel`, `code`) REFERENCES `Subject` (`area`, `yearlevel`, `code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `StudyArea`
--

DROP TABLE IF EXISTS `StudyArea`;

CREATE TABLE `StudyArea` (
  `id` char(4) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `Subject`
--

DROP TABLE IF EXISTS `Subject`;

CREATE TABLE `Subject` (
  `area` char(4) NOT NULL,
  `yearlevel` tinyint(3) unsigned NOT NULL,
  `code` char(4) NOT NULL,
  `name` varchar(50) NOT NULL,
  `creditpoints` decimal(3,1) unsigned NOT NULL,
  `lecturer` mediumint(8) unsigned DEFAULT NULL,
  PRIMARY KEY (`area`,`yearlevel`,`code`),
  KEY `fk_Subject_YearLevel1_idx` (`yearlevel`),
  KEY `fk_Subject_Lecturer1_idx` (`lecturer`),
  CONSTRAINT `fk_Subject_Lecturer1` FOREIGN KEY (`lecturer`) REFERENCES `Lecturer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Subject_StudyArea1` FOREIGN KEY (`area`) REFERENCES `StudyArea` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Subject_YearLevel1` FOREIGN KEY (`yearlevel`) REFERENCES `YearLevel` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `Suburb`
--

DROP TABLE IF EXISTS `Suburb`;

CREATE TABLE `Suburb` (
  `postcode` char(4) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`postcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `YearLevel`
--

DROP TABLE IF EXISTS `YearLevel`;

CREATE TABLE `YearLevel` (
  `id` tinyint(3) unsigned NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- adding data to each table
--
-- INSERT data for table `Course`
--

--
-- INSERT data for table `Suburb`
--

INSERT INTO `Suburb` VALUES 
('3000','MELBOURNE'),
('3002','EAST MELBOURNE'),
('3003','WEST MELBOURNE'),
('3004','MELBOURNE'),
('3005','WORLD TRADE CENTRE'),
('3006','SOUTHBANK'),
('3008','DOCKLANDS'),
('3010','UNIVERSITY OF MELBOURNE'),
('3011','FOOTSCRAY'),
('3012','BROOKLYN'),
('3013','YARRAVILLE'),
('3015','NEWPORT'),
('3016','WILLIAMSTOWN'),
('3018','ALTONA'),
('3019','BRAYBROOK'),
('3020','ALBION'),
('3021','ALBANVALE'),
('3022','ARDEER'),
('3023','BURNSIDE'),
('3025','ALTONA EAST'),
('3026','LAVERTON NORTH'),
('3028','ALTONA MEADOWS'),
('3031','FLEMINGTON'),
('3032','ASCOT VALE'),
('3033','KEILOR EAST'),
('3034','AVONDALE HEIGHTS'),
('3036','KEILOR'),
('3037','CALDER PARK'),
('3038','KEILOR DOWNS'),
('3039','MOONEE PONDS'),
('3040','ABERFELDIE'),
('3041','ESSENDON NORTH'),
('3042','AIRPORT WEST'),
('3043','GLADSTONE PARK'),
('3044','PASCOE VALE'),
('3045','MELBOURNE AIRPORT'),
('3046','GLENROY'),
('3047','BROADMEADOWS'),
('3048','COOLAROO'),
('3049','ATTWOOD'),
('3050','ROYAL MELBOURNE HOSPITAL'),
('3051','HOTHAM HILL'),
('3052','MELBOURNE UNIVERSITY'),
('3053','CARLTON'),
('3054','CARLTON NORTH'),
('3055','BRUNSWICK SOUTH'),
('3056','BRUNSWICK'),
('3057','BRUNSWICK EAST'),
('3058','BATMAN'),
('3060','FAWKNER'),
('3061','CAMPBELLFIELD'),
('3062','SOMERTON'),
('3065','FITZROY'),
('3066','COLLINGWOOD'),
('3067','ABBOTSFORD'),
('3068','CLIFTON HILL'),
('3070','NORTHCOTE'),
('3071','THORNBURY'),
('3072','GILBERTON'),
('3109','DONCASTER EAST'),
('3110','NUNAWADING BUSINESS CENTRE'),
('3121','BURNLEY'),
('3122','AUBURN SOUTH'),
('3123','AUBURN'),
('3124','CAMBERWELL'),
('3125','BENNETTSWOOD'),
('3126','CAMBERWELL EAST'),
('3127','MONT ALBERT'),
('3128','BOX HILL'),
('3129','BOX HILL NORTH'),
('3130','BLACKBURN'),
('3131','BRENTFORD SQUARE'),
('3132','MITCHAM'),
('3133','VERMONT'),
('3134','RINGWOOD'),
('3135','BEDFORD ROAD'),
('3136','CROYDON'),
('3137','KILSYTH'),
('3141','CHAPEL STREET NORTH'),
('3142','HAWKSBURN'),
('3143','ARMADALE'),
('3144','KOOYONG'),
('3145','CAULFIELD EAST'),
('3146','GLEN IRIS'),
('3147','ASHBURTON'),
('3148','CHADSTONE'),
('3149','MOUNT WAVERLEY'),
('3150','GLEN WAVERLEY'),
('3151','BURWOOD EAST'),
('3161','CAULFIELD JUNCTION'),
('3162','CAULFIELD'),
('3163','CARNEGIE'),
('3165','BENTLEIGH EAST'),
('3166','HUGHESDALE'),
('3167','OAKLEIGH SOUTH'),
('3168','CLAYTON'),
('3169','CLARINDA'),
('3170','MULGRAVE'),
('3181','PRAHRAN'),
('3182','ST KILDA'),
('3183','BALACLAVA'),
('3184','BRIGHTON ROAD'),
('3185','ELSTERNWICK'),
('3186','BRIGHTON'),
('3187','BRIGHTON EAST'),
('3188','HAMPTON'),
('3189','MOORABBIN'),
('3190','HIGHETT'),
('3191','SANDRINGHAM'),
('3192','CHELTENHAM'),
('3193','BEAUMARIS'),
('3194','MENTONE'),
('3195','ASPENDALE'),
('3202','HEATHERTON'),
('3204','BENTLEIGH'),
('3205','SOUTH MELBOURNE'),
('3206','ALBERT PARK'),
('3207','FISHERMANS BEND');


--
-- INSERT data for table `YearLevel`
--

INSERT INTO `YearLevel` VALUES 
(1,'first'),
(2,'second'),
(3,'third'),
(4,'fourth'),
(5,'fifth'),
(6,'sixth'),
(9,'postgrad');

--
-- INSERT data for table `CourseLevel`
--

INSERT INTO `CourseLevel` VALUES 
('P','postgraduate'),
('U','undergraduate');


INSERT INTO `Course` VALUES 
('B-DES','Bachelor of Design','U',300),
('B-SCI','Bachelor of Science','U',300),
('MC-IS','Master of Information Systems','P',200),
('MC-IT','Master of Information Technology','P',200);


--
-- INSERT data for table `Lecturer`
--

INSERT INTO `Lecturer` VALUES 
(111111,'Alan','Turing','B'),
(222222,'Ada','Lovelace','B'),
(333333,'John','Von Neumann','C'),
(444444,'Grace','Hopper','C'),
(555555,'Thomas','Watson','C'),
(666666,'Katherine','Johnson','A'),
(777777,'Mary','Jackson','B'),
(888888,'John','Harrison','A');


--
-- INSERT data for table `Student`
--


INSERT INTO `Student` VALUES 
(123001,'Britteny','Abston','MC-IT','3161'),
(123002,'Margy','Alter','MC-IT','3018'),
(123003,'Freida','Amaral','MC-IT','3072'),
(123004,'Kera','Basham','MC-IT','3145'),
(123005,'Barbra','Batchelor','MC-IT','3046'),
(123006,'Lon','Belew','B-SCI','3195'),
(123007,'Heide','Bergen','MC-IT','3170'),
(123008,'Susan','Binion','MC-IT','3110'),
(123009,'Ouida','Bisceglia','MC-IT','3133'),
(123010,'Wai','Bruton','B-SCI','3161'),
(123011,'Joane','Cargile','B-SCI','3062'),
(123012,'Wes','Christen','B-SCI','3151'),
(123013,'Jung','Danna','MC-IT','3165'),
(123014,'Milford','Desjardin','MC-IT','3043'),
(123015,'Huong','Dodson','MC-IT','3110'),
(123016,'Maegan','Eason','MC-IT','3068'),
(123017,'Nelia','Espinosa','MC-IT','3146'),
(123018,'Roseline','Francia','B-SCI','3068'),
(123019,'Cathie','Froehlich','MC-IT','3110'),
(123020,'Irena','Gaughan','MC-IT','3126'),
(123021,'Thelma','Genest','MC-IT','3046'),
(123022,'Karla','Goodreau','MC-IT','3070'),
(123023,'Carri','Gotto','MC-IT','3195'),
(123024,'Cornelia','Goudeau','MC-IT','3129'),
(123025,'Laticia','Guzzi','MC-IT','3031'),
(123026,'Jettie','Hampson','MC-IT','3050'),
(123027,'Collene','Haverly','MC-IT','3044'),
(123028,'Lue','Helsel','MC-IT','3072'),
(123029,'Keesha','Hillsman','MC-IT','3072'),
(123030,'Sally','Hodge','MC-IT','3190'),
(123031,'Kaye','Holcombe','MC-IT','3195'),
(123032,'Avril','Howse','MC-IT','3151'),
(123033,'Sherrell','Hoying','MC-IT','3016'),
(123034,'Vinita','Huebner','MC-IT','3170'),
(123035,'Florrie','Jenning','MC-IT','3072'),
(123036,'Rudolf','Ketterman','B-SCI','3170'),
(123037,'Fidelia','Khang','B-SCI','3072'),
(123038,'Rheba','Kingsley','MC-IT','3194'),
(123039,'Shantay','Kisner','MC-IT','3061'),
(123040,'Ardelia','Kleckner','MC-IT','3195'),
(123041,'Birdie','Kottwitz','B-SCI','3163'),
(123042,'Yoko','Kravitz','B-DES','3072'),
(123043,'Skye','Leake','B-DES','3130'),
(123044,'Heriberto','Leavell','B-DES','3170'),
(123045,'Lyn','Low','B-DES','3151'),
(123046,'Lesia','Marcellus','B-DES','3023'),
(123047,'Norah','Mccusker','B-DES','3072'),
(123048,'Adelia','Mcentire','B-DES','3110'),
(123049,'Vita','Mcmickle','B-DES','3058'),
(123050,'Theola','Mcnerney','B-DES','3053'),
(123051,'Marielle','Mcphee','MC-IS','3053'),
(123052,'Reita','Meekins','MC-IS','3165'),
(123053,'Helen','Mendiola','MC-IS','3011'),
(123054,'Connie','Menjivar','MC-IS','3151'),
(123055,'Shaunta','Millner','B-SCI','3053'),
(123056,'Season','Mitchum','MC-IS','3131'),
(123057,'Deidra','Murphy','MC-IS','3072'),
(123058,'Vivienne','Nellis','MC-IS','3072'),
(123059,'Mervin','Nolette','MC-IS','3072'),
(123060,'Fletcher','Nolte','B-SCI','3072'),
(123061,'Sharita','Orner','B-SCI','3181'),
(123062,'Marg','Pasquale','B-SCI','3166'),
(123063,'Percy','Pellegren','B-SCI','3170'),
(123064,'Twanda','Perfecto','B-SCI','3151'),
(123065,'Latrice','Pulliam','B-SCI','3072'),
(123066,'Hayley','Rm','B-SCI','3072'),
(123067,'Davida','Rodes','B-SCI','3058'),
(123068,'Michele','Roussell','B-SCI','3170'),
(123069,'Keitha','Saini','B-SCI','3072'),
(123070,'Luigi','Sample','B-SCI','3123'),
(123071,'Elia','Schlachter','B-SCI','3072'),
(123072,'Anh','Schreck','B-SCI','3072'),
(123073,'Katherin','Schrom','B-SCI','3023'),
(123074,'Jerrell','Shakespeare','B-SCI','3163'),
(123075,'Jenna','Shin','B-SCI','3186'),
(123076,'Darci','Shoaff','B-SCI','3010'),
(123077,'Julieta','Shull','B-SCI','3110'),
(123078,'Daisy','Sloan','B-SCI','3149'),
(123079,'Crista','Sova','B-SCI','3195'),
(123080,'Marketta','Stephan','B-SCI','3011'),
(123081,'Su','Strack','MC-IS','3072'),
(123082,'Alec','Stutts','MC-IS','3040'),
(123083,'Ebony','Summa','MC-IS','3193'),
(123084,'Elia','Talmadge','MC-IS','3003'),
(123085,'Arla','Thurgood','MC-IS','3072'),
(123086,'Dorine','Tullos','MC-IS','3028'),
(123087,'Kimberely','Wareham','MC-IS','3195'),
(123088,'Lina','Wentzel','MC-IS','3049'),
(123089,'Amee','Westra','MC-IS','3167'),
(123090,'Carmelina','Wigton','MC-IS','3072'),
(123091,'Darryl','Wang','MC-IS','3133'),
(123092,'Florine','Li','MC-IS','3026'),
(123093,'Bryce','Zhang','MC-IS','3002'),
(123094,'Joann','Liu','MC-IS','3170'),
(123095,'Liana','Chen','MC-IS','3060'),
(123096,'Dania','Yang','MC-IS','3170'),
(123097,'Loris','Huang','MC-IS','3170'),
(123098,'Jeni','Zhao','MC-IS','3132'),
(123099,'Danyelle','Wu','MC-IS','3055'),
(123100,'Katelin','Zhou','MC-IS','3072');




--
-- INSERT data for table `StudyArea`
--


INSERT INTO `StudyArea` VALUES 
('COMP','Computer Science'),
('INFO','Informatics'),
('ISYS','Information Systems');


--
-- INSERT data for table `Subject`
--


INSERT INTO `Subject` VALUES 
('COMP',1,'0001','Foundations of Computing',12.5,333333),
('COMP',1,'0002','Foundations of Algorithims',12.5,777777),
('COMP',2,'0003','Alogorithms and Data Structures',12.5,777777),
('COMP',9,'0007','Internet Technologies',12.5,111111),
('COMP',9,'0038','Algorithms and Complexity',12.5,444444),
('COMP',9,'0041','Programming and Software Development',12.5,222222),
('INFO',2,'0002','Foundations of Informatics',12.5,666666),
('INFO',2,'0003','Database Systems',12.5,222222),
('INFO',3,'0825','Industry Based Training',25.0,444444),
('INFO',9,'0002','Database Systems & Information Modelling',12.5,222222),
('ISYS',1,'0001','Foundations of Information Systems',12.5,888888),
('ISYS',3,'0008','Business Analytics',12.5,666666),
('ISYS',9,'0026','Fundamentals of Information Systems',12.5,111111),
('ISYS',9,'0049','Business Analysis Modelling and Design',12.5,444444),
('ISYS',9,'0081','Organisational Processes',12.5,111111);

--
-- INSERT data for table `StudentTakesSubject`
--

INSERT INTO `StudentTakesSubject` VALUES 
(123006,'COMP',1,'0001',2015,'1',73),
(123006,'COMP',1,'0002',2015,'2',94),
(123006,'COMP',2,'0003',2016,'1',81),
(123006,'INFO',2,'0003',2016,'2',38),
(123006,'INFO',2,'0003',2017,'1',73),
(123006,'ISYS',1,'0001',2016,'2',93),
(123008,'COMP',9,'0007',2016,'1',82),
(123008,'COMP',9,'0038',2016,'2',77),
(123008,'COMP',9,'0041',2016,'1',45),
(123008,'COMP',9,'0041',2017,'1',NULL),
(123008,'INFO',9,'0002',2015,'1',77),
(123008,'ISYS',9,'0026',2015,'2',82),
(123008,'ISYS',9,'0049',2016,'1',88),
(123008,'ISYS',9,'0081',2015,'2',65),
(123009,'INFO',9,'0002',2015,'1',71),
(123009,'ISYS',9,'0026',2015,'2',89),
(123009,'ISYS',9,'0081',2015,'2',61),
(123010,'COMP',1,'0001',2016,'2',77),
(123010,'COMP',1,'0002',2016,'1',82),
(123010,'COMP',2,'0003',2016,'1',45),
(123010,'COMP',2,'0003',2017,'1',NULL),
(123010,'INFO',2,'0002',2015,'2',65),
(123010,'INFO',2,'0003',2015,'1',77),
(123010,'ISYS',1,'0001',2015,'2',82),
(123010,'ISYS',3,'0008',2016,'1',88),
(123011,'INFO',2,'0002',2015,'2',60),
(123011,'INFO',2,'0003',2015,'1',70),
(123011,'ISYS',1,'0001',2015,'2',88),
(123012,'INFO',2,'0002',2015,'2',77),
(123012,'INFO',2,'0003',2015,'1',55),
(123012,'ISYS',1,'0001',2015,'2',66),
(123018,'COMP',1,'0001',2015,'1',91),
(123018,'COMP',1,'0002',2015,'2',85),
(123018,'COMP',2,'0003',2016,'1',75),
(123018,'INFO',2,'0003',2016,'2',59),
(123018,'ISYS',1,'0001',2016,'2',78),
(123018,'ISYS',3,'0008',2017,'1',76),
(123036,'COMP',1,'0001',2015,'1',71),
(123036,'COMP',1,'0002',2015,'2',79),
(123036,'COMP',2,'0003',2016,'1',72),
(123036,'INFO',2,'0003',2016,'2',78),
(123036,'ISYS',1,'0001',2016,'2',81),
(123036,'ISYS',3,'0008',2017,'1',72),
(123037,'INFO',2,'0003',2016,'2',49),
(123037,'INFO',2,'0003',2017,'1',NULL),
(123039,'COMP',9,'0007',2017,'2',68),
(123039,'INFO',9,'0002',2017,'2',74),
(123041,'INFO',2,'0002',2017,'1',89),
(123041,'ISYS',3,'0008',2017,'1',93),
(123055,'COMP',1,'0001',2015,'1',74),
(123055,'COMP',1,'0002',2015,'2',71),
(123055,'COMP',2,'0003',2016,'1',74),
(123055,'INFO',2,'0003',2016,'2',73),
(123055,'ISYS',1,'0001',2016,'2',81),
(123055,'ISYS',3,'0008',2017,'1',68);


-- 
-- END

drop table *;