/**
 *   File name: Painter.java
 *   Written by: Dan Choy
 *   Description: This program is going to create a GUI for user to pick a shape and color from top menu or left side
 *   options and use a mouse their mouse to drawing a shapes in the drawing area
 *
 *
 *
 *   Time Spent: About 16 hours
 *
 *           Revision History
 *   Date:         By:      Action:
 *   ---------------------------------------------------
 *   04/24/2021   (DC)      Create GUI and define method to call assignment 1 calculate
 *
 */

//import difference function from javafx and java library
import java.util.Stack;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.*;

//Define GUI as a class end extend from Application class
public class Painter extends Application {

    // Too define different variable and method for our GUI
    private Scene scene;
    private ComboBox<String> cb1 = new ComboBox<>();
    private CheckBox chk1 = new CheckBox("Filled");
    private HBox hb1 = new HBox(15);
    private HBox hb2 = new HBox();
    private TitledPane tp1 = new TitledPane();
    private TitledPane tp2 = new TitledPane();
    private Button btn1 = new Button("Color Picker");
    private Button btn2 = new Button("Undo");
    private Button btn3 = new Button("Clear");
    private Button btn4 = new Button("Exit");
    private TextArea history = new TextArea("History:");
    final ColorPicker colorPicker = new ColorPicker(Color.BLACK);
    private Text colorText = new Text("Color Picker:");
    private StackPane sp = new StackPane();
    private RadioButton rbBlack = new RadioButton("Black");
    private RadioButton rbRed = new RadioButton("Red");
    private RadioButton rbGreen = new RadioButton("Green");
    private RadioButton rbBlue = new RadioButton("Blue");
    private RadioButton rbSmall = new RadioButton("Small(2 px)");
    private RadioButton rbMedium = new RadioButton("Medium(4 px)");
    private RadioButton rbLarge = new RadioButton("Large(6 px)");
    private ToggleGroup rbColorGroup = new ToggleGroup();
    private ToggleGroup rbSizeGroup = new ToggleGroup();
    private Pane canvas = new Pane();
    private BorderPane main = new BorderPane();
    private VBox vMenuBar = new VBox(8);
    private VBox vRBC = new VBox(10);
    private VBox vRBS = new VBox(10);
    private VBox vbColor = new VBox();
    private VBox vbCanvas = new VBox();
    private MenuBar menuBar = new MenuBar();
    private Label lb1 = new Label("Mouse move ");

    private CustomerShape currentShape;
    private Stack<CustomerShape> shapes;
    private Stage primaryStage;

    // Define a Star method to for the GUI stage
    public void start(Stage primaryStage) throws Exception {
        comboBoxShape();        // To activate ComboBox Method
        createVBoxItem();       // To activate VBox and Radio Button Method
        createMainBorderPane(); // To activate main BorderPane to host another pane as base Method
        createMenuBar();        // To activate MenuBar function
        rbColor();              // To activate Radio Button for drawing Color
        createTitledPane();     // To activate TitledPane function
        colorPicker();          // To activate ColorPicker function
        createStackPane();      // To activate StackPane function
        rbSize();               // To activate Radio Button for pen size
        Button();               // To activate Button function
        Canvas();               // To activate Canvas function for drawing area

        shapes = new Stack<>();
        scene = new Scene(main, 710, 750);
        primaryStage.setTitle("Painter");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        this.primaryStage = primaryStage;
    }

    //Define a method to create Canvas function for drawing area
    public void Canvas() {
        canvas.setPrefHeight(530);
        canvas.setPrefWidth(510);

        canvas.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_PRESSED,
                new EventHandler<javafx.scene.input.MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        String shape = cb1.getValue();
                        currentShape = new CustomerShape(event.getX(), event.getY(), shape);
                        if (colorPicker.isDisabled()) {
                            if (rbBlue.isSelected()) {
                                currentShape.setColor(Color.BLUE);
                            } else if (rbGreen.isSelected()) {
                                currentShape.setColor(Color.GREEN);
                            } else if (rbRed.isSelected()) {
                                currentShape.setColor(Color.RED);
                            }
                        } else {
                            currentShape.setColor(colorPicker.getValue());
                        }
                        if (rbMedium.isSelected()) {
                            currentShape.setStrokeWidth(4);
                        } else if (rbLarge.isSelected()) {
                            currentShape.setStrokeWidth(6);
                        }
                        if (chk1.isSelected()) {
                            currentShape.setFilled(true);
                        }
                    }
                });
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                canvas.getChildren().remove(currentShape.getShape());
                lb1.setText("Mouse Move: [" + event.getX() + ", " + event.getY() + "]");

                double endX = event.getX();
                double endY = event.getY();
                if (endX < 0) {
                    endX = 0;
                }
                if (endX > 510) {
                    endX = 510;
                }
                if (endY < 0) {
                    endY = 0;
                }
                if (endY > 600) {
                    endY = 600;
                }
                currentShape.setWidth(endX - currentShape.getX());
                currentShape.setHeight(endY - currentShape.getY());
                canvas.getChildren().add(currentShape.getShape());
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                lb1.setText("");
                shapes.add(currentShape);
                historyOperation(currentShape.shapeInfo());
                currentShape = null;
            }
        });
    }

    //Define a method to create Button function for Undo, Clear, and Exit

    public void Button() {
        btn1.setMaxWidth(150);
        btn1.setAlignment(Pos.CENTER_LEFT);
        btn1.setOnAction((event) -> {
            if (colorPicker.isDisabled()) {
                colorPicker.setDisable(false);
                historyOperation("ColorPicker button is clicked and the color picker is disabled");
            } else {
                colorPicker.setDisable(true);
                historyOperation("ColorPicker button is clicked and the color picker is enabled");
            }
        });
        btn2.setMaxWidth(150);
        btn2.setAlignment(Pos.CENTER);
        btn2.setOnAction((event) -> {
            undo();
        });
        btn3.setMaxWidth(150);
        btn3.setAlignment(Pos.CENTER);
        btn3.setOnAction((event) -> {
            clear();
        });
        btn4.setMaxWidth(150);
        btn4.setAlignment(Pos.CENTER);
        btn4.setOnAction((event) -> {
            exit();
        });
    }

    //Define a method to create ColorPicker function for drawing color
    public void colorPicker() {
        colorText.setFill(colorPicker.getValue());
        colorPicker.setPrefWidth(150);
        colorPicker.setDisable(true);
        colorPicker.setOnAction(e -> {
            colorText.setFill(colorPicker.getValue());
            historyOperation("Choose: " + colorPicker.getValue());
        });
        hb1.getChildren().add(colorPicker);
    }

    //Define a method to create RadioButton function for drawing color beside colorpicker
    public void rbColor() {
        rbBlack.setSelected(true);
        rbBlack.setToggleGroup(rbColorGroup);
        rbBlack.setOnAction((event) -> {
            historyOperation("Choose: color black");
        });
        rbRed.setToggleGroup(rbColorGroup);
        rbRed.setOnAction((event) -> {
            historyOperation("Choose: color red");
        });
        rbGreen.setToggleGroup(rbColorGroup);
        rbGreen.setOnAction((event) -> {
            historyOperation("Choose: color green");
        });
        rbBlue.setToggleGroup(rbColorGroup);
        rbBlue.setOnAction((event) -> {
            historyOperation("Choose: color blue");
        });
        vRBC.setPadding(new Insets(10, 10, 10, 10));
        vRBC.getChildren().addAll(rbBlack, rbRed, rbGreen, rbBlue);
    }

    //Define a method to create RadioButton function for drawing pen size
    public void rbSize() {
        rbSmall.setSelected(true);
        rbSmall.setToggleGroup(rbSizeGroup);
        rbSmall.setOnAction((event) -> {
            historyOperation("Change stroke width: small(2 px)");
        });
        rbMedium.setToggleGroup(rbSizeGroup);
        rbMedium.setOnAction((event) -> {
            historyOperation("Change stroke width: medium(4 px)");
        });
        rbLarge.setToggleGroup(rbSizeGroup);
        rbLarge.setOnAction((event) -> {
            historyOperation("Change stroke width: large(6 px)");
        });
        vRBS.setPadding(new Insets(10, 10, 10, 10));
        vRBS.getChildren().addAll(rbSmall, rbMedium, rbLarge);
    }

    //Define a method to create ComboBox function for drawing different shape
    private void comboBoxShape() {
        cb1 = new ComboBox<>();
        cb1.getItems().addAll("Circle", "Rectangle", "Square", "Triangle");
        cb1.setValue("Circle");
        cb1.setPrefWidth(120);
        cb1.setOnAction(e -> {
            switch (cb1.getValue()) {
                case "Circle":
                    historyOperation("Change current shape to Circle");
                    break;
                case "Rectangle":
                    historyOperation("Change current shape to Rectangle");
                    break;
                case "Square":
                    historyOperation("Change current shape to Square");
                    break;
                case "Triangle":
                    historyOperation("Change current shape to Triangle");
                    break;
            }
        });
    }
    //Define a method to create StackPane to hold CheckBox
    public void createStackPane() {
        chk1.setOnAction((event) -> {
            historyOperation("Change filled property to " + chk1.isSelected());
        });
        sp.getChildren().add(chk1);
    }
    //Define a method to create TitledPane to hold RadioButton for color and size
    public void createTitledPane() {
        tp1.setText("Drawing Color ");
        tp1.setPrefSize(150, 20);
        tp1.setContent(vRBC);
        tp1.setContent(vRBC);
        tp2.setText("Pen Size ");
        tp2.setContent(vRBS);
        tp2.setContent(vRBS);
    }
    //Define a method to create MenuBar function for menu items
    public void createMenuBar() {

        Menu fileMenu = new Menu("File");

        Menu shapeMenu = new Menu("Shape");
        MenuItem cMenu = new MenuItem("Circle");
        cMenu.setOnAction((event) -> {
            setShapeType(cMenu.getText());
            historyOperation("Change current shape to Circle");
        });
        MenuItem rMenu = new MenuItem("Rectangle");
        rMenu.setOnAction((event) -> {
            setShapeType(rMenu.getText());
            historyOperation("Change current shape to Rectangle");
        });
        MenuItem sMenu = new MenuItem("Square");
        sMenu.setOnAction((event) -> {
            setShapeType(sMenu.getText());
            historyOperation("Change current shape to Square");
        });
        MenuItem tMenu = new MenuItem("Triangle");
        tMenu.setOnAction((event) -> {
            setShapeType(tMenu.getText());
            historyOperation("Change current shape to Triangle");
        });

        Menu editMenu = new Menu("Edit");
        MenuItem undoMenu = new MenuItem("Undo");
        undoMenu.setOnAction((event) -> {
            undo();
        });
        MenuItem clearMenu = new MenuItem("Clear");
        clearMenu.setOnAction((event) -> {
            clear();
        });
        MenuItem exitMenu = new MenuItem("Exit");
        exitMenu.setOnAction((event) -> {
            exit();
        });

        Menu textMenu = new Menu("Text");
        Menu colorMenu = new Menu("Color");
        RadioMenuItem blackMenu = new RadioMenuItem("Black");
        blackMenu.setSelected(true);
        blackMenu.setOnAction((event) -> {
            String style = history.getStyle() + "-fx-text-fill: black;";
            history.setStyle(style);
            historyOperation("Change font color to black.");
        });
        RadioMenuItem blueMenu = new RadioMenuItem("Blue");
        blueMenu.setOnAction((event) -> {
            String style = history.getStyle() + "-fx-text-fill: blue;";
            history.setStyle(style);
            historyOperation("Change font color to blue.");
        });
        RadioMenuItem redMenu = new RadioMenuItem("Red");
        redMenu.setOnAction((event) -> {
            String style = history.getStyle() + "-fx-text-fill: red;";
            history.setStyle(style);
            historyOperation("Change font color to red.");
        });
        RadioMenuItem greenMenu = new RadioMenuItem("Green");
        greenMenu.setOnAction((event) -> {
            String style = history.getStyle() + "-fx-text-fill: green;";
            history.setStyle(style);
            historyOperation("Change font color to green.");
        });
        ToggleGroup colorToggle = new ToggleGroup();
        colorToggle.getToggles().addAll(blackMenu, blueMenu, redMenu, greenMenu);

        Menu fontMenu = new Menu("Font");
        Menu fontNameMenu = new Menu("Name");
        RadioMenuItem helveticaMenu = new RadioMenuItem("Helvetica");
        helveticaMenu.setOnAction((event) -> {
            history.setFont(Font.font("Helvetica", 16));
            historyOperation("Change font to Helvetica");
        });
        RadioMenuItem sansserifMenu = new RadioMenuItem("Sans-Serif");
        sansserifMenu.setOnAction((event) -> {
            history.setFont(Font.font("Sans-Serif", 16));
            historyOperation("Change font to Sans-Serif");
        });
        RadioMenuItem serifMenu = new RadioMenuItem("Serif");
        serifMenu.setOnAction((event) -> {
            history.setFont(Font.font("Serif", 16));
            historyOperation("Change font to Serif");
        });
        ToggleGroup fontNameToggle = new ToggleGroup();
        fontNameToggle.getToggles().addAll(helveticaMenu, sansserifMenu, serifMenu);

        Menu styleMenu = new Menu("Style");
        CheckMenuItem boldMenu = new CheckMenuItem("Bold");
        CheckMenuItem italicMenu = new CheckMenuItem("Italic");
        boldMenu.setOnAction((event) -> {
            String style = "-fx-font-weight: bold;";
            String operation = "Change font weight to bold";
            if (!boldMenu.isSelected()) {
                style = "-fx-font-weight: normal;";
                operation = "Change font weight to Normal";
            }
            if (italicMenu.isSelected()) {
                style += "-fx-font-style: italic;";
            }
            history.setStyle(history.getStyle() + style);
            historyOperation(operation);
        });
        italicMenu.setOnAction((event) -> {
            String style = "-fx-font-style: italic;";
            String operation = "Change font style to italic";
            if (!italicMenu.isSelected()) {
                style = "-fx-font-style: normal;";
                operation = "Change font style to normal";
            }
            if (boldMenu.isSelected()) {
                style += "-fx-font-weight: bold;";
            }
            history.setStyle(history.getStyle() + style);
            historyOperation(operation);
        });

        menuBar.getMenus().addAll(fileMenu, textMenu);
        fileMenu.getItems().addAll(shapeMenu, editMenu, exitMenu);
        shapeMenu.getItems().addAll(cMenu, rMenu, sMenu, tMenu);
        editMenu.getItems().addAll(undoMenu, clearMenu);
        textMenu.getItems().addAll(colorMenu, fontMenu);
        colorMenu.getItems().addAll(blackMenu, blueMenu, redMenu, greenMenu);
        fontMenu.getItems().addAll(fontNameMenu, styleMenu);
        fontNameMenu.getItems().addAll(helveticaMenu, sansserifMenu, serifMenu);
        styleMenu.getItems().addAll(boldMenu, italicMenu);

    }

    // Define a method to create BorderPane as a main Pane to hold all objects
    public void createMainBorderPane() {
        history.setPrefHeight(100);
        history.setEditable(false);
        vbCanvas.getChildren().add(canvas);
        hb2.getChildren().addAll(vMenuBar, vbCanvas);
        hb2.setMaxWidth(900);
        main.setTop(menuBar);
        main.setBottom(history);
        main.setCenter(hb2);
    }
    //Define a method to create VBox function to hold all items on leftside
    public void createVBoxItem() {
        chk1.setSelected(false);
        vbColor.getChildren().addAll(btn1, hb1);
        lb1.setFont(new Font(12));
        vMenuBar.getChildren().addAll(cb1, tp1, vbColor, sp, tp2, btn2, btn3, btn4, lb1);
        vMenuBar.setPadding(new Insets(10, 10, 10, 10));
        vbCanvas.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;"
                + "-fx-background-color: #ffffcc");
    }
    //Define a method to create Undo method to undo lase drawing in canvas
    public void undo() {
        if (!shapes.isEmpty()) {
            CustomerShape lastShape = shapes.pop();
            canvas.getChildren().remove(lastShape.getShape());
            historyOperation("Undo the operation.");
        }
    }

    //Define a method to create clear function to clear all drawing in canvas
    public void clear() {
        while (!shapes.isEmpty()) {
            CustomerShape lastShape = shapes.pop();
            canvas.getChildren().remove(lastShape.getShape());
        }
        historyOperation("Clear the pane.");
    }

    //Define a method to create exit method to exit the whole program
    public void exit() {
        this.primaryStage.close();
    }

    //Define a method to ShapeType method to set a shaape as a value in ComboBox
    public void setShapeType(String value) {
        cb1.setValue(value);
    }

    //Define a method to create TextArea to display all action in the GUI
    public void historyOperation(String message) {
        history.setText("History:\n" + message);
    }

    //Define a class to hold the drawing shape method on canvas
    public class CustomerShape {
        private double locationX;
        private double locationY;
        private double width;
        private double height;
        private Color color;
        private String shapeType;
        private Shape shape;
        private boolean isFilled;

        //Define a constructor to defind x y location for drawing in canvas
        public CustomerShape(double locationX, double locationY, String shapeType) {
            color = Color.BLACK;
            this.locationX = locationX;
            this.locationY = locationY;
            this.shapeType = shapeType;
            if (shapeType.equals("Circle")) {
                shape = new Ellipse();
            } else if (shapeType.equals("Rectangle") || shapeType.equals("Square")) {
                shape = new Rectangle();
            } else if (shapeType.equals("Triangle")) {
                shape = new Polygon();
            }
            shape.setFill(null);
            shape.setStrokeWidth(2);
            shape.setStroke(color);
        }

        //define set and get method to return difference value
        public double getX() {
            return locationX;
        }

        public double getY() {
            return locationY;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public void setColor(Color color) {
            this.color = color;
            shape.setStroke(color);
        }

        public void setFilled(boolean isFilled) {
            this.isFilled = isFilled;
            if (isFilled) {
                shape.setFill(color);
            } else {
                shape.setFill(null);
            }
        }

        public void setStrokeWidth(double strokeWidth) {
            shape.setStrokeWidth(strokeWidth);
        }

        //define a getshape method to drawing different shape in canvas
        public Shape getShape() {
            if (shape instanceof Ellipse) {
                double radius = Math.min(Math.abs(width / 2), Math.abs(height / 2));
                ((Ellipse) shape).setRadiusX(radius);
                ((Ellipse) shape).setRadiusY(radius);
                ((Ellipse) shape).setCenterX(locationX + (width >= 0 ? radius : -radius));
                ((Ellipse) shape).setCenterY(locationY + (height >= 0 ? radius : -radius));
            } else if (shape instanceof Rectangle) {
                ((Rectangle) shape).setX(locationX);
                ((Rectangle) shape).setY(locationY);
                if (shapeType.equals("Rectangle")) {
                    if (width >= 0) {
                        ((Rectangle) shape).setWidth(width);
                    } else {
                        ((Rectangle) shape).setX(locationX + width);
                        ((Rectangle) shape).setWidth(-width);
                    }
                    if (height >= 0) {
                        ((Rectangle) shape).setHeight(height);
                    } else {
                        ((Rectangle) shape).setY(locationY + height);
                        ((Rectangle) shape).setHeight(-height);
                    }
                } else {
                    double side = Math.min(Math.abs(width), Math.abs(height));
                    ((Rectangle) shape).setWidth(side);
                    ((Rectangle) shape).setHeight(side);
                    if (width < 0) {
                        ((Rectangle) shape).setX(locationX - side);
                    }
                    if (height < 0) {
                        ((Rectangle) shape).setY(locationY - side);
                    }
                }
            } else {
                ((Polygon) shape).getPoints().clear();
                ((Polygon) shape).getPoints().addAll(new Double[] {locationX, locationY + height, locationX + width, locationY + height, locationX + width / 2, locationY});
            }
            return shape;
        }

        //Define a shapeinfor method to gather shape information and calculate shape information from Assignment 1
        public String shapeInfo() {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("(x1, y1) = (%f, %f)\n(x2, y2) = (%f, %f)\n", locationX, locationY, locationX + width, locationY + height));
            GeometricObject object = null;
            if (shape instanceof Ellipse) {
                double radius = Math.min(Math.abs(width / 2), Math.abs(height / 2));
                object = new Circle(radius);
            } else if (shape instanceof Rectangle) {
                if (shapeType.equals("Rectangle")) {
                    object = new Rectangles(Math.abs(width), Math.abs(height));
                } else {
                    double side = Math.min(Math.abs(width), Math.abs(height));
                    object = new Squares(side);
                }
            } else {
                object = new Triangle(Math.abs(width), Math.sqrt(width * width + height * height / 4), Math.sqrt(width * width + height * height / 4));
            }
            object.setColor(color.toString());
            object.setisFilled(isFilled);
            builder.append(object.howToDraw());
            return builder.toString();
        }
    }

    //Define a main method to launch the GUI
    public static void main(String[] args)
    {
        launch(args);
    }
}
