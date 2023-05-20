package calendar_org.calendar.alarms;

import java.time.LocalDateTime;

public class SoundAlarm extends Alarm {
    private String subtype = "SoundAlarm";
    public SoundAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    public SoundAlarm(){}

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype){
        this.subtype = subtype;
    }

    //Post: Rings making a sound.
    @Override
    protected void ring() {
        //Make sound algorithm...
    }

}
