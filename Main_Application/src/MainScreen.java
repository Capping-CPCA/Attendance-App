import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScreen extends Application {
    Stage window;
    Scene mainMenu;
    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Label label1 = new Label("Welcome to the first scene!");
        Button button = new Button("Go to scene 2");
        button.setOnAction(e -> CloseProgram.display());

        //Layout 1 - children are laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button);
        mainMenu = new Scene(layout1, 200, 200);

        window.setScene(mainMenu);
        window.setTitle("Test");
        window.show();
    }

}