import models.Consumable;
import models.OverAllInfo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Consumable> bookConsumables = new ArrayList<>();
    private static final ArrayList<Consumable> seriesConsumables = new ArrayList<>();
    private static final ArrayList<Consumable> movieConsumables = new ArrayList<>();
    private static final OverAllInfo overAllInfo = new OverAllInfo();

    private static int selectConsumableType() {
        System.out.print("Choose a type by entering initial number. \n" +
                "1. Book\n2. Series\n3. Movie\n");
        return scanner.nextInt();
    }

    private static void addConsumable() {
        int type = selectConsumableType();
        if (isValidTye(type)) {
            Consumable consumable = new Consumable();
            System.out.println("Enter Name: ");
            scanner.nextLine();
            consumable.setName(scanner.nextLine());

            System.out.println("Enter consumption starting date in format YYYY-MM-DD (could be blank): ");
            consumable.setConsumptionStartDate(scanner.nextLine());
            System.out.println("Enter consumption ending date in format YYYY-MM-DD (could be blank): ");
            consumable.setConsumptionEndDate(scanner.nextLine());

            System.out.println("Enter Total consumption time in hours: ");
            String conTimeInString = scanner.nextLine();
            if (conTimeInString.equals("") || conTimeInString.equals(" ")) consumable.setTotalConsumptionTime(0);
            else consumable.setTotalConsumptionTime(Integer.parseInt(conTimeInString));

            System.out.println("Enter rating in format x.yz (could be blank): ");
            String ratingInString = scanner.nextLine();
            if (ratingInString.equals("") || ratingInString.equals(" ")) consumable.setRating("");
            else consumable.setRating(ratingInString);

            System.out.println("Enter Total days of consumption: ");
            String conDaysInString = scanner.nextLine();
            if (conDaysInString.equals("") || conDaysInString.equals(" ")) consumable.setTotalConsumptionDays(0);
            else consumable.setTotalConsumptionDays(Integer.parseInt(conDaysInString));

            consumable.setEditable(true);
            consumable.setConsumableType(type);

            if (type == 1) {
                overAllInfo.setBookSumOfConsumptionTime(overAllInfo.getBookSumOfConsumptionTime() + consumable.getTotalConsumptionTime());
                overAllInfo.setBookSumOfConsumptionDays(overAllInfo.getBookSumOfConsumptionDays() + consumable.getTotalConsumptionDays());
                if (!consumable.getRating().equals("")) {
                    overAllInfo.setBookSumRating(overAllInfo.getBookSumRating() + Double.parseDouble(consumable.getRating()));
                    overAllInfo.setBookAvgRating(overAllInfo.getBookSumRating() / bookConsumables.size());
                }
                bookConsumables.add(consumable);
            } else if (type == 2) {
                overAllInfo.setSeriesSumOfConsumptionTime(overAllInfo.getSeriesSumOfConsumptionTime() + consumable.getTotalConsumptionTime());
                overAllInfo.setSeriesSumOfConsumptionDays(overAllInfo.getSeriesSumOfConsumptionDays() + consumable.getTotalConsumptionDays());
                if (!consumable.getRating().equals("")) {
                    overAllInfo.setSeriesSumRating(overAllInfo.getSeriesSumRating() + Double.parseDouble(consumable.getRating()));
                    overAllInfo.setSeriesAvgRating(overAllInfo.getSeriesSumRating() / seriesConsumables.size());
                }
                seriesConsumables.add(consumable);
            }
            else if (type == 3) {
                overAllInfo.setMovieSumOfConsumptionTime(overAllInfo.getMovieSumOfConsumptionTime() + consumable.getTotalConsumptionTime());
                overAllInfo.setMovieSumOfConsumptionDays(overAllInfo.getMovieSumOfConsumptionDays() + consumable.getTotalConsumptionDays());
                if (!consumable.getRating().equals("")) {
                    overAllInfo.setMovieSumRating(overAllInfo.getMovieSumRating() + Double.parseDouble(consumable.getRating()));
                    overAllInfo.setMovieAvgRating(overAllInfo.getMovieSumRating() / movieConsumables.size());
                }
                movieConsumables.add(consumable);
            }

        } else {
            System.out.println("Wrong selection. Please choose again\n");
            addConsumable();
        }
    }

    private static void editConsumable() {
        int type = listOfConsumables();
        if (type == 0) {
            editConsumable();
        } else if (isValidTye(type)) {
            if (type == 1) chooseEditAble(type, bookConsumables);
            else if (type == 2) chooseEditAble(type, seriesConsumables);
            else chooseEditAble(type, movieConsumables);
        }
    }

    private static void chooseEditAble(int type, ArrayList<Consumable> consumables) {
        System.out.println("Enter a number to edit (enter 0 to exit): ");
        int number = scanner.nextInt();

        if (number != 0) {
            if (number > 0 && number <= consumables.size()) {
                if (consumables.get(number - 1).isEditable()) {
                    doEdit(type, consumables.get(number - 1));

                } else {
                    System.out.println("This consumable is not editable. Please select another");
                    chooseEditAble(type, consumables);
                }
            } else {
                System.out.println("Invalid Selection.");
                chooseEditAble(type, consumables);
            }
        }
    }

    private static void doEdit(int type, Consumable consumable) {
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
                        consumable.setTotalConsumptionTime(consumable.getTotalConsumptionTime() + Integer.parseInt(conTimeInString));
                        overAllInfo.setBookSumOfConsumptionTime(overAllInfo.getBookSumOfConsumptionTime() + Integer.parseInt(conTimeInString));
                    }

                    System.out.println("Enter days of consumption to add: ");
                    String conDaysInString = scanner.nextLine();
                    if (!conDaysInString.isEmpty()) {
                        consumable.setTotalConsumptionDays(consumable.getTotalConsumptionDays() + Integer.parseInt(conDaysInString));
                        overAllInfo.setBookSumOfConsumptionDays(overAllInfo.getBookSumOfConsumptionDays() + Integer.parseInt(conDaysInString));
                    }

                    continue;

                case 2:
                    System.out.println("Enter rating in format x.yz to edit: ");
                    String ratingInString = scanner.nextLine();
                    if (ratingInString.isEmpty()) {
                        if (!consumable.getRating().isEmpty()) {
                            if (type == 1) {
                                overAllInfo.setBookSumRating(overAllInfo.getBookSumRating() - Double.parseDouble(consumable.getRating()));
                                overAllInfo.setBookAvgRating(overAllInfo.getBookSumRating() / bookConsumables.size());
                            } else if (type == 2) {
                                overAllInfo.setSeriesSumRating(overAllInfo.getSeriesSumRating() - Double.parseDouble(consumable.getRating()));
                                overAllInfo.setSeriesAvgRating(overAllInfo.getSeriesSumRating() / seriesConsumables.size());
                            } else {
                                overAllInfo.setMovieSumRating(overAllInfo.getMovieSumRating() - Double.parseDouble(consumable.getRating()));
                                overAllInfo.setMovieAvgRating(overAllInfo.getMovieSumRating() / movieConsumables.size());
                            }
                        }
                        consumable.setRating("");

                    } else {
                        consumable.setRating(ratingInString);
                        if (type == 1) {
                            overAllInfo.setBookSumRating(overAllInfo.getBookSumRating() - Double.parseDouble(consumable.getRating()));
                            overAllInfo.setBookAvgRating(overAllInfo.getBookSumRating() / bookConsumables.size());
                        } else if (type == 2) {
                            overAllInfo.setSeriesSumRating(overAllInfo.getSeriesSumRating() - Double.parseDouble(consumable.getRating()));
                            overAllInfo.setSeriesAvgRating(overAllInfo.getSeriesSumRating() / seriesConsumables.size());
                        } else {
                            overAllInfo.setMovieSumRating(overAllInfo.getMovieSumRating() - Double.parseDouble(consumable.getRating()));
                            overAllInfo.setMovieAvgRating(overAllInfo.getMovieSumRating() / movieConsumables.size());
                        }
                    }
                    continue;

                case 3:
                    System.out.println("Enter consumption ending date in format YYYY-MM-DD : ");
                    consumable.setConsumptionEndDate(scanner.nextLine());
                    consumable.setEditable(false);
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

    private static void deleteConsumable() {
        int type = listOfConsumables();
        if (type == 0) {
            deleteConsumable();
        } else if (isValidTye(type)) {
            if (type == 1) delete(type, bookConsumables);
            else if (type == 2) delete(type, seriesConsumables);
            else delete(type, movieConsumables);
        }
    }

    private static void delete(int type, ArrayList<Consumable> consumables) {
        System.out.print("\nchoose a number to delete (enter 0 to exit): ");
        int number = scanner.nextInt();
        if (number != 0) {
            if (number > 0 && number <= consumables.size()) {
                String rating = consumables.get(number - 1).getRating();

                if (type == 1) {
                    if (!rating.equals("")) {
                        overAllInfo.setBookSumRating(overAllInfo.getBookSumRating() - Double.parseDouble(rating));
                        overAllInfo.setBookAvgRating(overAllInfo.getBookSumRating() / consumables.size());
                    }
                } else if (type == 2) {
                    if (!rating.equals("")) {
                        overAllInfo.setSeriesSumRating(overAllInfo.getSeriesSumRating() - Double.parseDouble(rating));
                        overAllInfo.setSeriesAvgRating(overAllInfo.getSeriesSumRating() / consumables.size());
                    }
                } else {
                    if (!rating.equals("")) {
                        overAllInfo.setMovieSumRating(overAllInfo.getMovieSumRating() - Double.parseDouble(rating));
                        overAllInfo.setMovieAvgRating(overAllInfo.getMovieSumRating() / consumables.size());
                    }
                }
                consumables.remove(number - 1);
                System.out.println("successfully deleted\n");
            } else {
                System.out.print("Invalid selection.");
                delete(type, consumables);
            }
        }
    }

    private static void showConsumables() {
        int type = listOfConsumables();
        if (type == 0) {
            showConsumables();
        } else if (isValidTye(type)) {
            System.out.print("\nyou can pick one & see the full details (enter 0 to exit): \n");
            showIndividualConsumable(type);
        }
    }

    private static void showIndividualConsumable(int type) {
        int number = scanner.nextInt();
        if (number != 0) {
            if (type == 1) printIndividual(type, number, bookConsumables);
            else if (type == 2) printIndividual(type, number, seriesConsumables);
            else printIndividual(type, number, movieConsumables);
        }
    }

    private static void printIndividual(int type, int number, ArrayList<Consumable> consumables) {
        if (number > 0 && number <= consumables.size()) {
            System.out.print("\nname\t consumptionStartDate\t consumptionEndDate\t hrsOfConsumption\t daysOfConsumption\t rating\n");
            Consumable consumable = consumables.get(number - 1);
            System.out.println(consumable.getName() + "\t" + consumable.getConsumptionStartDate() + "\t" + consumable.getConsumptionEndDate() + "\t" +
                    consumable.getTotalConsumptionTime() + "\t" + consumable.getTotalConsumptionDays() + "\t" + consumable.getRating() + "\n");
        } else {
            System.out.println("choose a valid number (enter 0 to exit): ");
            showIndividualConsumable(type);
        }
    }

    private static int listOfConsumables() {
        int type = selectConsumableType();
        if (isValidTye(type)) {
            if (type == 1) printConsumables(bookConsumables);
            else if (type == 2) printConsumables(seriesConsumables);
            else printConsumables(movieConsumables);
            return type;
        } else {
            System.out.println("Wrong selection. Please choose again\n");
            return 0;
        }
    }

    private static void printConsumables(ArrayList<Consumable> consumables) {
        if (consumables.size() > 0) {
            System.out.println("\nserNo\t name\t hrsOfConsumption\t daysOfConsumption\t rating");
            for (int i = 0; i < consumables.size(); i++) {
                Consumable consumable = consumables.get(i);
                System.out.print((i + 1) + ".\t" + consumable.getName() + "\t" + consumable.getTotalConsumptionTime() + "\t" +
                        consumable.getTotalConsumptionDays() + "\t" + consumable.getRating() + "\n");
            }
        } else System.out.println("not found any");
    }

    private static void showOverallInfo() {
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

    private static boolean isValidTye(int type) {
        return type == 1 || type == 2 || type == 3;
    }

    public static void main(String[] args) {
        boolean flag = true;

        while (flag) {
            System.out.print("Track of Consumable. \n" +
                    "To add a consumable enter 1. \n" +
                    "To edit a consumable enter 2. \n" +
                    "To delete a consumable enter 3. \n" +
                    "To see the list of consumables enter 4. \n" +
                    "To see overall info enter 5. \n" +
                    "To exit enter 6 \n");

            int inputConsumable = scanner.nextInt();
            switch (inputConsumable) {
                case 1:
                    addConsumable();
                    continue;

                case 2:
                    editConsumable();
                    continue;

                case 3:
                    deleteConsumable();
                    continue;

                case 4:
                    showConsumables();
                    continue;

                case 5:
                    showOverallInfo();
                    continue;

                case 6:
                    System.out.println("Exiting from system...");
                    flag = false;
                    break;

                default:
                    System.out.println("No such choice available" + "\n");
            }
        }
    }
}
