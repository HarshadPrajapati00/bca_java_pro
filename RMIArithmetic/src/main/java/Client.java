import java.rmi.Naming;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Arithmetic obj =
                (Arithmetic) Naming.lookup("rmi://localhost/ArithmeticService");

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter First Number: ");
            int a = sc.nextInt();

            System.out.println("Enter Second Number: ");
            int b = sc.nextInt();

            System.out.println("Addition: " + obj.add(a, b));
            System.out.println("Subtraction: " + obj.sub(a, b));
            System.out.println("Multiplication: " + obj.mul(a, b));
            System.out.println("Division: " + obj.div(a, b));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
