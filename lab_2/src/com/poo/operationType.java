package com.poo;

public class operationType {

    String operationName;
    String operationType;

    public operationType(String operationName, String operationType){
        this.operationName = operationName;
        this.operationType = operationType;
    }

    public Object getOperationName(){
        return this.operationName;
    }

    public Object getOperationType(){
        return this.operationType;
    }

    public void setOperationName(String operationName){
        this.operationName = operationName;
    }

    public void setOperationType(String operationType){
        this.operationType = operationType;
    }
}
