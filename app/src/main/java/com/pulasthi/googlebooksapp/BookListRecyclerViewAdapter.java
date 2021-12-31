package com.pulasthi.googlebooksapp;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookListRecyclerViewAdapter extends RecyclerView.Adapter<BookListRecyclerViewAdapter.ViewHolder>{

    Context context;

    ImageView img_book_thumb;
    TextView tv_book_title;
    TextView tv_book_desc;

     ArrayList<String> array_img_book_thumb = new ArrayList<>();
     ArrayList<String> array_tv_book_title = new ArrayList<>();
     ArrayList<String> array_tv_book_desc= new ArrayList<>();

    public BookListRecyclerViewAdapter( Context reqContext,
                                           ImageView Img_bookThumb,
                                           TextView Tv_bookTitle,
                                           TextView Tv_bookDesciption,
                                        ArrayList<String> array_img_book_thumb,
                                        ArrayList<String> array_tv_book_title,
                                        ArrayList<String> array_tv_book_desc)
    {

        context = reqContext;
        this.img_book_thumb= Img_bookThumb;
        this.tv_book_title= Tv_bookTitle;
        this.tv_book_desc =Tv_bookDesciption;

        this.array_img_book_thumb =array_img_book_thumb;
        this.array_tv_book_title=array_tv_book_title;
        this.array_tv_book_desc=array_tv_book_desc;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlebookitem,parent,false);

        return new BookListRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListRecyclerViewAdapter.ViewHolder holder, int position) {


        //holder.img_book_thumb.setImageURI(reqId.get(position));
        Picasso.get().load(array_img_book_thumb.get(position)).into(holder.img_book_thumb);
        holder.tv_book_title.setText(array_tv_book_title.get(position));
        holder.tv_book_des.setText(array_tv_book_desc.get(position));
    }

    @Override
    public int getItemCount() {
        return array_img_book_thumb.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img_book_thumb;
        TextView tv_book_title;
        TextView tv_book_des;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_book_thumb = itemView.findViewById(R.id.book_tumbnail);
            tv_book_title = itemView.findViewById(R.id.book_title);
            tv_book_des = itemView.findViewById(R.id.book_description);



        }
    }

}
