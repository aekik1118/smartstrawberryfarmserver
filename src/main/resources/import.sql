DROP TABLE IF EXISTS ENVIRONMENTINFO;
DROP SEQUENCE IF EXISTS seq_environment_info;


CREATE SEQUENCE seq_environment_info START 1;

CREATE TABLE IF NOT EXISTS ENVIRONMENTINFO (
  id integer not null DEFAULT nextval('seq_environment_info'),
  farmname varchar(20) not null ,
  humidity float(8) not null ,
  temperature float(8) not null ,
  brightness float(8) not null ,
  date_prod TIMESTAMP default now(),
  constraint pk_environment_info primary key (id)
);

INSERT INTO ENVIRONMENTINFO (farmname, humidity, temperature, brightness) VALUES ('testfarm', 11,12,13);
-- INSERT INTO ENVIRONMENTINFO (farmname, humidity, temperature, brightness) VALUES ('testfarm2', 112,122,123);






