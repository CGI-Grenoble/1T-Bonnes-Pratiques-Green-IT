create table favorite
(
    id       bigint                                                   not null
        primary key,
    user_id  varchar(255)                                             not null,
    category varchar(255) default 'non rencontrée'::character varying not null,
    card_id  bigint                                                   not null
        constraint fk62a95lhsr67q2ck26u1y0km1f
            references card
);

alter table favorite
    owner to postgres;

INSERT INTO public.favorite (id, user_id, category, card_id) VALUES (0, 'ca7244d0-f12c-430b-b7c8-e8c9d473e1ee', 'TODO', 33);
INSERT INTO public.favorite (id, user_id, category, card_id) VALUES (1, 'ca7244d0-f12c-430b-b7c8-e8c9d473e1ee', 'NOT_APPLICABLE', 39);
INSERT INTO public.favorite (id, user_id, category, card_id) VALUES (2, 'ca7244d0-f12c-430b-b7c8-e8c9d473e1ee', 'TODO', 43);
