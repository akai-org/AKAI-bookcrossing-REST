package pl.akai.bookcrossing.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.akai.bookcrossing.book.database.BookEntity;
import pl.akai.bookcrossing.book.database.BookRepository;
import pl.akai.bookcrossing.book.dto.BookDetailsDto;
import pl.akai.bookcrossing.book.dto.BookListDto;
import pl.akai.bookcrossing.book.dto.BookRequestDto;
import pl.akai.bookcrossing.book.mapper.BookEntityToBookDetailsDtoMapper;
import pl.akai.bookcrossing.book.mapper.BookEntityToBookListDtoMapper;
import pl.akai.bookcrossing.book.mapper.BookRequestDtoToBookEntityMapper;
import pl.akai.bookcrossing.security.CurrentUserService;
import pl.akai.bookcrossing.tag.service.TagService;
import pl.akai.bookcrossing.user.database.UserEntity;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CurrentUserService currentUserService;
    private final TagService tagService;
    private final BookEntityToBookListDtoMapper bookEntityToBookListDtoMapper;
    private final BookEntityToBookDetailsDtoMapper bookEntityToBookDetailsDtoMapper;
    private final BookRequestDtoToBookEntityMapper bookRequestDtoToBookEntityMapper;


    public Page<BookListDto> getBooksByPageable(Pageable pageable) {
        return bookRepository.findAll(pageable)
                             .map(bookEntityToBookListDtoMapper::from);
    }

    public Page<BookListDto> getBooksByPageableAndTagId(Pageable pageable, int tagId) {
        return bookRepository.findAllByTags_Id(tagId, pageable)
                             .map(bookEntityToBookListDtoMapper::from);
    }

    public Page<BookListDto> getBooksCurrentlyOwnedByCurrentUser(Pageable pageable) {
        UserEntity user = currentUserService.getUser();
        return bookRepository.findAllByCurrentOwner_Id(user.getId(), pageable)
                             .map(bookEntityToBookListDtoMapper::from);
    }

    public Page<BookListDto> getBooksOriginallyOwnedByCurrentUser(Pageable pageable) {
        var user = currentUserService.getUser();
        return bookRepository.findAllByOriginalOwner_Id(user.getId(), pageable)
                             .map(bookEntityToBookListDtoMapper::from);
    }

    public BookDetailsDto getBookById(int id) {
        return bookRepository.findById(id)
                             .map(bookEntityToBookDetailsDtoMapper::from)
                             .orElseThrow(() -> new EntityNotFoundException("Book"));
    }

    @Transactional
    public BookDetailsDto addBook(BookRequestDto bookDto) {
        UserEntity user = currentUserService.getUser();
        BookEntity book = bookRequestDtoToBookEntityMapper.from(bookDto);
        book.setCurrentOwner(user);
        book.setOriginalOwner(user);
        book.setTags(tagService.getTagEntitiesFromStringList(bookDto.getTags()));
        BookEntity responseBook = bookRepository.save(book);
        return bookEntityToBookDetailsDtoMapper.from(responseBook);
    }

    @Transactional
    public BookDetailsDto updateBook(int id, BookRequestDto bookDto) {
        BookEntity book = bookRepository.findById(id)
                                        .orElseThrow(() -> new EntityNotFoundException("Book"));
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
        book.setTags(tagService.getTagEntitiesFromStringList(bookDto.getTags()));
        BookEntity responseBook = bookRepository.save(book);
        return bookEntityToBookDetailsDtoMapper.from(responseBook);
    }

    @Transactional
    public void deleteBook(int id) {
        try {
            bookRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Book");
        }
    }

}
