package com.hfut.rpc.myrpc2.ControllerInstance.clazz.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hfut.rpc.myrpc2.Annotation.FieldParam;
import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.MethodPipeline;
import com.hfut.rpc.myrpc2.ControllerInstance.IControllerInstance;
import com.hfut.rpc.myrpc2.ControllerInstance.clazz.IClassControllerInstance;
import com.hfut.rpc.myrpc2.Util.ObjectUtil;

import io.netty.channel.ChannelHandlerContext;

public class ClassControllerInstance implements IClassControllerInstance{
    Class aClass;
    
    MethodPipeline pipeline;
    
    Field returnField;
    
    List<Field> paramFields;

	public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }
    
    public void setReturnField(Field field) {
    	ObjectUtil.checkNull(field);
    	returnField=field;
    }
    
    public void setParamFields(List<Field> fields) {
    	paramFields=fields;
    }

    @Override
    public void invokeController(String json,ChannelHandlerContext ctx,Object...objects) {
        try {
            Object object=aClass.newInstance();
            Map<String, String> paramMap = JSON.parseObject(ObjectUtil.checkNotNull(json,"json无效"), new TypeReference<Map<String, String>>() {});
            for (Field field : ObjectUtil.checkNotNull(paramFields, "paramFields not found")) {
            	Object newField = JSON.parseObject(ObjectUtil.checkNotNull(paramMap.get(field.getAnnotation(FieldParam.class).name()), "parameter not found")
                        , field.getType());
                ObjectUtil.checkAccessible(field,field.getName()).set(object,newField);
            }
            pipeline.invoke(ctx,object,(obj,context)->{
            	try {
            		if(context.channel().eventLoop().inEventLoop()) {
            			context.writeAndFlush(obj);
            		}else {
            			context.channel().eventLoop().execute(()->{
            				context.writeAndFlush(obj);
            			});
            		}
            	}catch(Exception e){
            		return false;
            	}
            	return true;
            });
        }catch (Exception e){

        }
    }

	@Override
	public void setMethodPipeline(MethodPipeline pipeline) {
		this.pipeline=pipeline;
	}
	
	
}
