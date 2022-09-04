package com.ttp.ziaratarbaeen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.adapter.MentionAdapter;
import com.ttp.ziaratarbaeen.classes.Mention;

import java.util.ArrayList;

public class SelectMentionFragment extends Fragment {

    RecyclerView rvMention;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_mention,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);

        setUpMentionList();
    }


    private void findViews(View view){

        rvMention=view.findViewById(R.id.rv_mention);
    }

    private void setUpMentionList(){

        ArrayList<Mention>mentionList=new ArrayList<>();

        mentionList.add(new Mention("الله اکیر","خداوند بزرگ است",100,3));
        mentionList.add(new Mention("الله اکیر","خداوند بزرگ است",100,3));
        mentionList.add(new Mention("الله اکیر","خداوند بزرگ است",100,3));
        mentionList.add(new Mention("الله اکیر","خداوند بزرگ است",100,3));
        mentionList.add(new Mention("الله اکیر","خداوند بزرگ است",100,3));
        mentionList.add(new Mention("الله اکیر","خداوند بزرگ است",100,3));
        mentionList.add(new Mention("الله اکیر","خداوند بزرگ است",100,3));
        mentionList.add(new Mention("الله اکیر","خداوند بزرگ است",100,3));
        mentionList.add(new Mention("الله اکیر","خداوند بزرگ است",100,3));
        mentionList.add(new Mention("الله اکیر","خداوند بزرگ است",100,3));
        mentionList.add(new Mention("الله اکیر","خداوند بزرگ است",100,3));
        mentionList.add(new Mention("الله اکیر","خداوند بزرگ است",100,3));
        mentionList.add(new Mention("الله اکیر","خداوند بزرگ است",100,3));
        mentionList.add(new Mention("الله اکیر","خداوند بزرگ است",100,3));
        mentionList.add(new Mention("الله اکیر","خداوند بزرگ است",100,3));

        MentionAdapter mentionAdapter=new MentionAdapter(mentionList);
        rvMention.setAdapter(mentionAdapter);
    }
}
