package com.JMS.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Elvira on 02.06.2018.
 */

@Entity
@Table(name = "change")
public class Change implements Serializable{

    @Id
    @GenericGenerator(name = "num", strategy = "increment")
    @GeneratedValue(generator = "num")
    @Column(name = "id_change")
    private Long id;

    @Column(name = "change_type")
    private String changeType;

    @Column(name = "change_class")
    private String changeClass;

    @Column(name = "id_entity")
    private Long id_entity;

    @Column(name = "new_values")
    private String newValues;

    public Change() {
    }

    @Override
    public String toString() {
        return "Change{" +
                "id=" + id +
                ", changeType='" + changeType + '\'' +
                ", changeClass=" + changeClass +
                ", id_entity=" + id_entity +
                ", newValues='" + newValues + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getChangeClass() {
        return changeClass;
    }

    public void setChangeClass(String changeClass) {
        this.changeClass = changeClass;
    }

    public Long getId_entity() {
        return id_entity;
    }

    public void setId_entity(Long id_entity) {
        this.id_entity = id_entity;
    }

    public String getNewValues() {
        return newValues;
    }

    public void setNewValues(String newValues) {
        this.newValues = newValues;
    }
}
