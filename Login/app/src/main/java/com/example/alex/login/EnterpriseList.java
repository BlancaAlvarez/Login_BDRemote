package com.example.alex.login;

import android.app.Activity;
import android.os.Bundle;

import com.example.alex.controller.EnterpriseListController;

public class EnterpriseList extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterprise_list);

        EnterpriseListController enterpriseListController
                = new EnterpriseListController(EnterpriseList.this);
        enterpriseListController.execute();
    }
}