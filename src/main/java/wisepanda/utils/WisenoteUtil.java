package wisepanda.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

public class WisenoteUtil {
    public static final Object getPropertyValueViaReflection(Object o, String field)
                throws ReflectiveOperationException, IllegalArgumentException, IntrospectionException {
        return new PropertyDescriptor(field, o.getClass()).getReadMethod().invoke(o);
    }
}
