CREATE TABLE public.plot_docs
(
    id bigserial NOT NULL,
    name character varying(255) NOT NULL,
    doc_type character varying(255) NOT NULL,
    data_type character varying(255) NOT NULL,
    plot_id bigint NOT NULL,
    CONSTRAINT plot_docs_pkey PRIMARY KEY (id),
    CONSTRAINT fkPlot FOREIGN KEY (plot_id)
        REFERENCES public.plot (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
