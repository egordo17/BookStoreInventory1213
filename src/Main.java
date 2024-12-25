import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
/* The function of this program allows the user to see the inventory of the store. If the user decides to buy a book
it will be documented in a receipt made from a txt file and the store inventory will update. If the user wants to return
the books they purchase they can enter the name of their receipt and the program will read the file and update the
bookstore inventory.*/

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BookStore bookStore = new BookStore();
        String firstName = "";
        boolean open = true;

        // loop the menu so the customer can use repeat
        while(open) {
            System.out.println("Welcome to the BookStore, enter a number of one of the following options:");
            System.out.println("1. Browse inventory: ");
            System.out.println("2. Purchase a book: ");
            System.out.println("3. Return books: ");
            System.out.println("4. Exit: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:

                    System.out.println("Here's what we have in store:");
                    //Loop through and print all the different books in the inventory
                    for(Books books : bookStore.getInventory()){
                        System.out.println(books);
                    }
                    break;
                case 2:
                    try {

                            //Create a diredtory for the Receipt file
                            File dir = new File("Receipts");
                            if (!dir.exists()) {
                                dir.mkdir();
                            }
                            //Customers name will be name of the receipt
                            System.out.println("What is your first name?");
                            firstName = scanner.nextLine().trim();
                           //Exception handle for missing filename
                            if (firstName.isEmpty()) {
                                System.out.println("Error: File name cannot be empty.");
                                break;
                            }
                        //create file name after the customer
                        File file = new File(dir.getPath() + File.separator + firstName + ".txt");
                        boolean isNewFile = file.createNewFile();
                        PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
                            //create header for the receipt
                            if(isNewFile) {
                                writer.println("ID:   Title:  Price: ");
                            }
                            //while loop allow the customer to make as many purchases that they want
                            while (true) {

                                //Document customer selection as they make purchase
                                System.out.println("Enter the ID number of the product you'd like to purchase: ");
                                int choice2 = scanner.nextInt();
                                scanner.nextLine();
                                /*Shows the customer what book they purchase, add the book to the receipt,
                                and updates inventory
                                 */
                                if (choice2 >= 1 && choice2 <= bookStore.getInventory().size()) {
                                    Books selectedBook = bookStore.getInventory().get(choice2 - 1);
                                    System.out.println(selectedBook.toString());
                                    selectedBook.setNumCopies(selectedBook.getNumCopies() - 1);
                                    writer.println(String.format(selectedBook.getProductID()+ " "
                                            + selectedBook.getTitle()+ " " + selectedBook.getPrice()));
                                } else {
                                    System.out.println("Invalid choice");
                                }
                                System.out.println("Do you want to purchase another book? (Y/N)");
                                String choice3 = scanner.nextLine();

                                if (choice3.equalsIgnoreCase("Y")) {
                                    continue;
                                }
                                if (choice3.equalsIgnoreCase("N")) {
                                    break;
                                }
                            }
                            writer.flush();
                            writer.close();



                    } catch (FileNotFoundException e) {
                        System.out.println("Error: Could not create receipt file.");
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid input. Please enter a number.");
                        scanner.nextLine(); // Clear the invalid input
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    //READ the receipt and add inventory
                    try {
                        //ask customer to specify their receipt name
                        System.out.println("Enter your name to look up your receipt.");
                        String choice4 = scanner.nextLine();
                        //initialize scanner to read receipt
                        Scanner scanner2 = new Scanner(new File("Receipts" + File.separator + choice4 + ".txt"));
                        //read the header one
                        String lineOne = scanner2.nextLine();
                        // read the rest of the file and parse the ID number of the products of the receipt
                        String line;
                        while (scanner2.hasNextLine()) {
                             line = scanner2.nextLine().trim();
                            String[] split = line.split(" ");
                            int ID = Integer.parseInt(split[0]);
                            /*Loop through inventory and if item on the receipt matches with a book in inventory update
                            the number of books in the inventory
                             */
                            for (Books books : bookStore.getInventory()) {
                                if (books.getProductID() == ID) {
                                    books.setNumCopies(books.getNumCopies() + 1);
                                }
                            }
                        }


                        System.out.println("Your return is valid!");
                        scanner2.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Error: Receipt file not found.");
                    }
                    break;
                case 4:
                    //exit the program
                    System.out.println("Goodbye!");
                    open = false;

                    default: break;



            }
        }
    }
}