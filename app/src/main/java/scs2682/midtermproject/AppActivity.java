package scs2682.midtermproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import scs2682.midtermproject.data.Contact;
import scs2682.midtermproject.ui.contacts.Contacts;
import scs2682.midtermproject.ui.form.Form;


public class AppActivity extends AppCompatActivity {

    private static final class Page {
        private final int layoutId;
        @NonNull
        private final String title;

        private Page(final int layoutId, @NonNull final String title) {
            this.layoutId = layoutId;
            this.title = title;
        }
    }

    public static final class Adapter extends PagerAdapter {
        private static final int POSITION_CONTACTS = 0;
        private static final int POSITION_FORM = 1;

        private final List<Page> pages;
        private final LayoutInflater layoutInflater;

        private final ViewPager viewPager;
        private Contacts contacts;
        private Form form;

        private Adapter(ViewPager viewPager) {
            Context context = viewPager.getContext();

            this.viewPager = viewPager;
            layoutInflater = LayoutInflater.from(context);

            pages = new ArrayList<>(2);
            pages.add(POSITION_CONTACTS, new Page(R.layout.contacts, ""));
            pages.add(POSITION_FORM, new Page(R.layout.form, ""));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Page page = pages.get(position);
            View view = layoutInflater.inflate(page.layoutId, container, false);
            container.addView(view);

            if (view instanceof Contacts) {
                contacts = (Contacts) view;
                contacts.setAdapter(this);
            }
            else if (view instanceof  Form) {
                form = (Form) view;
                form.setAdapter(this);
            }

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pages.get(position).title;
        }

        @Override
        public int getCount() {
            return pages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        public void onOpenForm(Contact contact, int postionInContacts) {
            viewPager.setCurrentItem(POSITION_FORM);
            form.updateContact(contact, postionInContacts);
        }

        public void onContactUpdated(Contact contact, int postionInContacts) {
            viewPager.setCurrentItem(POSITION_CONTACTS);
            contacts.updateContact(contact, postionInContacts);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appactivity);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new Adapter(viewPager));
    }
}
