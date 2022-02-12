// File name: Circle.java
// Written by: Dan Choy
// Description: This Program is creating Circle class which is a subclass of GeometricObject
// in this class will be contain getter and setter method to receive data and send it back to
// MyShapesTest after perform calculation
// Challenges: To figure out how to declare howToDraw method with two decimal place

// Time Spent: About 1.5 hours

//            Revision History
// Date:         By:      Action:
// ---------------------------------------------------
// 02/21/2021   (DC)      Create abstract class
//
//
// import DecimalFormat to apply value in howToDraw method

import javafx.scene.control.RadioButton;

import java.text.DecimalFormat;

// *****************************************
// Decalre Circle class and make it to be subclass of GeometricObject
public class Circle extends GeometricObject
{
    private double radius;                  // Declare instance variable
// *****************************************
// Declare empty constructor with default value
    public Circle(RadioButton rb)
    {
        this(1.0);
    }

    // *****************************************
    // Declare constructor with variable
    public Circle(double radius)
    {
        //super();
        this.radius = radius;
    }
    // *****************************************
    // Declare constructor with more variable
    public Circle(double radius, String color, boolean isFilled)
    {
        super(color, isFilled);
        this.radius = radius;
    }
    // *****************************************
    // Declare getter and setter method to gather value
    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }
    // *****************************************
    // Declare getter to return specific data when method is called
    public String getObjName()
    {
        return "Circle";
    }

    @Override
    public double getArea()
    {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter()
    {
        return 2 * Math.PI * radius;
    }
    // *****************************************
    // Declare boolean method to compare two object
    public boolean equals(GeometricObject o)
    {
        if (this.getRadius() == ((Circle) o).getRadius())
            return true;
        else
            return false;
    }
    // *****************************************



    @Override
    // *****************************************
    // Declare method to return value when method is called
   public String howToDraw()
    {
        DecimalFormat df = new DecimalFormat(".##");
        return " ["+ getObjName() + "] radius = " + radius + "\n"
                +  " " + getObjName() + super.toString() + "\n" + " " +  getObjName()
                + "'s area is " + df.format(getArea()) + " and the perimeter is "
                + df.format(getPerimeter())+ "\n" ;
    }
    // *****************************************
}