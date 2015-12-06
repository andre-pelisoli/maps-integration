package br.com.pelisoli.mapsintegration.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.pelisoli.mapsintegration.R;
import br.com.pelisoli.mapsintegration.listeners.IClickItemListener;
import br.com.pelisoli.mapsintegration.models.Address;

/**
 * Created by pelisoli on 21/11/15.
 */
public class MapsAdapter extends RecyclerView.Adapter<MapsAdapter.ViewHolder> {

    private ArrayList<Address> addressList;

    private LayoutInflater mLayoutInflater;

    public IClickItemListener clickItemListener;

    public MapsAdapter(Context context, ArrayList<Address> addressList, IClickItemListener clickItemListener) {
        this.addressList = addressList;
        this.clickItemListener = clickItemListener;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater
                .inflate(R.layout.item_address, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(addressList.get(position).getFormatted_address());

    }

    @Override
    public int getItemCount() {
        int size = 0;

        if(addressList != null) {
            size = addressList.size();
        }

        return size;
    }


    public void addAddressList(ArrayList<Address> addressList){
        this.addressList = addressList;
        notifyDataSetChanged();
    }

    public void setOnClickItemListener(IClickItemListener itemListener){
        this.clickItemListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.item_address);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickItemListener != null){
                clickItemListener.onClick(getLayoutPosition());
            }
        }
    }
}
