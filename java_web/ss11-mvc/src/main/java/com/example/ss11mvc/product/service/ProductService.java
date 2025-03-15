package com.example.ss11mvc.product.service;



import com.example.ss11mvc.product.model.Product;
import com.example.ss11mvc.product.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final String SELECT_ALL ="select * from product";
    private final String DELETE_BY_ID ="delete from product where pid =?";
    private final String INSERT_INTO ="insert into product(pName,pPrice,pDesc,pManufac) values (?,?,?,?)";
    private final String UPDATE_BY_ID = "update product set pName=?,pPrice=?,pDesc=?,pManufac=? where pid=?";
    private final String SELECT_BY_ID ="select * from product where pid=?";
    private final String SELECT_BY_NAME ="select * from product where pName=?";
    public static List<Product> products = new ArrayList<>();

    public List<Product> getProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("pid");
            String name = resultSet.getString("pName");
            double price = resultSet.getDouble("pPrice");
            String description = resultSet.getString("pDesc");
            String manufacturer = resultSet.getString("pManufac");
            Product product = new Product(id, name, price, description, manufacturer);
            products.add(product);
        }
        return products;
    }
    public void addProduct(Product product) throws SQLException {
        Connection connection = BaseRepository.getConnectDB();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setString(3, product.getDescription());
        preparedStatement.setString(4, product.getManufacturer());
        preparedStatement.executeUpdate();
    }
    public void updateProduct(int id, Product product) throws SQLException {
        Connection connection = BaseRepository.getConnectDB();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setString(3, product.getDescription());
        preparedStatement.setString(4, product.getManufacturer());
        preparedStatement.setInt(5, id);
        preparedStatement.executeUpdate();
    }
    public void deleteProduct(int id) throws SQLException {
        Connection connection = BaseRepository.getConnectDB();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
    public Product getProductById(int id) throws SQLException {
        Connection connection = BaseRepository.getConnectDB();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return new Product(
                    resultSet.getInt("pid"),
                    resultSet.getString("pName"),
                    resultSet.getDouble("pPrice"),
                    resultSet.getString("pDesc"),
                    resultSet.getString("pManufac")
            );
        }
        return null;
    }
    public List<Product> getProductByName(String name) throws SQLException {
        List<Product> result = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            result.add(new Product(
                    resultSet.getInt("pid"),
                    resultSet.getString("pName"),
                    resultSet.getDouble("pPrice"),
                    resultSet.getString("pDesc"),
                    resultSet.getString("pManufac")
            ));
        }
        return result;
    }
    public int generateId() {
        String query = "select max(id) as max_id from products";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt("max_id") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }}
