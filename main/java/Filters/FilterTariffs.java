package Filters;

import Cooperation.Check;
import Tariff.BaseTariff;
import java.util.*;

public class FilterTariffs {
    public static ArrayList<BaseTariff> filterTariffsByDurationTime (ArrayList<BaseTariff>tariffs,String firstLimit, String secondLimit){
        if(firstLimit.equals(""))
            firstLimit = "1 day";
        if(secondLimit.equals(""))
            secondLimit = "2 year";
        if(!Check.checkTimeDuration(firstLimit) || !Check.checkTimeDuration(secondLimit)){
            throw new NullPointerException();
        }
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
    public static ArrayList<BaseTariff> filterTariffsByPrice(ArrayList<BaseTariff>tariffs,String firstLimit, String secondLimit){
        if(firstLimit.equals(""))
            firstLimit = "1";
        if(secondLimit.equals(""))
            secondLimit = "1400";
        if(!Check.checkDouble(firstLimit, 1500) || !Check.checkDouble(secondLimit, 1500)){
            throw new NullPointerException();
        }
        double firstlimit = Double.parseDouble(firstLimit);
        double secondlimit = Double.parseDouble(secondLimit);
        tariffs.removeIf(i -> i.getPrice() < firstlimit || i.getPrice() > secondlimit);
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
