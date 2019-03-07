package com.assignment.lotusassignment.customviews;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.assignment.lotusassignment.R;
import com.assignment.lotusassignment.models.ResponseItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 *
 * @author Amol Deshmukh
 * @since 07/03/19
 */
public class ThirdView extends RecyclerViewAdapter.DataViewHolder {


    @BindView(R.id.switch_compat2)
    SwitchCompat switchCompat;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.edtText)
    EditText edtText;
    OnThirdItemActionDone onThirdItemActionDone;


    public ThirdView(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void bind(ResponseItem responseItem){
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    edtText.setVisibility(View.VISIBLE);
                    edtText.setText("");
                    //onThirdItemActionDone.onTextChanged("",getLayoutPosition());
                }else {
                    edtText.setVisibility(View.GONE);
                    edtText.setText("");
                    //onThirdItemActionDone.onTextChanged("",getLayoutPosition());

                }

            }
        });
        edtText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onThirdItemActionDone.onTextChanged(s.toString(),getLayoutPosition());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    public interface OnThirdItemActionDone{
        void onTextChanged(String text,int position);
        void onCheckChanged(boolean ischecked,int position);
    }

    public OnThirdItemActionDone getOnThirdItemActionDone() {
        return onThirdItemActionDone;
    }

    public void setOnThirdItemActionDone(OnThirdItemActionDone onThirdItemActionDone) {
        this.onThirdItemActionDone = onThirdItemActionDone;
    }
}
