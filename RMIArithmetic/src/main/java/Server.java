import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); // start registry
            Arithmetic obj = new ArithmeticImpl();
            Naming.rebind("rmi://localhost/ArithmeticService", obj);

            System.out.println("RMI Server Started...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
