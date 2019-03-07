package com.assignment.lotusassignment.customviews;

import android.os.Build;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.assignment.lotusassignment.LotusApplication;
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
public class SecondView extends RecyclerViewAdapter.DataViewHolder {
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    @BindView(R.id.txtTitle)
    TextView txtTitle;
    OnSecondActionDone onSecondActionDone;

    public SecondView(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(ResponseItem responseItem) {
        txtTitle.setText(responseItem.getTitle());

        if (responseItem.getDataMap() != null && responseItem.getDataMap().getOptions() != null && radioGroup.getChildCount() == 0) {
            for (int i = 0; i < responseItem.getDataMap().getOptions().size(); i++) {
                RadioButton radioButton = new RadioButton(LotusApplication.getInstance());
                radioButton.setText(responseItem.getDataMap().getOptions().get(i));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    radioButton.setTextColor(LotusApplication.getInstance().getColor(R.color.black));
                } else {
                    radioButton.setTextColor(LotusApplication.getInstance().getResources().getColor(R.color.black));
                }
                radioButton.setId(i);
                radioGroup.addView(radioButton);
            }

        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                onSecondActionDone.onRadioButtonClicked(checkedId, getLayoutPosition());
            }
        });

    }

    public interface OnSecondActionDone {
        void onRadioButtonClicked(int checkedId, int position);
    }

    public OnSecondActionDone getOnSecondActionDone() {
        return onSecondActionDone;
    }

    public void setOnSecondActionDone(OnSecondActionDone onSecondActionDone) {
        this.onSecondActionDone = onSecondActionDone;
    }
}
