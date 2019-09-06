package lv.javaguru.chemisov;

public enum ProductCategory {
    VEGETABLES("Vegetables"),
    FRUITS("Fruits"),
    GRAINS("Grains, Beans and Nuts"),
    MEAT("Meat and Poultry"),
    FISH("Fish and Seafood"),
    DAIRY("Dairy Foods");

    private String title;

    ProductCategory(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
