
import java.util.ArrayList;

public class BookStore {
    //ArrayList for the inventory of books
    private ArrayList<Books> inventory = new ArrayList<Books>();
    //Preset inventory of books initialized and added to the ArrayList
    public BookStore() {
        Books b1 = new Books("Cat and the Hat", "Dr.Seuss", 5.99, 5, 1);
        Books b2 = new Books("Harry Potter", "J.K Rowling", 8.99, 3, 2);
        Books b3 = new Books("Percy Jackson ", "Rick Riordan", 9.99, 4, 3);
        Books b4 = new Books("Green Eggs and Ham", "Dr.Seuss", 4.99, 5, 4);
        inventory.add(b1);
        inventory.add(b2);
        inventory.add(b3);
        inventory.add(b4);
    }
    //getter for the inventory
    public ArrayList<Books> getInventory() {
        return inventory;
    }


}
