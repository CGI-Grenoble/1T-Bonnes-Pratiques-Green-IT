create table game
(
    id              bigint                                                 not null
        primary key,
    date            timestamp(6)                                           not null,
    organisation_id bigint                                                 not null
        constraint fk1rfy9mbrf9v4qs2ttidkfo0rb
            references organisation,
    status          varchar(255) default 'WAITING_TO_S'::character varying not null
);

alter table game
    owner to postgres;

INSERT INTO public.game (id, date, organisation_id, status) VALUES (1728, '2024-03-15 12:37:44.623000', 0, 'WAITING_TO_START');
