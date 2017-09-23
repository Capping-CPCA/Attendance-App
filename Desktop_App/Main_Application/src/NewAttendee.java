import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.soap.Text;

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

        Label lblFirst = new Label("First: ");
        TextField tfFirst = new TextField();
        Label lblLast = new Label("Last: ");
        TextField tfLast = new TextField();

        Label lblRace = new Label("Race: ");
        ChoiceBox<String> chobRace = new ChoiceBox<>();
        chobRace.getItems().addAll("black",
          "white",
          "hispanic",
          "other");

        Label lblNumChildren = new Label("Number of Children: ");
        TextField tfNumChildren = new TextField();

        Label lblZipcode = new Label("Zip Code: ");
        TextField tfZipcode = new TextField();

        //Cancel button
        Button btnCancel = new Button("Cancel Entry");
        //TODO: confirm user wants to discard his/her work
        btnCancel.setOnAction(e -> window.close());

        //Submit button
        Button btnSubmit = new Button("Submit");
        //TODO: implement functionality of button
        btnSubmit.setOnAction(e -> window.close());


        //layout
        GridPane.setConstraints(lblFirst, 0, 0);
        GridPane.setConstraints(tfFirst, 1, 0);

        GridPane.setConstraints(lblLast, 2, 0);
        GridPane.setConstraints(tfLast, 3, 0);

        GridPane.setConstraints(lblRace, 0,1);
        GridPane.setConstraints(chobRace, 1, 1);

        GridPane.setConstraints(lblNumChildren, 0, 2);
        GridPane.setConstraints(tfNumChildren, 1, 2);

        GridPane.setConstraints(lblZipcode,0,3);
        GridPane.setConstraints(tfZipcode, 1, 3);

        GridPane.setConstraints(btnSubmit, 1, 4);
        GridPane.setConstraints(btnCancel, 2,4);

        grid.getChildren().addAll(
          lblFirst, tfFirst,
          lblLast, tfLast,
          lblRace, chobRace,
          lblNumChildren, tfNumChildren,
          lblZipcode, tfZipcode,
          btnSubmit,
          btnCancel
        );

        Scene scene = new Scene(grid, 500, 500);
        window.setScene(scene);

        window.showAndWait();
    }

}
