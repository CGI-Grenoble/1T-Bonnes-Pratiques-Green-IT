create table organisation
(
    id          bigint      not null
        primary key,
    description varchar(256),
    is_public   boolean     not null,
    name        varchar(50) not null
        constraint uk_4cj3idr72jukvc49m5dgo9jmo
            unique
);

alter table organisation
    owner to postgres;

INSERT INTO public.organisation (id, description, is_public, name) VALUES (0, 'Michelin est un leader international de fabrication de pneumatiques. L''entreprise française a son siège social à Clermont-Ferrand (Puy-de-Dôme).', true, 'Michelin');
INSERT INTO public.organisation (id, description, is_public, name) VALUES (2, 'Viseo est une entreprise de services du numérique (ESN) française créée en 1999 basée à Paris', false, 'Viseo');
INSERT INTO public.organisation (id, description, is_public, name) VALUES (5, 'Schneider Electric SE est une société multinationale française créée en 1836 sous le nom de Schneider et Cⁱᵉ avant d''être rebaptisée Schneider Electric en mai 1999. ', true, 'Schneider Electric');
INSERT INTO public.organisation (id, description, is_public, name) VALUES (7, 'Grande surface', true, 'E.Leclerc');
INSERT INTO public.organisation (id, description, is_public, name) VALUES (3, 'test', true, 'Spartoo');
INSERT INTO public.organisation (id, description, is_public, name) VALUES (1106, 'tes', true, 'test');
