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
