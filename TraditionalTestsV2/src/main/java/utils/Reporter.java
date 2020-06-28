package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Reporter {

    private static Reporter SINGLE_INSTANCE = null;

    /*
     * Private constructor to avoid creating multiple instances of this class
     */
    private Reporter() {}

    public static Reporter getInstance() {
        if (SINGLE_INSTANCE == null) {
            synchronized (Reporter.class) {
                SINGLE_INSTANCE = new Reporter();
            }
        }
        return SINGLE_INSTANCE;
    }

    public boolean report(int task, String testName, String domId, boolean comparisonResult) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Traditional-V1-TestResults.txt", true))) {
            writer.write("Task: " + task + ", Test Name: " + testName + ", DOM Id: " + domId + ", Browser: " + System.getProperty("browser")
                    + ", Viewport: " + System.getProperty("viewport") + ", Device: " + System.getProperty("device") + ", Status: " + (comparisonResult ? "Pass" : "Fail"));
            writer.newLine();
            System.out.println("Success writing to report file");
        } catch (Exception e) {
            System.out.println("Error writing to report file");
            e.printStackTrace();
        }
        //returns the result so that it can be used for further Assertions in the test code.
        return comparisonResult;
    }

}