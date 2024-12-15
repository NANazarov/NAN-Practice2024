import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MatrixDet extends Application {

    public double calculateDeterminant(double[][] matrix) {
        int n = matrix.length;

        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double determinant = 0;
        for (int i = 0; i < n; i++) {
            // Создаем матрицу для миноров
            double[][] minor = new double[n - 1][n - 1];

            for (int j = 1; j < n; j++) {
                int colIndex = 0;
                for (int k = 0; k < n; k++) {
                    if (k == i) continue;
                    minor[j - 1][colIndex] = matrix[j][k];
                    colIndex++;
                }
            }
            determinant += (i % 2 == 0 ? 1 : -1) * matrix[0][i] * calculateDeterminant(minor);
        }
        return determinant;
    }

    @Override
    public void start(Stage primaryStage) {
        ComboBox<String> matrixSizeComboBox = new ComboBox<>();
        matrixSizeComboBox.getItems().addAll("2x2", "3x3", "4x4", "5x5", "6x6", "7x7", "8x8", "9x9", "10x10");
        matrixSizeComboBox.setValue("2x2");

        GridPane matrixGrid = new GridPane();
        matrixGrid.setVgap(5);
        matrixGrid.setHgap(5);
        matrixGrid.setAlignment(Pos.CENTER);

        Label resultLabel = new Label("Результат: ");
        Button calculateButton = new Button("Вычислить");

        // Добавляем стили напрямую через setStyle
        matrixSizeComboBox.setStyle(
                "-fx-background-color: #ffffff; -fx-border-radius: 5px; -fx-border-color: #dcdcdc; -fx-padding: 5px; -fx-font-size: 14px;");

        calculateButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #007BFF; -fx-text-fill: white; -fx-padding: 10 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"
        );
        calculateButton.setOnMouseEntered(e -> calculateButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #0040ff; -fx-text-fill: white; -fx-padding: 10 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"
        ));
        calculateButton.setOnMouseExited(e -> calculateButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #007BFF; -fx-text-fill: white; -fx-padding: 10 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"
        ));
        resultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #333333; -fx-font-weight: bold;");

        calculateButton.setOnAction(e -> {
            try {
                int size = Integer.parseInt(matrixSizeComboBox.getValue().substring(0, 1));
                double[][] matrix = new double[size][size];
                int index = 0;
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        TextField textField = (TextField) matrixGrid.getChildren().get(index++);
                        String input = textField.getText().trim();
                        if (input.isEmpty()) {
                            throw new NumberFormatException("Пожалуйста, заполните все поля.");
                        }
                        try {
                            matrix[i][j] = Double.parseDouble(input);
                        } catch (NumberFormatException ex) {
                            throw new NumberFormatException("Некорректный ввод: введите число.");
                        }
                    }
                }
                double determinant = calculateDeterminant(matrix);
                resultLabel.setText("Результат: " + determinant);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Ошибка: " + ex.getMessage());
            }
        });

        matrixSizeComboBox.setOnAction(e -> {
            int size = Integer.parseInt(matrixSizeComboBox.getValue().substring(0, 1));
            matrixGrid.getChildren().clear();

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    TextField textField = new TextField();
                    textField.setPrefWidth(50);
                    // Добавляем стили для полей ввода
                    textField.setStyle(
                            "-fx-background-color: #ffffff; -fx-border-radius: 5px; -fx-border-color: #dcdcdc; -fx-padding: 5px; -fx-font-size: 14px;");
                    matrixGrid.add(textField, j, i);
                }
            }
        });

        VBox root = new VBox(10, matrixSizeComboBox, matrixGrid, calculateButton, resultLabel);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f9f9f9; -fx-padding: 20px;");

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setTitle("Определитель");
        primaryStage.getIcons().add(new Image(getClass().getResource("/images/layers-plus.png").toExternalForm()));
        primaryStage.setScene(scene);
        primaryStage.show();

        matrixGrid.getChildren().clear();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                TextField textField = new TextField();
                textField.setPrefWidth(50);
                textField.setStyle(
                        "-fx-background-color: #ffffff; -fx-border-radius: 5px; -fx-border-color: #dcdcdc; -fx-padding: 5px; -fx-font-size: 14px;");
                matrixGrid.add(textField, j, i);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
