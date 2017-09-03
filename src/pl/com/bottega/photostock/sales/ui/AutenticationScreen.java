package pl.com.bottega.photostock.sales.ui;

import java.util.Scanner;

public class AutenticationScreen {
    private Scanner scanner;
    private AutenticationMaganger autenticationMaganger;

    public AutenticationScreen(Scanner scanner, AutenticationMaganger autenticationMaganger) {
        this.scanner = scanner;
        this.autenticationMaganger = autenticationMaganger;
    }

    public void show() {
        while (true) {
            System.out.print("Podaj login: ");
            String login = scanner.nextLine();
            if (autenticationMaganger.authenticate(login)) {
                return;
            } else {
                System.out.println("Nieprawidłowy login.");
            }
        }
    }
}
