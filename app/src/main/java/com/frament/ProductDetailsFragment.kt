package com.frament


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.solidindia.activity.MainActivity
import com.adapter.MyViewPagerAdapter
import com.bumptech.glide.Glide
import com.model.ProductResponse
import com.solidindia.R
import kotlinx.android.synthetic.main.row_sub_product.view.*
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adapter.ProductMainAdapter
import com.adapter.ProductSubAdapter
import java.lang.StringBuilder


class ProductDetailsFragment : BaseFrament(), View.OnClickListener {
    private val TAG: String = javaClass.simpleName
    private var rootView: View? = null
    private lateinit var tvBack: TextView
    private lateinit var vpSlider: ViewPager
    private var productData: ProductResponse.Data.Category.Product? = null
    private lateinit var ivSubImage: ImageView
    private lateinit var tvProductName: TextView
    private lateinit var tvProductSubName: TextView
    private lateinit var btnPrice: Button
    private lateinit var btnShare: Button
    private lateinit var tvHighlightCapacityValue: TextView
    private lateinit var tvHighlightDrumDiaValue: TextView
    private lateinit var tvHighlightHotMixValue: TextView
    private lateinit var tvHighlightCompect: TextView
    private lateinit var rvProductDetails: RecyclerView
    private var productSubdapter: ProductSubAdapter?=null
    private var productSubList: ArrayList<ProductResponse.Data.Category.Product>? = null

    private var myViewPagerAdapter: MyViewPagerAdapter? = null
    var introSliderList: ArrayList<ProductResponse.Data.Category.Product.ProductImage>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
        productData = appcontroller.getProductData()
        Log.e(TAG, "Product data-->" + appcontroller.getProductData())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragmnet_product_details, container, false)
        initIDs(rootView!!)
        initData()
        initListeners()
        setAdapter()
        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun initComponent() {

    }

    override fun initToolbar() {

    }

    override fun initListeners() {
        tvBack.setOnClickListener(this)
        btnShare.setOnClickListener(this)
        btnPrice.setOnClickListener(this)
    }

    override fun initData() {
        if (productData?.productImage?.size!! > 0) {
            Glide.with(context)
                .load(productData?.productImage?.get(0)?.path)
                .error(R.mipmap.ic_launcher)
                .into(ivSubImage)
        }

        tvProductName.text = productData?.productName
        tvProductSubName.text = productData?.capacity
        tvHighlightCapacityValue.text = productData?.capacity
        tvHighlightDrumDiaValue.text = productData?.drumDiameter
        tvHighlightHotMixValue.text = productData?.storage
        tvHighlightCompect.text = productData?.highlights
    }

    override fun initIDs(rootView: View) {
        tvBack = rootView.findViewById(R.id.tvBack)
        vpSlider = rootView.findViewById(R.id.vpSlider)
        ivSubImage = rootView.findViewById(R.id.ivSubImage)
        tvProductName = rootView.findViewById(R.id.tvProductName)
        tvProductSubName = rootView.findViewById(R.id.tvProductSubName)
        btnPrice = rootView.findViewById(R.id.btnPrice)
        btnShare = rootView.findViewById(R.id.btnShare)
        tvHighlightCapacityValue = rootView.findViewById(R.id.tvHighlightCapacityValue)
        tvHighlightDrumDiaValue = rootView.findViewById(R.id.tvHighlightDrumDiaValue)
        tvHighlightHotMixValue = rootView.findViewById(R.id.tvHighlightHotMixValue)
        tvHighlightCompect = rootView.findViewById(R.id.tvHighlightCompect)
        rvProductDetails = rootView.findViewById(R.id.rvProductDetails)

    }

    override fun onClick(view: View?) {
        when (view) {
            tvBack -> {
                (context as MainActivity).fragmentHandling()
            }
            btnShare->{
                shareIntent()
            }
            btnPrice->{
                val phoneNumberWithCountryCode = "+919624777773"
                val message = "Solid India"

                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            String.format(
                                "https://api.whatsapp.com/send?phone=%s&text=%s",
                                phoneNumberWithCountryCode, shareData()
                            )
                        )
                    )
                )
            }
        }
    }

    private fun setAdapter() {
        introSliderList = ArrayList()
        introSliderList!!.addAll(productData?.productImage as ArrayList)
        myViewPagerAdapter = MyViewPagerAdapter(activity, introSliderList)
        vpSlider.setAdapter(myViewPagerAdapter);
        vpSlider.addOnPageChangeListener(viewPagerPageChangeListener);

        Log.e(TAG,"getMainAdapterPost->" + appcontroller.getMainAdapterPost() )
        Log.e(TAG,"getSubAdapterPost->" + appcontroller.getSubAdapterPost() )

        productSubList = ArrayList()
        productSubList?.addAll(appcontroller.getDataList()[appcontroller.getMainAdapterPost()].category.product)
        productSubdapter = ProductSubAdapter(activity!!, activity!!, productSubList!!,appcontroller.getMainAdapterPost(),false)
        rvProductDetails.adapter = productSubdapter
        rvProductDetails.layoutManager = LinearLayoutManager(activity)

    }

    var viewPagerPageChangeListener: ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(arg0: Int) {}
        }

    private fun shareIntent() {



        val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, R.string.app_name)
        sharingIntent.putExtra(
            android.content.Intent.EXTRA_TEXT,shareData()
        )
        startActivity(Intent.createChooser(sharingIntent, "Share app via"))
    }


    private fun shareData():String{
        var stringBuilder = StringBuilder()
        stringBuilder.append(productData?.productName)
        stringBuilder.append(" ")
        stringBuilder.append(productData?.price)
        stringBuilder.append(" ")
        stringBuilder.append(productData?.capacity)
        stringBuilder.append(" ")
        stringBuilder.append(productData?.drumDiameter)
        stringBuilder.append(" ")
        stringBuilder.append("Solid india pvt ltd")
        stringBuilder.append(" ")
        stringBuilder.append("http://www.solid1972.com")
        return stringBuilder.toString()

    }
}
