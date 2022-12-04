package Cooperation;

import java.util.Scanner;

public class Check {
    static Scanner in = new Scanner(System.in);
    public static int inputIntegerWithLimit(int limit, String str){
        while(true){
            System.out.print(str);
            String comp = in.nextLine();
            if (Check.checkInteger(comp, limit))
                return Integer.parseInt(comp);
            System.out.println("Inputed wrong value. Try again!");
        }
    }
    public static boolean checkDouble(String value, double limit){
        try {
            double val = Double.parseDouble(value);
            if ( val > limit || val <= 0) {
                System.out.println("Wrong value!!");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean checkTimeDuration(String str){
        String temp = str.replaceAll("[0-9 ]", "");
        if( !(temp.equals("day") || temp.equals("month") || temp.equals("year")))
            return false;
        String numb = str.replaceAll("[^0-9]", "");
        if(temp == "year")
            return checkInteger(numb, 2);
        else
            return checkInteger(numb, 45);
    }
    public static boolean checkInteger(String value, int limit){
        try {
            int val = Integer.parseInt(value);
            if ( val > limit || val <= 0)
                return false;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean checkName(String name){
        String check = "\\p{Upper}(\\p{Lower}+\\s?)";
        return name.matches(check);
    }
    public static boolean checkCompanyName(String companyName){
        String check = "^[a-zA-Z0-9]{2,15}";
        return companyName.matches(check);
    }
    public static boolean checkIdentifier(String str){
        String check = "^[0-9]{2}";
        return str.matches(check);
    }
    public static boolean checkPhoneNumber(String number){
        String temp = number.replaceAll("[- ()]", "");
        String check = "^[0-9]{7}";
        return temp.matches(check);
    }
    public static boolean askForGetBack(){
        while(true) {
            String answer = in.nextLine();
            if (answer.equals("N"))
                return false;
            if (answer.equals("Y"))
                return true;
            System.out.print("Try again! Answer: ");
        }
    }
}
