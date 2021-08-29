CREATE TABLE User
(
    UserID BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Name   VARCHAR(50) NOT NULL,
    Pass   VARCHAR(50) NOT NULL,
    Mail   VARCHAR(255)
);

INSERT INTO User (Name, Pass, Mail)
VALUES ('Petr', '123', 'Petr@dot.com'),
       ('Basyl', 'ABC', 'XXX@YYY.com'),
       ('Root', '@#$', 'admin@factory.com');