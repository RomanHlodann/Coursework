package Main;

import Cooperation.Company;
import Cooperation.User;
import Tariff.BaseTariff;
import Tariff.MaximumTariff;
import Tariff.OptimumTariff;
import Tariff.StartTariff;

import java.sql.*;


public class DB {
    static final String DB_URL = "jdbc:sqlserver://DESKTOP-42MCIM6\\DEV;Database=MobileTariff;IntegratedSecurity=true;encrypt=false";
    public Connection connectDatabase() throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            System.out.println("Connection established");
            return connection;
        } catch (SQLException e) {
            System.out.println("Failed");
            e.printStackTrace();
            throw new SQLException();
        }
    }
    public void getInfo(){
        String QUERY = "SELECT * FROM Company";
        String QUERY2 = "SELECT * FROM Tariff WHERE id_company = ";
        String QUERY3 = "SELECT * FROM User WHERE id_tariff = ";
        try {
            Connection connection = connectDatabase();

            Statement stat = connection.createStatement();
            ResultSet comp = stat.executeQuery(QUERY);
            Statement statTar = connection.createStatement();
            while(comp.next()){

                Company company = new Company(comp.getString("name"), comp.getString("keyNumb"));
                addTariff(connection, company, Integer.toString(comp.getInt("id_company")));
                company.updateCountOfUsersByTariffs();
            }
        } catch (SQLException e) {
            System.out.println("Failed");
            e.printStackTrace();
            return;
        }
    }
    public void setInfo(){
        try{
            Connection connection = connectDatabase();
            Statement stat = connection.createStatement();
            prepereAndTruncateTables(stat);
            int count = 1;
            for(Company company : Company.companies){
                String InsertCompany = "INSERT INTO Company (name, keyNumb, countOfUsers) VALUES('" + company.getName() +
                        "', '" + company.getKeyNumb() + "','" + company.getCountOfUsers() + "')";
                stat.executeUpdate(InsertCompany);

                setTariff(connection, company, Integer.toString(count++));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    private void addTariff(Connection connection, Company company, String id_company){
        String QUERY = "SELECT * FROM Tariff WHERE id_company = ";
        try {
            Statement stat = connection.createStatement();
            ResultSet tariffs = stat.executeQuery(QUERY + id_company);

            while(tariffs.next()){
                BaseTariff tar = findTariff(tariffs);
                company.addTariffToList(tar);

                addUser(connection, tar, Integer.toString(tariffs.getInt("id_tariff")));
            }

        } catch (SQLException e) {
            return;
        }
    }
    static int counterOfTariffs = 1;
    private void setTariff(Connection connection, Company company, String id_company) {
        try {
            Statement stat = connection.createStatement();
            for(BaseTariff tariff : company.getTariffs()){
                String QUERY = "INSERT INTO Tariff (id_company, name, gbOfInternet, minutesWithOtherNetworks, minutesAbroad," +
                        "limitTopApps, countOfSms, price, timeAction)" +
                        "VALUES('" + id_company + "', '" + tariff.getName() + "', '" + tariff.getGbOfInternet() + "', '" +
                        tariff.getMinutesWithOtherNetwork() + "', '" + tariff.getMinutesAbroad() + "', '" +
                        tariff.getLimitTopApps() + "', '" + tariff.getCountOfSms() + "', '" + tariff.getPrice() + "', '" +
                        tariff.getTimeAction() + "')";
                stat.executeUpdate(QUERY);
                setUser(connection, tariff);
                counterOfTariffs++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
    private void addUser(Connection connection, BaseTariff tariff, String id_tariff) {
        String QUERY = "SELECT * FROM [User] WHERE id_tariff = ";
        try {
            Statement stat = connection.createStatement();
            ResultSet users = stat.executeQuery(QUERY + id_tariff);
            while (users.next()) {
                tariff.addNewUser(new User(users.getString("name"), users.getString("surname"), users.getString("telephoneNumber")));
            }
        } catch (SQLException e) {
            return;
        }
    }
    private void setUser(Connection connection, BaseTariff tariff) {
        try {
            Statement stat = connection.createStatement();

            for(User user : tariff.getUsers()){
                String QUERY = "INSERT INTO [User] ( id_tariff , name, surname, telephoneNumber)" +
                        "VALUES('" + counterOfTariffs + "', '" + user.getName() + "', '" + user.getSurname() + "', '" +
                        user.getTelephoneNumber() + "')";
                stat.executeUpdate(QUERY);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    private BaseTariff findTariff(ResultSet tariff) throws SQLException {
        String tar = tariff.getString("name");
        try {
            switch (tar) {
                case "Start tariff": return new StartTariff(tariff.getInt("minutesWithOtherNetworks"),
                        tariff.getInt("countOfSms"), tariff.getDouble("price"));

                case "Optimum tariff": return new OptimumTariff(tariff.getInt("gbOfInternet"),
                        tariff.getInt("minutesAbroad"), tariff.getString("limitTopApps"),
                        tariff.getDouble("price"), tariff.getString("timeAction"));

                case "Maximum Tariff": return new MaximumTariff(tariff.getInt("gbOfInternet"),
                        tariff.getInt("minutesWithOtherNetworks"), tariff.getDouble("price"),
                        tariff.getString("timeAction"));
                case "Annual Tariff": return new StartTariff(tariff.getInt("gbOfInternet"),
                        tariff.getInt("minutesAbroad"), tariff.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("Failed");
            e.printStackTrace();
        }
        throw new SQLException();
    }
    public void prepereAndTruncateTables(Statement stat) {
        try {
            stat.executeUpdate("ALTER Table [User] DROP CONSTRAINT [FK__User__id_tariff__286302EC] Truncate Table [User]");
            stat.executeUpdate("ALTER Table Tariff DROP CONSTRAINT [FK__Tariff__id_compa__267ABA7A] Truncate Table Tariff");
            stat.executeUpdate("Truncate Table Company");
            stat.executeUpdate("ALTER Table [User] Add CONSTRAINT [FK__User__id_tariff__286302EC] Foreign Key (id_tariff) REFERENCES Tariff(id_tariff)");
            stat.executeUpdate("ALTER Table Tariff Add CONSTRAINT [FK__Tariff__id_compa__267ABA7A] Foreign Key (id_company) REFERENCES Company(id_company)");
        } catch (SQLException e) {
            return;
        }
    }
}
