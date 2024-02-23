use Yearning;

create table Yearning.core_rollbacks
(
    id      bigint unsigned auto_increment
        primary key,
    work_id varchar(50) not null,
    `sql`   longtext    not null
);

create index workId_idx
    on Yearning.core_rollbacks (work_id);

