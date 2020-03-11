package com.hand.tcpsample;

import com.google.gson.annotations.SerializedName;

/**
 * @author diaokaibin@gmail.com on 2019/4/15.
 */
public class receiveUpdBean {
    @SerializedName("ver")
    public int ver;
    @SerializedName("from")
    public FromBean from;
    @SerializedName("to")
    public ToBean to;
    @SerializedName("ts")
    public int ts;
    @SerializedName("idx")
    public int idx;
    @SerializedName("mtype")
    public String mtype;
    @SerializedName("data")
    public DataBean data;

    @Override
    public String toString() {
        return "receiveUpdBean{" +
                "ver=" + ver +
                ", from=" + from +
                ", to=" + to +
                ", ts=" + ts +
                ", idx=" + idx +
                ", mtype='" + mtype + '\'' +
                ", data=" + data +
                '}';
    }

    public static class FromBean {
        @SerializedName("ctype")
        public int ctype;
        @SerializedName("uid")
        public String uid;
    }

    public static class ToBean {
        @SerializedName("ctype")
        public int ctype;
        @SerializedName("uid")
        public String uid;
    }

    public static class DataBean {
        @SerializedName("device_type")
        public String deviceType;
        @SerializedName("act")
        public String act;
        @SerializedName("act_params")
        public ActParamsBean actParams;

        public static class ActParamsBean {
            @SerializedName("sku")
            public String sku;
            @SerializedName("device_id")
            public String deviceId;
            @SerializedName("sn")
            public String sn;
            @SerializedName("ip")
            public String ip;
            @SerializedName("rssi")
            public String rssi;
            @SerializedName("box_hardware_ver")
            public String boxHardwareVer;
            @SerializedName("box_firmware_ver")
            public String boxFirmwareVer;
        }
    }
}
