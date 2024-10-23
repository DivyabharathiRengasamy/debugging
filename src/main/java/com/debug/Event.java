package com.debug;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Event {
    private String eventId;
    private int capacity;
    private LocalDateTime eventDate;
    private List<String> registeredUsers;
    private Map<String, RegistrationStatus> userRegistrations;

    public Event(String eventId, int capacity, LocalDateTime eventDate) {
        this.eventId = eventId;
        this.capacity = capacity;
        this.eventDate = eventDate;
        this.registeredUsers = new ArrayList<>();
        this.userRegistrations = new HashMap<>();
    }

    public RegistrationResult registerUser(String userId) {
        if (userRegistrations.containsKey(userId)) {
            return new RegistrationResult(false, "Already registered");
        }

        if (registeredUsers.size() >= capacity) { //here i corrected
            userRegistrations.put(userId, RegistrationStatus.WAITLIST);
            return new RegistrationResult(false, "Event is full");
        }

        registeredUsers.add(userId);
        userRegistrations.put(userId, RegistrationStatus.CONFIRMED);
        
        userRegistrations.remove(userId);// here i changed
        
        return new RegistrationResult(true, "Registration successful");
    }

    public boolean cancelRegistration(String userId) {
        if (userRegistrations.get(userId) .equals(RegistrationStatus.CONFIRMED)) {//here we want to compare equals method
        	// I change the code here 
        	//
            userRegistrations.remove(userId);
            return true;
        }
        return false;
    }

    public void processWaitlist() {
        for (Map.Entry<String, RegistrationStatus> entry : userRegistrations.entrySet()) {
            if (entry.getValue().equals(RegistrationStatus.WAITLIST)) {
            	//here also i changed the code
                entry.setValue(RegistrationStatus.WAITLIST);
            }
        }
    }

    public List<String> getRegisteredUsers() {
        return registeredUsers;
    }
}

enum RegistrationStatus {
    CONFIRMED, WAITLIST
}