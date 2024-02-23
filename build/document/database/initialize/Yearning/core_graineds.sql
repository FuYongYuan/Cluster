use Yearning;

create table Yearning.core_graineds
(
    id       bigint unsigned auto_increment
        primary key,
    username varchar(50) not null,
    `group`  json        null
);

create index user_idx
    on Yearning.core_graineds (username);

