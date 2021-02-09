package com.example.thenamequizapp.Classes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;


public class Converter {
    @TypeConverter
    public static Drawable imageToDrawable(String s) {
        try {
            byte[] encodeByte = Base64.decode(s, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return new BitmapDrawable(Resources.getSystem(), bitmap);
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    @TypeConverter
    public static String imageToString(Drawable d) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) d;
        Bitmap bitmap = bitmapDrawable.getBitmap();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
