package com.example.aad9exam.view.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.aad9exam.R;
import com.example.aad9exam.databinding.ActivityMainBinding;
import com.example.aad9exam.entity.Category;
import com.example.aad9exam.entity.Drink;
import com.example.aad9exam.prefs.PrefsFragment;
import com.example.aad9exam.util.ToastUtil;
import com.example.aad9exam.view.OnActionPerformedListener;
import com.example.aad9exam.view.RepositoryCallback;
import com.example.aad9exam.view.RepositoryImpl;
import com.example.aad9exam.view.fragments.AddFragment;
import com.example.aad9exam.view.fragments.DetailsFragment;
import com.example.aad9exam.view.fragments.DrinkDetailsFragment;
import com.example.aad9exam.view.fragments.EditFragment;
import com.example.aad9exam.view.fragments.ListFragment;
import com.example.aad9exam.view.fragments.SavedDrinksFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Objects;

import static com.example.aad9exam.prefs.PrefsRepository.LIST_PREF_KEY;
import static com.example.aad9exam.prefs.PrefsRepository.NOTIF_KEY;
import static com.example.aad9exam.prefs.PrefsRepository.TOAST_KEY;

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
        /*
        TODO if some functionality is in more then one fragment in toolbar
         we can put it here
         */
//        if(item.getItemId() == R.id.menu_search) {
//            fragmentTransaction(SearchFragment.newInstance(new ArrayList<Meal>()), null);
//        }
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

        /*
        TODO define actions from fragments
         define separate case for each action
         (ex. open add fragment, open edit fragment, save entity, modify entity etc)
         */

        switch (bundleKey) {

            case OPEN_PARENT_DETAILS:
                this.category = bundle.getParcelable(OBJECT_PARCELABLE);
                repository.getByCategory(this, this.category.getStrCategory());
                break;

            case OPEN_CHILD_DETAILS:
                String id = bundle.getString(OBJECT_ID);
//                this.drink = bundle.getParcelable(OBJECT_PARCELABLE);
                repository.getByDrinkId(this, id);
                break;

            case OPEN_ADD:
                fragmentTransaction(AddFragment.newInstance(), null);
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

//            case OPEN_IMG:
//                String url = bundle.getString(IMG_KEY);
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                startActivity(intent);
//                break;
//
////            case ACTION_SCHEDULE:
////                checkPrefs(this, NOTIF_KEY, "Tour Scheduled", "");
////                break;
////
////            case ACTION_DIAL:
////                parent = bundle.getParcelable(OBJECT_PARCELABLE);
////                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + parent.getPhoneNo())));
////                break;
////
////            case ACTION_PICK_IMG:
////                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI),1000);
////                break;
//
        }
    }






    /* ************** RETROFIT ********************* */
    // result from retrofit call
    @Override
    public void resultCallback(List<?> list, int code) {
      /*
       TODO define codes for retrofit result callback
        define separate case for each retrofit call result
        (ex. getting all, getting by id, getting by name etc)
       */
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

//            case MEALS_BY_NAME_RES_CODE:
//                if (list != null && list.size() > 0 && list.get(0) instanceof Meal) {
//                    this.meals = (List<Meal>) list;
//                    fragmentTransaction(SearchFragment.newInstance(meals), null);
//                }
//                break;

        }
    }




    /* ********************** LIFECYCLE ************************** */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        repository = null;
    }

    @Override
    protected void onResume() {
        super.onResume();

//        repository.getAll();

    }


    /* ************************ GET IMG FROM GALLERY ******************** */
    // if we have to get img from gallery when adding entity
    private Uri uri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case 1000:
                    uri = data.getData();
                    Fragment fr = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                    ((AddFragment)fr).getData(uri);
            }
    }

}
