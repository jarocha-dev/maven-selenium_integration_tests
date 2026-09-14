@import static io.bootify.my_app.util.JteContext.*
@import io.bootify.my_app.model.MaintenanceLogDTO

@param java.util.List<MaintenanceLogDTO> maintenanceLogs


@template.layout(pageTitle=localize("maintenanceLog.list.headline"), content=@`
    <div class="d-flex flex-wrap mb-4">
        <h1 class="flex-grow-1">${localize("maintenanceLog.list.headline")}</h1>
        <div>
            <a href="/maintenanceLogs/add" class="btn btn-primary ms-2">${localize("maintenanceLog.list.createNew")}</a>
        </div>
    </div>
    @if (maintenanceLogs.isEmpty())
        <div>${localize("maintenanceLog.list.empty")}</div>
    @else
    <div class="table-responsive">
        <table class="table table-striped table-hover align-middle">
            <thead>
                <tr>
                    <th scope="col">${localize("maintenanceLog.id.label")}</th>
                    <th scope="col">${localize("maintenanceLog.maintenanceDate.label")}</th>
                    <th scope="col">${localize("maintenanceLog.goals.label")}</th>
                    <th scope="col">${localize("maintenanceLog.odometer.label")}</th>
                    <th scope="col">${localize("maintenanceLog.userBike.label")}</th>
                    <th><!-- --></th>
                </tr>
            </thead>
            <tbody>
                @for (MaintenanceLogDTO maintenanceLog : maintenanceLogs)
                <tr>
                    <td>${maintenanceLog.getId()}</td>
                    <td>${maintenanceLog.getMaintenanceDate() == null ? "-" : maintenanceLog.getMaintenanceDate().toString()}</td>
                    <td>${maintenanceLog.getGoals()}</td>
                    <td>${maintenanceLog.getOdometer()}</td>
                    <td>${maintenanceLog.getUserBike()}</td>
                    <td>
                        <div class="float-end text-nowrap">
                            <a href="/maintenanceLogs/edit/${maintenanceLog.getId()}" class="btn btn-sm btn-secondary">${localize("maintenanceLog.list.edit")}</a>
                            <form action="/maintenanceLogs/delete/${maintenanceLog.getId()}" data-confirm-message="${localize("delete.confirm")}"
                                    method="post" class="js-submit-confirm d-inline">
                                <button type="submit" class="btn btn-sm btn-secondary">${localize("maintenanceLog.list.delete")}</button>
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
