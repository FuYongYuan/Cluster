use Yearning;

create table Yearning.core_accounts
(
    id             bigint unsigned auto_increment
        primary key,
    username       varchar(50)       not null,
    password       varchar(150)      not null,
    department     varchar(50)       null,
    real_name      varchar(50)       null,
    email          varchar(50)       null,
    is_recorder    tinyint default 2 not null,
    query_password varchar(150)      null
);

create index user_idx
    on Yearning.core_accounts (username);

