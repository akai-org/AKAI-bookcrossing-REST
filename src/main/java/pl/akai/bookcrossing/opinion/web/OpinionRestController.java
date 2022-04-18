package pl.akai.bookcrossing.opinion.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akai.bookcrossing.opinion.dto.OpinionDto;
import pl.akai.bookcrossing.opinion.dto.OpinionRequestDto;
import pl.akai.bookcrossing.opinion.service.OpinionService;

@RestController
@RequiredArgsConstructor
public class OpinionRestController {

    private final OpinionService opinionService;

    @GetMapping({"/api/books/{resourceId}/opinions",
            "/api/ebooks/{resourceId}/opinions"})
    public ResponseEntity<Page<OpinionDto>> getOpinionsByResourceId(@PathVariable int resourceId,
                                                                    Pageable pageable) {
        Page<OpinionDto> opinions = opinionService.getOpinionsByResourceId(resourceId, pageable);
        return new ResponseEntity<>(opinions, HttpStatus.OK);
    }

    @PostMapping({"/api/books/{resourceId}/opinions",
            "/api/ebooks/{resourceId}/opinions"})
    public ResponseEntity<OpinionDto> opinionSubmit(@PathVariable int resourceId,
                                                    @RequestBody OpinionRequestDto opinionDto) {
        OpinionDto opinion = opinionService.addOpinion(opinionDto, resourceId);
        return new ResponseEntity<>(opinion, HttpStatus.CREATED);
    }

    @PutMapping({"/api/books/{resourceId}/opinions/{id}",
            "/api/ebooks/{resourceId}/opinions/{id}"})
    public ResponseEntity<OpinionDto> updateOpinion(@PathVariable int resourceId,
                                                    @PathVariable int id,
                                                    @RequestBody OpinionRequestDto opinionDto) {
        OpinionDto opinion = opinionService.updateOpinion(id, opinionDto, resourceId);
        return new ResponseEntity<>(opinion, HttpStatus.OK);
    }

    @DeleteMapping({"/api/books/{resourceId}/opinions/{id}",
            "/api/ebooks/{resourceId}/opinions/{id}"})
    public ResponseEntity<Void> deleteOpinion(@PathVariable int resourceId,
                                              @PathVariable int id) {
        opinionService.deleteOpinion(id, resourceId);
        return ResponseEntity.noContent().build();
    }
}
