package com.example.android.mrasyidridhotobing_1202150064_studycase5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddToDo extends AppCompatActivity {

    //deklarasi variable yang akan digunakan
    EditText ToDo, Description, Priority;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        setTitle("Add Todo");

        //menarik id edittext pada layout
        ToDo = (EditText) findViewById(R.id.editTodo);
        Description = (EditText) findViewById(R.id.editDesc);
        Priority = (EditText) findViewById(R.id.editPriority);
        db = new Database(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddToDo.this, ListToDo.class);
        startActivity(intent);
        this.finish();
    }

    //method yang dijalanan ketika tombol tambah to do di klik
    public void tambah(View view) {
        //logika apabila to do name, description, dan priority diisi
        if (db.inputdata(new AddData(ToDo.getText().toString(), Description.getText().toString(), Priority.getText().toString()))){
            //toast jika dilakukan pengisian dan menekan button add to do
            Toast.makeText(this, "To Do List added!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddToDo.this, ListToDo.class));
            this.finish();
        }else {
            //logika apabila to do name, description, dan priority tidak diisi
            Toast.makeText(this, "Cannot add the list", Toast.LENGTH_SHORT).show();
            //set semua edit text menjadi kosong
            ToDo.setText(null);
            Description.setText(null);
            Priority.setText(null);
        }
    }
}
