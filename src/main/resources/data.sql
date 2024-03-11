DROP TABLE IF EXISTS public.card;
DROP TABLE IF EXISTS public.favorite;
DROP TABLE IF EXISTS public.game;
DROP TABLE IF EXISTS public.player;
DROP TABLE IF EXISTS public.organisation;
DROP TABLE IF EXISTS public.organisation_players;



create table public.card
(
    id               bigint       not null
        primary key,
    actors           varchar(255),
    background_image varchar(255) not null,
    title            varchar(255) not null,
    components       varchar(255),
    description      varchar(1000),
    difficulty       integer,
    subtitle         varchar(255),
    gain_type        varchar(255),
    logo             varchar(255) not null,
    value            integer,
    formation_link   varchar(255)
);

alter table public.card
    owner to postgres;

create table public.favorite
(
    id       bigint                                                   not null
        primary key,
    user_id  varchar(255)                                             not null,
    category varchar(255) default 'non rencontrée'::character varying not null
);

alter table public.favorite
    owner to postgres;

create table public.game
(
    id   bigint       not null
        primary key,
    date timestamp(6) not null
);

alter table public.game
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

create table public.player
(
    id bigint not null
        primary key
);

alter table public.player
    owner to postgres;

create table public.organisation_players
(
    organisation_id bigint not null
        constraint fkc7iica9k4uddcjnr7jfgs2ywk
            references public.organisation,
    players_id      bigint not null
        constraint fk6uber8vyjyr55gt3maveeutsr
            references public.player,
    primary key (organisation_id, players_id)
);

alter table public.organisation_players
    owner to postgres;

create table public.player_player_organisations
(
    player_id               bigint not null
        constraint fkpov80md4vro2d0ire6x3jbkyt
            references public.player,
    player_organisations_id bigint not null
        constraint fkghchsrb96bytq2y1lra1ht5f5
            references public.organisation,
    primary key (player_id, player_organisations_id)
);

alter table public.player_player_organisations
    owner to postgres;



INSERT INTO public.organisation (id, description, is_public, name) VALUES (0, 'Les pneus', true, 'Michelin') ON CONFLICT DO NOTHING;
INSERT INTO public.organisation (id, description, is_public, name) VALUES (1, 'La électricité', false, 'Schneider Electric') ON CONFLICT DO NOTHING;
INSERT INTO public.organisation (id, description, is_public, name) VALUES (2, '...', false, 'Viseo') ON CONFLICT DO NOTHING;
INSERT INTO public.organisation (id, description, is_public, name) VALUES (3, 'Les chaussures', true, 'Spartoo') ON CONFLICT DO NOTHING;


INSERT INTO public.game (id, date) VALUES (0, '2024-02-14 10:47:54.000000') ON CONFLICT DO NOTHING;
INSERT INTO public.game (id, date) VALUES (1, '2024-02-13 15:02:00.000000') ON CONFLICT DO NOTHING;

INSERT INTO public.card (id, actors, background_image, title, subtitle, components, description, difficulty, gain_type, logo, value, formation_link) VALUES (2, null, 'sensibilisation_background.jpg', 'Sensibilisation', null, null, 'Regarder une vidéo en utilisant le signal Wifi permet de consommer 23 fois moins d’énergie qu’en passant par une connexion 4G.', null, null, 'sensibilisation.png', null, null) ON CONFLICT DO NOTHING;
INSERT INTO public.card (id, actors, background_image, title, subtitle, components, description, difficulty, gain_type, logo, value, formation_link) VALUES (3, null, 'code_background.jpg', 'Expert développeur artisan écoresponsable', null, null, null, null, null, 'expert_developpeur.jpg', null, null) ON CONFLICT DO NOTHING;
INSERT INTO public.card (id, actors, background_image, title, subtitle, components, description, difficulty, gain_type, logo, value, formation_link) VALUES (4, null, 'formation_background.jph', 'Formation au développement green', 'Référentiel GreenIT.fr', null, 'GreenIT.fr, avec le soutien de plus de 40 contributeurs membres du collectif conception numérique responsable, a mis au point un référentiel de 115 bonnes pratiques d''éco-conception web.', null, null, 'formation_logo.png', null, 'https://www.google.fr') ON CONFLICT DO NOTHING;
INSERT INTO public.card (id, actors, background_image, title, subtitle, components, description, difficulty, gain_type, logo, value, formation_link) VALUES (1, 'product owner', 'bonnes_pratiques_background.jpg', '', 'Ajouter des entêtes Expires ou Cache-Control', 'internet', 'Définissez avec les en-têtes Expires et Cache-Control la durée de conservation d’une ressource dans le cache du navigateur. Cela évite qu’il les redemande au serveur, permettant d’économiser de la bande passante et des ressources serveur.', 1, 'cpu,ram,network', 'bonne_pratique.jpg', 50, null) ON CONFLICT DO NOTHING;
INSERT INTO public.card (id, actors, background_image, title, subtitle, components, description, difficulty, gain_type, logo, value, formation_link) VALUES (0, 'product owner', 'bonnes_pratiques_background.jpg', '', 'Utiliser des frameworks efficients supportant l''autoscaling', 'server', 'L''auto-scaling (ou auto-dimensionnement) permet d''adapter le nombre de ressources (VM, ...) nécessaires à l''exécution d''un service. Il faut être capable de démarrer des services en cas de pic de charge mais aussi de les réduire en creux de charges. L''utilisation combinée du cloud, de la conteneurisation, de l''orchestration voire des Function As A Service (Serverless) permettent d''optimiser les ressources. Pour être efficient, il faut choisir les images Docker les plus légères et les moins consommatrices et choisir un framework capable de démarrer rapidement et supportant le downscaling (tels que Quarkus, Spring Cloud Function...).', 3, 'cpu,ram', 'bonne_pratique.png', 25, null) ON CONFLICT DO NOTHING;
INSERT INTO public.card (id, actors, background_image, title, subtitle, components, description, difficulty, gain_type, logo, value, formation_link) VALUES (5, 'developer', 'mauvaise_pratique_background.jpg', 'Mauvaise pratique', 'Requêtes SQL multiples au sein d''une boucle', 'database', 'Les requêtes SQL à l’intérieur de boucles posent de gros problèmes de performance et saturent la bande passante réseau en raison des allers- retour réseau nécessaires. C''est d’autant plus vrai si le serveur SQL n’est pas sur la machine locale. En effet, ces serveurs sont optimisés pour traiter plusieurs sélections, insertions ou modifications dans une seule requête ensembliste ou une seule transaction. Mal utilisées, ces requêtes consomment inutilement des cycles CPU, de la mémoire vive et surtout de la bande passante.', 2, 'ram,network', 'mauvaise_pratique_logo.png', null, null) ON CONFLICT DO NOTHING;
