package Sevices;

import models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsumableService {
    private static final Scanner scanner = new Scanner(System.in);
    public static final ArrayList<Book> bookConsumables = new ArrayList<>();
    public static final ArrayList<Series> seriesConsumables = new ArrayList<>();
    public static final ArrayList<Movie> movieConsumables = new ArrayList<>();
    public static final OverAllInfo overAllInfo = new OverAllInfo();

    public void addConsumable() {
        new AddService().addConsumable();
    }

    public void editConsumable() {
        new EditService().editConsumable();
    }


    public void deleteConsumable() {
        new DeleteService().deleteConsumable();
    }

    public void showConsumables() {
        new ShowService().showConsumables();
    }

    public void showOverallInfo() {
        new ShowService().showOverallInfo();
    }

    public static ConsumableType selectConsumableType() {
        System.out.print("\nChoose a type by entering initial number. \n" +
                "1. Book\n2. Series\n3. Movie\n");
        System.out.print("Enter Consumable type: ");
        int type = scanner.nextInt();
        if (type == 1) return ConsumableType.BOOK;
        else if (type == 2) return ConsumableType.SERIES;
        else if (type == 3) return ConsumableType.MOVIE;
        return null;
    }

    public static int selectSerNo() {
        System.out.print("\nEnter any serial no (enter 0 to exit): ");
        return scanner.nextInt();
    }

    public static boolean checkIsDataAvailable(ConsumableType type) {
        if (type == ConsumableType.BOOK) return bookConsumables.size() > 0;
        else if (type == ConsumableType.SERIES) return seriesConsumables.size() > 0;
        else if (type == ConsumableType.MOVIE) return movieConsumables.size() > 0;
        return false;
    }

    public static boolean isValidTye(ConsumableType type) {
        return type == ConsumableType.BOOK || type == ConsumableType.SERIES || type == ConsumableType.MOVIE;
    }
}
