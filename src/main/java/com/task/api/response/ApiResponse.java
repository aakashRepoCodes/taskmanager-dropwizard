package com.task.api.response;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "Success", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    public static ApiResponse<String> error(String message) {
        return new ApiResponse<>(false, message, null);
    }


    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public T getData() { return data; }
}

