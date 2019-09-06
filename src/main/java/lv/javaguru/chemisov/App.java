package lv.javaguru.chemisov;

import lv.javaguru.chemisov.consoleui.ConsoleUI;

public class App
{
    public static void main( String[] args )
    {
        ProductDatabase database = ProductDatabase.getInstance();
        database.start();

        ConsoleUI ui = new ConsoleUI();

        ui.printWelcome();
        ui.runMenu();
        ui.printGoodBye();

        database.shutdown();
    }
}
