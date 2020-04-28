CREATE SCHEMA partner;

CREATE TABLE partner.users (
  id bigint NOT NULL,
  name varchar(255) NOT NULL,
  countryCodeId varchar(3) NULL,
  constraint pk_users primary key (id)
);

INSERT INTO partner.users (id, name, countryCodeId)
VALUES
    (1, 'Marge Simpson', 'US'),
    (2, 'Homer Simpson', 'US'),
    (3, 'Bart Simpson', 'US'),
    (4, 'Lisa Simpson', 'US'),
    (5, 'Maggie Simpson', 'US'),
    (6, 'Itchy', 'US'),
    (7, 'Scratchy', 'US');