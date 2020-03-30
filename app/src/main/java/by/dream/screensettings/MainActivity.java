package by.dream.screensettings;

import android.graphics.Point;
import android.util.DisplayMetrics;
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

    public void OnDisplay(View view) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        String strScreen = "";
        strScreen += "Width: " + String.valueOf(metrics.widthPixels) + " pixels"
                + "\n";
        strScreen += "Height: " + String.valueOf(metrics.heightPixels) + " pixels"
                + "\n";
        strScreen += "The Logical Density: " + String.valueOf(metrics.density)
                + "\n";
        strScreen += "X Dimension: " + String.valueOf(metrics.xdpi) + " dot/inch"
                + "\n";
        strScreen += "Y Dimension: " + String.valueOf(metrics.ydpi) + " dot/inch"
                + "\n";
        strScreen += "The screen density expressed as dots-per-inch: "
                + metrics.densityDpi + "\n";
        strScreen += "A scaling factor for fonts displayed on the display: "
                + metrics.scaledDensity + "\n";

        TextView infoTextView = findViewById(R.id.textView);
        infoTextView.setText(strScreen);
    }
}
