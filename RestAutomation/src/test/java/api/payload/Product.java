package api.payload;

public class Product {
    private String name;
    private ProductData data;

    // Constructor
    public Product(String name, ProductData data) {
        this.name = name;
        this.data = data;
    }

    // Getters
    public String getName() {
        return name;
    }

    public ProductData getData() {
        return data;
    }

    // Setters (if needed)
    public void setName(String name) {
        this.name = name;
    }

    public void setData(ProductData data) {
        this.data = data;
    }
}

