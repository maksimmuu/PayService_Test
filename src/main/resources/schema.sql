create table account (
                         id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
                         name VARCHAR(50) NOT NULL,
                         amount float NOT NULL,
                         country varchar(50) NOT NULL

);

create table transferLogs (
                         pay_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
                         sender_id int REFERENCES account(id) ON DELETE SET NULL,
                         sender_name varchar(50) NOT NULL,
                         receiver_id int NOT NULL,
                         receiver_name varchar(50) NOT NULL,
                         send_amount float NOT NULL,
                         logMessage VARCHAR(1000) NOT NULL

);

create table permissionToTransfer (
    account_id int primary key REFERENCES account(id) on DELETE cascade,
    block_account boolean NOT NULL

);











