package com.hfut.rpc.myrpc2.Exception;

public class ScannerConflictException extends Exception{

    public ScannerConflictException(String type,String name){
        super(name+"注入失败,"+type+"类型的扫描器已存在");
    }
}
