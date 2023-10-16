package webdriver;

public record By(LocatorMethod method, String value, String additionalValue) {

    public static By role(String role, String text) {
        return new By(LocatorMethod.BY_ROLE, role, text);
    }

    public static By label(String label) {
        return new By(LocatorMethod.BY_LABEL, label, null);
    }

    public static By text(String text) {
        return new By(LocatorMethod.BY_TEXT, text, null);
    }

    public static By placeholder(String placeholder) {
        return new By(LocatorMethod.BY_PLACEHOLDER, placeholder, null);
    }

    public static By testId(String testId) {
        return new By(LocatorMethod.BY_TESTID, testId, null);
    }

    public static By cssSelector(String cssSelector) {
        return new By(LocatorMethod.BY_CSS, cssSelector, null);
    }

    public static By xpath(String xpathSelector) {
        return new By(webdriver.LocatorMethod.BY_XPATH, xpathSelector, null);
    }

}