package com.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private TableView<Student> table;
    private TextField idField, firstNameField, lastNameField,
            departmentField, majorField, emailField, imageField;

    private final ObservableList<Student> students = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        // ===== TOP MENU =====
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu theme = new Menu("Theme");
        Menu help = new Menu("Help");

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> stage.close());
        file.getItems().add(exitItem);

        menuBar.getMenus().addAll(file, edit, theme, help);
        root.setTop(menuBar);

        // ===== LEFT PANEL =====
        VBox leftPanel = new VBox();
        leftPanel.setPrefWidth(150);
        leftPanel.setAlignment(Pos.TOP_CENTER);
        leftPanel.setPadding(new Insets(20));

        ImageView avatar = new ImageView(
                new Image(getClass().getResourceAsStream("/avatar.png"))
        );
        avatar.setFitWidth(100);
        avatar.setFitHeight(100);

        leftPanel.getChildren().add(avatar);
        leftPanel.getStyleClass().add("left-panel");

        root.setLeft(leftPanel);

        // ===== CENTER TABLE =====
        table = new TableView<>();

        TableColumn<Student, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> data.getValue().idProperty());

        TableColumn<Student, String> firstCol = new TableColumn<>("First Name");
        firstCol.setCellValueFactory(data -> data.getValue().firstNameProperty());

        TableColumn<Student, String> lastCol = new TableColumn<>("Last Name");
        lastCol.setCellValueFactory(data -> data.getValue().lastNameProperty());

        TableColumn<Student, String> deptCol = new TableColumn<>("Department");
        deptCol.setCellValueFactory(data -> data.getValue().departmentProperty());

        TableColumn<Student, String> majorCol = new TableColumn<>("Major");
        majorCol.setCellValueFactory(data -> data.getValue().majorProperty());

        TableColumn<Student, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(data -> data.getValue().emailProperty());

        table.getColumns().addAll(idCol, firstCol, lastCol, deptCol, majorCol, emailCol);
        table.setItems(students);

        root.setCenter(table);

        // ===== RIGHT PANEL =====
        VBox rightPanel = new VBox(10);
        rightPanel.setPadding(new Insets(20));
        rightPanel.setPrefWidth(250);

        idField = new TextField();
        idField.setPromptText("ID");

        firstNameField = new TextField();
        firstNameField.setPromptText("First Name");

        lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        departmentField = new TextField();
        departmentField.setPromptText("Department");

        majorField = new TextField();
        majorField.setPromptText("Major");

        emailField = new TextField();
        emailField.setPromptText("Email");

        imageField = new TextField();
        imageField.setPromptText("imageURL");

        Button clearBtn = new Button("Clear");
        Button addBtn = new Button("Add");
        Button deleteBtn = new Button("Delete");
        Button editBtn = new Button("Edit");

        clearBtn.setOnAction(e -> clearFields());

        addBtn.setOnAction(e -> {
            students.add(new Student(
                    idField.getText(),
                    firstNameField.getText(),
                    lastNameField.getText(),
                    departmentField.getText(),
                    majorField.getText(),
                    emailField.getText()
            ));
            clearFields();
        });

        deleteBtn.setOnAction(e -> {
            Student selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                students.remove(selected);
            }
        });

        editBtn.setOnAction(e -> {
            Student selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setId(idField.getText());
                selected.setFirstName(firstNameField.getText());
                selected.setLastName(lastNameField.getText());
                selected.setDepartment(departmentField.getText());
                selected.setMajor(majorField.getText());
                selected.setEmail(emailField.getText());
                table.refresh();
            }
        });

        rightPanel.getChildren().addAll(
                idField, firstNameField, lastNameField,
                departmentField, majorField, emailField, imageField,
                clearBtn, addBtn, deleteBtn, editBtn
        );

        rightPanel.getStyleClass().add("right-panel");
        root.setRight(rightPanel);

        // ===== FOOTER =====
        HBox footer = new HBox();
        footer.setPrefHeight(30);
        footer.getStyleClass().add("footer");
        root.setBottom(footer);

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        stage.setTitle("FSC CSC325 _ Full Stack Project");
        stage.setScene(scene);
        stage.show();
    }

    private void clearFields() {
        idField.clear();
        firstNameField.clear();
        lastNameField.clear();
        departmentField.clear();
        majorField.clear();
        emailField.clear();
        imageField.clear();
    }

    public static void main(String[] args) {
        launch();
    }
}