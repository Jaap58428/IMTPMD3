package nl.itsjaap.week3.List;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nl.itsjaap.week3.R;
import nl.itsjaap.week3.database.DatabaseHelper;
import nl.itsjaap.week3.database.DatabaseInfo;
import nl.itsjaap.week3.models.CourseModel;

public class CourseListActivity extends AppCompatActivity {

    private ListView mListView;
    private CourseListAdapter mAdapter;
    private List<CourseModel> courseModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courselist);

        DatabaseHelper db = DatabaseHelper.getHelper(getApplicationContext());
        Cursor rs = db.query(DatabaseInfo.CourseTable.COURSETABLE, new String[]{"*"}, null, null, null, null, null);

        mListView = (ListView) findViewById(R.id.my_list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                     Toast t = Toast.makeText(CourseListActivity.this,"Click" + position,Toast.LENGTH_SHORT);
                     t.show();
                 }
             }
        );

        if (rs.getCount() > 0) {
            rs.moveToFirst();
            for (int i = 0 ; i < rs.getCount() ; i++) {
                String name = rs.getString(rs.getColumnIndex("name"));
                String ects = rs.getString(rs.getColumnIndex("ects"));
                String period = rs.getString(rs.getColumnIndex("period"));
                String grade = rs.getString(rs.getColumnIndex("grade"));
                courseModels.add(new CourseModel(name, ects, grade, period));
                rs.moveToNext();
            }
        }


        mAdapter = new CourseListAdapter(CourseListActivity.this, 0, courseModels);
        mListView.setAdapter(mAdapter);
    }

}
