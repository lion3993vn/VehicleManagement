package data;

import java.util.Scanner;

public class Validation {

    public static int getInt(String mess, String err) {
        Scanner sc = new Scanner(System.in);
        int choose;
        do {
            try {
                System.out.print(mess);
                choose = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception e) {
                System.err.println(err);
            }
        } while (true);
        return choose;
    }

    public static String getNoneBlankString(String inMsg, String errMsg) {
        String s = null;
        boolean cont = true;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(inMsg);
                s = sc.nextLine().trim();
                if (s.isEmpty() || s.equals(" ")) {
                    throw new Exception();
                }
                cont = false;
            } catch (Exception e) {
                System.err.println(errMsg);
            }
        } while (cont == true);
        return s;
    }

    public static boolean isID(String ID) {
        String regex = "^VE\\d{5}$";
        if (ID.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }
}
