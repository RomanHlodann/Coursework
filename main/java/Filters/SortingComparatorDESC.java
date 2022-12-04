package Filters;

import Tariff.BaseTariff;

import java.util.Comparator;

public class SortingComparatorDESC implements Comparator<BaseTariff> {
    public int compare(BaseTariff a, BaseTariff b){
        return Double.compare(b.getPrice(), a.getPrice());
    }
}
