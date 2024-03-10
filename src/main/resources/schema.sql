create table users(
    id bigserial primary key ,
    username varchar,
    email varchar,
    password varchar,
    created_date timestamp default now()
);

create table todo(
    id bigserial primary key,
    title varchar,
    description varchar,
    due_date timestamp default now()
);