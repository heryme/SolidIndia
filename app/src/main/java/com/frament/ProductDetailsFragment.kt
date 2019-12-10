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
        tvHighlightHotMixValue.text = productData?.drumDiameter
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

    }

    override fun onClick(view: View?) {
        when (view) {
            tvBack -> {
                (context as MainActivity).fragmentHandling()
            }
        }
    }

    private fun setAdapter() {
        introSliderList = ArrayList()
        introSliderList!!.addAll(productData?.productImage as ArrayList)
        myViewPagerAdapter = MyViewPagerAdapter(activity, introSliderList)
        vpSlider.setAdapter(myViewPagerAdapter);
        vpSlider.addOnPageChangeListener(viewPagerPageChangeListener);
    }

    var viewPagerPageChangeListener: ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(arg0: Int) {}
        }
}
