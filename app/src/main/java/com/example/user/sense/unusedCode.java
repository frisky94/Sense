/** UNUSED CODE **/
/* Created by User on 9/25/2017. */

/*ENABLE BT*/
//    public void enableDisableBT(){
//        if (mBluetoothAdapter == null){
//            Toast.makeText(bluetooth.this , "enableDisableBT: Does not have BT capabilities", Toast.LENGTH_SHORT).show();
//        }
//        if (!mBluetoothAdapter.isEnabled()){
//            Toast.makeText(bluetooth.this , "enableDisableBT: enabling BT", Toast.LENGTH_SHORT).show();
//            txtSpeech.speak("Blue tooth on", TextToSpeech.QUEUE_FLUSH, null);
//            mBluetoothAdapter.enable();
//            mBluetoothAdapter.startDiscovery();
//
//            //REQUEST BLUETOOTH PERMISSION
//            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivity(enableBTIntent);
//
//            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
//            registerReceiver(mBroadcastReceiver1, BTIntent);
//        }
//        if (mBluetoothAdapter.isEnabled()){
//            Toast.makeText(bluetooth.this , "enableDisableBT: disabling BT", Toast.LENGTH_SHORT).show();
//            txtSpeech.speak("Blue tooth off", TextToSpeech.QUEUE_FLUSH, null);
//            mBluetoothAdapter.disable();
//            mBluetoothAdapter.cancelDiscovery();
//
//            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
//            registerReceiver(mBroadcastReceiver1, BTIntent);
//        }
//    }

/* INSIDE ONCLICK btStart */
//REQUEST DISCOVERABLE PERMISSION & MAKE DEVICE DISCOVERABLE FOR 300 SECOND
//        Toast.makeText(bluetooth.this , "Making Device discoverable for 300 seconds.", Toast.LENGTH_SHORT).show();
//        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
//        startActivity(discoverableIntent);

/* DISCOVERING BT DEVICE */
//    public void discover(){
//        Toast.makeText(bluetooth.this, "Looking for unpaired devices.", Toast.LENGTH_SHORT).show();
//
//
//        if (mBluetoothAdapter.isDiscovering()){
//            mBluetoothAdapter.cancelDiscovery();
//            Toast.makeText(bluetooth.this, "Canceling Discovery.", Toast.LENGTH_SHORT).show();
//
//            mBluetoothAdapter.startDiscovery();
//            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//            registerReceiver(mBroadcastReceiver3, discoverDevicesIntent);
//        }
//        if (!mBluetoothAdapter.isDiscovering()){
//            mBluetoothAdapter.startDiscovery();
//            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//            registerReceiver(mBroadcastReceiver3, discoverDevicesIntent);
//        }
//    }
