package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@FilterDef(name = "filterDelete",
        parameters = @ParamDef(name = "isDeleted", type = "boolean"),
        defaultCondition = "is_deleted = :isDeleted")
@Filter(name = "filterDelete")
@Data
public class AbstructEntity {

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

}
