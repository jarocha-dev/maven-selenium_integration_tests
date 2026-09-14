package io.bootify.my_app.util;

import gg.jte.Content;
import gg.jte.support.LocalizationSupport;
import java.lang.reflect.Field;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;


public class JteContext {

    private static final LocalizationSupport localizer = WebUtils::getMessage;
    private static final BindingResult emptyBindingResult = new BeanPropertyBindingResult(new Object(), "empty");
    private static final ThreadLocal<ModelMap> model = new ThreadLocal<>();

    public static void init(final ModelMap model) {
        JteContext.model.set(model);
    }

    public static void reset() {
        JteContext.model.remove();
    }

    public static Content localize(final String key, final Object... params) {
        return localizer.localize(key, params);
    }

    public static ModelMap getModel() {
        return model.get();
    }

    public static String getMsgSuccess() {
        return ((String)model.get().getAttribute(WebUtils.MSG_SUCCESS));
    }

    public static String getMsgInfo() {
        return ((String)model.get().getAttribute(WebUtils.MSG_INFO));
    }

    public static String getMsgError() {
        return ((String)model.get().getAttribute(WebUtils.MSG_ERROR));
    }

    public static String getRequestUri() {
        return WebUtils.getRequest().getRequestURI();
    }

    public static BindingResult getBindingResult(final String object) {
        final BindingResult bindingResult = ((BindingResult)model.get().getAttribute(
                "org.springframework.validation.BindingResult." + object));
        if (bindingResult == null) {
            return emptyBindingResult;
        }
        return bindingResult;
    }

    public static String getFieldValue(final String object, final String field) {
        return ((String)getBindingResult(object).getFieldValue(field));
    }

    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(final String object, final String field,
            final Class<T> type) {
        final Object dto = model.get().getAttribute(object);
        if (dto == null) {
            return null;
        }
        try {
            final Field classField = dto.getClass().getDeclaredField(field);
            classField.setAccessible(true);
            return ((T)classField.get(dto));
        } catch (final Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
