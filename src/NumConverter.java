import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NumConverter extends Application {

    @Override
    public void start(Stage stage) {
        Text title = new Text("Перевод систем счислений");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #333;");
        stage.getIcons().add(new Image(getClass().getResource("/images/arrows-repeat-1.png").toExternalForm()));
        Label fromLabel = new Label("Из системы:");
        fromLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: normal; -fx-text-fill: #333;");
        ComboBox<String> fromSystem = new ComboBox<>();
        fromSystem.getItems().addAll("Десятичная", "Двоичная", "Шестнадцатеричная", "Троичная", "Пятеричная", "Восьмеричная");
        fromSystem.setValue("Десятичная");
        Label toLabel = new Label("В систему:");
        toLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: normal; -fx-text-fill: #333;");
        ComboBox<String> toSystem = new ComboBox<>();
        toSystem.getItems().addAll("Десятичная", "Двоичная", "Шестнадцатеричная", "Троичная", "Пятеричная", "Восьмеричная");
        toSystem.setValue("Десятичная");
        Label inputLabel = new Label("Введите число:");
        inputLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: normal; -fx-text-fill: #333;");
        TextField inputField = new TextField();
        inputField.setStyle("-fx-font-size: 14px; -fx-padding: 8px; -fx-border-color: #ccc; -fx-border-radius: 5px;");
        Button convertButton = new Button("Перевести");
        convertButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #007BFF; -fx-text-fill: white; -fx-padding: 10 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"
        );
        convertButton.setOnMouseEntered(e -> convertButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #0040ff; -fx-text-fill: white; -fx-padding: 10 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"
        ));
        convertButton.setOnMouseExited(e -> convertButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #007BFF; -fx-text-fill: white; -fx-padding: 10 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"
        ));
        Label resultLabel = new Label("Результат:");
        resultLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: normal; -fx-text-fill: #333;");
        Label resultText = new Label("");
        resultText.setStyle("-fx-font-size: 14px; -fx-font-weight: normal; -fx-text-fill: #333;");

        convertButton.setOnAction(e -> {
            try {
                String from = fromSystem.getValue();
                String to = toSystem.getValue();
                String input = inputField.getText();

                int decimalValue = 0;
                switch (from) {
                    case "Десятичная":
                        decimalValue = Integer.parseInt(input);
                        break;
                    case "Двоичная":
                        decimalValue = Integer.parseInt(input, 2);
                        break;
                    case "Шестнадцатеричная":
                        decimalValue = Integer.parseInt(input, 16);
                        break;
                    case "Троичная":
                        decimalValue = Integer.parseInt(input, 3);
                        break;
                    case "Пятеричная":
                        decimalValue = Integer.parseInt(input, 5);
                        break;
                    case "Восьмеричная":
                        decimalValue = Integer.parseInt(input, 8);
                        break;
                }

                String result = "";
                switch (to) {
                    case "Десятичная":
                        result = String.valueOf(decimalValue);
                        break;
                    case "Двоичная":
                        result = Integer.toBinaryString(decimalValue);
                        break;
                    case "Шестнадцатеричная":
                        result = Integer.toHexString(decimalValue).toUpperCase();
                        break;
                    case "Троичная":
                        result = Integer.toString(decimalValue, 3);
                        break;
                    case "Пятеричная":
                        result = Integer.toString(decimalValue, 5);
                        break;
                    case "Восьмеричная":
                        result = Integer.toOctalString(decimalValue);
                        break;
                }

                resultText.setText("Результат: " + result);
            } catch (NumberFormatException ex) {
                resultText.setText("Ошибка! Неверный формат числа.");
            }
        });

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle("-fx-padding: 20px; -fx-background-color: #f9f9f9;");
        grid.add(title, 0, 0, 2, 1);
        grid.add(fromLabel, 0, 1);
        grid.add(fromSystem, 1, 1);
        grid.add(toLabel, 0, 2);
        grid.add(toSystem, 1, 2);
        grid.add(inputLabel, 0, 3);
        grid.add(inputField, 1, 3);
        grid.add(convertButton, 0, 4, 2, 1);
        grid.add(resultLabel, 0, 5, 2, 1);
        grid.add(resultText, 0, 6, 2, 1);

        Scene scene = new Scene(grid, 400, 350);
        stage.setTitle("Перевод систем счислений");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
