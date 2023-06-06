module org {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens org to javafx.fxml, com.fasterxml.jackson.databind;
    exports org.backEnd.calendar.appointment to com.fasterxml.jackson.databind;
    exports org.backEnd.calendar.task to com.fasterxml.jackson.databind;
    exports org.backEnd.calendar.event to com.fasterxml.jackson.databind;
    exports org.backEnd.calendar.alarms to com.fasterxml.jackson.databind;
    exports org.backEnd.calendar.event.frequency to com.fasterxml.jackson.databind;
    exports org;

    opens org.backEnd.calendar.appointment to com.fasterxml.jackson.databind;
    opens org.backEnd.calendar.task to com.fasterxml.jackson.databind;
    opens org.backEnd.calendar.event to com.fasterxml.jackson.databind;
    opens org.backEnd.calendar.alarms to com.fasterxml.jackson.databind;
    opens org.backEnd.calendar.event.frequency to com.fasterxml.jackson.databind;
}
