package lv.javaguru.chemisov.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleMenu extends ConsoleApp {
    private static final String MENU_PATTERN = "%s - %s\n";
    private List<ConsoleMenuEntry> entries = new ArrayList<>();
    private boolean isExit = false;

    public ConsoleMenu() {
        entries.add(new ConsoleMenuEntry("Exit") {
            @Override
            public void run() {
                isExit = true;
            }
        });
    }

    public void run() {
        while (!isExit) {
            printMenu();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String line = reader.readLine();
                int choice = Integer.parseInt(line);
                ConsoleMenuEntry entry = entries.get(choice - 1);
                entry.run();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                printError("Error: Please enter the number.");
            } catch (IndexOutOfBoundsException e) {
                printError("Error: The number is out of range.");
            }
        }
    }

    public ConsoleMenu addEntry(ConsoleMenuEntry entry) {
        int index = entries.size() - 1;
        entries.add(index, entry);
        return this;
    }

    private void printMenu() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("\nMenu:\n");
        for (int i = 0; i < entries.size(); i++) {
            ConsoleMenuEntry entry = entries.get(i);
            String entryFormatted = String.format(MENU_PATTERN, (i + 1), entry.getTitle());
            buffer.append(entryFormatted);
        }
        System.out.print(buffer.toString());
    }
}
