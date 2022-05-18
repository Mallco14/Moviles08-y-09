package tecsp.edu.mallcco.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tecsp.edu.mallcco.data.User
import tecsp.edu.mallcco.util.SharedPreferenceUtil

class RegisterViewModel(private val context: Context): ViewModel(){
    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    val emptyFieldsError = MutableLiveData<Boolean>()
    val fieldsAuthenticateError = MutableLiveData<Boolean>()
    val goalSuccesActivity = MutableLiveData<Boolean>()

    fun onCreate() {
        sharedPreferenceUtil = SharedPreferenceUtil().also {
            it.setSharedPreference(context)
        }
    }

    fun validateInputs(name: String, email: String, password: String, password2: String){
        if (name.isNotEmpty() || email.isNotEmpty() || password.isNotEmpty() || password2.isNotEmpty()){
            if(password == password2){
                register(name, email, password)
                goalSuccesActivity.postValue(true)
            }else{
                fieldsAuthenticateError.postValue(true)
            }
        }
        else{
            emptyFieldsError.postValue(true)
        }
    }
    fun register(name: String, email: String, password: String){
        val user = User (
            "1",
            name,
            email,
            password,
        )
        sharedPreferenceUtil.saveFacebookUser (user)
    }
}