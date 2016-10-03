package com.cod3vstudio.bidreminder.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cod3vstudio.bidreminder.BR;
import com.cod3vstudio.bidreminder.R;
import com.cod3vstudio.core.model.entities.Category;
import com.cod3vstudio.core.view.BaseRecyclerViewAdapter;
import com.cod3vstudio.core.view.ViewHolder;
import com.cod3vstudio.core.viewmodel.CategoryViewModel;

import java.util.List;

/**
 * Created by Administrator on 8/2/2016.
 */
public class CategoryListAdapter extends BaseRecyclerViewAdapter<CategoryViewModel, List<Category>> {

    //region Override Methods

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_category, parent, false);
        return new ViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewDataBinding viewDataBinding = ((ViewHolder) holder).getViewDataBinding();

        viewDataBinding.setVariable(BR.category, mData.get(position));
        viewDataBinding.setVariable(BR.viewModel, mViewModel);

        viewDataBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    //endregion
}
