package io.bootify.my_app.model;

import jakarta.validation.constraints.Size;


public class UserBikeDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String speedsFront;

    @Size(max = 255)
    private String speedsRear;

    @Size(max = 255)
    private String observations;

    private Long bikeModel;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSpeedsFront() {
        return speedsFront;
    }

    public void setSpeedsFront(final String speedsFront) {
        this.speedsFront = speedsFront;
    }

    public String getSpeedsRear() {
        return speedsRear;
    }

    public void setSpeedsRear(final String speedsRear) {
        this.speedsRear = speedsRear;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(final String observations) {
        this.observations = observations;
    }

    public Long getBikeModel() {
        return bikeModel;
    }

    public void setBikeModel(final Long bikeModel) {
        this.bikeModel = bikeModel;
    }

}
