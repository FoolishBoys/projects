package com.example.codingboy.notebook1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
{

    private Button addbtn;
    private ListView lv;
    private MyAdapter adapter;
    private NotesDB notesDB;
    private SQLiteDatabase dbReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addbtn=(Button)findViewById(R.id.add);
        lv=(ListView)findViewById(R.id.list);
        notesDB=new NotesDB(this);
        dbReader=notesDB.getReadableDatabase();

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddContent.class);
                startActivity(intent);
            }
        });

    }

    public void selectDB()
    {
        Cursor cursor=dbReader.query(NotesDB.TABLE_NAME,null,null,null,null,null,null);
//        Cursor cursor = dbReader.rawQuery("select * from " + NotesDB.TABLE_NAME,new String[]{});
        adapter=new MyAdapter(this,cursor);
        lv.setAdapter(adapter);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        selectDB();
    }
}
