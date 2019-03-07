package com.assignment.lotusassignment.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by
 *
 * @author Amol Deshmukh
 * @since 05/03/19
 */
public class DataMap implements Serializable {
    List<String> options;

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
