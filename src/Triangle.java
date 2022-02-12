// File name: Triangle.java
// Written by: Dan Choy
// Description: This Program is creating Triangle class which is a subclass of GeometricObject
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
// Declare Triangle class and make it to be subclass of GeometricObject
public class Triangle extends GeometricObject
{
    private double side1;                   // Declare instance variable
    private double side2;
    private double side3;

    // *****************************************
    // Declare empty constructor with default value
    public Triangle()
    {
        this(1.0,1.0,1.0);
    }
    // *****************************************
    // Declare constructor with variable
    public Triangle(double side1, double side2, double side3)
    {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }
    // *****************************************
    // Declare constructor with more variable
    public Triangle(double side1, double side2, double side3, String color, boolean isFilled)
    {
        super(color, isFilled);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    // *****************************************
    // Declare getter and setter method to gather value
    public double getSide1()
    {
        return side1;
    }

    public void setSide1(double side1)
    {
        this.side1 = side1;
    }

    public double getSide2()
    {
        return side2;
    }

    public void setSide2(double side2)
    {
        this.side2 = side2;
    }

    public double getSide3()
    {
        return side3;
    }

    public void setSide3(double side3)
    {
        this.side3 = side3;
    }

    // *****************************************
    // Declare getter to return specific data when method is called
    @Override
    public String getObjName()
    {
        return "Triangle";
    }

    @Override
    public double getArea()
    {
        double p;
        double area;
        p = (side1 + side2 + side3) / 2;
        area = Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
        return area;
    }

    @Override
    public double getPerimeter()
    {
        return (side1 + side2 + side3);
    }

    @Override
    public boolean equals(Object o)
    {
        Triangle t = (Triangle) o;
        if (this.side1 == t.getSide1() && this.side2 == t.getSide2()
                && this.side3 == t.getSide3())
            return true;
        else
            return false;
    }// *****************************************



    @Override
    // *****************************************
    // Declare method to return value when method is called
    public String howToDraw()
    {
        DecimalFormat df = new DecimalFormat(".00");
        return " [" + getObjName() + "] side1 = " + side1 + " side2 = " + side2
                + " side3 = " + side3 + "\n" + " " + getObjName() + super.toString()
                + "\n" + " " + getObjName() + "'s area = " + df.format(getArea())
                + " and the perimeter is = " + df.format(getPerimeter())+ "\n";
    }
    // *****************************************
}