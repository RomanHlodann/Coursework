package Filters;

import Tariff.BaseTariff;

import java.util.Comparator;

public class SortingComparatorASC implements Comparator<BaseTariff> {
    @Override
    public int compare(BaseTariff a, BaseTariff b){
        return Double.compare(a.getPrice(), b.getPrice());
    }
}
