package carbon.dialog;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import carbon.R;
import carbon.recycler.RowFactory;
import carbon.recycler.RowListAdapter;
import carbon.widget.LinearLayout;
import carbon.widget.RecyclerView;

public class ListDialog<Type> extends DialogBase {
    protected RecyclerView recyclerView;
    protected RowListAdapter adapter;
    protected List<Type> items;
    private RecyclerView.OnItemClickedListener listener;
    private RecyclerView.OnItemClickedListener internalListener = (view, position) -> {
        if (listener != null)
            listener.onItemClicked(view, position);
        dismiss();
    };

    public ListDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public ListDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init();
    }

    private void init() {
        recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        int padding = getContext().getResources().getDimensionPixelSize(R.dimen.carbon_paddingHalf);
        recyclerView.setPadding(0, padding, 0, padding);
        super.setContentView(recyclerView, null);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        throw new RuntimeException("not supported");
    }

    @Override
    public void setContentView(@NonNull View view) {
        throw new RuntimeException("not supported");
    }

    @Override
    public void setContentView(@NonNull View view, ViewGroup.LayoutParams params) {
        throw new RuntimeException("not supported");
    }

    public void setItems(Type[] items, RowFactory<Type> factory) {
        this.items = Arrays.asList(items);
        adapter = new RowListAdapter<>(this.items, factory);
        adapter.setOnItemClickedListener(internalListener);
        recyclerView.setAdapter(adapter);
    }

    public void setItems(List<Type> items, RowFactory<Type> factory) {
        this.items = items;
        adapter = new RowListAdapter<>(items, factory);
        adapter.setOnItemClickedListener(internalListener);
        recyclerView.setAdapter(adapter);
    }

    public void setOnItemClickedListener(RecyclerView.OnItemClickedListener listener) {
        this.listener = listener;
    }
}
