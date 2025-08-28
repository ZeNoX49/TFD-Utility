package app.model;

public class ID {

    public static int updateID(int id, int newId) {
        if(id < newId) {
            return newId;
        } return id;
    }

}