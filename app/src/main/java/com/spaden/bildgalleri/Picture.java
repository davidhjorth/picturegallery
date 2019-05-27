package com.spaden.bildgalleri;

import android.net.Uri;

public class Picture {

    public static final String EXTRA_TITLE = "TITLE";
    public static final String EXTRA_IMAGE_URI = "IMAGE_URI";
    private String mTitle;
    private String mImageUri;
    //private Uri mImage;


    public Picture(Uri imageUri) {      //
        String path = imageUri.getPath();
        mTitle=  path.substring(path.lastIndexOf('/') + 1);
        mImageUri = imageUri.toString();
    }


    public Uri getImage() {
        return Uri.parse(mImageUri);
    }

    public String getTitle() {
        return mTitle;
    }

}
