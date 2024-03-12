package com.example.roomdatabase.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdatabase.R
import com.example.roomdatabase.model.User
import com.example.roomdatabase.viewmodel.UserViewModel

class AddFragment : Fragment() {
    private lateinit var myUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        myUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val fName = view?.findViewById<EditText>(R.id.editTextFName)?.text.toString()
        val lName = view?.findViewById<EditText>(R.id.editTextLName)?.text.toString()
        val age = view?.findViewById<EditText>(R.id.editTextAge)?.text.toString().toInt()

        if(inputCheck(fName, lName, age)) {
            val user = User(0, fName, lName, Integer.parseInt(age.toString()))

            myUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else {
            Toast.makeText(requireContext(), "Please fill out all the fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(fName: String, lName: String, age: Int): Boolean {
        return !(TextUtils.isEmpty(fName) && TextUtils.isEmpty(lName) && TextUtils.isEmpty(age.toString()))
    }
}