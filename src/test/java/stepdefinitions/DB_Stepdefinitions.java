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
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;


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
    String email;
    PreparedStatement deleteStatement ;
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
        log.info("total price");
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
        log.info("txn_id - amount");
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
            log.info("Days: " + note + ", Unique Notes: " + String.join(", ", day));
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

    @Given("Query19Update is prepared and executed.")
    public void query19Update_is_prepared_and_executed () throws SQLException {
        query = manage.getQuery19Update();
        preparedStatement = DBUtils.getPraperedStatement(query);
        rowCount = preparedStatement.executeUpdate();
    }

    @When("Query19 is prepared and executed.")
    public void query19_is_prepared_and_executed() throws SQLException {
        query = manage.getQuery19();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }

    @Then("I verify that no records have negative opening balance")
    public void i_verify_that_no_records_have_negative_opening_balance() throws SQLException {
       boolean hasNegativeBalance = false;

        while (resultSet.next()) {
            if (resultSet.getDouble("opening_balance") < 0) {
                hasNegativeBalance = true;

            }
        }

        assertFalse(!hasNegativeBalance);
       /*boolean hasNegativeBalance = false;

        while (resultSet.next()) {
            double openingBalance = resultSet.getDouble("opening_balance");
            if (openingBalance < 0) {
                hasNegativeBalance = true;
                break; // Negatif bakiye bulunduğunda döngüyü sonlandır
            }
        }

        assertFalse(!hasNegativeBalance);*/
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
        query = manage.getQuery23();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }

    @Then("Process result and verify the result")
    public void process_result_and_verify_the_result() throws SQLException {
        resultSet.next();
        int actualUserCount = resultSet.getInt("type_count");
        int expectedUserCount = 6;
        log.info("The type_count should match the expected count(6).");
        Assert.assertEquals(actualUserCount, expectedUserCount);
    }

    @Given("Prepare a query that adds datas to the bank_accounts table in bulk.")
    public void prepare_a_query_that_adds_datas_to_the_bank_accounts_table_in_bulk() throws SQLException {
        query = manage.getQuery18();
        preparedStatement = DBUtils.getPraperedStatement(query);
        //resultSet = preparedStatement.executeQuery();
    }
    @Given("Enter the data in bulk. Check that it is added to the table.")
    public void enter_the_data_in_bulk_check_that_it_is_added_to_the_table() throws SQLException {
        try (Connection connection = DBUtils.getStatement().getConnection()) {
            PreparedStatement stm = connection.prepareStatement("Insert Into bank_accounts (id,bank_name,branch_name,account_name,account_number,opening_balance,description,status) Values(?,?,?,?,?,?,?,?)");

            for (int i = 0; i < 4; i++) {
                stm.setInt(1, (int) faker.number().numberBetween(30000, 9000000));
                stm.setString(2, faker.company().name());
                stm.setString(3, faker.lorem().word()); // branch_name
                stm.setString(4, faker.lorem().word()); // account_name
                stm.setString(5, faker.numerify("#######")); // account_number
                stm.setDouble(6, faker.random().nextDouble()); // opening_balance
                stm.setString(7, faker.lorem().word()); // description
                stm.setString(8, String.valueOf(faker.number().randomDigitNotZero())); // status

                stm.addBatch();

            }

            int[] affectedRows = new int[]{stm.executeUpdate()};


            log.info("Eklenen veriler:");
            for (int rowCount : affectedRows) {
                log.info("Etkilenen satır sayısı: " + rowCount);
            }

        }
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

    @Given("Query08 is prepared to select the first five names from delivery_processes and executed.")
    public void query08_is_prepared_to_select_the_first_five_names_from_delivery_processes_and_executed() throws SQLException {

        resultSet = DBUtils.getStatement().executeQuery(manage.getQuery08());
    }

    @Then("The results Query08 should be in reverse order: Shipped, Received, Processing, Pending, Delivered.")
    public void The_results_Query08_should_be_in_reverse_order_shipped_received_processing_pending_delivered() throws SQLException {

        List<String> expectedOrder = Arrays.asList("Delivered, Recieved, Shipped, Processing, Pending");
        List<String> actualOrder = new ArrayList<>();

        while (resultSet.next()) {
            actualOrder.add(resultSet.getString("name"));
        }
        System.out.println("before reverse : " + actualOrder);
        Collections.reverse(actualOrder);
        System.out.println("expected result : " + expectedOrder);
        System.out.println("actual result : " + actualOrder);
        assert actualOrder.equals(expectedOrder) : "The data names are not in the expected reverse order.";

    }

    @When("I delete the added contact with email from the table")
    public void iDeleteTheAddedContactWithEmailFromTheTable() throws SQLException {

        deleteStatement = DBUtils.getPraperedStatement(manage.getQuery05DeleteAddedContact());
        deleteStatement.setString(3, email);
        deleteStatement.executeUpdate();

    }

    @Then("I verify that the contact data with email is no longer exist in the table")
    public void iVerifyThatTheContactDataWithEmailIsNoLongerExistInTheTable() throws SQLException {
        ResultSet resultset = deleteStatement.executeQuery();
        count = 0;
        if (resultset.next()) {
            count = resultset.getInt("count");
        }
        Assert.assertEquals(0, count);
    }

    @Given("Query_{int} is prepared and executed.")
    public void query_IsPreparedAndExecuted() throws SQLException {
        query = manage.getQuery05AddAContact();
        email = faker.internet().emailAddress();
        id = faker.number().numberBetween(100, 900);
        preparedStatement = DBUtils.getPraperedStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, faker.name().firstName());
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, "customer");
        preparedStatement.setString(5, "Hi there");
        rowCount = preparedStatement.executeUpdate();

        assertTrue("An error occurred while inserting data.", rowCount > 0);
    }


    @When("the user queries ids with  with shipping_address=Switzerland in the order_address_details table according to the orders table. to list orders shipped to Switzerland according to the orders table.")
    public void theUserQueriesIdsWithWithShipping_addressSwitzerlandInTheOrder_address_detailsTableAccordingToTheOrdersTableToListOrdersShippedToSwitzerlandAccordingToTheOrdersTable() throws SQLException {
        query = manage.getQuery16Join();
        resultSet = DBUtils.getStatement().executeQuery(query);

    }

    @And("The user list the ids from the resultset")
    public void theUserListTheIdsFromTheResultset() throws SQLException {
        List<Object> idList = new ArrayList<>();
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
            idList.add(row);
        }

        System.out.println(": " + idList);
    }

    @When("Query17 is prepared  for users and attendances table and execute")
    public void queryIsPreparedForUsersAndAttendancesTableAndExecute() throws SQLException {
        query = manage.getQuery17();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }

    @Then("Process result and verify the email address")
    public void processResultAndVerifyTheEmailAddress() throws SQLException {
        if (!resultSet.next()) {
            log.info("There is no e-mail address that meets this condition.");

        } else {

            String actualEmail = resultSet.getString("email");
            log.info("It works for UserId = 3");
            String expectedUserEmail = "seller@gmail.com";
            Assert.assertEquals(actualEmail, expectedUserEmail, "The actual email should match with expected");
        }
    }

    @Given("I query to group the coupon_products table by coupon_id")
    public void iQueryToGroupTheCoupon_productsTableByCoupon_id() throws SQLException {
        query = manage.getQuery06GroupId();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }

    @When("I find out how many products for each coupon")
    public void iFindOutHowManyProductsForEachCoupon() throws SQLException {
        while (resultSet.next()) {
            int couponId = resultSet.getInt("coupon_id");
            int productCount = resultSet.getInt("product_count");
            System.out.println("Coupon ID: " + couponId + ", Product Count: " + productCount);
        }

    }

    @Given("Query21 is prepared and executed.")
    public void query21_is_prepared_and_executed() {
        try {
            query = manage.getQuery21() ;

            preparedStatement = DBUtils.getPraperedStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            log.error("Error preparing or executing Query21: {}", e.getMessage());
        }
    }

    @Given("ResultSet21 results are processed.")
    public void result_set21_results_are_processed() {
        try {
            if (resultSet.next()) {
                int totalOrders = resultSet.getInt("total_orders");
                System.out.println("Total orders placed according to the specified order_id: " + totalOrders);
                log.info("Total orders placed according to the specified order_id: {}", totalOrders);
            }
        } catch (SQLException e) {
            log.error("Error processing ResultSet21: {}", e.getMessage());
        } finally {
            DBUtils.closeResultSet(resultSet);
            DBUtils.closeStatement(preparedStatement);
        }
    }

    @Given("Query22 is prepared and executed.")
    public void data_is_entered_into_the_digital_gift_cards_table() {
        try {

            int id = 3726;
            String giftName = faker.lorem().words(2).toString();
            String descriptionOne = faker.lorem().sentence();
            String thumbnailImageOne = faker.internet().image();
            String thumbnailImageTwo = faker.internet().image();


            query = manage.getPreparedQuery22();
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

    @Given("ResultSet22 results are processed.")
    public void delete_inserted_data_from_digital_gift_cards_table() {
        try {
            query = manage.getResultQuery22();

            int insertedId = 3726;

            preparedStatement = DBUtils.getPraperedStatement(query);
            preparedStatement.setInt(1, insertedId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                log.info("Inserted data successfully deleted from digital_gift_cards table.");
            } else {
                log.error("Failed to delete inserted data from digital_gift_cards table.");
            }
        } catch (SQLException e) {
            log.error("Error deleting inserted data from digital_gift_cards table: {}", e.getMessage());
        } finally {
            DBUtils.closeStatement(preparedStatement);
        }
    }


    @Given("Query24 is prepared and executed.")
    public void query24_is_prepared_and_executed() {
        try {
            query = manage.getPreparedQuery24();

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
            query = manage.getResultQuery24();

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





