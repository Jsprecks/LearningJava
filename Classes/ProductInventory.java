/*
 * Product Inventory Project - Create an application which manages
 * an inventory of products. Create a product class which has a
 * price, id, and quantity on hand. Then create an inventory class
 * which keeps track of various products and can sum up the inventory
 * value.
 *
 * @author RomanGotsiy (gotsijroman@gmail.com)
 */

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

class Product {
    public double price;
    private String name;
    private int quantity;

    public Product(String name, double price,  int quantity){
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    public void incQuantity(int qnt) {
        this.quantity +=qnt;
    }

    public void decQuantity(int qnt) {
        this.quantity = Math.max(this.quantity-qnt, 0);
    }

    public int getQuantity(){
        return this.quantity;
    }

    public String toString(){
        return String.format("%s\t%.2f\t%d",name,price,quantity);
    }
}

class Inventory {
    private List<Product> products;

    public Inventory(){
        products = new ArrayList<Product>();
    }

    public void addProduct(Product p){
        products.add(p);
    }

    public void printInventory(){
        double sum = 0;
        for(Product p : products){
            System.out.println(p.toString());
            sum+=p.price*p.getQuantity();
        }
        System.out.println("Average is: " + sum);
    }
    public void saveInventoryToFile() {
        try {
            FileWriter writer = new FileWriter("inventory.txt");
            for (Product p : products) {
                writer.write(p.toString() + "\n");
            }
            writer.close();
            System.out.println("Inventory saved to inventory.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the inventory to file: " + e.getMessage());
        }
    }
}

import java.util.Scanner;

public class ProductInventory {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Inventory inv = new Inventory();


        while (true) {
            System.out.print("Enter product name(or type 'quit' to end): ");
            String input = scanner.nextLine();
            if (input.equals("quit")) {
                break;
            }
            String name = input;

            System.out.print("Enter product price: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter product quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            Product product = new Product(name, price, quantity);
            inv.addProduct(product);
        }

        inv.printInventory();
        inv.saveInventoryToFile();
        scanner.close();
    }
}


