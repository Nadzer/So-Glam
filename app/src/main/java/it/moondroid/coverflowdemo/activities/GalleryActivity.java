package it.moondroid.coverflowdemo.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import it.moondroid.coverflowdemo.R;
import it.moondroid.coverflowdemo.adapters.GridViewAdapter;
import it.moondroid.coverflowdemo.models.ImageEntity;

public class GalleryActivity extends AppCompatActivity {
    private GridView gridView;
    private GridViewAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout_gallery, getImageResource(getIntent().getStringExtra("identifier")));
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageEntity item = (ImageEntity) parent.getItemAtPosition(position);

                Intent intent = new Intent(GalleryActivity.this, ImageActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("image", item.getImage());

                ActivityOptions options = ActivityOptions.makeScaleUpAnimation(v, 0, 0,
                        v.getWidth(), v.getHeight());
                if (Double.parseDouble(android.os.Build.VERSION.RELEASE
                        .substring(0, 3)) >= 1.6) {
                    Log.d("__start_activity", "1");
                    startActivity(intent, options.toBundle());
                } else {
                    Log.d("__start_activity", "2");
                    startActivity(intent);
                }
            }
        });
    }

    private ArrayList<ImageEntity> getImageResource(String identifier) {
        ArrayList<ImageEntity> imageItems = new ArrayList<>();
        int count = 1;
        int id = 1;

        while (!(id == 0)) {
            id = getResources().getIdentifier(identifier + count, "drawable", getPackageName());
            if (id != 0) imageItems.add(new ImageEntity(id, "image " + count));
            count++;
        }

        return imageItems;
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
    }
}
