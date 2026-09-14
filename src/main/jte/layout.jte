@import static io.bootify.my_app.util.JteContext.*

@param gg.jte.Content pageTitle
@param gg.jte.Content content


<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${pageTitle} - ${localize("app.title")}</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" type="image/x-icon" href="/favicon.ico">
        <link href="/webjars/bootstrap/5.3.8/css/bootstrap.min.css" rel="stylesheet" />
        <link href="/webjars/flatpickr/4.6.13/dist/flatpickr.min.css" rel="stylesheet" />
        <link href="/css/app.css" rel="stylesheet" />
        <script src="/webjars/bootstrap/5.3.8/js/bootstrap.bundle.min.js" defer></script>
        <script src="/webjars/flatpickr/4.6.13/dist/flatpickr.min.js" defer></script>
        <script src="/js/app.js" defer></script>
    </head>
    <body>
        <header class="bg-light">
            <div class="container">
                <nav class="navbar navbar-light navbar-expand-md">
                    <a href="/" class="navbar-brand">
                        <img src="/images/logo.png" alt="${localize("app.title")}" width="30" height="30" class="d-inline-block align-top" />
                        <span class="ps-1">${localize("app.title")}</span>
                    </a>
                    <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarToggle"
                            aria-label="${localize("navigation.toggle")}" aria-controls="navbarToggle" aria-expanded="false">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarToggle">
                        <ul class="navbar-nav ms-auto">
                            <li class="navbar-item">
                                <a href="/" class="nav-link">${localize("navigation.home")}</a>
                            </li>
                            <li class="navbar-item dropdown">
                                <button type="button" class="nav-link dropdown-toggle" data-bs-toggle="dropdown" id="navbarEntitiesLink"
                                        aria-expanded="false">${localize("navigation.entities")}</button>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarEntitiesLink">
                                    <li><a href="/bikeModels" class="dropdown-item">${localize("bikeModel.list.headline")}</a></li>
                                    <li><a href="/userBikes" class="dropdown-item">${localize("userBike.list.headline")}</a></li>
                                    <li><a href="/maintenanceLogs" class="dropdown-item">${localize("maintenanceLog.list.headline")}</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </header>
        <main class="my-5">
            <div class="container">
                @if (getMsgSuccess() != null)
                    <p class="alert alert-success mb-4" role="alert">${getMsgSuccess()}</p>
                @elseif (getMsgInfo() != null)
                    <p class="alert alert-info mb-4" role="alert">${getMsgInfo()}</p>
                @elseif (getMsgError() != null)
                    <p class="alert alert-danger mb-4" role="alert">${getMsgError()}</p>
                @endif
                ${content}
            </div>
        </main>
    </body>
</html>
