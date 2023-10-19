package elements;

public enum ElementTag {
    BUTTON(new String[]{"button"}),
    DIALOG(new String[]{"dialog"}),
    FORM(new String[]{"form"}),
    IMG(new String[]{"image"}),
    LI(new String[]{"listitem"}),
    UL(new String[]{"list"}),
    OL(new String[]{"list"}),
    OPTION(new String[]{"option"}),
    P(new String[]{"paragraph"}),
    TEXTBOX(new String[]{"textbox"}),
    INPUT(new String[]{"button", "checkbox", "textbox", "radio", "submit"}),
    CHECKBOX(new String[]{"checkbox"}),
    RADIO(new String[]{"radio"}),
    SELECT(new String[]{"listbox", "combobox"}),
    H1(new String[]{"heading"});

    private final String[] roles;

    ElementTag(String[] value) {
        this.roles = value;
    }

    public String[] getRoles() {
        return roles;
    }
}

