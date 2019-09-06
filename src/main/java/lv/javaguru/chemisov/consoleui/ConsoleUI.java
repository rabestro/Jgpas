package lv.javaguru.chemisov.consoleui;

public class ConsoleUI extends ConsoleApp {

    public void printWelcome() {
        printMessage("Welcome to the JavaGuru Product Accounting System!");
    }
    public void printGoodBye() {
        printMessage("Thank you for using our system!");
    }

    public void runMenu() {
        ConsoleMenu menu = new ConsoleMenu();
        final ProductApp app = new ProductApp();

        menu.addEntry(new ConsoleMenuEntry("Add a new product") {
            @Override
            public void run() {
                app.productAdd();
            }
        }).addEntry(new ConsoleMenuEntry("Categories of products") {
            @Override
            public void run() {
                app.showCategories();
            }
        }).addEntry(new ConsoleMenuEntry("List of all products.") {
            @Override
            public void run() {
                app.productList();
            }
        }).addEntry(new ConsoleMenuEntry("Print a product by id") {
            @Override
            public void run() {
                app.productPrint();
            }
        }).addEntry(new ConsoleMenuEntry("Delete a product by id") {
            @Override
            public void run() {
                app.productDelete();
            }
        });

        menu.run();
    }
}
