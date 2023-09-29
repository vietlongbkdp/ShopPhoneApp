package models;

public class Product {
    private int id;
    private String productName;
    private Branch branch;
    private String image;
    private String price;
    private String quantity;
    private String warrantyPeriod;
    private String ram;
    private String size;
    private String color;
    private String camera;
    private String operatingSystem;
    private String pin;

    public Product() {
    }

    public Product(String productName, Branch branch, String image, String price, String quantity, String warrantyPeriod, String ram, String size, String color, String camera, String operatingSystem, String pin) {
        this.productName = productName;
        this.branch = branch;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.warrantyPeriod = warrantyPeriod;
        this.ram = ram;
        this.size = size;
        this.color = color;
        this.camera = camera;
        this.operatingSystem = operatingSystem;
        this.pin = pin;
    }

    public Product(int pId) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Product(int id, String productName, Branch branch, String image, String price, String quantity, String warrantyPeriod, String ram, String size, String color, String camera, String operatingSystem, String pin) {
        this.id = id;
        this.productName = productName;
        this.branch = branch;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.warrantyPeriod = warrantyPeriod;
        this.ram = ram;
        this.size = size;
        this.color = color;
        this.camera = camera;
        this.operatingSystem = operatingSystem;
        this.pin = pin;
    }
}
