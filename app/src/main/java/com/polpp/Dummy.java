package com.polpp;

import com.polpp.entity.EntityWalkthrough;

import java.util.ArrayList;


public class Dummy {

    public static ArrayList<EntityWalkthrough> getWalktrought() {

        EntityWalkthrough item;

        ArrayList<EntityWalkthrough> arrayData = new ArrayList<EntityWalkthrough>();

        item = new EntityWalkthrough();
        item.setDescWalktrought("Polpp Lorem ipsum dolor siamet consectur dolor consectur siamet consectur siamet ");
        item.setImageWalktrought(R.drawable.ic_logo);
        item.setBackWalktrought(R.drawable.walktrough_1);
        arrayData.add(item);

        item = new EntityWalkthrough();
        item.setTitleWalktrought("KETAHUILAH POSISI ZONA PEDAGANG KAKI LIMA");
        item.setDescWalktrought("Polpp Lorem ipsum dolor siamet consectur dolor consectur siamet consectur siamet ");
        item.setBackWalktrought(R.drawable.walktrough_2);

        arrayData.add(item);

        item = new EntityWalkthrough();
        item.setTitleWalktrought("PELAPORAN PELANGGARAN PEDAGANG KAKI LIMA");
        item.setDescWalktrought("Polpp Lorem ipsum dolor siamet consectur dolor consectur siamet consectur siamet");
        item.setBackWalktrought(R.drawable.walktrough_3);
        arrayData.add(item);

        return arrayData;
    }



}
