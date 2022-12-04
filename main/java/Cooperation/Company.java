package Cooperation;

import Filters.FilterTariffs;
import Filters.SortingComparatorASC;
import Filters.SortingComparatorDESC;
import Tariff.BaseTariff;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Company implements Serializable{
    String name;
    String keyNumb;
    int countOfUsers = 0;
    ArrayList <BaseTariff> tariffs = new ArrayList <>();
    public static ArrayList <Company> companies = new ArrayList <>();
    public Company(String name, String keyNumb){
        this.name = name;
        this.keyNumb = keyNumb;
        companies.add(this);
    }
    public enum TariffSorting{
        ASC, DESC;
    }
    public static ArrayList<BaseTariff> sortTariffs(ArrayList<BaseTariff> tariffs, TariffSorting type){
        ArrayList<BaseTariff> sortedTariffs = new ArrayList<BaseTariff>(tariffs);
        if(type == TariffSorting.ASC)
            Collections.sort(sortedTariffs, new SortingComparatorASC());
        else
            Collections.sort(sortedTariffs, new SortingComparatorDESC());
        return sortedTariffs;
    }
    public void addTariffToList(BaseTariff tariff){
        tariffs.add(tariff);
    }
    public boolean checkForPhoneInCompany(String phoneNumber){
        for (BaseTariff i : tariffs ){
            if (i.getUsers().contains(phoneNumber))
                return true;
        }
        return false;
    }
    public static boolean checkForAvailbleName(String name){
        for(Company i: companies){
            if(name.equals(i.getName()))
                return false;
        }
        return true;
    }
    public static boolean checkForAvailbleKeyNumb(String name){
        for(Company i: companies){
            if(i.keyNumb.equals(name))
                return false;
        }
        return true;
    }
    public static void printCompanies(){
        int count = 1;
        for ( Company i: companies){
            System.out.println( count + ". " + i + "\n");
            count++;
        }
    }

    public ArrayList<BaseTariff> getTariffs(){
        return tariffs;
    }

    public String getKeyNumb() {
        return keyNumb;
    }

    public String getName() {
        return name;
    }

    public int getCountOfUsers() {
        return countOfUsers;
    }

    public void updateCountOfUsers(){
        this.countOfUsers += 1;
    }
    public void updateCountOfUsersByTariffs(){
        for(BaseTariff tariff : tariffs){
            countOfUsers += tariff.getUsers().size();
        }
    }

    public void setTariffs(ArrayList<BaseTariff> tariffs) {
        this.tariffs = tariffs;
    }


    @Override
    public String toString() {
        return "Company name='" + name + '\'' +
                ", identifier =" + keyNumb +
                ", tariffs =" + tariffs +
                ", users =" + countOfUsers;
    }
}