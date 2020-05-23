package ooad.Database;

public interface Menu {
    void runMenu();
    void printHeader();
    void printMenu();
    int getMenuChoice();
    void performAction(int choice);
    void displayHeader(String message);
}
