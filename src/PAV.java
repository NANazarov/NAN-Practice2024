import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PAV extends Application {

    private VBox root;
    private HBox buttonBox;
    private HBox figureButtonBox;
    private VBox formBox;
    private VBox resultBox;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Кнопки типов операций
        Text title = new Text("Выберите операцию");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-fill: #333333;");

        Button perimeterButton = new Button("Периметр");
        Button areaButton = new Button("Площадь");
        Button volumeButton = new Button("Объём");
        configureButton(perimeterButton, true);
        configureButton(areaButton, true);
        configureButton(volumeButton, false);
        buttonBox = new HBox(perimeterButton, areaButton, volumeButton);
        styleButtonBox(buttonBox);
        figureButtonBox = new HBox();
        figureButtonBox.setVisible(true);

        // Кнопки фигур периметров
        Button triangleButton = new Button("Треугольник");
        Button rectangleButton = new Button("Прямоугольник");
        Button parallelogramButton = new Button("Параллелограмм");
        Button trapezoidButton = new Button("Трапеция");
        Button rhombusButton = new Button("Ромб");
        Button circleButton = new Button("Окружность");
        configureButton(triangleButton, true);
        configureButton(rectangleButton, true);
        configureButton(parallelogramButton, true);
        configureButton(trapezoidButton, true);
        configureButton(rhombusButton, true);
        configureButton(circleButton, false);
        figureButtonBox.getChildren().addAll(
                triangleButton, rectangleButton, parallelogramButton,
                trapezoidButton, rhombusButton, circleButton
        );
        styleButtonBox(figureButtonBox);

        formBox = new VBox(10);
        formBox.setAlignment(Pos.TOP_LEFT);
        formBox.setPadding(new Insets(20));
        resultBox = new VBox(10);
        resultBox.setAlignment(Pos.TOP_RIGHT);
        resultBox.setPadding(new Insets(20));
        HBox mainContent = new HBox(20, formBox, resultBox);
        mainContent.setAlignment(Pos.CENTER);
        root = new VBox(20, title, buttonBox, figureButtonBox, mainContent);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f9f9f9;");
        perimeterButton.setOnAction(e -> showFigureSelection());
        areaButton.setOnAction(e -> showAreaForm());
        volumeButton.setOnAction((e -> showVolumeForm()));

        triangleButton.setOnAction(e -> showTriangleForm());
        rectangleButton.setOnAction(e -> showRectangleForm());
        circleButton.setOnAction(e -> showCircleForm());
        parallelogramButton.setOnAction(e -> showParallelogramForm());
        trapezoidButton.setOnAction(e -> showTrapezoidForm());
        rhombusButton.setOnAction(e -> showRhombusForm());

        Scene scene = new Scene(root, 800, 400);
        primaryStage.setTitle("Функции");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void configureButton(Button button, boolean hasBorder) {
        String buttonStyle = "-fx-background-color: #f5f5f5; -fx-text-fill: #333333; -fx-font-size: 14px; "
                + (hasBorder ? "-fx-border-color: #e0e0e0; -fx-border-width: 0 1 0 0;" : "")
                + "-fx-padding: 10;";
        button.setStyle(buttonStyle);
        button.setPrefWidth(120);
    }

    private void styleButtonBox(HBox buttonBox) {
        buttonBox.setSpacing(0);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 1; -fx-border-radius: 8; "
                + "-fx-background-radius: 8; -fx-background-color: #ffffff;");
    }

    private void showFigureSelection() {
        formBox.getChildren().clear();
        resultBox.getChildren().clear();
        primaryStage.setWidth(800);
        primaryStage.setHeight(400);
        if (!root.getChildren().contains(figureButtonBox)) {
            root.getChildren().add(figureButtonBox);
        }
        figureButtonBox.setVisible(true);
        figureButtonBox.setManaged(true);
    }

    // Периметр треугольника
    private void showTriangleForm() {
        formBox.getChildren().clear();
        resultBox.getChildren().clear();
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        Label sideALabel = new Label("Сторона 1 (a):");
        TextField sideAField = new TextField();
        Label sideBLabel = new Label("Сторона 2 (b):");
        TextField sideBField = new TextField();
        Label sideCLabel = new Label("Сторона 3 (c):");
        TextField sideCField = new TextField();
        Button calculateButton = new Button("Вычислить");
        calculateButton.setOnAction(e -> {
            try {
                double a = Double.parseDouble(sideAField.getText());
                double b = Double.parseDouble(sideBField.getText());
                double c = Double.parseDouble(sideCField.getText());
                double perimeter = a + b + c;
                resultBox.getChildren().clear();
                resultBox.getChildren().add(new Label("Периметр: " + perimeter));
            } catch (NumberFormatException ex) {
                resultBox.getChildren().clear();
                resultBox.getChildren().add(new Label("Ошибка: неверный ввод"));
            }
        });

        formBox.getChildren().addAll(sideALabel, sideAField, sideBLabel, sideBField, sideCLabel, sideCField, calculateButton);
    }

    // Периметр прямоугольника
    private void showRectangleForm() {
        formBox.getChildren().clear();
        resultBox.getChildren().clear();
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        Label lengthLabel = new Label("Длина (a):");
        TextField lengthField = new TextField();
        Label widthLabel = new Label("Ширина (b):");
        TextField widthField = new TextField();
        Button calculateButton = new Button("Вычислить");
        calculateButton.setOnAction(e -> {
            try {
                double length = Double.parseDouble(lengthField.getText());
                double width = Double.parseDouble(widthField.getText());
                double perimeter = 2 * (length + width);
                resultBox.getChildren().clear();
                resultBox.getChildren().add(new Label("Периметр: " + perimeter));
            } catch (NumberFormatException ex) {
                resultBox.getChildren().clear();
                resultBox.getChildren().add(new Label("Ошибка: неверный ввод"));
            }
        });

        formBox.getChildren().addAll(lengthLabel, lengthField, widthLabel, widthField, calculateButton);
    }

    // Периметр параллелограмма
    private void showParallelogramForm() {
        formBox.getChildren().clear();
        resultBox.getChildren().clear();
        primaryStage.setWidth(800);
        primaryStage.setHeight(400);

        Button bySidesButton = new Button("По сторонам");
        Button byDiagonalsButton = new Button("По диагоналям и стороне");
        Button bySideHeightAngleButton = new Button("По стороне, высоте и углу");
        String buttonStyle = "-fx-border-radius: 0px 8px 8px 0px; -fx-background-radius: 0px 8px 8px 0px; -fx-border-color: transparent; -fx-background-color: #F5F5F5;";
        bySidesButton.setStyle(buttonStyle);
        byDiagonalsButton.setStyle(buttonStyle);
        bySideHeightAngleButton.setStyle(buttonStyle);
        bySidesButton.setStyle("-fx-border-color: transparent #E0E0E0 transparent transparent; -fx-border-width: 1px; -fx-border-radius: 8 0 0 8; -fx-background-radius: 8 0 0 8; -fx-background-color: #F5F5F5;");
        byDiagonalsButton.setStyle("-fx-border-color: transparent #E0E0E0 transparent transparent; -fx-border-width: 1px; -fx-border-radius: 0 0 0 0; -fx-background-radius: 0 0 0 0; -fx-background-color: #F5F5F5;");

        HBox methodBox = new HBox(bySidesButton, byDiagonalsButton, bySideHeightAngleButton);
        methodBox.setSpacing(0);
        methodBox.setAlignment(Pos.CENTER);
        methodBox.setPadding(new Insets(10));
        methodBox.setStyle("-fx-text-fill: #333333; -fx-font-size: 14px; -fx-font-weight: normal; " +
                "-fx-border-color: #E0E0E0; -fx-border-width: 1; -fx-border-radius: 8; " +
                "-fx-background-radius: 8; -fx-background-color: #ffffff;");

        VBox inputBox = new VBox(10);

        bySidesButton.setOnAction(e -> {
            inputBox.getChildren().clear();
            resultBox.getChildren().clear();
            primaryStage.setWidth(800);
            primaryStage.setHeight(650);

            Label sideALabel = new Label("Сторона 1 (a):");
            TextField sideAField = new TextField();
            Label sideBLabel = new Label("Сторона 2 (b):");
            TextField sideBField = new TextField();
            Button calculateButton = new Button("Вычислить");
            calculateButton.setOnAction(ev -> {
                try {
                    double sideA = Double.parseDouble(sideAField.getText());
                    double sideB = Double.parseDouble(sideBField.getText());
                    double perimeter = 2 * (sideA + sideB);
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Периметр: " + perimeter));
                } catch (NumberFormatException ex) {
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Ошибка: неверный ввод"));
                }
            });

            inputBox.getChildren().addAll(sideALabel, sideAField, sideBLabel, sideBField, calculateButton);
        });

        byDiagonalsButton.setOnAction(e -> {
            inputBox.getChildren().clear();
            resultBox.getChildren().clear();

            primaryStage.setWidth(800);
            primaryStage.setHeight(650);

            Label sideALabel = new Label("Сторона a:");
            TextField sideAField = new TextField();
            Label diagonal1Label = new Label("Диагональ 1 (d1):");
            TextField diagonal1Field = new TextField();
            Label diagonal2Label = new Label("Диагональ 2 (d2):");
            TextField diagonal2Field = new TextField();
            Button calculateButton = new Button("Вычислить");
            calculateButton.setOnAction(ev -> {
                try {
                    double sideA = Double.parseDouble(sideAField.getText());
                    double D = Double.parseDouble(diagonal1Field.getText());
                    double d = Double.parseDouble(diagonal2Field.getText());
                    double perimeter = 2 * sideA + Math.sqrt(2 * Math.pow(D, 2) + 2 * Math.pow(d, 2) - 4 * Math.pow(sideA, 2));
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Периметр: " + perimeter));
                } catch (NumberFormatException ex) {
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Ошибка: неверный ввод"));
                }
            });

            inputBox.getChildren().addAll(sideALabel, sideAField, diagonal1Label, diagonal1Field, diagonal2Label, diagonal2Field, calculateButton);
        });

        bySideHeightAngleButton.setOnAction(e -> {
            inputBox.getChildren().clear();
            resultBox.getChildren().clear();
            primaryStage.setWidth(800);
            primaryStage.setHeight(650);

            Label sideALabel = new Label("Сторона (a):");
            TextField sideAField = new TextField();
            Label heightLabel = new Label("Высота (h):");
            TextField heightField = new TextField();
            Label angleLabel = new Label("Угол (α в градусах):");
            TextField angleField = new TextField();
            Button calculateButton = new Button("Вычислить");
            calculateButton.setOnAction(ev -> {
                try {
                    double sideA = Double.parseDouble(sideAField.getText());
                    double height = Double.parseDouble(heightField.getText());
                    double angleInDegrees = Double.parseDouble(angleField.getText());
                    double sinAngle = Math.sin(Math.toRadians(angleInDegrees));
                    double perimeter = 2 * (sideA + height / sinAngle);
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Периметр: " + perimeter));
                } catch (NumberFormatException ex) {
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Ошибка: неверный ввод"));
                }
            });

            inputBox.getChildren().addAll(sideALabel, sideAField, heightLabel, heightField, angleLabel, angleField, calculateButton);
        });

        formBox.getChildren().addAll(methodBox, inputBox);
    }

    // Периметр трапеции
    private void showTrapezoidForm() {
        formBox.getChildren().clear();
        resultBox.getChildren().clear();
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        Label base1Label = new Label("Основание 1 (a):");
        TextField base1Field = new TextField();
        Label base2Label = new Label("Основание 2 (b):");
        TextField base2Field = new TextField();
        Label side1Label = new Label("Сторона 1 (c):");
        TextField side1Field = new TextField();
        Label side2Label = new Label("Сторона 2 (d):");
        TextField side2Field = new TextField();
        Button calculateButton = new Button("Вычислить");
        calculateButton.setOnAction(e -> {
            try {
                double base1 = Double.parseDouble(base1Field.getText());
                double base2 = Double.parseDouble(base2Field.getText());
                double side1 = Double.parseDouble(side1Field.getText());
                double side2 = Double.parseDouble(side2Field.getText());
                double perimeter = base1 + base2 + side1 + side2;
                resultBox.getChildren().clear();
                resultBox.getChildren().add(new Label("Периметр: " + perimeter));
            } catch (NumberFormatException ex) {
                resultBox.getChildren().clear();
                resultBox.getChildren().add(new Label("Ошибка: неверный ввод"));
            }
        });

        formBox.getChildren().addAll(base1Label, base1Field, base2Label, base2Field, side1Label, side1Field, side2Label, side2Field, calculateButton);
    }

    // Периметр ромба
    private void showRhombusForm() {
        formBox.getChildren().clear();
        resultBox.getChildren().clear();
        primaryStage.setWidth(800);
        primaryStage.setHeight(400);

        Label sideLabel = new Label("Сторона (любая):");
        TextField sideField = new TextField();
        Button calculateButton = new Button("Вычислить");
        calculateButton.setOnAction(e -> {
            try {
                double side = Double.parseDouble(sideField.getText());
                double perimeter = 4 * side;
                resultBox.getChildren().clear();
                resultBox.getChildren().add(new Label("Периметр: " + perimeter));
            } catch (NumberFormatException ex) {
                resultBox.getChildren().clear();
                resultBox.getChildren().add(new Label("Ошибка: неверный ввод"));
            }
        });

        formBox.getChildren().addAll(sideLabel, sideField, calculateButton);
    }

    // Периметр окружности
    private void showCircleForm() {
        formBox.getChildren().clear();
        resultBox.getChildren().clear();
        primaryStage.setWidth(800);
        primaryStage.setHeight(400);
        Label radiusLabel = new Label("Радиус (r):");
        TextField radiusField = new TextField();
        Button calculateButton = new Button("Вычислить");
        calculateButton.setOnAction(e -> {
            try {
                double radius = Double.parseDouble(radiusField.getText());
                double perimeter = 2 * Math.PI * radius;
                resultBox.getChildren().clear();
                resultBox.getChildren().add(new Label("Периметр: " + perimeter));
            } catch (NumberFormatException ex) {
                resultBox.getChildren().clear();
                resultBox.getChildren().add(new Label("Ошибка: введите числа!"));
            }
        });

        formBox.getChildren().addAll(radiusLabel, radiusField, calculateButton);
    }

    // Площади
    private void showAreaForm() {
        formBox.getChildren().clear();
        resultBox.getChildren().clear();
        figureButtonBox.setVisible(false);
        figureButtonBox.setManaged(false);

        Button triangleButton = new Button("Треугольник");
        Button rectangleButton = new Button("Прямоугольник");
        Button parallelogramButton = new Button("Параллелограмм");
        Button rhombusButton = new Button("Ромб");
        Button trapezoidButton = new Button("Трапеция");
        Button circleButton = new Button("Круг");
        triangleButton.setStyle("-fx-border-color: transparent #E0E0E0 transparent transparent; -fx-border-width: 2px; -fx-border-radius: 8 0 0 8; -fx-background-radius: 8 0 0 8; -fx-background-color: #F5F5F5; -fx-padding: 10;");
        rectangleButton.setStyle("-fx-border-color: transparent #E0E0E0 transparent transparent; -fx-border-width: 2px; -fx-border-radius: 0 0 0 0; -fx-background-radius: 0 0 0 0; -fx-background-color: #F5F5F5; -fx-padding: 10;");
        parallelogramButton.setStyle("-fx-border-color: transparent #E0E0E0 transparent transparent; -fx-border-width: 2px; -fx-border-radius: 0 0 0 0; -fx-background-radius: 0 0 0 0; -fx-background-color: #F5F5F5; -fx-padding: 10;");
        rhombusButton.setStyle("-fx-border-color: transparent #E0E0E0 transparent transparent; -fx-border-width: 2px; -fx-border-radius: 0 0 0 0; -fx-background-radius: 0 0 0 0; -fx-background-color: #F5F5F5; -fx-padding: 10;");
        trapezoidButton.setStyle("-fx-border-color: transparent #E0E0E0 transparent transparent; -fx-border-width: 2px; -fx-border-radius: 0 0 0 0; -fx-background-radius: 0 0 0 0; -fx-background-color: #F5F5F5; -fx-padding: 10;");
        circleButton.setStyle("-fx-border-color: transparent; -fx-border-width: 1px; -fx-border-radius: 0 8 8 0; -fx-background-radius: 0 8 8 0; -fx-background-color: #F5F5F5; -fx-padding: 10;");

        HBox shapeBox = new HBox(triangleButton, rectangleButton, parallelogramButton, rhombusButton, trapezoidButton, circleButton);
        shapeBox.setSpacing(-1);
        shapeBox.setAlignment(Pos.CENTER);
        shapeBox.setPadding(new Insets(10));
        shapeBox.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 8px; -fx-background-color: #FFFFFF;");

        VBox inputBox = new VBox(10);

        // Площадь треугольника
        triangleButton.setOnAction(e -> {
            inputBox.getChildren().clear();
            resultBox.getChildren().clear();
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);

            Label baseLabel = new Label("Основание (a):");
            TextField baseField = new TextField();
            Label heightLabel = new Label("Высота (h):");
            TextField heightField = new TextField();
            Button calculateButton = new Button("Вычислить");
            calculateButton.setOnAction(ev -> {
                try {
                    double base = Double.parseDouble(baseField.getText());
                    double height = Double.parseDouble(heightField.getText());
                    double area = 0.5 * base * height;
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Площадь: " + area));
                } catch (NumberFormatException ex) {
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Ошибка: неверный ввод"));
                }
            });

            inputBox.getChildren().addAll(baseLabel, baseField, heightLabel, heightField, calculateButton);
        });

        // Площадь прямоугольника
        rectangleButton.setOnAction(e -> {
            inputBox.getChildren().clear();
            resultBox.getChildren().clear();
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);

            Label lengthLabel = new Label("Длина (a):");
            TextField lengthField = new TextField();
            Label widthLabel = new Label("Ширина (b):");
            TextField widthField = new TextField();
            Button calculateButton = new Button("Вычислить");
            calculateButton.setOnAction(ev -> {
                try {
                    double length = Double.parseDouble(lengthField.getText());
                    double width = Double.parseDouble(widthField.getText());
                    double area = length * width;
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Площадь: " + area));
                } catch (NumberFormatException ex) {
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Ошибка: неверный ввод"));
                }
            });

            inputBox.getChildren().addAll(lengthLabel, lengthField, widthLabel, widthField, calculateButton);
        });

        // Площадь параллелограмма
        parallelogramButton.setOnAction(e -> {
            inputBox.getChildren().clear();
            resultBox.getChildren().clear();
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);

            Label baseLabel = new Label("Основание (b):");
            TextField baseField = new TextField();
            Label heightLabel = new Label("Высота (h):");
            TextField heightField = new TextField();
            Button calculateButton = new Button("Вычислить");
            calculateButton.setOnAction(ev -> {
                try {
                    double base = Double.parseDouble(baseField.getText());
                    double height = Double.parseDouble(heightField.getText());
                    double area = base * height;
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Площадь: " + area));
                } catch (NumberFormatException ex) {
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Ошибка: неверный ввод"));
                }
            });

            inputBox.getChildren().addAll(baseLabel, baseField, heightLabel, heightField, calculateButton);
        });

        // Площадь ромба
        rhombusButton.setOnAction(e -> {
            inputBox.getChildren().clear();
            resultBox.getChildren().clear();
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);

            Label diagonal1Label = new Label("Диагональ 1 (d1):");
            TextField diagonal1Field = new TextField();
            Label diagonal2Label = new Label("Диагональ 2 (d2):");
            TextField diagonal2Field = new TextField();
            Button calculateButton = new Button("Вычислить");
            calculateButton.setOnAction(ev -> {
                try {
                    double d1 = Double.parseDouble(diagonal1Field.getText());
                    double d2 = Double.parseDouble(diagonal2Field.getText());
                    double area = 0.5 * d1 * d2;
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Площадь: " + area));
                } catch (NumberFormatException ex) {
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Ошибка: неверный ввод"));
                }
            });

            inputBox.getChildren().addAll(diagonal1Label, diagonal1Field, diagonal2Label, diagonal2Field, calculateButton);
        });

        // Площадь трапеции
        trapezoidButton.setOnAction(e -> {
            inputBox.getChildren().clear();
            resultBox.getChildren().clear();
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);

            Label base1Label = new Label("Основание 1 (b1):");
            TextField base1Field = new TextField();
            Label base2Label = new Label("Основание 2 (b2):");
            TextField base2Field = new TextField();
            Label heightLabel = new Label("Высота (h):");
            TextField heightField = new TextField();
            Button calculateButton = new Button("Вычислить");
            calculateButton.setOnAction(ev -> {
                try {
                    double b1 = Double.parseDouble(base1Field.getText());
                    double b2 = Double.parseDouble(base2Field.getText());
                    double height = Double.parseDouble(heightField.getText());
                    double area = 0.5 * (b1 + b2) * height;
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Площадь: " + area));
                } catch (NumberFormatException ex) {
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Ошибка: неверный ввод"));
                }
            });

            inputBox.getChildren().addAll(base1Label, base1Field, base2Label, base2Field, heightLabel, heightField, calculateButton);
        });

        // Площадь круга
        circleButton.setOnAction(e -> {
            inputBox.getChildren().clear();
            resultBox.getChildren().clear();
            primaryStage.setWidth(800);
            primaryStage.setHeight(400);

            Label radiusLabel = new Label("Радиус (r):");
            TextField radiusField = new TextField();
            Button calculateButton = new Button("Вычислить");
            calculateButton.setOnAction(ev -> {
                try {
                    double radius = Double.parseDouble(radiusField.getText());
                    double area = Math.PI * radius * radius;
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Площадь: " + area));
                } catch (NumberFormatException ex) {
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Ошибка: неверный ввод"));
                }
            });

            inputBox.getChildren().addAll(radiusLabel, radiusField, calculateButton);
        });

        formBox.getChildren().addAll(shapeBox, inputBox);
    }

    // Объём
    private void showVolumeForm() {
        formBox.getChildren().clear();
        resultBox.getChildren().clear();
        figureButtonBox.setVisible(false);
        figureButtonBox.setManaged(false);

        Button parallelepipedButton = new Button("Параллелепипед");
        Button rectangularButton = new Button("Прямоугольный параллелепипед");
        Button pyramidButton = new Button("Пирамида");
        Button tetrahedronButton = new Button("Тетраэдр");
        Button cylinderButton = new Button("Цилиндр");
        Button coneButton = new Button("Конус");
        Button sphereButton = new Button("Сфера");
        parallelepipedButton.setStyle("-fx-border-color: transparent #E0E0E0 transparent transparent; -fx-border-width: 2px; -fx-border-radius: 8 0 0 8; -fx-background-radius: 8 0 0 8; -fx-background-color: #F5F5F5;");
        rectangularButton.setStyle("-fx-border-color: transparent #E0E0E0 transparent transparent; -fx-border-width: 2px; -fx-border-radius: 0 0 0 0; -fx-background-radius: 0 0 0 0; -fx-background-color: #F5F5F5;");
        pyramidButton.setStyle("-fx-border-color: transparent #E0E0E0 transparent transparent; -fx-border-width: 2px; -fx-border-radius: 0 0 0 0; -fx-background-radius: 0 0 0 0; -fx-background-color: #F5F5F5;");
        tetrahedronButton.setStyle("-fx-border-color: transparent #E0E0E0 transparent transparent; -fx-border-width: 2px; -fx-border-radius: 0 0 0 0; -fx-background-radius: 0 0 0 0; -fx-background-color: #F5F5F5;");
        cylinderButton.setStyle("-fx-border-color: transparent #E0E0E0 transparent transparent; -fx-border-width: 2px; -fx-border-radius: 0 0 0 0; -fx-background-radius: 0 0 0 0; -fx-background-color: #F5F5F5;");
        coneButton.setStyle("-fx-border-color: transparent #E0E0E0 transparent transparent; -fx-border-width: 2px; -fx-border-radius: 0 0 0 0; -fx-background-radius: 0 0 0 0; -fx-background-color: #F5F5F5;");
        sphereButton.setStyle("-fx-border-color: transparent; -fx-border-width: 2px; -fx-border-radius: 0 8 8 0; -fx-background-radius: 0 8 8 0; -fx-background-color: #F5F5F5;");

        HBox shapeBox = new HBox(parallelepipedButton, rectangularButton, pyramidButton, tetrahedronButton, cylinderButton, coneButton, sphereButton);
        shapeBox.setSpacing(-1);
        shapeBox.setAlignment(Pos.CENTER);
        shapeBox.setPadding(new Insets(10));
        shapeBox.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5px; -fx-background-color: #ffffff;");

        VBox inputBox = new VBox(10);

        // Объём параллелепипеда
        parallelepipedButton.setOnAction(e -> {
            inputBox.getChildren().clear();
            resultBox.getChildren().clear();
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);

            Label baseAreaLabel = new Label("Площадь основания (S):");
            TextField baseAreaField = new TextField();
            Label heightLabel = new Label("Высота (h):");
            TextField heightField = new TextField();
            Button calculateButton = new Button("Вычислить");
            calculateButton.setOnAction(ev -> {
                try {
                    double baseArea = Double.parseDouble(baseAreaField.getText());
                    double height = Double.parseDouble(heightField.getText());
                    double volume = baseArea * height;
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Объём: " + volume));
                } catch (NumberFormatException ex) {
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Ошибка: введите числа!"));
                }
            });

            inputBox.getChildren().addAll(baseAreaLabel, baseAreaField, heightLabel, heightField, calculateButton);
        });

        // Объём прямоугольного параллелепипеда
        rectangularButton.setOnAction(e -> {
            inputBox.getChildren().clear();
            resultBox.getChildren().clear();
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);

            Label lengthLabel = new Label("Длина (l):");
            TextField lengthField = new TextField();
            Label widthLabel = new Label("Ширина (w):");
            TextField widthField = new TextField();
            Label heightLabel = new Label("Высота (h):");
            TextField heightField = new TextField();
            Button calculateButton = new Button("Вычислить");
            calculateButton.setOnAction(ev -> {
                try {
                    double length = Double.parseDouble(lengthField.getText());
                    double width = Double.parseDouble(widthField.getText());
                    double height = Double.parseDouble(heightField.getText());
                    double volume = length * width * height;
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Объём: " + volume));
                } catch (NumberFormatException ex) {
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Ошибка: введите числа!"));
                }
            });

            inputBox.getChildren().addAll(lengthLabel, lengthField, widthLabel, widthField, heightLabel, heightField, calculateButton);
        });

        // Объём призмы
        pyramidButton.setOnAction(e -> {
            inputBox.getChildren().clear();
            resultBox.getChildren().clear();
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);

            Label baseAreaLabel = new Label("Площадь основания (S):");
            TextField baseAreaField = new TextField();
            Label heightLabel = new Label("Высота (h):");
            TextField heightField = new TextField();
            Button calculateButton = new Button("Вычислить");
            calculateButton.setOnAction(ev -> {
                try {
                    double baseArea = Double.parseDouble(baseAreaField.getText());
                    double height = Double.parseDouble(heightField.getText());
                    double volume = (1.0 / 3) * baseArea * height;
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Объём: " + volume));
                } catch (NumberFormatException ex) {
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Ошибка: введите числа!"));
                }
            });

            inputBox.getChildren().addAll(baseAreaLabel, baseAreaField, heightLabel, heightField, calculateButton);
        });

        // Объём тетраэдра
        tetrahedronButton.setOnAction(e -> {
            inputBox.getChildren().clear();
            resultBox.getChildren().clear();
            primaryStage.setWidth(800);
            primaryStage.setHeight(500);

            Label edgeLabel = new Label("Ребро (a):");
            TextField edgeField = new TextField();
            Button calculateButton = new Button("Вычислить");
            calculateButton.setOnAction(ev -> {
                try {
                    double edge = Double.parseDouble(edgeField.getText());
                    double volume = (Math.pow(edge, 3) / (6 * Math.sqrt(2)));
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Объём: " + volume));
                } catch (NumberFormatException ex) {
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Ошибка: неверный ввод"));
                }
            });

            inputBox.getChildren().addAll(edgeLabel, edgeField, calculateButton);
        });

        // Объём цилиндра
        cylinderButton.setOnAction(e -> {
            inputBox.getChildren().clear();
            resultBox.getChildren().clear();
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);

            Label radiusLabel = new Label("Радиус основания (r):");
            TextField radiusField = new TextField();
            Label heightLabel = new Label("Высота (h):");
            TextField heightField = new TextField();
            Button calculateButton = new Button("Вычислить");
            calculateButton.setOnAction(ev -> {
                try {
                    double radius = Double.parseDouble(radiusField.getText());
                    double height = Double.parseDouble(heightField.getText());
                    double volume = Math.PI * Math.pow(radius, 2) * height;
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Объём: " + volume));
                } catch (NumberFormatException ex) {
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Ошибка: введите числа!"));
                }
            });

            inputBox.getChildren().addAll(radiusLabel, radiusField, heightLabel, heightField, calculateButton);
        });

        // Объём конуса
        coneButton.setOnAction(e -> {
            inputBox.getChildren().clear();
            resultBox.getChildren().clear();
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);

            Label radiusLabel = new Label("Радиус основания (r):");
            TextField radiusField = new TextField();
            Label heightLabel = new Label("Высота (h):");
            TextField heightField = new TextField();
            Button calculateButton = new Button("Вычислить");
            calculateButton.setOnAction(ev -> {
                try {
                    double radius = Double.parseDouble(radiusField.getText());
                    double height = Double.parseDouble(heightField.getText());
                    double volume = (1.0 / 3) * Math.PI * Math.pow(radius, 2) * height;
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Объём: " + volume));
                } catch (NumberFormatException ex) {
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Ошибка: введите числа!"));
                }
            });

            inputBox.getChildren().addAll(radiusLabel, radiusField, heightLabel, heightField, calculateButton);
        });

        // Объём сферы
        sphereButton.setOnAction(e -> {
            inputBox.getChildren().clear();
            resultBox.getChildren().clear();
            primaryStage.setWidth(800);
            primaryStage.setHeight(500);

            Label radiusLabel = new Label("Радиус (r):");
            TextField radiusField = new TextField();
            Button calculateButton = new Button("Вычислить");
            calculateButton.setOnAction(ev -> {
                try {
                    double radius = Double.parseDouble(radiusField.getText());
                    double volume = (4.0 / 3) * Math.PI * Math.pow(radius, 3);
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Объём: " + volume));
                } catch (NumberFormatException ex) {
                    resultBox.getChildren().clear();
                    resultBox.getChildren().add(new Label("Ошибка: неверный ввод"));
                }
            });

            inputBox.getChildren().addAll(radiusLabel, radiusField, calculateButton);
        });

        VBox volumeBox = new VBox(10, shapeBox, inputBox, resultBox);
        formBox.getChildren().add(volumeBox);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
