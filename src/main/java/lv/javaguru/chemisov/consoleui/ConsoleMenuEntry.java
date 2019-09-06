package lv.javaguru.chemisov.consoleui;

public abstract class ConsoleMenuEntry {
    private String title;

    public ConsoleMenuEntry(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract void run();
}
