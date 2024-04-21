package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.When;
import manage.Manage;
import utilities.DB_Utilities.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    Manage queryManage = new Manage();

    @When("Database connection is established.")
    public void databaseConnectionIsEstablished() {

        DBUtils.createConnection();

    }

    @When("The database connection is closed.")
    public void theDatabaseConnectionIsClosed() {

        DBUtils.closeConnection();
    }
}
