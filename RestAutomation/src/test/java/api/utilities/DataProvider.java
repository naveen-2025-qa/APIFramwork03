package api.utilities;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.payload.Product;
import api.payload.ProductData;


public class dataProvider {
	
	@DataProvider(name="Excel")
	public static Object[][] readExcelData(String path) throws IOException
	{
		Object[][] data =ExcelUtility.getAllRows(path);
		return data;
		
	}
	
	@DataProvider(name = "dbProducts")
    public static Object[][] provideProducts() throws SQLException {
        String query = "SELECT * FROM products WHERE test_scenario = 'VALID'";
        //return SQLDataReader.getProductPayloads(query);
        return new Object[][] {
            { new Product("Ipad", new ProductData(2023, 1999.99, "M2", "1TB")) }, // Missing name
            { new Product("MacBook", new ProductData(2023, 100.00, "M2", "1TB")) } // Negative price
		};
	}
        
    
	
	@DataProvider(name = "dbUsers")
    public static Object[][] provideUsers() throws SQLException {
        String query = "SELECT * FROM users WHERE is_test_account = true";
        return SQLDataReader.getProductPayloads(query);
    }
    
    @DataProvider(name = "invalidProductData")
    public static Object[][] provideInvalidProductData() {
        return new Object[][] {
		            { new Product(null, new ProductData(2023, 1999.99, "M2", "1TB")) }, // Missing name
		            { new Product("MacBook", new ProductData(2023, -100.00, "M2", "1TB")) } // Negative price
        		};
    		}
    }
