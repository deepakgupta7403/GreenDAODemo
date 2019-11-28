package com.app.greendaodemo.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.greendaodemo.R;
import com.app.greendaodemo.Utility.MyUtils;
import com.app.greendaodemo.databasehelper.NoteOperation;
import com.app.greendaodemo.model.NoteModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewNoteActivity extends AppCompatActivity {
    RecyclerView rv_view_notes;
    FloatingActionButton fab_add_notes;
    LinearLayout ll_no_notes_found;
    Context context;
    RecyclerViewAdapter recyclerViewAdapter;
    List<NoteModel> noteModels = new ArrayList<>();
    HashMap<Long, NoteModel> noteModelHashMap = new HashMap<>();
    AppCompatImageButton ib_delete, ib_select_unselect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        context = getApplicationContext();
        setViews();
        initRecyclerView();

        fab_add_notes.setOnClickListener(v -> {
            addNoteDialogbox(ViewNoteActivity.this);
        });
    }


    private void setViews() {
        fab_add_notes = (FloatingActionButton) findViewById(R.id.fab_add_notes);
        ll_no_notes_found = (LinearLayout) findViewById(R.id.ll_no_notes_found);
        ib_delete = (AppCompatImageButton) findViewById(R.id.ib_delete);
        ib_select_unselect = (AppCompatImageButton) findViewById(R.id.ib_select_unselect);

        ib_delete.setOnClickListener(v -> {
            for (NoteModel noteModel : noteModelHashMap.values()) {
                NoteOperation.deleteNote(noteModel);
            }
            noteModelHashMap.clear();
            noteModels.clear();
            noteModels.addAll(NoteOperation.getAllNotes());
            notifyDatasetChanged();
            ib_delete.setVisibility(View.GONE);
        });

        ib_select_unselect.setOnClickListener(v -> {
            Toast.makeText(context, "SELECT UNSELECT", Toast.LENGTH_SHORT).show();
        });

    }

    private void initRecyclerView() {
        rv_view_notes = (RecyclerView) findViewById(R.id.rv_view_notes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_view_notes.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(noteModels);
        rv_view_notes.setAdapter(recyclerViewAdapter);

    }

    private void checkAndHandleDeleteNote() {
        if (noteModelHashMap.size() == 0) {
            ib_delete.setVisibility(View.GONE);
        } else {
            ib_delete.setVisibility(View.VISIBLE);
        }
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter {
        List<NoteModel> mList;

        public RecyclerViewAdapter(List<NoteModel> mList) {
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
            NoteModel noteModel = mList.get(position);
            MainViewHolder viewHolder = (MainViewHolder) holder;

            if (!TextUtils.isEmpty(noteModel.getTitle())) {
                viewHolder.tv_title.setText(noteModel.getTitle());
            } else {
                viewHolder.tv_title.setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(noteModel.getDescription())) {
                viewHolder.tv_description.setText(noteModel.getDescription());
            } else {
                viewHolder.tv_description.setVisibility(View.GONE);
            }

            if (noteModel.getLastUpdatedAt() != null) {
                viewHolder.tv_time.setText(MyUtils.getDateTimeFromMillies(noteModel.getLastUpdatedAt()));
            }

            viewHolder.cb_delete.setOnCheckedChangeListener(null);
            if (noteModelHashMap.containsKey(noteModel.getId())) {
                viewHolder.cb_delete.setChecked(true);
            } else {
                viewHolder.cb_delete.setChecked(false);
            }

            viewHolder.cb_delete.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                if (isChecked) {
                    noteModelHashMap.put(noteModel.getId(), noteModel);
                } else {
                    noteModelHashMap.remove(noteModel.getId());
                }
                checkAndHandleDeleteNote();
            });

            viewHolder.itemView.setOnClickListener(view -> {
                viewNoteDialogbox(context,noteModel);
            });

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class MainViewHolder extends RecyclerView.ViewHolder {
            CheckBox cb_delete;
            TextView tv_title, tv_description, tv_time;


            public MainViewHolder(View view) {
                super(view);
                cb_delete = (CheckBox) view.findViewById(R.id.cb_delete);

                tv_title = (TextView) view.findViewById(R.id.tv_title);
                tv_description = (TextView) view.findViewById(R.id.tv_description);
                tv_time = (TextView) view.findViewById(R.id.tv_time);
            }
        }
    }

    private void notifyDatasetChanged() {
        if (recyclerViewAdapter != null) {
            recyclerViewAdapter.notifyDataSetChanged();
        }
        checkAndHandleNoData();
    }

    private void checkAndHandleNoData() {
        if (noteModels != null && !noteModels.isEmpty()) {
            ll_no_notes_found.setVisibility(View.GONE);
        } else {
            ll_no_notes_found.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<NoteModel> noteModelList = NoteOperation.getAllNotes();
        noteModels.clear();
        if (noteModelList != null && !noteModelList.isEmpty()) {
            noteModels.addAll(noteModelList);
            notifyDatasetChanged();
        }
    }

    private void addNoteDialogbox(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_note_view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextInputEditText tiet_title = dialog.findViewById(R.id.tiet_title);
        TextInputEditText tiet_description = dialog.findViewById(R.id.tiet_description);

        dialog.setOnDismissListener(dialogInterface -> {
            if (!TextUtils.isEmpty(tiet_title.getText().toString().trim()) || !TextUtils.isEmpty(tiet_description.getText().toString().trim())) {
                NoteModel noteModel = new NoteModel();
                if (!TextUtils.isEmpty(tiet_title.getText().toString().trim())) {
                    noteModel.setTitle(tiet_title.getText().toString().trim());
                }
                if (!TextUtils.isEmpty(tiet_description.getText().toString().trim())) {
                    noteModel.setDescription(tiet_description.getText().toString().trim());
                }

                noteModel.setCreatedAt(System.currentTimeMillis());
                noteModel.setLastUpdatedAt(System.currentTimeMillis());
                noteModel.setIsActivated(true);
                NoteOperation.insertNoteData(noteModel);
            }
            List<NoteModel> noteModelList = NoteOperation.getAllNotes();
            noteModels.clear();
            if (noteModelList != null && !noteModelList.isEmpty()) {
                noteModels.addAll(noteModelList);
                notifyDatasetChanged();
            }
        });


        dialog.show();
    }

    private void viewNoteDialogbox(Context context, NoteModel noteModel) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_note_view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextInputEditText tiet_title = dialog.findViewById(R.id.tiet_title);
        TextInputEditText tiet_description = dialog.findViewById(R.id.tiet_description);

        if (noteModel != null) {
            if (!TextUtils.isEmpty(noteModel.getTitle())) {
                tiet_title.setText(noteModel.getTitle());
            }
            if (!TextUtils.isEmpty(noteModel.getDescription())) {
                tiet_description.setText(noteModel.getDescription());
            }
        }

        dialog.setOnDismissListener(dialogInterface -> {
            if (!TextUtils.isEmpty(tiet_title.getText().toString().trim()) || !TextUtils.isEmpty(tiet_description.getText().toString().trim())) {
                if (!TextUtils.isEmpty(tiet_title.getText().toString().trim())) {
                    noteModel.setTitle(tiet_title.getText().toString().trim());
                }
                if (!TextUtils.isEmpty(tiet_description.getText().toString().trim())) {
                    noteModel.setDescription(tiet_description.getText().toString().trim());
                }

                noteModel.setLastUpdatedAt(System.currentTimeMillis());
                noteModel.setIsActivated(true);
                NoteOperation.updateNotes(noteModel);
            }
            noteModels.clear();
            noteModels.addAll(NoteOperation.getAllNotes());
            notifyDatasetChanged();
        });


        dialog.show();
    }
}
