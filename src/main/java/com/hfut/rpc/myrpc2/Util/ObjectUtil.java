package com.hfut.rpc.myrpc2.Util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

import com.hfut.rpc.myrpc2.Exception.AnnotationNotFoundException;
import com.hfut.rpc.myrpc2.Exception.FieldNotAccessibleException;
import com.hfut.rpc.myrpc2.Exception.ObjectNotNullException;

public class ObjectUtil {

    public static <T> T checkNotNull(T arg, String text) {
        if (arg == null) {
            throw new NullPointerException(text);
        }
        return arg;
    }

    public static Field checkAccessible(Field arg, String text){
        if (!arg.isAccessible()){
            throw  new FieldNotAccessibleException(text);
        }
        return arg;
    }

    public static <T extends Annotation> T checkAnnotation(Class<T> clazz, AnnotatedElement annotatedElement){
        if(!annotatedElement.isAnnotationPresent(clazz)){
            throw new AnnotationNotFoundException();
        }
        return annotatedElement.getAnnotation(clazz);
    }
    
    public static <T> T checkNull(T t) {
    	if(t!=null) {
    		throw new ObjectNotNullException(t.toString());
    	}
    	return t;
    }
}
