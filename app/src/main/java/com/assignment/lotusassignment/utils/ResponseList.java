package com.assignment.lotusassignment.utils;

import com.assignment.lotusassignment.models.ResponseItem;

import java.util.List;

/**
 * Created by
 *
 * @author Amol Deshmukh
 * @since 06/03/19
 */
public class ResponseList {
    private List<ResponseItem> aa;

    public List<ResponseItem> getResponseItemList() {
        return aa;
    }

    public void setResponseItemList(List<ResponseItem> responseItemList) {
        this.aa = responseItemList;
    }
}
