import java.rmi.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to RMI server
            String URI = "rmi://127.0.0.1:1337/string";

            // Lookup remote object
            StringIface serv = (StringIface) Naming.lookup(URI);

            // Take input from user
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter first string: ");
            String str1 = sc.nextLine();

            System.out.print("Enter second string: ");
            String str2 = sc.nextLine();

            // Remote method call
            String result = serv.concat(str1, str2);

            // Display result
            System.out.println("Concatenated String: " + result);

            sc.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
