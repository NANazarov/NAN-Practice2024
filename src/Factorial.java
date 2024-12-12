import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Factorial extends Application {

    @Override
    public void start(Stage stage) {
        Text title = new Text("Вычисление факториала");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #333;");
        Label inputLabel = new Label("Введите число:");
        inputLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: normal; -fx-text-fill: #333;");
        TextField inputField = new TextField();
        inputField.setStyle("-fx-font-size: 14px; -fx-padding: 8px; -fx-border-color: #ccc; -fx-border-radius: 5px;");
        Button calculateButton = new Button("Вычислить");
        calculateButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px;");
        Label resultLabel = new Label("Результат:");
        resultLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: normal; -fx-text-fill: #333;");
        Label resultText = new Label("");
        resultText.setStyle("-fx-font-size: 14px; -fx-font-weight: normal; -fx-text-fill: #333;");

        calculateButton.setOnAction(e -> {
            String input = inputField.getText().trim();
            if (input.endsWith("!")) {
                input = input.substring(0, input.length() - 1);
                try {
                    int number = Integer.parseInt(input);
                    if (number < 0) {
                        resultText.setText("Ошибка: число должно быть положительным");
                    } else {
                        String result = calculateFactorial(number);
                        resultText.setText(result);
                    }
                } catch (NumberFormatException ex) {
                    resultText.setText("Ошибка: неверный ввод");
                }
            } else {
                resultText.setText("Ошибка: необходим ввод в формате \"<число>!\"");
            }
        });

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle("-fx-padding: 20px; -fx-background-color: #f9f9f9;");
        grid.add(title, 0, 0, 2, 1);
        grid.add(inputLabel, 0, 1);
        grid.add(inputField, 1, 1);
        grid.add(calculateButton, 0, 2, 2, 1);
        grid.add(resultLabel, 0, 3, 2, 1);
        grid.add(resultText, 0, 4, 2, 1);

        Scene scene = new Scene(grid, 500, 300);
        stage.setTitle("Вычисление факториала");
        stage.setScene(scene);
        stage.show();
    }

    private String calculateFactorial(int number) {
        if (number == 0 || number == 1) {
            return number + "! = 1";
        }
        StringBuilder result = new StringBuilder();
        long factorial = 1;
        result.append(number).append("! = ");

        StringBuilder calculation = new StringBuilder();
        if (number > 10) {
            for (int i = 1; i <= 10; i++) {
                factorial *= i;
                calculation.append(i).append(" * ");
            }
            calculation.append("... * ");
            for (int i = 11; i <= number; i++) {
                factorial *= i;
            }
            calculation.append(number).append(" = ");
        } else {
            for (int i = 1; i <= number; i++) {
                factorial *= i;
                calculation.append(i).append(" * ");
            }
        }
        calculation.append(factorial);
        return calculation.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
