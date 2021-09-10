package com.beginend.grozmerbackend.entity;

import com.beginend.grozmerbackend.model.Plot;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "plot_docs")
public class PlotDocsEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @Column(name = "doc_type")
    private String docType;

    @Getter
    @Setter
    @Column(name = "data_type")
    private String dataType;

    @ManyToOne
    @JoinColumn(name = "plot_id", insertable = false, updatable = false)
    private PlotEntity plot;

    @Getter
    @Setter
    @Column(name = "plot_id", insertable = true, updatable = true)
    private Long plotId;
}
