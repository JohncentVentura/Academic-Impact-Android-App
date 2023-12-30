package com.example.ccs322lacademicimpact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivitiesActivity extends AppCompatActivity {

    EditText txtActivitiesAdminCode;
    Button btnActivitiesAdminEnter;
    ImageButton ibtnActivitiesBack;
    ListView listviewActivities;

    SQLClasses sqlClasses = new SQLClasses(this);
    SQLActivities sqlActivities = new SQLActivities(this);
    IconDataAdapter iconAdapter;
    String adminCode = "ccs322";
    int uid, classId;
    ArrayList<Integer> classIds = new ArrayList<Integer>();
    boolean isRadioOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        txtActivitiesAdminCode = (EditText) findViewById(R.id.txtActivitiesAdminCode);
        btnActivitiesAdminEnter = (Button) findViewById(R.id.btnActivitiesAdminEnter);
        ibtnActivitiesBack = (ImageButton) findViewById(R.id.ibtnActivitiesBack);
        listviewActivities = (ListView) findViewById(R.id.listviewActivities);

        uid = getIntent().getExtras().getInt("uid");

        btnActivitiesAdminEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtActivitiesAdminCode.getText().toString().equals(adminCode)){
                    Intent intentAct2 = new Intent(ActivitiesActivity.this, Activities2Activity.class);
                    intentAct2.putExtra("uid", uid);
                    startActivity(intentAct2);
                }
                else{
                    Toast.makeText(ActivitiesActivity.this, "Invalid Admin Code", Toast.LENGTH_SHORT).show();
                    txtActivitiesAdminCode.setText("");
                }
            }
        });

        ibtnActivitiesBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMenu = new Intent(ActivitiesActivity.this, MenuActivity.class);
                intentMenu.putExtra("uid", uid);
                startActivity(intentMenu);
            }
        });

        fetchData();
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

        iconAdapter = new IconDataAdapter(ActivitiesActivity.this, R.layout.layout_activities, iconDlist);
        listviewActivities.setAdapter(iconAdapter);
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
}