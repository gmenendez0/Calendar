package org.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.models.calendar.Calendar;
import org.models.calendar.alarms.*;
import org.models.calendar.appointment.Appointment;
import org.models.calendar.event.Event;
import org.models.calendar.event.PeriodTimeEvent;
import org.models.calendar.event.WholeDayEvent;
import org.models.calendar.event.frequency.*;
import org.models.calendar.task.ExpirationTimeTask;
import org.models.calendar.task.Task;
import org.models.calendar.task.WholeDayTask;
import org.controllers.exceptions.EventTimeException;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CreateAppointmentControllers {
    final int ONE_DAY = 1;
    final int ZERO_HOUR = 0;
    final int FIRST_HOUR = 1;
    final int FIRST_MINUTE = 1;
    final int FINAL_MINUTE = 59;
    final int FINAL_HOUR = 23;
    final int INDEX_TAB_ALARM = 2;
    final int MAX_NUM_CHARACTER = 60;
    private int idAlarm = 0;
    private Calendar calendar;

    private Appointment appointmentCreated;

    private final AppointmentAlarmsControllers appointmentAlarmsControllers = new AppointmentAlarmsControllers();

    private final MessageControllers messageControllers = new MessageControllers();

    private LocalDateTime dateAppointmentStart;

    @FXML
    private Stage createStage;

    @FXML
    private TabPane tabPaneCreate;

    @FXML
    private Tab tabEvent;

    @FXML
    private Tab tabTask;

    @FXML
    private Tab tabAlarm;

    @FXML
    private Spinner<Integer> hourStartEvent;

    @FXML
    private Spinner<Integer> minuteStartEvent;

    @FXML
    private Spinner<Integer> hourEndEvent;

    @FXML
    private Spinner<Integer> minuteEndEvent;

    @FXML
    private Spinner<Integer> hourStartTask;

    @FXML
    private Spinner<Integer> minuteStartTask;

    @FXML
    private Pane dateTimeEndEvent;

    @FXML
    private Pane timeStartEvent;

    @FXML
    private Pane paneCheckDaily;

    @FXML
    private Pane paneCheckWeekly;

    @FXML
    private Pane timeStartTask;

    @FXML
    private CheckBox checkPeriodTimeEvent;

    @FXML
    private CheckBox checkWholeDayTask;

    @FXML
    private final ToggleGroup groupAlarms = new ToggleGroup();

    @FXML
    private RadioButton checkAbsolute;

    @FXML
    private RadioButton checkRelative;

    @FXML
    private DatePicker dateAlarm;

    @FXML
    private Spinner<Integer> hourAlarm;

    @FXML
    private Spinner<Integer> minuteAlarm;

    @FXML
    private Spinner<Integer> intervalDaysValue;

    @FXML
    private TextField eventTitle;

    @FXML
    private TextField descriptionEvent;

    @FXML
    private DatePicker startDateEvent;

    @FXML
    private DatePicker endDateEvent;

    @FXML
    private CheckBox checkMonday;

    @FXML
    private CheckBox checkTuesday;

    @FXML
    private CheckBox checkWednesday;

    @FXML
    private CheckBox checkThursday;

    @FXML
    private CheckBox checkFriday;

    @FXML
    private CheckBox checkSaturday;

    @FXML
    private CheckBox checkSunday;

    @FXML
    private Button btnCreateEvent;

    @FXML
    private Button btnCreateTask;

    @FXML
    private Button btnCreateAlarm;

    @FXML
    private Spinner<String> spinnerFrequency;

    @FXML
    private DatePicker deadLinePicker;

    @FXML
    private TextField taskTitle;

    @FXML
    private TextField descriptionTask;

    @FXML
    private DatePicker startDateTask;

    @FXML
    private ComboBox<String> comboBoxAlarms;

    @FXML
    private Button btnFinish;

    //Post: start the stage configuration and the creation of the scene or a file warning.
    public void setupViewCreateAppointment(Calendar calendar, Stage createStage) {
        this.calendar = calendar;
        this.createStage = createStage;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/createAppointment.fxml"));
            fxmlLoader.setController(this);
            Scene thirdScene = new Scene(fxmlLoader.load());
            thirdScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            createStage.setScene(thirdScene);
            configEventInCreateView();
        }
        catch (IOException ex) {
            messageControllers.warningFile("Not found: 'createAppointment.fxml'");
        }
    }

    //Post: start the general configuration of the scene
    private void configEventInCreateView(){
        configPaneCheck();
        configSpinnerIntegerValue();
        configComboBoxAlarms();
        createButtonEvent();
        createButtonTask();
        createButtonAlarms();
        closeStageCreate();
        configFinishButton();
        //configDatePickerEvent();
        createStage.show();
    }

    //Post: adds the options to the frequency spinner
    private void configSpinnerFrequency(){
        ObservableList<String> typesFrequency = FXCollections.observableArrayList("Without frequency", "Daily", "Weekly", "Monthly", "Annual");
        SpinnerValueFactory<String> factoryTypesFrequency = new SpinnerValueFactory.ListSpinnerValueFactory<>(typesFrequency);
        spinnerFrequency.setValueFactory(factoryTypesFrequency);
        BooleanProperty propertyDaily = new SimpleBooleanProperty();
        BooleanProperty propertyWeekly = new SimpleBooleanProperty();
        spinnerFrequency.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            propertyDaily.setValue(newValue.equals("Daily"));
            paneCheckDaily.visibleProperty().bindBidirectional(propertyDaily);

            propertyWeekly.setValue(newValue.equals("Weekly"));
            paneCheckWeekly.visibleProperty().bindBidirectional(propertyWeekly);
        });
    }

    //Post: configure the alarm checkboxes. If one is enabled, the other is disabled.
    private void configToggleCheckAlarm(){
        checkAbsolute.setSelected(true);
        checkAbsolute.setToggleGroup(groupAlarms);
        checkRelative.setToggleGroup(groupAlarms);
        dateAlarm.disableProperty().bindBidirectional(checkRelative.selectedProperty());
    }

    //Post: configure the checkboxes to create options according to their duration
    private void configPaneCheck(){
        dateTimeEndEvent.visibleProperty().bindBidirectional(checkPeriodTimeEvent.selectedProperty());
        timeStartEvent.visibleProperty().bindBidirectional(checkPeriodTimeEvent.selectedProperty());
        timeStartTask.visibleProperty().bindBidirectional(checkWholeDayTask.selectedProperty());
        configSpinnerFrequency();
        configToggleCheckAlarm();
    }

    //Post: the factory is created to configure the hour spinners
    private SpinnerValueFactory<Integer> createFactoryHour(){
        return new SpinnerValueFactory.IntegerSpinnerValueFactory(ZERO_HOUR, FINAL_HOUR, FIRST_HOUR);
    }

    //Post: the factory is created to configure the minute spinners
    private SpinnerValueFactory<Integer> createFactoryMinute(){
        return new SpinnerValueFactory.IntegerSpinnerValueFactory(ZERO_HOUR, FINAL_MINUTE, FIRST_MINUTE);
    }

    //Post: the factory is created to configure the day spinners
    private SpinnerValueFactory<Integer> createFactoryDay(){
        return new SpinnerValueFactory.IntegerSpinnerValueFactory(ONE_DAY, Integer.MAX_VALUE, ONE_DAY);
    }

    //Post: each spinner is added its respective factory
    private void configSpinnerIntegerValue(){
        hourStartEvent.setValueFactory(createFactoryHour());
        hourEndEvent.setValueFactory(createFactoryHour());
        hourStartTask.setValueFactory(createFactoryHour());
        hourAlarm.setValueFactory(createFactoryHour());

        minuteStartEvent.setValueFactory(createFactoryMinute());
        minuteEndEvent.setValueFactory(createFactoryMinute());
        minuteStartTask.setValueFactory(createFactoryMinute());
        minuteAlarm.setValueFactory(createFactoryMinute());

        intervalDaysValue.setValueFactory(createFactoryDay());
    }

    //Post: the options are added to the alarm comboBox
    private void configComboBoxAlarms(){
        comboBoxAlarms.setItems(FXCollections.observableArrayList("Notification", "Sound", "Email"));
    }

    //Post: add which days of the week were chosen for the frequency
    private List<DayOfWeek> obtainListDaysOfTheWeek(){
        List<DayOfWeek> arrayWeek = new ArrayList<>();
        if (checkMonday.isSelected()) arrayWeek.add(DayOfWeek.MONDAY);
        if (checkTuesday.isSelected()) arrayWeek.add(DayOfWeek.TUESDAY);
        if (checkWednesday.isSelected()) arrayWeek.add(DayOfWeek.WEDNESDAY);
        if (checkThursday.isSelected()) arrayWeek.add(DayOfWeek.THURSDAY);
        if (checkFriday.isSelected()) arrayWeek.add(DayOfWeek.FRIDAY);
        if (checkSaturday.isSelected()) arrayWeek.add(DayOfWeek.SATURDAY);
        if (checkSunday.isSelected()) arrayWeek.add(DayOfWeek.SUNDAY);
        return arrayWeek;
    }

    private Frequency addFrequencyNewEvent(){
        Frequency newFrequency;
        LocalDate deadline = deadLinePicker.getValue();

        switch (spinnerFrequency.getValue()) {
            case "Daily" -> {
                int interval = intervalDaysValue.getValue();
                newFrequency = new FrequencyDaily(interval, deadline);
            }
            case "Weekly" -> {
                List<DayOfWeek> dayOfWeeks = obtainListDaysOfTheWeek();
                newFrequency = new FrequencyWeekly(dayOfWeeks, deadline);
            }
            case "Monthly" -> newFrequency = new FrequencyMonthly(deadline);
            case "Annual" -> newFrequency = new FrequencyAnnual(deadline);
            default -> {
                return null;
            }
        }

        return newFrequency;
    }

    //Post: checks if the beginning does not exceed the end, in that case it throws an exception
    private void compareDatesAndTimes(LocalDateTime start, LocalDateTime end) throws EventTimeException {
        if (start.isAfter(end) || start.isEqual(end)) throw new EventTimeException("The start of the event cannot exceed the end");
    }

    private Event createPeriodTimeEvent(String title, String description) throws NullPointerException, EventTimeException {
        int hourStart = hourStartEvent.getValue();
        int minuteStart = minuteStartEvent.getValue();
        LocalDate startDate = startDateEvent.getValue();
        LocalTime startTime = LocalTime.of(hourStart, minuteStart);
        LocalDateTime startDateTime = startDate.atTime(startTime);

        int hourEnd = hourEndEvent.getValue();
        int minuteEnd = minuteEndEvent.getValue();
        LocalDate endDate = endDateEvent.getValue();
        LocalTime endTime = LocalTime.of(hourEnd, minuteEnd);
        LocalDateTime endDateTime = endDate.atTime(endTime);

        compareDatesAndTimes(startDateTime, endDateTime);

        return new PeriodTimeEvent(title, description, startDateTime, endDateTime);
    }

    private Event createWholeDayEvent(String title, String description) throws NullPointerException{
        LocalDate startDate = startDateEvent.getValue();
        return new WholeDayEvent(title, description, startDate);
    }

    //Post: check if the title does not exceed 60 characters, in that case an exception is thrown
    private void verifyCharacterInTitle(String title){
        if (title.chars().count() > MAX_NUM_CHARACTER) messageControllers.error("The title must not exceed 60 characters");
    }

    //Post: an error message is thrown with a custom text
    private void errorCreateAppointment(String message) {
        messageControllers.error(message);
    }

    //Post: the event is added to the button to create events
    private void createButtonEvent(){
        btnCreateEvent.setOnAction(actionEvent -> {
            Event newEvent;
            String title = eventTitle.getText();
            verifyCharacterInTitle(title);
            String description = descriptionEvent.getText();
            try {
                if (checkPeriodTimeEvent.isSelected()) {
                    newEvent = createPeriodTimeEvent(title, description);
                } else {
                    newEvent = createWholeDayEvent(title, description);
                }

                Frequency newFrequency = addFrequencyNewEvent();
                if(newFrequency != null) newEvent.setFrequency(newFrequency);

                this.dateAppointmentStart = newEvent.getStartDateTime();
                this.appointmentCreated = newEvent;
                enableAlarmTab();
            } catch (NullPointerException ex) {
                errorCreateAppointment("The required date and/or time is missing");
            } catch (EventTimeException ex) {
                errorCreateAppointment(ex.getMessage());
            }

        });
    }

    //Post: returns the created task.
    private Task createExpirationTimeTask(String title, String description) throws NullPointerException{
        int hourTask = hourStartTask.getValue();
        int minuteTask = minuteStartTask.getValue();
        LocalTime timeTask = LocalTime.of(hourTask, minuteTask);
        LocalDate dateTask = startDateTask.getValue();
        LocalDateTime dateTimeTask = dateTask.atTime(timeTask);

        return new ExpirationTimeTask(title, description, dateTimeTask);
    }

    //Post: returns the created task.
    private Task createWholeDayTask(String title, String description) throws NullPointerException{
        LocalDate dateTask = startDateTask.getValue();
        return new WholeDayTask(title, description, dateTask);
    }

    //Post: the task is added to the button to create task
    private void createButtonTask(){
        btnCreateTask.setOnAction(actionEvent -> {
            Task newTask;
            String title = taskTitle.getText();
            verifyCharacterInTitle(title);
            String description = descriptionTask.getText();
            try {
                if (checkWholeDayTask.isSelected()) {
                    newTask = createWholeDayTask(title, description);
                } else {
                    newTask = createExpirationTimeTask(title, description);
                }
                this.dateAppointmentStart = newTask.getExpirationDateTime();
                this.appointmentCreated = newTask;
                enableAlarmTab();
            } catch (NullPointerException ex) {
                errorCreateAppointment("The required date and/or time is missing");
            }

        });
    }

    //Post: the tabs of the tabPane are configured so that the alarm is configured after the event or task is created
    private void enableAlarmTab(){
        tabEvent.setDisable(true);
        tabTask.setDisable(true);
        tabAlarm.setDisable(false);
        tabPaneCreate.getSelectionModel().select(INDEX_TAB_ALARM);
    }

    //Post: the LocalDateTime is returned with the hour and minutes marked in the spinner to create the alarm
    private LocalDateTime configRingDateTime(){
        try {
            int hour = hourAlarm.getValue();
            int minute = minuteAlarm.getValue();

            if (checkAbsolute.isSelected()) {
                LocalDate date = dateAlarm.getValue();
                return date.atTime(hour, minute);
            }

            return this.dateAppointmentStart.minusHours(hour).minusMinutes(minute);
        } catch (NullPointerException ex) {
            messageControllers.error("The required date and/or time is missing");
        }
        return null;
    }

    //Post: alarm is created.
    private void createAlarm(String type) {
        LocalDateTime ringDateTime = configRingDateTime();
        if (ringDateTime == null) return;

        Alarm newAlarm = switch (type) {
            case "Email" -> new EmailAlarm(idAlarm, ringDateTime);
            case "Notification" -> new NotificationAlarm(idAlarm, ringDateTime);
            case "Sound" -> new SoundAlarm(idAlarm, ringDateTime);
            default -> null;
        };

        if (type.equals("Notification")) appointmentAlarmsControllers.active(newAlarm, appointmentCreated);
        this.appointmentCreated.addAlarm(newAlarm);
        idAlarm++;
        messageControllers.success("Added an alarm to " + appointmentCreated.getType());
    }

    //Post: the event is added to create alarms
    private void createButtonAlarms(){
        btnCreateAlarm.setOnAction(actionEvent -> {
            String type = comboBoxAlarms.getValue();

            if(type == null) {
                messageControllers.error("You must select an alarm type");
            } else {
                createAlarm(type);
            }
        });
    }

    //Post: the created appointment is added to the calendar
    private void addAppointmentToCalendar(){
        calendar.addAppointment(appointmentCreated);
        messageControllers.success("The " + appointmentCreated.getType() + " was created correctly");
    }

    //Post: event when the stage closes
    private void closeStageCreate(){
        createStage.setOnCloseRequest(event -> {
            if (appointmentCreated != null) {
                addAppointmentToCalendar();
            }
            createStage.close();
            appointmentCreated = null;
        });
    }

    //Post: button when the user wants to finish the creation.
    private void configFinishButton(){
        btnFinish.setOnAction(event -> {
            addAppointmentToCalendar();
            createStage.close();
            appointmentCreated = null;
        });
    }
}
