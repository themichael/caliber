--------------------------------------------------------
-- Configures users in the database. 
-- 		Run with schema.sql to have the users needed
-- 		to access Caliber application.
--	Do not delete trainers after your tests.
-- Author: Patrick Walsh
--------------------------------------------------------

--------------------------------------------------------
--  DDL for Sequence TRAINER_ID_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "CALIBER"."TRAINER_ID_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;

-------------TRAINER-------------
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'patrick.walsh@revature.com', 'Patrick Walsh', 'Lead Trainer', 'ROLE_VP');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'pjw6193@hotmail.com', 'Dan Pickles', 'Lead Trainer', 'ROLE_VP');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'ravi.singh@revature.com', 'Ravi Singh', 'Vice President of Technology', 'ROLE_VP');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'karan.dhirar@revature.com', 'Karan Dhirar', 'Technology Manager', 'ROLE_VP');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'brian.connolly@revature.com', 'Brian Connolly', 'Senior Java Developer', 'ROLE_VP');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'genesis.bonds@revature.com', 'Genesis Bonds', 'Trainer', 'ROLE_TRAINER');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'ankit.garg@revature.com', 'Ankit Garg', 'Lead Trainer', 'ROLE_TRAINER');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'ryan.lessley@revature.com', 'Ryan Lessley', 'Senior Trainer', 'ROLE_TRAINER');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'steven.kelsey@revature.com', 'Steven Kelsey', 'Senior Trainer', 'ROLE_TRAINER');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'emily.higgins@revature.com', 'Emily Higgins', 'Trainer', 'ROLE_TRAINER');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'taylor.kemper@revature.com', 'Taylor Kemper', 'Senior Trainer', 'ROLE_TRAINER');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'richard.orr@revature.com', 'Richard Orr', 'Trainer', 'ROLE_TRAINER');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'nickolas.jurczak@revature.com', 'Nickolas Jurczak', 'Trainer', 'ROLE_TRAINER');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'august.duet@revature.com', 'August Duet', 'Trainer', 'ROLE_TRAINER');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'yuvarajd@revature.com', 'Yuvaraj Damodaran', 'Lead Trainer', 'ROLE_TRAINER');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'fred.belotte@revature.com', 'Fred Belotte', 'Senior Trainer', 'ROLE_TRAINER');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'mehrab.rahman@revature.com', 'Mehrab Rahman', 'Trainer', 'ROLE_TRAINER');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'peter.alagna@revature.com', 'Peter Alagna', 'Trainer', 'ROLE_TRAINER');
--------------------------------------------------------------------------------------------------------------------MAKE PANEL ROLE
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'rajeshy@revature.com', 'Rajesh Yamunachari', 'Senior Technical Manager', 'ROLE_QC');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'raghavan@revature.com', 'Raghavan Swaminathan', 'Senior Technical Manager', 'ROLE_QC');
-------------TRAINER END-------------
COMMIT;