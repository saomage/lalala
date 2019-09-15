package com.hfut.rpc.myrpc2;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hfut.rpc.myrpc2.Annotation.Controller;
import com.hfut.rpc.myrpc2.Annotation.Scanner;
import com.hfut.rpc.myrpc2.ControllerInstance.IControllerInstance;
import com.hfut.rpc.myrpc2.Exception.ScannerConflictException;
import com.hfut.rpc.myrpc2.Scanner.IControllerScanner;
import com.hfut.rpc.myrpc2.Util.ObjectUtil;

public class ControllerScanner {

    public static final Map<String,IControllerInstance> map=new HashMap<>();
    public static final Map<Class,Object> singleTonPool=new HashMap<>();
    public static final Map<String,IControllerScanner> scannerMap;

    static {
        scannerMap=new HashMap<>();
        List<String> classNames = getClassName("com.example.mydubbo.myrpc.Scanners");

        for (String className:classNames){
            try {
                Class<?> aClass = Class.forName(className);
                boolean isScanner = aClass.isAnnotationPresent(Scanner.class);
                if(isScanner){
                    Scanner annotation = aClass.getAnnotation(Scanner.class);
                    String type = annotation.type();
                    if (scannerMap.containsKey(type)){
                        throw new ScannerConflictException(className,type);
                    }
                    scannerMap.put(type,(IControllerScanner) aClass.newInstance());
                }
            }catch (Exception e){

            }
        }

    }

    public static Map<String,IControllerInstance> Scan(String path){

        List<String> classNames = getClassName(path);

        for(String className:classNames){
            Scan0(className);
        }
        return map;
    }

    private static boolean Scan0(String className){
        try {
            Class<?> aClass = Class.forName(className);
            boolean isController = aClass.isAnnotationPresent(Controller.class);
            if (isController){
                Controller controller = aClass.getAnnotation(Controller.class);
                String type = controller.type();
                IControllerScanner scanner = scannerMap.get(type);
                int i = ObjectUtil.checkNotNull(scanner, "scanner").scan(aClass, map);
                return i>0;
            }
        }catch (Exception e){

        }
        return false;
    }

    public static List<String> getClassName(String packageName) {
        String filePath = ClassLoader.getSystemResource("").getPath() + packageName.replace(".", "\\");
        List<String> fileNames = getClassName(filePath);
        return fileNames;
    }

    private static List<String> getClassName0(String filePath) {
        List<String> myClassName = new ArrayList<>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                myClassName.addAll(getClassName0(childFile.getPath()));
            } else {
                String childFilePath = childFile.getPath();
                childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));
                childFilePath = childFilePath.replace("\\", ".");
                myClassName.add(childFilePath);
            }
        }
        return myClassName;
    }
}
