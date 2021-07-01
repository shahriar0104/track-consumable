package Sevices;

import models.Book;
import models.Movie;
import models.OverAllInfo;
import models.Series;

import java.util.ArrayList;
import java.util.Scanner;

import static Sevices.ConsumableService.*;

public class AddService {
    private final Scanner scanner = new Scanner(System.in);

    // ADD PORTION
    public void addConsumable() {
        ConsumableType type = selectConsumableType();
        if (isValidTye(type)) {

            if (type == ConsumableType.BOOK) {
                Book book = new Book();

                book.setName(addName());
                book.setConsumptionStartDate(addConsumptionStartDate());
                book.setConsumptionEndDate(addConsumptionEndDate());
                book.setTotalConsumptionTime(addConsumptionInHrs());
                book.setRating(addRating());
                book.setTotalConsumptionDays(addConsumptionInDays());

                overAllInfo.setBookSumOfConsumptionTime(overAllInfo.getBookSumOfConsumptionTime() + book.getTotalConsumptionTime());
                overAllInfo.setBookSumOfConsumptionDays(overAllInfo.getBookSumOfConsumptionDays() + book.getTotalConsumptionDays());
                if (!book.getRating().equals("")) {
                    overAllInfo.setBookSumRating(overAllInfo.getBookSumRating() + Double.parseDouble(book.getRating()));
                    overAllInfo.setBookAvgRating(overAllInfo.getBookSumRating() / bookConsumables.size());
                }

                book.setEditable(true);
                bookConsumables.add(book);

            } else if (type == ConsumableType.SERIES) {
                Series series = new Series();

                series.setName(addName());
                series.setConsumptionStartDate(addConsumptionStartDate());
                series.setConsumptionEndDate(addConsumptionEndDate());
                series.setTotalConsumptionTime(addConsumptionInHrs());
                series.setRating(addRating());
                series.setTotalConsumptionDays(addConsumptionInDays());

                overAllInfo.setSeriesSumOfConsumptionTime(overAllInfo.getSeriesSumOfConsumptionTime() + series.getTotalConsumptionTime());
                overAllInfo.setSeriesSumOfConsumptionDays(overAllInfo.getSeriesSumOfConsumptionDays() + series.getTotalConsumptionDays());
                if (!series.getRating().equals("")) {
                    overAllInfo.setSeriesSumRating(overAllInfo.getSeriesSumRating() + Double.parseDouble(series.getRating()));
                    overAllInfo.setSeriesAvgRating(overAllInfo.getSeriesSumRating() / seriesConsumables.size());
                }

                series.setEditable(true);
                seriesConsumables.add(series);

            } else if (type == ConsumableType.MOVIE) {
                Movie movie = new Movie();

                movie.setName(addName());
                movie.setConsumptionStartDate(addConsumptionStartDate());
                movie.setConsumptionEndDate(addConsumptionEndDate());
                movie.setTotalConsumptionTime(addConsumptionInHrs());
                movie.setRating(addRating());
                movie.setTotalConsumptionDays(addConsumptionInDays());

                overAllInfo.setMovieSumOfConsumptionTime(overAllInfo.getMovieSumOfConsumptionTime() + movie.getTotalConsumptionTime());
                overAllInfo.setMovieSumOfConsumptionDays(overAllInfo.getMovieSumOfConsumptionDays() + movie.getTotalConsumptionDays());
                if (!movie.getRating().equals("")) {
                    overAllInfo.setMovieSumRating(overAllInfo.getMovieSumRating() + Double.parseDouble(movie.getRating()));
                    overAllInfo.setMovieAvgRating(overAllInfo.getMovieSumRating() / movieConsumables.size());
                }

                movie.setEditable(true);
                movieConsumables.add(movie);
            }

        } else {
            System.out.println("Wrong selection. Please choose again\n");
            addConsumable();
        }
    }

    private String addName() {
        System.out.print("\nEnter Name: ");
        return scanner.nextLine();
    }

    private String addConsumptionStartDate() {
        System.out.print("Enter consumption starting date in format YYYY-MM-DD (could be blank): ");
        return scanner.nextLine();
    }

    private String addConsumptionEndDate() {
        System.out.print("Enter consumption ending date in format YYYY-MM-DD (could be blank): ");
        return scanner.nextLine();
    }

    private int addConsumptionInHrs() {
        System.out.print("Enter Total consumption time in hours: ");
        String conTimeInString = scanner.nextLine();
        if (conTimeInString.equals("") || conTimeInString.equals(" ")) return 0;
        else return Integer.parseInt(conTimeInString);
    }

    private String addRating() {
        System.out.print("Enter rating in format x.yz out of 10 (could be blank): ");
        String ratingInString = scanner.nextLine();
        if (ratingInString.equals("") || ratingInString.equals(" ")) return "";
        else return ratingInString;
    }

    private int addConsumptionInDays() {
        System.out.print("Enter Total days of consumption: ");
        String conDaysInString = scanner.nextLine();
        if (conDaysInString.equals("") || conDaysInString.equals(" ")) return 0;
        else return Integer.parseInt(conDaysInString);
    }
    // ADD PORTION
}
