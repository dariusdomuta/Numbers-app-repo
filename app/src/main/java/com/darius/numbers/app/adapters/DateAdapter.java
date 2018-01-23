package com.darius.numbers.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darius.numbers.R;
import com.darius.numbers.app.RealmModels.DateModel;

import io.realm.RealmResults;
import timber.log.Timber;

/**
 * Created by dariu on 1/22/2018.
 */

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ItemViewHolder>{
    private RealmResults<DateModel> queryResults;
    private Context context;

    public RealmResults<DateModel> getQueryResults() {
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

    public DateAdapter(Context context, RealmResults<DateModel> queryResults){
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
    public DateAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorites_list_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DateAdapter.ItemViewHolder holder, int position) {
        DateModel currentResult = queryResults.get(position);
        Timber.d("bind" + currentResult.getStoredDateFact());
        Timber.d("position " + position );

        int currentDate = currentResult.getStoredDate();
        String numberToString = "";
        if (currentDate < 1000) {
           numberToString = "0" + Integer.toString(currentResult.getStoredDate());
        } else {
            numberToString = Integer.toString(currentResult.getStoredDate());
        }
        try {
            numberToString = numberToString.substring(0,2) + " " + numberToString.substring(2,4);
        } catch (StringIndexOutOfBoundsException e) {

        }

        Timber.d("date is " + numberToString);
        holder.tvNumber.setText(numberToString);
        holder.tvFact.setText(currentResult.getStoredDateFact());
    }
}
