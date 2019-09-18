package kr.ac.koreatech.swkang.buttoneventinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    class MyListenerClass implements View.OnClickListener {
        public void onClick(View view) {
            //
            // 이벤트가 발생했을 때 실행할 코드를 작성한다.
            //

            // 이 예제에서는 토스트 메시지를 출력한다.
            Toast.makeText(getApplicationContext(), "Button pressed!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        MyListenerClass buttonListener = new MyListenerClass();
        button.setOnClickListener(buttonListener);
    }
}
