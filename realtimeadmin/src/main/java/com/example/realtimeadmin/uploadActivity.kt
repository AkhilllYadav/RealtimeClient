package com.example.realtimeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtimeadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class uploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener{
            val ownername = binding.ownerName.text.toString()
            val vehicalBrand = binding.vehicalBrand.text.toString()
            val vehicalRTO = binding.vehicalRTO.text.toString()
            val vehicalNumber = binding.vehicalNumber.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Vehical Information")
            val vehicalData = VehicalData( ownername , vehicalBrand,vehicalRTO,vehicalNumber )
            databaseReference.child(vehicalNumber).setValue(vehicalData).addOnSuccessListener {
                binding.ownerName.text.clear()
                binding.vehicalBrand.text.clear()
                binding.vehicalRTO.text.clear()
                binding.vehicalNumber.text.clear()

                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@uploadActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }

    }
}