use Yearning;

create table Yearning.core_global_configurations
(
    id            bigint unsigned auto_increment
        primary key,
    authorization varchar(50)       not null,
    ldap          json              null,
    message       json              null,
    other         json              null,
    stmt          tinyint default 0 not null,
    audit_role    json              null,
    board         longtext          null
);

