import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Discriminant extends Application {

    private Text resultText;
    private Text rootsText;

    @Override
    public void start(Stage stage) {
        Text title = new Text("Дискриминант");
        title.getStyleClass().add("title");
        stage.getIcons().add(new Image(getClass().getResource("/images/triangle.png").toExternalForm()));

        VBox leftBlock = new VBox(10);
        leftBlock.setAlignment(Pos.TOP_CENTER);
        leftBlock.setPadding(new Insets(20));

        TextField aField = new TextField();
        aField.setPromptText("Введите значение a");
        TextField bField = new TextField();
        bField.setPromptText("Введите значение b");
        TextField cField = new TextField();
        cField.setPromptText("Введите значение c");
        Text exampleText = new Text("Пример: ax² + bx + c = 0");
        exampleText.getStyleClass().add("example-text");
        Button calculateButton = new Button("Вычислить");
        calculateButton.setOnAction(e -> {
            try {
                double a = Double.parseDouble(aField.getText());
                double b = Double.parseDouble(bField.getText());
                double c = Double.parseDouble(cField.getText());
                double discriminant = calculateDiscriminant(a, b, c);
                showResult(discriminant);
                showRoots(a, b, c, discriminant);
            } catch (NumberFormatException ex) {
                showError("Пожалуйста, введите числовые значения для a, b и c.");
            }
        });

        leftBlock.getChildren().addAll(new Label("Введите коэффициенты:"), aField, bField, cField, exampleText, calculateButton);

        VBox rightBlock = new VBox(10);
        rightBlock.setAlignment(Pos.TOP_CENTER);
        rightBlock.setPadding(new Insets(20));

        Text resultLabel = new Text("Результат");
        resultLabel.getStyleClass().add("result-label");
        resultText = new Text("Дискриминант: ");
        resultText.setStyle("-fx-font-size: 16px;");
        resultText.getStyleClass().add("result-box");
        rootsText = new Text("Корни уравнения: ");
        rootsText.setStyle("-fx-font-size: 16px;");
        rootsText.getStyleClass().add("result-box");

        rightBlock.getChildren().addAll(resultLabel, resultText, rootsText);

        HBox hbox = new HBox(20, leftBlock, rightBlock);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20));

        // Встраиваем CSS прямо в Scene
        Scene scene = new Scene(hbox, 640, 360);
        stage.setMinWidth(640);
        stage.setMinHeight(360);
        stage.setTitle("Дискриминант");
        stage.setScene(scene);

        // Встраиваем стили CSS
        scene.getRoot().setStyle(
                "-fx-background-color: #f9f9f9; -fx-font-family: 'Arial', sans-serif; -fx-font-size: 16px; -fx-text-fill: #333;"
        );

        // Стили для заголовков и текстов
        title.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #4CAF50;"
        );

        exampleText.setStyle(
                "-fx-font-size: 14px; -fx-text-fill: #7F8C8D;"
        );

        // Стили для кнопки
        calculateButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #007BFF; -fx-text-fill: white; -fx-padding: 10 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"
        );
        calculateButton.setOnMouseEntered(e -> calculateButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #0040ff; -fx-text-fill: white; -fx-padding: 10 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"
        ));
        calculateButton.setOnMouseExited(e -> calculateButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #007BFF; -fx-text-fill: white; -fx-padding: 10 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"
        ));
        stage.show();
    }

    // Дискриминант и корни
    private double calculateDiscriminant(double a, double b, double c) {
        return b * b - 4 * a * c;
    }

    private String formatNumber(double number) {
        return String.format("%.2f", number);
    }

    private void showResult(double discriminant) {
        String formattedDiscriminant = formatNumber(discriminant);
        resultText.setText("Дискриминант: D = " + formattedDiscriminant);
    }

    private void showRoots(double a, double b, double c, double discriminant) {
        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);

            String formattedRoot1 = formatNumber(root1);
            String formattedRoot2 = formatNumber(root2);
            rootsText.setText("Корни уравнения: x₁ = " + formattedRoot1 + ", x₂ = " + formattedRoot2);
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            String formattedRoot = formatNumber(root);
            rootsText.setText("Корни уравнения: x = " + formattedRoot);
        } else {
            rootsText.setText("Корней нет (D < 0)");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
