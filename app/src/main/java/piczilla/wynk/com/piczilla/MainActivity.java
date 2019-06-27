package piczilla.wynk.com.piczilla;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button previous, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        initView();
    }

    private void initView() {
        previous = (Button) findViewById(R.id.previous);
        //onclick of previous button should navigate the user to previous image
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        next = (Button) findViewById(R.id.next);
        //onclick of next button should navigate the user to next image
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }




}
