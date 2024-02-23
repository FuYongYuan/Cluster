use Yearning;

create table Yearning.core_auto_tasks
(
    id        bigint unsigned auto_increment
        primary key,
    name      varchar(50)  not null,
    source    varchar(50)  not null,
    source_id varchar(200) not null,
    data_base varchar(50)  not null,
    `table`   varchar(50)  not null,
    tp        tinyint      not null,
    affectrow int          null,
    status    tinyint      null,
    task_id   varchar(200) not null,
    id_c      varchar(50)  not null
);

create index source_idx
    on Yearning.core_auto_tasks (source_id);

create index task_idx
    on Yearning.core_auto_tasks (task_id);

