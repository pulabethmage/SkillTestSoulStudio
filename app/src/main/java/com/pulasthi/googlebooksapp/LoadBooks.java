package com.pulasthi.googlebooksapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoadBooks extends AppCompatActivity {

    ImageView Imgv_bookthum;
    TextView Tv_bookTitle,Tv_bookDes;

    ProgressDialog progressDialog;


    private ArrayList<String> arraybooktitles= new ArrayList<>();
    private ArrayList<String> arraybookdescriptions= new ArrayList<>();
    private ArrayList<String> arraybookthumbnails= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_books);

        progressDialog = new ProgressDialog(LoadBooks.this);

        Imgv_bookthum = findViewById(R.id.book_tumbnail);
        Tv_bookTitle = findViewById(R.id.book_title);
        Tv_bookDes = findViewById(R.id.book_description);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadBooks();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void loadBooks()
    {

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://www.googleapis.com/books/v1/volumes?q=flowers&startIndex=0&maxResults=10";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            setresponse(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        error.printStackTrace();

                    }
                });

        progressDialog.setMessage("\tLoading Books...");
        progressDialog.show();
        queue.add(request);

        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {

            @Override
            public void onRequestFinished(Request<Object> request) {
                queue.getCache().clear();

            }
        });
    }


    public void setresponse(JSONObject response) throws JSONException {


        JSONArray jsonArray = response.getJSONArray("items");

        for (int i = 0; i<jsonArray.length();i++)
        {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject jsonVolumeInfo = jsonObject.getJSONObject("volumeInfo");
                JSONObject jsonImageLinksObject = jsonVolumeInfo.getJSONObject("imageLinks");

                ///jsonVolumeInfo
                arraybooktitles.add(jsonVolumeInfo.getString("title"));
                arraybookdescriptions.add(jsonVolumeInfo.getString("description"));

                ///jsonImageLinksObject
                arraybookthumbnails.add(jsonImageLinksObject.getString("thumbnail"));

                // Toast.makeText(this,jsonImageLinksObject.getString("thumbnail"), Toast.LENGTH_SHORT).show();


            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        initRecyclerSingleAdapter();

    }

    public void initRecyclerSingleAdapter()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = findViewById(R.id.book_list_recyclerview);
        recyclerView.setLayoutManager(layoutManager);

        BookListRecyclerViewAdapter adapter = new BookListRecyclerViewAdapter(
                this,Imgv_bookthum,Tv_bookTitle,Tv_bookDes,arraybookthumbnails,arraybooktitles,arraybookdescriptions);
        recyclerView.setAdapter(adapter);
    }
    

}