package org.example.fiangonana.exception;

import java.util.ArrayList;
import java.util.List;

public class ExceptionList extends Exception {
    List<String> messages = new ArrayList<>();


    public ExceptionList() {}

    public ExceptionList(String message) {
        this.messages.add(message);
    }

    public ExceptionList(List<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

    public List<String> getMessages() {
        return this.messages;
    }

}
