package tasks.formexample;

import actions.User;
import pages.URL;
import pages.formexample.QAFormPage;
import tasks.Task;

public class QAForm implements Task {

    private User user;

    public QAForm(User user) {
        this.user = user;
    }

    @Override
    public void visit() {
        user.navigate(URL.FORM_URL);
    }

    public QAForm typeName(String input) {
        user.click(QAFormPage.NAME_TEXT_BOX_LOCATOR);
        user.fill(QAFormPage.NAME_TEXT_BOX_LOCATOR, input);
        return this;
    }

    public QAForm typeMessage(String input) {
        user.click(QAFormPage.MESSAGE_TEXT_BOX_LOCATOR);
        user.fill(QAFormPage.MESSAGE_TEXT_BOX_LOCATOR, input);
        return this;
    }

    public void submitForm() {
        user.click(QAFormPage.SUBMIT_BUTTON_LOCATOR);
    }
}
