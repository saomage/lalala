package com.hfut.rpc.myrpc2.Scanner.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hfut.rpc.myrpc2.Annotation.FieldParam;
import com.hfut.rpc.myrpc2.Annotation.MethodChain;
import com.hfut.rpc.myrpc2.Annotation.RequestMapping;
import com.hfut.rpc.myrpc2.Annotation.ReturnParam;
import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.MethodPipeline;
import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.MethodPipelineFactory;
import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.impl.MethodPipelineFactoryImpl;
import com.hfut.rpc.myrpc2.ControllerInstance.clazz.impl.ClassControllerInstance;
import com.hfut.rpc.myrpc2.Scanner.IControllerScanner;
import com.hfut.rpc.myrpc2.Util.ObjectUtil;

public class ClassControllerScanner implements IControllerScanner{
	
	MethodPipelineFactory factory=new MethodPipelineFactoryImpl();
	
    @Override
    public int scan(Class clazz, Map map) {
        try{
            ClassControllerInstance instance = new ClassControllerInstance();
            instance.setaClass(clazz);
            RequestMapping requestMapping = ObjectUtil.checkAnnotation(RequestMapping.class, clazz);
            Field[] fields = clazz.getDeclaredFields();
            List<Field> paramFields=new ArrayList<>();
            for (Field field : fields) {
                if (field.isAnnotationPresent(ReturnParam.class)){
                    instance.setReturnField(field);
                }
                if(field.isAnnotationPresent(FieldParam.class)) {
                	paramFields.add(field);
                }
            }
            Method[] methods = clazz.getMethods();
            MethodPipeline methodPipeline = factory.create();
            instance.setMethodPipeline(methodPipeline);
            for (Method method : methods) {
                if (method.isAnnotationPresent(MethodChain.class)){
                    methodPipeline.addMehtod(method);
                }
            }
            instance.setParamFields(paramFields);
            map.put(requestMapping.value(),instance);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
}
