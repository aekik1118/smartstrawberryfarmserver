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


INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness) VALUES ('gyFarm', 'gyArea', 11,12,13);
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness) VALUES ('gyFarm', 'gyArea', 11,21,31);
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness,date_prod) VALUES ('gyFarm', 'gyArea', 11,21,31,to_timestamp('2018-05-05','YYYY-MM-DD'));
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness,date_prod) VALUES ('gyFarm', 'gyArea', 11,21,31,to_timestamp('2018-05-05','YYYY-MM-DD'));
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness) VALUES ('cyFarm', 'cyArea', 12,22,32);
INSERT INTO ENVIRONMENTINFO (farmname, area, humidity, temperature, brightness) VALUES ('cyFarm', 'gyArea', 13,23,33);
-- INSERT INTO ENVIRONMENTINFO (farmname, humidity, temperature, brightness) VALUES ('testfarm2', 112,122,123);


CREATE TABLE IF NOT EXISTS pollution (
  farmname varchar(20) not null ,
  area varchar(20) not null ,
  ispollution boolean default FALSE,
  date_prod TIMESTAMP default now(),
  CONSTRAINT no_duplicate_tag UNIQUE (farmname, area)
);

INSERT INTO pollution (farmname, area) VALUES ('testfarm', 'testArea');