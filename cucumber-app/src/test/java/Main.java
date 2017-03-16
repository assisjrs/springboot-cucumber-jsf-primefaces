import database.Usuario;
import org.javalite.activejdbc.Base;

public class Main {
    public static void main(String... args){
        Base.open("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/leiloes?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "cs!rede123");

        Usuario usuario = new Usuario();

        usuario.set("nome", "Assis");

        usuario.save();


    }
}