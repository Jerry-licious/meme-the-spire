package memethespire;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionUtils {
    public static Object getPrivate(Object object, Class<?> c, String fieldName) {
        try {
            Field field = c.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } // Shouldn't happen.
        catch (NoSuchFieldException | IllegalAccessException ignored) {
            return null;
        }
    }

    public static void setPrivate(Object object, Class<?> c, String fieldName, Object value) {
        try {
            Field field = c.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException ignored) { }
    }

    public static void setFinal(Object object, Class<?> c, String fieldName, Object value) {
        try {
            Field field = c.getDeclaredField(fieldName);
            field.setAccessible(true);

            Field modifiersField = field.getClass().getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException ignored) { }
    }

    public static Object getPrivateStatic(Class<?> c, String fieldName) {
        return getPrivate(null, c, fieldName);
    }

    public static void invokePrivate(Object object, Class<?> c, String methodName) {
        try {
            Method method = c.getDeclaredMethod(methodName);
            method.setAccessible(true);

            method.invoke(object);
        } // Shouldn't happen.
        catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) { }
    }
}
