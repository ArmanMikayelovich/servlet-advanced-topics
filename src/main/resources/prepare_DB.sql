create table customers
(
    id         bigserial,
    name       varchar(255)  not null,
    email      varchar(255)  not null,
    address    varchar(1000) not null,
    created_at timestamp     not null
);

create table request_audit
(
    ip     varchar(255)  not null,
    method varchar(20)   not null,
    path   varchar(2000) not null,
    params varchar       not null
);


INSERT INTO customers (name, email, address, created_at)
VALUES
    (md5(random()::text), md5(random()::text) || '@example.com', md5(random()::text), NOW() - (random() * (NOW() - '2020-01-01 00:00:00')::interval));
INSERT INTO customers (name, email, address, created_at)
VALUES
    (md5(random()::text), md5(random()::text) || '@gmail.com', md5(random()::text), NOW() - (random() * (NOW() - '2020-01-01 00:00:00')::interval));
INSERT INTO customers (name, email, address, created_at)
VALUES
    (md5(random()::text), md5(random()::text) || '@msn.com', md5(random()::text), NOW() - (random() * (NOW() - '2020-01-01 00:00:00')::interval));
INSERT INTO customers (name, email, address, created_at)
VALUES
    (md5(random()::text), md5(random()::text) || '@yahoo.com', md5(random()::text), NOW() - (random() * (NOW() - '2020-01-01 00:00:00')::interval));
INSERT INTO customers (name, email, address, created_at)
VALUES
    (md5(random()::text), md5(random()::text) || '@10minutesmail.com', md5(random()::text), NOW() - (random() * (NOW() - '2020-01-01 00:00:00')::interval));
