package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Utils {

    public static List<Vehicle> ve = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void mainMenu() {
        System.out.println("");
        System.out.println("=====================================");
        System.out.println("| 1. Adding new Vehicle             |");
        System.out.println("| 2. Check exits Vehicle            |");
        System.out.println("| 3. Update Vehicle                 |");
        System.out.println("| 4. Delete Vehicle                 |");
        System.out.println("| 5. Search Vehicle                 |");
        System.out.println("| 6. Display all Vehicle            |");
        System.out.println("| 7. Saving Vehicle to file         |");
        System.out.println("| 8. Printing Vehicle               |");
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

    public static List<Vehicle> loadData() {
        String file = "Vehicle.txt";
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
        return a;
    }

    public static void addVehicle() {
        String ID;
        while (true) {
            ID = Validation.getNoneBlankString("Input id: ", "ID invalid");
            ID = ID.toUpperCase();
            if (!Validation.isID(ID) && getVehicle(ID) == null) {
                break;
            } else {
                System.out.println("ID must be VE_xxxxx");
            }
        }
        String name = Validation.getNoneBlankString("Input name", "Name not valid");
        name = name.toLowerCase();
        String color = Validation.getNoneBlankString("Input color", "Color not valid");
        int price = Validation.getInt("Input price", "Please input number");
        String brand = Validation.getNoneBlankString("Input brand: ", "Brand not valid");
        String type = Validation.getNoneBlankString("Input type: ", "Type not valid");
        int year = Validation.getInt("Input year: ", "Year not valid");
        ve.add(new Vehicle(ID, name, color, price, brand, type, year));
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
        String s;
        while (true) {
            s = Validation.getNoneBlankString("Enter ID to check", "ID invalid");
            s = s.toLowerCase();
            if (!Validation.isID(s)) {
                System.out.println("ID must be VE_xxxxx");
            } else {
                break;
            }
        }
        if (getVehicle(s) != null) {
            System.out.println("Exist Vehicle");
        } else {
            System.out.println("No Vehicle Found!");
        }
    }

    public static void updateVehicle() {
        String s;
        while (true) {
            s = Validation.getNoneBlankString("Enter ID to check", "ID invalid");
            s = s.toUpperCase();
            if (!Validation.isID(s)) {
                System.out.println("ID must be VE_xxxxx");
            } else {
                break;
            }
        }
        if (getVehicle(s) != null) {
            System.out.println("Enter new name: ");
            String name = sc.nextLine();
            name = name.toLowerCase();
            if (!name.isEmpty() || !name.equals(" ")) {
                getVehicle(s).setName(name);
            }
            System.out.println("Enter new color: ");
            String color = sc.nextLine();
            if (!color.isEmpty() || !color.equals(" ")) {
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
            System.out.println("Enter new Brand: ");
            String brand = sc.nextLine();
            if (!brand.isEmpty() || !brand.equals(" ")) {
                getVehicle(s).setBrand(brand);
            }
            System.out.println("Enter new type: ");
            String type = sc.nextLine();
            if (!type.isEmpty() || !type.equals(" ")) {
                getVehicle(s).setBrand(brand);
            }
            System.out.println(getVehicle(s).toString());
        } else {
            System.out.println("Vehicle does not exist");
        }

    }

    public static void deleteVehicle() {
        String s;
        while (true) {
            s = Validation.getNoneBlankString("Enter ID to check", "ID invalid");
            if (!Validation.isID(s)) {
                System.out.println("ID must be VE_xxxxx");
            } else {
                break;
            }
        }
        if (getVehicle(s) == null) {
            System.out.println("Vehicle does not exist");
        } else {
            ve.remove(getVehicle(s));
        }
    }

    public static void searchVehicleByName() {
        List<Vehicle> search = new ArrayList<>();
        System.out.print("Search Name: ");
        String name = sc.nextLine().toLowerCase();
        for (Vehicle o : ve) {
            if(o.getName().contains(name)){
                search.add(o);
            }
        }
        Collections.sort(search, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle t, Vehicle t1) {
                return t.getName().compareTo(t1.getName());
            }
        });
        for (Vehicle o : search) {
            System.out.println(o.toString());
        }
    }
    public static void searchVehicleByID(){
        String ID;
        while (true) {
            ID = Validation.getNoneBlankString("Input id: ", "ID invalid");
            ID = ID.toUpperCase();
            if (!Validation.isID(ID) && getVehicle(ID) == null) {
                break;
            } else {
                System.out.println("ID must be VE_xxxxx");
            }
        }
    }
}
