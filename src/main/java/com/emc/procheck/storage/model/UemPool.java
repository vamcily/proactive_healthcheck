package com.emc.procheck.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "uem_pool_ent")
@Table(name = "uem_pool")
public class UemPool {

    private String systemKey;
    private String healthValue;
    private Boolean isEmpty;
    private String name;
    @Id
    @GeneratedValue(generator = "uem_poolKeyGenerator")
    @GenericGenerator(name = "uem_poolKeyGenerator", strategy = "assigned")
    private String poolKey;
    private Float sizeFree;
    private Float sizeSubscribed;
    private Float sizeTotal;
    private Float sizeUsed;

    public UemPool() {
    }

    public UemPool(String poolKey) {
        setPoolKey(poolKey);
    }

    public String getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }

    public String getHealthValue() {
        return healthValue;
    }

    public void setHealthValue(String healthValue) {
        this.healthValue = healthValue;
    }

    public Boolean getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(Boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(insertable = false, updatable = false)
    public String getPoolKey() {
        return poolKey;
    }

    public void setPoolKey(String poolKey) {
        this.poolKey = poolKey;
    }

    public Float getSizeFree() {
        return sizeFree;
    }

    public void setSizeFree(Float sizeFree) {
        this.sizeFree = sizeFree;
    }

    public Float getSizeSubscribed() {
        return sizeSubscribed;
    }

    public void setSizeSubscribed(Float sizeSubscribed) {
        this.sizeSubscribed = sizeSubscribed;
    }

    public Float getSizeTotal() {
        return sizeTotal;
    }

    public void setSizeTotal(Float sizeTotal) {
        this.sizeTotal = sizeTotal;
    }

    public Float getSizeUsed() {
        return sizeUsed;
    }

    public void setSizeUsed(Float sizeUsed) {
        this.sizeUsed = sizeUsed;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
