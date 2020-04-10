package sumeng.student.uap.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

@Data
public class Result {

    private Integer code;//状态码
    private Integer count;//数据条数
    private Object data;//返回数据
    private String msg;//返回消息

    public Result(Integer code, Integer count, Object data, String msg) {
        this.code = code;
        this.count = count;
        this.data = data;
        this.msg = msg;
    }

    public Result() {
    }

    /**
     * bool值得判断
     * @param b
     * @param msg
     * @return
     */
    public static Result isSuccess(Boolean b, String msg){
        if(b){
            return success(msg+"成功");
        }else {
            return error(msg+"失败");
        }
    }
    /**
     * bool值得判断
     * @param data
     * @param msg
     * @return
     */
    public static Result isSuccess(IPage data, String msg){
        if(data!=null){
            return success(data.getRecords(),msg+"成功");
        }else {
            return error(msg+"失败");
        }
    }
    /**
     * 数据得判断
     * @param data
     * @param msg
     * @return
     */
    public static Result isSuccess(Object data, String msg){
        if(data!=null){
            return success(data,msg+"成功");
        }else {
            return error(msg+"失败");
        }
    }
    /**
     * 失败返回 默认
     */

    public static Result error(){
        return new Result(400,0,null,"error");
    }
    /**
     * 失败返回 返回消息
     */
    public static Result error(String msg){
        return new Result(400,0,null,msg);
    }
    /**
     * 失败返回 返回消息+数据
     */
    public static Result error(String msg, Object data){
        return new Result(400,0,data,msg);
    }
    /**
     * 失败返回 数据
     */
    public static Result error(Object data){
        return new Result(400,0,data,"error");
    }
    /**
     * 成功返回
     */
    public static Result success(){
        return new Result(0,0,null,"success");
    }

    /**
     * 成功返回 数据+条数
     */
    public static Result success(Object data, Integer count){
        return new Result(0,count, data,"success");
    }
    /**
     * 成功返回 消息
     */
    public static Result success(String msg){
        return new Result(0,0, null,msg);
    }
    /**
     * 成功返回 数据+消息
     */
    public static Result success(Object data, String msg){
        return new Result(0,1, data,msg);
    }
    /**
     * 成功返回 数据+条数+消息
     */
    public static Result success(Object data, Integer count, String msg){
        return new Result(0,count, data,msg);
    }
    /**
     * 成功返回 数据
     */
    public static Result success(Object data){
        return new Result(0,1, data,"success");
    }

    /**
     * 自定义
     */
    public static Result custom(Integer code, Integer count, Object data, String msg){
        return new Result(code,count, data,msg);
    }
}