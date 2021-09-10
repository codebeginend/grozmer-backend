CREATE TABLE public.users
(
    id bigserial NOT NULL,
    login character varying(255) UNIQUE NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT fkUserRole FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

INSERT INTO public.users(name, login, password, role_id)
VALUES ('Иван Иванов', 'ivan', 'password', (select id  from role r where r.name = 'ROLE_ADMIN'));
