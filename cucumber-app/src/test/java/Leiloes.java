import org.javalite.activejdbc.Base;

public class Leiloes {
    public static void main(String... args){
        Base.open("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/leiloes?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "cs!rede123");
    }
}