import models.Consumable;
import models.OverAllInfo;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsumableService {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Consumable> bookConsumables = new ArrayList<>();
    private final ArrayList<Consumable> seriesConsumables = new ArrayList<>();
    private final ArrayList<Consumable> movieConsumables = new ArrayList<>();
    private final OverAllInfo overAllInfo = new OverAllInfo();

    public int selectConsumableType() {
        System.out.print("\nChoose a type by entering initial number. \n" +
                "1. Book\n2. Series\n3. Movie\n");
        System.out.print("Enter type: ");
        return scanner.nextInt();
    }

    public void addConsumable() {
        int type = selectConsumableType();
        if (isValidTye(type)) {
            Consumable consumable = new Consumable();
            System.out.print("\nEnter Name: ");
            scanner.nextLine();
            consumable.setName(scanner.nextLine());

            System.out.print("Enter consumption starting date in format YYYY-MM-DD (could be blank): ");
            consumable.setConsumptionStartDate(scanner.nextLine());
            System.out.print("Enter consumption ending date in format YYYY-MM-DD (could be blank): ");
            consumable.setConsumptionEndDate(scanner.nextLine());

            System.out.print("Enter Total consumption time in hours: ");
            String conTimeInString = scanner.nextLine();
            if (conTimeInString.equals("") || conTimeInString.equals(" ")) consumable.setTotalConsumptionTime(0);
            else consumable.setTotalConsumptionTime(Integer.parseInt(conTimeInString));

            System.out.print("Enter rating in format x.yz (could be blank): ");
            String ratingInString = scanner.nextLine();
            if (ratingInString.equals("") || ratingInString.equals(" ")) consumable.setRating("");
            else consumable.setRating(ratingInString);

            System.out.print("Enter Total days of consumption: ");
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

    public void editConsumable() {
        int type = listOfConsumables();
        if (type == 0) {
            editConsumable();
        } else if (isValidTye(type)) {
            if (checkIsDataAvailable(type)) {
                if (type == 1) chooseEditAble(type, bookConsumables);
                else if (type == 2) chooseEditAble(type, seriesConsumables);
                else chooseEditAble(type, movieConsumables);
            }
        }
    }

    public void chooseEditAble(int type, ArrayList<Consumable> consumables) {
        System.out.print("\nEnter any serial no to edit (enter 0 to exit): ");
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

    public void doEdit(int type, Consumable consumable) {
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
                        if (type == 1)
                            overAllInfo.setBookSumOfConsumptionTime(overAllInfo.getBookSumOfConsumptionTime() + Integer.parseInt(conTimeInString));
                        else if (type == 2)
                            overAllInfo.setSeriesSumOfConsumptionTime(overAllInfo.getSeriesSumOfConsumptionTime() + Integer.parseInt(conTimeInString));
                        else if (type == 3)
                            overAllInfo.setMovieSumOfConsumptionTime(overAllInfo.getMovieSumOfConsumptionTime() + Integer.parseInt(conTimeInString));
                    }

                    System.out.println("Enter days of consumption to add: ");
                    String conDaysInString = scanner.nextLine();
                    if (!conDaysInString.isEmpty()) {
                        consumable.setTotalConsumptionDays(consumable.getTotalConsumptionDays() + Integer.parseInt(conDaysInString));
                        if (type == 1)
                            overAllInfo.setBookSumOfConsumptionDays(overAllInfo.getBookSumOfConsumptionDays() + Integer.parseInt(conDaysInString));
                        else if (type == 2)
                            overAllInfo.setSeriesSumOfConsumptionDays(overAllInfo.getSeriesSumOfConsumptionDays() + Integer.parseInt(conDaysInString));
                        else if (type == 3)
                            overAllInfo.setMovieSumOfConsumptionDays(overAllInfo.getMovieSumOfConsumptionDays() + Integer.parseInt(conDaysInString));
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

    public void deleteConsumable() {
        int type = listOfConsumables();
        if (type == 0) {
            deleteConsumable();
        } else if (isValidTye(type)) {
            if (checkIsDataAvailable(type)) {
                if (type == 1) delete(type, bookConsumables);
                else if (type == 2) delete(type, seriesConsumables);
                else delete(type, movieConsumables);
            }
        }
    }

    public void delete(int type, ArrayList<Consumable> consumables) {
        System.out.print("\nchoose any serial no to delete (enter 0 to exit): ");
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

    public void showConsumables() {
        int type = listOfConsumables();
        if (type == 0) {
            showConsumables();
        } else if (isValidTye(type)) {
            if (checkIsDataAvailable(type)) {
                System.out.print("\npick any serial no & see the full details (enter 0 to exit): ");
                showIndividualConsumable(type);
            }
        }
    }

    public void showIndividualConsumable(int type) {
        int number = scanner.nextInt();
        if (number != 0) {
            if (type == 1) printIndividual(type, number, bookConsumables);
            else if (type == 2) printIndividual(type, number, seriesConsumables);
            else printIndividual(type, number, movieConsumables);
        }
    }

    public void printIndividual(int type, int number, ArrayList<Consumable> consumables) {
        if (number > 0 && number <= consumables.size()) {
            String leftAlignFormat = "| %-20s | %-22s | %-22s | %-20d | %-19d | %-7s |%n";

            System.out.format("+----------------------+------------------------+------------------------+----------------------+---------------------+---------+%n");
            System.out.format("|          Name        | Consumption Start Date | Consumption Start Date | Hours Of Consumption | Days Of Consumption | Rating  |%n");
            System.out.format("+----------------------+------------------------+------------------------+----------------------+---------------------+---------+%n");

            Consumable consumable = consumables.get(number - 1);
            System.out.format(leftAlignFormat, consumable.getName(), consumable.getConsumptionStartDate(), consumable.getConsumptionEndDate(),
                    consumable.getTotalConsumptionTime(), consumable.getTotalConsumptionDays(), consumable.getRating());
            System.out.format("+----------------------+------------------------+------------------------+----------------------+---------------------+---------+%n");

        } else {
            System.out.println("choose a valid number (enter 0 to exit): ");
            showIndividualConsumable(type);
        }
    }

    private boolean checkIsDataAvailable(int type) {
        if (type == 1) return bookConsumables.size() > 0;
        else if (type == 2) return seriesConsumables.size() > 0;
        else if (type == 3) return movieConsumables.size() > 0;
        return false;
    }

    public int listOfConsumables() {
        int type = selectConsumableType();
        if (isValidTye(type)) {
            if (type == 1) printConsumables(type, bookConsumables);
            else if (type == 2) printConsumables(type, seriesConsumables);
            else printConsumables(type, movieConsumables);
            return type;
        } else {
            System.out.println("Wrong selection. Please choose again\n");
            return 0;
        }
    }

    public void printConsumables(int type, ArrayList<Consumable> consumables) {
        if (consumables.size() > 0) {

            String leftAlignFormat = "| %-9d | %-20s | %-20d | %-19d | %-7s |%n";

            System.out.format("+-----------+----------------------+----------------------+---------------------+---------+%n");
            System.out.format("| Serial No |          Name        | Hours Of Consumption | Days Of Consumption | Rating  |%n");
            System.out.format("+-----------+----------------------+----------------------+---------------------+---------+%n");
            for (int i = 0; i < consumables.size(); i++) {
                Consumable consumable = consumables.get(i);
                System.out.format(leftAlignFormat, (i + 1), consumable.getName(), consumable.getTotalConsumptionTime(),
                        consumable.getTotalConsumptionDays(), consumable.getRating());
            }
            System.out.format("+-----------+----------------------+----------------------+---------------------+---------+%n");

        } else {
            if (type == 1) System.out.println("No consumable found under BOOK");
            else if (type == 2) System.out.println("No consumable found under SERIES");
            else if (type == 3) System.out.println("No consumable found under MOVIE");
        }
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

    public boolean isValidTye(int type) {
        return type == 1 || type == 2 || type == 3;
    }
}
