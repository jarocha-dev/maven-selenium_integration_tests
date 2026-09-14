package io.bootify.my_app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;


@Entity
public class MaintenanceLog {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate maintenanceDate;

    @Column
    private String goals;

    @Column(columnDefinition = "longtext")
    private String descriptive;

    @Column
    private Integer odometer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_bike_id")
    private UserBike userBike;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDate getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(final LocalDate maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(final String goals) {
        this.goals = goals;
    }

    public String getDescriptive() {
        return descriptive;
    }

    public void setDescriptive(final String descriptive) {
        this.descriptive = descriptive;
    }

    public Integer getOdometer() {
        return odometer;
    }

    public void setOdometer(final Integer odometer) {
        this.odometer = odometer;
    }

    public UserBike getUserBike() {
        return userBike;
    }

    public void setUserBike(final UserBike userBike) {
        this.userBike = userBike;
    }

}
