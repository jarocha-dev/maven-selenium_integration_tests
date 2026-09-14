package io.bootify.my_app.repos;

import io.bootify.my_app.domain.BikeModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BikeModelRepository extends JpaRepository<BikeModel, Long> {
}
