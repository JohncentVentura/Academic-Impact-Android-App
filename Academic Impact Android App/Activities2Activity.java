package com.example.ccs322lacademicimpact;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Activities2Activity extends AppCompatActivity {

    ImageButton ibtnActivities2Add, ibtnActivities2Save, ibtnActivities2Edit, ibtnActivities2Del, ibtnActivities2Back;
    Button btnActivities2Find;
    EditText txtActivities2ID, txtActivities2Name, txtActivities2Grade, txtActivities2Desc;
    ImageView imgActivities2;
    SeekBar sbActivities2;
    Spinner spinnerActivities2;
    ListView listviewActivities2;

    SQLClasses sqlClasses = new SQLClasses(this);
    SQLActivities sqlActivities = new SQLActivities(this);
    IconDataAdapter iconAdapter;
    int uid, classId, image, imageCount = 0;
    int[] elements = {R.drawable.elementanemo, R.drawable.elementcryo, R.drawable.elementgeo, R.drawable.elementdendro,
            R.drawable.elementhydro, R.drawable.elementelectro, R.drawable.elementpyro};
    String answer = "Enter Answer";
    List<Integer> classIds = new ArrayList<Integer>();
    List<String> classNames = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities2);

        ibtnActivities2Add = (ImageButton) findViewById(R.id.ibtnActivities2Add);
        ibtnActivities2Save = (ImageButton) findViewById(R.id.ibtnActivities2Save);
        ibtnActivities2Edit = (ImageButton) findViewById(R.id.ibtnActivities2Edit);
        ibtnActivities2Del = (ImageButton) findViewById(R.id.ibtnActivities2Del);
        ibtnActivities2Back = (ImageButton) findViewById(R.id.ibtnActivities2Back);
        btnActivities2Find = (Button) findViewById(R.id.btnActivities2Find);
        txtActivities2ID = (EditText) findViewById(R.id.txtActivities2ID);
        txtActivities2Name = (EditText) findViewById(R.id.txtActivities2Name);
        txtActivities2Grade = (EditText) findViewById(R.id.txtActivities2Grade);
        txtActivities2Desc = (EditText) findViewById(R.id.txtActivities2Desc);
        imgActivities2 = (ImageView) findViewById(R.id.imgActivities2);
        sbActivities2 = (SeekBar) findViewById(R.id.sbActivities2);
        spinnerActivities2 = (Spinner) findViewById(R.id.spinnerActivities2);
        listviewActivities2 = (ListView) findViewById(R.id.listviewActivities2);

        uid = getIntent().getExtras().getInt("uid");
        image = elements[imageCount];
        setSpinnerClasses();

        BtnClick btnClick = new BtnClick();
        ibtnActivities2Add.setOnClickListener(btnClick);
        ibtnActivities2Save.setOnClickListener(btnClick);
        ibtnActivities2Edit.setOnClickListener(btnClick);
        ibtnActivities2Del.setOnClickListener(btnClick);
        ibtnActivities2Back.setOnClickListener(btnClick);
        btnActivities2Find.setOnClickListener(btnClick);

        ibtnActivities2Save.setEnabled(false);
        txtActivities2Name.setEnabled(false);
        txtActivities2Grade.setEnabled(false);
        spinnerActivities2.setEnabled(false);
        txtActivities2Desc.setEnabled(false);

        sbActivities2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i <= (14*1)){
                    imageCount = 0;
                    imgActivities2.setBackgroundResource(elements[imageCount]);
                    image = elements[imageCount];
                }
                else if(i <= (14*2)){
                    imageCount = 1;
                    imgActivities2.setBackgroundResource(elements[imageCount]);
                    image = elements[imageCount];
                }
                else if(i <= (14*3)){
                    imageCount = 2;
                    imgActivities2.setBackgroundResource(elements[imageCount]);
                    image = elements[imageCount];
                }
                else if(i <= (14*4)){
                    imageCount = 3;
                    imgActivities2.setBackgroundResource(elements[imageCount]);
                    image = elements[imageCount];
                }
                else if(i <= (14*5)){
                    imageCount = 4;
                    imgActivities2.setBackgroundResource(elements[imageCount]);
                    image = elements[imageCount];
                }
                else if(i <= (14*6)){
                    imageCount = 5;
                    imgActivities2.setBackgroundResource(elements[imageCount]);
                    image = elements[imageCount];
                }
                else if(i <= (14*7)){
                    imageCount = 6;
                    imgActivities2.setBackgroundResource(elements[imageCount]);
                    image = elements[imageCount];
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spinnerActivities2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                classId = classIds.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fetchData();
    }

    private class BtnClick implements View.OnClickListener {
        @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case  R.id.btnActivities2Find:
                    txtActivities2Name.setEnabled(true);
                    txtActivities2Grade.setEnabled(true);
                    spinnerActivities2.setEnabled(true);
                    txtActivities2Desc.setEnabled(true);

                    sqlActivities.findRecord(sqlActivities, txtActivities2ID.getText().toString());
                    txtActivities2Name.setText(sqlActivities.findRecord(sqlActivities, txtActivities2ID.getText().toString()).get(0).getName());
                    txtActivities2Grade.setText(sqlActivities.findRecord(sqlActivities, txtActivities2ID.getText().toString()).get(0).getGrade()+"");
                    txtActivities2Desc.setText(sqlActivities.findRecord(sqlActivities, txtActivities2ID.getText().toString()).get(0).getDescription());
                    imgActivities2.setBackgroundResource(sqlActivities.findRecord(sqlActivities, txtActivities2ID.getText().toString()).get(0).getImage());
                    classId = sqlActivities.findRecord(sqlActivities, txtActivities2ID.getText().toString()).get(0).getCid();
                break;

                case R.id.ibtnActivities2Add:
                    ibtnActivities2Save.setEnabled(true);
                    txtActivities2Name.setEnabled(true);
                    txtActivities2Grade.setEnabled(true);
                    spinnerActivities2.setEnabled(true);
                    txtActivities2Desc.setEnabled(true);
                break;

                case R.id.ibtnActivities2Save:
                    int tempGrade2 = Integer.parseInt(txtActivities2Grade.getText().toString());

                    sqlActivities.insertRecord(sqlActivities, txtActivities2ID.getText().toString(), String.valueOf(classId), String.valueOf(image),
                        txtActivities2Name.getText().toString(), txtActivities2Grade.getText().toString(), txtActivities2Desc.getText().toString(), answer);

                    LayoutActivities objNew = new LayoutActivities();
                    objNew.setLayoutActImage(image);
                    objNew.setLayoutActName(txtActivities2Name.getText().toString());
                    objNew.setLayoutActID(String.valueOf(classId));
                    objNew.setLayoutActGrade(tempGrade2);
                    objNew.setLayoutActDesc(txtActivities2Desc.getText().toString());
                    iconAdapter.add(objNew);
                    iconAdapter.notifyDataSetChanged();

                    btnActivities2Find.setEnabled(true);
                    ibtnActivities2Save.setEnabled(false);
                    txtActivities2Name.setEnabled(false);
                    txtActivities2Grade.setEnabled(false);
                    spinnerActivities2.setEnabled(false);
                    txtActivities2Desc.setEnabled(false);

                    txtActivities2ID.setText("");
                    txtActivities2Name.setText("");
                    txtActivities2Grade.setText("");
                    txtActivities2Desc.setText("");

                    //System.out.println("Class ID: "+sqlActivities.findRecord(sqlActivities, txtActivities2ID.getText().toString()).get(0).getCid());
                    fetchData();
                break;

                case R.id.ibtnActivities2Edit:
                    sqlActivities.updateRecord(sqlActivities, txtActivities2ID.getText().toString(), String.valueOf(classId), String.valueOf(image),
                            txtActivities2Name.getText().toString(), txtActivities2Grade.getText().toString(), txtActivities2Desc.getText().toString(), answer);

                    txtActivities2Name.setEnabled(false);
                    txtActivities2Grade.setEnabled(false);
                    spinnerActivities2.setEnabled(false);
                    txtActivities2Desc.setEnabled(false);

                    txtActivities2ID.setText("");
                    txtActivities2Name.setText("");
                    txtActivities2Grade.setText("");
                    txtActivities2Desc.setText("");

                    fetchData();
                break;

                case R.id.ibtnActivities2Del:
                    sqlActivities.deleteRecord(sqlActivities, txtActivities2ID.getText().toString());

                    txtActivities2Name.setEnabled(false);
                    txtActivities2Grade.setEnabled(false);
                    spinnerActivities2.setEnabled(false);
                    txtActivities2Desc.setEnabled(false);

                    txtActivities2ID.setText("");
                    txtActivities2Name.setText("");
                    txtActivities2Grade.setText("");
                    txtActivities2Desc.setText("");

                    fetchData();
                break;

                case R.id.ibtnActivities2Back:
                    Intent intent = new Intent(Activities2Activity.this, ActivitiesActivity.class);
                    intent.putExtra("uid", uid);
                    startActivity(intent);
                break;
            }
        }
    }

    private void fetchData(){
        ArrayList<DBActivities> list = sqlActivities.getAllRecords(sqlActivities);
        ArrayList<LayoutActivities> iconDlist = new ArrayList<LayoutActivities>();
        LayoutActivities layoutAct;
        iconDlist.clear();

        for (int i = 0; i < list.size(); i++){
            layoutAct = new LayoutActivities();
            layoutAct.setLayoutActID(sqlActivities.getAllRecords(sqlActivities).get(i).getAid()+"");
            layoutAct.setLayoutActClassID(sqlActivities.getAllRecords(sqlActivities).get(i).getCid()+"");
            layoutAct.setLayoutActImage(sqlActivities.getAllRecords(sqlActivities).get(i).getImage());
            layoutAct.setLayoutActName(sqlActivities.getAllRecords(sqlActivities).get(i).getName());
            layoutAct.setLayoutActGrade(sqlActivities.getAllRecords(sqlActivities).get(i).getGrade());
            layoutAct.setLayoutActDesc(sqlActivities.getAllRecords(sqlActivities).get(i).getDescription());
            layoutAct.setLayoutActAnswer(sqlActivities.getAllRecords(sqlActivities).get(i).getAnswer());
            iconDlist.add(layoutAct);
        }

        iconAdapter = new IconDataAdapter(Activities2Activity.this, R.layout.layout_activities, iconDlist);
        listviewActivities2.setAdapter(iconAdapter);
        ((ArrayAdapter<?>) iconAdapter).notifyDataSetChanged();
    }

    private class IconDataAdapter extends ArrayAdapter<LayoutActivities>{
        private ArrayList<LayoutActivities> iconList;
        int layoutresID;

        public IconDataAdapter(Context context, int resourceLayoutID, ArrayList<LayoutActivities> listObj) {
            super(context, resourceLayoutID, listObj);
            this.layoutresID = resourceLayoutID;
            this.iconList = new ArrayList<LayoutActivities>();
            this.iconList.addAll(listObj);
        }

        @Override
        public void add(LayoutActivities object) {
            super.add(object);
            iconList.add(object);
        }

        public void addAll(ArrayList<LayoutActivities> obj) {
            iconList.clear();
            iconList.addAll(obj);
        }

        @Override
        public void remove(LayoutActivities object) {
            super.remove(object);
            iconList.remove(object);
        }

        private class ViewHolder{
            ImageView imgLayoutActImage;
            TextView tvLayoutActId;
            TextView tvLayoutActName;
            TextView tvLayoutActClassId;
            TextView tvLayoutActGrade;
            TextView tvLayoutActDesc;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            IconDataAdapter.ViewHolder holder = null;
            View view = convertView;

            if(convertView == null){
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(layoutresID, null);
                holder = new IconDataAdapter.ViewHolder();
                holder.imgLayoutActImage = (ImageView) view.findViewById(R.id.imgLayoutActImage);
                holder.tvLayoutActId = (TextView) view.findViewById(R.id.tvLayoutActId);
                holder.tvLayoutActName = (TextView) view.findViewById(R.id.tvLayoutActName);
                holder.tvLayoutActClassId = (TextView) view.findViewById(R.id.tvLayoutActClassId);
                holder.tvLayoutActGrade = (TextView) view.findViewById(R.id.tvLayoutActGrade);
                holder.tvLayoutActDesc = (TextView) view.findViewById(R.id.tvLayoutActDesc);
                view.setTag(holder);
            } else {
                holder = (IconDataAdapter.ViewHolder) view.getTag();
            }
            try {
                LayoutActivities iconObj = iconList.get(position);
                holder.imgLayoutActImage.setBackgroundResource(iconObj.getLayoutActImage());
                holder.tvLayoutActId.setText(iconObj.getLayoutActID());
                holder.tvLayoutActName.setText(iconObj.getLayoutActName());
                holder.tvLayoutActClassId.setText(iconObj.getLayoutActClassID());
                holder.tvLayoutActGrade.setText(Integer.toString(iconObj.getLayoutActGrade()));
                holder.tvLayoutActDesc.setText(iconObj.getLayoutActDesc());
            } catch (Exception e){
                e.printStackTrace();
            }
            return view;
        }
    }

    private void setSpinnerClasses() {
        ArrayList<DBClasses> classList = sqlClasses.getAllRecords(sqlClasses);
        classIds.clear();
        for (int i = 0; i < classList.size(); i++){
            classIds.add(sqlClasses.getAllRecords(sqlClasses).get(i).getCid());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, classIds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActivities2.setAdapter(adapter);
    }


}