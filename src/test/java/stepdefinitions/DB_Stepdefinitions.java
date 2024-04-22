package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
import java.util.List;
import static org.junit.Assert.assertEquals;


@Data
@Slf4j
public class DB_Stepdefinitions {


    ResultSet resultSet; //select sorgulari resultset döndurur.
    PreparedStatement preparedStatement; //update sorgusunda kullandik.
    String query;
    int rowCount;
    Faker faker = new Faker();
    int id;
    String city;
    String updateLog;
    int supportMessageId;
    Manage manage = new Manage();

    @When("Database connection is established.")
    public void databaseConnectionIsEstablished() {

        DBUtils.createConnection();

    }

    @When("The database connection is closed.")
    public void theDatabaseConnectionIsClosed() {

        DBUtils.closeConnection();
    }


    //==Esra=====
    @When("Query01 is prepared and executed.")
    public void query01IsPreparedAndExecuted() throws SQLException {
        query=manage.getQuery01();
        resultSet=DBUtils.getStatement().executeQuery(query);
    }

    @When("ResultSet01 results are processed.")
    public void resultset01ResultsAreProcessed() throws SQLException {
        resultSet.next();
        String actualName=resultSet.getString("name");//yani user_id column'da 1.satirdaki degeri getir
        String  expectedName="Fashion";
        assertEquals(expectedName,actualName);
    }

    @When("Query04 is prepared and executed.")
    public void query04IsPreparedAndExecuted() throws SQLException {
        query=manage.getQuery04();
        preparedStatement=DBUtils.getPraperedStatement(query);
        //INSERT INTO contacts (id,name,email,query_type,message) VALUES (?,?,?,?,?)
        preparedStatement.setInt(1,faker.number().numberBetween(100,900));
        preparedStatement.setString(2,"Fransa");
        preparedStatement.setString(3,"akdeniz@gmail.com");
        preparedStatement.setString(4,"customer");
        preparedStatement.setString(5,"DB testi basladi");
        rowCount=preparedStatement.executeUpdate();
    }
    @When("Query05 is prepared and executed.")
    public void query05IsPreparedAndExecuted() throws SQLException {
        query=manage.getQuery05();
        preparedStatement=DBUtils.getPraperedStatement(query);
        preparedStatement.setString(1,"Herkese kolay gelsin");
        rowCount=preparedStatement.executeUpdate();
    }





    @When("Query07 is prepared and executed.")
    public void query07_is_prepared_and_executed() throws SQLException {

        query = manage.getQuery07();
        resultSet = DBUtils.getStatement().executeQuery(query);

    }
    @When("ResultSet07 results are processed.")
    public void result_set07_results_are_processed() throws SQLException {

        boolean hasFive = false;
        List<String> phoneNumbers = new ArrayList<>();
        while (resultSet.next()) {
            phoneNumbers.add(resultSet.getString("phone"));
        }
        // boolean hasFive = phoneNumbers.stream().anyMatch(phone -> phone.contains("5"));
        for (String phone : phoneNumbers) {
            if (phone.contains("5")) {
                hasFive = true;
                break;  // Stop the loop once a '5' is found
            }
        }
    }
    @Given("Query02 is prepared and executed.")
    public void query02_is_prepared_and_executed() throws SQLException {

        query= manage.getQuery02();
        preparedStatement = DBUtils.getPraperedStatement(query);
        preparedStatement.setInt(1,100008);
        preparedStatement.setString(2,"Cannes");
        preparedStatement.setInt(3,110);
        preparedStatement.setInt(4,1);
        preparedStatement.setDate(5,Date.valueOf(LocalDate.now()));
        rowCount = preparedStatement.executeUpdate();

    }
    @Given("ResultSet02 results are processed.")
    public void result_set02_results_are_processed() {
        assertEquals(1,rowCount);

    }

    @Given("Query03 is prepared and executed.")
    public void query03_is_prepared_and_executed() throws SQLException {

        city=faker.options().option("London", "Birmingham", "Milton Keynes", "Manchester", "Glasgow");
        id=faker.number().numberBetween(90010,100010);
        query= manage.getQuery03();
        preparedStatement = DBUtils.getPraperedStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,city);
        preparedStatement.setInt(3,115);
        preparedStatement.setInt(4,1);
        preparedStatement.setDate(5, Date.valueOf(LocalDate.now()));
        rowCount = preparedStatement.executeUpdate();
       /* int flag=0;
        if (rowCount>0){
            flag++;
        }
        rowCount = 0;
        assertEquals(1,flag);*/
        System.out.println("id : "+id);


    }

    @Given("Delete the data on Query03 and verify deletion")
    public void delete_the_data_on_query03_and_verify_deletion() throws SQLException {
        query=manage.getPreparedQuery03Delete();
        preparedStatement =DBUtils.getPraperedStatement(query);
        preparedStatement.setInt(1,id);
        rowCount = preparedStatement.executeUpdate();
        System.out.println("deleted id :"+id);
        //assertEquals(1,rowCount);
    }


}
