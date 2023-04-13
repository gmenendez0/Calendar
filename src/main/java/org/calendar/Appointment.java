package org.calendar;

public class Appointment{
    private final int id;
    private String title;
    private String description;
    private boolean completed;
    private boolean destroyed;

    //Constructor.
    public Appointment(int id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
        completed = false;
        destroyed = false;
    }

    protected int getId(){
        return id;
    }

    //Post: Sets the title.
    protected void setTitle(String title) {
        this.title = title;
    }

    //Post: Sets the description.
    protected void setDescription(String description){
        this.description = description;
    }

    //Post: Sets the "completed" attribute.
    protected void setCompleted(boolean completed){
        this.completed = completed;
    }

    //Post: Sets the destroyed attribute as true.
    protected void destroy(){
        destroyed = true;
    }

    //Post: Returns the title.
    protected String getTitle(){
        return title;
    }

    //Post: Returns the description.
    protected String getDescription(){
        return description;
    }

    //Post: Returns the state of the appointment. True if completed, false otherwise
    protected boolean isCompleted(){
        return completed;
    }

    //Post: Returns the state of the appointment. True if destroyed, false otherwise
    protected boolean isDestroyed(){
        return destroyed;
    }
}
