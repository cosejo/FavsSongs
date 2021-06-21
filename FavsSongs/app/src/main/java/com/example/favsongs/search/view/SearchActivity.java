package com.example.favsongs.search.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.favsongs.MainActivity;
import com.example.favsongs.R;
import com.example.favsongs.network.NetworkRequestController;
import com.example.favsongs.search.SearchContract;
import com.example.favsongs.search.SearchPresenter;
import com.example.favsongs.search.model.ItunesSong;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {

    public static final String SEARCH_PARAMETER = "searchValue";

    private SearchContract.Presenter mPresenter;
    private RecyclerView mSongsRecyclerView;
    private Spinner mSearchSpinner;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle == null) {
            openMainView();
        }

        String searchValue = bundle.getString(SearchActivity.SEARCH_PARAMETER);
        mPresenter = new SearchPresenter(this, new NetworkRequestController(),searchValue);

        mSongsRecyclerView = (RecyclerView) findViewById(R.id.songsList);
        mSongsRecyclerView.setHasFixedSize(true);
        mSongsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSongsRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mPresenter.retrieveSongs();
    }

    @Override
    public void displaySongs(List<ItunesSong> songsList) {
        SearchRecyclerViewAdapter adapter = new SearchRecyclerViewAdapter(songsList);
        mSongsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void searchMoreSongs() {

    }

    @Override
    public void errorSearching() {
        Toast.makeText(this, R.string.message_wrong_username, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorOnAddingToPlaylist() {

    }

    private void openMainView() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainIntent);
    }
}
