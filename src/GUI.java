/**
 *   File name: GUI.java
 *   Written by: Dan Choy
 *   Description: This program is going to create a GUI for user input number
 *   and which difference choice of shape and color to using assignment 1 to calculate the result.
 *
 *
 *   Time Spent: About 16 hours
 *
 *           Revision History
 *   Date:         By:      Action:
 *   ---------------------------------------------------
 *   03/21/2021   (DC)      Create GUI and define method to call assignment 1 for calculation
 *
 */

//import difference function from javafx and java library
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;
import java.text.DecimalFormat;


//Define GUI as a class end extend from Application class
public class GUI extends Application {

    // Too define different variable and method for our GUI
    public Scene scene;
    private ComboBox<String> cb1 = new ComboBox<>();
    final CheckBox chk1 = new CheckBox("Filled");
    final Label lb1 = new Label("    Select a geometric object:                                      ");
    final Label lb2 = new Label("Shape:");
    final Label lb3 = new Label("Information:");
    final Label lb4 = new Label("Area:");
    final Label lb5 = new Label("Perimeter:");
    public Label lb6 = new Label("Width");
    public Label lb7 = new Label("Height");
    public Label lb8 = new Label("Side");
    final HBox hb1 = new HBox(15);
    final HBox hb2 = new HBox();
    final HBox hb3 = new HBox();
    final GridPane gp1 = new GridPane();
    final GridPane gp2 = new GridPane();
    final TitledPane tp1 = new TitledPane();
    final TitledPane tp2 = new TitledPane();
    final Button btn1 = new Button("Calculate");
    final Button btn2 = new Button("Clear");
    final Button btn3 = new Button("Exit");
    final BorderPane bp1 = new BorderPane();
    final BorderPane bp2 = new BorderPane();
    final Slider sl1 = new Slider(0, 30, 15);
    public TextField tf1 = new TextField();
    public TextField tf2 = new TextField();
    public TextField tf3 = new TextField();
    public TextField tf4 = new TextField();
    public TextField tf5 = new TextField();
    public TextField tf6 = new TextField();
    public TextField tf7 = new TextField();
    public Label sCap = new Label("Radius: " + (sl1.getValue()));
    public RadioButton rbBlack = new RadioButton("Black");
    public RadioButton rbRed = new RadioButton("Red");
    public RadioButton rbGreen = new RadioButton("Green");
    public RadioButton rbBlue = new RadioButton("Blue");
    public ToggleGroup group = new ToggleGroup();
    BorderPane main = new BorderPane();
    VBox vb1 = new VBox();
    VBox vb2 = new VBox();



// Define a Star method to for the GUI stage
    public void start(Stage primaryStage) throws Exception
{
        comboBoxShape();            //To activate ComboBox Method
        createGridPane1();          //To activate upper part GridPane Method
        createGridPane2();          //To activate lower part GridPane Method
        createVBoxNRadioBtn();      //To activate VBox and Radio Button Method
        createTitledPane();         //To activate TitledPane Method
        createMainBorderPane();     //To activate main BorderPane to host another pane as base Method
        btnClear();                 //To activate Clear button Method
        btnExit();                  //To activate Exit button Method
        btnCalc();                  //To activate Calculation button Method
    rbColor();



        scene = new Scene(main, 600, 565);
        primaryStage.setTitle("Shape Chooser");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
}

    // Define a method to create BorderPane with other objects
    public void createMainBorderPane()
    {
        main.setTop(vb1);
        main.setBottom(vb2);
        tp1.setText("Input Data: ");
        tp2.setText("Result: ");
        vb1.getChildren().addAll(hb1, tp1);
        vb1.setPadding(new Insets(5, 5, 5, 5));
        vb2.getChildren().addAll(tp2, hb3);
        vb2.setPadding(new Insets(5, 5, 5, 5));
    }

    // Define a method to create ComboBox to let user to choice shape
    private void comboBoxShape()
    {
        cb1 = new ComboBox<>();
        cb1.setPromptText("Pick up a shape");
        cb1.getItems().addAll("Circle", "Rectangle", "Square", "Triangle");
        cb1.setOnAction(e ->
        {
            switch (cb1.getValue())
            {
                case "Circle":
                        circle();
                        break;
                case "Rectangle" :
                        rectangles();
                        break;
                case "Square" :
                        squares();
                        break;
                case "Triangle" :
                        triangle();
                        break;
            }
        });
    }

    // Define a method to create upper GridPane to hosting a label and textfield objects
    public void createGridPane1()
    {
        lb1.setFont(Font.font(15));
        gp1.setPadding(new Insets(10));
        gp1.setVgap(10);
        gp1.setHgap(5);
        GridPane.setConstraints(sl1, 1, 0);
        gp1.getChildren().add(sl1);
        GridPane.setConstraints(sCap, 0, 0);
        gp1.getChildren().add(sCap);
        gp1.add(new Slider(), 1, 1);
        gp1.add(lb6, 0, 1);
        gp1.add(tf1 = new TextField(), 1, 1);
        gp1.add(lb7, 0, 2);
        gp1.add(tf2 = new TextField(), 1, 2);
        gp1.add(lb8, 0, 3);
        gp1.add(tf3 = new TextField(), 1, 3);
        sl1.setDisable(true);
        tf1.setEditable(false);
        tf2.setEditable(false);
        tf3.setEditable(false);

        //Define HBox to hosting a lable and ComboBox
        hb1.getChildren().addAll(lb1, cb1);


        // Define a Slider with range or value and default value
        sl1.setPrefSize(0, 30);
        sl1.setMin(0);
        sl1.setMax(30);
        sl1.setValue(15);
        sl1.valueProperty().addListener((observableValue,number, t1) -> sCap.setText(String.format("Radius: %.1f", t1)));
    }

    // Define a method to create lower GridPane to hosting a label and textfield objects
    public void createGridPane2()
    {
        gp2.setPadding(new Insets(10));
        gp2.setStyle("-fx-background-color: azure");
        gp2.setVgap(10);
        gp2.setHgap(5);
        gp2.add(lb2, 0, 0);
        GridPane.setHalignment(lb2, HPos.RIGHT);
        gp2.add(tf4 = new TextField(), 1, 0);
        gp2.add(lb3, 0, 1);
        GridPane.setHalignment(lb3, HPos.RIGHT);
        gp2.add(tf5 = new TextField(), 1, 1);
        gp2.add(lb4, 0, 2);
        GridPane.setHalignment(lb4, HPos.RIGHT);
        gp2.add(tf6 = new TextField(), 1, 2);
        gp2.add(lb5, 0, 3);
        GridPane.setHalignment(lb5, HPos.RIGHT);
        gp2.add(tf7 = new TextField(), 1, 3);


        //Define a setting for Textfield
        tf1.setAlignment(Pos.CENTER_RIGHT);
        tf2.setAlignment(Pos.CENTER_RIGHT);
        tf3.setAlignment(Pos.CENTER_RIGHT);
        tf4.setEditable(false);
        tf4.setPrefWidth(440);
        tf5.setEditable(false);
        tf6.setEditable(false);
        tf7.setEditable(false);


       //Define a HBox to host 3 button
        hb3.getChildren().addAll(btn1, btn2, btn3);
        hb3.setAlignment(Pos.BOTTOM_CENTER);
    }

    //Define a VBox to host RadioButton
    public void createVBoxNRadioBtn()
    {
        VBox vRB = new VBox(20);
        vRB.setPadding(new Insets(5, 5, 5, 5));
        String css =("-fx-border-color: green; -fx-border-insets: 5; -fx-border-width: 3; -fx-border-style: solid;");
        vRB.setStyle(css);

        //Define radio button
        rbBlack.setSelected(true);
        rbBlack.setToggleGroup(group);
        rbRed.setToggleGroup(group);
        rbGreen.setToggleGroup(group);
        rbBlue.setToggleGroup(group);
        vRB.getChildren().addAll(rbBlack, rbRed, rbGreen, rbBlue);

        //Define a HBox to host the checkbox function
        hb2.getChildren().add(chk1);
        hb2.setAlignment(Pos.TOP_RIGHT);
        hb2.setPadding(new Insets(10));

        //Define a BorderPane to Host the HBox that hosting the checkbox and radio button
        bp1.setTop(hb2);
        bp1.setCenter(gp1);
        bp1.setRight(vRB);
    }

    //Define a TitledPane to host 2 BorderPane for upper part and lower part
    public void createTitledPane()
    {
        tp1.setPadding(new Insets(5));
        tp1.setPrefSize(550, 250);
        tp1.setContent(bp1);



        tp2.setPadding(new Insets(5));
        tp2.setStyle("-fx-background-color: Blue");
        tp2.setPrefSize(550, 200);
        tp2.setCollapsible(false);
        tp2.setContent(bp2);
        bp2.setBottom(gp2);
    }

    //Define a Clear button method to clear all value in GUI when clicked
    public void btnClear()
    {
        btn2.setOnAction(e ->
        {
            cb1.setValue("Circle");
            sl1.setDisable(false);
            sl1.setValue(15);
            tf1.setEditable(false);
            tf2.setEditable(false);
            tf3.setEditable(false);
            rbBlack.setSelected(true);
            clearTF();
        });
    }

    //Define a Exit button method exit program when cicked
    public void btnExit()
    {
        btn3.setOnAction(e ->
                System.exit(0));
    }

    //Define a Calculate button method to handle difference shape of calculation, and error exception handling
    public void btnCalc()
    {
        btn1.setOnAction(e ->
        {
            try
            {
                String cbSelected = cb1.getValue();
                switch (cbSelected)
                {
                    case "Circle" :
                            CircleResult();
                            break;
                    case "Rectangle" :
                            RectangleResult();
                            break;
                    case "Square" :
                            SquareResult();
                            break;
                    case "Triangle" :
                            TriangleResult();
                            break;
                }
            }
            catch (NumberFormatException error)
            {
                Numeric();
            }
            catch (NullPointerException error)
            {
                pickAshape();
            }
        });
    }


    //Define Circle function on which function will available and not available
    public void circle()
    {
        setLBTitle();
        clearTF();
        sl1.setDisable(false);
        sl1.setValue(15);
        tf1.setEditable(false);
        tf2.setEditable(false);
        tf3.setEditable(false);
    }

    //Define Rectangle function on which function will available and not available
    public void rectangles()
    {
        setLBTitle();
        clearTF();
        sl1.setDisable(true);
        tf1.setEditable(true);
        tf2.setEditable(true);
        tf3.setEditable(false);
    }

    //Define Square function on which function will available and not available
    public void squares()
    {
        setLBTitle();
        clearTF();
        sl1.setDisable(true);
        tf1.setEditable(false);
        tf2.setEditable(false);
        tf3.setEditable(true);
    }

    //Define Triangle function on which function will available and not available
    public void triangle()
    {
        sl1.setDisable(true);
        lb6.setText("Side 1");
        lb7.setText("Side 2");
        lb8.setText("Side 3");
        clearTF();
        tf1.setEditable(true);
        tf2.setEditable(true);
        tf3.setEditable(true);
    }

    //Define clear all Textfield value method
    public void clearTF()
    {
        chk1.setSelected(false);
        tf1.setText("");
        tf2.setText("");
        tf3.setText("");
        tf4.setText("");
        tf5.setText("");
        tf6.setText("");
        tf7.setText("");
    }

    //Define Label title method
    public void setLBTitle()
    {
        lb6.setText("Width");
        lb7.setText("Height");
        lb8.setText("Side");
    }

    //Define a method to pass value to Circle class for calculation and display result
    public void CircleResult()
    {
        Circle c = new Circle(sl1.getValue());
        rbColor();

        DecimalFormat df1 = new DecimalFormat(".#");
        DecimalFormat df = new DecimalFormat(".##");
        tf4.setText("[" + c.getObjName() + "] radius = " + df1.format(sl1.getValue()) + "\n");
        tf5.setText(c.getObjName() + " color: " + rbColor() + " and filled: " + checkbox());
        tf6.setText(df.format(c.getArea()));
        tf7.setText(df.format(c.getPerimeter()));
    }

    //Define a method to pass value to Rectangle class for calculation and display result
    public void RectangleResult()
    {
        double width = Double.parseDouble(String.valueOf(tf1.getText()));
        double height = Double.parseDouble(String.valueOf(tf2.getText()));


        Rectangles r = new Rectangles(width, height);

        rbColor();
        DecimalFormat df = new DecimalFormat("0.00");

        tf4.setText("[" + r.getObjName() + "] width = " + width + " height " + height + "\n");
        tf5.setText(r.getObjName() + " color: " + rbColor() + " and filled: " + checkbox());
        tf6.setText(df.format(r.getArea()));
        tf7.setText(df.format(r.getPerimeter()));
    }

    //Define a method to pass value to Square class for calculation and display result
    public void SquareResult()
    {
        double side = Double.parseDouble(String.valueOf(tf3.getText()));
        Squares s = new Squares(side);
        rbColor();

        DecimalFormat df = new DecimalFormat("0.00");
        tf4.setText("[" + s.getObjName() + "] width = " + side + "\n");
        tf5.setText(s.getObjName() + " color: " + rbColor() + " and filled: " + checkbox());
        tf6.setText(df.format(s.getArea()));
        tf7.setText(df.format(s.getPerimeter()));
    }

    //Define a method to pass value to Triangle class for calculation and display result
    public void TriangleResult()
    {
        double side1 = Double.parseDouble(String.valueOf(tf1.getText()));
        double side2 = Double.parseDouble(String.valueOf(tf2.getText()));
        double side3 = Double.parseDouble(String.valueOf(tf3.getText()));
        Triangle t = new Triangle(side1, side2, side3);
        rbColor();

        DecimalFormat df = new DecimalFormat("0.00");
        tf4.setText("[" + t.getObjName() + "] side1 = " + side1 + " side2 = " + side2 + " side3 = " + side3 + "\n");
        tf5.setText(t.getObjName() + " color: " + rbColor() + " and filled: " + checkbox());
        tf6.setText(df.format(t.getArea()));
        tf7.setText(df.format(t.getPerimeter()));
    }

    //Define a checkbox function and validate when checkbox is checked
    public boolean checkbox()
    {
        return chk1.isSelected();
    }

    //Define a method to display color on textfield result when radio button has picked a color
    public String rbColor()
    {
        group.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {});
        RadioButton rb = (RadioButton) group.getSelectedToggle();


        //Define a switch method when color is selected and change the result as the color

        String r = rb.getText();
            switch (r)
            {
                case "Black" :
                    tf5.setStyle("-fx-text-fill: Black");
                    break;

                case "Red" :
                    tf5.setStyle("-fx-text-fill: Red");
                    break;

                case "Green" :
                    tf5.setStyle("-fx-text-fill: Green");
                    break;

                case "Blue" :
                    tf5.setStyle("-fx-text-fill: Blue");
                    break;
            }

        return rb.getText();
    }


    //Define a alert method to handle when textfield input is not a number
    public void Numeric()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Please Enter the numeric number");
        alert.showAndWait();
    }

    //Define a alert method to handle when user didn't pick shape and press calculate button
    public void pickAshape()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Please Select one geometric object");
        alert.showAndWait();
    }

    //Define a main method to launch the GUI
    public static void main(String[] args)
    {
        launch(args);
    }
}



