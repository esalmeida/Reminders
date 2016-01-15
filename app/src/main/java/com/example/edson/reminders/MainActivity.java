package com.example.edson.reminders;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;

//Estabelece a logica da aplicacao
public class MainActivity extends AppCompatActivity {
    private ListView fListView;
    private LembreteDbAdapter fDbAdapter;
    private LembreteSimpleCursorAdapter fCursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fListView = (ListView) findViewById(R.id.listView);
        fListView.setDivider(null);
        fDbAdapter = new LembreteDbAdapter(this);
        try {
            fDbAdapter.open();
            if(savedInstanceState == null){
                fDbAdapter.deleteTodosLembretes();
                fDbAdapter.createLembrete("prova de matematica dia 10/01", true);
                fDbAdapter.createLembrete("prova de ingles dia 12/01", false);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        fListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Toast.makeText(MainActivity.this, "clicked" + position, Toast.LENGTH_SHORT).show();
            }

        });
        Cursor cursor = fDbAdapter.fetchAllLembretes();
        //from colunas definidas no db
        String[] from = new String[]{LembreteDbAdapter.COL_CONTENT};
        //to ids das views no layout
        int[] to = new int[]{R.id.linha_texto};

        //O cursorAdapter faz o papel de controlador seguindo o
        //modelo model-view-control
        fCursorAdapter = new LembreteSimpleCursorAdapter(
                //contexto
                MainActivity.this,
                //o layout da linha
                R.layout.lembretes_linha,
                //cursor
                cursor,
                //from colunas definidas no db
                from,
                //to os ids das views no layout
                to,
                //flag - nao usado
                0
        );
        fListView.setAdapter(fCursorAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_new:
                Log.d(getLocalClassName(), "cria um novo Lembrete");
                return true;
            case R.id.action_exit:
                finish();
                return true;
            default:
                return false;
        }


//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }


    }
}
