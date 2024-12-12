import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        ComboBox<Integer> matrixSizeComboBox = new ComboBox<>();
        matrixSizeComboBox.getItems().addAll(2, 3, 4, 5, 6, 7, 8, 9, 10);
        matrixSizeComboBox.setValue(2);
        GridPane matrixGrid = new GridPane();
        matrixGrid.setVgap(5);
        matrixGrid.setHgap(5);
        matrixGrid.setAlignment(Pos.CENTER);

        Label resultLabel = new Label("Результат: ");
        resultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #333333;");
        Button calculateButton = new Button("Рассчитать");
        calculateButton.setOnAction(e -> {
            try {
                int size = matrixSizeComboBox.getValue();
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
            int size = matrixSizeComboBox.getValue();
            matrixGrid.getChildren().clear();

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    TextField textField = new TextField();
                    textField.setPrefWidth(50);
                    matrixGrid.add(textField, j, i);
                }
            }
        });

        matrixSizeComboBox.setOnAction(null);
        matrixSizeComboBox.setValue(2);
        matrixSizeComboBox.setOnAction(e -> {
            int size = matrixSizeComboBox.getValue();
            matrixGrid.getChildren().clear();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    TextField textField = new TextField();
                    textField.setPrefWidth(50);
                    matrixGrid.add(textField, j, i);
                }
            }
        });

        VBox root = new VBox(10, matrixSizeComboBox, matrixGrid, calculateButton, resultLabel);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f4f4f9; -fx-padding: 20px;");

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setTitle("Вычисление Определителя");
        primaryStage.setScene(scene);
        primaryStage.show();

        matrixGrid.getChildren().clear();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                TextField textField = new TextField();
                textField.setPrefWidth(50);
                matrixGrid.add(textField, j, i);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
