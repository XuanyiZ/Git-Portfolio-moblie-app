package com.example.xuanyi.gitapp;
/**
 * Created by Administrator on 2018/3/11.
 * citation: https://stackoverflow.com/questions/13196234/simple-parse-json-from-url-on-android-and-display-in-listview/13196301#13196301
 * http://www.vetbossel.in/android-json-parsing-from-url/
 * https://stackoverflow.com/questions/13784825/how-can-i-parse-a-json-object-and-display-it-in-a-list-view
 * https://stackoverflow.com/questions/36221795/android-parse-json-data-from-a-web-server-and-display-on-listview
 */
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;


/*
https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
In Android development, any time we want to show a vertical list of scrollable items we will use a ListView which has data populated using an Adapter.
The simplest adapter to use is called an ArrayAdapter because the adapter converts an ArrayList of objects into View items loaded into the ListView container.
 */
public class UsersAdapter extends ArrayAdapter<RepoInfo> {

    public UsersAdapter(Context context , int i, ArrayList<RepoInfo> users) {
        super(context, i, users);
    }

    /*
    use adapter to achieve recycling : Each item/cell of the list should be recycled/reused
    When your ListView is connected to an adapter, the adapter will instantiate rows until the ListView has been fully populated with enough items to fill
    the full height of the list. At that point, no additional row items are created in memory.
    Instead, as the user scroll through the list, items that leave the screen are kept in memory for later use and then every new row that enters the screen
    reuses an older row kept around in memory. In this way, even for a list of 1000 items, only ~7 item view rows are ever instantiated or held in memory.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        RepoInfo user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.repo_item, parent, false);
        }
        // Lookup view for data population
        TextView repo_name = (TextView) convertView.findViewById(R.id.repo_name);
        TextView owner_name = (TextView) convertView.findViewById(R.id.owner_name);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        // Populate the data into the template view using the data object
        repo_name.setText(user.getRepo_name());
        owner_name.setText(user.getOwner_name());
        description.setText(user.getDescription());
        // Return the completed view to render on screen
        return convertView;
    }
}
