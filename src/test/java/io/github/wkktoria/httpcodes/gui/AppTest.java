package io.github.wkktoria.httpcodes.gui;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class AppTest {
    private final Robot robot = new Robot();
    private FrameFixture window;

    public AppTest() throws AWTException {
    }

    @BeforeClass
    public static void beforeClass() {
        FailOnThreadViolationRepaintManager.install();
    }

    @Before
    public void setUp() {
        App frame = GuiActionRunner.execute(App::new);
        window = new FrameFixture(frame);
        window.show();
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void shouldShowWindowWithAppTitle() {
        window.requireVisible();
        window.requireTitle("HTTP Codes");
    }

    @Test
    public void shouldSetInitialTextForStatusCodeLabel() {
        window.label("statusCodeLabel").requireVisible();
        window.label("statusCodeLabel").requireText("Status code: 204");
    }

    @Test
    public void shouldSetInitialTextForStatusDescriptionTextArea() {
        window.textBox("statusDescriptionTextArea").requireVisible();
        window.textBox("statusDescriptionTextArea").requireText("No Content");
    }

    @Test
    public void shouldBeStatusCodeTextFieldPresent() {
        window.textBox("statusCodeTextField").requireVisible();
        window.textBox("statusCodeTextField").requireEnabled();
    }

    @Test
    public void shouldBeSearchButtonPresent() {
        window.button("searchButton").requireVisible();
        window.button("searchButton").requireEnabled();
        window.button("searchButton").requireText("Search");
    }

    @Test
    public void shouldUpdateStatusCodeLabelTextWhenStatusCodeTextFieldIsNotEmptyAndSearchButtonIsClicked() {
        window.textBox("statusCodeTextField").setText("200");
        window.button("searchButton").click();
        window.label("statusCodeLabel").requireText("Status code: 200");
    }

    @Test
    public void shouldUpdateStatusDescriptionTextAreaContentWhenStatusCodeTextFieldIsNotEmptyAndSearchButtonIsClicked() {
        window.textBox("statusCodeTextField").setText("200");
        window.button("searchButton").click();
        window.textBox("statusDescriptionTextArea")
                .requireText("The HTTP 200 OK successful response status code indicates that a request has succeeded. A 200 OK response is cacheable by default.");
    }

    @Test
    public void shouldUpdateStatusCodeLabelTextWhenStatusCodeTextFieldIsNotEmptyAndEnterKeyIsPressed() {
        window.textBox("statusCodeTextField").setText("200");
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        window.label("statusCodeLabel").requireText("Status code: 200");
    }

    @Test
    public void shouldUpdateStatusDescriptionTextAreaContentWhenStatusCodeTextFieldIsNotEmptyAndEnterKeyIsPressed() {
        window.textBox("statusCodeTextField").setText("200");
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        window.textBox("statusDescriptionTextArea")
                .requireText("The HTTP 200 OK successful response status code indicates that a request has succeeded. A 200 OK response is cacheable by default.");
    }
}