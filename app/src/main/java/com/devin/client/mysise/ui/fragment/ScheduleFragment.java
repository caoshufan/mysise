package com.devin.client.mysise.ui.fragment;

/**
 * Created by 书凡 on 2015-11-10.
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.bean.Schedule;
import com.devin.client.mysise.model.parse.ParseSchedular;
import com.devin.client.mysise.ui.adapter.ScheduleAdapter;


/**
 * A placeholder fragment containing a simple view.
 */
public class ScheduleFragment extends Fragment {

    private RecyclerView recycler;

    private Schedule schedule;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_SCHEDULE:
                    setSchedule();
                    break;
            }
        }
    };

    private static final int UPDATE_SCHEDULE = 1;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ScheduleFragment newInstance(int sectionNumber) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScheduleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        recycler = (RecyclerView)rootView.findViewById(R.id.recycler);

        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

        requestSchedule();

        return rootView;
    }

    private void requestSchedule() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                schedule = ParseSchedular.getSchedule();
                Message message = new Message();
                message.what = UPDATE_SCHEDULE;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void setSchedule() {
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getActivity(), schedule, getArguments().getInt(ARG_SECTION_NUMBER));
        recycler.setAdapter(scheduleAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
