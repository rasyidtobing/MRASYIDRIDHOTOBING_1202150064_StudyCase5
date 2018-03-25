package com.example.android.mrasyidridhotobing_1202150064_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class ListToDo extends AppCompatActivity {
    //pendeklarasian variabel yang digunakan
    Database db;
    RecyclerView rv;
    Adapter adapter;
    ArrayList<AddData> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_to_do);
        setTitle("Todo List");

        //memanggil recycleview pada layout
        rv = findViewById(R.id.recycleview);
        //membuat araylist baru
        datalist = new ArrayList<>();
        //membuat database baru
        db = new Database(this);
        //memanggil method readdata
        db.readdata(datalist);

        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //membuat adapter baru
        adapter = new Adapter(this,datalist, color);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        swipeToDelete();
    }

    //membuat method untuk menghapus item pada to do list
    public void swipeToDelete(){
        //membuat touch helper baru untuk recycler view
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AddData current = adapter.getData(position);
                //apabila item di swipe ke kiri
                if(direction==ItemTouchHelper.LEFT){
                    if(db.removedata(current.getTodo())){
                        //menghapus data
                        adapter.deleteData(position);
                        //membuat snack bar item telah dihapus
                        Snackbar.make(findViewById(R.id.color), "Data Deleted", 1000).show();
                    }
                }
            }
        };
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(rv);
    }

    //membuat menu setting ketika aplikasi dijalankan
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_to_do_list, menu);
        return true;
    }

    //method ketika item pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //apabila item setting dipilih
        if (id==R.id.action_settings){
            Intent intent = new Intent(ListToDo.this, Setting.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    //method ketika FAB atau tombol add ditekan
    public void add(View view) {
        Intent intent = new Intent(ListToDo.this, AddToDo.class);
        startActivity(intent);
    }
}
