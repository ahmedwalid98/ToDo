package com.example.todo.ui.edit

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.database.Todo
import com.example.todo.database.TodoDatabase
import com.example.todo.databinding.FragmentEditBinding
import com.example.todo.repository.MyRepository
import com.example.todo.util.clearAll
import com.example.todo.viewmodels.MainViewModel
import com.example.todo.viewmodels.MainViewModelFactory
import java.util.*


class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    private lateinit var calendar: Calendar
    private lateinit var viewModel: MainViewModel




    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(inflater, container, false)
        val args = EditFragmentArgs.fromBundle(requireArguments())
        val database = TodoDatabase.getInstance(requireContext())
        val repository = MyRepository(database)
        val mainViewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(),mainViewModelFactory).get(MainViewModel::class.java)
        binding.task = args.task
        calendar = Calendar.getInstance()
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
        binding.saveButton.setOnClickListener {
            if (!validate()) return@setOnClickListener
            calendar.clearAll()
            val todo= Todo(
                id=args.task.id,
                title = binding.titleEditText.editText?.text.toString(),
                description = binding.descriptionEditText.editText?.text.toString(),
                date = calendar.time,
                isDone = false
            )
            viewModel.updateTask(todo)
            findNavController().popBackStack()
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