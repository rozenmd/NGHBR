/*Create Tables*/
CREATE TABLE IF NOT EXISTS USER_PROFILE
(id int(11) NOT NULL auto_increment primary key,
 type varchar(45) NOT NULL
 );
 
CREATE TABLE IF NOT EXISTS APP_USER
(id int(11) NOT NULL auto_increment primary key,
 password varchar(45) NOT NULL,
 sso_id varchar(45) NOT NULL,
 first_name varchar(45),
 last_name varchar(45),
 email varchar(200),
 state varchar(45)
 );
 
CREATE TABLE IF NOT EXISTS APP_USER_USER_PROFILE
(USER_ID int(11) NOT NULL,
 USER_PROFILE_ID int(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS APP_ITEM
(ITEM_ID int(11) NOT NULL auto_increment primary key,
 DESCRIPTION varchar(500),
 NAME varchar(100),
 START_DATE datetime,
 END_DATE datetime,
 OWNER_ID int(11),
 BORROWER_ID int(11)
);

CREATE TABLE IF NOT EXISTS APP_MESSAGE
( id int(11) not null auto_increment primary key,
  POSTCODE int(11),
  STATE varchar(255),
  TEXT varchar(255),
  USERNAME varchar(255)
);

/* Populate USER_PROFILE Table */
INSERT INTO USER_PROFILE(type)
VALUES ('USER');

INSERT INTO USER_PROFILE(type)
VALUES ('ADMIN');

INSERT INTO USER_PROFILE(type)
VALUES ('DBA');

/* Populate APP_USER Table */
INSERT INTO APP_USER(sso_id, password, first_name, last_name, email, state)
VALUES ('khanh','abc123', 'Khanh','Nguyen','khanh@nghbr.com', 'Active');

INSERT INTO APP_USER(sso_id, password, first_name, last_name, email, state)
VALUES ('imran','abc124', 'Imran','Khan','imran@nghbr.com', 'Active');

INSERT INTO APP_USER(sso_id, password, first_name, last_name, email, state)
VALUES ('max','abc125', 'Max','Rozen','maxy@nghbr.com', 'Active');

INSERT INTO APP_USER(sso_id, password, first_name, last_name, email, state)
VALUES ('jason','abc126', 'Jason','Silver','jason@nghbr.com', 'Active');

/* Populate JOIN Table */
INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM APP_USER user, USER_PROFILE profile
  where user.sso_id='khanh' and profile.type='ADMIN';

INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM APP_USER user, USER_PROFILE profile
  where user.sso_id='imran' and profile.type='ADMIN';

INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM APP_USER user, USER_PROFILE profile
  where user.sso_id='max' and profile.type='ADMIN';

INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM APP_USER user, USER_PROFILE profile
  where user.sso_id='jason' and profile.type='ADMIN';
  
INSERT INTO APP_ITEM(DESCRIPTION, END_DATE, NAME, START_DATE, BORROWER_ID, OWNER_ID)
values ('This is a strong hammer, to be weilded only by the mighty.', '2015-01-01 04:20:20', 'Strong Hammer', '2015-01-01 04:20:20', 1, 2);

INSERT INTO APP_ITEM(DESCRIPTION, END_DATE, NAME, START_DATE, BORROWER_ID, OWNER_ID)
values ('This is a weak hammer, unworthy of any honor.', '2015-01-01 04:20:20', 'Weak Hammer', '2015-01-01 04:20:20', 3, 2);