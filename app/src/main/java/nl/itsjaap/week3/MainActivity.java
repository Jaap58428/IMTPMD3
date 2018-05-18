package nl.itsjaap.week3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import nl.itsjaap.week3.GSON.GsonRequest;
import nl.itsjaap.week3.GSON.VolleyHelper;
import nl.itsjaap.week3.List.CourseListActivity;
import nl.itsjaap.week3.database.DatabaseHelper;
import nl.itsjaap.week3.database.DatabaseInfo;
import nl.itsjaap.week3.models.CourseModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize DB connection
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(getApplicationContext());
        Cursor rs = dbHelper.query(DatabaseInfo.CourseTable.COURSETABLE, new String[]{"*"}, null, null,null,null,null);

        // Link to button and set listener
        Button fillDb = findViewById(R.id.grabBtn);
        fillDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Grab JSON from web and enter to DB
                requestSubjects();

                // Give toast feedback when successful
                Toast.makeText(getApplicationContext(), "The JSON has arrived!", Toast.LENGTH_SHORT).show();
            }
        });

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CourseListActivity.class);
                startActivity(intent);

            }
        });



    }


    private void requestSubjects(){
        Type type = new TypeToken<List<CourseModel>>(){}.getType();

        GsonRequest<List<CourseModel>> request = new GsonRequest<List<CourseModel>>("http://fuujokan.nl/subject_lijst.json",
                type, null, new Response.Listener<List<CourseModel>>() {
            @Override
            public void onResponse(List<CourseModel> response) {
                processRequestSucces(response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                processRequestError(error);
            }
        });
        VolleyHelper.getInstance(this).addToRequestQueue(request);
    }

    private void processRequestSucces(List<CourseModel> subjects ){
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(getApplicationContext());

        // putting all received classes in my database.
        for (CourseModel cm : subjects) {
            ContentValues cv = new ContentValues();
            cv.put(DatabaseInfo.CourseColumn.NAME, cm.getName());
            cv.put(DatabaseInfo.CourseColumn.ECTS, cm.getEcts());
            cv.put(DatabaseInfo.CourseColumn.GRADE, cm.getGrade());
            cv.put(DatabaseInfo.CourseColumn.PERIOD , cm.getPeriod());
            dbHelper.insert(DatabaseInfo.CourseTable.COURSETABLE, null, cv);
        }

        Cursor rs = dbHelper.query(DatabaseInfo.CourseTable.COURSETABLE, new String[]{"*"}, null, null, null, null, null);
        rs.moveToFirst();   // kan leeg zijn en faalt dan
        DatabaseUtils.dumpCursor(rs);

    }

    private void processRequestError(VolleyError error){
        Toast.makeText(getApplicationContext(), "There was an error:\n" + error , Toast.LENGTH_LONG).show();
    }




}
