package com.supportportal.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="Agency")
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idAgency;

    @Column(nullable = false,unique = true)
    private String name;
    private String coordsLong;
    private String coordsLarg;

    public Agency(Long idAgency, String name, String coordsLong, String coordsLarg) {
        this.idAgency = idAgency;
        this.name = name;
        this.coordsLong = coordsLong;
        this.coordsLarg = coordsLarg;
    }

    public Agency() {
    }

    public Long getIdAgency() {
        return idAgency;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordsLong() {
        return coordsLong;
    }

    public void setCoordsLong(String coordsLong) {
        this.coordsLong = coordsLong;
    }

    public String getCoordsLarg() {
        return coordsLarg;
    }

    public void setCoordsLarg(String coordsLarg) {
        this.coordsLarg = coordsLarg;
    }
}
