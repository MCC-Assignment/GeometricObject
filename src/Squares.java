// File name: Square.java
// Written by: Dan Choy
// Description: This Program is creating Square class which is a subclass of GeometricObject
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
// Declare Squares class and make it to be subclass of GeometricObject
public class Squares extends GeometricObject
{
    private double side;            // Declare instance variable

    // *****************************************
    // Declare empty constructor with default value
    public Squares()
    {
        this(1.0);
    }
    // *****************************************
    // Declare constructor with variable
    public Squares(double side)
    {
        this.side = side;
    }

    // *****************************************
    // Declare constructor with more variable
    public Squares(double side, String color, boolean isFilled)
    {
        super(color, isFilled);
        this.side = side;
    }


    // *****************************************
    // Declare getter and setter method to gather value
    public double getSide()
    {
        return side;
    }

    public void setSide(double side)
    {
        this.side = side;
    }


    // *****************************************
    // Declare getter to return specific data when method is called
    @Override
    public String getObjName()
    {
        return "Square";
    }

    @Override
    public double getArea()
    {
        return side * side;
    }

    @Override
    public double getPerimeter()
    {
        return 4 * side;
    }
    // *****************************************



    @Override
    // *****************************************
    // Declare method to return value when method is called
    public String howToDraw()
    {
        DecimalFormat df = new DecimalFormat(".00");

        return " [" + getObjName() + "] side = " + side + "\n"
                + " " + getObjName() + super.toString() + "\n" + " " + getObjName() + "'s area is "
                + df.format(getArea()) + " and the perimeter is " + df.format(getPerimeter())+ "\n";
    }
    // *****************************************

}