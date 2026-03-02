package fr.fae.project.memoriaeback.account.user.application.common;

public class ServiceResponse<T> {

    // Attributes
    private String code;
    private String message;
    private T data;

    // Constructors
    public ServiceResponse() {
    }
    public ServiceResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getters & Setters
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

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