package com.mukundan.www.todo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    ArrayList<String> todoItems;
    ArrayAdapter<String> aTodoAdapter;
    ListView lvItems;
    EditText etNewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateArrayItems();
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(aTodoAdapter);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
               @Override
               public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                   todoItems.remove(position);
                   aTodoAdapter.notifyDataSetChanged();
                   writeItems();
                   return true;
               }
           });
        etNewItem = (EditText) findViewById(R.id.etNewItem);
    }

    public void populateArrayItems() {
        readItems();
        aTodoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
    }

    private void readItems() {
        File fileDir = getFilesDir();
        File file = new File(fileDir, "todo.txt");

        try {
            todoItems = new ArrayList<String>(FileUtils.readLines(file));
        } catch (IOException e) {
            todoItems = new ArrayList<>();
        }
    }

    private void writeItems() {
        File fileDir = getFilesDir();
        File file = new File(fileDir, "todo.txt");

        try {
            FileUtils.writeLines(file, todoItems);
        } catch (IOException e) {

        }
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onAddItem(View view) {
        aTodoAdapter.add(etNewItem.getText().toString());
        etNewItem.setText("");
        writeItems();
    }
}
