package cn.fyy.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 通用Bean工具类，支持控制null值复制
 *
 * @author fyy
 */
public class BeanUtil {

    /**
     * 复制对象属性
     *
     * @param source   源对象
     * @param target   目标对象
     * @param copyNull 是否复制null值（true表示复制null值，false表示忽略null值）
     * @throws BeansException 如果复制过程中出错
     */
    public static void copyProperties(Object source, Object target, boolean copyNull) throws BeansException {
        copyProperties(source, target, copyNull, (String[]) null);
    }

    /**
     * 复制对象属性
     *
     * @param source           源对象
     * @param target           目标对象
     * @param copyNull         是否复制null值（true表示复制null值，false表示忽略null值）
     * @param ignoreProperties 要忽略的属性列表
     * @throws BeansException 如果复制过程中出错
     */
    public static void copyProperties(Object source, Object target, boolean copyNull, String... ignoreProperties) throws BeansException {
        Assert.notNull(source, "源 必须不为 null");
        Assert.notNull(target, "目标 必须不为 null");

        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        Set<String> ignoreList = (ignoreProperties != null ? new HashSet<>(Arrays.asList(ignoreProperties)) : null);

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null &&
                            ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);

                            // 根据copyNull参数决定是否复制null值
                            // copyNull为true时复制所有值（包括null）
                            // copyNull为false时不复制null值
                            if (copyNull || value != null) {
                                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                    writeMethod.setAccessible(true);
                                }
                                writeMethod.invoke(target, value);
                            }
                        } catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "无法复制属性 '" + targetPd.getName() + "' 从源至目标", ex);
                        }
                    }
                }
            }
        }
    }
}
