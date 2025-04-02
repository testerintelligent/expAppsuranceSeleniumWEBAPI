package utility;

import java.util.Random;

public class FunctionLibrary {
    Random random = new Random();

    //    Function to create random name
    public String generateRandomName() {
        String randomName = "";
        int randomFiveDigitNumber = 10000 + random.nextInt(90000);
        randomName = "Tester" + randomFiveDigitNumber;

        return randomName;
    }

    //    Function to create random email
    public String generateRandomEmail() {
        String randomEmail = "";
        randomEmail = generateRandomName() + "@expleotest.com";

        return randomEmail;
    }
}
