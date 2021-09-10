CREATE TABLE public.plot
(
    id bigserial NOT NULL,
    address character varying(255),
    cadastral_number character varying(255),
    latitude double precision NOT NULL,
    longitude double precision NOT NULL,
    number_phone character varying(255),
    owner character varying(255),
    owner_type character varying(255),
    square double precision NOT NULL,
    CONSTRAINT plot_pkey PRIMARY KEY (id)
)
