package sg.edu.np.mad.madpractical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ListViewHolder extends RecyclerView.ViewHolder{
    TextView descTxt;
    TextView nameTxt;
    ImageView profilePicImg;
    public ListViewHolder(View itemView){
        super(itemView);
        descTxt = itemView.findViewById(R.id.description_recycler);
        nameTxt = itemView.findViewById(R.id.name_recycler);
        profilePicImg = itemView.findViewById(R.id.img_profile);
    }
}