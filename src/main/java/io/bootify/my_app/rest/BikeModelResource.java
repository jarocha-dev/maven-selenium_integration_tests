package io.bootify.my_app.rest;

import io.bootify.my_app.model.BikeModelDTO;
import io.bootify.my_app.service.BikeModelService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/bikeModels", produces = MediaType.APPLICATION_JSON_VALUE)
public class BikeModelResource {

    private final BikeModelService bikeModelService;

    public BikeModelResource(final BikeModelService bikeModelService) {
        this.bikeModelService = bikeModelService;
    }

    @GetMapping
    public ResponseEntity<List<BikeModelDTO>> getAllBikeModels() {
        return ResponseEntity.ok(bikeModelService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BikeModelDTO> getBikeModel(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(bikeModelService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createBikeModel(
            @RequestBody @Valid final BikeModelDTO bikeModelDTO) {
        final Long createdId = bikeModelService.create(bikeModelDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateBikeModel(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final BikeModelDTO bikeModelDTO) {
        bikeModelService.update(id, bikeModelDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBikeModel(@PathVariable(name = "id") final Long id) {
        bikeModelService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
