
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class MySqlConection {
    Connection con = null;
    public static Connection ConnectDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/dbapplication","root","2@2@ & happy!");
            return con;
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
             return null;
        }
        
        
    }
}
