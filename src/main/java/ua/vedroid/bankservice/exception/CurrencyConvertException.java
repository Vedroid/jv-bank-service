package ua.vedroid.bankservice.exception;

public class CurrencyConvertException extends RuntimeException {
    public CurrencyConvertException(String message, Throwable cause) {
        super(message, cause);
    }
}
