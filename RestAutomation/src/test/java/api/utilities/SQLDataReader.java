package api.utilities;
import java.sql.*;
import java.util.*;

import api.payload.Product;
import api.payload.ProductData;

public class SQLDataReader {
    private static final String DB_URL = ConfigManager.getProperty("db.url");
    private static final String DB_USER = ConfigManager.getProperty("db.user");
    private static final String DB_PASSWORD = ConfigManager.getProperty("db.password");

    /**
     * Generic method to read SQL data and return as List<Map<String, Object>>
     * @param query SQL query to execute
     * @return List of rows with column name/value pairs
     */
    public static List<Map<String, Object>> executeQuery(String query) {
        List<Map<String, Object>> result = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                result.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute SQL query: " + query, e);
        }
        return result;
    }

    /**
     * Converts SQL results to TestNG DataProvider format
     * @param query SQL query
     * @return Object[][] suitable for TestNG DataProvider
     */
    public static Object[][] getProductPayloads(String query) throws SQLException {
        // 1. Get raw data from database
        List<Map<String, Object>> dbRows = executeQuery(query);
        
        // 2. Prepare array for TestNG (rows x 1 column)
        Object[][] payloads = new Object[dbRows.size()][1];
        
        // 3. Convert each row to a Product object
        for (int i = 0; i < dbRows.size(); i++) {
            Map<String, Object> row = dbRows.get(i);
            
            // Create Product payload
            Product product = new Product(
                (String) row.get("name"),
                new ProductData(
                    ((Number) row.get("year")).intValue(),
                    ((Number) row.get("price")).doubleValue(),
                    (String) row.get("cpu_model"),
                    (String) row.get("disk_size")
                )
            );
            
            payloads[i][0] = product; // Store ready-to-use payload
        }
        
        return payloads;
    }
}