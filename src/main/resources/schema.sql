create table account (
                         id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
                         name VARCHAR(50) NOT NULL,
                         amount float NOT NULL,
                         country varchar(50) NOT NULL
);

create table transferLogs (
                         id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
                         sender_id int REFERENCES account(id) ON DELETE SET NULL,
                         logMessage VARCHAR(1000) NOT NULL

);



