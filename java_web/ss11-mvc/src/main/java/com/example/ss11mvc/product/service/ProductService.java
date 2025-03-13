package com.example.ss11mvc.product.service;



import com.example.ss11mvc.product.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public static List<Product> products = new ArrayList<>();
    static {
        products.add(new Product(1, "SwiftStream 200", 950.00, "Everyday Use", "NovaTech"));
        products.add(new Product(2, "GameMaster X", 1800.00, "Gaming", "Apex Systems"));
        products.add(new Product(3, "WorkPro 50", 1200.00, "Business", "GlobalTech"));
        products.add(new Product(4, "EduBook 100", 700.00, "Student", "LearnWell"));
        products.add(new Product(5, "MediaMax Pro", 1550.00, "Creative", "Visionary"));
        products.add(new Product(6, "TravelLite 30", 850.00, "Travel", "RoamFree"));
        products.add(new Product(7, "PowerHouse 90", 2000.00, "High Performance", "Quantum Leap"));
        products.add(new Product(8, "DailyDriver 25", 650.00, "Basic", "Simple Solutions"));
        products.add(new Product(9, "CodeCrafter 11", 1400.00, "Programming", "Logic Labs"));
        products.add(new Product(10, "Artisan 7", 1700.00, "Design", "Creative Canvas"));
        products.add(new Product(11, "NetSurfer 15", 550.00, "Web Browsing", "Cloud Connect"));
        products.add(new Product(12, "UltraView 88", 1900.00, "Video Editing", "Pixel Perfect"));
        products.add(new Product(13, "OfficeMate 42", 1100.00, "Office", "Productive Pro"));
        products.add(new Product(14, "Student Plus", 750.00, "Student", "Academic Ace"));
    }

    public List<Product> getProducts() {
        for (int i = 0; i < generateId()-1; i++) {
            products.get(i).setId(i+1);
        }
        return products;
    }
    public void addProduct(Product product) {
        products.add(product);
    }
    public void updateProduct(int id, Product product) {
        for(Product p : products) {
            if(p.getId() == id) {
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                p.setDescription(product.getDescription());
                p.setManufacturer(product.getManufacturer());
                return;
            }
        }
    }
    public void deleteProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                break;
            }
        }
    }
    public Product getProductById(int id) {
        for(Product p : products) {
            if(p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    public List<Product> getProductByName(String name) {
        List<Product> result = new ArrayList<>();
        for(Product p : products) {
            if(p.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }
    public int generateId() {
        int maxId = 0;
        for (Product p : products) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        return maxId + 1;
    }
}
