package io.bootify.my_app.rest;

import io.bootify.my_app.model.MaintenanceLogDTO;
import io.bootify.my_app.service.MaintenanceLogService;
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
@RequestMapping(value = "/api/maintenanceLogs", produces = MediaType.APPLICATION_JSON_VALUE)
public class MaintenanceLogResource {

    private final MaintenanceLogService maintenanceLogService;

    public MaintenanceLogResource(final MaintenanceLogService maintenanceLogService) {
        this.maintenanceLogService = maintenanceLogService;
    }

    @GetMapping
    public ResponseEntity<List<MaintenanceLogDTO>> getAllMaintenanceLogs() {
        return ResponseEntity.ok(maintenanceLogService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceLogDTO> getMaintenanceLog(
            @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(maintenanceLogService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createMaintenanceLog(
            @RequestBody @Valid final MaintenanceLogDTO maintenanceLogDTO) {
        final Long createdId = maintenanceLogService.create(maintenanceLogDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateMaintenanceLog(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final MaintenanceLogDTO maintenanceLogDTO) {
        maintenanceLogService.update(id, maintenanceLogDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenanceLog(@PathVariable(name = "id") final Long id) {
        maintenanceLogService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
