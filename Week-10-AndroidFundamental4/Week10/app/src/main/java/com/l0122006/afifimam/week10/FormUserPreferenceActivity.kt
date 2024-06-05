package com.l0122006.afifimam.week10

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.l0122006.afifimam.week10.databinding.ActivityFormUserPreferenceBinding

class FormUserPreferenceActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFormUserPreferenceBinding

    companion object {
        const val EXTRA_TYPE_FORM = "extra_type_form"
        const val EXTRA_RESULT = "extra_result"
        const val RESULT_CODE = 101

        const val TYPE_ADD = 1
        const val TYPE_EDIT = 2

        private const val FIELD_REQUIRED = "Field tidak boleh kosong"
        private const val FIELD_DIGIT_ONLY = "Hanya boleh terisi numerik"
        private const val FIELD_IS_NOT_VALID = "Email tidak valid"
    }

    private lateinit var userModel: UserModel
    private var selectedImageUri: Uri? = null
    private val imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            if (data != null && data.data != null) {
                selectedImageUri = data.data
                Glide.with(this)
                    .load(selectedImageUri)
                    .into(binding.ivProfilePicture)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormUserPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener(this)
        binding.btnChoosePicture.setOnClickListener(this)

        userModel = intent.getParcelableExtra<UserModel>("USER") as UserModel
        val formType = intent.getIntExtra(EXTRA_TYPE_FORM, 0)

        var actionBarTitle = ""
        var btnTitle = ""

        when (formType) {
            TYPE_ADD -> {
                actionBarTitle = "Tambah Baru"
                btnTitle = "Simpan"
            }
            TYPE_EDIT -> {
                actionBarTitle = "Ubah"
                btnTitle = "Update"
                showPreferenceInForm()
            }
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSave.text = btnTitle
    }

    private fun showPreferenceInForm() {
        binding.edtName.setText(userModel.name)
        binding.edtNim.setText(userModel.nim)
        binding.edtEmail.setText(userModel.email)
        binding.edtAge.setText(userModel.age.toString())
        binding.edtPhone.setText(userModel.phoneNumber)
        if (userModel.gender) {
            binding.rbLaki.isChecked = true
        } else {
            binding.rbPr.isChecked = true
        }

        val profilePictureUrl = userModel.profilePictureUrl
        if (!profilePictureUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(profilePictureUrl)
                .into(binding.ivProfilePicture)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_save -> {
                val name = binding.edtName.text.toString().trim()
                val nim = binding.edtNim.text.toString().trim()
                val email = binding.edtEmail.text.toString().trim()
                val age = binding.edtAge.text.toString().trim()
                val phoneNo = binding.edtPhone.text.toString().trim()
                val gender = binding.rgGender.checkedRadioButtonId == R.id.rb_laki

                if (name.isEmpty()) {
                    binding.edtName.error = FIELD_REQUIRED
                    return
                }
                if (nim.isEmpty()) {
                    binding.edtNim.error = FIELD_REQUIRED
                    return
                }

                if (email.isEmpty()) {
                    binding.edtEmail.error = FIELD_REQUIRED
                    return
                }

                if (!isValidEmail(email)) {
                    binding.edtEmail.error = FIELD_IS_NOT_VALID
                    return
                }

                if (age.isEmpty()) {
                    binding.edtAge.error = FIELD_REQUIRED
                    return
                }

                if (phoneNo.isEmpty()) {
                    binding.edtPhone.error = FIELD_REQUIRED
                    return
                }

                if (!TextUtils.isDigitsOnly(phoneNo)) {
                    binding.edtPhone.error = FIELD_DIGIT_ONLY
                    return
                }

                saveUser(name, nim, email, age, phoneNo, gender)

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_RESULT, userModel)
                setResult(RESULT_CODE, resultIntent)

                finish()
            }
            R.id.btn_choose_picture -> {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                imagePickerLauncher.launch(intent)
            }
        }
    }

    private fun saveUser(name: String, nim: String, email: String, age: String, phoneNo: String, gender: Boolean) {
        val userPreference = UserPreference(this)

        userModel.name = name
        userModel.nim = nim
        userModel.email = email
        userModel.age = Integer.parseInt(age)
        userModel.phoneNumber = phoneNo
        userModel.gender = gender
        userModel.profilePictureUrl = selectedImageUri?.toString() ?: userModel.profilePictureUrl

        userPreference.setUser(userModel)
        Toast.makeText(this, "Data tersimpan", Toast.LENGTH_SHORT).show()
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
