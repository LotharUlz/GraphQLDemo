CREATE SCHEMA partner;

CREATE TABLE partner.users (
  id serial NOT NULL,
  name varchar(255) NOT NULL,
  countryCodeId varchar(3) NULL,
  constraint pk_users primary key (id)
);

INSERT INTO partner.users (name, countryCodeId)
VALUES
    ('Marge Simpson', 'US'),
    ('Homer Simpson', 'US'),
    ('Bart Simpson', 'US'),
    ('Lisa Simpson', 'US'),
    ('Maggie Simpson', 'US'),
    ('Itchy', 'US'),
    ('Scratchy', 'US');
