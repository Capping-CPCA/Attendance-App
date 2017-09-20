import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewAttendee {



    public static void display(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("New Attendee");
        window.setMinWidth(500);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets( 10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        window.setOnCloseRequest(e -> {
                e.consume();
                Alert.display("Please continue editing information",
                        "You are in the progress of adding a person. If " +
                                "you would like to stop, please press cancel at " +
                                "the bottom of the screen.");
        });



        Scene scene = new Scene(grid, 500, 500);
        window.setScene(scene);

        window.showAndWait();
    }

}
