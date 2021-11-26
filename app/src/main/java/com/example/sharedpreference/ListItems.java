package com.example.sharedpreference;

public class ListItems {

    private  String name;
    private String stream;
    private String id;

    public ListItems(){

    }

    public ListItems(String name, String stream, String  id){

        this.name = name;
        this.stream = stream;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStream() {
        return stream;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }
}
