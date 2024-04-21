package manage;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manage {


    private String Query01 = "SELECT user_id FROM deposits Where amount > 100 And amount <500;";


}
