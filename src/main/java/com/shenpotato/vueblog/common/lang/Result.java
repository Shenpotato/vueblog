package com.shenpotato.vueblog.common.lang;

import lombok.Data;
import java.io.Serializable;

@Data
public class Result implements Serializable {
    private int code;   // normal: 200, innormal: not 200
    private String msg;
    private Object data;

    public static Result success(int code, String msg, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result success(Object data){
        Result result = new Result();
        return success(200, "operate success", data);
    }

    public static Result fail(int code, String msg, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result fail(String msg, Object data){
        return fail(400, msg, data);
    }

    public static Result fail(String msg){
        return fail(400, msg, null);
    }
}

