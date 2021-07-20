package com.ttp.ziaratarbaeen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.VirtualPilgrimageAdapter;
import com.ttp.ziaratarbaeen.classes.VirtualPilgrimageModel;

import java.util.ArrayList;

public class VirtualPilgrimageFragment extends Fragment {

    RecyclerView rvVirtualPilgrimage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_virtual_pilgrimage,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        setUpList();

    }

    private void setUpList() {

        ArrayList<VirtualPilgrimageModel>arrayList=new ArrayList<>();

        /*arrayList.add(new VirtualPilgrimageModel("حرم",R.drawable.ic_advertisement));
        arrayList.add(new VirtualPilgrimageModel("حرم",R.drawable.ic_advertisement));
        arrayList.add(new VirtualPilgrimageModel("حرم",R.drawable.ic_advertisement));
        arrayList.add(new VirtualPilgrimageModel("حرم",R.drawable.ic_advertisement));
        arrayList.add(new VirtualPilgrimageModel("حرم",R.drawable.ic_advertisement));
        arrayList.add(new VirtualPilgrimageModel("حرم",R.drawable.ic_advertisement));
        arrayList.add(new VirtualPilgrimageModel("حرم",R.drawable.ic_advertisement));
        arrayList.add(new VirtualPilgrimageModel("حرم",R.drawable.ic_advertisement));
        arrayList.add(new VirtualPilgrimageModel("حرم",R.drawable.ic_advertisement));
        arrayList.add(new VirtualPilgrimageModel("حرم",R.drawable.ic_advertisement));
        arrayList.add(new VirtualPilgrimageModel("حرم",R.drawable.ic_advertisement));*/
      //  arrayList.add(new VirtualPilgrimageModel("حرم",1));

        VirtualPilgrimageAdapter virtualPilgrimageAdapter=new VirtualPilgrimageAdapter(getContext(),arrayList);
        rvVirtualPilgrimage.setLayoutManager(new LinearLayoutManager(getContext()));
        rvVirtualPilgrimage.setAdapter(virtualPilgrimageAdapter);



    }

    private void findViews(View view) {

        rvVirtualPilgrimage =view.findViewById(R.id.rv_virtual_pilgrimage);
    }


}
