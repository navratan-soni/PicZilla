package piczilla.wynk.com.piczilla;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import piczilla.wynk.com.piczilla.viewmodel.FetchDataViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FetchDataViewModel viewModel;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        initView();
        initViewModel();
    }

    public void initView() {
        imageView = findViewById(R.id.imageview);
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(MainActivity.this).get(FetchDataViewModel.class);
        viewModel.getBitmapLiveData().observe(MainActivity.this, bitmap -> {
             imageView.setImageBitmap(bitmap);
        });
    }


    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.previous:
               viewModel.fetchPrevousImage();
               break;
           case R.id.next:
               viewModel.fetchNextImage();
               break;
       }
    }
}
