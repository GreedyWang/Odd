package com.cglib;


import com.google.common.collect.Maps;
import net.sf.cglib.beans.BeanGenerator;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by frinder on 2017/7/18.
 */
public class DynamicBean2 {

    /**
     * 目标对象
     */
    private Object target;

    /**
     * 缓存方法
     */
    private Map<String, Method> methodMap;

    public DynamicBean2() {
        super();
    }

    public DynamicBean2(Class superclass, Map<String, Class> propertyMap) {
        this.target = generateBean(superclass, propertyMap);
        methodMap = Maps.newConcurrentMap();
        Method[] methods = this.target.getClass().getMethods();
        for (Method m : methods) {
            methodMap.put(m.getName().toLowerCase(), m);
        }
    }


    /**
     * bean 添加属性和值
     *
     * @param property
     * @param value
     */
    public void setValue(String property, Object value) throws Exception {
        String name = String.format("%s%s", "set", property).toLowerCase();
        if (methodMap.containsKey(name)) {
            methodMap.get(name).invoke(this.target, value);
        }
    }

    /**
     * 获取属性值
     *
     * @param property
     * @return
     */
    public Object getValue(String property) throws Exception {
        String name = String.format("%s%s", "get", property).toLowerCase();
        if (methodMap.containsKey(name)) {
            return methodMap.get(name).invoke(this.target);
        }
        return null;
    }

    /**
     * 获取对象
     *
     * @return
     */
    public Object getTarget() {
        return this.target;
    }


    /**
     * 根据属性生成对象
     *
     * @param superclass
     * @param propertyMap
     * @return
     */
    private Object generateBean(Class superclass, Map<String, Class> propertyMap) {
        BeanGenerator generator = new BeanGenerator();
        if (null != superclass) {
            generator.setSuperclass(superclass);
        }
        BeanGenerator.addProperties(generator, propertyMap);
        return generator.create();
    }

}
