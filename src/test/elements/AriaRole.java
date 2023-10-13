package elements;

public enum AriaRole {
    BUTTON("button"),
    DIALOG("dialog"),
    FORM("form"),
    HEADING("heading"),
    IMAGE("IMAGE"),
    LISTITEM("listitem"),
    LIST("list"),
    OPTION("option"),
    PARAGRAPH("paragraph"),
    TEXTBOX("textbox"),
    CHECKBOX("checkbox"),
    RADIO("radio"),
    LISTBOX("listbox"),
    COMBOBOX("combobox");

    private final String value;

    AriaRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

