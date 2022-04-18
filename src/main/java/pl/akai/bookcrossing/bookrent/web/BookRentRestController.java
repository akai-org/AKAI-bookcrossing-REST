package pl.akai.bookcrossing.bookrent.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akai.bookcrossing.book.dto.BookDetailsDto;
import pl.akai.bookcrossing.bookrent.dto.BookRentRequestDto;
import pl.akai.bookcrossing.bookrent.service.BookRentService;

@RestController
@RequiredArgsConstructor
public class BookRentRestController {

    private final BookRentService bookRentService;

    @GetMapping("/api/books/requests")
    public ResponseEntity<Page<BookRentRequestDto>> bookRental(Pageable pageable) {
        Page<BookRentRequestDto> requests = bookRentService.getBookRentRequestsByCurrentUser(pageable);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @PostMapping("/api/books/{bookId}/requests")
    public ResponseEntity<BookRentRequestDto> bookRental(@PathVariable int bookId) {
        BookRentRequestDto request = bookRentService.createBookUserRequest(bookId);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/api/books/{bookId}/change-available")
    public ResponseEntity<BookDetailsDto> changeBookAvailability(@PathVariable int bookId) {
        BookDetailsDto book = bookRentService.changeBookAvailability(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);

    }

    @DeleteMapping("/api/books/requests/{requestId}")
    public ResponseEntity<Void> bookRentRequestAccept(@PathVariable() int requestId,
                                      @RequestParam("accept") Boolean accept) {
        bookRentService.processBookRentRequestAcceptation(requestId, accept);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
