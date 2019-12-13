package com.frament


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adapter.ProductMainAdapter
import com.model.ProductResponse
import com.rest.ApiInitialize
import com.rest.ApiRequest
import com.rest.ApiResponseInterface
import com.rest.ApiResponseManager
import com.solidindia.R
import com.solidindia.activity.MainActivity
import com.utils.getLanguageType
import com.utils.isNetWork


class HomeFragment : BaseFrament(), ApiResponseInterface {
    var TAG: String = javaClass.simpleName

    private var rootView: View? = null
    private var searchView: SearchView? = null
    private lateinit var rvProductMain: RecyclerView
    private lateinit var productMainAdapter: ProductMainAdapter
    private var productList: ArrayList<ProductResponse.Data>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        initIDs(rootView!!)
        var langTyape = sessionManager["type", "en"]
        Log.e(TAG, "LanType-->$langTyape")

        getLanguageType(activity!!,langTyape)
        //callProductListAPI(langTyape)
        return rootView
    }

    override fun initComponent() {

    }

    override fun initToolbar() {

    }

    override fun initListeners() {

    }

    override fun initData() {

    }

    override fun initIDs(rootView: View) {
        rvProductMain = rootView.findViewById(R.id.rvProductMain)

    }

    private fun setAdpater() {
        ///val productList: ArrayList<ProductResponse.Data.Category> = ArrayList()

        productMainAdapter = ProductMainAdapter(activity!!, activity!!, productList!!)
        rvProductMain.adapter = productMainAdapter
        rvProductMain.layoutManager = LinearLayoutManager(activity)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)

        // Associate searchable configuration with the SearchView
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search).getActionView() as SearchView
        searchView!!.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView!!.setMaxWidth(Integer.MAX_VALUE)

        // listening to search query text change
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                productMainAdapter.getFilter().filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                productMainAdapter.getFilter().filter(query)
                return false
            }
        })

        searchView!!.setOnCloseListener(SearchView.OnCloseListener {
            (context as MainActivity).ivbarToolbar.visibility = View.VISIBLE
            false
        })

        searchView!!.setOnSearchClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                (context as MainActivity).ivbarToolbar.visibility = View.GONE
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_search) {
            true
        } else if (id == R.id.menuEng) {
            sessionManager.put("type", "en")

            getLanguageType(activity!!,"en")
            //setLocale("en")
            reCreateFragment()
            // callProductListAPI("en")
            true
        } else if (id == R.id.menuArabian) {
            sessionManager.put("type", "ar")
            //setLocale("ar")
            getLanguageType(activity!!,"ar")
            reCreateFragment()
            //callProductListAPI("ar")
            true
        } else if (id == R.id.menuSpanish) {
            sessionManager.put("type", "sp")
            //setLocale("es")
            getLanguageType(activity!!,"sp")
            reCreateFragment()
            //callProductListAPI("sp")
            true
        } else if (id == R.id.menuChinese) {
            sessionManager.put("type", "ch")
            getLanguageType(activity!!,"ch")
            //setLocale("zh")
            reCreateFragment()
            //callProductListAPI("ch")
            true
        } else if (id == R.id.menuFrench) {
            sessionManager.put("type", "fr")
            //setLocale("fr")
            getLanguageType(activity!!,"fr")
            reCreateFragment()
            //callProductListAPI("fr")
            true
        } else if (id == R.id.menuTamil) {
            sessionManager.put("type", "tm")
            getLanguageType(activity!!,"tm")
            //setLocale("ta")
            reCreateFragment()
           // callProductListAPI("tm")
            true
        } else if (id == R.id.menuTelugu) {
            sessionManager.put("type", "te")
            getLanguageType(activity!!,"te")
            //callProductListAPI("te")
            //setLocale("te")
            reCreateFragment()
            true
        } else super.onOptionsItemSelected(item)

    }

    private fun reCreateFragment() {
        activity?.recreate()
        val fm = activity?.supportFragmentManager
        fm?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }


    override fun getApiResponse(apiResponseManager: ApiResponseManager<*>) {
        when (apiResponseManager.type) {
            100 -> {
                var model: ProductResponse
                model = apiResponseManager.response as ProductResponse
                Log.e(TAG, "Event List Response:- ${model}")
                if (model.statusCode == 200) {
                    productList = ArrayList()
                    productList?.addAll(model.data)
                    // productMainAdapter.notifyDataSetChanged()
                    setAdpater()
                }
            }
        }
    }

    override fun onFailure(
        apiResponseManager: ApiResponseManager<*>,
        error_message: String,
        error: Boolean
    ) {
        if (!error) {
            showSnackBar(error_message)
        }
    }

    private fun callProductListAPI(type: String) {
        if (isNetWork(activity!!)) {
            ApiRequest(
                activity!!, ApiInitialize.initialize(ApiInitialize.MAIN_URL_API).productList(type),
                100, true, this
            )
        } else {
            showSnackBar(activity!!.resources.getString(com.solidindia.R.string.internet_not_available))
        }

    }

  /*  fun setLocale(localeString: String) {
        val res = resources
        val conf = res.configuration
        val locale = Locale(localeString)
        Locale.setDefault(locale)
        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(locale)
            context?.getApplicationContext()?.createConfigurationContext(conf)
        }

        val dm = res.displayMetrics
        if (VERSION.SDK_INT >= VERSION_CODES.N) {
            conf.locales = LocaleList(locale)
        } else {
            conf.locale = locale
        }
        res.updateConfiguration(conf, dm)
    }*/

   /* fun getLanguageType(type: String){
        Log.e(TAG,"Selecetd Language Type---$type")
        if(type.equals("en")) {
            setLocale(activity!!,"en")
        }else if (type.equals("ar")) {
            setLocale(activity!!,"ar")
        }else if (type.equals("sp")) {
            setLocale(activity!!,"es")
        }else if (type.equals("ch")) {
            setLocale(activity!!,"zh")
        }else if (type.equals("fr")) {
            setLocale(activity!!,"fr")
        }else if (type.equals("tm")) {
            setLocale(activity!!,"ta")
        }else if (type.equals("te")) {
            setLocale(activity!!,"te")
        }
    }*/
}
