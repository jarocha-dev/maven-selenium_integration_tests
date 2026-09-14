@import static io.bootify.my_app.util.JteContext.*

@param io.bootify.my_app.model.BikeModelDTO bikeModel
@param Boolean withDownloads = false


@template.layout(pageTitle=localize("bikeModel.edit.headline"), content=@`
    <div class="d-flex flex-wrap mb-4">
        <h1 class="flex-grow-1">${localize("bikeModel.edit.headline")}</h1>
        <div>
            <a href="/bikeModels" class="btn btn-secondary">${localize("bikeModel.edit.back")}</a>
        </div>
    </div>
    @template.components.globalErrors(object="bikeModel")
    <form action="${getRequestUri()}" method="post" novalidate="">
        @template.components.inputRow(object="bikeModel", field="id", type="number", disabled=true)
        @template.components.inputRow(object="bikeModel", field="brandName", required=true)
        @template.components.inputRow(object="bikeModel", field="model", required=true)
        @template.components.inputRow(object="bikeModel", field="year", type="number")
        <input type="submit" value="${localize("bikeModel.edit.headline")}" class="btn btn-primary mt-4" />
    </form>
`)
