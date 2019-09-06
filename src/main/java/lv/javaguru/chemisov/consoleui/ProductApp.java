package lv.javaguru.chemisov.consoleui;

import lv.javaguru.chemisov.Product;
import lv.javaguru.chemisov.ProductCategory;
import lv.javaguru.chemisov.ProductDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;

class ProductApp extends ConsoleApp {
    private final ProductDatabase database = ProductDatabase.getInstance();

    private long promptId() {
        long id = -1;
        do {
            String line = readLine("Enter the product id: ");
            if (line.length() < 1) {
                break;
            }
            try {
                id = Long.parseLong(line);
            }
            catch (NumberFormatException e) {
                printError("Cant't parse product id.");
                printMessage("Please use correct format for numbers.");
            }
        } while (id < 0);
        return id;
    }
    private String promptName() {
        String name = null;

        do {
            name = readLine("Enter the product name: ");
            if (name.length() < 1) {
                printError("Product name can't be empty.");
            }
        } while (name.length() < 1);

        return name;
    }
    private String promptDescription() {
        String description = null;
        printMessage("Enter description for the product.");
        description = readLine("Press Enter if no description: ");
        return description;
    }
    private BigDecimal promptPrice() {
        BigDecimal price = null;

        do {
            String line = readLine("Enter the product price: ");
            if (line.length() < 1) {
                printError("Product price is mandatory field.");
                continue;
            }

            try {
                price = new BigDecimal(line);
            }
            catch (NumberFormatException e) {
                printError("Cant't parse product price.");
                printMessage("Please use correct format for decimal numbers.");
                continue;
            }

            if (price.compareTo(new BigDecimal(0.0)) == -1) {
                printError("The product price can't be negative number.");
                price = null;
            }
        } while (price == null);

        return price;
    }
    private BigDecimal promptDiscount() {
        BigDecimal discount = null;

        do {
            printMessage("Enter the discount [0 to 1]");
            String line = readLine("Press Enter if no discount: ");

            if (line.length() < 1) {
                discount = new BigDecimal(0.0);
                continue;
            }

            try {
                discount = new BigDecimal(line);
            }
            catch (NumberFormatException e) {
                printError("Cant't parse discount. %n" +
                        "Please use correct format for decimal numbers.");
                continue;
            }

            if (discount.compareTo(new BigDecimal(0.0)) == -1) {
                printError("The discount can't be negative number.");
                discount = null;
                continue;
            }

            if (discount.compareTo(new BigDecimal(1.0)) == 1) {
                printError("The discount can't be more the 1.");
                discount = null;
            }
        } while (discount == null);

        return discount;
    }
    private ProductCategory selectCategory() {
        ProductCategory category = null;

        do {
            showCategories();
            String line = readLine("Enter category number: ");
            try {
                category = ProductCategory.values()[Integer.parseInt(line)];
            }
            catch (NumberFormatException e) {
                printError("Not a number.");
            }
            catch (ArrayIndexOutOfBoundsException e) {
                printError("Incorrect category number.");
            }
        } while (category == null);

        return category;
    }

    void productAdd() {
        printMessage("Please enter the information about new product.");
        ProductCategory category = selectCategory();
        String name = promptName();
        String description = promptDescription();
        BigDecimal price = promptPrice();
        BigDecimal discount = promptDiscount();
        database.addProduct(new Product(category, name, price, description, discount));
    }

    void productList() {
        ArrayList<Product> products = database.getProductList();
        for (Product product : products) {
            printFormat("%5d %-21s %s: %8.2f",
                    product.getId(),
                    product.getName(),
                    "Price", product.getPrice());
            if (product.hasDiscount()) {
                printFormat(" (-%3s) %s: %8.2f",
                        product.getDiscountPercent(),
                        "Actual price", product.getActualPrice());
            }
            System.out.println();
        }
        pauseExecution();
    }

    void showCategories() {
        printMessage("Categories of products: ");
        for (ProductCategory category : ProductCategory.values()) {
            printFormat("%5d %s%n", category.ordinal(), category.getTitle());
        }
        pauseExecution();
    }

    void productDelete() {
        printMessage("Deleting product.");
        final long id = promptId();
        if (!database.hasProduct(id)) {
            printError("Product with ID: " + id + " can't be found...");
            return;
        }
        database.deleteProduct(id);
        printMessage("Product with ID: " + id + " was deleted.");
        pauseExecution();
    }

    void productPrint() {
        Product product = null;

        do {
            final long id = promptId();
            if (id < 0) {
                printError("Entered incorrect id. Product print aborted.");
                return;
            }
            if (database.hasProduct(id)) {
                product = database.getProduct(id);
            } else {
                printError("Can't find a product with the ID: " + id);
            }
        } while (product == null);
        printMessage(product.toString());
        pauseExecution();
    }

    void productUpdate() {
    }
    void showProduct() {

    }
}
