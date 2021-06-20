package com.example.favsongs.profile;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.annotation.Nullable;

public class ImageProcessor {

    public static final String IMAGE_DIR = "imageDir";
    public static final String IMAGE_FORMAT = ".jpg";

    /**
     * Decodes uri into bitmap
     * @param context Application Context
     * @param uri Image Uri
     * @param requiredSize The size needed
     * @return The uri bitmap
     * @throws FileNotFoundException
     */
    public Bitmap decodeUri(Context context, Uri uri, final int requiredSize) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, o);

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;

        while (true) {
            if (width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, o2);
    }

    /**
     * Save Bitmap to Internal Storage
     * @param context Application Context
     * @param bitmapImage Bitmap to save
     * @param imageName The name to save the bitmap as a file
     * @return The path of the file saved
     */
    public String saveToInternalStorage(Context context, Bitmap bitmapImage, String imageName){
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(IMAGE_DIR, Context.MODE_PRIVATE);
        File imageFile = new File(directory, imageName + IMAGE_FORMAT);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(imageFile);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    /**
     * Get Bitmap from File in path
     * @param localPath Path of the image
     * @return Bitmap found on the file
     */
    public @Nullable Bitmap getImageBitmap(String localPath) {
        try {
            File imageFile = new File(localPath);
            return BitmapFactory.decodeStream(new FileInputStream(imageFile));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
