package com.warrantix.main.common.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.noveogroup.android.log.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ImageProcessor
{
    private String mDestinationDirectory;
    private String mFinishedImageName;

    public ImageProcessor(String destDir, String destName)
    {
        mDestinationDirectory = destDir;
        mFinishedImageName = destName;
    }

    public void processAndSaveImage(Bitmap bitmap, float scaleFactor, int angle)
    {
        saveBitmapToDisk(resizeAndRotate(scaleFactor, bitmap, angle));
    }

    private void saveBitmapToDisk(Bitmap bitmap)
    {
        File outFile = new File(mDestinationDirectory, mFinishedImageName);
        FileOutputStream fos;

        try
        {
            fos = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        }catch (FileNotFoundException e)
        {
            e.getMessage();
        }
    }

    private Bitmap resizeAndRotate(float scaleFactor, Bitmap src, int angle)
    {
        Matrix matrix = rotateLogic(scaleFactor, angle);
        return  Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }

    private Matrix rotateLogic(float scaleFactor, int angle)
    {
        Matrix matrix = new Matrix();
        matrix.preScale(scaleFactor, scaleFactor);
        matrix.setRotate(angle);
        return matrix;

    }

    private void imageProcessorError()
    {
        Log.d("There was an error in your request");
    }
}