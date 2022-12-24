create table player
(
    id       text primary key,
    name     text unique,
    password text,
    token    text null,
    salt     text
);

INSERT INTO public.player (id, name, password, token, salt)
VALUES ('2243cbfc-9f54-4362-856b-c9b5f26424ae', 'jonny', 'h/86rq6mD+LtgKakvRHDIVYgj6jJHql967l4zT/Q8VE=', null,
        'cd91e91c-4bd7-41ba-b9a9-0b1bf135543c');
INSERT INTO public.player (id, name, password, token, salt)
VALUES ('aa1aea8a-9b37-469f-a348-02a00a0abebb', 'player1', 'Gb0/UNEWRGzXZDOUaomBYo6czzJaKU5rlz1fhRoWprU=', null,
        '01c881bc-5586-4576-9595-eadc30f6d0cb');
INSERT INTO public.player (id, name, password, token, salt)
VALUES ('fcdff658-0ccf-464a-a189-18fe34d06c0d', 'player2', 'WjQLLy+YkzrP/FdtVx9tQ32LR2f7YW14u5Y/cfsuR7E=', null,
        '1be84778-2fde-413b-bf27-c9ed92c34a1f');
INSERT INTO public.player (id, name, password, token, salt)
VALUES ('4879f651-6b88-49d3-93ab-065a99abaeb0', 'player3', 'sgZ+mHsQ5kxe4P0jmHv0dnl3e9CgsUGIXoNAVDJf4CY=', null,
        '67d15abf-bd47-477a-868e-e0fe80cca080');
INSERT INTO public.player (id, name, password, token, salt)
VALUES ('678cb1b9-4e11-42d7-9f79-2e0c61747bf1', 'player4', 'nM6vBTBazYFQyQo11Ck9D8Zjkz0wLxBuq8fOxJEvTRw=', null,
        'e03583c5-674f-4fa9-8e3f-c4edc1ea606c');

create table game
(
    id text primary key
);

create table game_player
(
    player text,
    game   text
);

create table shoe
(
    id            text primary key,
    game          text,
    next_position Int
);

create table card
(
    id     text primary key,
    suit   text,
    number text,
    value  INT
);

create table card_position
(
    id       text primary key,
    card     text,
    shoe     text,
    position INT
)


