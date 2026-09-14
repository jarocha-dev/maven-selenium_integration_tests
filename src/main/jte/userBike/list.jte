@import static io.bootify.my_app.util.JteContext.*
@import io.bootify.my_app.model.UserBikeDTO

@param java.util.List<UserBikeDTO> userBikes


@template.layout(pageTitle=localize("userBike.list.headline"), content=@`
    <div class="d-flex flex-wrap mb-4">
        <h1 class="flex-grow-1">${localize("userBike.list.headline")}</h1>
        <div>
            <a href="/userBikes/add" class="btn btn-primary ms-2">${localize("userBike.list.createNew")}</a>
        </div>
    </div>
    @if (userBikes.isEmpty())
        <div>${localize("userBike.list.empty")}</div>
    @else
    <div class="table-responsive">
        <table class="table table-striped table-hover align-middle">
            <thead>
                <tr>
                    <th scope="col">${localize("userBike.id.label")}</th>
                    <th scope="col">${localize("userBike.name.label")}</th>
                    <th scope="col">${localize("userBike.speedsFront.label")}</th>
                    <th scope="col">${localize("userBike.speedsRear.label")}</th>
                    <th scope="col">${localize("userBike.observations.label")}</th>
                    <th scope="col">${localize("userBike.bikeModel.label")}</th>
                    <th><!-- --></th>
                </tr>
            </thead>
            <tbody>
                @for (UserBikeDTO userBike : userBikes)
                <tr>
                    <td>${userBike.getId()}</td>
                    <td>${userBike.getName()}</td>
                    <td>${userBike.getSpeedsFront()}</td>
                    <td>${userBike.getSpeedsRear()}</td>
                    <td>${userBike.getObservations()}</td>
                    <td>${userBike.getBikeModel()}</td>
                    <td>
                        <div class="float-end text-nowrap">
                            <a href="/userBikes/edit/${userBike.getId()}" class="btn btn-sm btn-secondary">${localize("userBike.list.edit")}</a>
                            <form action="/userBikes/delete/${userBike.getId()}" data-confirm-message="${localize("delete.confirm")}"
                                    method="post" class="js-submit-confirm d-inline">
                                <button type="submit" class="btn btn-sm btn-secondary">${localize("userBike.list.delete")}</button>
                            </form>
                        </div>
                    </td>
                </tr>
                @endfor
            </tbody>
        </table>
    </div>
    @endif
`)
