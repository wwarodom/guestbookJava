import java.util.Scanner;

/** Class ExtendedEuclid **/
public class MainACM
{
    public void extendedEuclid(long a, long b)    {
        //base case gcd(0,1) return => 1
        long x = 0, y = 1;
        // store previous state
        long x1 = 1, y1 = 0, temp;

        while (b != 0)   {
            long quotient = a / b;
            long remainder = a % b;

            a = b;
            b = remainder;

            temp = x;
            x = x1 - quotient * x;
            x1 = temp;

            temp = y;
            y = y1 - quotient * y;
            y1 = temp;
        }
        System.out.println("gcd = " + a  );
        System.out.println("Roots  x : "+ x1 +" y :"+ y1);
    }


    long gcdExtended(long a, long b, long u, long v)
    {
        x = u; y = v;

        // Base Case
        if (a == 0)  {
            x = 0;
            y = 1;
            return b;
        }

        long x1=1, y1=0; // To store results of recursive call
        long gcd = gcdExtended(b%a, a, x1, y1);

        // Update x and y using results of recursive
        // call
        x = y1 - (b/a) * x1;
        y = x1;
        System.out.println("x = "  + x + " y = " + y);
        return gcd;
    }

    /** Main function **/
    static long a = 3,  b = 11, x=0,y=1 ;

    public static void main (String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Extended Euclid Algorithm Test\n");
        /** Make an object of ExtendedEuclid class **/
        MainACM ee = new MainACM();

        /** Accept two integers **/
        System.out.println("Enter a b of ax + by = gcd(a, b)\n");
//        long a = scan.nextLong();
//        long b = scan.nextLong();

        /** Call function solve of class ExtendedEuclid **/
//        ee.extendedEuclid(a, b);
        System.out.println("gcd = " + ee.gcdExtended(a,b,x,y));

//        System.out.println( ee.GCD(9,3));

    }

    public int GCD(int a, int b) { return b==0 ? a : GCD(b, a%b); }
}