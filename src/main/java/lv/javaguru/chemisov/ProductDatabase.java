package lv.javaguru.chemisov;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductDatabase {
    private final static ProductDatabase database = new ProductDatabase();
    private static Map<Long, Product> inMemoryDb = new HashMap<>();
    private static final String DB_FILE_NAME = "products.ser";

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
        try (FileInputStream fi = new FileInputStream(DB_FILE_NAME);
             ObjectInputStream os = new ObjectInputStream(fi)) {
            inMemoryDb = (HashMap<Long, Product>) os.readObject();
            Product.nextId = os.readLong();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (size() < 1) {
            fillInitialData();
        }
    }

    private void fillInitialData() {
        System.out.println("Pollute database with some records...");
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
        try (FileOutputStream fs = new FileOutputStream(DB_FILE_NAME);
             ObjectOutputStream os = new ObjectOutputStream(fs)) {
            os.writeObject(inMemoryDb);
            os.writeLong(Product.nextId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
