package common;

import org.apache.commons.lang3.RandomStringUtils;

public class CredentialsGenerator {
    private String randomEmail = RandomStringUtils.random(7, true, false) + "@" + RandomStringUtils.random(6, true, false) + ".ru";
    private String randomPassword = RandomStringUtils.random(6, true, true);
    private String randomName = RandomStringUtils.random(10, true, false);

    public String getRandomEmail() {
        return randomEmail;
    }

    public String getRandomPassword() {
        return randomPassword;
    }

    public String getRandomName() {
        return randomName;
    }

    public void setRandomEmail(String randomEmail) {
        this.randomEmail = randomEmail;
    }

    public void setRandomPassword(String randomPassword) {
        this.randomPassword = randomPassword;
    }

    public void setRandomName(String randomName) {
        this.randomName = randomName;
    }
}
