package voice.recognition.test;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

//These class use speechRecognizer
public class voiceRecognitionTest extends Activity implements OnClickListener
{


    private TextView mText;
    private SpeechRecognizer sr;
    private static final String TAG = "voiceRecognitionTest";
    public int REQUEST_CODE=22;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_recog);
        Button speakButton = (Button) findViewById(R.id.btn_speak);
        mText = (TextView) findViewById(R.id.textView1);
        speakButton.setOnClickListener(this);


    }

    public void askPermisson(){
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.RECORD_AUDIO) ==
                PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG,"your device support voice recognition!" );
            startListen();
        }  else {
            // You can directly ask for the permission.
            Log.i(TAG,"ask permission!" );
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_CODE);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode== requestCode&&grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.i(TAG,"Permisson granted your device support voice recognition!" );
            startListen();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    class listener implements RecognitionListener
    {
        public void onReadyForSpeech(Bundle params)
        {
            Log.d(TAG, "onReadyForSpeech");
        }
        public void onBeginningOfSpeech()
        {
            Log.d(TAG, "onBeginningOfSpeech");
        }
        public void onRmsChanged(float rmsdB)
        {
            Log.d(TAG, "onRmsChanged");
        }
        public void onBufferReceived(byte[] buffer)
        {
            Log.d(TAG, "onBufferReceived");
        }
        public void onEndOfSpeech()
        {
            Log.d(TAG, "onEndofSpeech");
        }
        public void onError(int error)
        {
            Log.d(TAG,  "error " +  error);
            mText.setText("error " + error);
        }
        public void onResults(Bundle results)
        {
            String str = new String();
            Log.d(TAG, "onResults " + results);
            ArrayList data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            for (int i = 0; i < data.size(); i++)
            {
                Log.d(TAG, "result " + data.get(i));
                str += data.get(i);
            }
            mText.setText("results: "+String.valueOf(data.size()));
        }
        public void onPartialResults(Bundle partialResults)
        {
            Log.d(TAG, "onPartialResults");
        }
        public void onEvent(int eventType, Bundle params)
        {
            Log.d(TAG, "onEvent " + eventType);
        }
    }
    public void onClick(View v) {
        if (v.getId() == R.id.btn_speak)
        {
            if (!SpeechRecognizer.isRecognitionAvailable(this)) {
                Log.i(TAG,"No voice recognition support on your device!" );
            }else {
                askPermisson();

            }

        }
    }

    public void startListen(){
        sr = SpeechRecognizer.createSpeechRecognizer(this);
        sr.setRecognitionListener(new listener());
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"voice.recognition.test");

        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
        sr.startListening(intent);
        Log.i("111111","11111111");
    }
}