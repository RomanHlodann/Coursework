package Tariff;

import Cooperation.Company;
import Cooperation.User;
import Cooperation.Check;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class BaseTariff implements Serializable {
    String name;
    int gbOfInternet = 0;
    int minutesWithOtherNetwork = 0;
    int minutesAbroad = 0;
    String limitTopApps;
    int countOfSms = 0;
    double price = 0;
    String timeAction;
    ArrayList<User> users = new ArrayList();
    static Scanner ing = new Scanner(System.in);

    public void addNewUser(User user) {
        users.add(user);
    }
    public ArrayList<User> getUsers() {
        return users;
    }

    public void printUsers(){
        if(users.size() == 0){
            System.out.println("No users are using this tariff");
        }
        int count = 1;
        for(User i: users){
            System.out.println(count + ". " + i);
        }
    }
    @Override
    public String toString() {
        return   name + '\'' +
                "{amount of gb= " + gbOfInternet +
                ", minutes with other network= " + minutesWithOtherNetwork +
                ", minutes abroad=" + minutesAbroad +
                ",\'" + limitTopApps + '\'' + " access to top apps" +
                ", count of sms= " + countOfSms +
                ", price= " + price +
                ", timeAction= '" + timeAction + '\'' +
                "}\n";
    }

    public String getTimeAction() {
        return timeAction;
    }
    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getGbOfInternet() {
        return gbOfInternet;
    }

    public int getMinutesWithOtherNetwork() {
        return minutesWithOtherNetwork;
    }

    public int getMinutesAbroad() {
        return minutesAbroad;
    }

    public String getLimitTopApps() {
        return limitTopApps;
    }

    public int getCountOfSms() {
        return countOfSms;
    }
}
