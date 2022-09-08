DROP TABLE IF EXISTS posts CASCADE;
DROP TABLE IF EXISTS users CASCADE;


create table IF NOT EXISTS public.users
(
   id serial not null,
   name character varying(255) not null,
   password character varying(255) not null,
   mail character varying(255) not null
);
ALTER TABLE public.users OWNER TO mealmory;

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.users_id_seq OWNER TO mealmory;

create table IF NOT EXISTS public.posts
(
   id serial not null,
   user_name character varying(255) not null,
   category_id bigint not null,
   diary_day date not null,
   record1 character varying(255) not null,
   record2 character varying(255) not null,
   record3 character varying(255) not null,
   image_name character varying(255) ,
   memo character varying(255) not null,
   create_at timestamp(0) without time zone not null,
   update_at timestamp(0) without time zone not null
    
   
);
ALTER TABLE public.posts OWNER TO mealmory;

CREATE SEQUENCE public.posts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.posts_id_seq OWNER TO mealmory;

