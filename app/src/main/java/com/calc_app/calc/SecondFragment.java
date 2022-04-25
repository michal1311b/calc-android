package com.calc_app.calc;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {
    JSONArray vats;
    Spinner vatSpinner;


    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchVats();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void fetchVats() {
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = getString(R.string.fetch_vats_url);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length() > 0) {
                            try {
                                vats = response;
                                vatSpinner = requireActivity().findViewById(R.id.vatSpinner);

                                ArrayList<String> vatList = new ArrayList<>();

                                for(int i = 0; i > vats.length() + 1; i++) {
                                    JSONObject vat = vats.getJSONObject(i);
                                    vatList.add(vat.getString("display_text"));
                                }

                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                        getActivity().getApplicationContext(),
                                        android.R.layout.simple_spinner_dropdown_item, vatList);
                                vatSpinner.setAdapter(adapter);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // this is where i get my json "how?"

                        System.out.println(error);
                    }
                }
        );

        queue.add(jsonArrayRequest);
    }
}