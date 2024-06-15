package com.perfume.haven.dto.response;

public class MessageResponse {
    private String response;
    private String message;

    public MessageResponse(String response, String message) {
        this.response = response;
        this.message = message;
    }

    public String getResponse() {
        return response;
    }

    public String getMessage() {
        return message;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
