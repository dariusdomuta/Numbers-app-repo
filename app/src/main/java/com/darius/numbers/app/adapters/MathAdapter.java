package com.darius.numbers.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darius.numbers.R;
import com.darius.numbers.app.RealmModels.MathModel;

import io.realm.RealmResults;
import timber.log.Timber;

/**
 * Created by dariu on 1/22/2018.
 */

public class MathAdapter extends RecyclerView.Adapter<MathAdapter.ItemViewHolder>{

    private RealmResults<MathModel> queryResults;
    private Context context;

    public RealmResults<MathModel> getQueryResults() {
        return queryResults;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvNumber;
        TextView tvFact;

        public ItemViewHolder(View itemView){
            super(itemView);

            tvNumber = itemView.findViewById(R.id.favorites_list_item_number);
            tvFact = itemView.findViewById(R.id.favorites_list_item_fact);
        }
    }

    public MathAdapter(Context context, RealmResults<MathModel> queryResults){
        this.context = context;
        this.queryResults = queryResults;
    }

    @Override
    public int getItemCount() {
        return queryResults.indexOf(queryResults.last())+1;

    }

    public void clear(){
        queryResults.deleteAllFromRealm();
    }

    @Override
    public MathAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorites_list_item, parent, false);
        return new MathAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MathAdapter.ItemViewHolder holder, int position) {
        MathModel currentResult = queryResults.get(position);
        Timber.d("bind" + currentResult.getStoredMathFact());
        Timber.d("position " + position );
        String numberToString = Integer.toString(currentResult.getStoredMath());
        holder.tvNumber.setText(numberToString);
        holder.tvFact.setText(currentResult.getStoredMathFact());
    }
}
