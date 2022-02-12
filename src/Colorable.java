// File name: Colorable.java
// Written by: Dan Choy
// Description: This Program to create abstract class, abstract method, toString method, and getter and setter method for Colorable class
// Challenges: To figure what should contain here for this super class
// Time Spent: About 1 hours

//            Revision History
// Date:         By:      Action:
// ---------------------------------------------------
// 02/21/2021   (DC)      Create abstract class
    // *****************************************
    //Define abstract class
    public abstract class Colorable
{
    // *****************************************
    //define abstract method
    public abstract String howToDraw();
    // *****************************************
    //declare instance variable
    private String color;
    private boolean isFilled;
    // *****************************************
    //Declare empty constructor declare default value for constructor
    public Colorable()
    {
        this.color = "White";
        this.isFilled = false;
    }
    // *****************************************
    // define constructor with variable
    public Colorable(String color, boolean isFilled)
    {
        this.color = color;
        this.isFilled = isFilled;
    }
    // *****************************************

    // *****************************************
    // Create getter and setter method
    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public boolean isisFilled()
    {
        return isFilled;
    }

    public void setisFilled(boolean isFilled)
    {
        this.isFilled = isFilled;
    }
    // *****************************************
    // Define toString method to receive request from GeometricObject class
    @Override
    public String toString()
    {
        return " color: " + " " + color + " " + "and filled:" + " " + isFilled;
    }
    // *****************************************

}