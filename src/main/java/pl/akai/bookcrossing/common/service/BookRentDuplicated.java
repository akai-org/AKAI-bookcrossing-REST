package pl.akai.bookcrossing.common.service;

public class BookRentDuplicated extends RuntimeException {
    public BookRentDuplicated(String message) {
        super(message);
    }
}
