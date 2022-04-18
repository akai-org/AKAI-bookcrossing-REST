package pl.akai.bookcrossing.bookrent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.akai.bookcrossing.book.database.BookEntity;
import pl.akai.bookcrossing.book.database.BookRepository;
import pl.akai.bookcrossing.book.dto.BookDetailsDto;
import pl.akai.bookcrossing.book.mapper.BookEntityToBookDetailsDtoMapper;
import pl.akai.bookcrossing.bookrent.database.BookRentRequestEntity;
import pl.akai.bookcrossing.bookrent.database.BookRentRequestRepository;
import pl.akai.bookcrossing.bookrent.dto.BookRentRequestDto;
import pl.akai.bookcrossing.bookrent.mapper.BookRentRequestEntityToBookRentRequestDtoMapper;
import pl.akai.bookcrossing.common.service.BookRentDuplicated;
import pl.akai.bookcrossing.common.service.IllegalUserException;
import pl.akai.bookcrossing.security.CurrentUserService;
import pl.akai.bookcrossing.user.database.UserEntity;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BookRentService {

    private final BookRentRequestRepository bookRentRequestRepository;
    private final BookRepository bookRepository;
    private final CurrentUserService currentUserService;
    private final BookEntityToBookDetailsDtoMapper bookEntityToBookDetailsDtoMapper;
    private final BookRentRequestEntityToBookRentRequestDtoMapper bookRentRequestEntityToBookRentRequestDtoMapper;

    public Page<BookRentRequestDto> getBookRentRequestsByCurrentUser(Pageable pageable) {
        UserEntity user = currentUserService.getUser();
        return bookRentRequestRepository.findAllByBook_CurrentOwner_Id(user.getId(), pageable)
                                        .map(bookRentRequestEntityToBookRentRequestDtoMapper::from);
    }

    public BookRentRequestDto createBookUserRequest(Integer bookId) {
        UserEntity currentUser = currentUserService.getUser();
        BookEntity requestedBook = bookRepository.findById(bookId)
                                                 .orElseThrow(() -> new EntityNotFoundException("Book"));
        BookRentRequestEntity bookRentRequest = BookRentRequestEntity.builder()
                                                                     .requester(currentUser)
                                                                     .book(requestedBook)
                                                                     .build();
        if (isBookRentRequestDuplicated(bookRentRequest)) {
            throw new BookRentDuplicated(bookId.toString());
        }
        BookRentRequestEntity savedBookRent = bookRentRequestRepository.save(bookRentRequest);
        return bookRentRequestEntityToBookRentRequestDtoMapper.from(savedBookRent);
    }

    public BookDetailsDto changeBookAvailability(int bookId) {
        BookEntity book = bookRepository.findById(bookId)
                                        .orElseThrow(() -> new EntityNotFoundException("Book"));
        if (!isCurrentUserCurrentlyOwner(book)) {
            throw new IllegalUserException();
        }
        book.setAvailable(!book.isAvailable());
        BookEntity responseBook = bookRepository.save(book);
        return bookEntityToBookDetailsDtoMapper.from(responseBook);
    }

    @Transactional
    public void processBookRentRequestAcceptation(int requestId, boolean accept) {
        BookRentRequestEntity request = bookRentRequestRepository.findById(requestId)
                                                                 .orElseThrow(() -> new EntityNotFoundException("Book Rent Request"));
        if (!isCurrentUserCurrentlyOwner(request.getBook())) {
            throw new IllegalUserException();
        }

        if (accept) {
            acceptBookRentRequest(request);
        } else {
            declineBookRentRequest(requestId);
        }
    }

    private boolean isCurrentUserCurrentlyOwner(BookEntity book) {
        return book.getCurrentOwner().getId() == currentUserService.getUser().getId();
    }

    private boolean isBookRentRequestDuplicated(BookRentRequestEntity bookRentRequest) {
        return bookRentRequestRepository.existsByBook_IdAndRequester_Id(bookRentRequest.getBook().getId(),
                bookRentRequest.getRequester().getId());
    }

    private void declineBookRentRequest(int requestId) {
        bookRentRequestRepository.deleteById(requestId);
    }

    private void acceptBookRentRequest(BookRentRequestEntity request) {
        BookEntity book = request.getBook();
        book.setCurrentOwner(request.getRequester());
        bookRepository.save(book);
        bookRentRequestRepository.deleteById(request.getId());
    }
}
