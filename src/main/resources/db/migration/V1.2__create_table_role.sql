CREATE TABLE public.role
(
    id bigserial NOT NULL,
    name character varying(255),
    CONSTRAINT role_pkey PRIMARY KEY (id)
);

INSERT INTO public.role(name)
	VALUES ('ROLE_ADMIN');

INSERT INTO public.role(name)
    VALUES ('ROLE_USER');

