package com.cod3vstudio.bidreminder.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.cod3vstudio.bidreminder.App;
import com.cod3vstudio.bidreminder.BR;
import com.cod3vstudio.bidreminder.R;
import com.cod3vstudio.bidreminder.adapters.BiddingListAdapter;
import com.cod3vstudio.bidreminder.adapters.SavedListAdapter;
import com.cod3vstudio.bidreminder.databinding.FragmentBiddingBinding;
import com.cod3vstudio.bidreminder.databinding.FragmentSavedBinding;
import com.cod3vstudio.core.view.BaseFragment;
import com.cod3vstudio.core.viewmodel.BiddingViewModel;
import com.cod3vstudio.core.viewmodel.SavedViewModel;

public class BiddingFragment extends BaseFragment<FragmentBiddingBinding, BiddingViewModel> {

    //region Properties

    private BiddingListAdapter mBiddingListAdapter;

    //endregion

    //region Constructors

    public BiddingFragment() {
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
        setBindingContentView(inflater, container, R.layout.fragment_bidding, BR.viewModel);
        getActivity().setTitle(getString(R.string.bidding));
        View view =  mViewDataBinding.getRoot();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.product_recycler_view);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, GridLayout.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mBiddingListAdapter = new BiddingListAdapter();
        mBiddingListAdapter.setViewModel(mViewModel);
        recyclerView.setAdapter(mBiddingListAdapter);
        return view;
    }

    //endregion

}
