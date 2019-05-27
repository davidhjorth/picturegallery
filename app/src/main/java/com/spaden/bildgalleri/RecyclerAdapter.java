package com.spaden.bildgalleri;

import android.content.Context;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ListItemHolder> {
    private ArrayList<Picture> itemList;
    private MainActivity activity;
    private Context context;

    public RecyclerAdapter(MainActivity mainActivity, ArrayList<Picture> itemList) {

        this.itemList = itemList;
        this.activity = mainActivity;
    }

    @Override
    public RecyclerAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_picture, viewGroup, false);


        return new ListItemHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapter.ListItemHolder listItemHolder, final int position) {
        Picture item = itemList.get(position);

        listItemHolder.Bind(item);


    }


    @Override
    public int getItemCount() {

        return itemList.size();
    }

    public ArrayList<Picture> getPictures(){
        return itemList;
    }

    public void addPicture(Picture picture) {
        itemList.add(picture);
        notifyItemInserted(itemList.size() - 1);
    }


    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Picture mPicture;
        TextView mTitle;
        ImageView mImage;

        public ListItemHolder(@NonNull View itemView) {
            super(itemView);


            mTitle = (TextView) itemView.findViewById(R.id.titleView);
            mImage = (ImageView) itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(this);
            mImage.setOnClickListener(this);


        }

        public void Bind(Picture picture) {
            mPicture = picture;
            mTitle.setText(mPicture.getTitle());
            mImage.setImageURI(mPicture.getImage());
        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(activity, PictureActivity.class);
            intent.putExtra(Picture.EXTRA_TITLE, mPicture.getTitle());
            intent.putExtra(Picture.EXTRA_IMAGE_URI, mPicture.getImage().toString());
            activity.startActivity(intent);


        }
    }
}
