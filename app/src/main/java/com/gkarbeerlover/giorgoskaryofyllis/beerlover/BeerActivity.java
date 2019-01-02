package com.gkarbeerlover.giorgoskaryofyllis.beerlover;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gkarbeerlover.giorgoskaryofyllis.beerlover.R;
import com.gkarbeerlover.giorgoskaryofyllis.beerlover.adapters.IngredientAdapter;
import com.gkarbeerlover.giorgoskaryofyllis.beerlover.models.Beer;

public class BeerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ImageView imageView = findViewById(R.id.img_beer);
        TextView tvTagLine = findViewById(R.id.tv_tagline);
        TextView tvDescription = findViewById(R.id.tv_description);
        TextView tvAuthor = findViewById(R.id.tv_author);
        TextView tvDate = findViewById(R.id.tv_date);
        RecyclerView recyclerView = findViewById(R.id.rv_ingredients);


        Beer beer = getIntent().getExtras().getParcelable("beer");

        setTitle(beer.getName());

        Glide.with(imageView.getContext())
                .load(beer.getImgUrl())
                .into(imageView);
        tvDescription.setText(beer.getDescription());
        tvTagLine.setText(beer.getTagline());
        tvAuthor.setText(beer.getAuthor());
        tvDate.setText(beer.getFirstBrewed());


        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new IngredientAdapter(beer.getIngredients()));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
