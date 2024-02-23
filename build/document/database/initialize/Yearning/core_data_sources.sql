use Yearning;

create table Yearning.core_data_sources
(
    id                 bigint unsigned auto_increment
        primary key,
    id_c               varchar(50)  not null,
    source             varchar(50)  not null,
    ip                 varchar(200) not null,
    port               int          not null,
    username           varchar(50)  not null,
    password           varchar(150) not null,
    is_query           tinyint      not null,
    flow_id            int          not null,
    source_id          varchar(200) not null,
    exclude_db_list    varchar(200) not null,
    insulate_word_list varchar(200) not null,
    principal          varchar(150) not null,
    ca_file            longtext     null,
    cert               longtext     null,
    key_file           longtext     null
);

create index source_idx
    on Yearning.core_data_sources (source_id);

