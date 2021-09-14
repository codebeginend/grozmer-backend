CREATE TABLE plot_progress
(
    id bigserial NOT NULL,
    plot_id bigint NOT NULL,
    status character varying(255) NOT NULL,
    user_id bigint NOT NULL,
    created_at TIMESTAMPTZ DEFAULT (now() AT TIME ZONE 'utc'),
    updated_at TIMESTAMPTZ,
    CONSTRAINT plot_progress_pkey PRIMARY KEY (id),
    CONSTRAINT fk68w5bme5uef247jhwyyiaranc FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk88cxk5ri1ncmagqxgg39mljaq FOREIGN KEY (plot_id)
        REFERENCES public.plot (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)