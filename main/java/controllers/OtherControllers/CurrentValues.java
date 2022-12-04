package controllers.OtherControllers;

import Cooperation.Company;
import Tariff.BaseTariff;

public class CurrentValues {
    public static Company company;
    public static BaseTariff tariff;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public BaseTariff getTariff() {
        return tariff;
    }

    public void setTariff(BaseTariff tariff) {
        this.tariff = tariff;
    }
}
