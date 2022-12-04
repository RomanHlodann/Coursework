package Tariff;

import Cooperation.Check;
import Tariff.BaseTariff;

public class StartTariff extends BaseTariff {
    public StartTariff(int minutesWithOtherNetwork, int countOfSms, double price) {
        this.name = "Start tariff";
        this.gbOfInternet = 50;
        this.minutesWithOtherNetwork = minutesWithOtherNetwork;
        this.minutesAbroad = 0;
        this.limitTopApps = "limited";
        this.countOfSms = countOfSms;
        this.price = price;
        this.timeAction = "14 day";
    }
    public static void printInfo(){
        System.out.println("\n1. Tariff name - Start Tariff\n" +
                "GB of Internet: 50\n" +
                "Constant parts:\n" +
                "Minutes abroad: 0\n" +
                "limited access to top apps\n" +
                "Duration time: 1 month\n");
    }
}
