package pl.akai.bookcrossing.ebook.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akai.bookcrossing.ebook.dto.EbookDetailsDto;
import pl.akai.bookcrossing.ebook.dto.EbookListDto;
import pl.akai.bookcrossing.ebook.dto.EbookRequestDto;
import pl.akai.bookcrossing.ebook.service.EbookService;

@RestController
@RequiredArgsConstructor
public class EbookController {

    private final EbookService ebookService;

    @GetMapping("/api/ebooks")
    public ResponseEntity<Page<EbookListDto>> getAllEbooks(Pageable pageable) {
        Page<EbookListDto> ebooks = ebookService.getEbooksByPageable(pageable);
        return new ResponseEntity<>(ebooks, HttpStatus.OK);
    }

    @GetMapping("/api/ebooks/{id}")
    public ResponseEntity<EbookDetailsDto> getEbook(@PathVariable int id) {
        EbookDetailsDto ebook = ebookService.getEbookById(id);
        return new ResponseEntity<>(ebook, HttpStatus.OK);
    }

    @PutMapping("/api/ebooks/{id}")
    public ResponseEntity<EbookDetailsDto> updateEbook(@PathVariable int id,
                                                       @RequestBody EbookRequestDto ebookDto) {
        EbookDetailsDto ebook = ebookService.updateEbook(id, ebookDto);
        return new ResponseEntity<>(ebook, HttpStatus.OK);
    }

}
