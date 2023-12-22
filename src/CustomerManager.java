import java.io.*;
import java.util.*;

class CustomerManager{
    private static Vector<Customer> customers;

    public CustomerManager() throws FileNotFoundException{
         customers = getCustomersfromFile();

    }

    public static Vector<Customer> getCustomersfromFile() throws FileNotFoundException {
        Vector<Customer> customers = new Vector<Customer>();
        Vector<User> users = User.readFromUserFile(2);

        for (User user : users) {
            Customer customer = new Customer(user.getUserID(), user.getUserName(), user.getPassword(), user.getEmail(), user.getUserRole());
            customers.add(customer);
        }
        return customers;
    }

    public void addCustomersIntoFile(Customer c) throws IOException{
        PrintWriter outputFile = new PrintWriter(new FileWriter("userDatabase.txt",true));
        outputFile.write(c.getUserID()+ " "+c.getUserName()+ " "+c.getPassword()+ " "+c.getEmail()+ " "+2+"\n");
        outputFile.close();
        customers.add(c);

        for(Customer ci:customers){
            System.out.println(ci.getUserName());
        }
    }
}