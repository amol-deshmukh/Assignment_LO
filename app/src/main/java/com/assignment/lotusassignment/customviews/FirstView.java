package com.assignment.lotusassignment.customviews;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.assignment.lotusassignment.R;
import com.assignment.lotusassignment.models.ResponseItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 *
 * @author Amol Deshmukh
 * @since 06/03/19
 */
public class FirstView extends RecyclerViewAdapter.DataViewHolder implements View.OnClickListener {
    @BindView(R.id.imgView)
    ImageView imageView;
    @BindView(R.id.imgRemove)
    ImageView imgRemove;
    @BindView(R.id.txtxTitle)
    TextView txtxTitle;
    ResponseItem responseItem;

    OnCameraClickListener onCameraClickListener;

    public FirstView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);

    }

    public void bind(ResponseItem responseItem) {
        this.responseItem = responseItem;
        txtxTitle.setText(responseItem.getTitle());

        imageView.setOnClickListener(this);
        imgRemove.setOnClickListener(this);

        if (responseItem.getBitmap() != null) {
            imageView.setImageBitmap(responseItem.getBitmap());
            imgRemove.setVisibility(View.VISIBLE);
        } else {
            imgRemove.setVisibility(View.GONE);
            // imageView.setImageDrawable(null);

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgView:
                if (onCameraClickListener != null) {
                    if (responseItem.getBitmap() != null) {
                        onCameraClickListener.goToDetailsActivity(getLayoutPosition());

                    } else {
                        onCameraClickListener.onCameraClicked(getLayoutPosition());
                    }
                }
                break;
            case R.id.imgRemove:
                if (onCameraClickListener != null) {
                    onCameraClickListener.onImageRemove(getLayoutPosition());
                }
                break;

        }
    }


    public interface OnCameraClickListener {
        void onCameraClicked(int position);

        void onImageRemove(int position);

        void goToDetailsActivity(int position);
    }

    public void setOnCameraClickListener(OnCameraClickListener onCameraClickListener) {
        this.onCameraClickListener = onCameraClickListener;
    }
}
