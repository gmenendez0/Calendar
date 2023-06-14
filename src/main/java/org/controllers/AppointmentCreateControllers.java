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

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentCreateControllers {

    final int ONE_DAY = 1;
    final int ZERO_HOUR = 0;
    final int FIRST_HOUR = 1;
    final int FIRST_MINUTE = 1;
    final int FINAL_MINUTE = 59;
    final int FINAL_HOUR = 23;
    final int INDEX_TAB_ALARM = 2;
    private Calendar calendar;
    private Appointment appointmentCreated;
    private final NotificationAlarmsControllers notificationAlarmsControllers = new NotificationAlarmsControllers();
    private LocalDateTime dateAppointmentStart;
    @FXML
    private final Stage createStage = new Stage();
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

    public void setupViewCreateAppointment(Calendar calendar) throws IOException {
        this.calendar = calendar;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/appointmentCreate.fxml"));
        fxmlLoader.setController(this);
        Scene thirdScene = new Scene(fxmlLoader.load());
        thirdScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        createStage.setScene(thirdScene);

        configEventInCreateView();
    }

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

    private void configToggleCheckAlarm(){
        checkAbsolute.setSelected(true);
        checkAbsolute.setToggleGroup(groupAlarms);
        checkRelative.setToggleGroup(groupAlarms);
        dateAlarm.disableProperty().bindBidirectional(checkRelative.selectedProperty());
    }

    private void configPaneCheck(){
        dateTimeEndEvent.visibleProperty().bindBidirectional(checkPeriodTimeEvent.selectedProperty());
        timeStartEvent.visibleProperty().bindBidirectional(checkPeriodTimeEvent.selectedProperty());
        timeStartTask.visibleProperty().bindBidirectional(checkWholeDayTask.selectedProperty());
        configSpinnerFrequency();
        configToggleCheckAlarm();
    }

    private SpinnerValueFactory<Integer> createFactoryHour(){
        return new SpinnerValueFactory.IntegerSpinnerValueFactory(ZERO_HOUR, FINAL_HOUR, FIRST_HOUR);
    }

    private SpinnerValueFactory<Integer> createFactoryMinute(){
        return new SpinnerValueFactory.IntegerSpinnerValueFactory(ZERO_HOUR, FINAL_MINUTE, FIRST_MINUTE);
    }

    private SpinnerValueFactory<Integer> createFactoryDay(){
        return new SpinnerValueFactory.IntegerSpinnerValueFactory(ONE_DAY, Integer.MAX_VALUE, ONE_DAY);
    }

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

    private void configComboBoxAlarms(){
        comboBoxAlarms.setItems(FXCollections.observableArrayList("Notification", "Sound", "Email"));
    }

    private void configEventInCreateView(){
        configPaneCheck();
        configSpinnerIntegerValue();
        configComboBoxAlarms();
        createButtonEvent();
        createButtonTask();
        createButtonAlarms();
        addAppointmentToCalendar();
        createStage.show();
    }

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

    private Event createPeriodTimeEvent(String title, String description){
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

        return new PeriodTimeEvent(title, description, startDateTime, endDateTime);
    }

    private Event createWholeDayEvent(String title, String description){
        LocalDate startDate = startDateEvent.getValue();
        return new WholeDayEvent(title, description, startDate);
    }

    private void createButtonEvent(){
        btnCreateEvent.setOnAction(actionEvent -> {
            Event newEvent;
            String title = eventTitle.getText();
            String description = descriptionEvent.getText();

            boolean isPeriodTimeEvent = checkPeriodTimeEvent.isSelected();
            if (isPeriodTimeEvent) {
                newEvent = createPeriodTimeEvent(title, description);
            } else {
                newEvent = createWholeDayEvent(title, description);
            }

            Frequency newFrequency = addFrequencyNewEvent();
            if(addFrequencyNewEvent() != null) newEvent.setFrequency(newFrequency);

            this.dateAppointmentStart = newEvent.getStartDateTime();
            this.appointmentCreated = newEvent;
            enableAlarmTab();
        });
    }

    private void enableAlarmTab(){
        tabEvent.setDisable(true);
        tabTask.setDisable(true);
        tabAlarm.setDisable(false);
        tabPaneCreate.getSelectionModel().select(INDEX_TAB_ALARM);
    }

    private Task createExpirationTimeTask(String title, String description){
        int hourTask = hourStartTask.getValue();
        int minuteTask = minuteStartTask.getValue();
        LocalTime timeTask = LocalTime.of(hourTask, minuteTask);
        LocalDate dateTask = startDateTask.getValue();
        LocalDateTime dateTimeTask = dateTask.atTime(timeTask);

        return new ExpirationTimeTask(title, description, dateTimeTask);
    }

    private Task createWholeDayTask(String title, String description) {
        LocalDate dateTask = startDateTask.getValue();
        return new WholeDayTask(title, description, dateTask);
    }

    private void createButtonTask(){
        btnCreateTask.setOnAction(actionEvent -> {
            Task newTask;
            String title = taskTitle.getText();
            String description = descriptionTask.getText();
            boolean isWholeDayTask = checkWholeDayTask.isSelected();
            if (isWholeDayTask) {
                newTask = createWholeDayTask(title, description);
            } else {
                newTask = createExpirationTimeTask(title, description);
            }
            this.dateAppointmentStart = newTask.getExpirationDateTime();
            this.appointmentCreated = newTask;
            enableAlarmTab();
        });
    }

    private LocalDateTime configRingDateTime(){
        int hour = hourAlarm.getValue();
        int minute = minuteAlarm.getValue();
        if (checkAbsolute.isSelected()) {
            LocalDate date = dateAlarm.getValue();
            return date.atTime(hour, minute);
        }
        return this.dateAppointmentStart.minusHours(hour).minusMinutes(minute);
    }

    private void createAlarm(String type) throws IOException {
        LocalDateTime ringDateTime = configRingDateTime();
        Alarm newAlarm = null;
        if(type == null) {
            notificationAlarmsControllers.error("You must select an alarm type");
            createStage.close();
            return;
        }
        switch(type) {
            case "Email":
                newAlarm = new EmailAlarm(appointmentCreated.getId(), ringDateTime);
                break;
            case "Notification":
                newAlarm = new NotificationAlarm(appointmentCreated.getId(), ringDateTime);
                notificationAlarmsControllers.activeNotification(newAlarm, calendar, newAlarm.getId());
                break;
            case "Sound":
                newAlarm = new SoundAlarm(appointmentCreated.getId(), ringDateTime);
                break;
        }
        this.appointmentCreated.addAlarm(newAlarm);
    }

    private void createButtonAlarms(){
        btnCreateAlarm.setOnAction(actionEvent -> {
            String type = comboBoxAlarms.getValue();
            try {
                createAlarm(type);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void addAppointmentToCalendar(){
        createStage.setOnCloseRequest(event -> {
            event.consume();
            if (appointmentCreated != null) calendar.addAppointment(appointmentCreated);
            createStage.close();
        });
    }
}
