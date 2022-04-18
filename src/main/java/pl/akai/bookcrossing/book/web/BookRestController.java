package pl.akai.bookcrossing.book.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akai.bookcrossing.book.dto.BookDetailsDto;
import pl.akai.bookcrossing.book.dto.BookListDto;
import pl.akai.bookcrossing.book.dto.BookRequestDto;
import pl.akai.bookcrossing.book.service.BookService;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping("/api/books")
    public ResponseEntity<Page<BookListDto>> getAllBooks(Pageable pageable) {
        Page<BookListDto> books = bookService.getBooksByPageable(pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/api/books/original-owner")
    public ResponseEntity<Page<BookListDto>> getBooksOriginallyOwnedByCurrentUser(Pageable pageable) {
        Page<BookListDto> books = bookService.getBooksOriginallyOwnedByCurrentUser(pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/api/books/current-owner")
    public ResponseEntity<Page<BookListDto>> getBooksCurrentlyOwnedByCurrentUser(Pageable pageable) {
        Page<BookListDto> books = bookService.getBooksCurrentlyOwnedByCurrentUser(pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/api/books/{id}")
    public ResponseEntity<BookDetailsDto> getBook(@PathVariable int id) {
        BookDetailsDto book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/api/books")
    public ResponseEntity<BookDetailsDto> addBook(@RequestBody BookRequestDto bookDto) {
        BookDetailsDto book = bookService.addBook(bookDto);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping("/api/books/{id}")
    public ResponseEntity<BookDetailsDto> updateBook(@PathVariable int id,
                                                     @RequestBody BookRequestDto bookDto) {
        BookDetailsDto book = bookService.updateBook(id, bookDto);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
