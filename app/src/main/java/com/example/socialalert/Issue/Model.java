package com.example.socialalert.Issue;

public class  Model {
    String name,number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public Model(String name,String number) {
        this.name = name;
        this.number=number;
    }
    public Character getProfileLetter(){
        return name.charAt(0);
    }
}
