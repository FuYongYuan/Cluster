use Yearning;

create table Yearning.core_workflow_details
(
    id       bigint unsigned auto_increment
        primary key,
    work_id  varchar(50)  not null,
    username varchar(50)  not null,
    time     varchar(50)  not null,
    action   varchar(550) not null
);

create index query_idx
    on Yearning.core_workflow_details (username);

create index workId_idx
    on Yearning.core_workflow_details (work_id);

