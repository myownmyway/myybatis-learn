create database learn;
use learn;
select database();
show tables;
create table if not exists user
(
    id        int auto_increment primary key not null comment '用户id',
    user_name varchar(255)                   not null comment '用户名称',
    age       int(3)                         not null comment '用户年纪'
);
show index from user;
explain select * from user;
insert into user(user_name,age) values("admin",1);
insert into user(user_name,age) values("admin",2);
insert into user(user_name,age) values("admin",3);
insert into user(user_name,age) values("admin",4);
insert into user(user_name,age) values("admin",5);
insert into user(user_name,age) values("admin",6);
insert into user(user_name,age) values("admin",7);
insert into user(user_name,age) values("admin",8);
insert into user(user_name,age) values("admin",9);
insert into user(user_name,age) values("admin",10);
insert into user(user_name,age) values("admin",11);