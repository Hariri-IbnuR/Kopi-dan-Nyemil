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

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin?.setOnClickListener {
            val usernameInput = etUsername?.text?.toString()?.trim().orEmpty()
            val passwordInput = etPassword?.text?.toString()?.trim().orEmpty()

            // 1. Akses SharedPreferences "UserPref"
            val sharedPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)

            // 2. Ambil username & password yang tersimpan saat registrasi
            val savedUsername = sharedPref.getString("KEY_USERNAME", "")
            val savedPassword = sharedPref.getString("KEY_PASSWORD", "")

            // 3. Validasi input
            if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(this, "Username dan password tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else if (usernameInput == savedUsername && passwordInput == savedPassword && !savedUsername.isNullOrEmpty()) {
                // Login Berhasil
                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Login Gagal
                Toast.makeText(this, "Username atau Password salah!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}