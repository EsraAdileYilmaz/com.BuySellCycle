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

import java.sql.*;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @When("UpdateQuery04 is prepared and executed.")
    public void query05IsPreparedAndExecuted() throws SQLException {
        query = manage.getUpdateQuery04();
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
        city = faker.options().option("London", "Birmingham", "Milton Keynes", "Manchester", "Glasgow");
        id = faker.number().numberBetween(100011, 100099);
        preparedStatement = DBUtils.getPraperedStatement(manage.getQuery02());
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, city);
        preparedStatement.setInt(3, 110);
        preparedStatement.setInt(4, 1);
        preparedStatement.setDate(5, Date.valueOf(LocalDate.now()));
        rowCount = preparedStatement.executeUpdate();
        System.out.println("id : " + id);
    }

    @Given("I add this data to the cities table and verify.")
    public void I_add_this_data_to_the_cities_table_and_verify() {

        assertEquals(1, rowCount);
    }

    @Given("I have a city with id and name in the cities table")
    public void i_have_a_city_with_id_and_name_in_the_cities_table() throws SQLException {
        String cityId = ConfigReader.getProperty("cityId");
        String cityName = ConfigReader.getProperty("cityName");
        int stateID = 119;
        int status = 1;
        preparedStatement = DBUtils.getPraperedStatement(manage.getQuery03());
        preparedStatement.setInt(1, Integer.parseInt(cityId));
        preparedStatement.setString(2, cityName);
        preparedStatement.setInt(3, stateID); // Assuming state_id is 1
        preparedStatement.setInt(4, status); // Assuming status is true
        preparedStatement.setDate(5, Date.valueOf(LocalDate.now()));
        rowCount = preparedStatement.executeUpdate();
        System.out.println("id : " + id);
        assertEquals(1, rowCount);
    }

    @When("I delete the city with id and name")
    public void i_delete_the_city_with_id_and_name() throws SQLException {
        String cityId = ConfigReader.getProperty("cityId");
        String cityName = ConfigReader.getProperty("cityName");
        preparedStatement = DBUtils.getPraperedStatement(manage.getPreparedQuery03Delete());
        preparedStatement.setInt(1, Integer.parseInt(cityId));
        preparedStatement.setString(2, cityName);
        preparedStatement.executeUpdate();

    }

    @Then("The city with id and name should no longer exist in the table")
    public void the_city_with_id_and_name_should_no_longer_exist_in_the_table() throws SQLException {
        String cityId = ConfigReader.getProperty("cityId");
        String cityName = ConfigReader.getProperty("cityName");
        preparedStatement = DBUtils.getPraperedStatement(manage.getSelectQuery03());
        preparedStatement.setInt(1, Integer.parseInt(cityId));
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
        query = manage.getQuery30();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }

    @Given("ResultSet30 results are processed.")
    public void result_set30_results_are_processed() {
        System.out.println("total price");
    }

    @Given("Query25 is prepared and executed.")
    public void query25_is_prepared_and_executed() throws SQLException {
        query = manage.getQuery25();
        resultSet = DBUtils.getStatement().executeQuery(query);
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
        while (resultSet.next()) {
            double amount = resultSet.getDouble("total_amount");
            totalAmount += amount;
        }
        System.out.println("Total amount for referrals with IDs between 10 and 20: " + totalAmount);
    }

    @When("Query09 is prepared to calculate on log_activity and executed")
    public void query09_is_prepared_and_executed() throws SQLException {
        query = manage.getPreparedQuery09();
        preparedStatement = DBUtils.getPraperedStatement(query);
        preparedStatement.setString(1, "46.2.239.35");
        resultSet = preparedStatement.executeQuery();
    }

    @Then("ResultSet09 results are processed.")
    public void result_set09_results_are_processed() throws SQLException {
            resultSet.next();
            int actualTotalCount = resultSet.getInt("total_count");
            int expectedCount = 0;
            Assert.assertEquals( actualTotalCount,expectedCount,"Total count should be 0.");

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

    @Given("Query12 is prepared and executed.")
    public void query12_is_prepared_and_executed() throws SQLException {
        query = manage.getQuery12();
        preparedStatement = DBUtils.getPraperedStatement(query);
        resultSet = preparedStatement.executeQuery();
    }

    @Given("ResultSet028 results are processed.")
    public void result_set028_results_are_processed() throws SQLException {
        while (resultSet.next()) {
            int userId = resultSet.getInt("user_id");
            System.out.println("User ID: " + userId);
        }

    }

    @Given("ResultSet12 results are processed.")
    public void result_set12_results_are_processed() throws SQLException {
        Map<String, List<String>> notes = new HashMap<>();
        Map<String, List<String>> dates = new HashMap<>();
        while (resultSet.next()) {
            String day = resultSet.getString("dates");
            String[] uniqueNotes = resultSet.getString("unique_notes").split(", ");
            String[] uniqueDays = resultSet.getString("dates").split(", ");

            //Unique notes processed for each day
            String[] uniqueNotesArray = uniqueNotes;
            for (String note : uniqueNotes) {
                notes.computeIfAbsent(day, k -> new ArrayList<>()).add(note);
            }

            // Unique days processed for each note
            String[] uniqueDaysArray = uniqueDays;
            for (String note : uniqueNotes) {
                dates.computeIfAbsent(note, k -> new ArrayList<>()).addAll(Arrays.asList(uniqueDaysArray));
            }
        }
        // Write days for each each unique notes
        for (Map.Entry<String, List<String>> entry : notes.entrySet()) {
            String note = entry.getKey();
            List<String> day = entry.getValue();
            System.out.println("Days: " + note + ", Unique Notes: " + String.join(", ", day));
        }
    }

    @When("Query10 is prepared to calculate on order_address_details and executed")
    public void queryIsPreparedAndExecuted() throws SQLException {
        query = manage.getQuery10();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }
    @Then("ResultSet10 results are processed.")
    public void resultsetResultsAreProcessed() throws SQLException {
        resultSet.next();
        int actualUserCount = resultSet.getInt("user_count");
        int expectedUserCount = 2;
        Assert.assertEquals(actualUserCount, expectedUserCount, "The user count should match the expected count(2).");
    }
    @Given("Query13 is prepared and executed.")
    public void query13_is_prepared_and_executed () throws SQLException {
            query = manage.getQuery13();
            resultSet = DBUtils.getStatement().executeQuery(query);
    }
    @Given("ResultSet13 results are processed.")
    public void result_set13_results_are_processed () throws SQLException {

      List<String> productsNotCoupon = new ArrayList<>();
      // To retrieve the first 3 records, resultSet.next() is called 3 times.
       for (int i = 0; i < 3 && resultSet.next(); i++) {
            productsNotCoupon.add(resultSet.getString("product_id"));
        }
        System.out.println(productsNotCoupon);
    }

        @Given("Query19 is prepared and executed.")
        public void query19_is_prepared_and_executed () throws SQLException {
            query = manage.getQuery19();
            resultSet = DBUtils.getStatement().executeQuery(query);
        }
    @Given("ResultSet19 results are processed.")
     public void result_set19_results_are_processed () {
      assertEquals(0, rowCount);
    }
    @Given("Query29 is prepared and executed.")
      public void query29_is_prepared_and_executed () throws SQLException {
        query = manage.getQuery29();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }
    @Given("ResultSet29 results are processed.")
     public void result_set29_results_are_processed () throws SQLException {
       resultSet.next();
       String actualAverage = resultSet.getString("average_grand_total");
       String expectedAverage = "176420.36284403672";
       assertEquals(expectedAverage, actualAverage);
    }
    @When("Query23 is prepared to calculate for module value is not null and execute")
    public void query23_is_prepared_to_calculate_for_module_value_is_not_null_and_execute() throws SQLException {
       // query = manage.getQuery23();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }
    @Then("Process result and verify the result")
    public void process_result_and_verify_the_result() throws SQLException {
        resultSet.next();
        int actualUserCount = resultSet.getInt("type_count");
        int expectedUserCount = 6;
        Assert.assertEquals(actualUserCount, expectedUserCount, "The type_count should match the expected count(6).");
    }



    @Given("Query08 is prepared to select the first five names from delivery_processes and executed.")
    public void query08_is_prepared_to_select_the_first_five_names_from_delivery_processes_and_executed() {



    }
    @Then("The results Query08 should be in reverse order: Shipped, Received, Processing, Pending, Delivered.")
    public void The_results_Query08_should_be_in_reverse_order_shipped_received_processing_pending_delivered() {




    }




    @Given("Query14 is prepared and executed.")
    public void query14_is_prepared_and_executed() throws SQLException{
      query = manage.getQuery014();
        preparedStatement = DBUtils.getPraperedStatement(query);
        resultSet = preparedStatement.executeQuery();
    }
    @Given("ResultSet14 results are processed.")
    public void result_set14_results_are_processed() throws SQLException {
        resultSet.next();
    Assert.assertTrue(resultSet.getInt(1)>0);


    }
    @Given("Query20 is prepared and executed.")
    public void query20_is_prepared_and_executed() throws SQLException {

        query = manage.getQuery20();
        preparedStatement = DBUtils.getPraperedStatement(query);

        for (int i = 0; i < 10; i++) {

            preparedStatement.setInt(1,  faker.number().numberBetween(200, 300));
            preparedStatement.setInt(2, 3001);
            preparedStatement.setString(3, faker.lorem().characters());
            preparedStatement.setDate(4, Date.valueOf(LocalDate.now()));
            preparedStatement.setDate(5, Date.valueOf(LocalDate.now()));
            rowCount = preparedStatement.executeUpdate();

        }

    }
    @Given("ResultSet20 results are processed.")
    public void result_set20_results_are_processed() {
        Assert.assertEquals(1, rowCount);


    }


      @Given("Query15 is prepared and executed.")
      public void query15_is_prepared_and_executed() throws SQLException {
          query = manage.getQuery15();
          resultSet = DBUtils.getStatement().executeQuery(query);
      }

      @When("ResultSet15 results are processed.")
      public void result_set15_results_are_processed() throws SQLException {
        /*List<Object> customerUsersList=new ArrayList<>();
          for (int i = 0; i <customerUsersList.size() ; i++) {
            customerUsersList.add(resultSet.getObject(i));
          }
          System.out.println("Customer coupon stories= " +customerUsersList);*/
          List<Object> customerUsersList = new ArrayList<>();
          ResultSetMetaData metaData = resultSet.getMetaData();
          int columnCount = metaData.getColumnCount();

          while (resultSet.next()) {
              // Her bir satır için bir Object listesi oluştur
              List<Object> row = new ArrayList<>();
              for (int i = 1; i <= columnCount; i++) {
                  // Her sütunun değerini alıp listeye ekle
                  row.add(resultSet.getObject(i));
              }
              // Oluşturulan satırı genel listeye ekle
              customerUsersList.add(row);
          }

          System.out.println("Customer coupon stories: " + customerUsersList);
      }


    @Given("Query21 is prepared and executed.")
    public void query21_is_prepared_and_executed_for_each_order_id() {
        int[] orderIds = {2, 23, 62, 78, 91, 92, 115, 116, 118, 129, 139, 149, 181};

        for (int orderId : orderIds) {
            try {
                query = manage.getPreparedQuery21();
                preparedStatement = DBUtils.getPraperedStatement(query);
                preparedStatement.setInt(1, orderId);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int numOrders = resultSet.getInt("num_orders");
                    log.info("Order ID: {}, Number of Orders: {}", orderId, numOrders);
                }
            } catch (SQLException e) {
                log.error("Error executing Query21 for Order ID {}: {}", orderId, e.getMessage());
            }
        }
    }


    @Given("ResultSet21 results are processed.")
    public void result_set21_results_are_processed_for_each_order_id() {
        int[] orderIds = {2, 23, 62, 78, 91, 92, 115, 116, 118, 129, 139, 149, 181};
        String newShippingName = "NewShippingName"; // Example new shipping name

        for (int orderId : orderIds) {
            try {

                query = manage.getPreparedQuery21();
                preparedStatement = DBUtils.getPraperedStatement(query);
                preparedStatement.setInt(1, orderId);
                resultSet = preparedStatement.executeQuery();


                if (resultSet.next()) {
                    int numOrders = resultSet.getInt("num_orders");

                    if (numOrders > 0) {

                        query = manage.getResultGetQuery21();
                        preparedStatement = DBUtils.getPraperedStatement(query);
                        preparedStatement.setString(1, newShippingName);
                        preparedStatement.setInt(2, orderId);


                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            log.info("Shipping name updated successfully for Order ID: {}", orderId);
                        } else {
                            log.error("No rows updated for Order ID: {}", orderId);
                        }
                    } else {
                        log.error("No orders found for Order ID: {}", orderId);
                    }
                } else {
                    log.error("No result obtained from Query21 for Order ID: {}", orderId);
                }
            } catch (SQLException e) {
                log.error("Error processing ResultSet21 for Order ID {}: {}", orderId, e.getMessage());

            }
        }
    }



    @Given("Query22 is prepared and executed.")
    public void data_is_entered_into_the_digital_gift_cards_table() {
        try {

            int id = 3626;
            String giftName = faker.lorem().words(2).toString();
            String descriptionOne = faker.lorem().sentence();
            String thumbnailImageOne = faker.internet().image();
            String thumbnailImageTwo = faker.internet().image();


            query = "INSERT INTO digital_gift_cards (id, gift_name, descriptionOne, thumbnail_image_one, thumbnail_image_two) " +
                    "VALUES (?, ?, ?, ?, ?)";
            preparedStatement = DBUtils.getPraperedStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, giftName);
            preparedStatement.setString(3, descriptionOne);
            preparedStatement.setString(4, thumbnailImageOne);
            preparedStatement.setString(5, thumbnailImageTwo);


            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                log.info("Data inserted successfully into digital_gift_cards table.");
            } else {
                log.error("Failed to insert data into digital_gift_cards table.");
            }
        } catch (SQLException e) {
            log.error("Error inserting data into digital_gift_cards table: {}", e.getMessage());

        }
    }


    @Given("Query24 is prepared and executed.")
    public void query24_is_prepared_and_executed() {
        try {
            query = "SELECT * FROM orders " +
                    "WHERE customer_email NOT LIKE '%customer%' AND sub_total < 2000 " +
                    "ORDER BY order_number DESC";

            log.info("Executing SQL query: {}", query);

            preparedStatement = DBUtils.getPraperedStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            log.error("Error preparing or executing Query24: {}", e.getMessage());
        }
    }

    @Given("ResultSet24 results are processed")
    public void result_set24_results_are_processed() {
        try {
            query = "SELECT * FROM orders " +
                    "WHERE customer_email NOT LIKE '%customer%' AND sub_total < 2000 " +
                    "ORDER BY order_number DESC";

            boolean hasResults = false;
            while (resultSet.next()) {
                hasResults = true;
                long id = resultSet.getLong("id");
                String customerEmail = resultSet.getString("customer_email");
                double subTotal = resultSet.getDouble("sub_total");
                long orderNumber = resultSet.getLong("order_number");
                System.out.println("Data retrieved and reversed successfully:");
                log.info("ID: {}, Customer Email: {}, Sub Total: {}, Order Number: {}", id, customerEmail, subTotal, orderNumber);
            }

            if (!hasResults) {
                System.out.println("No data found matching the criteria.");
                log.info("No data found matching the criteria.");
            } else {
                System.out.println("Data retrieval and processing completed successfully.");
                log.info("Data retrieval and processing completed successfully.");
            }
        } catch (SQLException e) {
            log.error("Error processing ResultSet24: {}", e.getMessage());
        } finally {
            DBUtils.closeResultSet(resultSet);
            DBUtils.closeStatement(preparedStatement);
        }
    }

}




