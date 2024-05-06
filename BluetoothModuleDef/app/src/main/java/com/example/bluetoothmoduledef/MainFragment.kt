package com.example.bluetoothmoduledef

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bluetoothmoduledef.databinding.FragmentMainBinding
import com.example.bt_def.BluetoothConstans
import com.example.bt_def.bluetooth.BluetoothController

class MainFragment : Fragment(), BluetoothController.Listener {

   private lateinit var binding: FragmentMainBinding
    private lateinit var bluetoothController: BluetoothController
    private lateinit var btAdapter: BluetoothAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBtAdapter()
        val pref = activity?.getSharedPreferences(
        BluetoothConstans.PREFERENCES, Context.MODE_PRIVATE)
        val mac = pref?.getString(BluetoothConstans.MAC, "")
        bluetoothController = BluetoothController(btAdapter)

        binding.bList.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_deviceListFragment)
        }
        binding.connectBt.setOnClickListener(){
            bluetoothController.connect(mac?: "", this)
        }
    }

    private fun initBtAdapter() {
        val bManager = activity?.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        btAdapter = bManager.adapter
    }

    override fun onReceive(message: String) {
        activity?.runOnUiThread {
            when(message){
               BluetoothController.BLUETOOTH_CONNECTED ->{
                    binding.connectBt.backgroundTintList = AppCompatResources
                        .getColorStateList(requireContext(), com.example.bt_def.R.color.red)
                            binding.connectBt.text ="Disconnect"
               }
                BluetoothController.BLUETOOTH_NO_CONNECTED ->{
                    binding.connectBt.backgroundTintList = AppCompatResources
                        .getColorStateList(requireContext(), com.example.bt_def.R.color.green)
                    binding.connectBt.text ="Connect"

                }
                else ->{


                }
            }
        }
    }
}