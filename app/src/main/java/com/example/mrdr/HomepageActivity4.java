package com.example.mrdr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.airbnb.lottie.LottieAnimationView;
import com.applozic.mobicommons.commons.core.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.kommunicate.KmConversationBuilder;
import io.kommunicate.Kommunicate;
import io.kommunicate.callbacks.KmCallback;


public class HomepageActivity4 extends AppCompatActivity {
      ExpandableListView expandableListView;
      List <String> listGroup;
      HashMap <String,List<String>> listItem;
      MainAdapter adapter;
      LottieAnimationView chatbot2;
      private View logouth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage4);
        Kommunicate.init(this, "2f93644cdc04e352bd0c8e534efd64c8c");
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Homepage");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        chatbot2 = findViewById(R.id.chatbot2);
        logouth = (View) findViewById(R.id.logouth);
        expandableListView = findViewById(R.id.expandable_listview);
        listGroup = new ArrayList<>();
        listItem = new HashMap<>();
        adapter = new MainAdapter(this,listGroup,listItem);
        expandableListView.setAdapter(adapter);
        initListData();

    }

    private void initListData() {
        listGroup.add(getString(R.string.clinicdetails));
        listGroup.add(getString(R.string.doctordetails));
        listGroup.add(getString(R.string.appointment));
        listGroup.add(getString(R.string.feedback));
        listGroup.add(getString(R.string.help));
        String[] array;
        List<String> list1 = new ArrayList<>();
        array = getResources().getStringArray(R.array.clinicdetails);
        for (String item : array){
            list1.add(item);
        }

        List<String> list2 = new ArrayList<>();
        array = getResources().getStringArray(R.array.doctordetails);
        for (String item : array){
            list2.add(item);
        }

        List<String> list3 = new ArrayList<>();
        array = getResources().getStringArray(R.array.appointment);
        for (String item : array){
            list3.add(item);
        }

        List<String> list4 = new ArrayList<>();
        array = getResources().getStringArray(R.array.feedback);
        for (String item : array){
            list4.add(item);
        }

        List<String> list5 = new ArrayList<>();
        array = getResources().getStringArray(R.array.help);
        for (String item : array){
            list5.add(item);
        }
        listItem.put(listGroup.get(0),list1);
        listItem.put(listGroup.get(1),list2);
        listItem.put(listGroup.get(2),list3);
        listItem.put(listGroup.get(3),list4);
        listItem.put(listGroup.get(4),list5);
        adapter.notifyDataSetChanged();

        chatbot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Kommunicate.openConversation(HomepageActivity4.this, null, new KmCallback() {
                    @Override
                    public void onSuccess(Object message) {
                        Utils.printLog(HomepageActivity4.this, "ChatTest", "Launch Success : " + message);
                    }

                    @Override
                    public void onFailure(Object error) {
                        Utils.printLog(HomepageActivity4.this, "ChatTest", "Launch Failure : " + error);
                    }
                });

                List<String> botList = new ArrayList(); botList.add("mr-dr--riwdj"); //enter your integrated bot Ids
                new KmConversationBuilder(HomepageActivity4.this).setAppId("2f93644cdc04e352bd0c8e534efd64c8c")
                        .setBotIds(botList)
                        .launchConversation(new KmCallback() {
                            @Override
                            public void onSuccess(Object message) {
                                Utils.printLog(HomepageActivity4.this, "ChatTest", "Success : " + message);
                            }

                            @Override
                            public void onFailure(Object error) {
                                Utils.printLog(HomepageActivity4.this, "ChatTest", "Failure : " + error);
                            }
                        });

            }
        });

        logouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomepageActivity4.this, LoginActivity3.class));
            }
        });
    }

}