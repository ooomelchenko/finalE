package delivery;

import java.util.Locale;
import java.util.ResourceBundle;

public class App {

    static String CONTENT_BUNDLE_NAME = "messages";

    static ResourceBundle bundle = ResourceBundle.getBundle(CONTENT_BUNDLE_NAME, new Locale("en_GB"));

    public static void main(String[] args) {

        System.out.println(bundle.getString("message.wrongaction"));

/*        System.out.println("Hello!");
        Connection con =
                DriverManager.
                        getConnection(  "jdbc:"+
                                        "mysql:"+
                                        "//localhost:3306/"+
                                        "mystudentdb",
                                "root" ,
                                "root");

        Statement query = con.createStatement();
        ResultSet rs = query.executeQuery("SELECT * FROM teacher");
        while( rs.next()) {
            System.out.println(rs.getString("name"));}*/

    }
}
