package voice.recognition.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.appcompat.app.AppCompatActivity;

import com.google.cloud.dialogflow.v2.QueryResult;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String TAG ="MainActivity";
    String projectId="plantesbot-pcnpcj";
    List<String> texts= new ArrayList<>();
    String sessionId="123";
    String languageCode="en-US";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent = new Intent(this, voiceRecognitionTest.class);
//
//        startActivity(intent);

        findViewById(R.id.test_dialogFlow).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"get dialog response .....");
                texts.clear();
                texts.add("what is the color of earth");
                DetectIntentTexts detectIntentTexts = new DetectIntentTexts();
                try {
                    Map<String, QueryResult> resultMap=  detectIntentTexts.detectIntentTexts(projectId,texts,sessionId,languageCode);
                    for(Map.Entry<String, QueryResult> entry : resultMap.entrySet()){

                        Log.i(TAG,entry.getKey()+" : "+entry.getValue());

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, e.getMessage());
                }

            }
        });
    }
}
