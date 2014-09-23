package everythingfactstogo.sergeybudkov.ru.translatortopic;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class DrawIt extends Activity{


    ImageView drawImage;
    TextView trans;
    TextView swipe;
    int nowPic = 0;
    String translation;
    int prevX,prevY,nextX,nextY;
    int dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_layout);
        drawImage = (ImageView) findViewById(R.id.imageView);
        trans = (TextView) findViewById(R.id.textView);

        nowPic = 0;

        translation = "Translation: " + MyActivity.theRusWord;
        trans.setText(translation);
        setImage(nowPic);
    }


    public void Right(View v){
        nowPic++;
        setImage(nowPic);
    }

    public void Left(View v){
        nowPic--;
        setImage(nowPic);
    }

    public void setImage(int now){
        if(now > 9){
            nowPic = 9;
            Toast.makeText(this, "Thats all, press Previos",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if(now < 0){
            nowPic = 0;
            Toast.makeText(this, "Thats all, press Next",
                    Toast.LENGTH_LONG).show();
            return;
        }
        drawImage.setImageBitmap(MyActivity.imagePack[now]);
    }

}