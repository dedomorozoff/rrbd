package com.example.myapplication;

public class MyDataModel {


    String firstname,lastname,gr,id;

    public MyDataModel(){

    }

    public MyDataModel(String id, String firstname, String lastname, String gr){
        this.id=id;
        this.firstname=firstname;
        this.lastname=lastname;
        this.gr=gr;

    }
    public  String getId(){
        return id;
    }

    public String getGr() {
        return gr;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public int size(){
        return 28;
    }
}
