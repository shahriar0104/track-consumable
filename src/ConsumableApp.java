import java.util.Scanner;

public class ConsumableApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        ConsumableService service = new ConsumableService();

        while (flag) {
            System.out.print("\nTrack of Consumable. \n" +
                    "To add a consumable enter 1. \n" +
                    "To edit a consumable enter 2. \n" +
                    "To delete a consumable enter 3. \n" +
                    "To see the list of consumables enter 4. \n" +
                    "To see overall info enter 5. \n" +
                    "To exit enter 6 \n");

            System.out.print("Enter your selection: ");
            int inputConsumable = scanner.nextInt();
            switch (inputConsumable) {
                case 1:
                    service.addConsumable();
                    continue;

                case 2:
                    service.editConsumable();
                    continue;

                case 3:
                    service.deleteConsumable();
                    continue;

                case 4:
                    service.showConsumables();
                    continue;

                case 5:
                    service.showOverallInfo();
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
