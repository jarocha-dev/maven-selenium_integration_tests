@import static io.bootify.my_app.util.JteContext.*


@template.layout(pageTitle=localize("bikeModel.add.headline"), content=@`
    <div class="d-flex flex-wrap mb-4">
        <h1 class="flex-grow-1">${localize("bikeModel.add.headline")}</h1>
        <div>
            <a href="/bikeModels" class="btn btn-secondary">${localize("bikeModel.add.back")}</a>
        </div>
    </div>
    @template.components.globalErrors(object="bikeModel")
    <form action="${getRequestUri()}" method="post" novalidate="">
        @template.components.inputRow(object="bikeModel", field="brandName", required=true)
        @template.components.inputRow(object="bikeModel", field="model", required=true)
        @template.components.inputRow(object="bikeModel", field="year", type="number")
        <input type="submit" value="${localize("bikeModel.add.headline")}" class="btn btn-primary mt-4" />
    </form>
`)
