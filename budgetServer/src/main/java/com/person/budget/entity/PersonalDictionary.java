package com.person.budget.entity;

import java.io.Serializable;

public class PersonalDictionary implements Serializable {

    private Long id;
    private Long dictionaryid;

    private String dictionaryname;

    private Long   dictionarycode;

    private String imagename;


    public Long getDictionaryid() {
        return dictionaryid;
    }

    public void setDictionaryid(Long dictionaryid) {
        this.dictionaryid = dictionaryid;
    }

    public String getDictionaryname() {
        return dictionaryname;
    }

    public void setDictionaryname(String dictionaryname) {
        this.dictionaryname = dictionaryname;
    }

    public Long getDictionarycode() {
        return dictionarycode;
    }

    public void setDictionarycode(Long dictionarycode) {
        this.dictionarycode = dictionarycode;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "PersonalDictionary{" +
                "id=" + id +
                ", dictionaryid=" + dictionaryid +
                ", dictionaryname='" + dictionaryname + '\'' +
                ", dictionarycode=" + dictionarycode +
                ", imagename='" + imagename + '\'' +
                '}';
    }
}
