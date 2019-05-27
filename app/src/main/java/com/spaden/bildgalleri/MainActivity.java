package com.spaden.bildgalleri;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int PICK_PICTURE_REQUEST_CODE = 1;

    RecyclerView mRecyclerView;
    RecyclerAdapter mRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);

        ArrayList<Picture> pictures = new ArrayList<Picture>(); //loadData();
        mRecyclerAdapter = new RecyclerAdapter(this, pictures);

        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent imagePicker = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(imagePicker, PICK_PICTURE_REQUEST_CODE);
            }
        });

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {

            case PICK_PICTURE_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    Picture newPic = new Picture(selectedImage);
                    mRecyclerAdapter.addPicture(newPic);
                    ArrayList<Picture> pictures = mRecyclerAdapter.getPictures();
                    //saveData(pictures);
                }
                break;
        }
    }

    private void saveData(ArrayList<Picture> pictures){
        String filename = "SaveData.json";
        FileOutputStream outputStream;
        try{
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            Writer writer = new OutputStreamWriter(outputStream);
            Gson gson = new Gson();
            gson.toJson(pictures, writer);
            writer.close();
            Log.d("Sparat","Sparat");
        }catch (Exception e){

            Log.e("Kan inte spara","Kan inte spara");

        }
    }

    private ArrayList<Picture> loadData(){
        ArrayList<Picture> pictures = new ArrayList<Picture>();
        String filename = "SaveData.json";
        FileInputStream inputStream;
        try{
            inputStream = openFileInput(filename);
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));
            Gson gson = new Gson();
            Type collectionType = new TypeToken<ArrayList<Picture>>(){}.getType();
            pictures = gson.fromJson(reader, collectionType);
            gson.fromJson(reader, collectionType);
            reader.close();
            Log.d("Laddat","Laddat");
        }catch (Exception e){

            Log.e("Kan inte ladda","Kan inte ladda");

        }
        return pictures;
    }


}

