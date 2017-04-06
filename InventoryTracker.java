/*
 * a program that will use a loop to accept the names of ten items and the raw cost of each item.
 * The names and the item raw costs will be stored in two parallel arrays. The program will then
 * output the names or the items, item raw costs and item prices (calculated using the formula
 * itemCost(x) * 1.3) in table form with the column title "Item Name" above the names, the title
 * "Item Cost" above the raw costs and the title "Item Price" above the prices.
 */

package assignment2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Scanner;

public class InventoryTracker {

  public static void main(String[] args) {
    // Initialize scanner just once here then pass it as a parameter
    // because it's used in multiple methods
    Scanner input = new Scanner(System.in);

    introduction();

    // Only if user is ready, store data. Otherwise just end the program with "Bye"
    if (isUserReady(input)) {
      storeUserData(input);
    }

    goodbye();
  }

  // A method that greets the user and provides brief instructions.
  public static void introduction() {
    System.out.println("Welcome to the Inventory Tracker Program.");
    System.out.println("This program will accept the names and costs for 3 stocked items.");
    System.out.println(
        "The program will then output a table with the names, costs, and prices of the items.");
    System.out.println("Prices are calculated with a 30% markup on cost.\n");
  }

  // A method to allow user to control when the data entry begins
  public static boolean isUserReady(Scanner input) {
    System.out.print("Enter B to begin, E to end: ");

    // ternary if/else. B = "begin", if they enter anything else, return false
    return input.next().toLowerCase().equals("b") ? true : false;
  }

  // A method that prompts for and accepts input from the user.
  private static void storeUserData(Scanner input) {
    final int NUMBEROFPRODUCTS = 10;
    String[] products = new String[NUMBEROFPRODUCTS];
    double[] costs = new double[NUMBEROFPRODUCTS];
    // A third parallel array where you'll store calculated product cost * 3% tax
    BigDecimal[] prices = new BigDecimal[NUMBEROFPRODUCTS];

    for (int i = 0; i < products.length; i++) {
      System.out.print("\nEnter product " + (i + 1) + " name: ");
      products[i] = input.next();

      System.out.print("Enter the item cost: ");
      costs[i] = input.nextDouble();

      // Calculate the cost * .3 tax and store in a new parallel array
      // Round here to 2 decimals because currency formatter doesn't round correctly
      prices[i] = new BigDecimal(costs[i] * 1.3).setScale(2, RoundingMode.HALF_UP);
    }

    printTable(products, costs, prices);
  }

  // A method that outputs the table.
  private static void printTable(String[] products, double[] costs, BigDecimal[] prices) {
    // Format is 3 columns with 10 spaces between. Minus sign means align left.
    System.out.println("\nInventory Table:\n");
    String format = "%-15s %-15s %-15s %n";
    System.out.format(format, "Item Name", "Item Cost", "Item Price");

    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
    double totalCost = 0;

    for (int i = 0; i < products.length; i++) {
      System.out.format(format, products[i], currencyFormatter.format(costs[i]),
          currencyFormatter.format(prices[i]));
      totalCost += costs[i];
    }

    System.out.println("\nTotal Cost of Items: " + currencyFormatter.format(totalCost));
  }

  // A method that thanks the user and says goodbye.
  private static void goodbye() {
    System.out.println("\nThanks for using this program. Goodbye!");
  }
}
