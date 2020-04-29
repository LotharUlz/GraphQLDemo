CREATE SCHEMA policy;

CREATE TABLE policy.policies (
  id serial NOT NULL,
  name varchar(255) NOT NULL,
  userId bigint NULL,
  constraint pk_policies primary key (id)
);

INSERT INTO policy.policies (name, userId)
VALUES
    ('Haftpflicht', 3),
    ('Hausrat', 3),
    ('Naturkatastrophen', 2),
    ('KFZ', 1);
