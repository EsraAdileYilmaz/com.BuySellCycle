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

    private String Query08="SELECT name FROM delivery_processes ORDER BY id LIMIT 5;";

    private String Query03 ="insert into cities (id, name, state_id, status, created_at) values (?, ?, ?, ?,?);";

    private String preparedQuery03Delete = "delete from cities where id = ? and name = ?;";

    private String selectQuery03="select * from cities where id = ? and name = ?";

    private String Query01="SELECT name FROM categories where slug='fashion'";

    private String Query04="INSERT INTO contacts (id,name,email,query_type,message) VALUES (?,?,?,?,?)";
  
    private String UpdateQuery04="UPDATE contacts SET message = 'Herkese kolay gelsin';";

    private String Query30="SELECT SUM(price) AS total_price FROM carts WHERE is_buy_now = 1 AND created_at < '2024-03-30'";

    private String Query25="SELECT txn_id, MAX(amount) AS amount FROM order_payments WHERE txn_id != 'none' AND amount > 9000 GROUP BY txn_id ORDER BY amount DESC;";

    private String Query12="SELECT DATE(day) AS day, GROUP_CONCAT(DISTINCT note ORDER BY note ASC SEPARATOR ', ') AS unique_notes, GROUP_CONCAT(DISTINCT day ORDER BY day ASC SEPARATOR ', ') AS dates FROM attendances GROUP BY DATE(day), note;";

    private String Query27="SELECT description FROM transactions WHERE payment_method IN ('Stripe', 'Cash On Delivery') GROUP BY description HAVING COUNT(DISTINCT payment_method) = 2";

    private String Query011="SELECT SUM(amount) AS total_amount FROM wallet_balances WHERE type = 'Referral' AND id BETWEEN 10 AND 20";

    private String preparedQuery09 = "SELECT COUNT(*) AS total_count FROM log_activity WHERE ip = ? AND method = 'Delete';";

    private String Query026="SELECT payment_method,SUM(amount) AS total_amount FROM transactions GROUP BY payment_method HAVING total_amount > 7000 ORDER BY total_amount DESC";

    private String Query028="SELECT DISTINCT user_id FROM support_tickets WHERE reference_no LIKE '%-%' OR reference_no NOT LIKE '%-%'";

    private String Query13 = "SELECT seller_products.product_id, coupon_products.coupon_id FROM seller_products LEFT JOIN coupon_products ON seller_products.product_id = coupon_products.product_id LIMIT 3;";

    private String Query19 = "SELECT * FROM bank_accounts WHERE bank_name = 'Witting Group' AND opening_balance < 0;";

    private String Query29 = "SELECT AVG(grand_total) AS average_grand_total FROM orders WHERE is_paid = 1;";

    private String query10 = "SELECT  COUNT(*) AS user_count FROM order_address_details WHERE shipping_address <> billing_address;";


    private String Query15="SELECT * FROM customer_coupon_stores JOIN users ON customer_coupon_stores.id = users.id LIMIT 3;";

    private String query23 = "select count(*) as type_count from email_template_types  where module is not null;";


    private String Query18="Insert Into bank_accounts (id,bank_name,branch_name,account_name,account_number,opening_balance,description,status) Values(?,?,?,?,?,?,?,?)";

    private String query014 = "SELECT COUNT(*) AS null_reason_count FROM refund_reasons WHERE reason IS NULL;";

    private  String query20 = "INSERT INTO device_tokens (id, user_id, device_token, created_at, updated_at) VALUES(?,?,?,?,?)";


}
