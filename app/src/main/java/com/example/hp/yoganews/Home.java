package com.example.hp.yoganews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{
    private static final int INITIAL_LOADER_ID = 1;

    /** URL used to fetch latest yoga news */
    private static final String INITIAL_REQUEST_URL = "https://content.guardianapis.com/search?q=yoga&format=json&show-references&show-fields=headline,thumbnail,&order-by=newest&api-key=test";

    /** Holds the boolean value for if the device is connected to a network or not*/
    boolean isConnected;

    ListView newsList;
    NewsListCustomAdapter newsListCustomAdapter;
    LinearLayout noConnectionView;
    ProgressBar loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // References to UI elements.
        newsList = (ListView)findViewById(R.id.news_list);
        loadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);
        noConnectionView = (LinearLayout) findViewById(R.id.noConnectionView);


        // Setup the list view with an empty adapter until the data is fetched
        newsListCustomAdapter = new NewsListCustomAdapter(new ArrayList<News>(), getApplicationContext());
        newsList.setAdapter(newsListCustomAdapter);

        // Open the web page of the book when it's list item is clicked
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = newsListCustomAdapter.getItem(i).getUrl();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader.
        loaderManager.initLoader(INITIAL_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new NewsLoader(this, INITIAL_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> News) {
        // Stop the loading indicator once the loading is finished so we can show either the list or the empty state
        loadingIndicator.setVisibility(View.GONE);
        newsList.setEmptyView(noConnectionView);

        // Clear the adapter of previous book data and load new data
        newsListCustomAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (News != null && !News.isEmpty()) {
            newsListCustomAdapter.addAll(News);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        // Loader reset, so we can clear out our existing data.
        newsListCustomAdapter.clear();
    }

}
