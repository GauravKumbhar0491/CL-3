import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.LocateRegistry;

interface StringIface extends Remote {
    String concat(String a, String b) throws RemoteException;
}

class StringImpl extends UnicastRemoteObject implements StringIface {
    protected StringImpl() throws RemoteException {}
    public String concat(String a, String b) {
        return a + b;
    }
}

class StringServer {
    public static void main(String[] args) {
        try {
            String URI = "rmi://0.0.0.0:1337/string";
            LocateRegistry.createRegistry(1337);
            Naming.rebind(URI, new StringImpl());
            
            StringIface serv = (StringIface) Naming.lookup(URI);
            String result = serv.concat("Hello ", "World");

            System.out.println(result);

            Naming.unbind(URI);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.exit(0);
    }
}

