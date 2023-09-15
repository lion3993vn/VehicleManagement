package data;

public class Main {

    public static void main(String[] args) {
        int choose;
        do {
            Utils.mainMenu();
            Utils.ve = Utils.loadData();
            choose = Validation.getInt("Choose your option: ", "Please type number again");
            switch (choose) {
                case 1:
                    Utils.addVehicle();
                    break;
                case 2:
                    Utils.existVehicle();
                    break;
                case 3:
                    Utils.updateVehicle();
                    break;
                case 4:
                    Utils.deleteVehicle();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                default:
                    break;
            }
        } while (choose < 9 && choose > 0);
    }
}
