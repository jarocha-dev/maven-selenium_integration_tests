@import static io.bootify.my_app.util.JteContext.*
@import static io.bootify.my_app.util.WebUtils.*
@import java.util.List
@import org.springframework.validation.FieldError

@param String object
@param String field
@param String type = "text"
@param Boolean required = false
@param Boolean disabled = false
@param String inputClass = ""


<div class="row mb-3">
    @if (!type.equals("checkbox"))
        <label for="${type.equals("radio") ? "" : field}" class="col-md-2 col-form-label">
            <span>${localize(object + "." + field + ".label")}${required ? "*" : ""}</span>
        </label>
    @endif

    <div class="${"col-md-10" + (type.equals("checkbox") ? " offset-md-2" : "")}">
        @if (type.equals("checkbox"))
            <div class="form-check">
                <input type="checkbox" id="${field}" name="${field}" disabled="${disabled}" checked="${"true".equals(getFieldValue(object, field))}"
                        class="form-check-input${getBindingResult(object).hasFieldErrors(field) ? " is-invalid" : ""} ${inputClass}" />
                <input type="hidden" name="_${field}" value="on" />
                <label for="${field}" class="form-check-label">
                    <span>${localize(object + "." + field + ".label")}${required ? "*" : ""}</span>
                </label>
            </div>
        @elseif (type.equals("text") || type.equals("password") || type.equals("email") || type.equals("tel") || type.equals("number"))
            <input type="${type}" id="${field}" name="${field}" disabled="${disabled}" value="${getFieldValue(object, field)}"
                    class="form-control${getBindingResult(object).hasFieldErrors(field) ? " is-invalid" : ""} ${inputClass}" />
        @elseif (type.equals("textarea"))
            <textarea id="${field}" name="${field}" disabled="${disabled}"
                    class="form-control${getBindingResult(object).hasFieldErrors(field) ? " is-invalid" : ""} ${inputClass}"
                    >${getFieldValue(object, field)}</textarea>
        @elseif (type.equals("select"))
            <select id="${field}" name="${field}" disabled="${disabled}"
                    class="form-select${getBindingResult(object).hasFieldErrors(field) ? " is-invalid" : ""} ${inputClass}">
                <option value="">${localize("select.empty.label")}</option>
                @if (getModel().getAttribute(field + "Values") instanceof java.util.Map<?, ?> valueMap)
                    @for (java.util.Map.Entry<?, ?> optionValue : valueMap.entrySet())
                        <option value="${optionValue.getKey().toString()}" selected="${optionValue.getKey().toString().equals(getFieldValue(object, field))}">${optionValue.getValue().toString()}</option>
                    @endfor
                @elseif (getModel().getAttribute(field + "Values") instanceof java.util.Collection<?> valueList)
                    @for (Object optionValue : valueList)
                        <option value="${optionValue.toString()}" selected="${optionValue.toString().equals(getFieldValue(object, field))}">${optionValue.toString()}</option>
                    @endfor
                @endif
            </select>
        @elseif (type.equals("multiselect"))
            <select multiple="multiple" id="${field}" name="${field}" disabled="${disabled}"
                    class="form-select${getBindingResult(object).hasFieldErrors(field) ? " is-invalid" : ""} ${inputClass}">
                @if (getModel().getAttribute(field + "Values") instanceof java.util.Map<?, ?> valueMap)
                    @for (java.util.Map.Entry<?, ?> optionValue : valueMap.entrySet())
                        <option value="${optionValue.getKey().toString()}" selected="${getFieldValue(object, field, List.class) != null && getFieldValue(object, field, List.class).contains(optionValue.getKey())}">${optionValue.getValue().toString()}</option>
                    @endfor
                @elseif (getModel().getAttribute(field + "Values") instanceof java.util.Collection<?> valueList)
                    @for (Object optionValue : valueList)
                        <option value="${optionValue.toString()}" selected="${getFieldValue(object, field, List.class) != null && getFieldValue(object, field, List.class).contains(optionValue)}">${optionValue.toString()}</option>
                    @endfor
                @endif
            </select>
        @elseif (type.equals("radio"))
            @if (!required)
                <div class="form-check form-check-inline pt-2">
                    <input type="radio" id="${field}" name="${field}" checked="${"".equals(getFieldValue(object, field))}" disabled="${disabled}"
                            class="form-check-input${getBindingResult(object).hasFieldErrors(field) ? " is-invalid" : ""} ${inputClass}" />
                    <label for="${field}" class="form-check-label">${localize("select.empty.label")}</label>
                </div>
            @endif
            @if (getModel().getAttribute(field + "Values") instanceof Object[] valueList)
                @for (Object radioValue : valueList)
                    <div class="form-check form-check-inline pt-2">
                        <input type="radio" id="${field + radioValue}" name="${field}" value="${radioValue.toString()}" checked="${radioValue.toString().equals(getFieldValue(object, field))}" disabled="${disabled}"
                                class="form-check-input${getBindingResult(object).hasFieldErrors(field) ? " is-invalid" : ""} ${inputClass}" />
                        <label for="${field + radioValue}" class="form-check-label">${radioValue.toString()}</label>
                    </div>
                @endfor
            @endif
        @endif

        @if (getBindingResult(object).hasFieldErrors(field))
            <div class="invalid-feedback d-block">
            @for (FieldError fieldError : getBindingResult(object).getFieldErrors(field))
                <p class="mb-0">
                    ${(fieldError.getField().equals(field) ? "" : fieldError.getField() + ": ") + getMessage(fieldError)}
                </p>
            @endfor
            </div>
        @endif
    </div>
</div>
