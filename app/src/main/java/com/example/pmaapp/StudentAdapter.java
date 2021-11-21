package com.example.pmaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmaapp.MyDataStorage;
import com.example.pmaapp.R;
import com.example.pmaapp.Student;

public class StudentAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final int HEADER = -1;
    final int STUDENT = -2;
    List<Object> dataList;

    public StudentAdapter(List<Object> myDataset) {
        dataList = myDataset;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup
                                                              viewGroup, int viewType) {
        // create a new view
        if(viewType == STUDENT)
        {
            View view = (View) LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.student_holder, viewGroup, false);
            return new StudentViewHolder(view);
        }
        else
        {
            View view = (View) LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.header_holder, viewGroup, false);
            return new HeaderViewHolder(view);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int
            position) {
        if(getItemViewType(position) == STUDENT)
        {
            StudentViewHolder studentViewHolder = (StudentViewHolder) viewHolder;
            studentViewHolder.tvName.setText(((Student) dataList.get(position)).getIme());
            studentViewHolder.tvPrezime.setText(((Student) dataList.get(position)).getPrezime());
            studentViewHolder.tvPredmet.setText(((Student) dataList.get(position)).getPredmet());
        }
        else
        {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
            headerViewHolder.tvNaslov.setText(dataList.get(position).toString());
        }
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if( dataList.get(position) instanceof Student )
        {
            return STUDENT;
        }
        else
        {
            return HEADER;
        }
    }
    class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvPrezime;
        TextView tvPredmet;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrezime = itemView.findViewById(R.id.tvPrezime);
            tvPredmet = itemView.findViewById(R.id.tvPredmet);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView tvNaslov;
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNaslov = itemView.findViewById(R.id.tvNaslov);
        }
    }
}