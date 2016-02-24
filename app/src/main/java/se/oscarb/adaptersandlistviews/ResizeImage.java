package se.oscarb.adaptersandlistviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ResizeImage {


    // Räknar ut många gånger bilden ska halveras för att nå önskad storlek
    // Önskad storlek anges som reqWidth och reqHeight
    // Bildens originalstorlek finns i options (BitmapFactory.options)
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    /*
            Metod som förminskar en bild givet ett visst ID och returnerar den nya bilden
            som en Bitmap
            res är t.ex. getResources()
            resId är t.ex. R.drawable.my_image
            reqWidth är önskad bredd
            reqHeight är önskad höjd

         */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        // Undvik att läsa in hela bilden i minnet
        options.inJustDecodeBounds = true;
        // Den här metoden ändrar på options så att options nu har värden på
        // options.outHeight och options.outWidth
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        // Hur mycket vill vi förminska bilden med? Lägg till i options
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        // Skapa en ny förminskad bild med metoden decodeResource
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static Bitmap getOptimizedBitmap(Context context, int resId, int reqWidth, int reqHeight) {

        return decodeSampledBitmapFromResource(context.getResources(), resId, reqWidth, reqHeight);
    }
}
