//import android.app.Dialog
//import android.graphics.Color
//import android.graphics.drawable.ColorDrawable
//import android.os.Bundle
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.app.AppCompatDelegate
//import com.example.bori.R
//
//class Start : AppCompatActivity(){
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_start)
//
//        val toSignUp: TextView = findViewById(R.id.start_signUpButton)
//
////        toSignUp.setOnClickListener {
////            val intent = Intent(this, SignUp::class.java)
////            startActivity(intent)
////        }
//
//        toSignUp.setOnClickListener {
//            val dialogView = layoutInflater.inflate(R.layout.activity_get_item_modal, null)
//
//            val addBucketDialog = Dialog(this)
//            addBucketDialog.setContentView(dialogView)
//
//            addBucketDialog.setCancelable(true)
//            addBucketDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            addBucketDialog.show()
//
//            val xButton = dialogView.findViewById<ImageButton>(R.id.certifyingShot_xButton)
//            xButton.setOnClickListener{
//                addBucketDialog.dismiss()
//            }
//        }
