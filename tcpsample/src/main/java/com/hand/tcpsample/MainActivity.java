package com.hand.tcpsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String data = "[{\"ver\":1,\"from\":{\"ctype\":228,\"uid\":\"CN1000g2\"},\"to\":{\"ctype\":227},\"ts\":946685022,\"idx\":1,\"mtype\":\"rsp\",\"data\":{\"device_type\":\"BOX\",\"act\":\"NORMAL_UDP_SCAN_BOX_RETURN\",\"act_params\":{\"sku\":\"K-25490T-X-0\",\"device_id\":\"CN1000g2\",\"sn\":\"PL00194\",\"ip\":\"192.168.101.1\",\"rssi\":0,\"box_hardware_ver\":\"0101\",\"box_firmware_ver\":\"1.1.111\"}}}]";
        String s = data.replace("[", "").replace("]", "");

        Log.e("-----", s);
        Gson gson = new Gson();

        receiveUpdBean receiveUpdBean = gson.fromJson(s, com.hand.tcpsample.receiveUpdBean.class);

        String ip = receiveUpdBean.data.actParams.ip;
        Toast.makeText(this, "ip:" + ip, Toast.LENGTH_SHORT).show();


    }
}
