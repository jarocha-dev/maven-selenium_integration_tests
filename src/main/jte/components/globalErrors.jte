@import static io.bootify.my_app.util.JteContext.*
@import io.bootify.my_app.util.WebUtils
@import org.springframework.validation.ObjectError

@param String object


@if (getBindingResult(object).hasGlobalErrors())
    <div class="invalid-feedback d-block">
        @for (ObjectError globalError : getBindingResult(object).getGlobalErrors())
            <p class="mb-0">
                ${WebUtils.getMessage(globalError)}
            </p>
        @endfor
    </div>
@endif
