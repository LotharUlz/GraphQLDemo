CREATE SCHEMA policy;

CREATE TABLE policy.policies (
  id bigint NOT NULL,
  name varchar(255) NOT NULL,
  userId bigint NULL,
  constraint pk_policies primary key (id)
);

INSERT INTO policy.policies (id, name, userId)
VALUES
    (1, 'Haftpflicht', 3),
    (2, 'Naturkatastrophen', 2),
    (3, 'KFZ', 1);