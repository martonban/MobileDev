package martonban.com.het02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        CheckBox terms = findViewById(R.id.termsBox);
        Button send = findViewById(R.id.sendButton);
        terms.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                send.setEnabled(true);
            }else{
                send.setEnabled(false);
            }
        });

        send.setOnClickListener(view -> {
            ViewGroup layout = findViewById(R.id.adatok);
            if(mindenKiVanToltve(layout)){
                System.out.println("Minden oké");
            }
        });

    }

    private boolean mindenKiVanToltve(ViewGroup layout) {
        boolean result = true;
        int count = layout.getChildCount();
        for(int i = 0; i<count; i++){
            View child = layout.getChildAt(i);
            if(child instanceof ViewGroup){
                if(!mindenKiVanToltve((ViewGroup)child)){
                    result = false;
                }
            }
            else if(child instanceof EditText){
                EditText editText = (EditText) child;
                if(editText.getText().toString().trim().isEmpty()){
                    result = false;
                    editText.setError("Kötelező");
                }

            }
        }
        return false;
    }
}