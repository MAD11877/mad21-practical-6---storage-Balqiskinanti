package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    private final static String TAG = "List Activity";
    //ArrayList<User>  userArray = new ArrayList<User>();
    MyDBHandler db= new MyDBHandler(ListActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.v(TAG,Long.toString(db.getRowCount()));
        if(db.getRowCount() < 20){
            db.addUser();
        }
        initRecyclerView();
        Log.v(TAG, "Create");
    }
    private void initRecyclerView(){
        RecyclerView listRecyclerView = findViewById(R.id.rv);
        ListAdapter listAdapter = new ListAdapter(ListActivity.this,db.getUsers());

        LinearLayoutManager listLayoutManager = new LinearLayoutManager(this);
        listRecyclerView.setLayoutManager(listLayoutManager);
        listRecyclerView.setItemAnimator(new DefaultItemAnimator());
        listRecyclerView.setAdapter(listAdapter);
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.v(TAG, "Start");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "Resume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "Pause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "Stop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "Destroy");
    }
}