package br.com.pelisoli.mapsintegration.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.pelisoli.mapsintegration.main.Main;
import br.com.pelisoli.mapsintegration.R;
import br.com.pelisoli.mapsintegration.adapters.MapsAdapter;
import br.com.pelisoli.mapsintegration.listeners.IClickItemListener;
import br.com.pelisoli.mapsintegration.models.Address;

/**
 * Created by pelisoli on 19/11/15.
 */
public class SearchActivity extends AppCompatActivity implements View.OnClickListener, IClickItemListener {

    EditText edtSearch;

    Button btnSearch;

    RecyclerView recyclerView;

    ProgressBar progressBar;

    LinearLayoutManager linearLayoutManager;

    MapsAdapter mapsAdapter;

    ArrayList<Address> addressList;

    TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        //UI components
        txtInfo = (TextView) findViewById(R.id.txtInfo);
        edtSearch = (EditText) findViewById(R.id.edtSearch);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        if(savedInstanceState != null){
            addressList = (ArrayList<Address>) savedInstanceState.getSerializable("addressList");
            txtInfo.setText((String) savedInstanceState.getSerializable("textInfo"));
        }

        //Button listener
        btnSearch.setOnClickListener(this);

        //Recycler view with all addresses found in search
        mapsAdapter = new MapsAdapter(getApplicationContext(), addressList, this);
        mapsAdapter.setOnClickItemListener(this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mapsAdapter);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSearch:
                recyclerView.setVisibility(View.GONE);

                //It's not allowed an empty string
                if(edtSearch.getText().toString().equals("")){
                    txtInfo.setVisibility(View.GONE);

                    //Show warning message

                }else {
                    txtInfo.setText(getString(R.string.infoMessage) + " " + edtSearch.getText().toString());
                    txtInfo.setVisibility(View.VISIBLE);
                    //Start communication with API
                    new SearchAddress().execute(edtSearch.getText().toString());
                }

                break;
        }
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this, MapsActivity.class);

        //Creating bundle with address coordinates
        Bundle bundle = new Bundle();
        bundle.putString("locationName", addressList.get(position).getFormatted_address());
        bundle.putDouble("lat", addressList.get(position).getGeometry().getLocation().getLat());
        bundle.putDouble("lng", addressList.get(position).getGeometry().getLocation().getLng());

        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Saving instance information
        outState.putSerializable("addressList", addressList);
        outState.putSerializable("textInfo", txtInfo.getText().toString());
    }

    private class SearchAddress extends AsyncTask<String, Integer, ArrayList<Address>>{

        @Override
        protected ArrayList<Address> doInBackground(String... strings) {
            publishProgress(View.VISIBLE);

            //call API
            return Main.getServiceAPI().getAddresses(strings[0]);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            progressBar.setVisibility(values[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Address> newAddressList) {
            super.onPostExecute(addressList);

            //Hide progress bar
            progressBar.setVisibility(View.GONE);

            addressList = newAddressList;

            //Add address list to recycler view
            mapsAdapter.addAddressList(addressList);

            //show recycler view
            recyclerView.setVisibility(View.VISIBLE);

        }
    }
}
