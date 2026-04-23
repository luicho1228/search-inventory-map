package com.plurasight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class StoreApp {

    public static void main(String[] args) {

        // this method loads product objects into inventory
        HashMap<Integer,Product> inventory = loadInventory();
        Scanner scanner = new Scanner(System.in);
        System.out.print("What item # are you interested in? ");
        int id = scanner.nextInt();
        Product matchedProduct = inventory.get(id);
        if (matchedProduct == null) {
            System.out.println("We don't carry that product");
            return;
        }
        System.out.printf("We carry %s and the price is $%.2f",
                matchedProduct.getName(), matchedProduct.getPrice());
    }

    public static HashMap loadInventory(){
        HashMap<Integer, Product> productHashMap = new HashMap<Integer,Product>();
        return readFile(productHashMap);
    }

    public static HashMap readFile(HashMap productHashMap){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("inventory.csv"));
            String fileInput = "";
            while ((fileInput = bufferedReader.readLine()) != null) {
                String[] stringParts = fileInput.split("\\|");
                int id = Integer.parseInt(stringParts[0]);
                String name = stringParts[1];
                float price = Float.parseFloat(stringParts[2]);
                productHashMap.put(id,new Product(id,name,price));
            }
            bufferedReader.close();
            return  productHashMap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
