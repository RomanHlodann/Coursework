package Filters;

import Tariff.BaseTariff;
import java.util.*;

public class FilterTariffs {
    public static ArrayList<BaseTariff> filterTariffsByDurationTime (ArrayList<BaseTariff>tariffs,String firstLimit, String secondLimit){
        Iterator<BaseTariff> iter = tariffs.iterator();
        int lowerLimit = parseDurations(firstLimit);
        int higherLimit = parseDurations(secondLimit);
        while(iter.hasNext()){
            BaseTariff i = iter.next();
            int duration = parseDurations(i.getTimeAction());
            if(duration > lowerLimit)
                if(duration < higherLimit)
                    continue;
            iter.remove();
        }
        return tariffs;
    }
    public static ArrayList<BaseTariff> filterTariffsByPrice(ArrayList<BaseTariff>tariffs,double firstLimit, double secondLimit){
        tariffs.removeIf(i -> i.getPrice() < firstLimit || i.getPrice() > secondLimit);
        return tariffs;
    }
    public static int parseDurations(String timeDurat){
        String unitOfTime = timeDurat.replaceAll("[0-9 ]", "");
        if(unitOfTime.equals("month"))
            return Integer.parseInt(timeDurat.replaceAll("[^0-9]", "")) * 30;
        if(unitOfTime.equals("year"))
            return Integer.parseInt(timeDurat.replaceAll("[^0-9]", "")) * 30 * 12;
        return Integer.parseInt(timeDurat.replaceAll("[^0-9]", ""));
    }
}
