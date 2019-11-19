package kr.ac.koreatech.swkang.batterybr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BatteryBroadcastReceiver mReceiver;
    TextView mBatteryLevelText;
    ProgressBar mBatteryLevelProgress;

    // BroadcastReceiver 클래스 정의
    // onReceive 콜백 메소드 구현
    private class BatteryBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // 수신한 Broadcast intent의 action이 ACTION_BATTERY_CHANGED인 경우 아래 코드 실행
            if(action == Intent.ACTION_BATTERY_CHANGED) {
                // 매개변수로 넘어온 Intent 객체를 이용해서 battery level 정보를 얻는다
                // getIntExtra() 메소드 이용, key는 BatteryManager.EXTRA_LEVEL, default 값은 임의로 정함
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);

                // 해당 level 값으로 TextView와 PrograssBar를 업데이트 한다
                mBatteryLevelText.setText("Battery level: " + level);
                mBatteryLevelProgress.setProgress(level);

                // battery level 값에 따라 메시지를 Toast 메시지로 만들어 표시한다
                if (level < 20) Toast.makeText(context, "배터리를 충전해야 합니다.", Toast.LENGTH_SHORT).show();
                else if (level <= 30) Toast.makeText(context, "배터리가 부족합니다.", Toast.LENGTH_SHORT).show();
                else if (level == 100) Toast.makeText(context, "배터리가 완충 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBatteryLevelText = findViewById(R.id.textview);
        mBatteryLevelProgress = findViewById(R.id.progressBar);

        // 위에서 정의한 BroadcastReceiver 클래스의 객체를 생성한다
        mReceiver = new BatteryBroadcastReceiver();
    }

    // onStart()에서 BroadcastReceiver를 등록한다
    @Override
    protected void onStart() {
        super.onStart();

        // Intent filter 객체 생성
        IntentFilter filter = new IntentFilter();

        // Intent action 설정 : Intent.ACTION_BATTERY_CHANGED
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);

        // BroadcastReceiver 등록한다
        registerReceiver(mReceiver, filter);
    }

    // onStop()에서 BroadcastReceiver를 해제한다
    @Override
    protected void onStop() {
        super.onStop();

        // 해제한다
        unregisterReceiver(mReceiver);
    }


}
