package com.example.registerapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class FormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val mainLayout = findViewById<LinearLayout>(R.id.mainLayout)
        mainLayout.setBackgroundResource(R.drawable.background_form)

        val name = findViewById<EditText>(R.id.name)
        val email = findViewById<EditText>(R.id.email2)
        val password = findViewById<EditText>(R.id.password2)
        val confirm = findViewById<EditText>(R.id.confirm)

        val genderGroup = findViewById<RadioGroup>(R.id.genderGroup)
        val jurusan = findViewById<Spinner>(R.id.jurusan)

        val h1 = findViewById<CheckBox>(R.id.h1)
        val h2 = findViewById<CheckBox>(R.id.h2)
        val h3 = findViewById<CheckBox>(R.id.h3)
        val h4 = findViewById<CheckBox>(R.id.h4)
        val h5 = findViewById<CheckBox>(R.id.h5)

        val btn = findViewById<Button>(R.id.btnSubmit)

        // Spinner Jurusan
        val listJurusan = arrayOf(
            "Pilih Jurusan",
            "Informatika",
            "Sistem Informasi",
            "Teknik Komputer",
            "Manajemen"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listJurusan)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        jurusan.adapter = adapter

        btn.setOnClickListener {

            if (name.text.isEmpty()) {
                toast("Nama tidak boleh kosong")
                return@setOnClickListener
            }

            if (password.text.toString() != confirm.text.toString()) {
                toast("Password tidak sama")
                return@setOnClickListener
            }

            if (genderGroup.checkedRadioButtonId == -1) {
                toast("Pilih gender")
                return@setOnClickListener
            }

            if (jurusan.selectedItem.toString() == "Pilih Jurusan") {
                toast("Pilih jurusan")
                return@setOnClickListener
            }

            val selectedGenderId = genderGroup.checkedRadioButtonId
            val selectedGender = findViewById<RadioButton>(selectedGenderId).text

            val hobiList = mutableListOf<String>()
            if (h1.isChecked) hobiList.add("Coding")
            if (h2.isChecked) hobiList.add("Gaming")
            if (h3.isChecked) hobiList.add("Musik")
            if (h4.isChecked) hobiList.add("Traveling")
            if (h5.isChecked) hobiList.add("Olahraga")

            AlertDialog.Builder(this)
                .setTitle("Data Anda")
                .setMessage(
                    "Nama: ${name.text}\n" +
                            "Email: ${email.text}\n" +
                            "Gender: $selectedGender\n" +
                            "Jurusan: ${jurusan.selectedItem}\n" +
                            "Hobi: ${hobiList.joinToString()}"
                )
                .setPositiveButton("OK") { _, _ ->
                    toast("Berhasil")
                }
                .show()
        }
    }

    fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}