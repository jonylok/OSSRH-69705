package com.vtm.ddd.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 实例工厂类，充当IoC容器的门面，通过它可以获得部署在IoC容器中的Bean的实例。 InstanceFactory向客户代码隐藏了IoC
 *
 * @author jony
 */
public class InstanceFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstanceFactory.class);

    /**
     * 以下部分仅用于提供代码测试功能，产品代码不要用
     */
    private static final Map<Object, Object> instances = new HashMap<Object, Object>();


    //实例提供者，代表真正的IoC容器
    private static InstanceProvider instanceProvider;

    private InstanceFactory() {
    }

    /**
     * 设置实例提供者。
     *
     * @param provider 一个实例提供者的实例。
     */
    public static void setInstanceProvider(InstanceProvider provider) {
        instanceProvider = provider;
    }

    /**
     * 根据类型获取对象实例。返回的对象实例所属的类是T或它的实现类或子类。如果找不到该类型的实例则抛出异常。
     *
     * @param <T>      对象的类型
     * @param beanType 对象所属的类型
     * @return 类型为T的对象实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> beanType) {
        T result = instanceProvider.getInstance(beanType);
        if (result != null) {
            return result;
        }
        throw new IocInstanceNotFoundException("There's not bean of type '" + beanType + "' exists in IoC container!");
    }

    /**
     * 检测是否有实例
     *
     * @param beanType
     * @return
     */
    public static <T> T getInstanceWithDefault(Class<T> beanType, T defaultBean) {
        T result = instanceProvider.getInstance(beanType);
        if (result != null) {
            return result;
        }
        return defaultBean;
    }

    /**
     * 根据类型和名称获取对象实例。返回的对象实例所属的类是T或它的实现类或子类。不同的IoC容器用不同的方式解释beanName。
     * 具体的解释方式请参见各种InstanceProvider实现类的Javadoc。 如果找不到该类型的实例则抛出异常。
     *
     * @param <T>      类型参数
     * @param beanName bean的名称
     * @param beanType 实例的类型
     * @return 指定类型的实例。
     */
    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> beanType, String beanName) {
        T result = instanceProvider.getInstance(beanType, beanName);
        if (result != null) {
            return result;
        }
        throw new IocInstanceNotFoundException("There's not bean of type '" + beanType + "' exists in IoC container!");
    }

    /**
     * 根据类型和Annotation获取对象实例。返回的对象实例所属的类是T或它的实现类或子类。不同的IoC容器用不同的方式解释annotation。
     * 具体的解释方式请参见各种InstanceProvider实现类的Javadoc。 如果找不到该类型的实例则抛出异常。
     *
     * @param <T>            类型参数
     * @param beanType       实例的类型
     * @param annotationType 实现类的annotation类型
     * @return 指定类型的实例。
     */
    public static <T> T getInstance(Class<T> beanType, Class<? extends Annotation> annotationType) {
        T result = instanceProvider.getInstance(beanType, annotationType);
        if (result != null) {
            return result;
        }
        throw new IocInstanceNotFoundException("There's not bean '"
                + annotationType + "' of type '" + beanType + "' exists in IoC container!");
    }

    /**
     * 将服务绑定到具体实例
     *
     * @param <T>                   Bean实例的类型
     * @param serviceInterface      注册类型
     * @param serviceImplementation 对象实例
     */
    public static <T> void bind(Class<T> serviceInterface, T serviceImplementation) {
        instances.put(serviceInterface, serviceImplementation);
    }

    /**
     * 将服务绑定到具体实例并指定名字
     *
     * @param <T>                   Bean实例的类型
     * @param serviceInterface      注册类型
     * @param serviceImplementation 对象实例
     * @param beanName              实例名称
     */
    public static <T> void bind(Class<T> serviceInterface, T serviceImplementation, String beanName) {
        instances.put(toName(serviceInterface, beanName), serviceImplementation);
    }

    /**
     * 删除缓存的bean实例
     */
    public static void clear() {
        instances.clear();
    }

    /**
     * 将服务绑定到具体实例并指定关联的Annotation
     *
     * @param <T>                   Bean实例的类型
     * @param serviceInterface      注册类型
     * @param serviceImplementation 对象实例
     * @param annotationType        标注类型
     */
    public static <T> void bind(Class<T> serviceInterface, T serviceImplementation, Class<? extends Annotation> annotationType) {
        instances.put(toName(serviceInterface, annotationType), serviceImplementation);
    }

    private static String toName(Class<?> beanType, String beanName) {
        return beanType.getName() + ":" + beanName;
    }

    private static String toName(Class<?> beanType, Class<? extends Annotation> annotationType) {
        return beanType.getName() + ":" + annotationType.getName();
    }
}
