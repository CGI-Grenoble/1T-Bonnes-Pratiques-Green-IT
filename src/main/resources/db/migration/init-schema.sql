create table public.card
(
    id           bigint       not null
        primary key,
    title        varchar(255) not null,
    description  varchar(1000),
    difficulty   integer,
    actor        varchar(255),
    cpu_gain     boolean,
    link         varchar(255),
    memory_gain  boolean,
    network_gain boolean,
    storage_gain boolean,
    type         varchar(255)
);

alter table public.card
    owner to postgres;

create table public.favorite
(
    id       bigint                                                   not null
        primary key,
    user_id  varchar(255)                                             not null,
    category varchar(255) default 'non rencontrée'::character varying not null,
    card_id  bigint                                                   not null
        constraint fk62a95lhsr67q2ck26u1y0km1f
            references public.card
);

alter table public.favorite
    owner to postgres;

create table public.organisation
(
    id          bigint      not null
        primary key,
    description varchar(256),
    is_public   boolean     not null,
    name        varchar(50) not null
        constraint uk_4cj3idr72jukvc49m5dgo9jmo
            unique
);

alter table public.organisation
    owner to postgres;

create table public.game
(
    id              bigint                                                 not null
        primary key,
    date            timestamp(6)                                           not null,
    organisation_id bigint                                                 not null
        constraint fk1rfy9mbrf9v4qs2ttidkfo0rb
            references public.organisation,
    status          varchar(255) default 'WAITING_TO_S'::character varying not null
);

alter table public.game
    owner to postgres;

create table public.orga_join_request
(
    id              bigint       not null
        primary key,
    user_id         varchar(255) not null,
    organisation_id bigint       not null
        constraint fkqxhg3v4caetqs0anm8o3pfe92
            references public.organisation
);

alter table public.orga_join_request
    owner to postgres;
