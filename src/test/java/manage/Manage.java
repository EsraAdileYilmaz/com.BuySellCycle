package manage;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Manage {


    //private String Query01 = "SELECT user_id FROM deposits Where amount > 100 And amount <500;";
    private String Query01="SELECT name FROM categories where slug='fashion'";
    private String Query04="INSERT INTO contacts (id,name,email,query_type,message) VALUES (?,?,?,?,?)";
    private String Query05="INSERT INTO contacts (message) VALUES (?)";



}
