package com.example.ss11mvc.product.controller;

import com.example.ss11mvc.product.model.Product;
import com.example.ss11mvc.product.service.ProductService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/products")
public class ProductController extends HttpServlet {
    private final ProductService productService = new ProductService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "new":
                showCreateform(request, response);
                break;
            case "edit":
                try {
                    showEdit(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    deleteProduct(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "search":
                try {
                    searchProduct(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            default:
                try {
                    listProduct(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String keyword = request.getParameter("keyword");
        List<Product> results = productService.getProductByName(keyword);
        request.setAttribute("results", results);
        request.getRequestDispatcher("/product_list.jsp").forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.deleteProduct(id);
        listProduct(request,response);
    }

    private void showCreateform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("create.jsp").forward(request, response);
    }
    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            double price = Integer.parseInt(request.getParameter("price"));
            String manufacturer = request.getParameter("manufacturer");
            Product product = new Product(productService.generateId(), name, price, description, manufacturer);
            productService.addProduct(product);
            listProduct(request, response);
        }
        catch (NumberFormatException e){
            request.setAttribute("error", "Invalid price format");
            request.getRequestDispatcher("/create.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Product> products = productService.getProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/product_list.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "edit":
                try {
                    editProduct(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "new":
                createProduct(request, response);
                break;
        }
    }
    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getProductById(id);
        if (product != null) {
            request.setAttribute("product", product);
            request.getRequestDispatcher("/edit.jsp").forward(request, response);
        } else {
            response.sendRedirect("/products");
        }
    }
    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String manufacturer = request.getParameter("manufacturer");

        Product product = new Product(id, name, price, description, manufacturer);
        productService.updateProduct(id, product);
        listProduct(request, response);

    }
}
