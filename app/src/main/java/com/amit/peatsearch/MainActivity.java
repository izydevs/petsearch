package com.amit.peatsearch;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.amit.peatsearch.Adapter.MovieAdapter;
import com.amit.peatsearch.Model.ApiResponse;
import com.amit.peatsearch.Utils.Utils;
import com.amit.peatsearch.ViewModel.MovieViewModel;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel viewModel;
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkInternetConnection();
    }

    private void checkInternetConnection() {
        if (Utils.checkInternetConnection(this)){
            initBindViews();
        }else {
            Toast.makeText(this,getResources().getString(R.string.something_wrong),Toast.LENGTH_LONG).show();
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
        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.getPopularMovie().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(@Nullable ApiResponse response) {
                updateUI(response);
                if (progressDialog.isShowing())
                    progressDialog.dismiss();

            }
        });
    }

    private void updateUI(ApiResponse response) {
        if(response.isStatus()){
            mAdapter = new MovieAdapter(response.getPopularMovies().getResults(),this);
            recyclerView.setAdapter(mAdapter);
        }else {
            Toast.makeText(this,getResources().getString(R.string.something_wrong),Toast.LENGTH_LONG).show();
        }

    }
}
