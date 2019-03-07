package com.assignment.lotusassignment.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.assignment.lotusassignment.R;
import com.assignment.lotusassignment.models.ResponseItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 *
 * @author Amol Deshmukh
 * @since 06/03/19
 */
public class DetailsActivity extends AppCompatActivity {
    @BindView(R.id.imgMainImageView)
    ImageView imgMainImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
        imgMainImageView.setImageBitmap(bitmap);



    }
}
