import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    private final ArrayList<String> monthsList = new ArrayList<>(Arrays.asList(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"));

    private TextField nameField;
    private ComboBox<String> monthCombo;
    private ComboBox<Integer> dayCombo;
    private TextField yearField;
    private Text timeNow;

    @Override
    public void start(Stage primaryStage) {
        VBox root = createRoot();
        Scene scene = new Scene(root);

        setupTitle(root);
        setupCurrentDate(root);

        GridPane inputGrid = createInputGrid();
        root.getChildren().add(inputGrid);

        TableView<Material> table = createTableView();
        root.getChildren().add(table);

        GridPane exportGrid = createExportGrid();
        root.getChildren().add(exportGrid);

        setupButtons(inputGrid, exportGrid, table);

        loadDataIntoTable(table);

        setupStage(primaryStage, scene);

    }

    private VBox createRoot() {
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        return root;
    }

    private void setupTitle(VBox root) {
        Text title = new Text("Refrigerator Expiry Tracker");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        root.getChildren().add(title);
    }

    private void setupCurrentDate(VBox root) {
        LocalDateTime now = LocalDateTime.now();
        timeNow = new Text("Today's date: " + now.getDayOfMonth() + " " + now.getMonth() + " " + now.getYear());
        root.getChildren().add(timeNow);
    }

    private GridPane createInputGrid() {
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(7);

        Label nameLabel = new Label("Name: ");
        nameField = new TextField();
        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setConstraints(nameField, 1, 0);
        inputGrid.getChildren().addAll(nameLabel, nameField);

        Label expireLabel = new Label("EXP (MM-DD-YYYY): ");

        monthCombo = new ComboBox<>();
        for (String month : monthsList) {
            monthCombo.getItems().add(month);
        }
        monthCombo.setPrefWidth(130);

        dayCombo = new ComboBox<>();
        dayCombo.setPrefWidth(80);

        yearField = new TextField();
        yearField.setPrefWidth(60);

        GridPane.setConstraints(expireLabel, 0, 1);
        GridPane.setConstraints(monthCombo, 1, 1);
        GridPane.setConstraints(dayCombo, 2, 1);
        GridPane.setConstraints(yearField, 3, 1);
        inputGrid.getChildren().addAll(expireLabel, monthCombo, dayCombo, yearField);

        monthCombo.setOnAction(event -> updateDayDropdown());

        return inputGrid;
    }

    private GridPane createExportGrid() {
        GridPane exportGrid = new GridPane();
        exportGrid.setHgap(10);
        exportGrid.setVgap(7);

        return exportGrid;
    }

    private TableView<Material> createTableView() {
        TableView<Material> table = new TableView<>();

        TableColumn<Material, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Material, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Material, String> dayCol = new TableColumn<>("Day");
        dayCol.setCellValueFactory(new PropertyValueFactory<>("day"));

        TableColumn<Material, String> monthCol = new TableColumn<>("Month");
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));

        TableColumn<Material, String> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Material, String> timeLeftCol = new TableColumn<>("Time Left");
        timeLeftCol.setCellValueFactory(param -> {
            Material material = param.getValue();
            int monthInt = monthsList.indexOf(material.getMonth()) + 1;
            String timeLeft = monthLeft(material.getDay(), monthInt, material.getYear());
            return new SimpleStringProperty(timeLeft);
        });

        table.getColumns().add(idCol);
        table.getColumns().add(nameCol);
        table.getColumns().add(dayCol);
        table.getColumns().add(monthCol);
        table.getColumns().add(yearCol);
        table.getColumns().add(timeLeftCol);

        idCol.setPrefWidth(40);
        nameCol.setPrefWidth(120);
        dayCol.setPrefWidth(60);
        monthCol.setPrefWidth(100);
        yearCol.setPrefWidth(80);
        timeLeftCol.setPrefWidth(260);

        return table;
    }

    private void setupButtons(GridPane inputGrid, GridPane exportGrid, TableView<Material> table) {
        Button addBtn = new Button("Add");
        GridPane.setConstraints(addBtn, 0, 2);
        inputGrid.getChildren().addAll(addBtn);

        Button clearBtn = new Button("Clear");
        GridPane.setConstraints(clearBtn, 1, 2);
        inputGrid.getChildren().addAll(clearBtn);

        Button refreshButton = new Button("Refresh");
        GridPane.setConstraints(refreshButton, 2, 2);
        inputGrid.getChildren().addAll(refreshButton);

        Button deleteButton = new Button("Delete");
        GridPane.setConstraints(deleteButton, 3, 2);
        inputGrid.getChildren().addAll(deleteButton);

        Button updateBtn = new Button("Update");
        GridPane.setConstraints(updateBtn, 4, 2);
        inputGrid.getChildren().addAll(updateBtn);

        Button exportButton = new Button("Export");
        GridPane.setConstraints(exportButton, 0, 0);
        exportGrid.getChildren().addAll(exportButton);

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addBtn.setOnAction(e -> {
                    try {
                        if (!nameField.getText().isEmpty() && !dayCombo.getSelectionModel().isEmpty()
                                && !monthCombo.getSelectionModel().isEmpty()
                                && !yearField.getText().isEmpty()) {
                            table.getItems().clear();
                            String name = nameField.getText();
                            int day = dayCombo.getSelectionModel().getSelectedItem();
                            String month = monthCombo.getSelectionModel().getSelectedItem();
                            int year = Integer.parseInt(yearField.getText());
                            int id = DB.insertData(name, day, month, year);
                            System.out.println(id);
                            monthCombo.getSelectionModel().clearSelection();
                            yearField.clear();
                            nameField.clear();
                            refreshButton.fire();
                        }
                    } catch (NumberFormatException ex) {
                        System.err.println("Error parsing year. Please enter a valid integer in format YYYY.");
                        ex.printStackTrace();
                    }
                });

            }

        });

        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nameField.clear();
                monthCombo.getSelectionModel().clearSelection();
                yearField.clear();
                table.getItems().clear();
                DB.deleteAllData();
            }
        });

        refreshButton.setOnAction(event -> {
            new Thread(() -> {
                table.getItems().clear();
                for (Material m : DB.readData()) {
                    table.getItems().add(m);
                }
            }).start();
        });

        deleteButton.setOnAction(event -> deleteSelectedRow(table));

        updateBtn.setOnAction(event -> {
            Material selectedMaterial = table.getSelectionModel().getSelectedItem();
            if (selectedMaterial != null && (!nameField.getText().isEmpty() || !dayCombo.getSelectionModel().isEmpty()
                    || !monthCombo.getSelectionModel().isEmpty() || !yearField.getText().isEmpty())) {
                int id = selectedMaterial.getId();
                String name;
                String month;
                int year;
                int day;

                if (nameField.getText().isEmpty()) {
                    name = selectedMaterial.getName();
                } else {
                    name = nameField.getText();
                }

                if (dayCombo.getSelectionModel().isEmpty()) {
                    day = selectedMaterial.getDay();
                } else {
                    day = dayCombo.getSelectionModel().getSelectedItem();
                }

                if (monthCombo.getSelectionModel().isEmpty()) {
                    month = selectedMaterial.getMonth();
                } else {
                    month = monthCombo.getSelectionModel().getSelectedItem();
                }

                if (yearField.getText().isEmpty()) {
                    year = selectedMaterial.getYear();
                } else {
                    year = Integer.parseInt(yearField.getText());
                }

                DB.updateData(id, name, day, month, year);

                nameField.clear();
                monthCombo.getSelectionModel().clearSelection();
                yearField.clear();
                refreshButton.fire();
            }
        });

        exportButton.setOnAction(event -> exportData(table));
    }

    private void loadDataIntoTable(TableView<Material> table) {
        for (Material m : DB.readData()) {
            table.getItems().add(m);
        }
    }

    private void setupStage(Stage primaryStage, Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.setTitle("Refrigerator Expiry Tracker");
        primaryStage.setWidth(650);
        primaryStage.setHeight(700);
        primaryStage.show();
    }

    private void deleteSelectedRow(TableView<Material> table) {
        Material selectedMaterial = table.getSelectionModel().getSelectedItem();
        if (selectedMaterial != null) {
            // materials.remove(selectedMaterial);
            DB.deleteData(selectedMaterial.getId());
            table.getItems().remove(selectedMaterial);
        }
    }

    private void updateDayDropdown() {
        int selectedMonthIndex = monthCombo.getSelectionModel().getSelectedIndex();
        if (selectedMonthIndex >= 0) {
            int selectedMonth = selectedMonthIndex + 1; // Adjust for 0-based index
            int daysInMonth = java.time.YearMonth.of(LocalDate.now().getYear(), selectedMonth).lengthOfMonth();
            ObservableList<Integer> daysList = FXCollections.observableArrayList();
            for (int i = 1; i <= daysInMonth; i++) {
                daysList.add(i);
            }
            dayCombo.setItems(daysList);
        }
    }

    private void exportData(TableView<Material> table) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("items_data.txt"));
            int count = 0;
            writer.write(timeNow.getText() + "\n\n");
            for (Material material : table.getItems()) {
                writer.write("ID: " + material.getId() + "\n");
                writer.write("Name: " + material.getName() + "\n");
                writer.write(
                        "Expire Date: " + material.getDay() + " " + material.getMonth() + " " + material.getYear()
                                + "\n");
                writer.write("Time Left: " + monthLeft(material.getDay(),
                        monthsList.indexOf(material.getMonth()) + 1, material.getYear()) + "\n");
                writer.write("-----\n");
                count++;
            }

            writer.close();
            System.out.println("Export " + count + " item(s) to items_data.txt");
        } catch (IOException e) {
            System.err.println("Error exporting data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: PostgreSQL driver not found");
            e.printStackTrace();
        }
        launch(args);
    }

    public String monthLeft(int eDate, int eMonth, int eYear) {
        LocalDate now = LocalDate.now();
        LocalDate expirationDate = LocalDate.of(eYear, Month.of(eMonth), eDate);
        Period period = Period.between(now, expirationDate);

        if (period.isNegative()) {
            return "Expired!";
        } else if (period.isZero()) {
            return "Expiring today!";
        }

        int yearLeft = period.getYears();
        int monthLeft = period.getMonths();
        int dayLeft = period.getDays();

        StringBuilder timeLeft = new StringBuilder();
        if (yearLeft > 0) {
            timeLeft.append(yearLeft).append(" year(s) ");
        }
        if (monthLeft > 0) {
            timeLeft.append(monthLeft).append(" month(s) ");
        }
        if (dayLeft > 0) {
            timeLeft.append(dayLeft).append(" day(s)");
        }

        return "Expires in " + timeLeft.toString();
    }
}
