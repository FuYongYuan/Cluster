use Yearning;

create table Yearning.core_workflow_tpls
(
    id     bigint unsigned auto_increment
        primary key,
    source varchar(50) not null,
    steps  json        null
);

create index source_idx
    on Yearning.core_workflow_tpls (source);

