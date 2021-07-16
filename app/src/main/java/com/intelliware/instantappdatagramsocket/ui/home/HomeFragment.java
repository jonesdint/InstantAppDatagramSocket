package com.intelliware.instantappdatagramsocket.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.intelliware.instantappdatagramsocket.R;
import com.intelliware.instantappdatagramsocket.databinding.FragmentHomeBinding;

import java.net.DatagramSocket;
import java.net.SocketException;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;

        StringBuffer buffer = new StringBuffer();
        buffer.append("Simple app that opens a DatagramSocket\n");
        buffer.append("Expected it to work for both Apps and for Instant Apps.\n");
        buffer.append("Please run for both the 'App' and the 'Instant App' Run Configurations.\n");
        buffer.append("See HomeFragment class for DatagramSocket usage.\n");
        buffer.append("\n--\n\n");

        boolean isInstantApp = getContext().getPackageManager().isInstantApp();
        buffer.append("Running as Instant App = " + isInstantApp +"\n");

        boolean isInternetPermissionEnabled = getContext().checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
        buffer.append("INTERNET permission enabled = "+isInternetPermissionEnabled+"\n");

        buffer.append("\n--\n");
        buffer.append("Creating DatagramSocket....\n");

        String openDatagramSocketResult = openDatagramSocket();
        buffer.append(openDatagramSocketResult);

        buffer.append("\n");
        buffer.append("See app log for more details");

        textView.setText(buffer.toString());

        openDatagramSocket();

        return root;
    }

    public String openDatagramSocket() {
        Log.i(TAG, "_-----------------------------------------------");
        Log.i(TAG, "Creating DatagramSocket....");
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            Log.i(TAG, "Created datagramSocket: "+datagramSocket+" OK");
            return "Datagram socket created OK";
        } catch (SocketException e1) {
            Log.w(TAG, "Failed to create datagram socket: "+e1);
            e1.printStackTrace();
            return "Datagram socket failed with exception: "+e1;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}