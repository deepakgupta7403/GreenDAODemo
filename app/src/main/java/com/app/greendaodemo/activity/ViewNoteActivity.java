package com.app.greendaodemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.greendaodemo.R;
import com.app.greendaodemo.Utility.MyUtils;
import com.app.greendaodemo.databasehelper.NoteOperation;
import com.app.greendaodemo.model.NoteModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ViewNoteActivity extends AppCompatActivity {
    RecyclerView rv_view_notes;
    FloatingActionButton fab_add_notes;
    LinearLayout ll_no_notes_found;
    Context context;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<NoteModel> noteModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        context = getApplicationContext();
        setViews();
        initRecyclerView();


        fab_add_notes.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddNoteActivity.class);
            startActivity(intent);
        });
    }


    private void setViews() {
        fab_add_notes = (FloatingActionButton) findViewById(R.id.fab_add_notes);
        ll_no_notes_found = (LinearLayout) findViewById(R.id.ll_no_notes_found);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(MyUtils.getStringResources(context, R.string.view_notes));
        }
    }

    private void initRecyclerView() {
        rv_view_notes = (RecyclerView) findViewById(R.id.rv_view_notes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_view_notes.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(noteModels);
        rv_view_notes.setAdapter(recyclerViewAdapter);

    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter {
        ArrayList<NoteModel> mList;

        public RecyclerViewAdapter(ArrayList<NoteModel> mList) {
            this.mList = mList;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_notes, parent, false);
            return new MainViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
//            NoteModel noteModel = mList.get(position);

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class MainViewHolder extends RecyclerView.ViewHolder {
//            Button btn_delete;

            public MainViewHolder(View view) {
                super(view);
//                btn_delete = (Button) findViewById(R.id.btn_delete);

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("ListIsEmpty", NoteOperation.getAllNotes().toString());
    }
}
