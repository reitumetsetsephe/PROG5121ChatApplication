
package com.mycompany.chatapplicationp2;


// need to import JOption Pane for part 2
// remember JSON file 

import javax.swing.JOptionPane;

public class ChatApplicationP2 {

    public static void main(String[] args) {
        
        // user must enter username for reg, password & SAn num with int code 
        String username = JOptionPane.showInputDialog("Enter Username:");
        String password = JOptionPane.showInputDialog("Enter Password:");
        String cell = JOptionPane.showInputDialog("Enter Cell Phone Number (+27xxxxxxxxx):");
        
        // login with registration details 
        Login user = new Login(username, password, cell);
        String regMessage = user.registerUser();
        JOptionPane.showMessageDialog(null, regMessage);
        
        // if reg failed 
        if (!regMessage.equals("User successfully registered.")) return;
        
        // user must enter username & password. 
        String loginUser = JOptionPane.showInputDialog("Enter Login Username:");
        String loginPass = JOptionPane.showInputDialog("Enter Login Password:");
        
        // to verify login credentials with user details from reg 
        boolean isLoggedIn = user.loginUser(loginUser, loginPass);

        // user must enter first & second name after logging in 
        String firstName = JOptionPane.showInputDialog("Enter First Name:");
        String lastName = JOptionPane.showInputDialog("Enter Last Name:");

        // message that will display if login is a success or failure 
        String loginMsg = user.returnLoginStatus(firstName, lastName, isLoggedIn);
        
        // user must see login status message 
        JOptionPane.showMessageDialog(null, loginMsg);

        // if login fails, program exits
        if (!isLoggedIn) return;

        // welcome message to user if logged in successfully
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat!");

        // ask user how many messages they want to send. Add array to store message objects 
        int totalMsgs = Integer.parseInt(JOptionPane.showInputDialog("How many messages would you like to send?"));
        Messages[] messages = new Messages[totalMsgs];

        // loop for num of msgs
        for (int i = 0; i < totalMsgs; i++) {
            
            // menu opts for sending msgs, to view or quit 
            String menu = """
                Choose an option:
                1. Send Message
                2. Show Recently Sent Messages
                3. Quit
                """;

            // get user choice from menu 
            int choice = Integer.parseInt(JOptionPane.showInputDialog(menu));
            if (choice == 1) {
                
                // user enter cell num of recipient 
                String recipient = JOptionPane.showInputDialog("Enter Recipient Cell Number:");
                // user enter text msg, no longer than 250 chars
                String msgText = JOptionPane.showInputDialog("Enter your message (max 250 chars):");

                // check message > 250 chars limit 
                if (msgText.length() > 250) {
                    
                    // if > 250, show how much user went over chars limit 
                    JOptionPane.showMessageDialog(null, "Message exceeds 250 characters by " + (msgText.length() - 250));
                    continue;
                }
                
                // Msg object with repipient & msg  
                Messages msg = new Messages(recipient, msgText);
                
                // show options to send, disregard or store msg 
                int sendChoice = Integer.parseInt(JOptionPane.showInputDialog("""
                        1. Send Message
                        2. Disregard Message
                        3. Store Message
                        """));
                
                // display user's choice 
                JOptionPane.showMessageDialog(null, msg.sendMessageOption(sendChoice));
                // store msg in array
                messages[i] = msg;

                // show details of msg created 
                JOptionPane.showMessageDialog(null, msg.toString());
            } 
            // placeholder to show recently sent msg 
            else if (choice == 2) {
                JOptionPane.showMessageDialog(null, "Coming Soon.");
            } 
            
            // if user chooses to quit msg sending 
            else if (choice == 3) {
                break;
            } 
            
            // if user entered invalid menu option, show the error 
            else {
                JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }

        // display num of msgs created during session 
        JOptionPane.showMessageDialog(null, "Total Messages Created: " + Messages.returnTotalMessages());
    }

}
