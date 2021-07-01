package Sevices;

import models.Book;
import models.Movie;
import models.OverAllInfo;
import models.Series;

import java.util.ArrayList;
import java.util.Scanner;

import static Sevices.ConsumableService.*;

public class DeleteService {

    private final Scanner scanner = new Scanner(System.in);
    public ArrayList<Book> bookConsumables = ConsumableService.bookConsumables;
    public ArrayList<Series> seriesConsumables = ConsumableService.seriesConsumables;
    public ArrayList<Movie> movieConsumables = ConsumableService.movieConsumables;
    public OverAllInfo overAllInfo = ConsumableService.overAllInfo;

    // Delete PORTION
    public void deleteConsumable() {
        ConsumableType type = new ShowService().listOfConsumables();
        if (type == null) {
            deleteConsumable();
        } else if (isValidTye(type)) {
            if (checkIsDataAvailable(type)) {
                if (type == ConsumableType.BOOK) deleteBook();
                else if (type == ConsumableType.SERIES) deleteSeries();
                else if (type == ConsumableType.MOVIE) deleteMovie();
            }
        }
    }

    private void deleteBook() {
        int number = selectSerNo();
        if (number != 0) {
            if (number > 0 && number <= bookConsumables.size()) {
                String rating = bookConsumables.get(number - 1).getRating();

                if (!rating.equals("")) {
                    overAllInfo.setBookSumRating(overAllInfo.getBookSumRating() - Double.parseDouble(rating));
                    overAllInfo.setBookAvgRating(overAllInfo.getBookSumRating() / bookConsumables.size());
                }

                bookConsumables.remove(number - 1);
                System.out.println("book deleted successfully\n");
            } else {
                System.out.print("Invalid selection.");
                deleteBook();
            }
        }
    }

    private void deleteSeries() {
        int number = selectSerNo();
        if (number != 0) {
            if (number > 0 && number <= seriesConsumables.size()) {
                String rating = seriesConsumables.get(number - 1).getRating();

                if (!rating.equals("")) {
                    overAllInfo.setSeriesSumRating(overAllInfo.getSeriesSumRating() - Double.parseDouble(rating));
                    overAllInfo.setSeriesAvgRating(overAllInfo.getSeriesSumRating() / seriesConsumables.size());
                }

                seriesConsumables.remove(number - 1);
                System.out.println("series deleted successfully\n");
            } else {
                System.out.print("Invalid selection.");
                deleteSeries();
            }
        }
    }

    private void deleteMovie() {
        int number = selectSerNo();
        if (number != 0) {
            if (number > 0 && number <= movieConsumables.size()) {
                String rating = movieConsumables.get(number - 1).getRating();

                if (!rating.equals("")) {
                    overAllInfo.setMovieSumRating(overAllInfo.getMovieSumRating() - Double.parseDouble(rating));
                    overAllInfo.setMovieAvgRating(overAllInfo.getMovieSumRating() / movieConsumables.size());
                }

                movieConsumables.remove(number - 1);
                System.out.println("movie deleted successfully\n");
            } else {
                System.out.print("Invalid selection.");
                deleteMovie();
            }
        }
    }
    // Delete PORTION
}
