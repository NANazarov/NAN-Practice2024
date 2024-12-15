import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Pythagoras extends Application {

    @Override
    public void start(Stage stage) {
        Text title = new Text("Теорема Пифагора");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #333;");
        stage.getIcons().add(new Image(getClass().getResource("/images/square-minus.png").toExternalForm()));
        Label catetALabel = new Label("Катет 1 (a):");
        catetALabel.setStyle("-fx-font-size: 14px; -fx-font-weight: normal; -fx-text-fill: #333;");
        TextField catetAField = new TextField();
        catetAField.setStyle("-fx-font-size: 14px; -fx-padding: 8px; -fx-border-color: #ccc; -fx-border-radius: 5px;");
        Label catetBLabel = new Label("Катет 2 (b):");
        catetBLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: normal; -fx-text-fill: #333;");
        TextField catetBField = new TextField();
        catetBField.setStyle("-fx-font-size: 14px; -fx-padding: 8px; -fx-border-color: #ccc; -fx-border-radius: 5px;");
        Label hypotenuseLabel = new Label("Гипотенуза (c):");
        hypotenuseLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: normal; -fx-text-fill: #333;");
        TextField hypotenuseField = new TextField();
        hypotenuseField.setStyle("-fx-font-size: 14px; -fx-padding: 8px; -fx-border-color: #ccc; -fx-border-radius: 5px;");
        Button calculateButton = new Button("Вычислить");
        calculateButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #007BFF; -fx-text-fill: white; -fx-padding: 10 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"
        );
        calculateButton.setOnMouseEntered(e -> calculateButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #0040ff; -fx-text-fill: white; -fx-padding: 10 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"
        ));
        calculateButton.setOnMouseExited(e -> calculateButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #007BFF; -fx-text-fill: white; -fx-padding: 10 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"
        ));
        Label resultLabel = new Label("Результат:");
        resultLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: normal; -fx-text-fill: #333;");
        Label resultText = new Label("");
        resultText.setStyle("-fx-font-size: 14px; -fx-font-weight: normal; -fx-text-fill: #333;");

        Image pythagoreanImage = new Image("images/pythagoras-image.png");
        ImageView imageView = new ImageView(pythagoreanImage);
        imageView.setFitWidth(250);
        imageView.setPreserveRatio(true);

        calculateButton.setOnAction(e -> {
            try {
                String catetA = catetAField.getText();
                String catetB = catetBField.getText();
                String hypotenuse = hypotenuseField.getText();
                double result = 0;
                boolean inputGiven = false;

                if (!catetA.isEmpty() && !catetB.isEmpty()) {
                    double a = Double.parseDouble(catetA);
                    double b = Double.parseDouble(catetB);
                    result = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
                    resultText.setText("Гипотенуза: c = " + String.format("%.2f", result));
                    inputGiven = true;
                } else if (!catetA.isEmpty() && !hypotenuse.isEmpty()) {
                    double a = Double.parseDouble(catetA);
                    double c = Double.parseDouble(hypotenuse);
                    if (c > a) {
                        result = Math.sqrt(Math.pow(c, 2) - Math.pow(a, 2));
                        resultText.setText("Катет 2: b = " + String.format("%.2f", result));
                        inputGiven = true;
                    } else {
                        resultText.setText("Ошибка: гипотенуза не может быть меньше катета");
                    }
                } else if (!catetB.isEmpty() && !hypotenuse.isEmpty()) {
                    double b = Double.parseDouble(catetB);
                    double c = Double.parseDouble(hypotenuse);
                    if (c > b) {
                        result = Math.sqrt(Math.pow(c, 2) - Math.pow(b, 2));
                        resultText.setText("Катет 1: a = " + String.format("%.2f", result));
                        inputGiven = true;
                    } else {
                        resultText.setText("Ошибка: гипотенуза не может быть меньше катета");
                    }
                } else {
                    resultText.setText("Ошибка: нужны значения двух переменных");
                }

                if (!inputGiven) {
                    resultText.setText("Ошибка: нужны значения двух переменных");
                }
            } catch (NumberFormatException ex) {
                resultText.setText("Ошибка: неверный ввод");
            }
        });

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle("-fx-padding: 20px; -fx-background-color: #f9f9f9;");
        grid.add(title, 0, 0, 2, 1);
        grid.add(catetALabel, 0, 1);
        grid.add(catetAField, 1, 1);
        grid.add(catetBLabel, 0, 2);
        grid.add(catetBField, 1, 2);
        grid.add(hypotenuseLabel, 0, 3);
        grid.add(hypotenuseField, 1, 3);
        grid.add(calculateButton, 0, 4, 2, 1);
        grid.add(resultLabel, 0, 5, 2, 1);
        grid.add(resultText, 0, 6, 2, 1);
        grid.add(imageView, 2, 1, 1, 3);

        Scene scene = new Scene(grid, 600, 350);
        stage.setTitle("Решение теоремы Пифагора");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
