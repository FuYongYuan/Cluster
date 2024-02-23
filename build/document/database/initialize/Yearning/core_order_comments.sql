use Yearning;

create table Yearning.core_order_comments
(
    id       bigint unsigned auto_increment
        primary key,
    work_id  varchar(50) not null,
    username varchar(50) not null,
    content  longtext    null,
    time     varchar(50) not null
);

create index workId_idx
    on Yearning.core_order_comments (work_id);

