package com.example.nurerkizilkaya.noteapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.nurerkizilkaya.noteapplication.adapter.NotesAdapter;
import com.example.nurerkizilkaya.noteapplication.model.Notes;
import com.example.nurerkizilkaya.noteapplication.webservice.NotesMethods;
import com.example.nurerkizilkaya.noteapplication.webservice.NotesRetroClient;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoteActivity extends AppCompatActivity {

    Notes[] notesArray;
    NotesAdapter adapter;
    @Bind(R.id.noteRycViewId)
    RecyclerView noteRycViewId;

    @Bind(R.id.insertBtnId)
    Button insertBtnId;
    @OnClick(R.id.insertBtnId) void insertNote()
    {
        Intent myIntent = new Intent(NoteActivity.this, NoteDetailActivity.class);
        myIntent.putExtra("key", "-1"); //Optional parameters
        NoteActivity.this.startActivity(myIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);

        LinearLayoutManager ll=new LinearLayoutManager(this);
        ll.setOrientation(LinearLayoutManager.VERTICAL);
        noteRycViewId.setLayoutManager(ll);

        //Recylerview içinde item'a tıklayarak yeni bir activty başlatmak
        noteRycViewId.addOnItemTouchListener(
                new RecyclerItemClickListener(this, noteRycViewId, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        if(notesArray != null && notesArray.length > 0){
                            Notes selectedPost = notesArray[position];

                            Intent intent = new Intent(NoteActivity.this,NoteDetailActivity.class);
                            intent.putExtra("postId", selectedPost.getId().toString());
                            NoteActivity.this.startActivity(intent);

                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );



       final  NotesMethods notesApi= NotesRetroClient.getClient().create(NotesMethods.class);
       final Call<Notes[]> call= notesApi.getNotesList();
        call.enqueue(new Callback<Notes[]>() {
            @Override
            public void onResponse(Call<Notes[]> call, Response<Notes[]> response) {
                notesArray=response.body();
                adapter = new NotesAdapter(Arrays.asList(response.body()));
                noteRycViewId.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Notes[]> call, Throwable t) {
                Log.d("Api Connection", "Disconnection");
            }
        });
    }
}
