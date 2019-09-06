package lv.javaguru.chemisov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductTest {
    final private ProductCategory category = ProductCategory.VEGETABLES;
    final private String name = "Potatoes";
    final private BigDecimal price = new BigDecimal("1.1475");
    final private String description =  "Potatoes are edible tubers, available worldwide and all year long. ";
    final private BigDecimal discount = new BigDecimal("0.05");
    private Product product = new Product(category, name, price, description, discount);

    @Before
    public void setUp() throws Exception {
        product.setDiscount(discount);
        product.setDescription(description);
        product.setCategory(category);
        product.setName(name);
        product.setPrice(price);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getId() {
        final long actual = product.getId();

        assertTrue("The product id has to be greater then zero", actual > 0);
    }

    @Test
    public void getDescription() {
        final String expected = description;
        final String actual = product.getDescription();
        assertEquals(expected, actual);
    }

    @Test
    public void setDescription() {
        final String expected = "The new description";
        product.setDescription(expected);
        final String actual = product.getDescription();
        assertEquals(expected, actual);
    }

    @Test
    public void getName() {
        final String expected = name;
        final String actual = product.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void setName() {
        final String expected = "New Name";
        product.setName(expected);
        final String actual = product.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void getCategory() {
        final ProductCategory expected = category;
        final ProductCategory actual = product.getCategory();
        assertEquals(expected, actual);
    }

    @Test
    public void setCategory() {
        ProductCategory expected = ProductCategory.FRUITS;
        product.setCategory(expected);
        ProductCategory actual = product.getCategory();
        assertEquals(expected, actual);

        expected = ProductCategory.VEGETABLES;
        product.setCategory(expected);
        actual = product.getCategory();
        assertEquals(expected, actual);
    }

    @Test
    public void getDiscount() {
        final BigDecimal actual = product.getDiscount();
        assertEquals(discount, actual);
    }

    @Test
    public void setDiscount() {
        BigDecimal expected = new BigDecimal("0.15");
        product.setDiscount(expected);
        BigDecimal actual = product.getDiscount();
        assertEquals(expected, actual);

        expected = this.discount;
        product.setDiscount(expected);
        actual = product.getDiscount();
        assertEquals(expected, actual);
    }

    @Test
    public void getPrice() {
        BigDecimal actual = product.getPrice();
        assertEquals(price, actual);
    }

    @Test
    public void setPrice() {
        BigDecimal expected = new BigDecimal("9.15");
        product.setPrice(expected);
        BigDecimal actual = product.getPrice();
        assertEquals(expected, actual);

        product.setPrice(price);
        actual = product.getPrice();
        assertEquals(price, actual);
    }

    @Test
    public void hasDiscount() {
        boolean expected = false;
        final BigDecimal noDiscount = new BigDecimal("0.00");

        product.setDiscount(noDiscount);
        boolean actual = product.hasDiscount();
        assertEquals(expected, actual);

        expected = true;
        product.setDiscount(this.discount);
        actual = product.hasDiscount();
        assertEquals(expected, actual);
    }

    @Test
    public void getActualPrice() {
        BigDecimal expected = price.subtract(price.multiply(discount));
        BigDecimal actual = product.getActualPrice();

        assertEquals(expected, actual);
    }

    @Test
    public void getDiscountPercent() {
        String expected =  discount.multiply(new BigDecimal(100)).intValue() + "%";
        String actual = product.getDiscountPercent();

        assertEquals(expected, actual);
    }
}