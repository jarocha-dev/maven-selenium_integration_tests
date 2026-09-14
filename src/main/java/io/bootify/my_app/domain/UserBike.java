package io.bootify.my_app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class UserBike {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String speedsFront;

    @Column
    private String speedsRear;

    @Column
    private String observations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bike_model_id")
    private BikeModel bikeModel;

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

    public BikeModel getBikeModel() {
        return bikeModel;
    }

    public void setBikeModel(final BikeModel bikeModel) {
        this.bikeModel = bikeModel;
    }

}
