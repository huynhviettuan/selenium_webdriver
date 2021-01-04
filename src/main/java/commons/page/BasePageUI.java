package commons.page;

public class BasePageUI {

    public static final String dynamicTextBox = "//td[text() = '%s']/following-sibling::td/input";
    public static final String dynamicLink = "//a[text() = '%s']";
    public static final String dynamicButton = "//input[@value = '%s']";
    public static final String dynamicCheckBox = "//td[text()= '%s']/following-sibling::td";

}
