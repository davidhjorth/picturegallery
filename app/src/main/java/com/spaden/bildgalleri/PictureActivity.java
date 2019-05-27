package com.spaden.bildgalleri;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class PictureActivity extends AppCompatActivity {

    private TextView mTitleView;
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        mTitleView = findViewById(R.id.titleView);
        mImageView = findViewById(R.id.imageView);

        Intent intent = getIntent();

        String title = intent.getStringExtra(Picture.EXTRA_TITLE);
        Uri uri = Uri.parse(intent.getStringExtra(Picture.EXTRA_IMAGE_URI));

        mTitleView.setText(title);
        mImageView.setImageURI(uri);
    }

}
