package com.fquintal.test.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.fquintal.test.R
import com.fquintal.test.databinding.FragmentLoginBinding
import com.fquintal.test.exception.PasswordEmptyLoginException
import com.fquintal.test.exception.UserNameEmptyLoginException
import com.fquintal.test.exception.UsernameAndPasswordWrongException
import com.fquintal.test.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.net.UnknownHostException

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    private var snackBar: Snackbar? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = DataBindingUtil.bind<FragmentLoginBinding>(view)
        binding!!.login = viewModel.login
        binding.viewModel = viewModel
        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    is UserNameEmptyLoginException -> {
                        binding.userNameTextInputLayout.error =
                            getString(R.string.error_username_is_empty)
                        binding.passwordTextInputLayout.error = null
                        binding.userNameTextInputLayout.requestFocus()
                    }
                    is PasswordEmptyLoginException -> {
                        binding.passwordTextInputLayout.error =
                            getString(R.string.error_password_is_empty)
                        binding.userNameTextInputLayout.error = null
                        binding.passwordTextInputLayout.requestFocus()
                    }
                    is UsernameAndPasswordWrongException -> {
                        binding.userNameTextInputLayout.error = null
                        binding.passwordTextInputLayout.error = null
                        Snackbar.make(
                            view,
                            R.string.error_username_and_password_wrong,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    is UnknownHostException ->{
                        binding.userNameTextInputLayout.error = null
                        binding.passwordTextInputLayout.error = null
                        Snackbar.make(
                            view,
                            R.string.error_connect_to_server,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    else -> {
                        Snackbar.make(view, it.message.toString(), Snackbar.LENGTH_SHORT).show()
                    }
                }
            } else {
                binding.userNameTextInputLayout.error = null
                binding.passwordTextInputLayout.error = null
            }

        }
        snackBar = Snackbar.make(view, R.string.label_logging_session, Snackbar.LENGTH_INDEFINITE)

        observeLogin()
        observeLogging(binding)
    }

    private fun observeLogin() {
        viewModel.loggedIn.observe(viewLifecycleOwner) {
            if (it != null && it) {
                Navigation.findNavController(activity as FragmentActivity, R.id.nav_host_fragment)
                    .navigate(R.id.action_loginFragment_to_movementFragment)
            }
        }
    }

    private fun observeLogging(fragmentLoginBinding: FragmentLoginBinding){
        viewModel.logging.observe(viewLifecycleOwner){
            if(it != null && it){
                fragmentLoginBinding.loginButton.isEnabled = false
                snackBar!!.show()
            }
            else{
                fragmentLoginBinding.loginButton.isEnabled = true
                snackBar!!.dismiss()
            }
        }
    }

}