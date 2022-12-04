package Tariff;

import Cooperation.Check;
import Tariff.BaseTariff;

public class MaximumTariff extends BaseTariff {
    public MaximumTariff(int gbOfInternet, int minutesWithOtherNetwork, double price, String timeAction){
        this.name = "Maximum Tariff";
        this.gbOfInternet = gbOfInternet;
        this.minutesWithOtherNetwork = minutesWithOtherNetwork;
        this.minutesAbroad = 150;
        this.limitTopApps = "unlimited";
        this.countOfSms = 500;
        this.price = price;
        this.timeAction = timeAction;
    }
    public static void printInfo(){
        System.out.println("\n3. Tariff name - Maximum Tariff\n" +
                "Constant parts:\n" +
                "Minutes abroad: 150\n" +
                "Unlimited access to top apps\n" +
                "SMS: 500\n");
    }
}
