package com.example.raihan.sharefoods;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Volunteer_preview_fragment extends Fragment {


    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private Volunteer_Preview_Adapter volunteer_preview_adapter;

    public ArrayList<Profile_Object> arrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.volunteer_preview_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        if(bundle!=null)
        {
            arrayList = bundle.getParcelableArrayList("arrayList");
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.volunteerPreviewRecycler);
        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        volunteer_preview_adapter = new Volunteer_Preview_Adapter(arrayList,getActivity());
        recyclerView.setAdapter(volunteer_preview_adapter);
        volunteer_preview_adapter.notifyDataSetChanged();
    }


}
