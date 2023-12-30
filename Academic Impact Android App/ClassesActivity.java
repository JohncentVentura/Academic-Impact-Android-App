package com.example.ccs322lacademicimpact;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class ClassesActivity extends AppCompatActivity {

    ImageButton ibtnClassesAdd, ibtnClassesSave, ibtnClassesEdit, ibtnClassesDelete, ibtnClassesBack;
    EditText txtClassesAdminCode, txtClassesID, txtClassesName, txtClassesInstructor, txtClassesTime;
    Button btnClassesIDFind, btnClassesAdminEnter;
    ToggleButton tbtnClassesMeridiem;
    ImageView imgClassesAvatar;
    SeekBar sbClassesImage;
    ListView listviewClasses;

    SQLClasses sqlClasses = new SQLClasses(this);
    IconDataAdapter iconAdapter;
    String adminCode = "ccs322", meridiem = "AM", prelimGrade, midtermGrade, semiFinalGrade, finalGrade;
    int uid, image, imageCount = 0;
    boolean isMeridiemOn = false;

    int[] avatarsArray = {R.drawable.avataraether, R.drawable.avataralbedo, R.drawable.avataraloy, R.drawable.avataramber, R.drawable.avatararatakiitto, R.drawable.avatarbarbara,
            R.drawable.avatarbeidou, R.drawable.avatarbennett, R.drawable.avatarchongyun, R.drawable.avatardiluc, R.drawable.avatardiona, R.drawable.avatareula, R.drawable.avatarfischl,
            R.drawable.avatarganyu, R.drawable.avatargorou, R.drawable.avatarhutao, R.drawable.avatarjean, R.drawable.avatarkaedeharakazuha, R.drawable.avatarkaeya,
            R.drawable.avatarkamisatoayaka, R.drawable.avatarkeqing, R.drawable.avatarklee, R.drawable.avatarkujousara, R.drawable.avatarlisa, R.drawable.avatarlumine, R.drawable.avatarmona,
            R.drawable.avatarningguang, R.drawable.avatarnoelle, R.drawable.avatarqiqi, R.drawable.avatarraiden, R.drawable.avatarrazor, R.drawable.avatarrosaria,
            R.drawable.avatarsangonomiyakokomi, R.drawable.avatarsayu, R.drawable.avatarshenhe, R.drawable.avatarsucrose, R.drawable.avatartartaglia, R.drawable.avatarthoma,
            R.drawable.avatarventi, R.drawable.avatarxiangling, R.drawable.avatarxiao, R.drawable.avatarxingqiu, R.drawable.avatarxinyan, R.drawable.avataryaemiko, R.drawable.avataryanfei,
            R.drawable.avataryoimiya, R.drawable.avataryunjin, R.drawable.avatarzhongli};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        ibtnClassesAdd = (ImageButton) findViewById(R.id.ibtnClassesAdd);
        ibtnClassesSave  = (ImageButton) findViewById(R.id.ibtnClassesSave);
        ibtnClassesEdit  = (ImageButton) findViewById(R.id.ibtnClassesEdit);
        ibtnClassesDelete  = (ImageButton) findViewById(R.id.ibtnClassesDelete);
        ibtnClassesBack  = (ImageButton) findViewById(R.id.ibtnClassesBack);
        txtClassesAdminCode = (EditText) findViewById(R.id.txtClassesAdminCode);
        txtClassesID = (EditText) findViewById(R.id.txtClassesID);
        txtClassesName = (EditText) findViewById(R.id.txtClassesName);
        txtClassesInstructor = (EditText) findViewById(R.id.txtClassesInstructor);
        txtClassesTime = (EditText) findViewById(R.id.txtClassesTime);
        btnClassesIDFind = (Button)findViewById(R.id.btnClassesIDFind);
        btnClassesAdminEnter = (Button)findViewById(R.id.btnClassesAdminEnter);
        tbtnClassesMeridiem = (ToggleButton) findViewById(R.id.tbtnClassesMeridiem);
        imgClassesAvatar = (ImageView) findViewById(R.id.imgClassesAvatar);
        sbClassesImage = (SeekBar) findViewById(R.id.sbClassesImage);
        listviewClasses = (ListView) findViewById(R.id.listviewClasses);

        uid = getIntent().getExtras().getInt("uid");
        imageCount = 0;
        imgClassesAvatar.setBackgroundResource(avatarsArray[imageCount]);
        sbClassesImage.setMax(480);

        BtnClick btnClick = new BtnClick();
        ibtnClassesAdd.setOnClickListener(btnClick);
        ibtnClassesSave.setOnClickListener(btnClick);
        ibtnClassesEdit.setOnClickListener(btnClick);
        ibtnClassesDelete.setOnClickListener(btnClick);
        ibtnClassesBack.setOnClickListener(btnClick);
        btnClassesIDFind.setOnClickListener(btnClick);
        btnClassesAdminEnter.setOnClickListener(btnClick);
        tbtnClassesMeridiem.setOnClickListener(btnClick);

        //Only true is txtClassesAdminCode, btnClassesAdminEnter, ibtnClassesBack, imgClassesAvatar, btnClassesNext, listviewClasses
        ibtnClassesAdd.setEnabled(false);
        ibtnClassesSave.setEnabled(false);
        ibtnClassesEdit.setEnabled(false);
        ibtnClassesDelete.setEnabled(false);
        txtClassesID.setEnabled(false);
        txtClassesName.setEnabled(false);
        txtClassesInstructor.setEnabled(false);
        txtClassesTime.setEnabled(false);
        btnClassesIDFind.setEnabled(false);
        tbtnClassesMeridiem.setEnabled(false);

        sbClassesImage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                for(int index = 1; index < avatarsArray.length + 1; index++) {
                    if (i <= (10 * index)) {
                        imageCount = index - 1;
                        break;
                    }
                }

                imgClassesAvatar.setBackgroundResource(avatarsArray[imageCount]);
                image = avatarsArray[imageCount];
                System.out.println("Character: "+avatarsArray[imageCount]); //For Debugging
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        fetchData();
    }

    private class BtnClick implements View.OnClickListener {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnClassesAdminEnter:
                    if(txtClassesAdminCode.getText().toString().equals(adminCode)){
                        ibtnClassesAdd.setEnabled(true);
                        ibtnClassesEdit.setEnabled(true);
                        ibtnClassesDelete.setEnabled(true);
                        txtClassesID.setEnabled(true);
                        btnClassesIDFind.setEnabled(true);
                    }
                    else{
                        Toast.makeText(ClassesActivity.this, "Invalid Admin Code", Toast.LENGTH_SHORT).show();
                        txtClassesAdminCode.setText("");
                    }
                break;

                case R.id.btnClassesIDFind:
                    try{
                        if(txtClassesID.getText().toString().equals(sqlClasses.findRecord(sqlClasses, txtClassesID.getText().toString()).get(0).getCid()+"")){
                            txtClassesName.setEnabled(true);
                            txtClassesInstructor.setEnabled(true);
                            txtClassesTime.setEnabled(true);
                            tbtnClassesMeridiem.setEnabled(true);

                            sqlClasses.findRecord(sqlClasses, txtClassesID.getText().toString());
                            txtClassesName.setText(sqlClasses.findRecord(sqlClasses, txtClassesID.getText().toString()).get(0).getName());
                            txtClassesInstructor.setText(sqlClasses.findRecord(sqlClasses, txtClassesID.getText().toString()).get(0).getInstructor());
                            txtClassesTime.setText(sqlClasses.findRecord(sqlClasses, txtClassesID.getText().toString()).get(0).getTime());

                            if(sqlClasses.findRecord(sqlClasses, txtClassesID.getText().toString()).get(0).getTime().toString().contains("AM")){
                                meridiem = "AM";
                                tbtnClassesMeridiem.setChecked(false);
                            }
                            else if(sqlClasses.findRecord(sqlClasses, txtClassesID.getText().toString()).get(0).getTime().toString().contains("PM")) {
                                meridiem = "PM";
                                tbtnClassesMeridiem.setChecked(true);
                            }

                            //Related to Edit Class
                            String tempStr = "";
                            if(txtClassesTime.getText().toString().contains(" AM")){
                                tempStr = txtClassesTime.getText().toString().replace(" AM", "");
                                txtClassesTime.setText(tempStr);
                            }
                            else if(txtClassesTime.getText().toString().contains(" PM")){
                                tempStr = txtClassesTime.getText().toString().replace(" PM", "");
                                txtClassesTime.setText(tempStr);
                            }
                            System.out.println("tempStr: "+tempStr); //For Debugging
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(ClassesActivity.this, "Invalid Class ID", Toast.LENGTH_SHORT).show();
                        txtClassesID.setText("");
                    }
                break;

                case R.id.ibtnClassesAdd:
                    ibtnClassesSave.setEnabled(true);
                    ibtnClassesEdit.setEnabled(false);
                    ibtnClassesDelete.setEnabled(false);
                    btnClassesIDFind.setEnabled(false);
                    txtClassesName.setEnabled(true);
                    txtClassesInstructor.setEnabled(true);
                    txtClassesTime.setEnabled(true);
                    tbtnClassesMeridiem.setEnabled(true);
                break;

                case R.id.ibtnClassesSave:
                    String timeStr = txtClassesTime.getText().toString()+" "+meridiem;
                    prelimGrade = "0";
                    midtermGrade = "0";
                    semiFinalGrade = "0";
                    finalGrade = "0";

                    sqlClasses.insertRecord(sqlClasses, txtClassesID.getText().toString(), String.valueOf(image), txtClassesName.getText().toString(),
                            txtClassesInstructor.getText().toString(), timeStr, prelimGrade, midtermGrade, semiFinalGrade, finalGrade);

                    LayoutClasses objNew = new LayoutClasses();
                    objNew.setLayoutClassesImage(image);
                    objNew.setLayoutClassesID(txtClassesID.getText().toString());
                    objNew.setLayoutClassesName(txtClassesName.getText().toString());
                    objNew.setLayoutClassesInstructor(txtClassesInstructor.getText().toString());
                    objNew.setLayoutClassesTime(txtClassesTime.getText().toString());
                    iconAdapter.add(objNew);
                    iconAdapter.notifyDataSetChanged();

                    ibtnClassesSave.setEnabled(false);
                    ibtnClassesEdit.setEnabled(true);
                    ibtnClassesDelete.setEnabled(true);
                    btnClassesIDFind.setEnabled(true);
                    txtClassesName.setEnabled(false);
                    txtClassesInstructor.setEnabled(false);
                    txtClassesTime.setEnabled(false);
                    tbtnClassesMeridiem.setEnabled(false);

                    txtClassesID.setText("");
                    txtClassesName.setText("");
                    txtClassesInstructor.setText("");
                    txtClassesTime.setText("");
                    tbtnClassesMeridiem.setChecked(false);
                    fetchData();
                break;

                case R.id.ibtnClassesEdit:
                    String timeStr2 = txtClassesTime.getText().toString()+" "+meridiem;
                    prelimGrade = "0";
                    midtermGrade = "0";
                    semiFinalGrade = "0";
                    finalGrade = "0";

                    sqlClasses.updateRecord(sqlClasses, txtClassesID.getText().toString(), String.valueOf(image), txtClassesName.getText().toString(),
                            txtClassesInstructor.getText().toString(), timeStr2, prelimGrade, midtermGrade, semiFinalGrade, finalGrade);

                    txtClassesID.setText("");
                    txtClassesName.setText("");
                    txtClassesInstructor.setText("");
                    txtClassesTime.setText("");
                    tbtnClassesMeridiem.setChecked(false);

                    fetchData();
                break;

                case R.id.ibtnClassesDelete:
                    sqlClasses.deleteRecord(sqlClasses, txtClassesID.getText().toString());

                    txtClassesName.setEnabled(false);
                    txtClassesInstructor.setEnabled(false);
                    txtClassesTime.setEnabled(false);
                    tbtnClassesMeridiem.setEnabled(false);

                    txtClassesID.setText("");
                    txtClassesName.setText("");
                    txtClassesInstructor.setText("");
                    txtClassesTime.setText("");
                    tbtnClassesMeridiem.setChecked(false);

                    fetchData();
                break;

                case R.id.tbtnClassesMeridiem:
                    if(isMeridiemOn){
                        meridiem = "AM";
                        isMeridiemOn = false;
                    }
                    else{
                        meridiem = "PM";
                        isMeridiemOn = true;
                    }
                    System.out.println("meridiem: "+meridiem); //For Debugging
                break;

                case R.id.ibtnClassesBack:
                    Intent intentClasses = new Intent(ClassesActivity.this, MenuActivity.class);
                    intentClasses.putExtra("uid", uid);
                    startActivity(intentClasses);
                break;
            }
        }
    }

    private void fetchData(){
        ArrayList<DBClasses> list = sqlClasses.getAllRecords(sqlClasses);
        ArrayList<LayoutClasses> iconDlist = new ArrayList<LayoutClasses>();
        LayoutClasses layoutClass;
        iconDlist.clear();

        for (int i = 0; i < list.size(); i++){
            layoutClass = new LayoutClasses();
            layoutClass.setLayoutClassesID(sqlClasses.getAllRecords(sqlClasses).get(i).getCid()+"");
            layoutClass.setLayoutClassesImage(sqlClasses.getAllRecords(sqlClasses).get(i).getImage());
            layoutClass.setLayoutClassesName(sqlClasses.getAllRecords(sqlClasses).get(i).getName());
            layoutClass.setLayoutClassesInstructor(sqlClasses.getAllRecords(sqlClasses).get(i).getInstructor());
            layoutClass.setLayoutClassesTime(sqlClasses.getAllRecords(sqlClasses).get(i).getTime());
            iconDlist.add(layoutClass);
        }

        iconAdapter = new IconDataAdapter(ClassesActivity.this, R.layout.layout_classes, iconDlist);
        listviewClasses.setAdapter(iconAdapter);
        ((ArrayAdapter<?>) iconAdapter).notifyDataSetChanged();
    }

    private class IconDataAdapter extends ArrayAdapter<LayoutClasses>{
        private ArrayList<LayoutClasses> iconList;
        int layoutresID;

        public IconDataAdapter(Context context, int resourceLayoutID, ArrayList<LayoutClasses> listObj) {
            super(context, resourceLayoutID, listObj);
            this.layoutresID = resourceLayoutID;
            this.iconList = new ArrayList<LayoutClasses>();
            this.iconList.addAll(listObj);
        }

        @Override
        public void add(LayoutClasses object) {
            super.add(object);
            iconList.add(object);
        }

        public void addAll(ArrayList<LayoutClasses> obj) {
            iconList.clear();
            iconList.addAll(obj);
        }

        @Override
        public void remove(LayoutClasses object) {
            super.remove(object);
            iconList.remove(object);
        }

        private class ViewHolder{
            ImageView layoutClassesImage;
            TextView layoutClassesId;
            TextView layoutClassesName;
            TextView layoutClassesInstructor;
            TextView layoutClassesTime;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            ViewHolder holder = null;
            View view = convertView;

            if(convertView == null){
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(layoutresID, null);

                holder = new ViewHolder();
                holder.layoutClassesImage = (ImageView) view.findViewById(R.id.imgLayoutClassesImage);
                holder.layoutClassesId = (TextView) view.findViewById(R.id.tvLayoutClassesId);
                holder.layoutClassesName = (TextView) view.findViewById(R.id.tvLayoutClassesName);
                holder.layoutClassesInstructor = (TextView) view.findViewById(R.id.tvLayoutClassesInstructor);
                holder.layoutClassesTime = (TextView) view.findViewById(R.id.tvLayoutClassesTime);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            try {
                LayoutClasses iconObj = iconList.get(position);
                holder.layoutClassesImage.setBackgroundResource(iconObj.getLayoutClassesImage());
                holder.layoutClassesId.setText(iconObj.getLayoutClassesID());
                holder.layoutClassesName.setText(iconObj.getLayoutClassesName());
                holder.layoutClassesInstructor.setText(iconObj.getLayoutClassesInstructor());
                holder.layoutClassesTime.setText(iconObj.getLayoutClassesTime());
            } catch (Exception e){
                e.printStackTrace();
            }
            return view;
        }
    }
}