@import static io.bootify.my_app.util.JteContext.*
@import io.bootify.my_app.model.BikeModelDTO

@param java.util.List<BikeModelDTO> bikeModels


@template.layout(pageTitle=localize("bikeModel.list.headline"), content=@`
    <div class="d-flex flex-wrap mb-4">
        <h1 class="flex-grow-1">${localize("bikeModel.list.headline")}</h1>
        <div>
            <a href="/bikeModels/add" class="btn btn-primary ms-2">${localize("bikeModel.list.createNew")}</a>
        </div>
    </div>
    @if (bikeModels.isEmpty())
        <div>${localize("bikeModel.list.empty")}</div>
    @else
    <div class="table-responsive">
        <table class="table table-striped table-hover align-middle">
            <thead>
                <tr>
                    <th scope="col">${localize("bikeModel.id.label")}</th>
                    <th scope="col">${localize("bikeModel.brandName.label")}</th>
                    <th scope="col">${localize("bikeModel.model.label")}</th>
                    <th scope="col">${localize("bikeModel.year.label")}</th>
                    <th><!-- --></th>
                </tr>
            </thead>
            <tbody>
                @for (BikeModelDTO bikeModel : bikeModels)
                <tr>
                    <td>${bikeModel.getId()}</td>
                    <td>${bikeModel.getBrandName()}</td>
                    <td>${bikeModel.getModel()}</td>
                    <td>${bikeModel.getYear()}</td>
                    <td>
                        <div class="float-end text-nowrap">
                            <a href="/bikeModels/edit/${bikeModel.getId()}" class="btn btn-sm btn-secondary">${localize("bikeModel.list.edit")}</a>
                            <form action="/bikeModels/delete/${bikeModel.getId()}" data-confirm-message="${localize("delete.confirm")}"
                                    method="post" class="js-submit-confirm d-inline">
                                <button type="submit" class="btn btn-sm btn-secondary">${localize("bikeModel.list.delete")}</button>
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
