use Yearning;

create table Yearning.core_query_orders
(
    id            bigint unsigned auto_increment
        primary key,
    work_id       varchar(50)  not null,
    username      varchar(50)  not null,
    date          varchar(50)  not null,
    approval_time varchar(50)  not null,
    text          longtext     not null,
    assigned      varchar(50)  not null,
    real_name     varchar(50)  not null,
    export        tinyint      not null,
    source_id     varchar(200) not null,
    status        tinyint      not null
);

create index source_idx
    on Yearning.core_query_orders (source_id);

create index status_idx
    on Yearning.core_query_orders (status);

create index workId_idx
    on Yearning.core_query_orders (work_id);

