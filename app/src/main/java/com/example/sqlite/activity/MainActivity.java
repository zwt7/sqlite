package com.example.sqlite.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.sqlite.R;
import com.example.sqlite.adapter.StudentsAdapter;
import com.example.sqlite.entity.Students;
import com.example.sqlite.service.StudentsService;
import com.example.sqlite.service.StudentsServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_REQUEST=100;
    private static final int MODIFY_REQUEST=101;
    //声明控件
    private ListView list;
    private Button Add,Modify,Delete;

    private StudentsAdapter studentsAdapter;
    private List<Students> students;

    private StudentsService studentsService;
    private Students selectedStu;

    private int selectedPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找到控件对象
        //初始化listView

        initView();
        initData();
    }

    private void initData() {
        studentsService=new StudentsServiceImpl(this);
        students=studentsService.getAllStudents();
        if(students==null){
            students=new ArrayList<>();
        }
    }

    private void initView() {
        list=findViewById(R.id.list);
        Add=findViewById(R.id.bt_add);
        Modify=findViewById(R.id.bt_modify);
        Delete=findViewById(R.id.bt_delete);

        studentsAdapter=new StudentsAdapter(students);
        list.setAdapter(studentsAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                selectedPos=position;
                selectedStu= (Students) parent.getItemAtPosition(position);

                //修改
                Modify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(MainActivity.this,InsertActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("studentsObj",selectedStu);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,MODIFY_REQUEST);
                    }
                });

                //删除

                Delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        studentsService.delete(selectedStu.getStudents_name());
                        students.remove(position);
                        studentsAdapter.notifyDataSetChanged();

                    }
                });
            }
        });
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivityForResult(intent, ADD_REQUEST);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        if (data!=null){
            Bundle bundle=data.getExtras();
            if(bundle==null){
                return;
            }
            selectedStu= (Students) bundle.get("studentsObj");
            if(requestCode==ADD_REQUEST){
                students.add(selectedStu);
            }
            else if(requestCode==MODIFY_REQUEST){
                students.set(selectedPos,selectedStu);
            }
            //刷新ListView
            studentsAdapter.notifyDataSetChanged();
        }
    }
}
