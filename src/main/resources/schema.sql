
create table users
(
   id integer generated by default as identity,
   name varchar (255),
   password varchar (255),
   email varchar (255),
   create_datetime timestamp not null,
   primary key (id)
);