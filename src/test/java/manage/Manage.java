package manage;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Getter
@Setter
public class Manage {

    private String Query07= "select phone from customer_addresses where phone like '%5%' order by id limit 3;";

    private String Query02="insert into cities (id, name, state_id, status, created_at) values (?, ?, ?, ?,?);";

    private String Query03 ="insert into cities (id, name, state_id, status, created_at) values (?, ?, ?, ?,?);";

    private String preparedQuery03Delete = "delete from cities where id = ? and name = ?;";

    private String selectQuery03="select * from cities where id = ? and name = ?";


    private String Query01="SELECT name FROM categories where slug='fashion'";

    private String Query04="INSERT INTO contacts (id,name,email,query_type,message) VALUES (?,?,?,?,?)";

    private String Query30="SELECT SUM(price) AS total_price FROM carts WHERE is_buy_now = 1 AND created_at < '2024-03-30'";
    private String Query25="SELECT txn_id, MAX(amount) AS amount FROM order_payments WHERE txn_id != 'none' AND amount > 9000 GROUP BY txn_id ORDER BY amount DESC;";


    private String Query05="UPDATE contacts SET message = 'Herkese kolay gelsin';";

    private String Query27="SELECT description FROM transactions WHERE payment_method IN ('Stripe', 'Cash On Delivery') GROUP BY description HAVING COUNT(DISTINCT payment_method) = 2";


    private String Query011="SELECT SUM(amount) AS total_amount FROM wallet_balances WHERE type = 'Referral' AND id BETWEEN 10 AND 20";

    private String preparedQuery09 = "SELECT COUNT(*) AS total_count FROM log_activity WHERE ip = ? AND method = 'Delete';";

    private String Query026="SELECT payment_method,SUM(amount) AS total_amount FROM transactions GROUP BY payment_method HAVING total_amount > 7000 ORDER BY total_amount DESC";

    private String Query028="SELECT DISTINCT user_id FROM support_tickets WHERE reference_no LIKE '%-%' OR reference_no NOT LIKE '%-%'";



}
