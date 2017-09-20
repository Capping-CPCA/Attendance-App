import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CloseProgram {

    public static void display(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Exit Program?");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText("Are you sure you wish to exit the program?");

        Button closeButton = new Button("Exit Program");
        closeButton.setOnAction(e -> System.exit(0));

        Button dontCloseButton = new Button("Continue Working");
        dontCloseButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,dontCloseButton, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();
    }
}
