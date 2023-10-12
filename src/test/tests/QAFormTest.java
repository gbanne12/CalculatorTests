package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import pages.formexample.QAFormPage;
import tasks.formexample.QAForm;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class QAFormTest extends BaseTest {

    @Test
    public void canSubmitFormWhenPopulated(TestInfo testInfo) {
        var form = new QAForm(user);
        form.visit();
        form.typeName("Jane Doe");
        form.typeMessage(testInfo.getDisplayName());
        form.submitForm();

        assertTrue(user.canSee(QAFormPage.CONFIRMATION_MESSAGE_LOCATOR));
    }

    @Test
    public void canReceiveErrorWhenEmpty() {
        var form = new QAForm(user);
        form.visit();
        form.submitForm();

        assertTrue(user.canSee(QAFormPage.ERROR_MESSAGE_LOCATOR));
    }

}
