package pl.com.bottega.photostock.sales.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    
    private List<MenuItem> items = new ArrayList<>();
    private String lastItemLabel;
    private Scanner scanner;
    private String titleLabel;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void show() {
        while (true) {
            showTitle();
            showItems();
            showLastItem();
            int decision = getUserDecision();
            if (wantsToQuit(decision)) {
                return;
            }
            processMethod(decision);
        }
    }

    private void processMethod(int decision) {
        if (decision >= 1 && decision <= items.size() + 1) {
            MenuItem menuItem = items.get(decision - 1);
            menuItem.executeAction();
        } else {
            System.out.println("Nie rozumiem.");
        }
    }

    private boolean wantsToQuit(int decision) {
        return decision == items.size() + 1;
    }

    private void showTitle() {
        if (titleLabel == null) {
            System.out.println(titleLabel);
        }
    }

    private void showLastItem() {
        System.out.println(String.format("%d. %s", items.size() + 1, lastItemLabel));
    }

    private void showItems() {
        for (MenuItem menuItem : items) {
            menuItem.show();
        }
    }

    public void setLastItemLabel(String label) {
        lastItemLabel = label;
    }

    public void addItem(String label, Runnable action) {
        items.add(new MenuItem(items.size() + 1, label, action));
    }

    public int getUserDecision() {
        System.out.print("Co chcesz zrobić?");
        String userInput = scanner.nextLine();
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    public void setTitleLabel(String titleLabel) {
        this.titleLabel = titleLabel;
    }
}
