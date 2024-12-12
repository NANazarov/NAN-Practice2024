import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Text title = new Text("Математические Формулы");
        title.getStyleClass().add("title");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20, 20, 20, 20));

        String[] forms = {
                "Дискриминант", "Площадь/Объём", "Тригонометрия",
                "Перевод систем", "Теорема Пифагора", "Производные",
                "Интегралы", "Факториалы", "Определитель"
        };
        String[] images = {
                "triangle.png", "cube.png", "angle.png",
                "arrows-repeat-1.png", "square-minus.png", "function.png",
                "integral.png", "circle-exclamation.png", "layers-plus.png"
        };
        for (int i = 0; i < forms.length; i++) {
            Button button = createButton(forms[i], images[i]);
            if (forms[i].equals("Дискриминант")) {
                button.setOnAction(e -> {
                    try {
                        new Discriminant().start(new Stage());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
            if (forms[i].equals("Площадь/Объём")) {
                button.setOnAction(e -> {
                    try {
                        new PAV().start(new Stage());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
            if (forms[i].equals("Тригонометрия")) {
                button.setOnAction(e -> {
                    try {
                        new Trigonometry().start(new Stage());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
            if (forms[i].equals("Перевод систем")) {
                button.setOnAction(e -> {
                    try {
                        new NumConverter().start(new Stage());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
            if (forms[i].equals("Теорема Пифагора")) {
                button.setOnAction(e -> {
                    try {
                        new Pythagoras().start(new Stage());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
            if (forms[i].equals("Факториалы")) {
                button.setOnAction(e -> {
                    try {
                        new Factorial().start(new Stage());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
            if (forms[i].equals("Определитель")) {
                button.setOnAction(e -> {
                    try {
                        new MatrixDet().start(new Stage());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }

            grid.add(button, i % 3, i / 3);
        }

        VBox root = new VBox(10, title, grid);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 1024, 576);
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(576);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        primaryStage.setTitle("Математические Формулы");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton(String text, String imagePath) {
        Button button = new Button(text);
        button.getStyleClass().add("form-button");
        Image image = new Image(getClass().getResource("/images/" + imagePath).toString());
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        button.setGraphic(imageView);

        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
