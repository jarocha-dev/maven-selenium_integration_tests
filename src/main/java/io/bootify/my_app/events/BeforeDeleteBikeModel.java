package io.bootify.my_app.events;


public class BeforeDeleteBikeModel {

    private Long id;

    public BeforeDeleteBikeModel(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
