package com.example.ccs322lacademicimpact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    ImageButton ibtnMenuClasses, ibtnMenuActivities, ibtnMenuGrades, ibtnMenuSettings, ibtnMenuBack;
    ListView listviewMenuClasses;

    SQLAccounts sqlAccounts = new SQLAccounts(this);
    SQLClasses sqlClasses = new SQLClasses(this);
    IconDataAdapter iconAdapter;
    int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        ibtnMenuClasses = (ImageButton) findViewById(R.id.ibtnMenuClasses);
        ibtnMenuActivities = (ImageButton) findViewById(R.id.ibtnMenuActivities);
        ibtnMenuSettings = (ImageButton) findViewById(R.id.ibtnMenuSettings);
        ibtnMenuGrades = (ImageButton) findViewById(R.id.ibtnMenuGrades);
        ibtnMenuBack = (ImageButton) findViewById(R.id.ibtnMenuBack);
        listviewMenuClasses = (ListView) findViewById(R.id.listviewMenuClasses);

        uid = getIntent().getExtras().getInt("uid");
        LoginActivity.printAccountDetails(sqlAccounts, uid); //For Debugging
        constraintLayout.setBackgroundResource(sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getImage());

        BtnClick btnClick = new BtnClick();
        ibtnMenuClasses.setOnClickListener(btnClick);
        ibtnMenuActivities.setOnClickListener(btnClick);
        ibtnMenuGrades.setOnClickListener(btnClick);
        ibtnMenuSettings.setOnClickListener(btnClick);
        ibtnMenuBack.setOnClickListener(btnClick);

        fetchData();
    }

    private class BtnClick implements View.OnClickListener {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ibtnMenuClasses:
                    Intent intentClasses = new Intent(MenuActivity.this, ClassesActivity.class);
                    intentClasses.putExtra("uid", uid);
                    startActivity(intentClasses);
                break;

                case R.id.ibtnMenuActivities:
                    Intent intentActivities = new Intent(MenuActivity.this, ActivitiesActivity.class);
                    intentActivities.putExtra("uid", uid);
                    startActivity(intentActivities);
                break;

                case R.id.ibtnMenuGrades:
                    Intent intentGrades = new Intent(MenuActivity.this, GradesActivity.class);
                    intentGrades.putExtra("uid", uid);
                    startActivity(intentGrades);
                break;

                case R.id.ibtnMenuSettings:
                    Intent intentSettings = new Intent(MenuActivity.this, SettingsActivity.class);
                    intentSettings.putExtra("uid", uid);
                    startActivity(intentSettings);
                break;

                case R.id.ibtnMenuBack:
                    uid = 0;
                    Intent intentBack = new Intent(MenuActivity.this, LoginActivity.class);
                    intentBack.putExtra("uid", uid);
                    startActivity(intentBack);
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

        iconAdapter = new IconDataAdapter(this, R.layout.layout_classes, iconDlist);
        listviewMenuClasses.setAdapter(iconAdapter);
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
            IconDataAdapter.ViewHolder holder = null;
            View view = convertView;

            if(convertView == null){
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(layoutresID, null);

                holder = new IconDataAdapter.ViewHolder();
                holder.layoutClassesImage = (ImageView) view.findViewById(R.id.imgLayoutClassesImage);
                holder.layoutClassesId = (TextView) view.findViewById(R.id.tvLayoutClassesId);
                holder.layoutClassesName = (TextView) view.findViewById(R.id.tvLayoutClassesName);
                holder.layoutClassesInstructor = (TextView) view.findViewById(R.id.tvLayoutClassesInstructor);
                holder.layoutClassesTime = (TextView) view.findViewById(R.id.tvLayoutClassesTime);
                view.setTag(holder);

            } else {
                holder = (IconDataAdapter.ViewHolder) view.getTag();
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

