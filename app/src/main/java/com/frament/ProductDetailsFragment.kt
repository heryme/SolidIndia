package com.frament


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.adapter.MyViewPagerAdapter
import com.adapter.ProductSubAdapter
import com.frament.HomeFragment.Companion.tempList
import com.model.ProductResponse
import com.solidindia.R
import com.solidindia.activity.MainActivity
import com.utils.*


class ProductDetailsFragment : BaseFrament(), View.OnClickListener {
    private val TAG: String = javaClass.simpleName
    private var rootView: View? = null
    private lateinit var tvBack: TextView
    private lateinit var vpSlider: ViewPager
    private var productData: ProductResponse.Data.Category.Product? = null
    private lateinit var ivSubImage: ImageView
    private lateinit var tvProductName: TextView
    private lateinit var tvProductSubName: TextView
    private lateinit var btnPrice: TextView
    private lateinit var btnShare: TextView
    private lateinit var tvHighlightCapacityValue: TextView
    private lateinit var tvHighlightDrumDiaValue: TextView
    private lateinit var tvHighlightHotMixValue: TextView
    private lateinit var tvHighlightCompect: TextView
    private lateinit var rvProductDetails: RecyclerView
    private lateinit var ivPdf: ImageView
    private lateinit var ivFacebook: ImageView
    private lateinit var ivInsta: ImageView
    private lateinit var ivYoutube: ImageView
    private var productSubdapter: ProductSubAdapter? = null
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
        initToolbar()
        initData()
        initListeners()
        setAdapter()
        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val item = menu.findItem(R.id.filter)
        item.setVisible(false)
    }

    override fun initComponent() {

    }

    override fun initToolbar() {
        (context as MainActivity).ivbarToolbar.visibility = View.GONE
        (context as MainActivity).llBackMain.visibility = View.VISIBLE
    }

    override fun initListeners() {
        tvBack.setOnClickListener(this)
        btnShare.setOnClickListener(this)
        btnPrice.setOnClickListener(this)
        ivPdf.setOnClickListener(this)
        ivYoutube.setOnClickListener(this)
        ivFacebook.setOnClickListener(this)
        ivInsta.setOnClickListener(this)
        //(context as MainActivity).ivbarToolbar.setOnClickListener(this)

    }

    override fun initData() {
        val capacity = getString(R.string.capacity)
        if (productData?.productImage?.size!! > 0) {

            productData?.productImage?.get(0)?.path?.let {
                loadImage(
                    it,
                    appcontroller.getAppContext()!!, ivSubImage,
                    R.mipmap.ic_launcher
                )
            }
            /*Glide.with(context)
                .load(productData?.productImage?.get(0)?.path)
                .error(R.mipmap.ic_launcher)
                .into(ivSubImage)*/
        }

        tvProductName.text = productData?.productName
        tvProductSubName.text = capacity + " " + productData?.capacity
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
        ivPdf = rootView.findViewById(R.id.ivPdf)
        ivFacebook = rootView.findViewById(R.id.ivFacebook)
        ivYoutube = rootView.findViewById(R.id.ivYoutube)
        ivInsta = rootView.findViewById(R.id.ivInsta)

    }

    override fun onClick(view: View?) {
        when (view) {
            (context as MainActivity).ivbarToolbar -> {
                //(context as MainActivity).fragmentHandling()
            }
            btnShare -> {
                shareIntent()
            }
            ivPdf -> {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(WEB_URL))
                startActivity(browserIntent)
            }
            ivFacebook -> {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(FB_URL))
                startActivity(browserIntent)
            }
            ivYoutube -> {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUE_URL))
                startActivity(browserIntent)
            }
            ivInsta->{
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(INSTA_URL))
                startActivity(browserIntent)
            }
            btnPrice -> {
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
        myViewPagerAdapter = MyViewPagerAdapter(activity!!, activity!!, introSliderList!!)
        vpSlider.setAdapter(myViewPagerAdapter);
        vpSlider.addOnPageChangeListener(viewPagerPageChangeListener);

        var hun = getResources().getDimensionPixelSize(R.dimen._20sdp);
        if (introSliderList?.size!! > 1) {
            vpSlider.setClipToPadding(false)
            vpSlider.setPadding(0, 0, hun, 0)
        }else{
            vpSlider.setClipToPadding(true)
            vpSlider.setPadding(0, 0, 0, 0)
        }
        Log.e(TAG, "getMainAdapterPost->" + appcontroller.getMainAdapterPost())
        Log.e(TAG, "getSubAdapterPost->" + appcontroller.getSubAdapterPost())

        productSubList = ArrayList()
        productSubList?.addAll(tempList/*appcontroller.getDataList()[appcontroller.getMainAdapterPost()].category.product*/)
        productSubdapter = ProductSubAdapter(
            activity!!,
            activity!!,
            productSubList!!,
            appcontroller.getMainAdapterPost(),
            false
        )
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
            android.content.Intent.EXTRA_TEXT, shareData()
        )
        startActivity(Intent.createChooser(sharingIntent, "Share app via"))
    }


    private fun shareData(): String {
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
