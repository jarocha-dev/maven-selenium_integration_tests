package io.bootify.my_app.model;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;


public class MaintenanceLogDTO {

    private Long id;

    private LocalDate maintenanceDate;

    @Size(max = 255)
    private String goals;

    private String descriptive;

    private Integer odometer;

    private Long userBike;

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

    public Long getUserBike() {
        return userBike;
    }

    public void setUserBike(final Long userBike) {
        this.userBike = userBike;
    }

}
