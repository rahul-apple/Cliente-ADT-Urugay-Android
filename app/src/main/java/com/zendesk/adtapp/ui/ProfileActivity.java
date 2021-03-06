package com.zendesk.adtapp.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zendesk.adtapp.model.UserProfile;
import com.zendesk.adtapp.storage.UserProfileStorage;


public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.zendesk.adtapp.R.layout.activity_profile);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(com.zendesk.adtapp.R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        ImageView   imageView   = (ImageView)this.findViewById(com.zendesk.adtapp.R.id.imageView);
        TextView    userName    = (TextView)this.findViewById(com.zendesk.adtapp.R.id.userName);
        TextView    email       = (TextView)this.findViewById(com.zendesk.adtapp.R.id.emailView);

        UserProfileStorage userProfileStorage = new UserProfileStorage(this);
        UserProfile userProfile = userProfileStorage.getProfile();

        if(userProfile.getAvatar() != null){
            imageView.setImageBitmap(userProfile.getAvatar());
        }

        userName.setText(userProfile.getName());
        email.setText(userProfile.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.zendesk.adtapp.R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.zendesk.adtapp.R.id.action_edit) {
            Intent intent = new Intent(this, CreateProfileActivity.class);

            startActivity(intent);
            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
            // Intentionally empty
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(com.zendesk.adtapp.R.layout.fragment_profile, container, false);
        }
    }
}
