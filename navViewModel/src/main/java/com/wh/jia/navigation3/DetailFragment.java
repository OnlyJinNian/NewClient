package com.wh.jia.navigation3;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wh.jia.navigation3.databinding.FragmentDetailBinding;
import com.wh.jia.navigation3.databinding.FragmentMasterBinding;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        MyViewModel myViewModel;
        myViewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        FragmentDetailBinding binding;
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(getActivity());
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_detailFragment_to_masterFragment);
            }
        });
        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detail, container, false);
    }

}
