package io.bootify.my_app.rest;

import io.bootify.my_app.model.UserBikeDTO;
import io.bootify.my_app.service.UserBikeService;
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
@RequestMapping(value = "/api/userBikes", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserBikeResource {

    private final UserBikeService userBikeService;

    public UserBikeResource(final UserBikeService userBikeService) {
        this.userBikeService = userBikeService;
    }

    @GetMapping
    public ResponseEntity<List<UserBikeDTO>> getAllUserBikes() {
        return ResponseEntity.ok(userBikeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBikeDTO> getUserBike(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(userBikeService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createUserBike(@RequestBody @Valid final UserBikeDTO userBikeDTO) {
        final Long createdId = userBikeService.create(userBikeDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateUserBike(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final UserBikeDTO userBikeDTO) {
        userBikeService.update(id, userBikeDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserBike(@PathVariable(name = "id") final Long id) {
        userBikeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
