package org.calendar.appointment;

public class Appointment{

    final int NO_ID = -1;
    private int id;
    private String title;
    private String description;
    private boolean completed;
    private boolean destroyed;

    //Constructor.
    public Appointment(String title, String description){
        this.id = NO_ID;
        this.title = title;
        this.description = description;
        completed = false;
        destroyed = false;
    }

    public void setId(int id){
        this.id = id;
    }

    //Post: Sets the id.
    public int getId(){
        return id;
    }

    //Post: Sets the title.
    public void setTitle(String title) {
        this.title = title;
    }

    //Post: Sets the description.
    public void setDescription(String description){
        this.description = description;
    }

    //Post: Sets the "completed" attribute.
    public void setCompleted(boolean completed){
        this.completed = completed;
    }

    //Post: Sets the destroyed attribute as true.
    public void destroy(){
        destroyed = true;
    }

    //Post: Returns the title.
    public String getTitle(){
        return title;
    }

    //Post: Returns the description.
    public String getDescription(){
        return description;
    }

    //Post: Returns the state of the appointment. True if completed, false otherwise
    public boolean isCompleted(){
        return completed;
    }

    //Post: Returns the state of the appointment. True if destroyed, false otherwise
    public boolean isDestroyed(){
        return destroyed;
    }
}
