package it.moondroid.coverflowdemo.activities;

import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;
import it.moondroid.coverflowdemo.GameEntity;
import it.moondroid.coverflowdemo.R;
import it.moondroid.coverflowdemo.adapters.CoverflowAdapter;


public class CoverFlowActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FeatureCoverFlow mCoverFlow;
    private CoverflowAdapter.CoverFlowAdapter mAdapter;
    private ArrayList<GameEntity> mData = new ArrayList<>(0);
    private TextSwitcher mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logosgc);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CoverFlowActivity.this, ContactActivity.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mData.add(new GameEntity(R.drawable.image_1, R.string.title1));
        mData.add(new GameEntity(R.drawable.image_2, R.string.title2));
        mData.add(new GameEntity(R.drawable.image_3, R.string.title3));
        mData.add(new GameEntity(R.drawable.image_4, R.string.title4));

        mTitle = (TextSwitcher) findViewById(R.id.title);
        mTitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(CoverFlowActivity.this);
                return (TextView) inflater.inflate(R.layout.item_title, null);
            }
        });
        Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom);
        mTitle.setInAnimation(in);
        mTitle.setOutAnimation(out);

        mAdapter = new CoverflowAdapter.CoverFlowAdapter(this);
        mAdapter.setData(mData);
        mCoverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
        mCoverFlow.setAdapter(mAdapter);

        mCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CoverFlowActivity.this,
                        getResources().getString(mData.get(position).titleResId),
                        Toast.LENGTH_SHORT).show();
            }
        });

        mCoverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mTitle.setText(getResources().getString(mData.get(position).titleResId));
            }

            @Override
            public void onScrolling() {
                mTitle.setText("");
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_coverflow_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent i = new Intent(this, ConditionsActivity.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.fb) {
            String url = "https://www.facebook.com/SoGlam-Cr%C3%A9ation-865367490170994/?ref=hl";
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=" + url)));
            } catch (Exception e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        }

        if (id == R.id.insta) {
            Uri uri = Uri.parse("http://www.instagram.com/_u/soglamcreation/");
            Intent insta = new Intent(Intent.ACTION_VIEW, uri);

            try {
                this.getPackageManager().getPackageInfo("it.moondroid.coverflowdemo", 0);
                startActivity(insta);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/soglamcreation/")));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i = null;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        ActivityOptions options = ActivityOptions.makeScaleUpAnimation(this.mCoverFlow, 0, 0,
                this.mCoverFlow.getWidth(), this.mCoverFlow.getHeight());

        if (id == R.id.nav_gallery) {
            i = new Intent(this, GalleryActivity.class);
            i.putExtra("identifier", "image_");
        } else if (id == R.id.nav_slideshow) {
            i = new Intent(this, GalleryActivity.class);
            i.putExtra("identifier", "caftan_");
        } else if (id == R.id.collection_caftan_invitees) {
            i = new Intent(this, GalleryActivity.class);
            i.putExtra("identifier", "caftan_");
        } else if (id == R.id.collection_caftan_mariees) {
            i = new Intent(this, GalleryActivity.class);
            i.putExtra("identifier", "caftan_");
        } else if (id == R.id.collection_summer_video_2015) {
            i = new Intent(this, WebViewActivity.class);
            i.putExtra("identifier", "caftan_");
            i.putExtra("uri", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/vyxci-2a_nM\" frameborder=\"0\" allowfullscreen></iframe>");
        } else if (id == R.id.collection_2015_video) {
            i = new Intent(this, WebViewActivity.class);
            i.putExtra("uri", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/E46A0xUcFW4\" frameborder=\"0\" allowfullscreen></iframe>");
            i.putExtra("identifier", "caftan_");
        } else if (id == R.id.nav_send) {
            i = new Intent(this, ContactActivity.class);
        }

        if (Double.parseDouble(android.os.Build.VERSION.RELEASE
                .substring(0, 3)) >= 1.6) {
            startActivity(i, options.toBundle());
        } else {
            startActivity(i);
        }

        return true;
    }
}
