package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;



//THE ANIMALCAREAPP GUI
public class AnimalCareApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = addOwnerFormPane();
        addUIControls(gridPane);
        primaryStage.setScene(new Scene(gridPane, 1000, 1000));
        primaryStage.setTitle("Animal Care App");
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }


    private GridPane addOwnerFormPane() {
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);

        //If the screen resizes, then the columns should grow as well
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Add owner into the system");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("Full Name : ");
        gridPane.add(nameLabel, 0,1);

        // Add Name Text Field
        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1,1);

        // Add Phone Number Label
        Label phoneNumberLabel = new Label("Phone Number : ");
        gridPane.add(phoneNumberLabel, 0, 2);

        // Add Phone Number Text Field
        TextField phoneNumberField = new TextField();
        phoneNumberField.setPrefHeight(40);
        gridPane.add(phoneNumberField, 1, 2);

        // Add Email Label
        Label emailLabel = new Label("Email : ");
        gridPane.add(emailLabel, 0, 3);

        // Add Email Text Field
        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        gridPane.add(emailField, 1, 3);

        // Add Address Label
        Label addressLabel = new Label("Address : ");
        gridPane.add(addressLabel, 0, 4);

        // Add Address Text Field
        TextField addressField = new TextField();
        addressField.setPrefHeight(40);
        gridPane.add(addressField, 1, 4);

        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (nameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                            "Form Error!", "Please enter your full name");
                    return;
                }

                if(phoneNumberField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                            "Form Error!", "Please enter your phone number");
                    return;
                }

                if(emailField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                            "Form Error!", "Please enter your email");
                    return;
                }

                if(addressField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                            "Form Error!", "Please enter your address");
                    return;
                }

                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(),
                        "Owner successfully added", "Welcome " + nameField.getText());

            }
        });

        gridPane.add(submitButton, 0, 5, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));
    }


    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


}

