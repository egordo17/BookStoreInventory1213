

public class Books {
    //book class attributes
    private String title;
    private String author;
    private double price;
    private int NumCopies;
    private int productID;


    //book constructor
    public Books(String title, String author, double price, int NumCopies, int productID) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.NumCopies = NumCopies;
        this.productID = productID;
    }
    //toString method to display the book information to the customer
    public String toString(){
        return  this.productID + " Title: "+ this.title + " Author: "+ this.author + " Price: "+ this.price + " NumCopies: "+ this.NumCopies;
    }
    //Only the necessary getters and setters
    public int getNumCopies(){
        return this.NumCopies;
    }
    public void setNumCopies(int numCopies){
        this.NumCopies = numCopies;
    }
    public String getTitle(){
        return this.title;
    }
    public double getPrice(){
        return this.price;
    }
    public int getProductID(){
        return this.productID;
    }
}
