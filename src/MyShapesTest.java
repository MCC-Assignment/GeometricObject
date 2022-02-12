// File name: MyShapesTest.java
// Written by: Dan Choy
// Description: This Program is creating MyShapesTest class as a driver class create method to send request to GeometricObject class
// In this class will contain arraylist to store value to print out result
// Challenges: To figure out how to declare arraylist
// Time Spent: About 4.5 hours

//            Revision History
// Date:         By:      Action:
// ---------------------------------------------------
// 02/21/2021   (DC)      Create abstract class
//
//
// import package to apply ArrayList method
import java.util.*;

// *****************************************
// Declare Rectangle class and make it to be subclass of GeometricObject
public class MyShapesTest
{
    // *****************************************
    //Declare main method
    // *****************************************
    public static void main(String[] args)
    {
        // *****************************************
        // Declare ArrayList and add object into element
        ArrayList<Circle> cList = new ArrayList<Circle>();
        cList.add(new Circle(15));
        cList.add(new Circle(10.0, "Blue", true));
        cList.add(new Circle(3.5));

        ArrayList<Rectangles> rList = new ArrayList<Rectangles>();
        rList.add(new Rectangles(25, 34));
        rList.add(new Rectangles(12, 20, "YELLOW", true));

        ArrayList<Squares> sList = new ArrayList<Squares>();
        sList.add(new Squares(10));
        sList.add(new Squares(24.2, "RED", true));

        ArrayList<Triangle> tList = new ArrayList<Triangle>();
        tList.add(new Triangle(60, 20, 30));
        tList.add(new Triangle(10, 20.5, 30.23, "GREEN", true));
        // *****************************************

        // Print out greeting message
        System.out.println(" GeometricObject processed polymorphically");
        System.out.println();

        // *****************************************
        // Call out data from arraylist and print out with howToDraw method
        for (Circle c : cList)
        {
            System.out.println(c.howToDraw());
        }
        for (Rectangles r : rList)
        {
            System.out.println(r.howToDraw());
        }
        for (Squares s : sList) {
            System.out.println(s.howToDraw());
        }
        for (Triangle t : tList) {
            System.out.println(t.howToDraw());
        }
        // *****************************************



        // *****************************************
        // Print out Hidden line
       System.out.println(printHL());
        // *****************************************
        // Clear object and value
        Circle circle1 = new Circle(cList.indexOf(0));
        Circle circle2 = new Circle(10.0, "Blue", true);
        Circle circle3 = (Circle) (circle1.clone());

        Rectangles r1 = new Rectangles(25, 34);
        Rectangles r2 = new Rectangles(12, 20, "YELLOW", true);

        Squares sq1 = new Squares(10);
        Squares sq2 = new Squares(24.2, "RED", true);

        Triangle tr1 = new Triangle(2, 3, 4);
        Triangle tr2 = new Triangle(10, 20.5, 30.23, "GREEN", true);
        // *****************************************


        // *****************************************
        // call out max method from difference class and define object and print out the result
        Circle maxC=(Circle)Circle.max(circle1,circle2);
        System.out.printf(" The max circle's radius is %.2f\n",maxC.getRadius());
        Rectangles maxR=(Rectangles)Rectangles.max(r1,r2);
        System.out.printf(" The max rectangle's width is %.2f and height is %.2f\n",maxR.getWidth(),maxR.getHeight());
        Squares maxS=(Squares)Squares.max(sq1,sq2);
        System.out.printf(" The max square side is %.2f\n",maxS.getSide());
        Triangle maxT=(Triangle)Triangle.max(tr1,tr2);
        System.out.printf(" The max triangle's is : [%s]: side1 = %.1f side2 = %.1f side3 = %.2f\n",maxT.getObjName(),maxT.getSide1(),maxT.getSide2(),maxT.getSide3());
        System.out.println();
        // *****************************************
        // Print out Hidden line
        System.out.println(printHL());
        // *****************************************
        // Print out result with comparable method
        System.out.println(" The Comparsion of circle1 and circle2: "+circle1.compareTo(circle2));
        System.out.println(" The Comparsion of circle1 and circle3: "+circle1.compareTo(circle3));
        System.out.println(" The Comparsion of circle2 and circle1: "+circle2.compareTo(circle1));
        System.out.println(" The Comparsion of rectangle1 and rectangle2: "+r1.compareTo(r2));
        System.out.println();
        // *****************************************
        // Print out Hidden line
        System.out.println(printHL());
        // *****************************************
        // Print out result with equals method
        System.out.println(" The result of circle1 and circle2 by using equals method: "+circle1.equals(circle2));
        System.out.println(" The result of circle1 and circle1 by using equals method: "+circle1.equals(circle1));
        System.out.println(" The result of circle3 and circle1 by using equals method: "+circle3.equals(circle1));
        // *****************************************
        // Print out bottom line for end result
        System.out.println(printDL());
    }
        // *****************************************




        // *****************************************
        // Declare Print line method
            public static String printHL()
         {
                for(int i = 1; i <= 53; i++)
                {
                System.out.print("-");
                }
                return ("");
         }

            public static String printDL()
         {
                for(int i = 1; i <= 32; i++)
         {
                System.out.print("—");
         }

                return ("");
         }
        // *****************************************

}

