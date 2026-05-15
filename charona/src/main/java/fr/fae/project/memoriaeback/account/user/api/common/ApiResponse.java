package fr.fae.project.memoriaeback.account.user.api.common;

public class ApiResponse<T> {

    // Attributes
    public String message;
    public T data;

    // Constructors
    public ApiResponse() {
    }
    public ApiResponse(String message, T data) {
        this.setMessage(message);
        this.setData(data);
    }

    // Getters & Setters
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
