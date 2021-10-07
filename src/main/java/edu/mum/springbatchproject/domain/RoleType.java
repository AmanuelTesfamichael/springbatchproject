package edu.mum.springbatchproject.domain;


public enum RoleType {

    PERSON("ADMIN");
    private String type;

    RoleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "RoleType{" +
                "type='" + type + '\'' +
                '}';
    }
}
