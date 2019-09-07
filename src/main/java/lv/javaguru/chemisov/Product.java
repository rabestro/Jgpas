package lv.javaguru.chemisov;

import java.math.BigDecimal;
import java.util.Objects;

public class Product implements Comparable {
    private static long nextId = 0;
    private static long generateNewId() {
        return ++nextId;
    }

    private final long id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal discount;
    private ProductCategory category;

    public Product(ProductCategory category, String name, BigDecimal price) {
        this(category, name, price, null, null);
    }

    public Product(ProductCategory category, String name, BigDecimal price,
            String description,  BigDecimal discount) {

        id = generateNewId();
        this.category = category;
        this.name = name;
        this.price = price;
        
        this.description = description == null ? "" : description;
        this.discount = discount == null ? new BigDecimal("0.00") : discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getName().equals(product.getName()) &&
                getCategory() == product.getCategory();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCategory());
    }

    @Override
    public int compareTo(Object o) {
        Product p = (Product) o;
        if (this.category != p.category) {
            return this.category.compareTo(p.category);
        }
        return this.name.compareTo(p.name);
    }
    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public boolean hasDiscount() {
        return discount.compareTo(new BigDecimal("0.00")) > 0;
    }
    public BigDecimal getActualPrice() {
        return price.subtract(price.multiply(discount));
    }
    public String getDiscountPercent() {
        return discount.multiply(new BigDecimal(100)).intValue() + "%";
    }
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(String.format("%-12s: %d%n", "Product ID", getId()));
        buffer.append(String.format("%-12s: %s%n", "Category", category.getTitle()));
        buffer.append(String.format("%-12s: %s%n", "Name", name));
        buffer.append(String.format("%-12s: %s%n", "Description", description));
        buffer.append(String.format("%-12s: %s%n", "Price", price.toString()));
        if (hasDiscount()) {
            buffer.append(String.format("%-12s: %s%n", "Discount", discount.toString()));
            buffer.append(String.format("%-12s: %s%n", "Actual Price", getActualPrice().toString()));
        }
        return buffer.toString();
    }
}
