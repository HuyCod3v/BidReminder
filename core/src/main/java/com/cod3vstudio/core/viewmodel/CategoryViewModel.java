package com.cod3vstudio.core.viewmodel;

import android.databinding.Bindable;

import com.cod3vstudio.core.BR;
import com.cod3vstudio.core.model.entities.Category;
import com.cod3vstudio.core.view.INavigator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 7/29/2016.
 */

public class CategoryViewModel extends BaseViewModel {

    //region Properties

    private List<Category> mCategories;

    //endregion

    //region Constructors

    public CategoryViewModel(INavigator navigator) {
        super(navigator);
    }

    //endregion

    //region Getter and Setter
    @Bindable
    public List<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Category> categories) {
        mCategories = categories;

        notifyPropertyChanged(BR.categories);
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();

        loadCategories();
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //endregion

    private void loadCategories() {
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Category category = new Category();
            category.setId(i);
            category.setName("Name " + i);
            categories.add(category);
        }

        setCategories(categories);
    }
}
