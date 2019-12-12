package com.example.facebook_fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class NoteActivity extends AppCompatActivity {
    String text = "Daily Message";
    String Body1 = "";
    String Body2 = "";
    String Body3 = "";
    EditText editText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final int mode = getIntent().getIntExtra("mode", 0);
        //text = getIntent().getStringExtra("text");
        editText1 = (EditText)findViewById(R.id.edit_text1);
        editText1.setText(text);

            Body1 = open("body1");
            EditText editText = findViewById(R.id.edit_text);
            editText.setText(Body1);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitText(mode);
            }
        });
    }

    /** Writes textToSave to the file denoted by fileName. **/
    private void save(String fileName, String textToSave) {
        try {
            OutputStreamWriter out =
                    new OutputStreamWriter(openFileOutput(fileName, 0));
            out.write(textToSave);
            out.close();
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    /** Checks if the file denoted by fileName exists. **/
    private boolean fileExists(String fileName) {
        File file = getBaseContext().getFileStreamPath(fileName);
        return file.exists();
    }

    /** Opens the file denoted by fileName and returns the contents of the file. **/
    private String open(String fileName) {
        String content = "";
        if (fileExists(fileName)) {
            try {
                InputStream in = openFileInput(fileName);
                if ( in != null) {
                    InputStreamReader tmp = new InputStreamReader( in );
                    BufferedReader reader = new BufferedReader(tmp);
                    String str;
                    StringBuilder buf = new StringBuilder();
                    while ((str = reader.readLine()) != null) {
                        buf.append(str + "\n");
                    } in .close();
                    content = buf.toString();
                }
            } catch (java.io.FileNotFoundException e) {} catch (Throwable t) {
                Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return content;
    }

    private void submitText(final int mode) {
        /*if (mode == 1) {
            EditText editText1 = (EditText)findViewById(R.id.edit_text1);
            // Prepare data intent
            //Intent data = new Intent(NoteActivity.this, MainActivity.class);
            // Pass relevant data back as a result
            //data.putExtra("text", editText1.getText().toString());

            EditText editText = findViewById(R.id.edit_text);
            Body1 = editText.getText().toString();
            save("body1", Body1);
            // Activity finished ok, return the data
            //setResult(RESULT_OK, data); // set result code and bundle data for response
            startActivity(data);

            finish(); // closes the activity, pass data to parent
        //}*/

        finish();

    }
}
