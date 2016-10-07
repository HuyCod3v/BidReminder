package com.cod3vstudio.bidreminder.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.cod3vstudio.bidreminder.App;
import com.cod3vstudio.bidreminder.BR;
import com.cod3vstudio.bidreminder.R;
import com.cod3vstudio.bidreminder.adapters.CartListAdapter;
import com.cod3vstudio.bidreminder.adapters.HomeListAdapter;
import com.cod3vstudio.bidreminder.databinding.FragmentHomeBinding;
import com.cod3vstudio.bidreminder.util.EndlessScrollRecyclerViewListener;
import com.cod3vstudio.core.util.Configuration;
import com.cod3vstudio.core.util.Constants;
import com.cod3vstudio.core.view.BaseFragment;
import com.cod3vstudio.core.viewmodel.HomeViewModel;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    //region Properties

    private HomeListAdapter mHomeListAdapter;
    private SearchView mSearchView;
    private String mName;

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
        setHasOptionsMenu(true);

        getActivity().setTitle(getString(R.string.app_name));
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


        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mHomeListAdapter = new HomeListAdapter();
        mHomeListAdapter.setViewModel(mViewModel);
        recyclerView.setAdapter(mHomeListAdapter);
        recyclerView.addOnScrollListener(new EndlessScrollRecyclerViewListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (mName != null && !mName.equals("")) {
                    mViewModel.loadProducts(mName, Configuration.ITEMS_PER_PAGE, page);
                }
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        MenuItem itemSearch = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) itemSearch.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mViewModel.loadProducts(query, Configuration.ITEMS_PER_PAGE, 1);
                mName = query;
                mSearchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            mViewModel.getNavigator().navigateTo(Constants.FILTER_PAGE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //endregion

}
