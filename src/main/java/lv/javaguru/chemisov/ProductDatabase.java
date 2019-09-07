package lv.javaguru.chemisov;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductDatabase {
    private final static ProductDatabase database = new ProductDatabase();
    private static Map<Long, Product> inMemoryDb = new HashMap<>();

    private ProductDatabase() {

    }

    public static ProductDatabase getInstance() {
        return database;
    }

    public int size() {
        return inMemoryDb.size();
    }
    public void addProduct(Product product) {
        inMemoryDb.put(product.getId(), product);
    }
    public boolean hasProduct(long id) {
        return inMemoryDb.containsKey(id);
    }
    public Product getProduct(long id) {
        return inMemoryDb.get(id);
    }
    public ArrayList<Product> getProductList() {
        return (ArrayList<Product>) new ArrayList(inMemoryDb.values());
    }
    public void deleteProduct(long productId) {
        inMemoryDb.remove(productId);
    }

    public void start() {
        addProduct(new Product(ProductCategory.FRUITS, "Apple", new BigDecimal("2.50")));
        addProduct(new Product(ProductCategory.FRUITS, "Banana", new BigDecimal("3.14")));
        addProduct(new Product(ProductCategory.VEGETABLES, "Potatoes", new BigDecimal("1.1475"),
                "Potatoes are edible tubers, available worldwide and all year long. " +
                        "They are relatively cheap to grow, rich in nutrients, " +
                        "and they can make a delicious treat.", new BigDecimal("0.05")));
        addProduct(new Product(ProductCategory.GRAINS, "Cashew nuts", new BigDecimal("6.8475"),
                "Cashew nuts are native to Brazil, where they have long been viewed as a delicacy.",
                new BigDecimal("0.15")));
    }

    public void readFile() {

    }
    public void writeFile() {

    }

    public void shutdown() {
        try (FileOutputStream fs = new FileOutputStream("products.ser");
             ObjectOutputStream os = new ObjectOutputStream(fs)) {
            os.writeObject(inMemoryDb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
