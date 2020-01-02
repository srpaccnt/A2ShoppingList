package com.srapps.user.a2shoppinglist;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>{
    private List<String> mDataset;
    private CBoxListener cBoxListener;
    public ListAdapter(List<String> aList,CBoxListener cBoxListener){
        this.mDataset = aList;
        this.cBoxListener = cBoxListener;



    }



    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CheckedTextView textView;
        public CheckBox cBox;
        Drawable a = new ColorDrawable(Color.TRANSPARENT);
        CBoxListener cBoxListener;
        private final String KEY_RECYCLER_STATE = "recycler state";
        private RecyclerView mRecyclerView;
        private static Bundle mBundleRecyclerViewState;
        public MyViewHolder(View v,CBoxListener cBoxListener) {
            super(v);
            textView = (CheckedTextView) v.findViewById(R.id.appTextViewCBox);
            this.cBoxListener = cBoxListener;
            itemView.setOnClickListener(this);





        }


        @Override
        public void onClick(View v) {
            updateChecked();

        }
        public void updateChecked(){
            if(textView.isChecked()){
                textView.setCheckMarkDrawable(a);
                textView.setChecked(false);
            }
            else
            if(!textView.isChecked()) {
                textView.setChecked(true);
                textView.setCheckMarkDrawable(R.drawable.tempboxsr);
            }
        }


    }





    public ListAdapter(List<String> myDataset) {
        this.mDataset = myDataset;


    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        // create a new view
        CheckedTextView v = (CheckedTextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopping_item, parent, false);


        return new MyViewHolder(v,cBoxListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {

        myViewHolder.textView.setText(mDataset.get(i));
        myViewHolder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mDataset.remove(myViewHolder.getLayoutPosition());
                notifyDataSetChanged();
                return true;
            }
        });
    }
    public interface CBoxListener{
        void onCheckClick(int position);
    }








}
