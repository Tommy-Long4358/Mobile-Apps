package com.example.photocamerav3;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.SystemClock;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class StorageUtility {
    /*
     * This method write its bitmap parameter to external storage
     * in the Pictures directory
     * @param activity, an Activity
     * @param bitmap, a Bitmap reference
     * @return returns the File it wrote bitmap to
     */
    // public static String MA="Directory Name";
    public static File writeToExternalStorage
    (Activity activity, Bitmap bitmap ) throws IOException {
        // get state of external storage
        // Environment class provides access to environment variables
        String storageState = Environment.getExternalStorageState( );
        File file = null;

        // Checks for if there is external storage on the device
        // Also grants read and write access
        if( storageState.equals( Environment.MEDIA_MOUNTED ) ) {
            // get external storage directory
            File dir = activity.getExternalFilesDir( Environment.DIRECTORY_PICTURES );

            // generate a unique file name
            // guarantees that the file name is unqiue every time the app is ran
            Date dateToday = new Date( );

            // elapedRealtime() returns number of milliseconds since the last boot
            long ms = SystemClock.elapsedRealtime( );

            // String filename = "/" + dateToday + "_" + ms + ".png";
            String filename = "/" + dateToday + "_" + ms + ".png";

            // create a file to write to
            file = new File( dir + filename );

            /*
             Check whether there is enough space in external
             storage before we write the Bitmap to the file
             */
            // Retrieve in bytes the available free space in the directory where the File is located
            long freeSpace = dir.getFreeSpace( ); // in bytes

            // Retrieve in bytes the amount of space needed by the Bitmap
            int bytesNeeded = bitmap.getByteCount( ); // in bytes

            // Check if we have atleast 50% more space than needed
            if( bytesNeeded * 1.5 < freeSpace ) {
                // there is space for the bitmap
                try {
                    FileOutputStream fos = new FileOutputStream( file );

                    // Write a compressed version of the bitmap to stream
                    // write to file as PNG
                    boolean result = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

                    fos.close( );

                    if( result )
                        return file;
                    else
                        throw new IOException( "Problem compressing the Bitmap"
                                + " to the output stream" );
                } catch( Exception e ) {
                    throw new IOException( "Problem opening the file for writing" );
                }
            }
            else
                throw new IOException( "Not enough space in external storage"
                                + " to write Bitmap" );
        }
        else
            throw new IOException( "No external storage found" );
    }
}
