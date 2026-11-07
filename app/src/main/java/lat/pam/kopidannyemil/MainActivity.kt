package lat.pam.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Menghubungkan Button dari XML berdasarkan ID-nya
        val btnTest = findViewById<Button>(R.id.btnTest)

        // 2. Menambahkan aksi klik pada button
        btnTest.setOnClickListener {
            // 3. Membuat Intent untuk berpindah ke SplashActivity
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
        }
    }
}