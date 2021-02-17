package in.dreamplug.jobservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;

public class Base {

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public void prePersist() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.createdAt = timestamp;
        this.updatedAt = timestamp;
    }

    public void preUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }


    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }


    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Base() {
    }

    public Base(Timestamp createdAt, Timestamp updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
