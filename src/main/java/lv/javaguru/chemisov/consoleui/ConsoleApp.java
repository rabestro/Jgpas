package lv.javaguru.chemisov.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ConsoleApp {
    String readLine() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            line = "";
        }
        return line;
    }

    String readLine(String prompt) {
        System.out.println(prompt);
        return readLine();
    }

    void pauseExecution() {
        printMessage("Press Enter to Continue... ");
        readLine();
    }

    void printError(String error) {
        System.out.println("Error: " + error);
        pauseExecution();
    }

    void printMessage(String text) {
        System.out.println(text);
    }

    void printFormat(String format, Object ... args) {
        System.out.format(format, args);
    }
}
