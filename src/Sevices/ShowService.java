package Sevices;

import models.Book;
import models.Movie;
import models.OverAllInfo;
import models.Series;

import java.util.ArrayList;
import java.util.Scanner;

import static Sevices.ConsumableService.*;

public class ShowService {

    private final Scanner scanner = new Scanner(System.in);
    public ArrayList<Book> bookConsumables = ConsumableService.bookConsumables;
    public ArrayList<Series> seriesConsumables = ConsumableService.seriesConsumables;
    public ArrayList<Movie> movieConsumables = ConsumableService.movieConsumables;
    public OverAllInfo overAllInfo = ConsumableService.overAllInfo;

    // show PORTION
    public void showConsumables() {
        ConsumableType type = listOfConsumables();
        if (type == null) {
            showConsumables();
        } else if (isValidTye(type)) {
            if (checkIsDataAvailable(type)) {
                System.out.print("\npick any serial no & see the full details (enter 0 to exit): ");
                showIndividualConsumable(type);
            }
        }
    }

    public void showIndividualConsumable(ConsumableType type) {
        int number = scanner.nextInt();
        if (number != 0) {
            if (type == ConsumableType.BOOK) {
                if (number > 0 && number <= bookConsumables.size()) printBook(number - 1);
                else chooseValidNumber(type);
            } else if (type == ConsumableType.SERIES) {
                if (number > 0 && number <= seriesConsumables.size()) printSeries(number - 1);
                else chooseValidNumber(type);
            } else if (type == ConsumableType.MOVIE) {
                if (number > 0 && number <= movieConsumables.size()) printMovie(number - 1);
                else chooseValidNumber(type);
            }
        }
    }

    private void chooseValidNumber(ConsumableType type) {
        System.out.println("choose a valid number (enter 0 to exit): ");
        showIndividualConsumable(type);
    }

    private void printBook(int index) {
        String leftAlignFormat = printIndividualHeader();
        Book book = bookConsumables.get(index);
        System.out.format(leftAlignFormat, book.getName(), book.getConsumptionStartDate(), book.getConsumptionEndDate(),
                book.getTotalConsumptionTime(), book.getTotalConsumptionDays(), book.getRating());
        printIndividualFooter();
    }

    private void printSeries(int index) {
        String leftAlignFormat = printIndividualHeader();
        Series series = seriesConsumables.get(index);
        System.out.format(leftAlignFormat, series.getName(), series.getConsumptionStartDate(), series.getConsumptionEndDate(),
                series.getTotalConsumptionTime(), series.getTotalConsumptionDays(), series.getRating());
        printIndividualFooter();
    }

    private void printMovie(int index) {
        String leftAlignFormat = printIndividualHeader();
        Movie movie = movieConsumables.get(index);
        System.out.format(leftAlignFormat, movie.getName(), movie.getConsumptionStartDate(), movie.getConsumptionEndDate(),
                movie.getTotalConsumptionTime(), movie.getTotalConsumptionDays(), movie.getRating());
        printIndividualFooter();
    }

    private String printIndividualHeader() {
        String leftAlignFormat = "| %-20s | %-22s | %-22s | %-20d | %-19d | %-7s |%n";

        System.out.format("+----------------------+------------------------+------------------------+----------------------+---------------------+---------+%n");
        System.out.format("|          Name        | Consumption Start Date | Consumption Start Date | Hours Of Consumption | Days Of Consumption | Rating  |%n");
        System.out.format("+----------------------+------------------------+------------------------+----------------------+---------------------+---------+%n");

        return leftAlignFormat;
    }

    private void printIndividualFooter() {
        System.out.format("+----------------------+------------------------+------------------------+----------------------+---------------------+---------+%n");
    }

    public ConsumableType listOfConsumables() {
        ConsumableType type = selectConsumableType();
        if (isValidTye(type)) {
            if (type == ConsumableType.BOOK) printListOfBook();
            else if (type == ConsumableType.SERIES) printListOfSeries();
            else if (type == ConsumableType.MOVIE) printListOfMovie();
            return type;
        } else {
            System.out.println("Wrong selection. Please choose again\n");
            return null;
        }
    }

    private void printListOfBook() {
        if (bookConsumables.size() > 0) {

            String leftAlignFormat = printListOfConsumableHeader();
            for (int i = 0; i < bookConsumables.size(); i++) {
                Book book = bookConsumables.get(i);
                System.out.format(leftAlignFormat, (i + 1), book.getName(), book.getTotalConsumptionTime(),
                        book.getTotalConsumptionDays(), book.getRating());
            }
            printListOfConsumableFooter();

        } else System.out.println("No consumable found under BOOK");
    }

    private void printListOfSeries() {
        if (seriesConsumables.size() > 0) {

            String leftAlignFormat = printListOfConsumableHeader();
            for (int i = 0; i < seriesConsumables.size(); i++) {
                Series series = seriesConsumables.get(i);
                System.out.format(leftAlignFormat, (i + 1), series.getName(), series.getTotalConsumptionTime(),
                        series.getTotalConsumptionDays(), series.getRating());
            }
            printListOfConsumableFooter();

        } else System.out.println("No consumable found under SERIES");
    }

    private void printListOfMovie() {
        if (movieConsumables.size() > 0) {

            String leftAlignFormat = printListOfConsumableHeader();
            for (int i = 0; i < movieConsumables.size(); i++) {
                Movie movie = movieConsumables.get(i);
                System.out.format(leftAlignFormat, (i + 1), movie.getName(), movie.getTotalConsumptionTime(),
                        movie.getTotalConsumptionDays(), movie.getRating());
            }
            printListOfConsumableFooter();

        } else System.out.println("No consumable found under MOVIE");
    }

    private String printListOfConsumableHeader () {
        String leftAlignFormat = "| %-9d | %-20s | %-20d | %-19d | %-7s |%n";

        System.out.format("+-----------+----------------------+----------------------+---------------------+---------+%n");
        System.out.format("| Serial No |          Name        | Hours Of Consumption | Days Of Consumption | Rating  |%n");
        System.out.format("+-----------+----------------------+----------------------+---------------------+---------+%n");

        return leftAlignFormat;
    }

    private void printListOfConsumableFooter () {
        System.out.format("+-----------+----------------------+----------------------+---------------------+---------+%n");
    }

    public void showOverallInfo() {
        System.out.print("\n" +
                "1. Total consumption time in hours across all types \n" +
                "2. Individual consumption time in hours of each type \n" +
                "3. Total days of consumption across all types \n" +
                "4. Individual days of consumption of each type \n" +
                "5. Average rating across all types \n" +
                "6. Average individual rating of each type \n" +
                "7. Total number of consumable across all types \n" +
                "8. Individual number of consumable of each type \n" +
                "0. Exit \n");

        boolean flag = true;
        while (flag) {
            System.out.print("choose a number: ");
            int inputNumber = scanner.nextInt();
            switch (inputNumber) {
                case 1:
                    System.out.println("Total consumption time in hours across all types: " +
                            (overAllInfo.getBookSumOfConsumptionTime() +
                                    overAllInfo.getSeriesSumOfConsumptionTime() +
                                    overAllInfo.getMovieSumOfConsumptionTime()));
                    continue;

                case 2:
                    System.out.println("Consumption time in hours of Book: " + overAllInfo.getBookSumOfConsumptionTime());
                    System.out.println("Consumption time in hours of Series: " + overAllInfo.getSeriesSumOfConsumptionTime());
                    System.out.println("Consumption time in hours of Movie: " + overAllInfo.getMovieSumOfConsumptionTime());
                    continue;

                case 3:
                    System.out.println("Total days of consumption across all types: " +
                            (overAllInfo.getBookSumOfConsumptionDays() +
                                    overAllInfo.getSeriesSumOfConsumptionDays() +
                                    overAllInfo.getMovieSumOfConsumptionDays()));
                    continue;

                case 4:
                    System.out.println("days of consumption of Book: " + overAllInfo.getBookSumOfConsumptionDays());
                    System.out.println("days of consumption of Series: " + overAllInfo.getSeriesSumOfConsumptionDays());
                    System.out.println("days of consumption of Movie: " + overAllInfo.getMovieSumOfConsumptionDays());
                    continue;

                case 5:
                    System.out.println("Average rating across all types: " +
                            (overAllInfo.getBookAvgRating() +
                                    overAllInfo.getSeriesAvgRating() +
                                    overAllInfo.getMovieAvgRating()) / 3);
                    continue;

                case 6:
                    System.out.println("average rating of Book: " + overAllInfo.getBookAvgRating());
                    System.out.println("average rating of Series: " + overAllInfo.getSeriesAvgRating());
                    System.out.println("average rating of Movie: " + overAllInfo.getMovieAvgRating());
                    continue;

                case 7:
                    System.out.println("Total number of consumable across all types: " +
                            (bookConsumables.size() +
                                    seriesConsumables.size() +
                                    movieConsumables.size()));
                    continue;

                case 8:
                    System.out.println("number of consumable of Book: " + bookConsumables.size());
                    System.out.println("number of consumable of Series: " + seriesConsumables.size());
                    System.out.println("number of consumable of Movie: " + movieConsumables.size());
                    continue;

                case 0:
                    System.out.println("Exiting from overall info...");
                    flag = false;
                    break;

                default:
                    System.out.println("Invalid selection" + "\n");
            }
        }
    }
    // show PORTION
}
