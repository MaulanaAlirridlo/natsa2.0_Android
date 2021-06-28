package com.natsa.natsa20_mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.adapter.ProductsAdapter;
import com.natsa.natsa20_mobile.adapter.RandomRiceFieldsAdapter;
import com.natsa.natsa20_mobile.fragment.AboutFragment;
import com.natsa.natsa20_mobile.fragment.AccountFragment;
import com.natsa.natsa20_mobile.fragment.ContactFragment;
import com.natsa.natsa20_mobile.fragment.EmailFragment;
import com.natsa.natsa20_mobile.fragment.FaqFragment;
import com.natsa.natsa20_mobile.fragment.ProductFragment;
import com.natsa.natsa20_mobile.fragment.ProductsFragment;
import com.natsa.natsa20_mobile.server.process.auth.Logout;
import com.natsa.natsa20_mobile.shared_preference.Preferences;

public class BackActivity extends AppCompatActivity implements
        ProductsAdapter.showDetailSawahListener,
        RandomRiceFieldsAdapter.showDetailSawahListener {

    String page;
    LinearLayout search, accountBeforeLogin, accountAfterLogin;
    ImageView backButton, showSearch, showBookmark, showAccountMenu;
    TextView title, showSawah, showAbout, showFaq, showContact, showRegister, showLogin, showDashboard, logout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);

        init();
        setOnclickListener();

        page = getIntent().getExtras().getString("page");
        switch (page) {
            case "sawah":
                loadFragment(new ProductsFragment());
                title.setText("Sawah");
                break;
            case "detailSawah":
                loadFragment(new ProductFragment());
                title.setText("Detail Sawah");
                break;
            case "about":
                loadFragment(new AboutFragment());
                title.setText("About");
                break;
            case "FAQ":
                loadFragment(new FaqFragment());
                title.setText("FAQ");
                break;
            case "contact":
                loadFragment(new ContactFragment());
                title.setText("Our Contact");
                break;
            case "email":
                loadFragment(new EmailFragment());
                title.setText("Send Email");
                break;
            case "account":
                loadFragment(new AccountFragment());
                showBookmark.setVisibility(View.GONE);
                break;
        }
    }


    // init variable
    private void init() {
        title = findViewById(R.id.title);
        search = findViewById(R.id.search);
        accountBeforeLogin = findViewById(R.id.accountBeforeLogin);
        accountAfterLogin = findViewById(R.id.accountAfterLogin);
        backButton = findViewById(R.id.back_button);
        showSearch = findViewById(R.id.show_search);
        showBookmark = findViewById(R.id.show_bookmark);
        showAccountMenu = findViewById(R.id.show_account_menu);
        showSawah = findViewById(R.id.show_sawah);
        showAbout = findViewById(R.id.show_about);
        showFaq = findViewById(R.id.show_faq);
        showContact = findViewById(R.id.show_contact);
        showRegister = findViewById(R.id.show_register);
        showLogin = findViewById(R.id.show_login);
        showDashboard = findViewById(R.id.show_dashboard);
        logout = findViewById(R.id.logout);
    }

    // onClick function
    private void setOnclickListener() {

        backButton.setOnClickListener(v -> {
            finish();
        });

        showSearch.setOnClickListener(v -> {
            if (search.getVisibility() == LinearLayout.GONE) {
                search.setVisibility(LinearLayout.VISIBLE);
            } else {
                search.setVisibility(LinearLayout.GONE);
            }
            checkAccountVisibility();
        });

        showAccountMenu.setOnClickListener(v -> {
            //cek login
            if (Preferences.isLogin(getApplicationContext())) {
                if (accountAfterLogin.getVisibility() == LinearLayout.GONE) {
                    accountAfterLogin.setVisibility(LinearLayout.VISIBLE);
                } else {
                    accountAfterLogin.setVisibility(LinearLayout.GONE);
                }
            } else {
                if (accountBeforeLogin.getVisibility() == LinearLayout.GONE) {
                    accountBeforeLogin.setVisibility(LinearLayout.VISIBLE);
                } else {
                    accountBeforeLogin.setVisibility(LinearLayout.GONE);
                }
            }
            checkSearchVisibility();
        });

        showRegister.setOnClickListener(v -> {
            Intent i = new Intent(BackActivity.this, RegisterActivity.class);
            startActivity(i);
            finish();
        });

        showLogin.setOnClickListener(v -> {
            Intent i = new Intent(BackActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        });

        showDashboard.setOnClickListener(v -> {
            showAccountPage("Dashboard");

        });

        showBookmark.setOnClickListener(v -> {
            showAccountPage("Bookmark");

        });

        logout.setOnClickListener(v -> {
            new Logout().LogoutProcess(BackActivity.this);

        });
    }

    private void showAccountPage(String page) {
        Intent i = new Intent(BackActivity.this, BackActivity.class);
        i.putExtra("page", "account");
        i.putExtra("accountPage", page);
        startActivity(i);
        checkAccountVisibility();
    }


    private void checkSearchVisibility() {
        if (search.getVisibility() == LinearLayout.VISIBLE) {
            search.setVisibility(LinearLayout.GONE);
        }
    }

    private void checkAccountVisibility() {
        if (accountBeforeLogin.getVisibility() == LinearLayout.VISIBLE) {
            accountBeforeLogin.setVisibility(LinearLayout.GONE);
        }
        if (accountAfterLogin.getVisibility() == LinearLayout.VISIBLE) {
            accountAfterLogin.setVisibility(LinearLayout.GONE);
        }
    }

    public void showDetailSawah(int id) {
        showProces("detailSawah", id);
    }


    //fragment loader
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    // process
    private void showProces(String page, int id) {
        Intent i = new Intent(BackActivity.this, BackActivity.class);
        i.putExtra("page", page);
        i.putExtra("id", id);
        startActivity(i);
    }
}