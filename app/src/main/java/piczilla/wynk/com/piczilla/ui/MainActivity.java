package piczilla.wynk.com.piczilla.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import piczilla.wynk.com.piczilla.R;
import piczilla.wynk.com.piczilla.viewmodel.FetchDataViewModel;

public class MainActivity extends AppCompatActivity{

    private FetchDataViewModel viewModel;
    private ImageView imageView;
    private ProgressBar progressBar;
    private Button nextButton;
    private Button previousButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        initViewModel();
        initView();
    }

    public void initView() {
        imageView = findViewById(R.id.imageview);
        progressBar = findViewById(R.id.progress_circular);
        nextButton = findViewById(R.id.next);
        nextButton.setOnClickListener(v -> fetchNext());
        previousButton = findViewById(R.id.previous);
        previousButton.setEnabled(false);
        previousButton.setOnClickListener(v -> fetchPrevious());
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(MainActivity.this).get(FetchDataViewModel.class);
        viewModel.getBitmapLiveData().observe(MainActivity.this, uiStateModel -> {
             progressBar.setVisibility(View.GONE);
             previousButton.setEnabled(uiStateModel.isEnabledPreviuos());
             nextButton.setEnabled(uiStateModel.isEnabledNext());
             imageView.setImageBitmap(uiStateModel.getBitmap());
        });
    }

    public void fetchNext() {
        progressBar.setVisibility(View.VISIBLE);
        previousButton.setEnabled(false);
        nextButton.setEnabled(false);
        viewModel.fetchNext();
    }

    public void fetchPrevious() {
        progressBar.setVisibility(View.VISIBLE);
        previousButton.setEnabled(false);
        nextButton.setEnabled(false);
        viewModel.fetchPreviuos();
    }
}
