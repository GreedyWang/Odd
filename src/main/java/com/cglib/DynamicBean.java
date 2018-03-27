package com.cglib;


import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import java.util.Map;

/**
 * Created by frinder on 2017/7/18.
 */
public class DynamicBean {

    /**
     * 目标对象
     */
    private Object target;

    /**
     * 属性集合
     */
    private BeanMap beanMap;

    public DynamicBean() {
        super();
    }

    public DynamicBean(Class superclass, Map<String, Class> propertyMap) {
        this.target = generateBean(superclass, propertyMap);
        this.beanMap = BeanMap.create(this.target);
    }


    /**
     * bean 添加属性和值
     *
     * @param property
     * @param value
     */
    public void setValue(String property, Object value) {
        beanMap.put(property, value);
    }

    /**
     * 获取属性值
     *
     * @param property
     * @return
     */
    public Object getValue(String property) {
        return beanMap.get(property);
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
    public static Object generateBean(Class superclass, Map<String, Class> propertyMap) {
        BeanGenerator generator = new BeanGenerator();
        if (null != superclass) {
            generator.setSuperclass(superclass);
        }
        BeanGenerator.addProperties(generator, propertyMap);
        return generator.create();
    }

}
