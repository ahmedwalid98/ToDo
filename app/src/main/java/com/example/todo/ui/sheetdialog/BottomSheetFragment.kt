package com.example.todo.ui.sheetdialog

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.todo.database.Todo
import com.example.todo.database.TodoDatabase
import com.example.todo.databinding.FragmentBottomSheetBinding
import com.example.todo.repository.MyRepository
import com.example.todo.util.clearAll
import com.example.todo.viewmodels.MainViewModel
import com.example.todo.viewmodels.MainViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var calendar: Calendar
    private lateinit var binding: FragmentBottomSheetBinding
    private lateinit var viewModel: MainViewModel
    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        calendar = Calendar.getInstance()
        val database = TodoDatabase.getInstance(requireContext())
        val repository = MyRepository(database)
        val mainViewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(),mainViewModelFactory).get(MainViewModel::class.java)
        binding.dateButton.text =
            "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${
                calendar.get(Calendar.YEAR)
            }"
        binding.dateButton.setOnClickListener {
            var datePicker = DatePickerDialog(
                requireContext(),
                { p0, year, month, day ->
                    calendar.set(year,month,day)
                    binding.dateButton.text =
                        "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${
                            calendar.get(Calendar.YEAR)
                        }"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.doneButton.setOnClickListener {
            if (!validate()) return@setOnClickListener
            calendar.clearAll()
            val todo= Todo(
                title = binding.titleEditText.editText?.text.toString(),
                description = binding.descriptionEditText.editText?.text.toString(),
                date = calendar.time,
                isDone = false
            )
            viewModel.insertTask(todo)
            dismiss()
        }

        return binding.root
    }

    private fun validate():Boolean{
        if (binding.titleEditText.editText?.text.toString().isEmpty()){
            binding.titleEditText.error = "Please Enter the title"
            return false
        }else{
            binding.titleEditText.error = null
        }
        if (binding.descriptionEditText.editText?.text.toString().isEmpty()){
            binding.descriptionEditText.error = "Please Enter the description"
            return false
        }else{
            binding.descriptionEditText.error = null
        }
        return true
    }


}