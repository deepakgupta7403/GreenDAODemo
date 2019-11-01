package com.app.greendaodemo.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "noteEntity")
public class NoteModel {

    @Id(autoincrement = true)
    @Property(nameInDb = "noteId")
    private Long id;

    @Property(nameInDb = "note")
    private String note;

    @Property(nameInDb = "createdAt")
    private Long createdAt;

    @Property(nameInDb = "lastUpdatedAt")
    private Long lastUpdatedAt;

    @Property(nameInDb = "isActivated")
    private boolean isActivated;

    @Generated(hash = 1487902773)
    public NoteModel(Long id, String note, Long createdAt, Long lastUpdatedAt,
            boolean isActivated) {
        this.id = id;
        this.note = note;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
        this.isActivated = isActivated;
    }

    @Generated(hash = 1532285157)
    public NoteModel() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Long lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    @Override
    public String toString() {
        return "NoteModel{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", createdAt=" + createdAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", isActivated=" + isActivated +
                '}';
    }

    public boolean getIsActivated() {
        return this.isActivated;
    }

    public void setIsActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }
}
