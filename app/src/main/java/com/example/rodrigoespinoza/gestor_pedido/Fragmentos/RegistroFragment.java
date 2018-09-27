package com.example.rodrigoespinoza.gestor_pedido.fragmentos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rodrigoespinoza.gestor_pedido.R;
import com.example.rodrigoespinoza.gestor_pedido.entitties.Person;
import com.example.rodrigoespinoza.gestor_pedido.entitties.SqlConecttion;
import com.example.rodrigoespinoza.gestor_pedido.entitties.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    User usuario;
    Person persona;

    EditText txtFragRegistroEmail, txtFragRegistroPass, txtFragRegistroRePass;
    EditText txtFragRegistroRut, txtFragRegistroNombre, txtFragRegistroApellido;
    String sexoSeleccionado, localidad;
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
        this.usuario = new User();
        this.persona = new Person();

        this.txtFragRegistroEmail = (EditText) this.view.findViewById(R.id.txtFragRegistroEmail);
        this.txtFragRegistroPass = (EditText) this.view.findViewById(R.id.txtFragRegistroPass);
        this.txtFragRegistroRePass = (EditText) this.view.findViewById(R.id.txtFragRegistroRePass);
        this.txtFragRegistroRut = (EditText) this.view.findViewById(R.id.txtFragRegistroRut);
        this.txtFragRegistroNombre = (EditText) this.view.findViewById(R.id.txtFragRegistroNombre);
        this.txtFragRegistroApellido = (EditText) this.view.findViewById(R.id.txtFragRegistroApellido);
        this.rgFragRegistroSexo = (RadioGroup) this.view.findViewById(R.id.rgFragRegistroSexo);
        this.rgFragRegistroSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbFragRegistroHombre){
                    sexoSeleccionado = "Masculino";
                } else {
                    sexoSeleccionado = "Femenino";
                }
            }
        });
        this.spFragRegistroLocalidad = (Spinner) this.view.findViewById(R.id.spFragRegistroLocalidad);

        List localidades = new ArrayList<>();
        localidades.add("Santiago");
        localidades.add("Indepencia");
        localidades.add("Conchali");
        localidades.add("Huechuraba");
        localidades.add("Recoleta");
        localidades.add("Providencia");
        localidades.add("Vitacura");
        localidades.add("Lo Barnechea");
        localidades.add("Las Condes");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, localidades);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        this.spFragRegistroLocalidad.setAdapter(arrayAdapter);

        this.spFragRegistroLocalidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                localidad = spFragRegistroLocalidad.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.btnFragRegistroRegistrar = (Button) this.view.findViewById(R.id.btnFragRegistroRegistrar);
        this.btnFragRegistroRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaPassword(txtFragRegistroPass.getText().toString(), txtFragRegistroRePass.getText().toString())){
                    if (validaRut(txtFragRegistroRut.getText().toString())){

                        usuario.setEmail(txtFragRegistroEmail.getText().toString());
                        usuario.setPass(txtFragRegistroPass.getText().toString());
                        persona.setRut(txtFragRegistroRut.getText().toString());
                        persona.setName(txtFragRegistroNombre.getText().toString());
                        persona.setLast_name(txtFragRegistroApellido.getText().toString());
                        persona.setSexo(sexoSeleccionado);
                        persona.setLocation(localidad);
                        persona.setId_user(registrarUsuario(usuario));

                        if (registrarPersona(persona) != 0) {
                            Toast.makeText(getContext(), "Registrado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Ha ocurrido un problema, intentelo mas tarde", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(),"Rut Invalido", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(),"Contraseñas Incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return this.view;
    }

    private Integer registrarPersona(Person persona) {
        SqlConecttion conexion = new SqlConecttion(getContext(), "bd_gestor_pedidos", null, 1);
        SQLiteDatabase dataBase = conexion.getWritableDatabase();
        try {
            ContentValues nuevaPersona = new ContentValues();
            nuevaPersona.put("rut", persona.getRut());
            nuevaPersona.put("name", persona.getName());
            nuevaPersona.put("last_name", persona.getLast_name());
            nuevaPersona.put("sexo", persona.getSexo());
            nuevaPersona.put("location", persona.getLocation());
            nuevaPersona.put("id_user", persona.getId_user());
            Long id = dataBase.insert("person", "id", nuevaPersona);
            Toast.makeText(getContext(), id.toString(), Toast.LENGTH_SHORT).show();
            dataBase.close();
            conexion.close();
            return Integer.parseInt(id.toString());
        } catch (Exception ex) {
            dataBase.close();
            conexion.close();
            Toast.makeText(getContext(),"No pude registrar, " + ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
            return 0;
        } finally {
            dataBase.close();
        }
    }

    private Integer registrarUsuario(User usuario) {
        SqlConecttion conexion = new SqlConecttion(getContext(), "bd_gestor_pedidos", null, 1);
        SQLiteDatabase dataBase = conexion.getWritableDatabase();
        try {
            ContentValues nuevoUsuario = new ContentValues();
            nuevoUsuario.put("email", usuario.getEmail());
            nuevoUsuario.put("pass", usuario.getPass());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            nuevoUsuario.put("fecha", dateFormat.format(date));
            Long id = dataBase.insert("user", "id",nuevoUsuario);
            //Toast.makeText(getContext(), id.toString(), Toast.LENGTH_SHORT).show();
            dataBase.close();
            conexion.close();
            return Integer.parseInt(id.toString());
        } catch (Exception ex) {
            dataBase.close();
            conexion.close();
            Toast.makeText(getContext(),"No pude registrar, " + ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
            return 0;
        } finally {
            dataBase.close();
        }
    }

    private boolean validaRut(String rut) {
        boolean valida = false;
        rut = rut.replace(".","");
        rut = rut.replace("-","");
        try {
            Integer rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
            char dv = rut.charAt(Integer.parseInt(rut.substring(rut.length() - 1, rut.length())));
            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10)
            {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                valida = true;
            }
            return valida;
        } catch (Exception ex){
            return valida;
        }
    }

    private boolean validaPassword(String password, String rePassword) {
        if(password.equals(rePassword)){
            return true;
        } else {
            return false;
        }
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
