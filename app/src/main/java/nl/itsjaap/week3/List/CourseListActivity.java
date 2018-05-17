package nl.itsjaap.week3.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nl.itsjaap.week3.R;
import nl.itsjaap.week3.models.CourseModel;

public class CourseListActivity extends AppCompatActivity {

    private ListView mListView;
    private CourseListAdapter mAdapter;
    private List<CourseModel> courseModels = new ArrayList<>();
    // WE MAY NEED A METHOD TO FILL THIS. WE COULD RETRIEVE THE DATA FROM AN ONLINE JSON SOURCE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courselist);

        mListView = (ListView) findViewById(R.id.my_list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                     Toast t = Toast.makeText(CourseListActivity.this,"Click" + position,Toast.LENGTH_LONG);
                     t.show();
                 }
             }
        );
        courseModels.add(new CourseModel("IKPMD", "3", "10", "2"));
        courseModels.add(new CourseModel("IOPR1", "4", "10", "2"));
        courseModels.add(new CourseModel("IPSEN", "6", "10", "2"));
        courseModels.add(new CourseModel("IOPR1", "4", "10", "2"));
        courseModels.add(new CourseModel("IPSEN", "6", "10", "2"));
        courseModels.add(new CourseModel("IOPR1", "4", "10", "2"));
        courseModels.add(new CourseModel("IPSEN", "6", "10", "2"));
        courseModels.add(new CourseModel("IOPR1", "4", "10", "2"));
        courseModels.add(new CourseModel("IPSEN", "6", "10", "2"));
        courseModels.add(new CourseModel("IOPR1", "4", "10", "2"));
        courseModels.add(new CourseModel("IPSEN", "6", "10", "2"));
        courseModels.add(new CourseModel("IOPR1", "4", "10", "2"));
        courseModels.add(new CourseModel("IPSEN", "6", "10", "2"));

        mAdapter = new CourseListAdapter(CourseListActivity.this, 0, courseModels);
        mListView.setAdapter(mAdapter);
    }

    public static class CourseListAdapter extends ArrayAdapter<CourseModel> {

        public CourseListAdapter(Context context, int resource, List<CourseModel> objects){
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;

            if (convertView == null ) {
                vh = new ViewHolder();
                LayoutInflater li = LayoutInflater.from(getContext());
                convertView = li.inflate(R.layout.view_content_row, parent, false);
                vh.name = (TextView) convertView.findViewById(R.id.subject_name);
                vh.code = (TextView) convertView.findViewById(R.id.subject_code);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            CourseModel cm = getItem(position);
            vh.name.setText((CharSequence) cm.getName());
            vh.code.setText((CharSequence) cm.getEcts());
            return convertView;
        }

        private static class ViewHolder {
            TextView name;
            TextView code;
        }
    }
}
