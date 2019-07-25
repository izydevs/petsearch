package com.amit.petsearch;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.amit.petsearch.Adapter.MovieAdapter;
import com.amit.petsearch.Model.ApiResponse;
import com.amit.petsearch.Utils.Utils;
import com.amit.petsearch.ViewModel.MovieViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getResources().getString(R.string.popular_movies));
        checkInternetConnection();
    }

    private void checkInternetConnection() {
        if (Utils.checkInternetConnection(this)){
            initBindViews();
        }else {
            Toast.makeText(this,getResources().getString(R.string.check_internet),Toast.LENGTH_LONG).show();
            finishAffinity();
        }
    }

    private void initBindViews() {
        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final ProgressDialog progressDialog = new ProgressDialog(this,R.style.MyAlertDialogStyle);
        progressDialog.setMessage(getApplicationContext().getResources().getString(R.string.fetch_data));
        progressDialog.setCancelable(false);
        progressDialog.show();
        MovieViewModel viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.getPopularMovie().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(@Nullable ApiResponse apiResponse) {
                assert apiResponse != null;
                updateUI(apiResponse);
                if (progressDialog.isShowing())
                    progressDialog.dismiss();

            }
        });
    }

    private void updateUI(ApiResponse response) {
        if(response.isStatus()){
            MovieAdapter mAdapter = new MovieAdapter(response.getPopularMovies().getResults(), this);
            recyclerView.setAdapter(mAdapter);
        }else {
            Toast.makeText(this,getResources().getString(R.string.something_wrong),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Toast.makeText(this,"clicked on search",Toast.LENGTH_LONG).show();
                return true;
            case R.id.plcament:
                Toast.makeText(this,"clicked on plcament",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
