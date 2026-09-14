@import static io.bootify.my_app.util.JteContext.*

@param io.bootify.my_app.model.UserBikeDTO userBike
@param Boolean withDownloads = false


@template.layout(pageTitle=localize("userBike.edit.headline"), content=@`
    <div class="d-flex flex-wrap mb-4">
        <h1 class="flex-grow-1">${localize("userBike.edit.headline")}</h1>
        <div>
            <a href="/userBikes" class="btn btn-secondary">${localize("userBike.edit.back")}</a>
        </div>
    </div>
    @template.components.globalErrors(object="userBike")
    <form action="${getRequestUri()}" method="post" novalidate="">
        @template.components.inputRow(object="userBike", field="id", type="number", disabled=true)
        @template.components.inputRow(object="userBike", field="name")
        @template.components.inputRow(object="userBike", field="speedsFront")
        @template.components.inputRow(object="userBike", field="speedsRear")
        @template.components.inputRow(object="userBike", field="observations")
        @template.components.inputRow(object="userBike", field="bikeModel", type="select")
        <input type="submit" value="${localize("userBike.edit.headline")}" class="btn btn-primary mt-4" />
    </form>
`)
