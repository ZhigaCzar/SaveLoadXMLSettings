package com.company.objects;

public class Setting {

    String name;
    String value;

    public Setting(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Setting() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }

}
