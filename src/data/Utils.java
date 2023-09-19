package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Utils {

    public static List<Vehicle> ve = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    public static int oldsize;
    private static int count;

    public static void mainMenu() {
        System.out.println("");
        System.out.println("=====================================");
        System.out.println("| 1. Adding new Vehicle             |");
        System.out.println("| 2. Check exits Vehicle            |");
        System.out.println("| 3. Update Vehicle                 |");
        System.out.println("| 4. Delete Vehicle                 |");
        System.out.println("| 5. Search Vehicle                 |");
        System.out.println("| 6. Display Vehicle                |");
        System.out.println("| Other. Quit                       |");
        System.out.println("=====================================");
    }

    public static void subMenuSearch() {
        System.out.println("");
        System.out.println("=====================================");
        System.out.println("| 1. Search by name                 |");
        System.out.println("| 2. Search by id                   |");
        System.out.println("| Other. Quit                       |");
        System.out.println("=====================================");
    }

    public static void goBackMainMenu() {
        System.out.println("");
        System.out.print("Press ENTER to go back menu ");
        sc.nextLine();
    }

    public static void subMenuShow() {
        System.out.println("");
        System.out.println("==============================================");
        System.out.println("| 1. Show all Vehicle                        |");
        System.out.println("| 2. Show Vehicle by Type                    |");
        System.out.println("| 3. Show all Vehicle (descending Price)     |");
        System.out.println("| 4. Show Vehicle by Type (descending Price) |");
        System.out.println("| Other. Quit                                |");
        System.out.println("==============================================");
    }

    public static List<Vehicle> loadData() {
        String file = "data.txt";
        List<Vehicle> a = new ArrayList<>();
        File f = new File(file);
        if (f.exists()) {
            if (f.length() == 0) {
                return null;
            } else {
                try {
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    String line = "";
                    while (line != null) {
                        line = br.readLine();
                        String arr[] = line.split(",");
                        a.add(new Vehicle(arr[0], arr[1], arr[2],
                                Double.parseDouble(arr[3]), arr[4], arr[5], Integer.parseInt(arr[6])));
                    }
                } catch (Exception e) {

                }
            }
        }
        oldsize = a.size();
        return a;
    }

    public static void addVehicle() {
        System.out.println("");
        System.out.println("========CREATION========");
        String ID;
        while (true) {
            ID = Validation.getNoneBlankString("Vehicle id (VExxxxx): ", "ID invalid");
            ID = ID.toUpperCase();
            if (!Validation.isID(ID)) {
                System.err.println("ID must be VExxxxx");
            } else if (getVehicle(ID) != null) {
                System.err.println("ID already exist");
            } else {
                break;
            }
        }
        String name = Validation.getNoneBlankString("Name: ", "Name not valid");
        name = name.toLowerCase();
        String color = Validation.getNoneBlankString("Color: ", "Color not valid");
        int price = Validation.getInt("Price: ", "Please input number");
        String brand = Validation.getNoneBlankString("Brand: ", "Brand not valid");
        String type = Validation.getNoneBlankString("Type: ", "Type not valid");
        int year;
        while (true) {
            year = Validation.getInt("Year: ", "Year not valid");
            if (year <= 0 || year > 2023) {
                System.err.println("Year not valid");
            } else {
                break;
            }
        }
        ve.add(new Vehicle(ID, name, color, price, brand, type, year));
        saveToFile();
        System.out.println("========================");
        System.out.println("");
        System.out.println("CREATE SUCCESSFULLY");
        goBackMainMenu();
    }

    public static Vehicle getVehicle(String id) {
        if (ve.isEmpty()) {
            return null;
        } else {
            for (Vehicle o : ve) {
                if (o.getId().equalsIgnoreCase(id)) {
                    return o;
                }
            }
        }
        return null;
    }

    public static void existVehicle() {
        System.out.println("");
        System.out.println("========CHECK EXIST========");
        String s;
        while (true) {
            s = Validation.getNoneBlankString("Id Vehicle: ", "ID invalid");
            s = s.toUpperCase();
            if (!Validation.isID(s)) {
                System.err.println("ID must be VExxxxx");
            } else {
                break;
            }
        }
        if (getVehicle(s) != null) {
            System.out.println("");
            System.out.println("      Exist Vehicle");
        } else {
            System.out.println("");
            System.out.println("     No Vehicle Found!");
        }
        System.out.println("===========================");
        goBackMainMenu();
    }

    public static void updateVehicle() {
        String s;
        System.out.println("");
        while (true) {
            s = Validation.getNoneBlankString("Enter ID to update: ", "ID invalid");
            s = s.toUpperCase();
            if (!Validation.isID(s)) {
                System.out.println("ID must be VExxxxx");
            } else {
                break;
            }
        }
        if (getVehicle(s) != null) {
            System.out.println("");
            System.out.print("Enter new name: ");
            String name = sc.nextLine();
            name = name.toLowerCase();
            if (!name.isEmpty() && !name.equals(" ")) {
                getVehicle(s).setName(name);
            }
            System.out.print("Enter new color: ");
            String color = sc.nextLine();
            if (!color.isEmpty() && !color.equals(" ")) {
                getVehicle(s).setColor(color);
            }
            while (true) {
                System.out.print("Enter new price: ");
                String price = sc.nextLine();
                try {
                    if (!price.isEmpty() && Double.parseDouble(price) >= 0) {
                        getVehicle(s).setPrice(Double.parseDouble(price));
                    }
                    break;
                } catch (Exception e) {
                    System.err.println("Price must be real number");
                }
            }
            System.out.print("Enter new brand: ");
            String brand = sc.nextLine();
            if (!brand.isEmpty() && !brand.equals(" ")) {
                getVehicle(s).setBrand(brand);
            }
            System.out.print("Enter new type: ");
            String type = sc.nextLine();
            if (!type.isEmpty() && !type.equals(" ")) {
                getVehicle(s).setType(type);
            }
            while (true) {
                System.out.print("Enter new year: ");
                String year = sc.nextLine();
                try {
                    if(year.isEmpty()) break;
                    if (Integer.parseInt(year) >= 0 || Integer.parseInt(year) <= 2023) {
                        getVehicle(s).setProductYear(Integer.parseInt(year));
                    }
                    break;
                } catch (Exception e) {
                    System.err.println("Year invalid");
                }
            }
            count++;
            saveToFile();
            System.out.println("");
            System.out.println("VEHICLE UPDATE:");
            System.out.println("");
            layoutPrint();
            Vehicle o = getVehicle(s);
            System.out.printf("| %-8s | %-8s | %-10s | %-8.2f | %-12s | %-8s | %-5d |\n",
                    o.getId(), o.getName(), o.getColor(), o.getPrice(), o.getBrand(),
                    o.getType(), o.getProductYear());
            System.out.println("======================================"
                    + "===========================================");
        } else {
            System.out.println("Vehicle does not exist");
        }
        goBackMainMenu();
    }

    public static void confirmDelete() {
        System.out.println("");
        System.out.println("=====ARE YOU SURE WANT TO DELETE=====");
        System.out.println("| 1. Accept Delete                  |");
        System.out.println("| Other. Cancel                     |");
        System.out.println("=====================================");
    }

    public static void layoutPrint() {
        System.out.println("========================================"
                + "=========================================");
        System.out.printf("| %-8s | %-8s | %-10s | %-8s | %-12s | %-8s | %-5s |\n",
                "ID", "Name", "Color", "Price", "Brand", "Type", "Year");
        System.out.println("======================================"
                + "===========================================");
    }

    public static void deleteVehicle() {
        String s;
        System.out.println("");
        while (true) {
            s = Validation.getNoneBlankString("Enter ID to delete: ", "ID invalid");
            s = s.toUpperCase();
            if (!Validation.isID(s)) {
                System.out.println("ID must be VExxxxx");
            } else {
                break;
            }
        }
        if (getVehicle(s) == null) {
            System.out.println("");
            System.out.println("Vehicle does not exist");
        } else {
            confirmDelete();
            int choose = Validation.getInt("Your option: ", "Choose Invalid");
            if (choose == 1) {
                ve.remove(getVehicle(s));
                count++;
                saveToFile();
                System.out.println("");
                System.out.println("DELETE SUCCESSFULLY");
            }
        }
        goBackMainMenu();
    }

    public static void searchVehicleByName() {
        List<Vehicle> search = new ArrayList<>();
        System.out.println("");
        System.out.print("Search Name: ");
        String name = sc.nextLine().toLowerCase();
        for (Vehicle o : ve) {
            if (o.getName().contains(name)) {
                search.add(o);
            }
        }
        System.out.println("");
        if (search.size() == 0) {
            System.out.println("NO VEHICLE FOUND WITH THAT NAME");
            goBackMainMenu();
            return;
        }
        Collections.sort(search, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle t, Vehicle t1) {
                return (int) (t1.getPrice() - t.getPrice());
            }
        });
        layoutPrint();
        for (Vehicle o : search) {
            System.out.printf("| %-8s | %-8s | %-10s | %-8.2f | %-12s | %-8s | %-5d |\n",
                    o.getId(), o.getName(), o.getColor(), o.getPrice(), o.getBrand(),
                    o.getType(), o.getProductYear());
        }
        System.out.println("======================================"
                + "===========================================");
        goBackMainMenu();
    }

    public static void searchVehicleByID() {
        String ID;
        System.out.println("");
        while (true) {
            ID = Validation.getNoneBlankString("Search id: ", "ID invalid");
            ID = ID.toUpperCase();
            if (Validation.isID(ID)) {
                break;
            } else {
                System.out.println("ID must be VE_xxxxx");
            }
        }
        if (getVehicle(ID) == null) {
            System.out.println("");
            System.out.println("NO VEHICLE FOUND WITH THAT ID");
        } else {
            layoutPrint();
            Vehicle o = getVehicle(ID);
            System.out.printf("| %-8s | %-8s | %-10s | %-8.2f | %-12s | %-8s | %-5d |\n",
                    o.getId(), o.getName(), o.getColor(), o.getPrice(), o.getBrand(),
                    o.getType(), o.getProductYear());
            System.out.println("======================================"
                    + "===========================================");
        }
        goBackMainMenu();
    }

    public static void showAll() {
        System.out.println("");
        layoutPrint();
        for (Vehicle o : ve) {
            System.out.printf("| %-8s | %-8s | %-10s | %-8.2f | %-12s | %-8s | %-5d |\n",
                    o.getId(), o.getName(), o.getColor(), o.getPrice(), o.getBrand(),
                    o.getType(), o.getProductYear());
        }
        System.out.println("====================================="
                + "============================================");
        goBackMainMenu();
    }

    public static void saveToFile() {
        String file = "data.txt";
        if (oldsize == 0 || count > 0) {
            try {
                FileWriter fw = new FileWriter(file);
                for (int i = 0; i < ve.size(); i++) {
                    fw.write(ve.get(i).toString() + "\n");
                }
                fw.close();
            } catch (Exception e) {

            }
            oldsize = ve.size();
            count = 0;
        } else if (ve.size() > oldsize) {
            try {
                FileWriter fw = new FileWriter(file, true);
                for (int i = oldsize; i < ve.size(); i++) {
                    fw.write(ve.get(i).toString() + "\n");
                }
                fw.close();
            } catch (Exception e) {

            }
        }
    }

    public static void showAllByType() {
        boolean check = false;
        System.out.println("");
        String s = Validation.getNoneBlankString("Enter Type to search: ", "Type not valid");
        System.out.println("");
        for (Vehicle o : ve) {
            if (o.getType().equalsIgnoreCase(s)) {
                if (!check) {
                    layoutPrint();
                }
                System.out.printf("| %-8s | %-8s | %-10s | %-8.2f | %-12s | %-8s | %-5d |\n",
                        o.getId(), o.getName(), o.getColor(), o.getPrice(), o.getBrand(),
                        o.getType(), o.getProductYear());
                check = true;
            }
        }
        if (!check) {
            System.out.println("No Vehicle Type found!");
        } else {
            System.out.println("======================================="
                    + "==========================================");
        }
        goBackMainMenu();
    }

    public static void showAllDescending() {
        System.out.println("");
        List<Vehicle> des = loadData();
        if (des.size() == 0) {
            System.out.println("No Vehicle to show");
            return;
        }
        layoutPrint();
        Collections.sort(des, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle t, Vehicle t1) {
                return (int) (t1.getPrice() - t.getPrice());
            }
        });
        for (Vehicle de : des) {
            System.out.printf("| %-8s | %-8s | %-10s | %-8.2f | %-12s | %-8s | %-5d |\n",
                    de.getId(), de.getName(), de.getColor(), de.getPrice(), de.getBrand(),
                    de.getType(), de.getProductYear());
        }
        System.out.println("====================================="
                + "============================================");
        goBackMainMenu();
    }

    public static void showTypeDescending() {
        List<Vehicle> des = new ArrayList<>();
        System.out.println("");
        String s = Validation.getNoneBlankString("Enter Type to search: ", "Type not valid");
        for (Vehicle o : ve) {
            if (o.getType().equalsIgnoreCase(s)) {
                des.add(o);
            }
        }
        if (des.size() == 0) {
            System.out.println("No Vehicle Type to show");
            return;
        }
        Collections.sort(des, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle t, Vehicle t1) {
                return (int) (t1.getPrice() - t.getPrice());
            }
        });
        layoutPrint();
        for (Vehicle de : des) {
            System.out.printf("| %-8s | %-8s | %-10s | %-8.2f | %-12s | %-8s | %-5d |\n",
                    de.getId(), de.getName(), de.getColor(), de.getPrice(), de.getBrand(),
                    de.getType(), de.getProductYear());
        }
        System.out.println("====================================="
                + "============================================");
        goBackMainMenu();
    }

}
