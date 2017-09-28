package com.example.user.sense;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class bluetooth extends AppCompatActivity {

    TextToSpeech txtSpeech;
    RelativeLayout imgBtn;
    LinearLayout mainBtn;
    TextView text1;
    public ArrayList<BluetoothDevice> mBTDevices = new ArrayList<>();
    public bt_discover mbt_discover;
    ListView lvNewDevices;

    private static final String TAG = "bluetooth";

    BluetoothAdapter mBluetoothAdapter;

    // Create a BroadcastReceiver for ACTION_FOUND.
    // Turn BT on/off.
    private final BroadcastReceiver mBroadcastReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        Toast.makeText(bluetooth.this , "onReceive: STATE OFF", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Toast.makeText(bluetooth.this , "onReceive: STATE TURNING OFF", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Toast.makeText(bluetooth.this , "onReceive: STATE ON", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Toast.makeText(bluetooth.this , "onReceive: STATE TURNING ON", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    };

    // Create a BroadcastReceiver for ACTION_FOUND.
    // Discoverability mode on/off or expire.
    private final BroadcastReceiver mBroadcastReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE, BluetoothAdapter.ERROR);

                switch (state) {
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
                        Toast.makeText(bluetooth.this , "Discoverability Enabled", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
                        Toast.makeText(bluetooth.this , "Discoverability Disabled. Able to receive connection.", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.SCAN_MODE_NONE:
                        Toast.makeText(bluetooth.this , "Discoverability Disabled. Not able to receive connection.", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_CONNECTING:
                        Toast.makeText(bluetooth.this , "Connecting...", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_CONNECTED:
                        Toast.makeText(bluetooth.this , "Connected!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    };

    private BroadcastReceiver mBroadcastReceiver3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            Toast.makeText(bluetooth.this , "ACTION FOUND.", Toast.LENGTH_SHORT).show();

            if (action.equals(BluetoothDevice.ACTION_FOUND)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mBTDevices.add(device);
                Toast.makeText(bluetooth.this , "onReceive: " +device.getName()+ ": " +device.getAddress(), Toast.LENGTH_SHORT).show();
                mbt_discover = new bt_discover(context, R.layout.bt_discover, mBTDevices);
                lvNewDevices.setAdapter(mbt_discover);
            }
        }
    };

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: called");
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver1);
        unregisterReceiver(mBroadcastReceiver2);
        unregisterReceiver(mBroadcastReceiver3);
        //BluetoothAdapter.cancelDiscovery();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth);

        mainBtn=(LinearLayout)findViewById(R.id.footer);
        text1=(TextView)findViewById(R.id.txt1);
        imgBtn=(RelativeLayout)findViewById(R.id.body);
        lvNewDevices = (ListView) findViewById(R.id.lvNewDevices);
        mBTDevices = new ArrayList<>();

        //Broadcast when band state changes (ie.pairing)
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //Enable BT on the app Startup
        if (!mBluetoothAdapter.isEnabled()){
            btStart();
        }



        //Text-to-Speech Module
        txtSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int status){
                if(status != TextToSpeech.ERROR){
                    txtSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

//        Clickable Image Body
//        imgBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                String toSpeak = text1.getText().toString();
//                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
//               txtSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
//            }
//        });
    }


    public void onPause(){
        if(txtSpeech != null){
            txtSpeech.stop();
            txtSpeech.shutdown();
        }
        super.onPause();
    }

    //START BT WHEN ONCLICK
    public void btStart(){
        Toast.makeText(bluetooth.this , "onClick: enabling/disable BT", Toast.LENGTH_SHORT).show();

        if (!mBluetoothAdapter.isEnabled()){
            Toast.makeText(bluetooth.this , "enableDisableBT: enabling BT", Toast.LENGTH_SHORT).show();
            txtSpeech.speak("Bluetooth on", TextToSpeech.QUEUE_FLUSH, null);
            mBluetoothAdapter.enable();

            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(mBroadcastReceiver1, BTIntent);
        }

        IntentFilter intentFilter = new IntentFilter(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        registerReceiver(mBroadcastReceiver2, intentFilter);

        if (!mBluetoothAdapter.isDiscovering()){
            mBluetoothAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mBroadcastReceiver3, discoverDevicesIntent);
        }


    }

}