package com.subrata.retrofit_2021_with_email.adapter;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.subrata.retrofit_2021_with_email.MainActivity;
import com.subrata.retrofit_2021_with_email.R;
import com.subrata.retrofit_2021_with_email.model.DataModel;

import java.util.List;



public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {


    private final List<DataModel> mList;
    private Context ctx;


    public  AdapterData (Context ctx, List<DataModel> mList)
    {
        this.ctx = ctx;
        this.mList = mList;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlist,parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        DataModel dm = mList.get(position);
        holder.name1.setText(dm.getName());
        holder.position1.setText(dm.getPosition());
        holder.salary.setText(dm.getSalary());
        holder.email.setText(dm.getEmail());
       // holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder{
        TextView name1, position1, salary,email;
        DataModel dm;
        public HolderData (View v)
        {
            super(v);

            name1  = (TextView)v.findViewById(R.id.stvNama);
            position1 = (TextView)v.findViewById(R.id.stvPos);


            salary = (TextView)v.findViewById(R.id.stvsalary);
            email=(TextView)v.findViewById(R.id.stvemail);

          /*  v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goInput = new Intent(ctx, MainActivity.class);
                    goInput.putExtra("id", dm.getId());
                    goInput.putExtra("name", dm.getName());
                    goInput.putExtra("position", dm.getPosition());
                    goInput.putExtra("salary", dm.getSalary());
                    goInput.putExtra("email", dm.getEmail());


                    ctx.startActivity(goInput);
                }
            });*/
        }
    }
}
