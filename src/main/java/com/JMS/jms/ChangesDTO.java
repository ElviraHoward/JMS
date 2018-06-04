package com.JMS.jms;

import com.JMS.model.Change;

import javax.validation.constraints.NotNull;

/**
 * Created by Elvira on 04.06.2018.
 */
public class ChangesDTO extends Change {

    private Long id;
    private Change.ChangeEnum changeType;
    private String changeClass;
    private Long id_entity;
    private String newValues;

    public ChangesDTO() {
    }

    public ChangesDTO(Change.ChangeEnum changeType, String changeClass, Long id_entity, String newValues) {
        this.changeType = changeType;
        this.changeClass = changeClass;
        this.id_entity = id_entity;
        this.newValues = newValues;
    }

    public ChangesDTO(Change entity) {
        this.id = entity.getId();
        this.changeType = entity.getChangeType();
        this.changeClass = entity.getChangeClass();
        this.id_entity = entity.getId_entity();
        this.newValues = entity.getNewValues();
    }

    @NotNull
    public static ChangesDTO createDeleteMessage(String entityClass, Long id, String newValues) {
        return new ChangesDTO(Change.ChangeEnum.DELETE, entityClass, id, newValues);
    }

    @NotNull
    public static ChangesDTO createCreateMessage(String entityClass, Long id, String newValues) {
        return new ChangesDTO(Change.ChangeEnum.CREATE, entityClass, id, newValues);
    }

    @NotNull
    public static ChangesDTO createUpdateMessage(String entityClass, Long id, String newValues) {
        return new ChangesDTO(Change.ChangeEnum.UPDATE, entityClass, id, newValues);
    }

    @NotNull
    public Change createEntity() {
        return new Change(this.id, this.changeType, this.changeClass, this.id_entity, this.newValues);
    }

    public Long getId() {
        return id;
    }

    public Change.ChangeEnum getChangeType() {
        return changeType;
    }

    public String getChangeClass() {
        return changeClass;
    }

    public Long getId_entity() {
        return id_entity;
    }

    public String getNewValues() {
        return newValues;
    }
}
