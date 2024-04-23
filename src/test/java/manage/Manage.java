package manage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j

@Getter

public class Manage {

    private String Query07= "select phone from customer_addresses where phone like '%5%' order by id limit 3;";

    private String Query02="insert into cities (id, name, state_id, status, created_at) values (?, ?, ?, ?,?);";

    private String Query03 ="insert into cities (id, name, state_id, status, created_at) values (?, ?, ?, ?,?);";

    private String preparedQuery03Delete = "delete cities where id =?;";


    private String Query01="SELECT name FROM categories where slug='fashion'";
    private String Query04="INSERT INTO contacts (id,name,email,query_type,message) VALUES (?,?,?,?,?)";
    private String Query05="INSERT INTO contacts (message) VALUES (?)";

    private String Query30="SELECT SUM(price) AS total_price FROM carts WHERE is_buy_now = 1 AND created_at < '2024-03-30'";
    private String Query25="SELECT txn_id, MAX(amount) AS amount FROM order_payments WHERE txn_id != 'none' AND amount > 9000 GROUP BY txn_id ORDER BY amount DESC;";

}
