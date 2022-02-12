// File name: Rectangles.java
// Written by: Dan Choy
// Description: This Program is creating Rectangles class which is a subclass of GeometricObject
// in this class will be contain getter and setter method to receive data and send it back to
// MyShapesTest after perform calculation
// Challenges: To figure out how to declare howToDraw method with two decimal place
// Time Spent: About 0.5 hours

//            Revision History
// Date:         By:      Action:
// ---------------------------------------------------
// 02/21/2021   (DC)      Create abstract class
//
//
// import DecimalFormat to apply value in howToDraw method
import java.text.DecimalFormat;

// *****************************************
// Declare Rectangle class and make it to be subclass of GeometricObject
public class Rectangles extends GeometricObject
{
    private double width;             // Declare instance variable
    private double height;

    // *****************************************
    // Declare empty constructor with default value
    public Rectangles()
    {
        this(1.0,1.0);
    }

    // *****************************************
    // Declare constructor with variable
    public Rectangles(double width, double height) {
        this.width = width;
        this.height = height;
    }

    // *****************************************
    // Declare constructor with more variable
    public Rectangles(double width, double height, String color, boolean isFilled) {
        super(color, isFilled);
        this.width = width;
        this.height = height;
    }



    // *****************************************
    // Declare getter and setter method to gather value
    public double getWidth()
    {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    // *****************************************
    // Declare getter to return specific data when method is called
    @Override
    public String getObjName()
    {
        return "Rectangle";
    }

    @Override
    public double getArea()
    {
        return width * height;
    }

    @Override
    public double getPerimeter()
    {
        return 2 * (width + height);
    }
    // *****************************************



    @Override
    // *****************************************
    // Declare method to return value when method is called
    public String howToDraw()
    {
        DecimalFormat df = new DecimalFormat(".00");
        return " [" + getObjName() + "] width = " + width + " height = "
                + height + "\n" + " " + getObjName() + " " + super.toString() + "\n"
                + " " + getObjName() + "'s area is " + df.format(getArea())
                + " and the perimeter is " + df.format(getPerimeter())+ "\n";
    }
    // *****************************************
}