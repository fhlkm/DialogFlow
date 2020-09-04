package voice.recognition.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class StartVoiceRecognition extends Activity {
    private final int REQUEST_SPEECH_RECOGNIZER = 3000;
    private TextView mTextView;
    private final String mQuestion = "Which company is the largest online retailer on the planet?";
    private String mAnswer = "";
    String TAG ="StartVoiceRecognition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_recog2);
        mTextView = (TextView)findViewById(R.id.speechmsg);
        startSpeechRecognizer();
        findViewById(R.id.start_speak).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpeechRecognizer();
            }
        });
    }

    private void startSpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, mQuestion);
        startActivityForResult(intent, REQUEST_SPEECH_RECOGNIZER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SPEECH_RECOGNIZER) {
            if (resultCode == RESULT_OK) {
                List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                mAnswer = results.get(0);

                for(String answer: results){
                    Log.i(TAG, "answer: "+answer);
                }
                if (mAnswer.toUpperCase().indexOf("AMAZON") > -1)
                    mTextView.setText("\n\nQuestion: " + mQuestion +
                            "\n\nYour answer is '" + mAnswer +
                            "' and it is correct!");
                else
                    mTextView.setText("\n\nQuestion: " + mQuestion +
                            "\n\nYour answer is '" + mAnswer +
                            "' and it is incorrect!");
            }
        }
    }
}