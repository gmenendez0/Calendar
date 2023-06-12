package org.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.models.calendar.Calendar;
import org.models.calendar.event.Event;
import org.models.calendar.event.PeriodTimeEvent;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class HomeControllers{
    final int FIRST_DAY_OF_MONTH = 1;
    final int ONE_DAY = 1;
    final int THREE_DAYS = 3;
    final int ONE_WEEK = 7;
    final int ONE_MONTH = 1;

    final int FIRST_HOUR = 1;
    final int FIRST_MINUTE = 1;
    final int FINAL_SECOND = 59;
    final int FINAL_MINUTE = 59;
    final int FINAL_HOUR = 23;

    final int MONTHLY_BUTTON = 2;
    final int WEEKLY_BUTTON = 1;
    final int DAILY_BUTTON = 0;

    @FXML
    private Stage secondStage = new Stage();

    @FXML
    private ListView<Text> appointmentList;

    @FXML
    private Button createButton;

    @FXML
    private MenuButton timeSelectorButton;

    @FXML
    private Button prevButton;

    @FXML
    private Button nextButton;

    @FXML
    private Label dateIndicator;

    @FXML
    private Label titleWindow;

    @FXML
    private Text titleAppointment;

    @FXML
    private Text descriptionAppointment;

    @FXML
    private Text typeAppointment;

    @FXML
    private Text dateImportantAppointment;

    @FXML
    private Text alarmsListAppointment;

    @FXML
    private Text titleFrequencyAppointment;

    @FXML
    private Text frequencyAppointment;

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
    private CheckBox checkWholeDayEvent;

    @FXML
    private CheckBox checkWholeDayTask;

    @FXML
    private RadioButton checkDaily;

    @FXML
    private RadioButton checkWeekly;

    @FXML
    private RadioButton checkMonthly;

    @FXML
    private RadioButton checkAnnual;

    @FXML
    private ToggleGroup groupFrequency = new ToggleGroup();

    @FXML
    private ToggleGroup groupAlarms = new ToggleGroup();

    @FXML
    private ToggleGroup groupTypesAlarms = new ToggleGroup();

    @FXML
    private RadioButton checkNotificationAlarm;

    @FXML
    private RadioButton checkSoundAlarm;

    @FXML
    private RadioButton checkEmailAlarm;

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


    private LocalDateTime shownDate;

    private final Calendar calendar;

    public HomeControllers(Calendar calendar){
        this.calendar = calendar;
    }

    //Post: Set-ups home view and returns its root.
    public Parent getHomeRoot() throws IOException{
        Parent root = getRoot();
        setUpInitialHomeView();
        return root;
    }

    //Pre: This controller must have assigned to the loader previously.
    //Post: Sets up initial home view.
    private void setUpInitialHomeView(){
        shownDate = LocalDateTime.now();
        updateRenderedAppointmentList(shownDate.toLocalDate().atStartOfDay(), shownDate.toLocalDate().atTime(FINAL_HOUR, FINAL_MINUTE, FINAL_SECOND));

        dateIndicator.setText(shownDate.toLocalDate().getDayOfWeek().toString() + " " + shownDate.toLocalDate().toString());
        setButtonsBehavior();
    }

    //Post: Returns the root of the home view.
    private Parent getRoot() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/home.fxml"));
        loader.setController(this);
        return loader.load();
    }

    //Post: Updates the appointment list shown in the view with the appointments between the given dates.
    private void updateRenderedAppointmentList(LocalDateTime start, LocalDateTime end){
        var appointmentsToRender = getAppointmentsBetweenStringFormatted(start, end);

        appointmentList.setItems(appointmentsToRender);
    }

    //Post: Returns an observable list of appointments between the given dates in text format.
    private ObservableList<Text> getAppointmentsBetweenStringFormatted(LocalDateTime start, LocalDateTime end){
        var appointments = calendar.getAppointmentsBetween(start, end);
        ObservableList<Text> appointmentsToRender = FXCollections.observableArrayList();

        Integer idNum = 0;
        for(var appointment : appointments){
            Text text = new Text(appointment.formatToString());
            text.setId(idNum.toString());
            text.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        setUpViewDetailsConfig(Integer.parseInt(text.getId()), start, end);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            appointmentsToRender.add(text);
            idNum++;
        };

        return appointmentsToRender;
    }

    //Post: Sets all home view buttons behavior.
    private void setButtonsBehavior(){
        createButtonClicked();
        prevButtonClicked();
        nextButtonClicked();
        timeSelectorButtonClicked();
    }

    //Post: Sets the "create" button behavior.
    private void createButtonClicked(){
        createButton.setOnAction(actionEvent -> {
            try {
                setupViewCreateAppointment();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    //Post: Sets the "prev" button behavior.
    private void prevButtonClicked(){
        prevButton.setOnAction(actionEvent -> {
            switch(timeSelectorButton.getText()){
                case "Daily" -> {
                    shownDate = shownDate.minusDays(ONE_DAY);
                    updateRenderedAppointmentList(shownDate.toLocalDate().atStartOfDay(), shownDate.toLocalDate().atTime(FINAL_HOUR, FINAL_MINUTE, FINAL_SECOND));
                    dateIndicator.setText(shownDate.toLocalDate().getDayOfWeek().toString() + " " + shownDate.toLocalDate().toString());
                }

                case "Weekly" -> {
                    shownDate = shownDate.minusDays(ONE_WEEK);
                    updateRenderedAppointmentList(shownDate.toLocalDate().minusDays(THREE_DAYS).atStartOfDay(), shownDate.toLocalDate().plusDays(THREE_DAYS).atTime(FINAL_HOUR, FINAL_MINUTE, FINAL_SECOND));
                    dateIndicator.setText(shownDate.minusDays(THREE_DAYS).toLocalDate().toString() + " - " + shownDate.plusDays(THREE_DAYS).toLocalDate().toString());
                }

                case "Monthly" -> {
                    shownDate = shownDate.minusMonths(ONE_MONTH);
                    renderMonthView();
                }
            }
        });
    }

    //Post: Sets the "next" button behavior.
    private void nextButtonClicked(){
        nextButton.setOnAction(actionEvent -> {
            switch(timeSelectorButton.getText()){
                case "Daily" -> {
                    shownDate = shownDate.plusDays(ONE_DAY);
                    updateRenderedAppointmentList(shownDate.toLocalDate().atStartOfDay(), shownDate.toLocalDate().atTime(FINAL_HOUR, FINAL_MINUTE, FINAL_SECOND));
                    dateIndicator.setText(shownDate.toLocalDate().getDayOfWeek().toString() + " " + shownDate.toLocalDate().toString());
                }

                case "Weekly" -> {
                    shownDate = shownDate.plusDays(ONE_WEEK);
                    updateRenderedAppointmentList(shownDate.toLocalDate().minusDays(THREE_DAYS).atStartOfDay(), shownDate.toLocalDate().plusDays(THREE_DAYS).atTime(FINAL_HOUR, FINAL_MINUTE, FINAL_SECOND));
                    dateIndicator.setText(shownDate.minusDays(THREE_DAYS).toLocalDate().toString() + " - " + shownDate.plusDays(THREE_DAYS).toLocalDate().toString());
                }

                case "Monthly" -> {
                    shownDate = shownDate.plusMonths(ONE_MONTH);
                    renderMonthView();
                }
            }
        });
    }

    //Post: Sets the "timeSelector" button options behavior.
    private void timeSelectorButtonClicked(){
        var dailyButton = timeSelectorButton.getItems().get(DAILY_BUTTON);
        var weeklyButton = timeSelectorButton.getItems().get(WEEKLY_BUTTON);
        var monthlyButton = timeSelectorButton.getItems().get(MONTHLY_BUTTON);

        setTimeSelectorDailyButtonBehavior(dailyButton);
        setTimeSelectorWeeklyButtonBehavior(weeklyButton);
        setTimeSelectorMonthlyButtonBehavior(monthlyButton);
    }

    //Post: Sets the "monthly" button (from timeSelector menuButton) behavior.
    private void setTimeSelectorMonthlyButtonBehavior(MenuItem monthlyButton){
        monthlyButton.setOnAction(actionEvent -> {
            timeSelectorButton.setText("Monthly");
            renderMonthView();
        });
    }

    //Post: Renders the shownDate s month view.
    private void renderMonthView(){
        var month = shownDate.getMonth();
        LocalDateTime firstDayOfMonth = LocalDate.of(shownDate.getYear(), month, FIRST_DAY_OF_MONTH).atStartOfDay();
        LocalDateTime lastDayOfMonth = LocalDate.of(shownDate.getYear(), month, month.length(shownDate.toLocalDate().isLeapYear())).atTime(FINAL_HOUR, FINAL_MINUTE, FINAL_SECOND);
        updateRenderedAppointmentList(firstDayOfMonth, lastDayOfMonth);

        dateIndicator.setText(shownDate.getMonth().toString() + " " + shownDate.getYear());
    }

    //Post: Sets the "Weekly" button (from timeSelector menuButton) behavior.
    private void setTimeSelectorWeeklyButtonBehavior(MenuItem weeklyButton){
        weeklyButton.setOnAction(actionEvent -> {
            timeSelectorButton.setText("Weekly");
            updateRenderedAppointmentList(shownDate.toLocalDate().minusDays(THREE_DAYS).atStartOfDay(), shownDate.toLocalDate().plusDays(THREE_DAYS).atTime(FINAL_HOUR, FINAL_MINUTE, FINAL_SECOND));
            dateIndicator.setText(shownDate.minusDays(THREE_DAYS).toLocalDate().toString() + " - " + shownDate.plusDays(THREE_DAYS).toLocalDate().toString());
        });
    }

    //Post: Sets the "Daily" button (from timeSelector menuButton) behavior.
    private void setTimeSelectorDailyButtonBehavior(MenuItem dailyButton){
        dailyButton.setOnAction(actionEvent -> {
            timeSelectorButton.setText("Daily");
            updateRenderedAppointmentList(shownDate.toLocalDate().atStartOfDay(), shownDate.toLocalDate().atTime(FINAL_HOUR, FINAL_MINUTE, FINAL_SECOND));
            dateIndicator.setText(shownDate.toLocalDate().getDayOfWeek().toString() + " " + shownDate.toLocalDate().toString());
        });
    }

    private void setUpViewDetailsConfig(Integer id, LocalDateTime start, LocalDateTime end) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/details.fxml"));
        fxmlLoader.setController(this);
        Scene secondScene = new Scene(fxmlLoader.load());
        secondScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        secondStage.setScene(secondScene);

        completeDetailsStage(calendar.getAppointmentsBetween(start, end).get(id).dataToMapOfString());
    }

    private void addFrequencyInDetails(Map<String, String> data){
        if(data.get("Type") == "Event"){
            titleFrequencyAppointment.setText("Frequency");
            frequencyAppointment.setText(data.get("Frequency"));
        }
    }

    private void completeDetailsStage(Map<String, String> data){
        titleWindow.setText(data.get("Title"));
        titleAppointment.setText(data.get("Title"));
        descriptionAppointment.setText(data.get("Description"));
        typeAppointment.setText(data.get("Type"));
        dateImportantAppointment.setText(data.get("DateImportant"));
        alarmsListAppointment.setText(data.get("Alarms"));
        addFrequencyInDetails(data);
        secondStage.show();
    }

    private void setupViewCreateAppointment() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/create.fxml"));
        fxmlLoader.setController(this);
        Scene thirdScene = new Scene(fxmlLoader.load());
        thirdScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        secondStage.setScene(thirdScene);

        configEventInCreateView();
    }

    private void configToggleCheckFrequency(){
        checkDaily.setToggleGroup(groupFrequency);
        checkWeekly.setToggleGroup(groupFrequency);
        checkMonthly.setToggleGroup(groupFrequency);
        checkAnnual.setToggleGroup(groupFrequency);

        paneCheckDaily.visibleProperty().bindBidirectional(checkDaily.selectedProperty());
        paneCheckWeekly.visibleProperty().bindBidirectional(checkWeekly.selectedProperty());
    }

    private void configToggleCheckTypesAlarm(){
        checkSoundAlarm.setToggleGroup(groupTypesAlarms);
        checkNotificationAlarm.setToggleGroup(groupTypesAlarms);
        checkEmailAlarm.setToggleGroup(groupTypesAlarms);
    }

    private void configToggleCheckAlarm(){
        checkAbsolute.setSelected(true);
        checkAbsolute.setToggleGroup(groupAlarms);
        checkRelative.setToggleGroup(groupAlarms);
        dateAlarm.disableProperty().bindBidirectional(checkRelative.selectedProperty());
    }

    private void configPaneCheck(){
        dateTimeEndEvent.visibleProperty().bindBidirectional(checkWholeDayEvent.selectedProperty());
        timeStartEvent.visibleProperty().bindBidirectional(checkWholeDayEvent.selectedProperty());
        timeStartTask.visibleProperty().bindBidirectional(checkWholeDayTask.selectedProperty());
        configToggleCheckFrequency();
        configToggleCheckAlarm();
        configToggleCheckTypesAlarm();
    }

    private void configSpinnerIntegerValue(){
        SpinnerValueFactory<Integer> factoryHourEventStart = new SpinnerValueFactory.IntegerSpinnerValueFactory(FIRST_HOUR, FINAL_HOUR, FIRST_HOUR);
        SpinnerValueFactory<Integer> factoryMinuteEventStart = new SpinnerValueFactory.IntegerSpinnerValueFactory(FIRST_MINUTE, FINAL_MINUTE, FIRST_MINUTE);
        SpinnerValueFactory<Integer> factoryHourEventEnd = new SpinnerValueFactory.IntegerSpinnerValueFactory(FIRST_HOUR, FINAL_HOUR, FIRST_HOUR);
        SpinnerValueFactory<Integer> factoryMinuteEventEnd = new SpinnerValueFactory.IntegerSpinnerValueFactory(FIRST_MINUTE, FINAL_MINUTE, FIRST_MINUTE);
        SpinnerValueFactory<Integer> factoryHourTask = new SpinnerValueFactory.IntegerSpinnerValueFactory(FIRST_HOUR, FINAL_HOUR, FIRST_HOUR);
        SpinnerValueFactory<Integer> factoryMinuteTask = new SpinnerValueFactory.IntegerSpinnerValueFactory(FIRST_MINUTE, FINAL_MINUTE, FIRST_MINUTE);
        SpinnerValueFactory<Integer> factoryHourAlarm = new SpinnerValueFactory.IntegerSpinnerValueFactory(FIRST_HOUR, FINAL_HOUR, FIRST_HOUR);
        SpinnerValueFactory<Integer> factoryMinuteAlarm = new SpinnerValueFactory.IntegerSpinnerValueFactory(FIRST_MINUTE, FINAL_MINUTE, FIRST_MINUTE);

        hourStartEvent.setValueFactory(factoryHourEventStart);
        hourEndEvent.setValueFactory(factoryHourEventEnd);
        hourStartTask.setValueFactory(factoryHourTask);
        hourAlarm.setValueFactory(factoryHourAlarm);

        minuteStartEvent.setValueFactory(factoryMinuteEventStart);
        minuteEndEvent.setValueFactory(factoryMinuteEventEnd);
        minuteStartTask.setValueFactory(factoryMinuteTask);
        minuteAlarm.setValueFactory(factoryMinuteAlarm);
    }

    private void configEventInCreateView(){
        configPaneCheck();
        configSpinnerIntegerValue();
        createButtonEvent();
        secondStage.show();
    }

    private void createPeriodTimeEvent(){
        Event newEvent = new PeriodTimeEvent();
    }

    private void createButtonEvent(){
        btnCreateEvent.setOnAction(actionEvent -> {
            Boolean isPeriodTimeEvent = checkWholeDayEvent.isSelected();
            if (isPeriodTimeEvent) {
                createPeriodTimeEvent();
            } else {

            }
        });
    }

}
