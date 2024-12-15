import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Trigonometry extends Application {

    @Override
    public void start(Stage stage) {
        Text title = new Text("Тригонометрия");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #333;");
        stage.getIcons().add(new Image(getClass().getResource("/images/angle.png").toExternalForm()));
        Label label = new Label("Введите угол (в градусах):");
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: normal; -fx-text-fill: #333;");
        TextField inputField = new TextField();
        inputField.setStyle("-fx-font-size: 14px; -fx-padding: 8px; -fx-border-color: #ccc; -fx-border-radius: 5px;");
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

        Label sinLabel = new Label("sin(α) = ");
        sinLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
        Label cosLabel = new Label("cos(α) = ");
        cosLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
        Label tanLabel = new Label("tg(α) = ");
        tanLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
        Label cotLabel = new Label("ctg(α) = ");
        cotLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
        Label arcsinLabel = new Label("arcsin(x) = ");
        arcsinLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
        Label arccosLabel = new Label("arccos(x) = ");
        arccosLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
        Label arctanLabel = new Label("arctg(x) = ");
        arctanLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
        Label arcctgLabel = new Label("arcctg(x) = ");
        arcctgLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");

        calculateButton.setOnAction(e -> {
            try {
                double angle = Double.parseDouble(inputField.getText());
                double radians = Math.toRadians(angle);
                sinLabel.setText("sin(α) = " + String.format("%.4f", Math.sin(radians)));
                cosLabel.setText("cos(α) = " + String.format("%.4f", Math.cos(radians)));
                tanLabel.setText("tan(α) = " + String.format("%.4f", Math.tan(radians)));
                cotLabel.setText("cot(α) = " + String.format("%.4f", 1 / Math.tan(radians)));
                double xValue = Math.sin(radians);
                arcsinLabel.setText("arcsin(x) = " + String.format("%.4f", Math.toDegrees(Math.asin(xValue))));
                arccosLabel.setText("arccos(x) = " + String.format("%.4f", Math.toDegrees(Math.acos(xValue))));
                arctanLabel.setText("arctan(x) = " + String.format("%.4f", Math.toDegrees(Math.atan(xValue))));
                arcctgLabel.setText("arcctg(x) = " + String.format("%.4f", Math.toDegrees(Math.PI / 2 - Math.atan(xValue))));
                resultLabel.setText("Результат:");
            } catch (NumberFormatException ex) {
                resultLabel.setText("Ошибка: неверный ввод");
            }
        });

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle("-fx-padding: 20px; -fx-background-color: #f9f9f9;");
        grid.add(title, 0, 0, 2, 1);
        grid.add(label, 0, 1);
        grid.add(inputField, 1, 1);
        grid.add(calculateButton, 0, 2, 2, 1);
        grid.add(resultLabel, 0, 3, 2, 1);
        grid.add(sinLabel, 0, 4, 2, 1);
        grid.add(cosLabel, 0, 5, 2, 1);
        grid.add(tanLabel, 0, 6, 2, 1);
        grid.add(cotLabel, 0, 7, 2, 1);
        grid.add(arcsinLabel, 0, 8, 2, 1);
        grid.add(arccosLabel, 0, 9, 2, 1);
        grid.add(arctanLabel, 0, 10, 2, 1);
        grid.add(arcctgLabel, 0, 11, 2, 1);

        Scene scene = new Scene(grid, 400, 500);
        stage.setTitle("Тригонометрия");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
