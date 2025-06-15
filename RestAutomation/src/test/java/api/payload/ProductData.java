package api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductData {
    private int year;
    private double price;
    
    @JsonProperty("CPU model")
    private String cpuModel; 
    
    @JsonProperty("Hard disk size")
    private String hardDiskSize; 

    // Constructor
    public ProductData(int year, double price, String cpuModel, String hardDiskSize) {
        this.year = year;
        this.price = price;
        this.cpuModel = cpuModel;
        this.hardDiskSize = hardDiskSize;
    }

    // Getters
    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public String getHardDiskSize() {
        return hardDiskSize;
    }
}