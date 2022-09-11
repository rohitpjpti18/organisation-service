package wisepanda.utils;

import java.util.Locale;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public class LocaleUtil {
    public static Locale getCurrentLocale(FacesContext context) {
        Locale locale = null;

        if (context != null) {
            UIViewRoot viewRoot = context.getViewRoot();

            // Prefer the locale set in the view.
            if (viewRoot != null) {
                locale = viewRoot.getLocale();
            }

            // Then the client preferred locale.
            if (locale == null) {
                locale = context.getExternalContext().getRequestLocale();
            }

            // Then the JSF default locale.
            if (locale == null) {
                locale = context.getApplication().getDefaultLocale();
            }
        }

        // Finally the system default locale.
        if (locale == null) {
            locale = Locale.getDefault();
        }

        return locale;
    }

    public static Locale getCurrentLocale() {
        return getCurrentLocale(FacesContext.getCurrentInstance());
    }
}
