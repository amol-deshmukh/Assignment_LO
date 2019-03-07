package com.assignment.lotusassignment.utils;

import android.text.TextUtils;
import android.util.Log;

import com.assignment.lotusassignment.LotusApplication;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by
 *
 * @author Amol Deshmukh
 * @since 05/03/19
 */
public class ResourceUtils {
    public static String readFromAssets(String filename) {
        InputStream is = null;
        BufferedReader reader = null;
        StringBuilder buf = new StringBuilder();
        try {
            is = LotusApplication.getInstance().getAssets().open(filename);
            reader = new BufferedReader(new InputStreamReader(is));

            String str = "";
            if (is != null) {
                while ((str = reader.readLine()) != null)
                    buf.append(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return buf.toString();
    }

    public static <T> T deserialize(String sText, Class<T> destinationClass, String apiOrMethodCall) {
        try {
            Gson gson = new Gson();
            if (TextUtils.isEmpty(sText)) {
                return null;
            } else {
                return gson.fromJson(sText.trim(), destinationClass);
            }
        } catch (Exception e) {
            return null;
        }
    }
}
