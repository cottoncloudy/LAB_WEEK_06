package com.example.lab_week_06

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    private val catAdapter by lazy {
        // Glide is used here to load the images
        // Here we are passing the onClickListener function to the Adapter
        CatAdapter(
            layoutInflater,
            GlideImageLoader(this),
            object: CatAdapter.OnClickListener {
                // When this is triggered, the pop up dialog will be shown
                override fun onItemClick(cat: CatModel) = showSelectionDialog(cat)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup the adapter for the recycler view
        recyclerView.adapter = catAdapter

        // Setup the layout manager for the recycler view
        // A layout manager is used to set the structure of the item views
        // For this tutorial, we're using the vertical linear structure
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        // Instantiate ItemTouchHelper for the swipe to delete callback and
// attach it to the recycler view
        val itemTouchHelper = ItemTouchHelper(catAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // Add data to the model list in the adapter
        catAdapter.setData(
            listOf(
                CatModel(Gender.Male, CatBreed.BalineseJavanese, "Asep", "Suka tidur di atas motor yang baru dipake.", "https://cdn2.thecatapi.com/images/7dj.jpg"),
                CatModel(Gender.Male, CatBreed.ExoticShorthair, "Mulyono", "Kalem tapi doyan ngintip kulkas.", "https://cdn2.thecatapi.com/images/egv.jpg"),
                CatModel(Gender.Unknown, CatBreed.AmericanCurl, "Fufufafa", "Namanya aneh, tingkahnya lebih aneh lagi.", "https://cdn2.thecatapi.com/images/bar.jpg"),
                CatModel(Gender.Male, CatBreed.BalineseJavanese, "Sugiono", "Senior di komplek, disegani semua kucing.", "https://cdn2.thecatapi.com/images/MTY3ODIyMQ.jpg"),
                CatModel(Gender.Male, CatBreed.ExoticShorthair, "Acab", "Galak kalau lapar, manja kalau kenyang.", "https://cdn2.thecatapi.com/images/3j4.jpg"),
                CatModel(Gender.Unknown, CatBreed.AmericanCurl, "Embege", "Punya suara meong yang mirip kambing.", "https://cdn2.thecatapi.com/images/6it.jpg"),
                CatModel(Gender.Female, CatBreed.ExoticShorthair, "Bowoendut", "Gendut tapi gesit kalo dengar suara kaleng whiskas.", "https://cdn2.thecatapi.com/images/9b0.jpg"),
                CatModel(Gender.Male, CatBreed.BalineseJavanese, "Omkegas", "Suka kabur ke rumah tetangga buat numpang makan.", "https://cdn2.thecatapi.com/images/8hf.jpg"),
                CatModel(Gender.Female, CatBreed.AmericanCurl, "Megachan", "Kalem tapi sering jadi rebutan jantan komplek.", "https://cdn2.thecatapi.com/images/b3p.jpg"),
                CatModel(Gender.Male, CatBreed.ExoticShorthair, "Bahlul", "Lompat ke akuarium, nyesel, tapi tetep diulang besok.", "https://cdn2.thecatapi.com/images/cdu.jpg")
            )
        )
    }

    // This will create a pop up dialog when one of the items from the recycler view is clicked
    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this)
            // Set the title for the dialog
            .setTitle("Cat Selected")
            // Set the message for the dialog
            .setMessage("You have selected cat ${cat.name}")
            // Set if the OK button should be enabled
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }
}