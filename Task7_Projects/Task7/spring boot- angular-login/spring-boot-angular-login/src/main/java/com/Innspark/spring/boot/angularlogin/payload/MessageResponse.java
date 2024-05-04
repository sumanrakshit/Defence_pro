package com.Innspark.spring.boot.angularlogin.payload;

public class MessageResponse {
	
	
	
    private String message;
    private String status;
    private boolean success;

    public MessageResponse() {
    }

    public MessageResponse(String message, String status, boolean success) {
        this.message = message;
        this.status = status;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "DeleteResponse{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", success=" + success +
                '}';
    }

}
