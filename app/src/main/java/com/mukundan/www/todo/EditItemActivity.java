package com.mukundan.www.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends ActionBarActivity {

    EditText etEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        etEditText = (EditText) findViewById(R.id.etEditItem);
        etEditText.setText(getIntent().getStringExtra("itemValue"));
    }

    public void onSaveItem(View view) {
        Intent data = new Intent();
        data.putExtra("newValue", etEditText.getText().toString());
        data.putExtra("position", getIntent().getIntExtra("position", 0));
        setResult(RESULT_OK, data);
        this.finish();
    }

}
