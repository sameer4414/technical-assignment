/**
 * The below class AddressBookParser helps in finding :
 * 1. How many males are in the address book?
 * 2. Who is the oldest person in the address book?
 * 3. How many days older is Bill than Paul?
 */

package org.ebay.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class AddressBookParser {

    private static final String FILE_PATH = "src//main//resources//AddressBook";
    private static final String SEPARATOR = ",";
    private static final String GENDER_TO_COUNT = "Male";

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        String currentLine = "";

        int genderCount = 0;
        String gender = "";

        Scanner scanner = new Scanner(getFile());

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            gender = getRecordFromLine(currentLine, RecordType.GENDER);

            // counting number of Males
            if (gender.equals(GENDER_TO_COUNT)) genderCount++;

        }
        System.out.println(genderCount);
    }

    private static File getFile(){
        return new File(FILE_PATH);
    }

    private static String getRecordFromLine(String inputStr, RecordType recordType) throws ParseException {
        switch (recordType){
            case GENDER: return inputStr.split(SEPARATOR)[RecordType.GENDER.getIndex()].trim();
            default:return null;
        }
    }

}