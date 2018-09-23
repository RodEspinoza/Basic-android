package com.example.rodrigoespinoza.gestor_pedido.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rodrigoespinoza.gestor_pedido.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistroFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    View view;

    EditText txtFragRegistroEmail, txtFragRegistroPass, txtFragRegistroRePass;
    EditText txtFragRegistroRut, txtFragRegistroNombre, txtFragRegistroApellido;
    RadioGroup rgFragRegistroSexo;
    Spinner spFragRegistroLocalidad;
    Button btnFragRegistroRegistrar;

    private OnFragmentInteractionListener mListener;

    public RegistroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroFragment newInstance(String param1, String param2) {
        RegistroFragment fragment = new RegistroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_registro, container, false);

        this.txtFragRegistroEmail = this.view.findViewById(R.id.txtFragRegistroEmail);
        this.txtFragRegistroPass = this.view.findViewById(R.id.txtFragRegistroPass);
        this.txtFragRegistroRePass = this.view.findViewById(R.id.txtFragRegistroRePass);
        this.txtFragRegistroRut = this.view.findViewById(R.id.txtFragRegistroRut);
        this.txtFragRegistroNombre = this.view.findViewById(R.id.txtFragRegistroNombre);
        this.txtFragRegistroApellido = this.view.findViewById(R.id.txtFragRegistroApellido);
        this.rgFragRegistroSexo = this.view.findViewById(R.id.rgFragRegistroSexo);
        this.spFragRegistroLocalidad = this.view.findViewById(R.id.spFragRegistroLocalidad);

        this.btnFragRegistroRegistrar = this.view.findViewById(R.id.btnFragRegistroRegistrar);
        this.btnFragRegistroRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        return this.view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
