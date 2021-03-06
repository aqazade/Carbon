package tk.zielony.carbonsamples;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import tk.zielony.carbonsamples.dialog.ListDialogActivity;
import tk.zielony.carbonsamples.dialog.SelectDialogActivity;
import tk.zielony.carbonsamples.dialog.SimpleDialogActivity;

public class DialogsActivity extends SamplesActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        Samples.initToolbar(this, getString(R.string.dialogsActivity_title));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        ViewModel[] items = new ViewModel[]{
                new ViewModel(SimpleDialogActivity.class, getString(R.string.simpleDialogActivity_title)),
                new ViewModel(SelectDialogActivity.class, getString(R.string.selectDialogActivity_title)),
                new ViewModel(ListDialogActivity.class, getString(R.string.listDialogActivity_title))
        };
        recyclerView.setLayoutManager(getResources().getBoolean(R.bool.tablet) ?
                new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false) :
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new MainListAdapter(items));
    }
}
