package com.jkdx.homework.util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ObjectUtil {
	

	
	
	public static boolean haveField(Object class_,String fname,boolean dval) {
		try {
			class_.getClass().getDeclaredField(fname);
//			class_.getClass().getField(fname);
			return dval;
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			return !dval;
		}
		
	}
	
	
	public static boolean isNullOrEmpty(Object[] strval,boolean dval) {
		
		if(null==strval)return dval;
		for(Object obj : strval) {
			boolean bool =  isNullOrEmpty(obj, dval);
			if(String.valueOf(bool).equals(String.valueOf(dval))) {
				return dval;
			}
		}
		return !dval;
	}
	
	public static boolean isNullOrEmpty(Object strval,boolean dval) {
		if(null==strval) {
			return dval;
		}
		else if(StringUtil.isNullOrEmpty(strval.toString(), true)) {
			return dval;
		}
		else
			return !dval;
	}
	
	/***
     * 将一个对象的属性值赋给另一个对象的相同的属性 这两个对象必须都符合javaBean的标准
     *
     * @param source
     *            要赋值的源对象
     * @param target
     *            被赋值的目标对象
     * @param ignoreProperties
     *            被忽略赋值的属性数组
     * @throws Exception
     *
     */
    @SuppressWarnings("unused")
    public static void copyProperties(Object source, Object target, String ignoreProperties[])throws Exception {
           
        Class targetClass = target.getClass();
        Class sourceClass = source.getClass();
 
        // 得到目标对象和源对象的属性数组
        PropertyDescriptor targetPds[] = Introspector.getBeanInfo(targetClass).getPropertyDescriptors();
                
        PropertyDescriptor sourcetPds[] = Introspector.getBeanInfo(sourceClass).getPropertyDescriptors();
                
        // 将忽略字段的数组转化为list
        List<String> ignoreList = ignoreProperties ==null?null: Arrays.asList(ignoreProperties);
               
        // 把源对象的所有属性放的map中
        Map<String, PropertyDescriptor> sourcePropertyMap =new HashMap<String, PropertyDescriptor>();
        for(int i =0; i < sourcetPds.length; i++) {
            PropertyDescriptor pd = sourcetPds[i];
            sourcePropertyMap.put(pd.getName(), pd);
        }
        for(int i =0; i < targetPds.length; i++) {
            PropertyDescriptor targetPd = targetPds[i];
 
            if(targetPd.getWriteMethod() ==null|| ignoreProperties !=null&& ignoreList.contains(targetPd.getName()))
                continue;
            PropertyDescriptor sourcePd = sourcePropertyMap.get(targetPd.getName());
            if(sourcePd ==null|| sourcePd.getReadMethod() ==null)
                continue;
            try{
                // 得到源对象对应的属性值
                Method readMethod = sourcePd.getReadMethod();
                if(!Modifier.isPublic(readMethod.getDeclaringClass() .getModifiers()))
                    readMethod.setAccessible(true);
                
                Object value = readMethod.invoke(source,new Object[0]);
                // 将源对象的属性值赋值给目标对象对应的属性
                Method writeMethod = targetPd.getWriteMethod();
                if(!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers()))
                	writeMethod.setAccessible(true);
                
                writeMethod.invoke(target,new Object[] { value });
            }catch(Throwable ex) {
                throw new IllegalArgumentException(
                        "Could not copy properties from source to target", ex);
            }
        }
 
    }


	
}
