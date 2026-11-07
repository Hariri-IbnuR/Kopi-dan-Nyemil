# ☕ Kopi dan Nyemil

> _Get a taste of your wish._

**Kopi dan Nyemil** adalah aplikasi Android sederhana untuk memesan kopi dan camilan. Pengguna dapat mendaftar, masuk, memilih menu, mengatur jumlah pesanan, mengisi alamat pengiriman, lalu menerima konfirmasi pesanan. Aplikasi ini dibuat sebagai proyek mata kuliah Pemrograman Aplikasi Mobile (PAM).

---

## 📖 Deskripsi Aplikasi

Aplikasi ini menggambarkan alur pemesanan makanan dan minuman dari awal sampai akhir: dari halaman pembuka, registrasi dan login, pemilihan menu, pengisian alamat, sampai konfirmasi pesanan. Seluruh data disimpan secara lokal di perangkat memakai `SharedPreferences`, sehingga aplikasi bisa berjalan tanpa koneksi internet maupun server.

## ✨ Fitur

- **Splash Screen**: halaman pembuka dengan tombol _Start now_.
- **Registrasi**: pendaftaran akun (nama lengkap, username, password) dengan validasi kolom tidak boleh kosong.
- **Login**: autentikasi menggunakan data akun yang tersimpan saat registrasi.
- **Daftar Menu Interaktif**: kartu menu yang dapat dibuka/ditutup (expand/collapse) dengan animasi untuk melihat deskripsi produk.
- **Atur Jumlah Pesanan**: tombol **+** dan **−** pada setiap menu (jumlah minimum 0).
- **Total Harga Otomatis**: total dihitung langsung dan ditampilkan dalam format Rupiah (contoh: `Rp 57.000`).
- **Tombol Order Cerdas**: tombol _Order_ nonaktif selama total pesanan masih Rp 0.
- **Form Alamat Pengiriman**: nama lengkap, alamat, dan patokan/detail lokasi, dengan validasi input.
- **Konfirmasi Pesanan**: pesan terima kasih yang menyapa pengguna dengan namanya.
- **Profil Pengguna**: ubah dan simpan nama, yang juga dipakai untuk sapaan di halaman lain.
- **Navigasi Bawah**: tombol Home, Order, dan Profile pada halaman profil.

### 🍽️ Daftar Menu

| Menu                | Keterangan                 | Harga     |
| ------------------- | -------------------------- | --------- |
| Kopi Susu Gula Aren | Espresso, susu, gula aren  | Rp 22.000 |
| Donat Cokelat       | Donat empuk, glaze cokelat | Rp 12.000 |
| Sourdough Loaf      | Roti fermentasi alami      | Rp 35.000 |
| Cappuccino          | Espresso, foam susu lembut | Rp 25.000 |
| Matcha Latte        | Matcha Jepang, susu segar  | Rp 27.000 |

## 🔄 Alur Aplikasi

```
Main (Test) → Splash → Sign Up ─┬→ Register → Login → Home → Address → Confirmation → Home
                                └→ Login ──────────────↗      ↕
                                                           Profile
```

## 🛠️ Teknologi yang Digunakan

| Komponen             | Keterangan                                                                     |
| -------------------- | ------------------------------------------------------------------------------ |
| **Bahasa**           | Kotlin                                                                         |
| **Platform**         | Android (View System dengan XML layout)                                        |
| **IDE**              | Android Studio                                                                 |
| **UI Library**       | AndroidX AppCompat, Material Components (`MaterialCardView`, `MaterialButton`) |
| **Penyimpanan Data** | `SharedPreferences` (`UserPref` dan `KopiNyemil`)                              |
| **Animasi**          | `TransitionManager` + `AutoTransition`                                         |
| **Tampilan**         | Edge-to-Edge dengan `WindowInsets`                                             |
| **Package**          | `lat.pam.kopidannyemil`                                                        |

## 📁 Struktur Proyek

```
app/src/main/
├── java/lat/pam/kopidannyemil/
│   ├── MainActivity.kt          # Halaman awal untuk menguji alur aplikasi
│   ├── SplashActivity.kt        # Halaman pembuka
│   ├── SignUpActivity.kt        # Pilihan Register / Login
│   ├── RegisterActivity.kt      # Pendaftaran akun
│   ├── LoginActivity.kt         # Masuk ke aplikasi
│   ├── HomeActivity.kt          # Daftar menu & perhitungan total
│   ├── AddressActivity.kt       # Form alamat pengiriman
│   ├── ConfirmationActivity.kt  # Konfirmasi pesanan
│   └── ProfileActivity.kt       # Pengaturan profil
└── res/layout/
    ├── activity_main.xml
    ├── activity_splash.xml
    ├── activity_signup.xml
    ├── activity_register.xml
    ├── activity_login.xml
    ├── activity_home.xml
    ├── activity_address.xml
    ├── activity_confirmation.xml
    └── activity_profile.xml
```

## 🚀 Cara Menjalankan Aplikasi

### Prasyarat

- [Android Studio](https://developer.android.com/studio) versi terbaru
- JDK 11 atau lebih baru (sudah termasuk di Android Studio)
- Emulator Android atau perangkat fisik dengan mode _USB debugging_ aktif

### Langkah-langkah

1. **Clone repository**
   ```bash
   git clone
   ```
2. **Buka proyek** di Android Studio melalui `File > Open`, lalu pilih folder proyek.
3. **Tunggu Gradle Sync** sampai selesai (pastikan terhubung ke internet saat pertama kali).
4. **Siapkan perangkat**: buat emulator lewat _Device Manager_, atau sambungkan HP Android lewat kabel USB.
5. **Jalankan aplikasi** dengan menekan tombol ▶️ **Run** (atau `Shift + F10`).

### Cara Menggunakan

1. Tekan tombol **Start now** di halaman Splash.
2. Pilih **Register** untuk membuat akun baru, lalu isi nama lengkap, username, dan password.
3. Masuk lewat halaman **Login** dengan akun yang baru dibuat.
4. Di halaman **Home**, ketuk kartu menu untuk melihat detail, lalu tambahkan jumlah dengan tombol **+**.
5. Tekan **Order**, isi alamat pengiriman, lalu tekan **Order dan Kirim**.
6. Halaman konfirmasi akan muncul. Tekan **Oke** untuk kembali ke Home.

> **Catatan:** Akun tersimpan secara lokal di perangkat. Jika data aplikasi dihapus atau aplikasi di-uninstall, akun harus didaftarkan ulang.

## 📸 Screenshot

|            Splash            |           Sign Up            |           Register           |
| :--------------------------: | :--------------------------: | :--------------------------: |
| <img width="200" alt="ss01" src="https://github.com/user-attachments/assets/7f54bbfa-4f72-46fe-ab23-aa7fd198c3e8" /> |<img width="200" alt="ss02" src="https://github.com/user-attachments/assets/d83faa1d-af35-415e-813f-338fe18536a6" /> |<img width="200" alt="ss03" src="https://github.com/user-attachments/assets/e6b277d4-1e57-4df6-b4e6-b9ff384890e8" /> |

|            Login             |   Home        |            Alamat            |
| :--------------------------: | :--------------------------: | :--------------------------: |
| <img width="200" alt="ss04" src="https://github.com/user-attachments/assets/b87447db-3cfd-4028-9e60-77b96b966c98" /> | <img width="200" src="https://github.com/user-attachments/assets/6cd0980f-ecc8-4eae-99d2-3ad8facc8e66" /> | <img width="200" alt="ss06" src="https://github.com/user-attachments/assets/9db95139-14e4-4ba9-8b8b-efcf044397c4" /> |

|          Konfirmasi          |            Profil            |
| :--------------------------: | :--------------------------: |
| <img width="200" src="https://github.com/user-attachments/assets/14f77bc0-fafe-4cec-9561-56911d08c3aa" /> | <img width="200" src="https://github.com/user-attachments/assets/4c9f17bd-e786-4143-9559-f00022508685" />|

---

## 👤 Pembuat

- **Nama:** _Ibnu Rafiq Hariri_
- **NIM:** _1247050068_
- **Mata Kuliah:** Pemrograman Aplikasi Mobile

---

_Dibuat dengan Kotlin._








