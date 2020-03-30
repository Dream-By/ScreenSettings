package by.dream.screensettings;

import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClick(View view) {
        Display display = getWindowManager().getDefaultDisplay();
        Point point =  new Point();
        display.getSize(point);
        int screenWidth = point.x;
        int screenHeight = point.y;

        String width = Integer.toString(screenWidth);
        String height = Integer.toString(screenHeight);

        String info = "Ширина: " + width + " Высота: " + height;

        TextView infotextview = (TextView) findViewById(R.id.textViewInfo);
        infotextview.setText(info);
    }
}
