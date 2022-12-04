package Tariff;

import Cooperation.Check;
import Tariff.BaseTariff;

public class OptimumTariff extends BaseTariff {
    public OptimumTariff(int gbOfInternet, int minutesAbroad, String limitTopApps, double price, String timeAction){
        this.name = "Optimum tariff";
        this.gbOfInternet = gbOfInternet;
        this.minutesWithOtherNetwork = 100;
        this.minutesAbroad = minutesAbroad;
        this.limitTopApps = limitTopApps;
        this.countOfSms = 200;
        this.price = price;
        this.timeAction = timeAction;
    }
    public static void printInfo(){
        System.out.println("\n2. Tariff name - Optimum Tariff\n" +
                "Constant parts:\n" +
                "Minutes with other networks: 100\n" +
                "Duration time: 3 month\n" +
                "SMS: 200\n");
    }
}
