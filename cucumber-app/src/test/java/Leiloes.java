import org.javalite.activejdbc.Base;

public class Leiloes {
    public static void main(String... args){
        Base.open("org.hsqldb.jdbcDriver", "jdbc:hsqldb:file:./../leiloes/leiloes.db", "sa", "");
    }
}