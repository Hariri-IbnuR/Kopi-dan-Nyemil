package lat.pam.kopidannyemil

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etFullName = findViewById<EditText>(R.id.etFullName)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister?.setOnClickListener {
            val fullName = etFullName?.text?.toString()?.trim().orEmpty()
            val username = etUsername?.text?.toString()?.trim().orEmpty()
            val password = etPassword?.text?.toString()?.trim().orEmpty()

            // Validasi input agar tidak ada yang kosong
            if (fullName.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
                // 1. Inisialisasi SharedPreferences dengan file bernama "UserPref"
                val sharedPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()

                // 2. Simpan data menggunakan key-value
                editor.putString("KEY_FULLNAME", fullName)
                editor.putString("KEY_USERNAME", username)
                editor.putString("KEY_PASSWORD", password)
                editor.apply()

                Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show()

                // 3. Pindah ke LoginActivity dan tutup RegisterActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}