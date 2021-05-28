package sg.edu.np.mad.madpractical;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder>{
    ArrayList<User> data;
    private Context myContext;
    public ListAdapter(Context context, ArrayList<User> input){
        data = input;
        myContext = context;
    }

    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item;
        if(viewType == 0){
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recycler_view,parent,false);
        }
        else{
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recycler_view_2,parent,false);
        }
        return new ListViewHolder(item);
    }

    public void onBindViewHolder(ListViewHolder holder, int position) {
        User userData = data.get(position);
        holder.descTxt.setText(userData.getDescription());
        holder.nameTxt.setText(userData.getName());
        holder.profilePicImg.setOnClickListener(new View.OnClickListener() {
            /*@Override
            public void onClick(View v) {
                Intent viewMain = new Intent(myContext, MainActivity.class);
                myContext.startActivity(viewMain);
                // add bundle
            }*/
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
                builder.setTitle("Profile");
                builder.setMessage(userData.getName());
                builder.setCancelable(true);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Intent viewMain = new Intent(myContext, MainActivity.class);

                        Bundle extras = new Bundle();
                        extras.putString("Name", userData.getName());
                        extras.putString("Description", userData.getDescription());
                        extras.putBoolean("IsFollowed", userData.isFollowed());
                        viewMain.putExtras(extras);

                        myContext.startActivity(viewMain);
                    }
                });
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        ((ListActivity)myContext).finish();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public int getItemCount(){
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        User userData = data.get(position);
        if(userData.getName().charAt(userData.getName().length()-1) != '7'){
            return 0;
        }
        else{
            return 1;
        }
    }
}
