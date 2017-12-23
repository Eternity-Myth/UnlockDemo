package gwc.com.unlockdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;

/**
 * Created by Eternity-Myth on 2017/12/22.
 */

public class ScreenListener {
    private Context mContext;
    private ScreenBroadcastReceiver mScreenReceiver;//屏幕广播接收
    private ScreenStateListener mScreenStateListener;//屏幕状态监听

    public ScreenListener(Context context) {
        mContext = context;
        mScreenReceiver = new ScreenBroadcastReceiver();
    }

    public interface ScreenStateListener {
        public void unlock();
    }

    private class ScreenBroadcastReceiver extends BroadcastReceiver {
        private String action = null;//

        public void onReceive(Context context, Intent intent) {
            action = intent.getAction();
            if (Intent.ACTION_USER_PRESENT.equals(action)) {//屏幕解锁
                mScreenStateListener.unlock();
            }
        }
    }

    private void registerListener() {//注册监听，启动screen状态广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_USER_PRESENT);
        mContext.registerReceiver(mScreenReceiver, filter);
    }

    private void getScreenState() {//获取屏幕状态
        PowerManager powerManager = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
    }


    public void begin(ScreenStateListener listener) {//开始监听
        mScreenStateListener = listener;
        registerListener();//注册
        getScreenState();//获取屏幕状态
    }
}
