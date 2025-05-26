/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.chatapplicationp2;

/**
 *
 * @author ST10433587 
 */

// import the Random 
import java.util.Random;

public class Messages {
    // variables for msg details 
    private static int messageCount = 0;
    private String messageID;
    private String messageText;
    private String recipientCell;
    private String messageHash;
    private boolean sent;
    private boolean received;
    private boolean read;
    
    // getter
    public String getMessageID() {
    return messageID;
    
    }

    public Messages(String recipientCell, String messageText) {
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
        this.sent = false;
        this.received = false;
        this.read = false;
        messageCount++;
    }

    // to generate random 10 dig method as string 
    private String generateMessageID() {
        Random rand = new Random();
        return String.valueOf(1000000000 + rand.nextInt(900000000));
    }

    // method for correct cell num format validation
    public boolean checkRecipientCell() {
        return recipientCell.matches("^\\+27\\d{9}$");
    }

    // method for msg ID length validation 
    public boolean checkMessageID() {
        return messageID.length() == 10;
    }

    // method for msg hash combining msg ID, count, concatenation of first & last words of msg
    public String createMessageHash() {
        String[] words = messageText.trim().split(" ");
        String first = words.length > 0 ? words[0] : "";
        String last = words.length > 1 ? words[words.length - 1] : "";
        return messageID.substring(0, 2) + ":" + messageCount + ":" + (first + last).toUpperCase();
    }

    // method that processes users choice to send, disregard or store msg
    // should update message flags & return status msg
    public String sendMessageOption(int choice) {
        return switch (choice) {
            case 1 -> {
                this.sent = true;
                yield "Message successfully sent.";
            }
            case 2 -> "Press 0 to delete message.";
            case 3 -> {
                // To store on JSON
                yield "Message successfully stored.";
            }
            default -> "Invalid option.";
        };
    }

    // for total msg created
    public static int returnTotalMessages() {
        return messageCount;
    }

    @Override
    public String toString() {
        return "MessageID: " + messageID + "\nHash: " + messageHash + "\nTo: " + recipientCell +
               "\nMessage: " + messageText + "\nSent: " + sent + "\nReceived: " + received + "\nRead: " + read;
    }

    // for total msg created
    public String getMessageHash() {
        return messageHash;
    }
}
