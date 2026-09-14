package io.bootify.my_app.repos;

import io.bootify.my_app.domain.MaintenanceLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MaintenanceLogRepository extends JpaRepository<MaintenanceLog, Long> {

    MaintenanceLog findFirstByUserBikeId(Long id);

}
