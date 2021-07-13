--liquibase formatted sql

--changeset DinarShagaliev:1
insert into user_role(role)
values ('ADMIN'),
       ('USER');
