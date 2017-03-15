package database;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import javax.sql.DataSource;
import java.io.File;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

/**
 * Created by assis on 15/03/17.
 */
public class DBUnitListener extends AbstractTestExecutionListener {
    private DataSource dbUnitDatabaseConnection;

    private boolean isDbUnit = false;

    @Override
    public void beforeTestClass(final TestContext testContext) throws Exception {
        final ApplicationContext context = testContext.getApplicationContext();

        if (context instanceof ConfigurableApplicationContext) {
            final DatabaseSetup annotation = findAnnotation(testContext.getTestClass(), DatabaseSetup.class);

            if (annotation == null) return;

            isDbUnit = true;

            dbUnitDatabaseConnection = context.getBean("dbUnitDatabaseConnection", DataSource.class);

            final FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            builder.setColumnSensing(true);

            final IDataSet dataSet = builder.build(new File("src/test/resources/CadastrarUmUsuarioSteps.xml"));
            final DatabaseConnection databaseConnection = new DatabaseConnection(dbUnitDatabaseConnection.getConnection());

            DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataSet);
        }
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        if(!isDbUnit) return;

        final FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);

        final IDataSet dataSet = builder.build(new File("src/test/resources/CadastrarUmUsuarioSteps.xml"));
        final DatabaseConnection databaseConnection = new DatabaseConnection(dbUnitDatabaseConnection.getConnection());

        DatabaseOperation.DELETE_ALL.execute(databaseConnection, dataSet);
    }
}
