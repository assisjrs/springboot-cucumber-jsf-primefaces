package database;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by assis on 06/03/17.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"database.*", "stepDefinition.*"})
public class Config {

    @Bean(name = "dbUnitDatabaseConnection")
    public DataSource dbUnitDatabaseConnection() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/leiloes?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("cs!rede123");

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dbUnitDatabaseConnection());
    }

    /*
    private static DataSource ds;
    private static DatabaseConnection databaseConnection;

    public static void createDataset(String dataset) {
        if (!dataset.startsWith("/")) {
            dataset = "/" + dataset;
        }
        try {
            initConn();
            DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, new XmlDataSet(DBUnitUtils.class.getResourceAsStream(dataset)));
        } catch (Exception e) {
            throw new RuntimeException("could not initialize dataset:" + dataset +
                    " \nmessage: " + e.getMessage());
        } finally {
            closeConn();
        }
    }

    public static void deleteDataset(String dataset) {
        if (!dataset.startsWith("/")) {
            dataset = "/" + dataset;
        }
        try {
            initConn();
            DatabaseOperation.DELETE_ALL.execute(databaseConnection, new XmlDataSet(DBUnitUtils.class.getResourceAsStream(dataset)));
        } catch (Exception e) {
            throw new RuntimeException("could not delete dataset dataset:" + dataset +
                    " \nmessage: " + e.getMessage());
        } finally {
            closeConn();
        }
    }
    */
}
