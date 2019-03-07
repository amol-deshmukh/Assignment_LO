package com.assignment.lotusassignment.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.lotusassignment.customviews.FirstView;
import com.assignment.lotusassignment.R;
import com.assignment.lotusassignment.customviews.RecyclerViewAdapter;
import com.assignment.lotusassignment.customviews.SecondView;
import com.assignment.lotusassignment.customviews.ThirdView;
import com.assignment.lotusassignment.models.ResponseItem;
import com.assignment.lotusassignment.utils.ResourceUtils;
import com.assignment.lotusassignment.utils.ResponseList;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements FirstView.OnCameraClickListener, SecondView.OnSecondActionDone,ThirdView.OnThirdItemActionDone {
    private static final int REQUEST_TAKE_PHOTO = 100;
    @BindView(R.id.txt)
    TextView txt;
    RecyclerViewAdapter recyclerViewAdapter;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    ResponseList responseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        responseList = ResourceUtils.deserialize(ResourceUtils.readFromAssets("response.json"), ResponseList.class, "");
        if (responseList != null) {
            recyclerViewAdapter = new RecyclerViewAdapter(responseList.getResponseItemList(), this);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(recyclerViewAdapter);

            recyclerViewAdapter.setOnCameraClickListener(this);
            recyclerViewAdapter.setOnSecondActionDone(this);
            recyclerViewAdapter.setOnThirdItemActionDone(this);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                    mLayoutManager.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);

            recyclerViewAdapter.notifyItemRangeChanged(0, responseList.getResponseItemList().size() - 1);

        }
    }

    int selectedPosition = 0;

    @Override
    public void onCameraClicked(int position) {
        selectedPosition = position;
        dispatchTakePictureIntent();

    }

    @Override
    public void onImageRemove(int position) {
        responseList.getResponseItemList().get(position).setBitmap(null);
        recyclerViewAdapter.notifyItemChanged(position);
    }

    @Override
    public void goToDetailsActivity(int position) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("BitmapImage", responseList.getResponseItemList().get(position).getBitmap());
        startActivity(intent);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    String currentPhotoPath;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            responseList.getResponseItemList().get(selectedPosition).setBitmap(imageBitmap);
            recyclerViewAdapter.notifyItemChanged(selectedPosition);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.submit) {
            diplayItemsAsToast();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void diplayItemsAsToast() {

        StringBuilder stringBuilder= new StringBuilder();
        if (responseList.getResponseItemList()!=null){
            for (ResponseItem responseItem : responseList.getResponseItemList()) {
                if (responseItem.getType()!=null){
                    switch (responseItem.getType()){
                        case "COMMENT":
                            stringBuilder.append("ID= "+responseItem.getId()+" "+
                                    responseItem.getComment()+" \n");
                            break;
                        case "SINGLE_CHOICE":
                            stringBuilder.append("ID= "+responseItem.getId()+"; Option Selected "+responseItem.getOptionSelected()+" \n");
                            break;
                    }
                }
            }


        }
        Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
        Log.d("LOG",stringBuilder.toString());


    }


    @Override
    public void onTextChanged(String text, int position) {
        responseList.getResponseItemList().get(position).setComment(text);

    }

    @Override
    public void onCheckChanged(boolean ischecked, int position) {

    }

    @Override
    public void onRadioButtonClicked(int checkedId, int position) {
        try {
            responseList.getResponseItemList().get(position).setOptionSelected(responseList.getResponseItemList().get(position).getDataMap().getOptions().get(checkedId));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
