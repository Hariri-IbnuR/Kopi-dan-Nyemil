package lat.pam.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prefs = getSharedPreferences("KopiNyemil", MODE_PRIVATE)
        val tvGreeting = findViewById<TextView>(R.id.tvGreeting)
        val etNama = findViewById<EditText>(R.id.etNama)
        val btnSaveProfile = findViewById<Button>(R.id.btnSaveProfile)

        val namaTersimpan = prefs.getString("nama", "") ?: ""
        if (namaTersimpan.isNotBlank()) {
            etNama.setText(namaTersimpan)
            tvGreeting.text = "Halo $namaTersimpan,"
        }

        btnSaveProfile.setOnClickListener {
            val nama = etNama.text.toString().trim()
            if (nama.isEmpty()) {
                etNama.error = "Nama tidak boleh kosong"
            } else {
                prefs.edit().putString("nama", nama).apply()
                tvGreeting.text = "Halo $nama,"
                Toast.makeText(this, "Profile berhasil disimpan", Toast.LENGTH_SHORT).show()
            }
        }

        val btnHome = findViewById<Button>(R.id.btnHome)
        val btnOrder = findViewById<Button>(R.id.btnOrder)
        val btnProfile = findViewById<Button>(R.id.btnProfile)

        btnHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        btnOrder.setOnClickListener {
            startActivity(Intent(this, AddressActivity::class.java))
        }
        btnProfile.setOnClickListener {
            // Sudah berada di halaman Profile
        }
    }
}
