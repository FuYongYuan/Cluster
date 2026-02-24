package cn.fyy.database.util;

import cn.fyy.jpa.bean.ao.DataState;
import cn.fyy.jpa.bean.po.BasePO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 通用 Bean 工具类，支持控制 null 值复制
 *
 * @author fyy
 */
public class BeanUtil {
    //------------------------------------------------------------------------------------------------------------------复制

    /**
     * 复制对象属性
     *
     * @param source   源对象
     * @param target   目标对象
     * @param copyNull 是否复制 null 值（ true 表示复制 null 值， false 表示忽略 null 值）
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
     * @param copyNull         是否复制 null 值（ true 表示复制 null 值， false 表示忽略 null 值）
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

    //------------------------------------------------------------------------------------------------------------------赋值

    /**
     * 插入
     *
     * @param source             源
     * @param id                 新增 ID
     * @param currentManagerId   当前管理员 ID
     * @param currentManagerName 当前管理员名称
     * @param localDateTime      时间
     * @param <T>                泛型
     * @return 插入对象
     */
    public static <T extends BasePO> T insert(
            T source,
            Long id,
            Long currentManagerId,
            String currentManagerName,
            LocalDateTime localDateTime
    ) {
        source.setId(id);
        source.setCreatorId(currentManagerId);
        source.setCreatorName(currentManagerName);
        source.setCreateTime(localDateTime);
        source.setUpdaterId(currentManagerId);
        source.setUpdaterName(currentManagerName);
        source.setUpdateTime(localDateTime);
        source.setState(DataState.NORMAL.getCode());
        source.setVersion(0L);
        return source;
    }

    /**
     * 更新
     *
     * @param source             源
     * @param currentManagerId   当前管理员 ID
     * @param currentManagerName 当前管理员名称
     * @param localDateTime      时间
     * @param <T>                泛型
     * @return 更新对象
     */
    public static <T extends BasePO> T update(
            T source,
            Long currentManagerId,
            String currentManagerName,
            LocalDateTime localDateTime
    ) {
        source.setUpdaterId(currentManagerId);
        source.setUpdaterName(currentManagerName);
        source.setUpdateTime(localDateTime);
        return source;
    }
}
