import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alert {


    public static void display(String title, String message){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label lblAlert = new Label(message);
        Button btnContinue = new Button("Continue");
        btnContinue.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(lblAlert, btnContinue);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();

    }



}
