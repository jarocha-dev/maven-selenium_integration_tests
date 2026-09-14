package io.bootify.my_app.service;

import io.bootify.my_app.domain.BikeModel;
import io.bootify.my_app.domain.UserBike;
import io.bootify.my_app.events.BeforeDeleteBikeModel;
import io.bootify.my_app.events.BeforeDeleteUserBike;
import io.bootify.my_app.model.UserBikeDTO;
import io.bootify.my_app.repos.BikeModelRepository;
import io.bootify.my_app.repos.UserBikeRepository;
import io.bootify.my_app.util.CustomCollectors;
import io.bootify.my_app.util.NotFoundException;
import io.bootify.my_app.util.ReferencedException;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserBikeService {

    private final UserBikeRepository userBikeRepository;
    private final BikeModelRepository bikeModelRepository;
    private final ApplicationEventPublisher publisher;

    public UserBikeService(final UserBikeRepository userBikeRepository,
            final BikeModelRepository bikeModelRepository,
            final ApplicationEventPublisher publisher) {
        this.userBikeRepository = userBikeRepository;
        this.bikeModelRepository = bikeModelRepository;
        this.publisher = publisher;
    }

    public List<UserBikeDTO> findAll() {
        final List<UserBike> userBikes = userBikeRepository.findAll(Sort.by("id"));
        return userBikes.stream()
                .map(userBike -> mapToDTO(userBike, new UserBikeDTO()))
                .toList();
    }

    public UserBikeDTO get(final Long id) {
        return userBikeRepository.findById(id)
                .map(userBike -> mapToDTO(userBike, new UserBikeDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UserBikeDTO userBikeDTO) {
        final UserBike userBike = new UserBike();
        mapToEntity(userBikeDTO, userBike);
        return userBikeRepository.save(userBike).getId();
    }

    public void update(final Long id, final UserBikeDTO userBikeDTO) {
        final UserBike userBike = userBikeRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userBikeDTO, userBike);
        userBikeRepository.save(userBike);
    }

    public void delete(final Long id) {
        final UserBike userBike = userBikeRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        publisher.publishEvent(new BeforeDeleteUserBike(id));
        userBikeRepository.delete(userBike);
    }

    private UserBikeDTO mapToDTO(final UserBike userBike, final UserBikeDTO userBikeDTO) {
        userBikeDTO.setId(userBike.getId());
        userBikeDTO.setName(userBike.getName());
        userBikeDTO.setSpeedsFront(userBike.getSpeedsFront());
        userBikeDTO.setSpeedsRear(userBike.getSpeedsRear());
        userBikeDTO.setObservations(userBike.getObservations());
        userBikeDTO.setBikeModel(userBike.getBikeModel() == null ? null : userBike.getBikeModel().getId());
        return userBikeDTO;
    }

    private UserBike mapToEntity(final UserBikeDTO userBikeDTO, final UserBike userBike) {
        userBike.setName(userBikeDTO.getName());
        userBike.setSpeedsFront(userBikeDTO.getSpeedsFront());
        userBike.setSpeedsRear(userBikeDTO.getSpeedsRear());
        userBike.setObservations(userBikeDTO.getObservations());
        final BikeModel bikeModel = userBikeDTO.getBikeModel() == null ? null : bikeModelRepository.findById(userBikeDTO.getBikeModel())
                .orElseThrow(() -> new NotFoundException("bikeModel not found"));
        userBike.setBikeModel(bikeModel);
        return userBike;
    }

    public Map<Long, Long> getUserBikeValues() {
        return userBikeRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(UserBike::getId, UserBike::getId));
    }

    @EventListener(BeforeDeleteBikeModel.class)
    public void on(final BeforeDeleteBikeModel event) {
        final ReferencedException referencedException = new ReferencedException();
        final UserBike bikeModelUserBike = userBikeRepository.findFirstByBikeModelId(event.getId());
        if (bikeModelUserBike != null) {
            referencedException.setKey("bikeModel.userBike.bikeModel.referenced");
            referencedException.addParam(bikeModelUserBike.getId());
            throw referencedException;
        }
    }

}
