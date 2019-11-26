package com.example.cocktaildbapi.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.cocktaildbapi.databinding.ActivityMainBinding;
import com.example.cocktaildbapi.R;
import com.example.cocktaildbapi.entity.Category;
import com.example.cocktaildbapi.entity.Drink;
import com.example.cocktaildbapi.prefs.PrefsFragment;
import com.example.cocktaildbapi.util.ToastUtil;
import com.example.cocktaildbapi.view.OnActionPerformedListener;
import com.example.cocktaildbapi.view.RepositoryCallback;
import com.example.cocktaildbapi.view.RepositoryImpl;
import com.example.cocktaildbapi.view.fragments.DetailsFragment;
import com.example.cocktaildbapi.view.fragments.DrinkDetailsFragment;
import com.example.cocktaildbapi.view.fragments.EditFragment;
import com.example.cocktaildbapi.view.fragments.ListFragment;
import com.example.cocktaildbapi.view.fragments.SavedDrinksFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Objects;

public class MainActivity
        extends BaseActivity
        implements RepositoryCallback, OnActionPerformedListener {

    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    private List<Category> list;
    private Category category;
    private Drink drink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setupToolbar();
        setupNavigationView();
        setupDrawer();

        repository = new RepositoryImpl(this);
        repository.getCategories(this);

        // TODO save instance state
//        fragmentTransaction(ListFragment.newInstance(list), HOME_FRAGMENT);

    }



    /* ************* TOOLBAR & MENU ************** */

    private void setupToolbar() {
        toolbar = (Toolbar) binding.drawerLayout.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return toggle.onOptionsItemSelected(item);
    }


    /* ************* DRAWER ************** */

    private void setupDrawer() {

        drawer = binding.drawerLayout.drawer;

        toggle = new ActionBarDrawerToggle(this,
                drawer,
                R.string.drawer_open,
                R.string.drawer_close);

        setupToolbarNav();
    }

    // when item is clicked on, close the drawer
    private void setupNavigationView() {

        binding.drawerLayout.navigationView
                .setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        drawer.closeDrawer(GravityCompat.START);

                        switch (menuItem.getItemId()) {
                            case R.id.show_home:
                                /* pop everything except first entry, which is list fragment
                                   when selecting "show all" I don't want anything on backstack,
                                   basically go back to"home page" */
                                getSupportFragmentManager().popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                                fragmentTransaction(ListFragment.newInstance(list), HOME_FRAGMENT);
                                return true;

                            case R.id.menu_settings:
                                fragmentTransaction(new PrefsFragment(), null);
                                return true;

                            case R.id.menu_about:
                                showAboutDialog(MainActivity.this);
                                return true;
                        }

                        return false;
                    }
                });
    }

    /*
    show hamburger in first(list) fragment; show back arrow in others
    open drawer in first fragment; go back in others
     */
    private void setupToolbarNav() {
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                // if other fragments, display back arrow
                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    toggle.setDrawerIndicatorEnabled(false);
                    // if back arrow, navigate back
                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onBackPressed();
                        }
                    });

                    // if list fragment, display hamburger
                } else {
                    toggle.setDrawerIndicatorEnabled(true);
                    drawer.addDrawerListener(toggle);
                    // if hamburger, open drawer
                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(!drawer.isDrawerOpen(GravityCompat.START)) {
                                drawer.openDrawer(GravityCompat.START);
                            }
                        }
                    });
                    toggle.syncState();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        // if drawer open, first close on back pressed
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            // if drawer closed
        } else {
            // if list fragment(first fragment), close app
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                // if other fragment, go back
            } else {
                super.onBackPressed();
            }
        }
    }



    /* ************************ ACTION LISTENER ************** */

    @Override
    public void onActionPerformed(Bundle bundle) {

        int bundleKey = bundle.getInt(BUNDLE_KEY, -1);

        switch (bundleKey) {

            case OPEN_PARENT_DETAILS:
                this.category = bundle.getParcelable(OBJECT_PARCELABLE);
                repository.getByCategory(this, this.category.getStrCategory());
                break;

            case OPEN_CHILD_DETAILS:
                String id = bundle.getString(OBJECT_ID);
                repository.getByDrinkId(this, id);
                break;

            case OPEN_EDIT:
                drink = bundle.getParcelable(OBJECT_PARCELABLE);
                fragmentTransaction(EditFragment.newInstance(drink), null);
                break;

            case OPEN_SAVED:
                List<Drink> drinks = repository.getSaved();
                fragmentTransaction(SavedDrinksFragment.newInstance(drinks), null);
                break;

            case ACTION_SAVE:
                drink = bundle.getParcelable(OBJECT_PARCELABLE);
                if (repository.insert(drink) != 0) {
                    checkPrefs(this, "Added", drink.getStrDrink());
                } else {
                    ToastUtil.showToast(this, "You have already saved this item");
                }
                break;

            case ACTION_UPDATE:
                getSupportFragmentManager().popBackStack();
                drink = bundle.getParcelable(OBJECT_PARCELABLE);
                if (repository.modify(drink) != 0) {
                    checkPrefs(this, "Updated", drink.getStrDrink());
                } else {
                    ToastUtil.showToast(this, "You have to save this item first");
                }
                break;

            case ACTION_DELETE:
                drink = bundle.getParcelable(OBJECT_PARCELABLE);
                if(repository.delete(drink) != 0) {
                    getSupportFragmentManager().popBackStack();
                    checkPrefs(this, "Deleted", drink.getStrDrink());
                } else {
                    ToastUtil.showToast(this, "You have to save this item first");
                }
                break;

            case ACTION_CONFIRM:
                drink = bundle.getParcelable(OBJECT_PARCELABLE);
                int actionCode = bundle.getInt(CONFIRM_KEY);
                showConfirmationDialog(this, actionCode, drink);
                break;

        }
    }






    /* ************** RETROFIT ********************* */
    // result from retrofit call
    @Override
    public void resultCallback(List<?> list, int code) {

        switch (code) {

            case ALL_CAT_RES_CODE:
                if (list != null && list.size() > 0 && list.get(0) instanceof Category) {
                    this.list = (List<Category>) list;
                    fragmentTransaction(ListFragment.newInstance((List<Category>) list), HOME_FRAGMENT);
                }
                break;

            case DRINKS_BY_CAT_RES_CODE:
                if (list != null && list.size() > 0 && list.get(0) instanceof Drink) {
                    this.category.setDrinkDetails((List<Drink>) list);
                    fragmentTransaction(DetailsFragment.newInstance(this.category), DETAILS_FRAGMENT);
                }
                break;

            case DRINK_RES_CODE:
                if (list != null && list.size() > 0 && list.get(0) instanceof Drink) {
                    Drink drink = (Drink) list.get(0);
                    getSupportFragmentManager().popBackStack(DETAILS_FRAGMENT, 0);
                    fragmentTransaction(DrinkDetailsFragment.newInstance(drink), CHILD_DETAILS_FRAGMENT);
                }
                break;

        }
    }




    /* ********************** LIFECYCLE ************************** */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        repository = null;
    }


}
