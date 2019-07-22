package com.rasol.training001.model.entity;

import ch.qos.logback.core.util.TimeUtil;
import com.rasol.training001.util.TimeUtils;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;

@MappedSuperclass
public class BaseEntity {

    @Column(updatable= false)
    private String createdDate;

    private String modifiedDate;

    @PrePersist
    protected void onCreate(){
        createdDate = TimeUtils.getCurrentISO8601MilliPlusTime();
        modifiedDate = TimeUtils.getCurrentISO8601MilliPlusTime();
    }

    @PreUpdate
    protected void onUpdate(){
        modifiedDate = TimeUtils.getCurrentISO8601MilliPlusTime();
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }
}
