package gwc.com.unlockdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ScreenListener screenListener;//创建ScreenListener类对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenListener = new ScreenListener(MainActivity.this);
        screenListener.begin(new ScreenListener.ScreenStateListener() {//开始监听屏幕状态
            public void unlock() {//重写unlock方法
                Toast.makeText(MainActivity.this, "Unlocked", Toast.LENGTH_SHORT).show();//解锁弹出Toast
            }
        });
    }
}
