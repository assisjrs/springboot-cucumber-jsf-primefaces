package database;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

/**
 * Created by assis on 15/03/17.
 */
public class SeleniumListener extends AbstractTestExecutionListener {
    private WebDriver webDriver;

    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public void beforeTestClass(final TestContext testContext) throws Exception {
        final ApplicationContext context = testContext.getApplicationContext();

        if (context instanceof ConfigurableApplicationContext) {
            final SeleniumTestCase annotation = findAnnotation(testContext.getTestClass(), SeleniumTestCase.class);

            webDriver = BeanUtils.instantiate(annotation.webDriver());

            final Object pageObject = PageFactory.initElements(webDriver, annotation.pageObject());

            webDriver.get(annotation.url());

            register((ConfigurableApplicationContext) context, webDriver);
            register((ConfigurableApplicationContext) context, pageObject);
        }
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        if (webDriver != null) {
            webDriver.close();
            webDriver.quit();
        }
    }

    private void register(final ConfigurableApplicationContext context, final Object object) {
        final ConfigurableListableBeanFactory bf = context.getBeanFactory();

        bf.registerResolvableDependency(object.getClass(), object);
    }
}