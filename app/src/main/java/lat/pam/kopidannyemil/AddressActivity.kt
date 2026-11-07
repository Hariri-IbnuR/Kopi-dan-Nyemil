package lat.pam.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_address)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNamaLengkap = findViewById<EditText>(R.id.etNamaLengkap)
        val etAlamat = findViewById<EditText>(R.id.etAlamat)
        val etPatokan = findViewById<EditText>(R.id.etPatokan)

        val prefs = getSharedPreferences("KopiNyemil", MODE_PRIVATE)
        val namaTersimpan = prefs.getString("nama", "") ?: ""
        if (namaTersimpan.isNotBlank()) etNamaLengkap.setText(namaTersimpan)

        val btnOrderKirim = findViewById<Button>(R.id.btnOrderKirim)
        btnOrderKirim.setOnClickListener {
            val nama = etNamaLengkap.text.toString().trim()
            val alamat = etAlamat.text.toString().trim()
            val patokan = etPatokan.text.toString().trim()

            if (nama.isEmpty()) {
                etNamaLengkap.error = "Nama lengkap harus diisi"
            } else if (alamat.isEmpty()) {
                etAlamat.error = "Alamat harus diisi"
            } else {
                prefs.edit()
                    .putString("nama_penerima", nama)
                    .putString("alamat", alamat)
                    .putString("patokan", patokan)
                    .apply()

                startActivity(Intent(this, ConfirmationActivity::class.java))
            }
        }
    }
}
