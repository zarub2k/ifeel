package com.cloudskol.ifeel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        renderUi();
    }

    private void renderUi() {
        ListView featuresListView = (ListView) findViewById(R.id.features_list);
        featuresListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switchActivity(position);
            }
        });
    }

    private void switchActivity(int index) {
        switch (index) {
            case 0:
                switchFeelActivity();
                break;

            case 1:
                break;

            case 2:
                break;
        }
    }

    private void switchFeelActivity() {
        final Intent intent = new Intent(this, FeelActivity.class);
        startActivity(intent);
    }
}
