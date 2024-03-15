create table orga_join_request
(
    id              bigint       not null
        primary key,
    user_id         varchar(255) not null,
    organisation_id bigint       not null
        constraint fkqxhg3v4caetqs0anm8o3pfe92
            references organisation
);

alter table orga_join_request
    owner to postgres;

