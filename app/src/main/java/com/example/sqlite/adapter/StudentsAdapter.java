package com.example.sqlite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sqlite.R;
import com.example.sqlite.entity.Students;

import java.util.List;

public class StudentsAdapter extends BaseAdapter {
    private List<Students> students;

    public StudentsAdapter(List<Students> students){
        this.students=students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_students,parent,false);
            holder=new ViewHolder();
            holder.tv_name=convertView.findViewById(R.id.tv_name);
            holder.tv_class=convertView.findViewById(R.id.tv_class);
            holder.tv_age=convertView.findViewById(R.id.tv_age);
            convertView.setTag(holder);
        }
        else{
            holder= (ViewHolder) convertView.getTag();
        }
        Students students1=students.get(position);
        holder.tv_name.setText(String.valueOf(students1.getStudents_name()));
        holder.tv_class.setText(String.valueOf(students1.getStudents_class()));
        holder.tv_age.setText(String.valueOf(students1.getStudents_age()));
        return convertView;



    }
    static class ViewHolder{
        TextView tv_name;
        TextView tv_class;
        TextView tv_age;

    }
}
