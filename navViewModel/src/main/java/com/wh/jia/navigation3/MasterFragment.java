package com.wh.jia.navigation3;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.wh.jia.navigation3.databinding.FragmentMasterBinding;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment {


    public MasterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final MyViewModel myViewModel;
        myViewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        final FragmentMasterBinding binding;
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_master,container,false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(getActivity());
        binding.btnCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_masterFragment_to_detailFragment);
            }
        });
        binding.seekBar.setProgress(myViewModel.getNumber().getValue());
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                myViewModel.getNumber().setValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_master, container, false);
        return binding.getRoot();
    }

}
