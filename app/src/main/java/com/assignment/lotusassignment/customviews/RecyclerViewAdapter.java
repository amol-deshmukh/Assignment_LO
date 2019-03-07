package com.assignment.lotusassignment.customviews;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.lotusassignment.R;
import com.assignment.lotusassignment.models.ResponseItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by
 *
 * @author Amol Deshmukh
 * @since 05/03/19
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataViewHolder> {

    private static final int TYPE1 = 1;
    private static final int TYPE2 = 2;
    private static final int TYPE3 = 3;
    private List<ResponseItem> list;
    private Activity activity;
    FirstView.OnCameraClickListener onCameraClickListener;
    SecondView.OnSecondActionDone onSecondActionDone;
    ThirdView.OnThirdItemActionDone onThirdItemActionDone;

    public RecyclerViewAdapter(List<ResponseItem> responses, Activity activity) {
        this.list = responses;
        this.activity = activity;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == TYPE1) {
            View view = inflater.inflate(R.layout.first_item, parent, false);
            FirstView firstView = new FirstView(view);
            firstView.setOnCameraClickListener(onCameraClickListener);
            return firstView;
        }

        if (viewType == TYPE2) {
            View view = inflater.inflate(R.layout.second_item, parent, false);
            SecondView secondView=new SecondView(view);
            secondView.setOnSecondActionDone(onSecondActionDone);
            return secondView;
        }

        if (viewType == TYPE3) {
            View view = inflater.inflate(R.layout.third_item, parent, false);
            ThirdView thirdView =new ThirdView(view);
            thirdView.setOnThirdItemActionDone(onThirdItemActionDone);
            return thirdView;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        if (holder instanceof FirstView) {
            ((FirstView) holder).bind(list.get(position));
        } else if (holder instanceof SecondView) {
            ((SecondView) holder).bind(list.get(position));
        } else if (holder instanceof ThirdView) {
            ((ThirdView) holder).bind(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if ("PHOTO".equalsIgnoreCase(list.get(position).getType())) {
            return TYPE1;
        } else if ("SINGLE_CHOICE".equalsIgnoreCase(list.get(position).getType())) {
            return TYPE2;
        } else if ("COMMENT".equalsIgnoreCase(list.get(position).getType())) {
            return TYPE3;
        } else {
            return TYPE1;
        }

    }


    static class DataViewHolder extends RecyclerView.ViewHolder {

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setOnCameraClickListener(FirstView.OnCameraClickListener onCameraClickListener) {
        this.onCameraClickListener = onCameraClickListener;
    }

    public SecondView.OnSecondActionDone getOnSecondActionDone() {
        return onSecondActionDone;
    }

    public void setOnSecondActionDone(SecondView.OnSecondActionDone onSecondActionDone) {
        this.onSecondActionDone = onSecondActionDone;
    }

    public ThirdView.OnThirdItemActionDone getOnThirdItemActionDone() {
        return onThirdItemActionDone;
    }

    public void setOnThirdItemActionDone(ThirdView.OnThirdItemActionDone onThirdItemActionDone) {
        this.onThirdItemActionDone = onThirdItemActionDone;
    }
}
