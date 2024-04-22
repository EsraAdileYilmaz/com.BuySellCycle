package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.When;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import manage.Manage;
import utilities.DB_Utilities.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    String version;
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




}
