package io.bootify.my_app.service;

import io.bootify.my_app.domain.MaintenanceLog;
import io.bootify.my_app.domain.UserBike;
import io.bootify.my_app.events.BeforeDeleteUserBike;
import io.bootify.my_app.model.MaintenanceLogDTO;
import io.bootify.my_app.repos.MaintenanceLogRepository;
import io.bootify.my_app.repos.UserBikeRepository;
import io.bootify.my_app.util.NotFoundException;
import io.bootify.my_app.util.ReferencedException;
import java.util.List;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class MaintenanceLogService {

    private final MaintenanceLogRepository maintenanceLogRepository;
    private final UserBikeRepository userBikeRepository;

    public MaintenanceLogService(final MaintenanceLogRepository maintenanceLogRepository,
            final UserBikeRepository userBikeRepository) {
        this.maintenanceLogRepository = maintenanceLogRepository;
        this.userBikeRepository = userBikeRepository;
    }

    public List<MaintenanceLogDTO> findAll() {
        final List<MaintenanceLog> maintenanceLogs = maintenanceLogRepository.findAll(Sort.by("id"));
        return maintenanceLogs.stream()
                .map(maintenanceLog -> mapToDTO(maintenanceLog, new MaintenanceLogDTO()))
                .toList();
    }

    public MaintenanceLogDTO get(final Long id) {
        return maintenanceLogRepository.findById(id)
                .map(maintenanceLog -> mapToDTO(maintenanceLog, new MaintenanceLogDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final MaintenanceLogDTO maintenanceLogDTO) {
        final MaintenanceLog maintenanceLog = new MaintenanceLog();
        mapToEntity(maintenanceLogDTO, maintenanceLog);
        return maintenanceLogRepository.save(maintenanceLog).getId();
    }

    public void update(final Long id, final MaintenanceLogDTO maintenanceLogDTO) {
        final MaintenanceLog maintenanceLog = maintenanceLogRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(maintenanceLogDTO, maintenanceLog);
        maintenanceLogRepository.save(maintenanceLog);
    }

    public void delete(final Long id) {
        final MaintenanceLog maintenanceLog = maintenanceLogRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        maintenanceLogRepository.delete(maintenanceLog);
    }

    private MaintenanceLogDTO mapToDTO(final MaintenanceLog maintenanceLog,
            final MaintenanceLogDTO maintenanceLogDTO) {
        maintenanceLogDTO.setId(maintenanceLog.getId());
        maintenanceLogDTO.setMaintenanceDate(maintenanceLog.getMaintenanceDate());
        maintenanceLogDTO.setGoals(maintenanceLog.getGoals());
        maintenanceLogDTO.setDescriptive(maintenanceLog.getDescriptive());
        maintenanceLogDTO.setOdometer(maintenanceLog.getOdometer());
        maintenanceLogDTO.setUserBike(maintenanceLog.getUserBike() == null ? null : maintenanceLog.getUserBike().getId());
        return maintenanceLogDTO;
    }

    private MaintenanceLog mapToEntity(final MaintenanceLogDTO maintenanceLogDTO,
            final MaintenanceLog maintenanceLog) {
        maintenanceLog.setMaintenanceDate(maintenanceLogDTO.getMaintenanceDate());
        maintenanceLog.setGoals(maintenanceLogDTO.getGoals());
        maintenanceLog.setDescriptive(maintenanceLogDTO.getDescriptive());
        maintenanceLog.setOdometer(maintenanceLogDTO.getOdometer());
        final UserBike userBike = maintenanceLogDTO.getUserBike() == null ? null : userBikeRepository.findById(maintenanceLogDTO.getUserBike())
                .orElseThrow(() -> new NotFoundException("userBike not found"));
        maintenanceLog.setUserBike(userBike);
        return maintenanceLog;
    }

    @EventListener(BeforeDeleteUserBike.class)
    public void on(final BeforeDeleteUserBike event) {
        final ReferencedException referencedException = new ReferencedException();
        final MaintenanceLog userBikeMaintenanceLog = maintenanceLogRepository.findFirstByUserBikeId(event.getId());
        if (userBikeMaintenanceLog != null) {
            referencedException.setKey("userBike.maintenanceLog.userBike.referenced");
            referencedException.addParam(userBikeMaintenanceLog.getId());
            throw referencedException;
        }
    }

}
