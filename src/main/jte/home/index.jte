@import static io.bootify.my_app.util.JteContext.*


@template.layout(pageTitle=localize("home.index.headline"), content=@`
    <h1 class="mb-4">${localize("home.index.headline")}</h1>
    <p class="mb-5">${localize("home.index.text")}</p>
    <div class="col-md-4 mb-5">
        <h4 class="mb-3">${localize("home.index.exploreEntities")}</h4>
        <div class="list-group">
            <a href="/bikeModels" class="list-group-item list-group-item-action">${localize("bikeModel.list.headline")}</a>
            <a href="/userBikes" class="list-group-item list-group-item-action">${localize("userBike.list.headline")}</a>
            <a href="/maintenanceLogs" class="list-group-item list-group-item-action">${localize("maintenanceLog.list.headline")}</a>
        </div>
    </div>
`)
