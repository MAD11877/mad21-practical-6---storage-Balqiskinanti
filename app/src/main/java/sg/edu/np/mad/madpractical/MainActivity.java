package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "Main Activity";
    User user;
    TextView nameTxt;
    Button followBtn;
    MyDBHandler db= new MyDBHandler(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = getCurrentUser();
        initUserDisplay();
        setButton();
        onFollowClick();
        Log.v(TAG, "Create");
    }
    private User getCurrentUser(){
        Intent receivingEnd = getIntent();
        int id = receivingEnd.getIntExtra("_id",1);
        User currentUser = db.getUsers().get(id);
        return currentUser;
    }
    private void initUserDisplay(){
        nameTxt = findViewById(R.id.name);
        TextView descTxt = findViewById(R.id.description);
        nameTxt.setText(user.getName());
        descTxt.setText(user.getDescription());
    }
    private void setButton(){
        followBtn = findViewById(R.id.btnFollow);
        if(user.isFollowed()){
            followBtn.setText("Unfollow");
        }
        else{
            followBtn.setText("Follow");
        }
    }
    private void onFollowClick(){
        followBtn = findViewById(R.id.btnFollow);
        followBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(user.isFollowed()) {
                    user.setFollowed(false);
                    followBtn.setText("Follow");
                    db.updateUser(user);
                    Toast.makeText(getApplicationContext(),"Unfollowed",Toast.LENGTH_SHORT).show();
                }
                else{
                    user.setFollowed(true);
                    followBtn.setText("Unfollow");
                    db.updateUser(user);
                    Toast.makeText(getApplicationContext(),"Followed", Toast.LENGTH_SHORT).show();
                }

            }
        });
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