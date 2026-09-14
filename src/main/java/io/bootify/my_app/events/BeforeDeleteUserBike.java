package io.bootify.my_app.events;


public class BeforeDeleteUserBike {

    private Long id;

    public BeforeDeleteUserBike(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
