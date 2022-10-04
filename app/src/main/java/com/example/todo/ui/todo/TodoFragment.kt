package com.example.todo.ui.todo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.database.Todo
import com.example.todo.database.TodoDatabase
import com.example.todo.databinding.FragmentTodoBinding
import com.example.todo.repository.MyRepository
import com.example.todo.util.clearAll
import com.example.todo.viewmodels.MainViewModel
import com.example.todo.viewmodels.MainViewModelFactory
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import java.util.*


class TodoFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: TodoAdapter
    var selectedDate = CalendarDay.today()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentTodoBinding.inflate(inflater, container, false)
        val database = TodoDatabase.getInstance(requireContext())
        val repository = MyRepository(database)
        val mainViewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(
            requireActivity(),
            mainViewModelFactory
        ).get(MainViewModel::class.java)


        adapter = TodoAdapter(OnItemCLickedListener { task ->

            viewModel.updateTask(
                Todo(
                    id = task.id,
                    title = task.title,
                    description = task.description,
                    date = task.date,
                    isDone = true
                )
            )

        }, OnItemCLickedListener {
            Log.i("hahah", it.id.toString())
            findNavController().navigate(TodoFragmentDirections.actionTodoFragmentToEditFragment(it))
        },OnItemCLickedListener{task ->
            viewModel.deleteTask(task)
        })
        binding.recyclerView.adapter = adapter
        getData(currentDate.time)
        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            selectedDate = date
            currentDate.set(selectedDate.year, selectedDate.month - 1, selectedDate.day)

            getData(currentDate.time)

        }

        binding.calendarView.selectedDate = selectedDate
        return binding.root
    }

    private val currentDate = Calendar.getInstance();

    init {
        currentDate.set(selectedDate.year, selectedDate.month - 1, selectedDate.day)
        currentDate.clearAll()
    }

    private fun getData(date: Date) {
        viewModel.taskByDay(date).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

}