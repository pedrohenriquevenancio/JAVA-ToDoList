package main.java.models;

public class Task {
    private int id;
    private int user_id;
    private String name;
    private String description;
    private boolean isComplete;

    public Task (int id, int user_id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return isComplete ? "Complete" : "Pendent";
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "[ID: #"+getUserId()+", Name: "+getName()+", Description: "+getDescription()+", Status: "+getStatus()+"]";
    }
}
