import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Scanner;

interface HBIface extends Remote {
    String addBooking(String name) throws RemoteException;
    String cancelBooking(String name) throws RemoteException;
    String[] getGuests() throws RemoteException;
}

class HBImpl extends UnicastRemoteObject implements HBIface {
    private ArrayList<String> guestList = new ArrayList<>();

    protected HBImpl() throws RemoteException {}

    public String addBooking(String name) {
        if (guestList.indexOf(name) != -1)
            return "Guest \"" + name + "\" is already booked.";

        boolean retval = guestList.add(name);
        if (retval) return "Guest \"" + name + "\" added successfully.";
        else return "Unable to add guest \"" + name + "\".";
    }

    public String cancelBooking(String name) {
        boolean retval = guestList.remove(name);
        if (retval) return "Guest \"" + name + "\" removed successfully.";
        else return "Unable to remove guest \"" + name + "\". Does not exist";
    }
public String[] getGuests() {
        return guestList.toArray(new String[guestList.size()]);
    }
}

class HotelBooking {
    public static void main(String[] args) {
        try {
            String uri = "rmi://0.0.0.0:1337/hotel";

            LocateRegistry.createRegistry(1337);
            Naming.rebind(uri, new HBImpl());

            HBIface serv = (HBIface) Naming.lookup(uri);

            System.out.println(serv.addBooking("Harsh"));
            System.out.println(serv.addBooking("Varun"));

            System.out.print("Enter a guest name: ");
            Scanner inp = new Scanner(System.in);
            String name = "";
            if (inp.hasNextLine())
                name = inp.nextLine();
            inp.close();

            if (!name.isBlank())
                System.out.println(serv.addBooking(name));

            System.out.println("\nCurrently added guests:");
            int counter = 1;
            for (String guest : serv.getGuests()) {
                System.out.println(Integer.toString(counter) + ". " + guest);
                counter += 1;
            }

            System.out.println(serv.cancelBooking("Harsh"));
            System.out.println(serv.cancelBooking("Varun"));
            System.out.println(serv.cancelBooking("Gaurav"));

            if (!name.isBlank())
                System.out.println(serv.cancelBooking(name));
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        System.exit(0);
    }
}

