package stepdefinitions;

import com.github.javafaker.Faker;
import config_Requirements.ConfigReader;
import io.cucumber.java.en.*;
import manage.Manage;
import org.testng.Assert;
import io.cucumber.java.sl.In;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import manage.Manage;

import utilities.DB_Utilities.DBUtils;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;


@Data
@Slf4j
@Getter
@Setter
public class DB_Stepdefinitions {

    Manage manage = new Manage();
    Faker faker = new Faker();
    ResultSet resultSet; //select sorgulari resultset döndurur.
    PreparedStatement preparedStatement; //update sorgusunda kullandik.
    String query;
    int rowCount;
    int id;
    String city;
    String updateLog;
    int supportMessageId;
    int count;

    @When("Database connection is established.")
    public void databaseConnectionIsEstablished() {
        DBUtils.createConnection();
    }

    @When("The database connection is closed.")
    public void theDatabaseConnectionIsClosed() {
        DBUtils.closeConnection();
    }

    @When("Query01 is prepared and executed.")
    public void query01IsPreparedAndExecuted() throws SQLException {
        query = manage.getQuery01();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }

    @When("ResultSet01 results are processed.")
    public void resultset01ResultsAreProcessed() throws SQLException {
        resultSet.next();
        String actualName = resultSet.getString("name");//yani user_id column'da 1.satirdaki degeri getir
        String expectedName = "Fashion";
        assertEquals(expectedName, actualName);
    }

    @When("Query04 is prepared and executed.")
    public void query04IsPreparedAndExecuted() throws SQLException {
        query = manage.getQuery04();
        preparedStatement = DBUtils.getPraperedStatement(query);
        //INSERT INTO contacts (id,name,email,query_type,message) VALUES (?,?,?,?,?)
        preparedStatement.setInt(1, faker.number().numberBetween(100, 900));
        preparedStatement.setString(2, "Fransa");
        preparedStatement.setString(3, "akdeniz@gmail.com");
        preparedStatement.setString(4, "customer");
        preparedStatement.setString(5, "DB testi basladi");
        rowCount = preparedStatement.executeUpdate();
    }

    @When("Query05 is prepared and executed.")
    public void query05IsPreparedAndExecuted() throws SQLException {
        query = manage.getQuery05();
        preparedStatement = DBUtils.getPraperedStatement(query);
        rowCount = preparedStatement.executeUpdate();
    }

    @When("Query27 is prepared and executed.")
    public void query27IsPreparedAndExecuted() throws SQLException {
        query = manage.getQuery27();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }

    @When("ResultSet27 results are processed.")
    public void resultset27ResultsAreProcessed() throws SQLException {
        resultSet.next();
        List<String> descriptions = new ArrayList<>();
        descriptions.add(resultSet.getString("description"));
        System.out.println(descriptions);
    }

    @When("Query07 is prepared to select phone numbers from the first three entries of customer_addresses.")
    public void query07_is_prepared_and_executed() throws SQLException {

        resultSet = DBUtils.getStatement().executeQuery(manage.getQuery07());
    }

    @When("The phone data should be checked for the presence of digit '5'.")

    public void result_set07_results_are_processed() throws SQLException {
        int count = 0;
        boolean containsFive = true; // Tüm numaraların '5' içerdiğini varsayarak başla.

        while (resultSet.next() && count < 3) { // Sadece ilk üç kaydı kontrol et.
            String phone = resultSet.getString("phone");
            System.out.println("Phone number " + (count + 1) + ": " + phone);
            if (!phone.contains("5")) {
                containsFive = false; // Eğer '5' içermeyen bir telefon numarası bulunursa, false yap.
                break; // '5' içermeyen bir numara bulunduğunda daha fazla kontrol yapmaya gerek yok.
            }
            count++; // Kayıtlı telefon numarası sayısını artır.
        }

        assert containsFive; // Tüm kontrol edilen numaraların '5' içerdiğini doğrula
    }

    @Given("I have the data ready for a new city and executed.")
    public void I_have_the_data_ready_for_a_new_city_and_executed() throws SQLException {
        city=faker.options().option("London", "Birmingham", "Milton Keynes", "Manchester", "Glasgow");
        id=faker.number().numberBetween(100011,100099);
        preparedStatement = DBUtils.getPraperedStatement(manage.getQuery02());
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,city);
        preparedStatement.setInt(3,110);
        preparedStatement.setInt(4,1);
        preparedStatement.setDate(5,Date.valueOf(LocalDate.now()));
        rowCount = preparedStatement.executeUpdate();
        System.out.println("id : " + id);
    }

    @Given("I add this data to the cities table and verify.")
    public void I_add_this_data_to_the_cities_table_and_verify() {

        assertEquals(1,rowCount);
    }

    @Given("I have a city with id and name in the cities table")
    public void i_have_a_city_with_id_and_name_in_the_cities_table() throws SQLException {
        String cityId = ConfigReader.getProperty("cityId");
        String cityName = ConfigReader.getProperty("cityName");
        int stateID=119;
        int status=1;
        preparedStatement = DBUtils.getPraperedStatement(manage.getQuery03());
        preparedStatement.setInt(1,Integer.parseInt(cityId));
        preparedStatement.setString(2, cityName);
        preparedStatement.setInt(3, stateID); // Assuming state_id is 1
        preparedStatement.setInt(4, status); // Assuming status is true
        preparedStatement.setDate(5,Date.valueOf(LocalDate.now()));
        rowCount = preparedStatement.executeUpdate();
        System.out.println("id : "+id);
        assertEquals(1,rowCount);

    }
    @When("I delete the city with id and name")
    public void i_delete_the_city_with_id_and_name() throws SQLException {
        String cityId = ConfigReader.getProperty("cityId");
        String cityName = ConfigReader.getProperty("cityName");
        preparedStatement = DBUtils.getPraperedStatement(manage.getPreparedQuery03Delete());
        preparedStatement.setInt(1,Integer.parseInt(cityId));
        preparedStatement.setString(2, cityName);
        preparedStatement.executeUpdate();


    }
    @Then("The city with id and name should no longer exist in the table")
    public void the_city_with_id_and_name_should_no_longer_exist_in_the_table() throws SQLException {
        String cityId = ConfigReader.getProperty("cityId");
        String cityName = ConfigReader.getProperty("cityName");

        preparedStatement = DBUtils.getPraperedStatement(manage.getSelectQuery03());
        preparedStatement.setInt(1,Integer.parseInt(cityId));
        preparedStatement.setString(2, cityName);
        ResultSet resultset = preparedStatement.executeQuery();
        count = 0;
        if (resultset.next()) {
            count = resultset.getInt("count");
        }
        Assert.assertEquals(0, count);

    }

    @Given("Query30 is prepared and executed.")
    public void query30_is_prepared_and_executed() throws SQLException {
        query=manage.getQuery30();
        resultSet=DBUtils.getStatement().executeQuery(query);
    }
    @Given("ResultSet30 results are processed.")
    public void result_set30_results_are_processed() {
        System.out.println("total price");
    }
    @Given("Query25 is prepared and executed.")
    public void query25_is_prepared_and_executed() throws SQLException {
        query = manage.getQuery25();
        resultSet=DBUtils.getStatement().executeQuery(query);
    }
    @Given("ResultSet25 results are processed.")
    public void result_set25_results_are_processed() throws SQLException {
        Map<String, Double> resultMap = new LinkedHashMap<>();
        while (resultSet.next()) {
            String txnId = resultSet.getString(("txn_id"));
            double amount = resultSet.getDouble("amount");
            resultMap.put(txnId, amount);
        }
        //To group and sort of the result
        System.out.println("txn_id - amount");
        for (Map.Entry<String, Double> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }




    @Given("Query011 is prepared and executed.")
    public void query011_is_prepared_and_executed() throws SQLException {
        query = manage.getQuery011();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }

    @Given("ResultSet011 results are processed.")
    public void result_set011_results_are_processed() throws SQLException {
        double totalAmount = 0;


        while(resultSet.next()) {
            double amount = resultSet.getDouble("total_amount");
            totalAmount += amount;
        }
        System.out.println("Total amount for referrals with IDs between 10 and 20: " + totalAmount);
    }



    @When("Query09 is prepared and executed.")
    public void query09_is_prepared_and_executed() throws SQLException {
        query = manage.getPreparedQuery09();
        preparedStatement = DBUtils.getPraperedStatement(query);
        preparedStatement.setString(1, "46.2.239.35");
        resultSet = preparedStatement.executeQuery();
    }



    @Then("ResultSet09 results are processed.")
    public void result_set09_results_are_processed() throws SQLException {
        if (resultSet.next()) {
            int totalCount = resultSet.getInt("total_count");
            //Assert.assertEquals( "Total count should be 0.",0,totalCount);
        }


    }
    @Given("Query026 is prepared and executed.")
    public void query026_is_prepared_and_executed() throws SQLException {
        query=manage.getQuery026();
        resultSet = DBUtils.getStatement().executeQuery(query);

    }
    @Given("ResultSet026 results are processed.")
    public void result_set026_results_are_processed() throws SQLException {
        while (resultSet.next()) {
            String paymentMethod = resultSet.getString("payment_method");
            double totalAmount = resultSet.getDouble("total_amount");

            System.out.println("Payment Method: " + paymentMethod + ", Total Amount: " + totalAmount);
        }

    }
    @Given("Query028 is prepared and executed.")
    public void query028_is_prepared_and_executed() throws SQLException {
       query= manage.getQuery028();
       resultSet=DBUtils.getStatement().executeQuery(query);
    }

    @Given("ResultSet028 results are processed.")
    public void result_set028_results_are_processed() throws SQLException {
        while (resultSet.next()) {
            int userId = resultSet.getInt("user_id");
            System.out.println("User ID: " + userId);
        }

    }




    @Given("Query13 is prepared and executed.")
    public void query13_is_prepared_and_executed() throws SQLException {
        query = manage.getQuery13();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }
    @Given("ResultSet13 results are processed.")
    public void result_set13_results_are_processed() {

    }
    @Given("Query19 is prepared and executed.")
    public void query19_is_prepared_and_executed() throws SQLException {
        query = manage.getQuery19();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }
    @Given("ResultSet19 results are processed.")
    public void result_set19_results_are_processed() {

        assertEquals(0,rowCount);
    }
    @Given("Query29 is prepared and executed.")
    public void query29_is_prepared_and_executed() throws SQLException {
        query = manage.getQuery29();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }
    @Given("ResultSet29 results are processed.")
    public void result_set29_results_are_processed() {

    }

}



