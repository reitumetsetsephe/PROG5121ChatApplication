
package com.mycompany.chatapplicationp2;


public class Login {

    private String username;
    private String password;
    private String cellNumber;

    public Login(String username, String password, String cellNumber) {
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
    }

    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        return password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-]).{8,}$");
    }
/* added new code here from ChatGPT. origional from Part 1 = public boolean checkCellPhoneNumber() {
        return cellNumber.matches("^\\+27\\d{9}$");
    } */
    
    public boolean checkCellPhoneNumber() {
    // Remove any non-visible characters like Unicode LRM or formatting
    String cleanedCell = cellNumber.replaceAll("[^\\x00-\\x7F]", "").trim();
    return cleanedCell.matches("^\\+27\\d{9}$");
}

    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        return "User successfully registered.";
    }

    public boolean loginUser(String loginUsername, String loginPassword) {
        return this.username.equals(loginUsername) && this.password.equals(loginPassword);
    }

    public String returnLoginStatus(String firstName, String lastName, Boolean loggedIn) {
        if (loggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}


/*
Reference list:
ChatGPT, 2025. Cell Phone Number Validation Regex. [Online]. Available at: chatgpt.com. [Accessed on 13 May 2025].

Stack Overflow, (n.d). Getting Random Numbers In Java. [Online]. Available at: https://stackoverflow.com/questions/16627910/how-to-code-a-very-simple-login-system-with-java. [Accessed on 17 May 2025]. 

Stack Overflow, (n.d). Storing Data in JSON File Using Java. [Online]. Available at: https://stackoverflow.com/questions/16627910/how-to-code-a-very-simple-login-system-with-java. [Accessed on 17 May 2025].

 
 */
