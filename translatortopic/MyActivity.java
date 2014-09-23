package everythingfactstogo.sergeybudkov.ru.translatortopic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class MyActivity extends Activity {

    EditText getWord;
    String theWord,div;
    static String theRusWord;
    static Bitmap[] imagePack = new Bitmap[10];




    public class Translator {
        private String word;

        Translator(String word) {
            this.word = word;
        }

        public String getWord() {
            return word;
        }

        public String translate() throws IOException {
            String apiKey = "trnsl.1.1.20140923T181120Z.3ee617c5029aec69.55e2497edcd49f0fefade1b343a3182832896067";
            String requestUrl = "https://translate.yandex.net/api/v1.5/tr.json/transla.."
                    + apiKey + "&text=" + URLEncoder.encode(word, "UTF-8") + "&lang=en-ru&format=plain";

            URL url = new URL(requestUrl);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.connect();
            int rc = httpConnection.getResponseCode();

            if (rc == 200) {
                String line;
                BufferedReader buffReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                StringBuilder strBuilder = new StringBuilder();
                while (true) {
                    line = buffReader.readLine();
                    if (line == null)
                        break;
                    strBuilder.append(line).append('\n');
                }
                return parseJSON(strBuilder.toString());
            }
            return null;
        }

        public String parseJSON(String line) {
            int begin = line.indexOf("\"text\":[\"");
            int end = line.indexOf("\"]");
            return line.substring(begin + 9, end);
        }
    }




//1111111/




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        getWord = (EditText) findViewById(R.id.editText);
    }


    public void Translate(View v) {
        if (getWord.getText().length() == 0) {
            Toast.makeText(this, "Insert the word, please ^_^",
                    Toast.LENGTH_LONG).show();
            return;
        }
        theWord = getWord.getText().toString();
        Translator trans = new Translator(theWord);
        try {
            theRusWord = trans.translate();
        } catch (IOException e) {
            Toast.makeText(this, "Sorry, Having some problems",
                    Toast.LENGTH_LONG).show();
            return;
        }

        imagePack = translateToPic(theWord);
        Intent intent = new Intent(MyActivity.this, DrawIt.class);
        startActivity(intent);
    }

    public String translateToRus(String tWord) {

        return "ЧЛЕН_СЛОНА";
    }

    public Bitmap[] translateToPic(String tWord) {
        Bitmap[] res = new Bitmap[10];
        res[0] = BitmapFactory.decodeResource(getResources(), R.drawable.q);
        res[1] = BitmapFactory.decodeResource(getResources(), R.drawable.w);
        res[2] = BitmapFactory.decodeResource(getResources(), R.drawable.e);
        res[3] = BitmapFactory.decodeResource(getResources(), R.drawable.r);
        res[4] = BitmapFactory.decodeResource(getResources(), R.drawable.t);
        res[5] = BitmapFactory.decodeResource(getResources(), R.drawable.y);
        res[6] = BitmapFactory.decodeResource(getResources(), R.drawable.u);
        res[7] = BitmapFactory.decodeResource(getResources(), R.drawable.i);
        res[8] = BitmapFactory.decodeResource(getResources(), R.drawable.o);
        res[9] = BitmapFactory.decodeResource(getResources(), R.drawable.p);

        return res;
    }


}
