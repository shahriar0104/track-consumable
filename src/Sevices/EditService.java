package Sevices;

import models.Book;
import models.Movie;
import models.OverAllInfo;
import models.Series;

import java.util.ArrayList;
import java.util.Scanner;

import static Sevices.ConsumableService.*;

public class EditService {
    private final Scanner scanner = new Scanner(System.in);
    public ArrayList<Book> bookConsumables = ConsumableService.bookConsumables;
    public ArrayList<Series> seriesConsumables = ConsumableService.seriesConsumables;
    public ArrayList<Movie> movieConsumables = ConsumableService.movieConsumables;
    public OverAllInfo overAllInfo = ConsumableService.overAllInfo;

    // Edit PORTION
    public void editConsumable() {
        ConsumableType type = new ShowService().listOfConsumables();
        if (type == null) {
            editConsumable();
        } else if (isValidTye(type)) {
            if (checkIsDataAvailable(type)) {
                if (type == ConsumableType.BOOK) selectBookEditIndex(type);
                else if (type == ConsumableType.SERIES) selectSeriesEditIndex(type);
                else if (type == ConsumableType.MOVIE) selectMovieEditIndex(type);
            }
        }
    }

    private void selectBookEditIndex(ConsumableType type) {
        int number = selectSerNo();
        if (number != 0) {
            if (number > 0 && number <= bookConsumables.size()) {
                if (bookConsumables.get(number - 1).isEditable()) {
                    doEdit(type, number - 1);

                } else {
                    System.out.println("This Book is not editable. Please select another");
                    selectBookEditIndex(type);
                }
            } else {
                System.out.println("Invalid Selection.");
                selectBookEditIndex(type);
            }
        }
    }

    private void selectSeriesEditIndex(ConsumableType type) {
        int number = selectSerNo();
        if (number != 0) {
            if (number > 0 && number <= seriesConsumables.size()) {
                if (seriesConsumables.get(number - 1).isEditable()) {
                    doEdit(type, number - 1);

                } else {
                    System.out.println("This Series is not editable. Please select another");
                    selectSeriesEditIndex(type);
                }
            } else {
                System.out.println("Invalid Selection.");
                selectSeriesEditIndex(type);
            }
        }
    }

    private void selectMovieEditIndex(ConsumableType type) {
        int number = selectSerNo();
        if (number != 0) {
            if (number > 0 && number <= movieConsumables.size()) {
                if (movieConsumables.get(number - 1).isEditable()) {
                    doEdit(type, number - 1);

                } else {
                    System.out.println("This Movie is not editable. Please select another");
                    selectMovieEditIndex(type);
                }
            } else {
                System.out.println("Invalid Selection.");
                selectMovieEditIndex(type);
            }
        }
    }

    public void doEdit(ConsumableType type, int index) {
        boolean flag = true;
        while (flag) {
            System.out.print("\n" +
                    "1. Edit consumption time \n" +
                    "2. Edit rating \n" +
                    "3. Edit consumption ending date \n" +
                    "0. Exit \n");
            System.out.println("Enter a number: ");
            int inputNumber = scanner.nextInt();
            scanner.nextLine();
            switch (inputNumber) {
                case 1:
                    System.out.println("Enter consumption time in hrs to add: ");
                    String conTimeInString = scanner.nextLine();
                    if (!conTimeInString.isEmpty()) {
                        if (type == ConsumableType.BOOK) {
                            bookConsumables.get(index).setTotalConsumptionTime(bookConsumables.get(index).getTotalConsumptionTime() +
                                    Integer.parseInt(conTimeInString));
                            overAllInfo.setBookSumOfConsumptionTime(overAllInfo.getBookSumOfConsumptionTime() + Integer.parseInt(conTimeInString));
                        } else if (type == ConsumableType.SERIES) {
                            seriesConsumables.get(index).setTotalConsumptionTime(seriesConsumables.get(index).getTotalConsumptionTime() +
                                    Integer.parseInt(conTimeInString));
                            overAllInfo.setSeriesSumOfConsumptionTime(overAllInfo.getSeriesSumOfConsumptionTime() + Integer.parseInt(conTimeInString));
                        } else if (type == ConsumableType.MOVIE) {
                            movieConsumables.get(index).setTotalConsumptionTime(movieConsumables.get(index).getTotalConsumptionTime() +
                                    Integer.parseInt(conTimeInString));
                            overAllInfo.setMovieSumOfConsumptionTime(overAllInfo.getMovieSumOfConsumptionTime() + Integer.parseInt(conTimeInString));
                        }
                    }

                    System.out.println("Enter days of consumption to add: ");
                    String conDaysInString = scanner.nextLine();
                    if (!conDaysInString.isEmpty()) {
                        if (type == ConsumableType.BOOK) {
                            bookConsumables.get(index).setTotalConsumptionDays(bookConsumables.get(index).getTotalConsumptionDays() +
                                    Integer.parseInt(conDaysInString));
                            overAllInfo.setBookSumOfConsumptionDays(overAllInfo.getBookSumOfConsumptionDays() + Integer.parseInt(conDaysInString));
                        } else if (type == ConsumableType.SERIES) {
                            seriesConsumables.get(index).setTotalConsumptionDays(seriesConsumables.get(index).getTotalConsumptionDays() +
                                    Integer.parseInt(conDaysInString));
                            overAllInfo.setSeriesSumOfConsumptionDays(overAllInfo.getSeriesSumOfConsumptionDays() + Integer.parseInt(conDaysInString));
                        } else if (type == ConsumableType.MOVIE) {
                            movieConsumables.get(index).setTotalConsumptionDays(movieConsumables.get(index).getTotalConsumptionDays() +
                                    Integer.parseInt(conDaysInString));
                            overAllInfo.setMovieSumOfConsumptionDays(overAllInfo.getMovieSumOfConsumptionDays() + Integer.parseInt(conDaysInString));
                        }
                    }

                    continue;

                case 2:
                    System.out.println("Enter rating in format x.yz out of 10 to edit: ");
                    String ratingInString = scanner.nextLine();
                    if (ratingInString.isEmpty()) {
                        if (type == ConsumableType.BOOK && !bookConsumables.get(index).getRating().isEmpty()) {
                            overAllInfo.setBookSumRating(overAllInfo.getBookSumRating() - Double.parseDouble(bookConsumables.get(index).getRating()));
                            overAllInfo.setBookAvgRating(overAllInfo.getBookSumRating() / bookConsumables.size());
                            bookConsumables.get(index).setRating("");

                        } else if (type == ConsumableType.SERIES && !seriesConsumables.get(index).getRating().isEmpty()) {
                            overAllInfo.setSeriesSumRating(overAllInfo.getSeriesSumRating() - Double.parseDouble(seriesConsumables.get(index).getRating()));
                            overAllInfo.setSeriesAvgRating(overAllInfo.getSeriesSumRating() / seriesConsumables.size());
                            seriesConsumables.get(index).setRating("");

                        } else if (type == ConsumableType.MOVIE && !movieConsumables.get(index).getRating().isEmpty()) {
                            overAllInfo.setMovieSumRating(overAllInfo.getMovieSumRating() - Double.parseDouble(movieConsumables.get(index).getRating()));
                            overAllInfo.setMovieAvgRating(overAllInfo.getMovieSumRating() / movieConsumables.size());
                            movieConsumables.get(index).setRating("");
                        }

                    } else {
                        if (type == ConsumableType.BOOK) {
                            bookConsumables.get(index).setRating(ratingInString);
                            overAllInfo.setBookSumRating(overAllInfo.getBookSumRating() + Double.parseDouble(bookConsumables.get(index).getRating()));
                            overAllInfo.setBookAvgRating(overAllInfo.getBookSumRating() / bookConsumables.size());
                        } else if (type == ConsumableType.SERIES) {
                            seriesConsumables.get(index).setRating(ratingInString);
                            overAllInfo.setSeriesSumRating(overAllInfo.getSeriesSumRating() + Double.parseDouble(seriesConsumables.get(index).getRating()));
                            overAllInfo.setSeriesAvgRating(overAllInfo.getSeriesSumRating() / seriesConsumables.size());
                        } else if (type == ConsumableType.MOVIE) {
                            movieConsumables.get(index).setRating(ratingInString);
                            overAllInfo.setMovieSumRating(overAllInfo.getMovieSumRating() + Double.parseDouble(movieConsumables.get(index).getRating()));
                            overAllInfo.setMovieAvgRating(overAllInfo.getMovieSumRating() / movieConsumables.size());
                        }
                    }
                    continue;

                case 3:
                    System.out.println("Enter consumption ending date in format YYYY-MM-DD : ");
                    if (type == ConsumableType.BOOK) {
                        bookConsumables.get(index).setConsumptionEndDate(scanner.nextLine());
                        bookConsumables.get(index).setEditable(false);
                    } else if (type == ConsumableType.SERIES) {
                        seriesConsumables.get(index).setConsumptionEndDate(scanner.nextLine());
                        seriesConsumables.get(index).setEditable(false);
                    } else if (type == ConsumableType.MOVIE) {
                        movieConsumables.get(index).setConsumptionEndDate(scanner.nextLine());
                        movieConsumables.get(index).setEditable(false);
                    }
                    flag = false;
                    break;

                case 0:
                    System.out.println("Exiting from system...");
                    flag = false;
                    break;

                default:
                    System.out.println("No such choice available" + "\n");
            }
        }
    }
    // Edit PORTION

}
