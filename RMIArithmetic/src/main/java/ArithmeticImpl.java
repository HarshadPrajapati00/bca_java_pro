import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ArithmeticImpl extends UnicastRemoteObject implements Arithmetic {

    public ArithmeticImpl() throws RemoteException {
        super();
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public double div(int a, int b) {
        if (b == 0)
            return 0;
        return (double) a / b;
    }
}
