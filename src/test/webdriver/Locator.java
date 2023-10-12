package webdriver;

public record Locator(LocatorMethod method, String value, String additionalValue) {

    public static Locator createByRole(String role, String text) {
        return new Locator(LocatorMethod.BY_ROLE, role, text);
    }

    public static Locator createByLabel(String label) {
        return new Locator(LocatorMethod.BY_LABEL, label, null);
    }

    public static Locator createByText(String text) {
        return new Locator(LocatorMethod.BY_TEXT, text, null);
    }

    public static Locator createByPlaceholder(String placeholder) {
        return new Locator(LocatorMethod.BY_PLACEHOLDER, placeholder, null);
    }

    public static Locator createByTestId(String testId) {
        return new Locator(LocatorMethod.BY_TESTID, testId, null);
    }

    public static Locator createByCss(String cssSelector) {
        return new Locator(LocatorMethod.BY_CSS, cssSelector, null);
    }

    public static Locator createByXpath(String xpathSelector) {
        return new Locator(LocatorMethod.BY_XPATH, xpathSelector, null);
    }

}