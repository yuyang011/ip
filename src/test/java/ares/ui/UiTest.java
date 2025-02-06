package ares.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {
    @Test
    public void testPrintWelcome() {
        Ui ui = new Ui();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printOutput = new PrintStream(outputStream);
        System.setOut(printOutput);
        ui.printWelcome();
        String expectedOutput = "   --------------------------------------------------------------\r\n"
                   + "   Hello! I'm Ares\r\n"
                   + "   Its been awhile\r\n"
                   + "   What can I do for you today?\r\n"
                   + "   --------------------------------------------------------------\r\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintAresException(){
        Ui ui = new Ui();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printOutput = new PrintStream(outputStream);
        System.setOut(printOutput);
        ui.printAresException("wrong input");
        String expectedOutput = "STOP RIGHT THERE!!! wrong input\r\n"
                + "   --------------------------------------------------------------\r\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}