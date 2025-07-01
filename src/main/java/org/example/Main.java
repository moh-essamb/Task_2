package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Main {

    protected WebDriver driver;

    // List of products and their quantities
    protected static String[] products = {"Cucumber","5","Carrot","8","Tomato","13", "Mushroom","2"};
    protected static String parent;

    // WebElements
    protected WebElement product, quantityBox;

    public Main() {
        // Initialize Edge browser
        driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#");
    }


     //Clears a text box
    public void deleteTextBox(WebElement textBox) {
        textBox.click(); // Focus the input
        textBox.sendKeys(Keys.chord(Keys.CONTROL, "a")); // Select all
        textBox.sendKeys(Keys.BACK_SPACE); // Delete
    }

    // sets quantity and clicks Add to Cart for a single product.
    public void addSingleProductsToCart(String product_Name, String quantity) {
        parent = "//div[@class='products']/div[@class='product']/h4[contains(text(), '" + product_Name + "')]/parent::div";
        // Locate the quantity input box using product name
        quantityBox = driver.findElement(By.xpath( parent + "/div[@class='stepper-input']/input"));
        deleteTextBox(quantityBox); // Clear the quantity box
        quantityBox.sendKeys(quantity); // Enter new quantity

        // Locate the Add to Cart button using product name
        product = driver.findElement(By.xpath(parent + "/div[@class='product-action']/button"));
        product.click();//click the Add to Cart button

        //check if the product was added to the cart or not
        if(product.getText().equals("✔ ADDED")){
            System.out.println(quantity+" Nos. of "+product_Name+" was added to the cart ✔");
        }
        else {
            System.out.println(quantity+" Nos. of "+product_Name+" wasn't added to the cart X");
        }
    }


     //Loops over a list of products and quantities to add each to the cart.
    public void addMultipleProductsToCart(String[] products) {
        for (int i = 0; i < products.length; i+=2) {
            addSingleProductsToCart(products[i],products[i+1]);
        }
    }

    public static void main(String[] args) {
        Main m = new Main();

        m.addMultipleProductsToCart(products);
    }
}
