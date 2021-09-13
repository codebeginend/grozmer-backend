package com.beginend.grozmerbackend.entity;

import javax.persistence.*;

@Entity
@Table(name = "plot_docs")
public class PlotDocsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "doc_type")
    private String docType;

    @Column(name = "data_type")
    private String dataType;

    @ManyToOne
    @JoinColumn(name = "plot_id", insertable = false, updatable = false)
    private PlotEntity plot;

    @Column(name = "plot_id", insertable = true, updatable = true)
    private Long plotId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Long getPlotId() {
        return plotId;
    }

    public void setPlotId(Long plotId) {
        this.plotId = plotId;
    }
}
