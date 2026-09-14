@import static io.bootify.my_app.util.JteContext.*


@template.layout(pageTitle=localize("maintenanceLog.add.headline"), content=@`
    <div class="d-flex flex-wrap mb-4">
        <h1 class="flex-grow-1">${localize("maintenanceLog.add.headline")}</h1>
        <div>
            <a href="/maintenanceLogs" class="btn btn-secondary">${localize("maintenanceLog.add.back")}</a>
        </div>
    </div>
    @template.components.globalErrors(object="maintenanceLog")
    <form action="${getRequestUri()}" method="post" novalidate="">
        @template.components.inputRow(object="maintenanceLog", field="maintenanceDate", inputClass="js-datepicker")
        @template.components.inputRow(object="maintenanceLog", field="goals")
        @template.components.inputRow(object="maintenanceLog", field="descriptive", type="textarea")
        @template.components.inputRow(object="maintenanceLog", field="odometer", type="number")
        @template.components.inputRow(object="maintenanceLog", field="userBike", type="select")
        <input type="submit" value="${localize("maintenanceLog.add.headline")}" class="btn btn-primary mt-4" />
    </form>
`)
