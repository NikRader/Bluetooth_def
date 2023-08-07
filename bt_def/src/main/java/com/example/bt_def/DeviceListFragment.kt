package com.example.bt_def

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.bt_def.databinding.FragmentListBinding
import com.google.android.material.snackbar.Snackbar


class DeviceListFragment : Fragment() {
    private lateinit var itemAdapter: ItemAdapter
    private var bAdapter: BluetoothAdapter? = null
    private lateinit var binding: FragmentListBinding
    private lateinit var btLauncher: ActivityResultLauncher<Intent>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bluetoothBt.setOnClickListener(){
            btLauncher.launch(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))

        }
        registerBtLauncher()
        initBtAdapter()
        bluetoothState()

    }
    private fun initRcViews() = with(binding){
      recyclerView

    }

    private fun initBtAdapter(){
        val bManager = activity?.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bAdapter = bManager.adapter
    }
    private fun bluetoothState(){
        if(bAdapter?.isEnabled==true) {
            changeButtonColor(binding.bluetoothBt, Color.GREEN)
             }
        }
    private fun registerBtLauncher(){
        btLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){

            if(it.resultCode == Activity.RESULT_OK){

                changeButtonColor(binding.bluetoothBt, Color.GREEN)
                Snackbar.make(binding.root, "Блютуз включен!", Snackbar.LENGTH_LONG).show()

            } else{
                Snackbar.make(binding.root, "Блютуз выключен!", Snackbar.LENGTH_LONG).show()

            }
        }

    }
    }



