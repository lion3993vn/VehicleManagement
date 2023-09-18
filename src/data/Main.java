package data;

public class Main {

    public static void main(String[] args) {
        int choose;
        Utils.ve = Utils.loadData();
        do {
            Utils.mainMenu();
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
                    int func5;
                    if (Utils.ve.size() == 0) {
                        System.out.println("");
                        System.out.println("LIST VEHICLE EMPTY TO SEARCH");
                        Utils.goBackMainMenu();
                        break;
                    }
                    do {
                        Utils.subMenuSearch();
                        func5 = Validation.getInt("Choose your option: ", "Please type number again");
                        switch (func5) {
                            case 1:
                                Utils.searchVehicleByName();
                                break;
                            case 2:
                                Utils.searchVehicleByID();
                                break;
                            default:
                                break;
                        }
                    } while (func5 < 3 && func5 > 0);
                    break;
                case 6:
                    int func6;
                    if (Utils.ve.size() == 0) {
                        System.out.println("");
                        System.out.println("LIST VEHICLE EMPTY TO SHOW");
                        Utils.goBackMainMenu();
                        break;
                    }
                    do {
                        Utils.subMenuShow();
                        func6 = Validation.getInt("Choose your option: ", "Please type number again");
                        switch (func6) {
                            case 1:
                                Utils.showAll();
                                break;
                            case 2:
                                Utils.showAllByType();
                                break;
                            case 3:
                                Utils.showAllDescending();
                                break;
                            case 4:
                                Utils.showTypeDescending();
                            default:
                                break;
                        }
                    } while (func6 < 5 && func6 > 0);
                    break;
                default:
                    break;
            }
        } while (choose < 7 && choose > 0);
    }
}
