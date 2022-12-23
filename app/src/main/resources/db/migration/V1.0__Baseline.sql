create table player
(
    id       text primary key,
    name     text unique,
    password text,
    token    text null,
    salt     text
);

insert into player(id, name, password, salt)
values ('2243cbfc-9f54-4362-856b-c9b5f26424ae', 'jonny', 'h/86rq6mD+LtgKakvRHDIVYgj6jJHql967l4zT/Q8VE=',
        'cd91e91c-4bd7-41ba-b9a9-0b1bf135543c');