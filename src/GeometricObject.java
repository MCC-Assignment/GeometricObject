// File name: GeometricObject.java
// Written by: Dan Choy
// Description: This Program is creating another abstract class in the second level, which is a subclass of Colorable.
// Also to implements Compareable method to compare area and perimeter and cloneable method, define system date and time,
// abstract method, toString method, and getter and setter method
//
// Challenges: To figure how to declare cloneable method with exception handling
// that and which value should return after.
// Time Spent: About 1.5 hours

//            Revision History
// Date:         By:      Action:
// ---------------------------------------------------
// 02/21/2021   (DC)      Create abstract class
//
//
import java.util.*;
// *****************************************
// TO define abstract class and using extends to defeine as a subclass of Colorable, and implement interface
public abstract class GeometricObject extends Colorable implements Comparable<GeometricObject>, Cloneable
// *****************************************
{

    //Declare instance variable
    // *****************************************
    private Date dateCreated;
    // *****************************************


    // *****************************************
    //Create getter and setter for Date generate
    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }
    // *****************************************


    //Declare empty constructor
    GeometricObject()
    {
        this.dateCreated = new Date();
    }

    // *****************************************
    //Declare constructor and declare values for color and date
    GeometricObject(String color, boolean isFilled)
    {
        super(color, isFilled);
        this.dateCreated = new Date();
    }
    // *****************************************


    // *****************************************
    //Declare abstract method
    public abstract String getObjName();

    public abstract double getArea();

    public abstract double getPerimeter();
    // *****************************************

    // *****************************************
    // Declare Comparable method to compare two object
    @Override
    public int compareTo(GeometricObject o)
    {
        if (this.getArea() < o.getArea())
            return -1;
        else if (this.getArea() > o.getArea())
            return 1;
        else
            return 0;
    }
    // *****************************************


    // *****************************************
    // Declare max method and two objects
    // under IF else control statement to use comparable method to compare two object
    public static GeometricObject max(GeometricObject o1, GeometricObject o2)
    {
        if (o1.compareTo(o2) < 0)
            return o2;
        else if (o1.compareTo(o2) > 0)
            return o1;
        return o1;
    }
    // *****************************************
    // Declare cloneable method and exception handling method with try catch statement
    @Override
    public Object clone()
    {
        try
        {
            return super.clone();
        } catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return new MyShapesTest();
    }
    // *****************************************
    // Define toString method to receive request from MyShapesTest class
    // also send request to Colorable class and receive and pass back to MyShapesTest class
    @Override
    public String toString() {
        return " created on = " + getDateCreated() + "\n" + super.toString();
    }
    // *****************************************

}