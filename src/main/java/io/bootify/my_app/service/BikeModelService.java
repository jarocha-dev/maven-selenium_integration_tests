package io.bootify.my_app.service;

import io.bootify.my_app.domain.BikeModel;
import io.bootify.my_app.events.BeforeDeleteBikeModel;
import io.bootify.my_app.model.BikeModelDTO;
import io.bootify.my_app.repos.BikeModelRepository;
import io.bootify.my_app.util.CustomCollectors;
import io.bootify.my_app.util.NotFoundException;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BikeModelService {

    private final BikeModelRepository bikeModelRepository;
    private final ApplicationEventPublisher publisher;

    public BikeModelService(final BikeModelRepository bikeModelRepository,
            final ApplicationEventPublisher publisher) {
        this.bikeModelRepository = bikeModelRepository;
        this.publisher = publisher;
    }

    public List<BikeModelDTO> findAll() {
        final List<BikeModel> bikeModels = bikeModelRepository.findAll(Sort.by("id"));
        return bikeModels.stream()
                .map(bikeModel -> mapToDTO(bikeModel, new BikeModelDTO()))
                .toList();
    }

    public BikeModelDTO get(final Long id) {
        return bikeModelRepository.findById(id)
                .map(bikeModel -> mapToDTO(bikeModel, new BikeModelDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final BikeModelDTO bikeModelDTO) {
        final BikeModel bikeModel = new BikeModel();
        mapToEntity(bikeModelDTO, bikeModel);
        return bikeModelRepository.save(bikeModel).getId();
    }

    public void update(final Long id, final BikeModelDTO bikeModelDTO) {
        final BikeModel bikeModel = bikeModelRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(bikeModelDTO, bikeModel);
        bikeModelRepository.save(bikeModel);
    }

    public void delete(final Long id) {
        final BikeModel bikeModel = bikeModelRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        publisher.publishEvent(new BeforeDeleteBikeModel(id));
        bikeModelRepository.delete(bikeModel);
    }

    private BikeModelDTO mapToDTO(final BikeModel bikeModel, final BikeModelDTO bikeModelDTO) {
        bikeModelDTO.setId(bikeModel.getId());
        bikeModelDTO.setBrandName(bikeModel.getBrandName());
        bikeModelDTO.setModel(bikeModel.getModel());
        bikeModelDTO.setYear(bikeModel.getYear());
        return bikeModelDTO;
    }

    private BikeModel mapToEntity(final BikeModelDTO bikeModelDTO, final BikeModel bikeModel) {
        bikeModel.setBrandName(bikeModelDTO.getBrandName());
        bikeModel.setModel(bikeModelDTO.getModel());
        bikeModel.setYear(bikeModelDTO.getYear());
        return bikeModel;
    }
    
    public Map<Long, String> getBikeModelValues() {
        return bikeModelRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(
                		BikeModel::getId,
                		BikeModel::getBikeModelComboName));
    }

}
