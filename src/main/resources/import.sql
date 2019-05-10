DROP TABLE IF EXISTS ENVIRONMENTINFO;
DROP TABLE IF EXISTS pollution;
DROP SEQUENCE IF EXISTS seq_environment_info;


CREATE SEQUENCE seq_environment_info START 1;

CREATE TABLE IF NOT EXISTS ENVIRONMENTINFO (
  id integer not null DEFAULT nextval('seq_environment_info'),
  farmname varchar(20) not null ,
  area varchar(20) not null ,
  humidity float(8) not null ,
  temperature float(8) not null ,
  brightness float(8) not null ,
  date_prod TIMESTAMP default now(),
  constraint pk_environment_info primary key (id)
);


INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness) VALUES ('cyFarm', 'cyArea', 12,22,32);
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness) VALUES ('cyFarm', 'gyArea', 13,23,33);
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness,date_prod) VALUES ('gyFarm', 'gyArea', 11,21,31,to_timestamp('2019-05-01 09:30:20','YYYY-MM-DD HH:MI:SS'));
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness,date_prod) VALUES ('gyFarm', 'gyArea', 12,2,34,to_timestamp('2019-05-02 09:30:20','YYYY-MM-DD HH:MI:SS'));
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness,date_prod) VALUES ('gyFarm', 'gyArea', 13,1,61,to_timestamp('2019-05-03 09:30:20','YYYY-MM-DD HH:MI:SS'));
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness,date_prod) VALUES ('gyFarm', 'gyArea', 14,11,51,to_timestamp('2019-05-04 09:30:20','YYYY-MM-DD HH:MI:SS'));
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness,date_prod) VALUES ('gyFarm', 'gyArea', 25,21,41,to_timestamp('2019-05-05 09:30:20','YYYY-MM-DD HH:MI:SS'));
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness,date_prod) VALUES ('gyFarm', 'gyArea', 36,21,21,to_timestamp('2019-05-06 09:30:20','YYYY-MM-DD HH:MI:SS'));
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness,date_prod) VALUES ('gyFarm', 'gyArea', 35,21,31,to_timestamp('2019-05-07 09:30:20','YYYY-MM-DD HH:MI:SS'));
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness,date_prod) VALUES ('gyFarm', 'gyArea', 24,21,11,to_timestamp('2019-05-08 09:30:20','YYYY-MM-DD HH:MI:SS'));
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness,date_prod) VALUES ('gyFarm', 'gyArea', 12,21,3,to_timestamp('2019-05-09 09:30:20','YYYY-MM-DD HH:MI:SS'));
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness,date_prod) VALUES ('gyFarm', 'gyArea', 9,21,31,to_timestamp('2019-05-10 09:30:20','YYYY-MM-DD HH:MI:SS'));
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness,date_prod) VALUES ('gyFarm', 'gyArea', 22,21,21,to_timestamp('2019-05-11 09:30:20','YYYY-MM-DD HH:MI:SS'));
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness,date_prod) VALUES ('gyFarm', 'gyArea', 23,41,51,to_timestamp('2019-05-12 09:30:20','YYYY-MM-DD HH:MI:SS'));
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness,date_prod) VALUES ('gyFarm', 'gyArea', 24,51,31,to_timestamp('2019-05-13 09:30:20','YYYY-MM-DD HH:MI:SS'));


CREATE TABLE IF NOT EXISTS pollution (
  farmname varchar(20) not null ,
  area varchar(20) not null ,
  ispollution boolean default FALSE,
  date_prod TIMESTAMP default now(),
  CONSTRAINT no_duplicate_tag UNIQUE (farmname, area)
);

INSERT INTO pollution (farmname, area) VALUES ('testfarm', 'testArea');