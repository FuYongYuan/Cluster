use Yearning;

create table Yearning.core_role_groups
(
    id          bigint unsigned auto_increment
        primary key,
    name        varchar(50)  not null,
    permissions json         null,
    group_id    varchar(200) not null
);

create index group_idx
    on Yearning.core_role_groups (group_id);

