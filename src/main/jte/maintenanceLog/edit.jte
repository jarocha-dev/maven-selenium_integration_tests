@import static io.bootify.my_app.util.JteContext.*

@param io.bootify.my_app.model.MaintenanceLogDTO maintenanceLog
@param Boolean withDownloads = false


@template.layout(pageTitle=localize("maintenanceLog.edit.headline"), content=@`
    <div class="d-flex flex-wrap mb-4">
        <h1 class="flex-grow-1">${localize("maintenanceLog.edit.headline")}</h1>
        <div>
            <a href="/maintenanceLogs" class="btn btn-secondary">${localize("maintenanceLog.edit.back")}</a>
        </div>
    </div>
    @template.components.globalErrors(object="maintenanceLog")
    <form action="${getRequestUri()}" method="post" novalidate="">
        @template.components.inputRow(object="maintenanceLog", field="id", type="number", disabled=true)
        @template.components.inputRow(object="maintenanceLog", field="maintenanceDate", inputClass="js-datepicker")
        @template.components.inputRow(object="maintenanceLog", field="goals")
        @template.components.inputRow(object="maintenanceLog", field="descriptive", type="textarea")
        @template.components.inputRow(object="maintenanceLog", field="odometer", type="number")
        @template.components.inputRow(object="maintenanceLog", field="userBike", type="select")
        <input type="submit" value="${localize("maintenanceLog.edit.headline")}" class="btn btn-primary mt-4" />
    </form>
`)
