package com.xiaozhao.paotui.intf.demand.DO;

/****
 * 所有ajax接口的返回对象
 */
public class ResponseDO {

    private boolean success;
    private String msg;
    private Object data;

    public ResponseDO() {
    }

    public ResponseDO(boolean success, String msg, Object data){
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDO{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
