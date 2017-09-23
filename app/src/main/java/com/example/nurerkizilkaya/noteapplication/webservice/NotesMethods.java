package com.example.nurerkizilkaya.noteapplication.webservice;

import com.example.nurerkizilkaya.noteapplication.model.Notes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by a s u s on 19.9.2017.
 */
public interface NotesMethods {
     @GET("posts")
    Call<Notes[]> getNotesList();

    @GET("posts/{id}")
    Call<Notes> getNoteDetail(@Path("id") String id);

    @PUT("posts/{id}")
    Call<Notes> updateNoteDetail(@Path("id") String id,@Body Notes notes);

    @DELETE("posts/{id}")
    Call<Notes> deleteNoteDetail(@Path("id") String id) ;

    @POST("posts")
    Call<Notes[]> insertNote(Notes notes);

}
