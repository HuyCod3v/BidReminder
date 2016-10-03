package com.cod3vstudio.bidreminder.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.cod3vstudio.bidreminder.App;
import com.cod3vstudio.bidreminder.BR;
import com.cod3vstudio.bidreminder.R;
import com.cod3vstudio.bidreminder.adapters.CartListAdapter;
import com.cod3vstudio.bidreminder.adapters.HomeListAdapter;
import com.cod3vstudio.bidreminder.databinding.FragmentHomeBinding;
import com.cod3vstudio.core.view.BaseFragment;
import com.cod3vstudio.core.viewmodel.HomeViewModel;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    //region Properties

    private HomeListAdapter mHomeListAdapter;

    //endregion

    //region Constructors

    public HomeFragment() {
        // Required empty public constructor
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.sharedComponent().inject(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBindingContentView(inflater, container, R.layout.fragment_home, BR.viewModel);

        View view =  mViewDataBinding.getRoot();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.product_recycler_view);


        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, GridLayout.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mHomeListAdapter = new HomeListAdapter();
        mHomeListAdapter.setViewModel(mViewModel);
        recyclerView.setAdapter(mHomeListAdapter);
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //endregion

}
