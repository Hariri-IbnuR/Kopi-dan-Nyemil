package lat.pam.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import java.text.NumberFormat
import java.util.Locale

class HomeActivity : AppCompatActivity() {

    private lateinit var llFoodList: LinearLayout
    private lateinit var tvTotalOrder: TextView
    private lateinit var btnOrder: MaterialButton
    private lateinit var btnProfile: MaterialButton

    // Jumlah pesanan per kartu. Key = id kartu, value = jumlah.
    private val orderCounts = mutableMapOf<Int, Int>()

    // Harga satuan per kartu (dibaca dari XML). Key = id kartu, value = harga.
    private val prices = mutableMapOf<Int, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        llFoodList = findViewById(R.id.llFoodList)
        tvTotalOrder = findViewById(R.id.tvTotalOrder)
        btnOrder = findViewById(R.id.btnOrder)
        btnProfile = findViewById(R.id.btnProfile)

        // Daftarkan setiap kartu makanan (harga dibaca dari tvPriceX di XML)
        setupFoodCard(R.id.cardFood1, R.id.layoutDetail1, R.id.btnMinus1, R.id.tvQty1, R.id.btnPlus1, R.id.tvPrice1)
        setupFoodCard(R.id.cardFood2, R.id.layoutDetail2, R.id.btnMinus2, R.id.tvQty2, R.id.btnPlus2, R.id.tvPrice2)
        setupFoodCard(R.id.cardFood3, R.id.layoutDetail3, R.id.btnMinus3, R.id.tvQty3, R.id.btnPlus3, R.id.tvPrice3)
        setupFoodCard(R.id.cardFood4, R.id.layoutDetail4, R.id.btnMinus4, R.id.tvQty4, R.id.btnPlus4, R.id.tvPrice4)
        setupFoodCard(R.id.cardFood5, R.id.layoutDetail5, R.id.btnMinus5, R.id.tvQty5, R.id.btnPlus5, R.id.tvPrice5)

        // Kondisi awal: total Rp 0 -> tombol Order nonaktif
        updateTotalAndOrderButton()

        btnOrder.setOnClickListener {
            val intent = Intent(this, AddressActivity::class.java)
            intent.putExtra("TOTAL_PRICE", calculateTotalPrice()) // opsional
            startActivity(intent)
        }

        btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    private fun setupFoodCard(
        cardId: Int,
        detailId: Int,
        minusId: Int,
        qtyId: Int,
        plusId: Int,
        priceId: Int
    ) {
        val card = findViewById<MaterialCardView>(cardId)
        val layoutDetail = findViewById<View>(detailId)
        val btnMinus = findViewById<MaterialButton>(minusId)
        val btnPlus = findViewById<MaterialButton>(plusId)
        val tvQty = findViewById<TextView>(qtyId)
        val tvPrice = findViewById<TextView>(priceId)

        orderCounts[cardId] = 0
        // Ambil angka dari teks XML: "Rp 22.000" -> 22000 (teks di layar tidak diubah)
        prices[cardId] = tvPrice.text.toString().filter { it.isDigit() }.toIntOrNull() ?: 0
        tvQty.text = "0"

        // Klik kartu: toggle detail dengan animasi
        card.setOnClickListener {
            TransitionManager.beginDelayedTransition(llFoodList, AutoTransition())
            layoutDetail.visibility = if (layoutDetail.isVisible) View.GONE else View.VISIBLE
        }

        // Tombol tambah (+)
        btnPlus.setOnClickListener {
            val newQty = (orderCounts[cardId] ?: 0) + 1
            orderCounts[cardId] = newQty
            tvQty.text = newQty.toString()
            updateTotalAndOrderButton()
        }

        // Tombol kurang (-), minimum 0
        btnMinus.setOnClickListener {
            val current = orderCounts[cardId] ?: 0
            if (current > 0) {
                val newQty = current - 1
                orderCounts[cardId] = newQty
                tvQty.text = newQty.toString()
                updateTotalAndOrderButton()
            }
        }
    }

    /** Total harga = jumlah x harga satuan, dijumlahkan untuk semua menu. */
    private fun calculateTotalPrice(): Int {
        return orderCounts.entries.sumOf { (cardId, qty) ->
            qty * (prices[cardId] ?: 0)
        }
    }

    /** 57000 -> "Rp 57.000" */
    private fun formatRupiah(amount: Int): String {
        val formatter = NumberFormat.getNumberInstance(Locale("in", "ID"))
        return "Rp ${formatter.format(amount)}"
    }

    /** Update kolom Total pesanan (jadi total harga) dan status tombol Order. */
    private fun updateTotalAndOrderButton() {
        val totalPrice = calculateTotalPrice()
        tvTotalOrder.text = formatRupiah(totalPrice)

        val canOrder = totalPrice > 0
        btnOrder.isEnabled = canOrder
        btnOrder.alpha = if (canOrder) 1f else 0.4f
    }
}