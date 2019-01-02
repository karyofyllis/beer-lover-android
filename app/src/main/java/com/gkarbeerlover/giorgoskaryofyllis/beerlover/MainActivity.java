package com.gkarbeerlover.giorgoskaryofyllis.beerlover;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gkarbeerlover.giorgoskaryofyllis.beerlover.adapters.BeerAdapter;
import com.gkarbeerlover.giorgoskaryofyllis.beerlover.models.Beer;
import com.gkarbeerlover.giorgoskaryofyllis.beerlover.networking.Endpoint;
import com.gkarbeerlover.giorgoskaryofyllis.beerlover.networking.Parser;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BeerAdapter.OnItemClickListener {

    private BeerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<Beer> beers;
    private SwipeRefreshLayout mySwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Init the UI elements
        mRecyclerView = findViewById(R.id.rv_beers);
        mySwipeRefreshLayout = findViewById(R.id.swiperefresh);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            String url = Endpoint.SEARCH_URL + query;
            setTitle("#" + query);
            loadBeers(url);
        }


        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        loadBeers(Endpoint.buildUrl());
                        setTitle(getString(R.string.app_name));
                    }
                }
        );

        beers = new ArrayList<>();
        mAdapter = new BeerAdapter(beers, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);


        loadBeers(Endpoint.buildUrl());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // Check if user triggered a refresh:
            case R.id.menu_refresh:
                // Signal SwipeRefreshLayout to start the progress indicator
                mySwipeRefreshLayout.setRefreshing(true);

                // Start the refresh background task.
                // This method calls setRefreshing(false) when it's finished.
                loadBeers(Endpoint.buildUrl());
                setTitle(getString(R.string.app_name));


                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));

        MenuItem menuItem = menu.findItem(R.id.menu_refresh);
        menuItem.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        return true;
    }

    private void loadBeers(String url) {

        RequestQueue queue = Volley.newRequestQueue(this);

        // prepare the Request
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        // display response
                        try {
                            beers = Parser.beersToJson(response);
                            mAdapter.updateData(beers);
                            mySwipeRefreshLayout.setRefreshing(false);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );



        // add it to the RequestQueue
        queue.add(getRequest);

    }

    @Override
    public void onItemClick(Beer item, ImageView imageView) {

        Intent intent = new Intent(this, BeerActivity.class);
        intent.putExtra("beer", item);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, ViewCompat.getTransitionName(imageView));
        startActivity(intent, compat.toBundle());

    }
}

