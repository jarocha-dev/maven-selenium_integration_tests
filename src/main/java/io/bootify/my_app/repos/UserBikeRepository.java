package io.bootify.my_app.repos;

import io.bootify.my_app.domain.UserBike;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserBikeRepository extends JpaRepository<UserBike, Long> {

    UserBike findFirstByBikeModelId(Long id);

}
