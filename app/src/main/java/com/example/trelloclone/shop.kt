package com.example.trelloclone

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trelloclone.adapters.AllProductsAdapter
import com.example.trelloclone.constants.Constants
import com.example.trelloclone.databinding.FragmentShopBinding
import com.example.trelloclone.models.ShoppingModels.AllProducts
import com.example.trelloclone.network.ProductServices
import retrofit.Call
import retrofit.Callback
import retrofit.GsonConverterFactory
import retrofit.Response
import retrofit.Retrofit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [shop.newInstance] factory method to
 * create an instance of this fragment.
 */
class shop : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var binding: FragmentShopBinding? = null
    private var mContext: Context? = null

    private var mProgressDialog: Dialog? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopBinding.inflate(inflater, container, false)

        getAllProducts(mContext!!, binding!!)
        return binding?.root
    }

    fun getAllProducts(context: Context, binding: FragmentShopBinding) {
        if (Constants.isNetworkAvailable(context)) {

            val retrofit: Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service: ProductServices =
                retrofit.create(ProductServices::class.java)
            val listCall: Call<AllProducts> = service.getAllProducts()

            listCall.enqueue(object : Callback<AllProducts> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    response: Response<AllProducts>,
                    retrofit: Retrofit
                ) {
                    Log.e("Current Response", "${response.body()}")
                    Log.e("Data Retrofit", "${response.body()}")
                    Log.e("Data Response", "${response.isSuccess}")

                    // Check weather the response is success or not.
                    if (response.isSuccess) {
                        hideProgressDialog()
                        /** The de-serialized response body of a successful response. */
                        val userList: AllProducts = response.body()
                        val adapter = AllProductsAdapter(userList)
                        binding?.shopGridView?.layoutManager = GridLayoutManager(mContext, 2)
                        // from Object class
                        binding?.shopGridView?.adapter = adapter

                        Log.i("Response GRID MADHAN Result", "$userList")
//                        val weatherResponseJsonString = Gson().toJson(userList)
                        // Save the converted string to shared preferences
//                        val editor = mSharedPreferences.edit()
//                        editor.putString(Constants.WEATHER_RESPONSE_DATA, weatherResponseJsonString)
//                        editor.apply()
//                        setupUI()
                    } else {
                        // If the response is not success then we check the response code.
                        val sc = response.code()
                        when (sc) {
                            400 -> {
                                Log.e("Error 400", "Bad Request")
                            }

                            404 -> {
                                Log.e("Error 404", "Not Found")
                            }

                            else -> {
                                Log.e("Error", "Generic Error")
                            }
                        }
                    }
                }

                override fun onFailure(t: Throwable) {
                    Log.e("Errorrrrr", t.message.toString())
                    hideProgressDialog()

                }
            })


        } else {

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment shop.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            shop().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun showCustomProgressDialog() {
        mProgressDialog = Dialog(mContext!!)

        /*Set the screen content from a layout resource.
        The resource will be inflated, adding all top-level views to the screen.*/
        mProgressDialog!!.setContentView(R.layout.custom_dialog)

        //Start the dialog and display it on screen.
        mProgressDialog!!.show()
    }

    private fun hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
        }
    }
}