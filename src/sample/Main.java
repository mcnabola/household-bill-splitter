package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;

import static java.time.temporal.ChronoUnit.DAYS;

public class Main extends Application {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private  ObservableSet<LocalDate> selectedDates;
    private  DatePicker datePicker;


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        Pane pane = new Pane();
        pane.setPrefSize(400,600);

        Button btn = new Button();
        btn.setText("Launch Keyboard lights");

        Label lbl = new Label();
        lbl.setText("label");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String random = "";
                Object[] forgotUcanhaveobjectsArray = selectedDates.toArray();  //LocalDate didnt work
                //random = forgotUcanhaveobjectsArray.toString();
                for (int i = 0;  i< forgotUcanhaveobjectsArray.length; i++){
                    LocalDate x = (LocalDate) forgotUcanhaveobjectsArray[i];
                    random += x.format(DATE_FORMAT) + ",";
                }

                //for each over LocalDate wont work, doesnt recognise it - have to cast
                lbl.setText(random);
            }
        });

        datePicker = new DatePicker();
        selectedDates = FXCollections.observableSet(new TreeSet<>());
        setUpDatePicker();

        /*datePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lbl.setText(datePicker.getValue().toString());
            }
        });*/



        StackPane root = new StackPane();
        pane.getChildren().add(btn);
        pane.getChildren().add(datePicker);
        pane.getChildren().add(lbl);
        //pane.getChildren().add(sc);
        btn.setLayoutX(50);  //this is how you change locations
        btn.setLayoutY(50);  //this is how you change locations

        lbl.setLayoutX(120);  //this is how you change locations
        lbl.setLayoutY(120);  //this is how you change locations


        datePicker.setLayoutX(80);  //this is how you change locations
        datePicker.setLayoutY(80);  //this is how you change locations
        root.getChildren().add(pane);

        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();


    }

    private void setUpDatePicker() {
        this.datePicker.setConverter(new StringConverter<LocalDate>()
        {
            @Override
            public String toString(LocalDate date)
            {
                return (date == null) ? "" : DATE_FORMAT.format(date);
            }

            @Override
            public LocalDate fromString(String string)
            {
                return ((string == null) || string.isEmpty()) ? null : LocalDate.parse(string, DATE_FORMAT);
            }
        });

        EventHandler<MouseEvent> mouseClickedEventHandler = (MouseEvent clickEvent) ->
        {
            if (clickEvent.getButton() == MouseButton.PRIMARY)
            {
                if (!this.selectedDates.contains(this.datePicker.getValue()))
                {
                    this.selectedDates.add(datePicker.getValue());

                } else
                {
                    this.selectedDates.remove(this.datePicker.getValue());

                    this.datePicker.setValue(getClosestDateInTree(new TreeSet<>(this.selectedDates), this.datePicker.getValue()));

                }

            }
            this.datePicker.show();
            clickEvent.consume();
        };

        this.datePicker.setDayCellFactory((DatePicker param) -> new DateCell()
        {
            @Override
            public void updateItem(LocalDate item, boolean empty)
            {
                super.updateItem(item, empty);

                //...
                if (item != null && !empty)
                {
                    //...
                    addEventHandler(MouseEvent.MOUSE_CLICKED, mouseClickedEventHandler);
                } else
                {
                    //...
                    removeEventHandler(MouseEvent.MOUSE_CLICKED, mouseClickedEventHandler);
                }

                if (selectedDates.contains(item))
                {

                    setStyle("-fx-background-color: rgba(3, 169, 244, 0.7);");

                } else
                {
                    setStyle(null);

                }
            }
        });

    }


    private static LocalDate getClosestDateInTree(TreeSet<LocalDate> dates, LocalDate date)
    {
        Long lower = null;
        Long higher = null;

        if (dates.isEmpty())
        {
            return null;
        }

        if (dates.size() == 1)
        {
            return dates.first();
        }

        if (dates.lower(date) != null)
        {
            lower = Math.abs(DAYS.between(date, dates.lower(date)));
        }
        if (dates.higher(date) != null)
        {
            higher = Math.abs(DAYS.between(date, dates.higher(date)));
        }

        if (lower == null)
        {
            return dates.higher(date);
        } else if (higher == null)
        {
            return dates.lower(date);
        } else if (lower <= higher)
        {
            return dates.lower(date);
        } else if (lower > higher)
        {
            return dates.higher(date);
        } else
        {
            return null;
        }
    }


    public static void main(String[] args) {
        launch(args);


    }
}
