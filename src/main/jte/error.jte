@import static io.bootify.my_app.util.JteContext.*

@param Integer status
@param String error


@template.layout(pageTitle=localize("error.page.headline", status, error), content=@`
    <h1 class="mb-4">${status} - ${error}</h1>
    <p>${localize("error.page.message")}</p>
`)
