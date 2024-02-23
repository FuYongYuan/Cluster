use Yearning;

create table Yearning.core_sql_records
(
    id        bigint unsigned auto_increment
        primary key,
    work_id   varchar(50) not null,
    `sql`     longtext    not null,
    state     varchar(50) not null,
    affectrow int         not null,
    time      varchar(50) not null,
    error     longtext    null
);

create index workId_idx
    on Yearning.core_sql_records (work_id);

