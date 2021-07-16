package com.intelliware.instantappdatagramsocket.ui.home;

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

        String text = "This is home fragment";
        textView.setText(text);

        openDatagramSocket();

        return root;
    }

    public void openDatagramSocket() {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();
            Log.i(TAG, "Created datagramSocket: "+datagramSocket);
        } catch (SocketException e1) {
            Log.w(TAG, "Failed to create datagram socket: "+e1);
            e1.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}