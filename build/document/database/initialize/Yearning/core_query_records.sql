use Yearning;

create table Yearning.core_query_records
(
    id       bigint unsigned auto_increment
        primary key,
    work_id  varchar(50) not null,
    `sql`    longtext    not null,
    ex_time  int         not null,
    time     varchar(50) not null,
    source   varchar(50) not null,
    `schema` varchar(50) not null
);

create index workId_idx
    on Yearning.core_query_records (work_id);

