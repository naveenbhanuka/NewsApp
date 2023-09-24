package com.example.newsapp.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsapp.databinding.FragmentProfileBinding
import com.example.newsapp.domain.model.User
import com.example.newsapp.presentation.home.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment: Fragment() {

    companion object {
        const val TAG = "profileFragment"
        fun newInstance() = ProfileFragment()
    }

    private lateinit var binding: FragmentProfileBinding
    private val vm by viewModel<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = vm.getUser(requireContext())
        user?.let {
            updateUI(it)
        }
    }

    private fun updateUI(user:User) {
        binding.etFirstName.setText(user.firstName)
        binding.etLastName.setText(user.lastName)
        binding.etEmail.setText(user.email)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}