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
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AddressBookParser {

    private static final String FILE_PATH = "src//main//resources//AddressBook";
    private static final String SEPARATOR = ",";
    private static final String GENDER_TO_COUNT = "Male";
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
    private static final String FIRST_NAME = "Bill McKnight";
    private static final String SECOND_NAME = "Paul Robinson";

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        String currentLine = "";

        int genderCount = 0;
        String gender = "";

        Date localDate;
        Date oldestDate = Date.from(Instant.now());
        String oldestPerson = "";
        String name = "";

        Date firstNameDOB = new Date();
        Date secondNameDOB = new Date();

        Scanner scanner = new Scanner(getFile());

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            gender = getRecordFromLine(currentLine, RecordType.GENDER);
            name = getRecordFromLine(currentLine, RecordType.NAME);
            localDate = FORMATTER.parse(getRecordFromLine(currentLine, RecordType.DATE));

            // counting number of Males
            if (gender.equals(GENDER_TO_COUNT)) genderCount++;

            // finding the oldest person
            if (localDate.before(oldestDate)){
                oldestDate = localDate;
                oldestPerson = name;
            }

            //finding DOBs for the provided 2 names
            if (name.equals(FIRST_NAME)){
                firstNameDOB = localDate;
            }else if (name.equals(SECOND_NAME)){
                secondNameDOB = localDate;
            }
        }

        long numDaysBetween = getNumberOfDaysInBetween(firstNameDOB, secondNameDOB);

        System.out.println("Total number of males : "+genderCount);
        System.out.println("Oldest person is : "+oldestPerson);
        System.out.println("Number of days between DOB of "+ FIRST_NAME + "and "+ SECOND_NAME + " is : "+Math.abs(numDaysBetween));
    }

    private static File getFile(){
        return new File(FILE_PATH);
    }

    private static String getRecordFromLine(String inputStr, RecordType recordType) throws ParseException {
        switch (recordType){
            case NAME: return inputStr.split(SEPARATOR)[RecordType.NAME.getIndex()].trim();
            case GENDER: return inputStr.split(SEPARATOR)[RecordType.GENDER.getIndex()].trim();
            case DATE: return inputStr.split(SEPARATOR)[RecordType.DATE.getIndex()].trim();
            default:return null;
        }
    }

    private static long getNumberOfDaysInBetween(Date firstDOB, Date secondDOB){
        return TimeUnit.DAYS.convert(Math.abs(firstDOB.getTime() - secondDOB.getTime()), TimeUnit.MILLISECONDS);
    }
}