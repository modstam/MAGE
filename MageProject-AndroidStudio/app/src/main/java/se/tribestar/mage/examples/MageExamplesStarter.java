package se.tribestar.mage.examples;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Andreas Stjerndal on 09-Jan-2015.
 */
public class MageExamplesStarter extends ListActivity {
    static final String examples[] = { "ColoredCubeTest", "TexturedCubeTest"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, examples));
    }

    @Override
    protected void onListItemClick(ListView list, View view, int position,
                                   long id) {
        super.onListItemClick(list, view, position, id);
        String exampleName = examples[position];
        try {
            Class clazz = Class.forName("se.tribestar.mage.examples."
                    + exampleName);
            Intent intent = new Intent(this, clazz);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
