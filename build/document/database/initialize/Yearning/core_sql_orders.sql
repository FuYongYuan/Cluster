use Yearning;

create table Yearning.core_sql_orders
(
    id           bigint unsigned auto_increment
        primary key,
    work_id      varchar(50)                not null,
    username     varchar(50)                not null,
    status       tinyint                    not null,
    type         tinyint                    not null,
    backup       tinyint                    not null,
    id_c         varchar(50)                not null,
    source       varchar(50)                not null,
    source_id    varchar(200)               not null,
    data_base    varchar(50)                not null,
    `table`      varchar(50)                not null,
    date         varchar(50)                not null,
    `sql`        longtext                   not null,
    text         longtext                   not null,
    assigned     varchar(550)               not null,
    delay        varchar(50) default 'none' not null,
    real_name    varchar(50)                not null,
    execute_time varchar(50)                null,
    current_step int                        null,
    relevant     json                       null,
    osc_info     longtext                   null,
    file         varchar(200)               not null
);

create index query_idx
    on Yearning.core_sql_orders (username);

create index source_idx
    on Yearning.core_sql_orders (source_id);

create index workId_idx
    on Yearning.core_sql_orders (work_id);

