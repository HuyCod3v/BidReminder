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
import com.cod3vstudio.bidreminder.adapters.CategoryListAdapter;
import com.cod3vstudio.bidreminder.adapters.SavedListAdapter;
import com.cod3vstudio.bidreminder.databinding.FragmentCategoryBinding;
import com.cod3vstudio.bidreminder.databinding.FragmentSavedBinding;
import com.cod3vstudio.core.view.BaseFragment;
import com.cod3vstudio.core.viewmodel.CategoryViewModel;
import com.cod3vstudio.core.viewmodel.SavedViewModel;

public class SavedFragment extends BaseFragment<FragmentSavedBinding, SavedViewModel> {

    //region Properties

    private SavedListAdapter mSavedListAdapter;

    //endregion

    //region Constructors

    public SavedFragment() {
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
        setBindingContentView(inflater, container, R.layout.fragment_saved, BR.viewModel);

        View view =  mViewDataBinding.getRoot();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.saved_recycler_view);


        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, GridLayout.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mSavedListAdapter = new SavedListAdapter();
        mSavedListAdapter.setViewModel(mViewModel);
        recyclerView.setAdapter(mSavedListAdapter);
        return view;
    }

    //endregion

}
