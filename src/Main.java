import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What is your PESEL number?");
        Pesel yourPesel = new Pesel(keyboard.nextLine());
        System.out.println("Repeat your PESEL number.");
        Pesel repeatPesel = new Pesel(keyboard.nextLine());
        yourPesel.compare(repeatPesel);
        Pesel.check(yourPesel);
    }
}