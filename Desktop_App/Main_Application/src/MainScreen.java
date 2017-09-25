import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainScreen extends Application {
    Stage window;
    Scene mainMenu;
    TableView<Person> table;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        window.setOnCloseRequest(e -> {
            e.consume();
            CloseProgram.display();
        });

        Label lblWelcome = new Label("Welcome to the application!");

        //First name column
        TableColumn<Person, String> firstName = new TableColumn<>("first");
        firstName.setMinWidth(200);
        firstName.setCellValueFactory(new PropertyValueFactory<>("first"));

        //Last name column
        TableColumn<Person, String> lastName = new TableColumn<>("last");
        lastName.setMinWidth(200);
        lastName.setCellValueFactory(new PropertyValueFactory<>("last"));

        //Age column
        TableColumn<Person, String> age = new TableColumn<>("age");
        age.setMinWidth(200);
        age.setCellValueFactory(new PropertyValueFactory<>("age"));


        table = new TableView<>();
        table.setItems(getPeople());
        table.getColumns().addAll(firstName,lastName,age);

        //New Attendee button
        Button btnNewAttendee = new Button("Enter New Attendee");
        btnNewAttendee.setOnAction(e -> NewAttendee.display());

        //Exit button
        Button btnExit = new Button("exit application");
        btnExit.setOnAction(e -> CloseProgram.display());

        //layout
        GridPane.setConstraints(lblWelcome, 0, 0);
        GridPane.setConstraints(table, 0,1);
        GridPane.setConstraints(btnNewAttendee, 0,2);
        GridPane.setConstraints(btnExit, 0, 3);

        grid.getChildren().addAll(lblWelcome, table,
                btnNewAttendee, btnExit);


        mainMenu = new Scene(grid, 700, 500);
        window.setScene(mainMenu);
        window.setTitle("CPCA Attendance Application");
        window.show();
    }

    //Get all of the products
    public ObservableList<Person> getPeople(){
        ObservableList<Person> people = FXCollections.observableArrayList();
        people.add(new Person("john", "smith", "M", "white", 21,5,"12601", false));
        people.add(new Person("jane", "smith", "F", "black", 22,5,"12602", false));
        people.add(new Person("alfred", "hitchcock", "M", "other", 70,70,"12601", false));

        return people;
    }

}