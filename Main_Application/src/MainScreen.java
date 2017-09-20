import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScreen extends Application {
    Stage window;
    Scene mainMenu;


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


        //Table Section
        Label lblTablePlaceHolder = new Label("table here");


        //Exit button
        Button btnExit = new Button("exit application");
        btnExit.setOnAction(e -> CloseProgram.display());

        //layout
        GridPane.setConstraints(lblWelcome, 0, 0);
        GridPane.setConstraints(lblTablePlaceHolder, 0,1);
        GridPane.setConstraints(btnExit, 0, 2);

        grid.getChildren().addAll(lblWelcome, lblTablePlaceHolder, btnExit);


        mainMenu = new Scene(grid, 500, 500);
        window.setScene(mainMenu);
        window.setTitle("CPCA Attendance Application");
        window.show();
    }

}