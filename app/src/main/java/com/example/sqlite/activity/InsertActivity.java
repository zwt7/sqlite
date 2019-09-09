package com.example.sqlite.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sqlite.R;
import com.example.sqlite.entity.Students;
import com.example.sqlite.service.StudentsService;
import com.example.sqlite.service.StudentsServiceImpl;

import java.util.Arrays;
import java.util.List;

public class InsertActivity extends AppCompatActivity implements View.OnClickListener {
    //声明控件对象
    private EditText et_name;
    private Spinner  sp_class;
    private EditText et_age;
    private Button define,cancel;
    private List<String> class_array;
    private StudentsService studentsService;
    private Students studentsObj;
    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        studentsService=new StudentsServiceImpl(this);

        initView();
        initData();


    }

    private void initData() {
        Intent intent=getIntent();
        flag=intent.getStringExtra("flag");

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            studentsObj = (Students) bundle.getSerializable("studentsObj");
            if (studentsObj != null) {
                et_name.setText(studentsObj.getStudents_name());
                et_name.setEnabled(false);
                sp_class.setSelection(class_array.indexOf(studentsObj.getStudents_class()),true);
                et_age.setText(studentsObj.getStudents_age());

            }
        }


    }

    private void initView() {
        //找到这些控件
        et_name=findViewById(R.id.et_name);
        sp_class=findViewById(R.id.sp_class);
        et_age=findViewById(R.id.et_age);

        define=findViewById(R.id.bt_define);
        cancel=findViewById(R.id.bt_cancel);


        define.setOnClickListener(this);
        cancel.setOnClickListener(this);

        //将String里面的spinner选项加入到里面
        class_array= Arrays.asList(getResources().getStringArray(R.array.class_array));
        sp_class.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                class_array));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_define:
                updateStudents();
                break;
            case R.id.bt_cancel:
               finish();
        }
    }

    private void updateStudents() {
        if(studentsObj==null){
            studentsObj=new Students();
        }
        studentsObj.setStudents_name(et_name.getText().toString());
        studentsObj.setStudents_class((String) sp_class.getSelectedItem());
        studentsObj.setStudents_age(Integer.valueOf(et_age.getText().toString()));
        if("修改".equals(flag)){
            studentsService.modifyStu(studentsObj);
        }
        else if("添加".equals(flag)){
            studentsService.insert(studentsObj);
        }

        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        intent.putExtra("studentsObj",studentsObj);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK,intent);
        finish();

    }

}
