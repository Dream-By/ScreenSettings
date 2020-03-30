package by.dream.screensettings;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private static final int WRITE_PERMISSION_REQUEST = 5000;

    private void showPermissionsDialog() {
        if (Build.VERSION.SDK_INT >= 23) {

            int hasWriteSettingsPermission = checkSelfPermission(Manifest.permission.WRITE_SETTINGS);
            if (hasWriteSettingsPermission != PackageManager.PERMISSION_GRANTED) {
                //You can skip the next if block. I use it to explain to user why I wan his permission.
                if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    showMessageOKCancel("You need to allow write settings",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_SETTINGS}, WRITE_PERMISSION_REQUEST);
                                }
                            });
                    return;
                }
//The next line causes a dialog to popup, asking the user to allow or deny us write permission
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_SETTINGS}, WRITE_PERMISSION_REQUEST);
                return;
            } else {
                //Permissions have already been granted. Do whatever you want :)
            }
        }
    }

    //Now you only need this if you want to show the rationale behind
//requesting the permission.
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this).setMessage(message).setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null).show();
    }

    //This method is called immediately after the user makes his decision to either allow
    // or disallow us permision.
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case WRITE_PERMISSION_REQUEST:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //User pressed the allowed button
                    //Do what you want :)
                } else {
                    //User denied the permission
                    //Come up with how to hand the requested permission
                }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        //startActivity(intent);

        showPermissionsDialog();

        SeekBar backLightSeekBar = findViewById(R.id.seekBar);
        final TextView settingTextView = findViewById(R.id.textViewSetting);

        backLightSeekBar
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        float backLightValue = (float) progress/100;

                        if(backLightValue < 0.1)
                        {
                            backLightValue = (float)0.1;
                        }

                        settingTextView.setText(String.valueOf(backLightValue));

                        WindowManager.LayoutParams layoutParams = getWindow()
                                .getAttributes();
                        layoutParams.screenBrightness = backLightValue;
                        getWindow().setAttributes(layoutParams);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
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
