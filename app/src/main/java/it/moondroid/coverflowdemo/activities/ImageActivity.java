package it.moondroid.coverflowdemo.activities;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import it.moondroid.coverflowdemo.R;


public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        String title = getIntent().getStringExtra("title");
        Integer image = getIntent().getIntExtra("image", 1);
        getSupportActionBar().setTitle(getString(getResources()
                .getIdentifier("title_" + title.charAt(title.length() - 1),
                        "string", getPackageName())));

        ImageView imageView = (ImageView) findViewById(R.id.image);
        final TextView titleTextView = (TextView) findViewById(R.id.title);
        imageView.setImageResource(image);
        Palette.generateAsync(((BitmapDrawable) imageView.getDrawable()).getBitmap(),
                new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        Palette.Swatch vibrant =
                                palette.getVibrantSwatch();
                        if (vibrant != null) {
                            getWindow().getDecorView().setBackgroundColor(vibrant.getRgb());
                            getWindow().setStatusBarColor(vibrant.getRgb());
                            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
                            titleTextView.setBackgroundColor(
                                    vibrant.getRgb());
                            titleTextView.setTextColor(vibrant.getTitleTextColor());
                        }
                    }
                });

        titleTextView.setText(Html.fromHtml(getResources()
                        .getString(getResources()
                                .getIdentifier("prix_" + title.charAt(title.length() - 1), "string", getPackageName()))
                ));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
